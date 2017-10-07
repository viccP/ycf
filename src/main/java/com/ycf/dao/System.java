/*
 * This file is generated by jOOQ.
*/
package com.ycf.dao;


import com.ycf.dao.tables.TmUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class System extends SchemaImpl {

    private static final long serialVersionUID = 366284410;

    /**
     * The reference instance of <code>system</code>
     */
    public static final System SYSTEM = new System();

    /**
     * The table <code>system.tm_user</code>.
     */
    public final TmUser TM_USER = com.ycf.dao.tables.TmUser.TM_USER;

    /**
     * No further instances allowed
     */
    private System() {
        super("system", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            TmUser.TM_USER);
    }
}
