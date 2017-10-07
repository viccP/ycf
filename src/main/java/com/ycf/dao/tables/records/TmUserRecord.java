/*
 * This file is generated by jOOQ.
*/
package com.ycf.dao.tables.records;


import com.ycf.dao.tables.TmUser;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


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
public class TmUserRecord extends UpdatableRecordImpl<TmUserRecord> implements Record7<String, String, String, String, Integer, String, String> {

    private static final long serialVersionUID = 1955432291;

    /**
     * Setter for <code>system.tm_user.user_id</code>. 用户id
     */
    public void setUserId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>system.tm_user.user_id</code>. 用户id
     */
    public String getUserId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>system.tm_user.login_id</code>. 登录id
     */
    public void setLoginId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>system.tm_user.login_id</code>. 登录id
     */
    public String getLoginId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>system.tm_user.password</code>. 密码
     */
    public void setPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>system.tm_user.password</code>. 密码
     */
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>system.tm_user.user_name</code>. 用户名
     */
    public void setUserName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>system.tm_user.user_name</code>. 用户名
     */
    public String getUserName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>system.tm_user.sex</code>. 性别&gt;1:男 0:女
     */
    public void setSex(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>system.tm_user.sex</code>. 性别&gt;1:男 0:女
     */
    public Integer getSex() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>system.tm_user.phone_no</code>. 电话号码
     */
    public void setPhoneNo(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>system.tm_user.phone_no</code>. 电话号码
     */
    public String getPhoneNo() {
        return (String) get(5);
    }

    /**
     * Setter for <code>system.tm_user.memo</code>. 备注
     */
    public void setMemo(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>system.tm_user.memo</code>. 备注
     */
    public String getMemo() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<String, String, String, String, Integer, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<String, String, String, String, Integer, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return TmUser.TM_USER.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TmUser.TM_USER.LOGIN_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TmUser.TM_USER.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TmUser.TM_USER.USER_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return TmUser.TM_USER.SEX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return TmUser.TM_USER.PHONE_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return TmUser.TM_USER.MEMO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getLoginId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getUserName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getSex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getPhoneNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getMemo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TmUserRecord value1(String value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TmUserRecord value2(String value) {
        setLoginId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TmUserRecord value3(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TmUserRecord value4(String value) {
        setUserName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TmUserRecord value5(Integer value) {
        setSex(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TmUserRecord value6(String value) {
        setPhoneNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TmUserRecord value7(String value) {
        setMemo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TmUserRecord values(String value1, String value2, String value3, String value4, Integer value5, String value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TmUserRecord
     */
    public TmUserRecord() {
        super(TmUser.TM_USER);
    }

    /**
     * Create a detached, initialised TmUserRecord
     */
    public TmUserRecord(String userId, String loginId, String password, String userName, Integer sex, String phoneNo, String memo) {
        super(TmUser.TM_USER);

        set(0, userId);
        set(1, loginId);
        set(2, password);
        set(3, userName);
        set(4, sex);
        set(5, phoneNo);
        set(6, memo);
    }
}
