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
			if (session.getAttribute("userInfo") == null) {
				return Ajax.responseString(CST.RES_SESSION_TIME_OUT, "用户已经过期，请重新登陆");
			}
			return Ajax.responseString(CST.RES_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}
}
