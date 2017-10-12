package com.ycf.utils;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ycf.cst.CST;
import com.ycf.dao.tables.pojos.TmUser;

public class Session {

	/**
	 * Session缓存
	 */
	protected static HttpSession sessionCache;

	/**
	 * 
	 * @Title: getSession
	 * @Description: 获取session
	 * @return
	 */
	public static HttpSession getSession() {
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			if (attr.getRequest().getSession() == null) {
				return sessionCache;
			}
			return attr.getRequest().getSession();
		} catch (Exception e) {
			e.printStackTrace();
			return sessionCache;
		}
	}

	/**
	 * 
	 * isSuperAdmin:(是否超级管理员). <br/>
	 * 
	 * @author liboqiang
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isSuperAdmin() {
		try {
			TmUser tmUser = (TmUser) getSession().getAttribute("tmUser");
			if (CST.USER_ID_DEFAULT.equals(tmUser.getUserId())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 设置session缓存
	 * 
	 * @param session
	 */
	public static void setSessionCache(HttpSession session) {
		sessionCache = session;
	}
}
