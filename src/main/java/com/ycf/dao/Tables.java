/*
 * This file is generated by jOOQ.
*/
package com.ycf.dao;


import com.ycf.dao.tables.ApplyInfo;
import com.ycf.dao.tables.TmUser;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in system
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>system.apply_info</code>.
     */
    public static final ApplyInfo APPLY_INFO = com.ycf.dao.tables.ApplyInfo.APPLY_INFO;

    /**
     * The table <code>system.tm_user</code>.
     */
    public static final TmUser TM_USER = com.ycf.dao.tables.TmUser.TM_USER;
}
