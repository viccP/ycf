package com.ycf.action;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ycf.bean.MainForm;
import com.ycf.cst.CST;
import com.ycf.service.EmailService;
import com.ycf.utils.Ajax;
import com.ycf.utils.CmdUtils;
import com.ycf.utils.Session;

/**
 * 
 * ClassName: ApplyAction <br/>
 * Function: 申请控制器. <br/>
 * date: 2017年10月9日 下午3:20:07 <br/>
 * 
 * @author liboqiang
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/apply")
public class ApplyAction {

	@Autowired
	private EmailService emailService;

	/**
	 * 
	 * apply:(提交申请). <br/>
	 * 
	 * @author liboqiang
	 * @param apply
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/doApply", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String apply(MainForm apply) {
		try {

			// 操作文件夹
			String userName = Session.getUser().getUserName();
			String uniID = UUID.randomUUID().toString();
			String dstDir = CST.UPLOAD_DIR + "/upload_tmp_" + uniID;
			String attachFile = userName + "(申请文件).zip";

			// 1.制作上传文件夹
			mkUploadDir(apply, dstDir);

			// 2.替换word文档
			mkWord(apply, dstDir);

			// 3.压缩并发送
			String[] cmds = { "/bin/sh", "-c", "cd " + dstDir + " && " + "zip -r " + attachFile + " ./*" };
			CmdUtils.exec(cmds);
			emailService.setFromUser(userName);
			emailService.setToUser("友车金融");
			emailService.setSubject("申请表");
			emailService.setFile(new File(dstDir + "/" + attachFile));
			emailService.setAttchName(attachFile);
			emailService.send();

			// 4.删除文件
			CmdUtils.exec("rm -rf " + dstDir);

			// 5.返回前台
			return Ajax.responseString(CST.RES_AUTO_DIALOG, "提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

	/**
	 * 
	 * mkWord:(制作word文档). <br/>
	 * 
	 * @author liboqiang
	 * @param apply
	 * @param dstDir
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @since JDK 1.6
	 */
	private void mkWord(MainForm apply, String dstDir) throws Exception {
		String fileName = dstDir + "/车贷险资料.doc";
		String desFileName = dstDir + "/des.doc";
		HWPFDocument document = new HWPFDocument(new FileInputStream(new File(fileName)));
		Range range = (Range) document.getRange();
		Class<?> clazz = apply.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			// 过滤上传图片
			if (String.class != field.getType()) {
				continue;
			}
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
			String fieldVal = (String) pd.getReadMethod().invoke(apply);
			fieldVal = (fieldVal == null ? "" : fieldVal);
			range.replaceText("${" + fieldName + "}", fieldVal);
		}

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(desFileName);
			document.write(out);

			// 删除文件、重命名文件
			CmdUtils.exec("rm -rf " + fileName);
			CmdUtils.exec("mv " + desFileName + " " + fileName);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			document.close();
		}
	}

	/**
	 * 
	 * mkUploadDir:(制作上传文件夹). <br/>
	 * 
	 * @author liboqiang
	 * @param apply
	 * @param dstDir
	 * @throws IOException
	 * @since JDK 1.6
	 */
	private void mkUploadDir(MainForm apply, String dstDir) throws Exception {
		// 1.拷贝模板文件夹
		CmdUtils.exec("cp -r " + CST.UPLOAD_DIR + "/upload " + dstDir);

		// 分发文件
		for (Map.Entry<String, String> entry : CST.PIC_MAPPER.entrySet()) {
			Class<?> clazz = apply.getClass();
			Class<?>[] parameterTypes = new Class[] {};
			Object[] methodPramas = new Object[] {};
			Method method = clazz.getMethod(entry.getKey(), parameterTypes);
			MultipartFile[] files = (MultipartFile[]) method.invoke(apply, methodPramas);
			if (files == null) {
				continue;
			}
			for (MultipartFile file : files) {
				File upFile = new File(dstDir + "/" + entry.getValue(), file.getOriginalFilename());
				file.transferTo(upFile);
			}
		}
	}

}
