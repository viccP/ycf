package com.ycf.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycf.cst.CST;
import com.ycf.dao.tables.pojos.TmUser;
import com.ycf.service.TmUserService;
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
@RequestMapping(value = "/user")
public class UserAction {
	
	@Autowired
	private TmUserService tmUserService;


	/**
	 * 
	 * add:(新增用户). <br/> 
	 * 
	 * @author liboqiang
	 * @param tmUser
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String add(TmUser tmUser) {
		try {
			tmUserService.add(tmUser);
			return Ajax.responseString(CST.RES_AUTO_DIALOG, "新增成功");
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
	public String list(TmUser tmUser) {
		try {
			List<TmUser> list = tmUserService.list(tmUser);
			return Ajax.responseString(CST.RES_SUCCESS, list);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_AUTO_DIALOG, e.getMessage());
		}
	}

}
