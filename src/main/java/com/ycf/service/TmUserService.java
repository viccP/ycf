package com.ycf.service;

import static com.ycf.dao.tables.TmUser.TM_USER;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ycf.dao.tables.daos.TmUserDao;
import com.ycf.dao.tables.pojos.TmUser;
import com.ycf.dao.tables.records.TmUserRecord;

@Component
public class TmUserService {

	@Autowired
	private TmUserDao tmUserDao;

	@Autowired
	private DefaultDSLContext dsl;

	/**
	 * 
	 * add:(添加用户). <br/>
	 * 
	 * @author liboqiang
	 * @param tmUser
	 * @since JDK 1.6
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(TmUser tmUser) {
		tmUserDao.insert(tmUser);
	}

	/**
	 * 
	 * list:(查询用户). <br/>
	 * 
	 * @author liboqiang
	 * @param tmUser
	 * @return
	 * @since JDK 1.6
	 */
	public List<TmUser> list(TmUser tmUser) {
		try {
			SelectConditionStep<TmUserRecord> sql = dsl.selectFrom(TM_USER).where("1=1");
			
			//用户登录ID是否为空
			if (StringUtils.isEmpty(tmUser.getLoginId())) {
				sql.and(TM_USER.LOGIN_ID.like(tmUser.getLoginId()));
			}
			return sql.fetchInto(TmUser.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<TmUser>();
		}
	}
}
