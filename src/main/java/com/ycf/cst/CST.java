package com.ycf.cst;

import java.util.HashMap;
import java.util.Map;

public class CST {

	/**默认用户ID**/
	public static final String USER_ID_DEFAULT = "0";
	public static final String RES_AUTO_DIALOG = "-1";
	public static final String RES_SUCCESS = "0";
	
	public static String UPLOAD_DIR="";
	@SuppressWarnings("serial")
	public static final Map<String,String> PIC_MAPPER=new HashMap<String,String>(){{  
	      put("getCreditPic","征信");
	      put("getHomePic","户口本");   
	      put("getMarriedPic","结婚证");   
	      put("getDriveUsePic","行驶证");   
	      put("getDrivePic","驾驶证");   
	      put("getExamPic","考察照片");   
	      put("getCarPic","车辆照片");   
	      put("getCarRegPic","车辆登记证");   
	      put("getHomePic","房产证或租房合同");   
	      put("getBankPic","收入证明与银行流水");   
	      put("getIdPic","申请人及配偶身份证");   
	}};  

}
