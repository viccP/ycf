package com.ycf.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import com.alibaba.fastjson.JSON;
import com.ycf.bean.MainForm;
import com.ycf.cst.CST;
import com.ycf.dao.tables.daos.TmUserDao;
import com.ycf.service.EmailService;
import com.ycf.utils.Ajax;

/**
 * 
 * ClassName: IndexAction <br/>
 * Function: index控制器. <br/>
 * date: 2017年9月29日 下午1:19:07 <br/>
 * 
 * @author liboqiang
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/index")
public class IndexAction {

	@Autowired
	private TmUserDao tmUserDao;

	@Autowired
	private EmailService emailService;

	/**
	 * 
	 * login:(登录). <br/>
	 * 
	 * @author liboqiang
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String login() {

		return JSON.toJSONString(tmUserDao.fetchByUserId(CST.USER_ID_DEFAULT));
	}

	/**
	 * 
	 * login:(登录). <br/>
	 * 
	 * @author liboqiang
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String apply(MainForm apply) {
		try {
			
			//操作文件夹
			String uniID=UUID.randomUUID().toString();
			String dstDir = CST.UPLOAD_DIR + "/upload_tmp_" + uniID;
			String attachFile=CST.UPLOAD_DIR+"/申请文件"+uniID+".zip";

			// 1.制作上传文件夹
			mkUploadDir(apply,dstDir);
			
			// 2.替换word文档
			mkWord(apply,dstDir);
			
//			// 3.压缩并发送
//			zipDir(attachFile,dstDir);
//			emailService.setFromUser("大帅哥");
//			emailService.setToUser("爱你的么么");
//			emailService.setSubject("这是标题哦");
//			emailService.setFile(new File(attachFile));
//			emailService.setAttchName("这是附件哦");
//			emailService.send();
//			
//			//4.删除文件
//			Runtime.getRuntime().exec("rm -rf "+attachFile);
//			
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
		String fileName=dstDir+"/车贷险资料.doc";
		HWPFDocument document = new HWPFDocument(new FileInputStream(new File(fileName)));
		Range range = (Range) document.getRange();
		System.out.println(range.text());
		document.close();
	}

	/**
	 * 
	 * zipDir:(压缩文件夹). <br/> 
	 * 
	 * @author liboqiang 
	 * @param dstDir 
	 * @param dstDir 
	 * @throws IOException 
	 * @since JDK 1.6
	 */
	private void zipDir(String sourceFile, String dstDir) throws IOException {
		String cmd="zip -r "+sourceFile+" "+dstDir;
		Runtime.getRuntime().exec(cmd);
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
		String cmd = "cp -r " + CST.UPLOAD_DIR + "/upload " + dstDir;
		Runtime.getRuntime().exec(cmd);
		
		// 分发文件
		for(Map.Entry<String, String> entry:CST.PIC_MAPPER.entrySet()) {
			Class<?> clazz = Class.forName("com.ycf.bean.MainForm");
			Class<?>[] parameterTypes = new Class[] {};
			Object[] methodPramas = new Object[] {};
			Method method = clazz.getMethod(entry.getKey(), parameterTypes);
			MultipartFile[] files =(MultipartFile[]) method.invoke(apply, methodPramas);
			if(files==null) {
				continue;
			}
			for(MultipartFile file : files) {
				File upFile = new File(dstDir + "/"+entry.getValue(), file.getOriginalFilename());
				file.transferTo(upFile);
			}
		}
	}
}
