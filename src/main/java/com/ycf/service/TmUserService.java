package com.ycf.service;

import static com.ycf.dao.tables.TmUser.TM_USER;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ycf.bean.UserForm;
import com.ycf.dao.tables.daos.TmUserDao;
import com.ycf.dao.tables.pojos.TmUser;
import com.ycf.dao.tables.records.TmUserRecord;
import com.ycf.page.Page;

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
	 * @param userForm.getTmUser()
	 * @return
	 * @since JDK 1.6
	 */
	public Page<TmUser> list(UserForm userForm) {
		try {
			int rows = userForm.getRows();
			int page = userForm.getPage();
			TmUser tmUser = userForm.getTmUser();

			SelectConditionStep<TmUserRecord> sql = dsl.selectFrom(TM_USER).where("1=1");

			if (tmUser != null) {
				// 用户登录ID是否为空
				if (!StringUtils.isEmpty(tmUser.getLoginId())) {
					sql.and(TM_USER.LOGIN_ID.contains(tmUser.getLoginId()));
				}

				// 用户登录ID是否为空
				if (!StringUtils.isEmpty(tmUser.getUserName())) {
					sql.and(TM_USER.USER_NAME.contains(tmUser.getUserName()));
				}

				// 性别是否为空
				if (tmUser.getSex() != null) {
					sql.and(TM_USER.SEX.eq(tmUser.getSex()));
				}

				// 密码状态是否为空
				if (tmUser.getPwdStatus() != null) {
					sql.and(TM_USER.PWD_STATUS.eq(tmUser.getPwdStatus()));
				}
			}

			// 添加分页节
			if (page > 0) {
				sql.limit((page - 1) * rows, page * rows);
			}

			// 获取查询结果
			List<TmUser> list = sql.fetchInto(TmUser.class);

			// 获取总数
			int total = dsl.fetchCount(TM_USER);
			int totalPage=(int)Math.ceil(((double)total/(double)rows));

			// 构造分页信息
			Page<TmUser> pageBean = new Page<TmUser>();
			pageBean.setRows(list);
			pageBean.setPage(userForm.getPage());
			pageBean.setPageSize(userForm.getRows());
			pageBean.setRecords(total);
			pageBean.setTotal(totalPage);

			return pageBean;
		} catch (Exception e) {
			e.printStackTrace();
			return new Page<TmUser>();
		}
	}
}
