package com.ycf.action;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycf.bean.UserForm;
import com.ycf.cst.CST;
import com.ycf.dao.tables.daos.TmUserDao;
import com.ycf.dao.tables.pojos.TmUser;
import com.ycf.page.Page;
import com.ycf.service.TmUserService;
import com.ycf.utils.Ajax;
import com.ycf.utils.MD5;
import com.ycf.utils.Session;

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
@RequestMapping(value = "/user")
public class UserAction {

	@Autowired
	private TmUserService tmUserService;

	@Autowired
	private TmUserDao tmUserDao;

	/**
	 * 
	 * add:(新增用户). <br/>
	 * 
	 * @author liboqiang
	 * @param tmUser
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String edit(@RequestBody TmUser tmUser) {
		try {
			if (tmUser.getSex() == null) {
				return Ajax.responseString(CST.RES_LOGIC_ERROR_2);
			}
			String status = tmUserService.edit(tmUser);
			return Ajax.responseString(status, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

	/**
	 * 
	 * list:(查询用户列表). <br/>
	 * 
	 * @author liboqiang
	 * @param tmUser
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String list(@RequestBody UserForm userForm) {
		try {
			// 0.身份认证
			if(!Session.isSuperAdmin()) {
				return Ajax.responseString(CST.RES_AUTO_DIALOG,"抱歉您没有权限");
			}
			
			// 1.查询数据
			Page<TmUser> pageBean = tmUserService.list(userForm);
			return Ajax.responseString(CST.RES_SUCCESS, pageBean, true);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

	/**
	 * 
	 * delete:(删除用户). <br/>
	 * 
	 * @author liboqiang
	 * @param userId
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String delete(String userId) {
		try {
			// 1.查询数据
			tmUserService.delete(userId);
			return Ajax.responseString(CST.RES_AUTO_DIALOG, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

	/**
	 * 
	 * get:(获取用户). <br/>
	 * 
	 * @author liboqiang
	 * @param userId
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String get(String userId) {
		try {
			// 1.查询数据
			TmUser tmUser = tmUserDao.fetchOneByUserId(userId);
			return Ajax.responseString(CST.RES_SUCCESS, tmUser);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

	@RequestMapping(value = "/initPwd", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String initPwd(String userId) {
		try {
			// 1.查询数据
			TmUser tmUser = tmUserDao.fetchOneByUserId(userId);
			tmUser.setPassword(MD5.getHash(CST.PWD_DEFAULT));
			tmUser.setPwdStatus(CST.PWD_STATUS_INIT);
			tmUser.setUpdTime(new Timestamp(System.currentTimeMillis()));
			tmUserDao.update(tmUser);
			return Ajax.responseString(CST.RES_AUTO_DIALOG, "初始化密码成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

}
