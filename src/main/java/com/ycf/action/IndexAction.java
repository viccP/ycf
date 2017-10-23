package com.ycf.action;

import static com.ycf.dao.tables.TmUser.TM_USER;

import javax.servlet.http.HttpSession;

import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycf.cst.CST;
import com.ycf.dao.tables.pojos.TmUser;
import com.ycf.utils.Ajax;
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
@RequestMapping(value = "/index")
public class IndexAction {

	@Autowired
	private DefaultDSLContext dsl;

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
	public String login(String loginId, String password) {

		try {
			TmUser result = dsl.selectFrom(TM_USER).where(TM_USER.LOGIN_ID.eq(loginId))
					.and(TM_USER.PASSWORD.eq(password.toUpperCase())).fetchOneInto(TmUser.class);

			if (result != null) {
				Session.getSession().setAttribute("tmUser", result);
				return Ajax.responseString(CST.RES_SUCCESS);
			} else {
				return Ajax.responseString(CST.RES_LOGIC_ERROR_1, "用户名或密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

	/**
	 * 
	 * logout:(登出). <br/>
	 * 
	 * @author liboqiang
	 * @param session
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String logout(HttpSession session) {
		try {
			session.invalidate();
			return Ajax.responseString(CST.RES_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

	/**
	 * 检查session
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/checkSession", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String checkSession(HttpSession session) {
		try {
			if (session.getAttribute("tmUser") == null) {
				return Ajax.responseString(CST.RES_SESSION_TIME_OUT, "用户已经过期，请重新登陆");
			}
			return Ajax.responseString(CST.RES_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

	/**
	 * 
	 * modifyPwd:(修改密码). <br/>
	 * 
	 * @author liboqiang
	 * @param oldPwd
	 * @param newPwd
	 * @param confirmPwd
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/modifyPwd", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String modifyPwd(String oldPwd, String newPwd, String confirmPwd) {
		try {
			// 0.获取用户ID
			String userId = Session.getUser().getUserId();

			// 1.旧密码验证
			TmUser res = dsl.selectFrom(TM_USER).where(TM_USER.USER_ID.eq(userId))
					.and(TM_USER.PASSWORD.eq(oldPwd.toUpperCase())).fetchOneInto(TmUser.class);

			if (res == null) {
				return Ajax.responseString(CST.RES_LOGIC_ERROR_1, "旧密码验证错误");
			}

			// 2.密码一致性验证
			if (!newPwd.equals(confirmPwd)) {
				return Ajax.responseString(CST.RES_LOGIC_ERROR_2, "两次密码不一致,请重新输入");
			}

			// 3.修改密码
			dsl.update(TM_USER).set(TM_USER.PASSWORD, newPwd.toUpperCase()).set(TM_USER.PWD_STATUS, CST.PWD_STATUS_ALTERED)
					.where(TM_USER.USER_ID.eq(userId)).execute();
			

			return Ajax.responseString(CST.RES_AUTO_DIALOG, "密码修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}


	/**
	 * 
	 * vlidatePwd:(验证密码状态). <br/> 
	 * 
	 * @author liboqiang
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/vlidatePwd", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String vlidatePwd() {
		try {
			TmUser tmUser = dsl.selectFrom(TM_USER).where(TM_USER.USER_ID.eq(Session.getUser().getUserId()))
					.fetchOneInto(TmUser.class);
			if (CST.PWD_STATUS_INIT == tmUser.getPwdStatus()) {
				return Ajax.responseString(CST.RES_LOGIC_ERROR_1);
			}
			return Ajax.responseString(CST.RES_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

}
