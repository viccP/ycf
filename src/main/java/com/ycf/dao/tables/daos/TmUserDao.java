/*
 * This file is generated by jOOQ.
*/
package com.ycf.dao.tables.daos;


import com.ycf.dao.tables.TmUser;
import com.ycf.dao.tables.records.TmUserRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TmUserDao extends DAOImpl<TmUserRecord, com.ycf.dao.tables.pojos.TmUser, String> {

    /**
     * Create a new TmUserDao without any configuration
     */
    public TmUserDao() {
        super(TmUser.TM_USER, com.ycf.dao.tables.pojos.TmUser.class);
    }

    /**
     * Create a new TmUserDao with an attached configuration
     */
    public TmUserDao(Configuration configuration) {
        super(TmUser.TM_USER, com.ycf.dao.tables.pojos.TmUser.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(com.ycf.dao.tables.pojos.TmUser object) {
        return object.getUserId();
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.ycf.dao.tables.pojos.TmUser> fetchByUserId(String... values) {
        return fetch(TmUser.TM_USER.USER_ID, values);
    }

    /**
     * Fetch a unique record that has <code>user_id = value</code>
     */
    public com.ycf.dao.tables.pojos.TmUser fetchOneByUserId(String value) {
        return fetchOne(TmUser.TM_USER.USER_ID, value);
    }

    /**
     * Fetch records that have <code>login_id IN (values)</code>
     */
    public List<com.ycf.dao.tables.pojos.TmUser> fetchByLoginId(String... values) {
        return fetch(TmUser.TM_USER.LOGIN_ID, values);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<com.ycf.dao.tables.pojos.TmUser> fetchByPassword(String... values) {
        return fetch(TmUser.TM_USER.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>user_name IN (values)</code>
     */
    public List<com.ycf.dao.tables.pojos.TmUser> fetchByUserName(String... values) {
        return fetch(TmUser.TM_USER.USER_NAME, values);
    }

    /**
     * Fetch records that have <code>sex IN (values)</code>
     */
    public List<com.ycf.dao.tables.pojos.TmUser> fetchBySex(Integer... values) {
        return fetch(TmUser.TM_USER.SEX, values);
    }

    /**
     * Fetch records that have <code>phone_no IN (values)</code>
     */
    public List<com.ycf.dao.tables.pojos.TmUser> fetchByPhoneNo(String... values) {
        return fetch(TmUser.TM_USER.PHONE_NO, values);
    }

    /**
     * Fetch records that have <code>memo IN (values)</code>
     */
    public List<com.ycf.dao.tables.pojos.TmUser> fetchByMemo(String... values) {
        return fetch(TmUser.TM_USER.MEMO, values);
    }

    /**
     * Fetch records that have <code>upd_time IN (values)</code>
     */
    public List<com.ycf.dao.tables.pojos.TmUser> fetchByUpdTime(Timestamp... values) {
        return fetch(TmUser.TM_USER.UPD_TIME, values);
    }

    /**
     * Fetch records that have <code>pwd_status IN (values)</code>
     */
    public List<com.ycf.dao.tables.pojos.TmUser> fetchByPwdStatus(Integer... values) {
        return fetch(TmUser.TM_USER.PWD_STATUS, values);
    }
}
