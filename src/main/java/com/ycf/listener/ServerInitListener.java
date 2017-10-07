package com.ycf.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ycf.cst.CST;
import com.ycf.utils.PropUtil;

public class ServerInitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 容器初始化
	 */
	public void contextInitialized(ServletContextEvent sce) {

		Properties props = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getResourceAsStream("/config.properties");
			props.load(inputStream);
			CST.UPLOAD_DIR = PropUtil.get(props, "upload.dir");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
