package com.ycf.action;

import static com.ycf.dao.tables.TmUser.TM_USER;

import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ycf.dao.tables.pojos.TmUser;

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
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String login() {

		return JSON.toJSONString(dsl.selectFrom(TM_USER).where("1=1").fetchInto(TmUser.class));
	}

}
