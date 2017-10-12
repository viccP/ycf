package com.ycf.cst;

import java.util.HashMap;
import java.util.Map;

public class CST {

	/**默认用户ID**/
	public static final String USER_ID_DEFAULT = "0";
	
	/**自动消息提示标识(-1)**/
	public static final String RES_AUTO_DIALOG = "-1";

	/**会话超时标识(-2)**/
	public static final String RES_SESSION_TIME_OUT = "-2";
	
	/**成功标识(0)**/
	public static final String RES_SUCCESS = "0";
	
	/**业务逻辑错误(1)**/
	public static final String RES_LOGIC_ERROR_1 = "1";

	/**业务逻辑错误(2)**/
	public static final String RES_LOGIC_ERROR_2 = "2";
	
	/**用户性别(1:男)**/
	public static final String USER_SEX_MALE="1";
	
	/**用户性别(0:女)**/
	public static final String USER_SEX_FEMALE="0";
	
	/**用户密码状态(0:初始密码)**/
	public static final int PWD_STATUS_INIT=0;
	
	/**用户密码状态(1:已经修改)**/
	public static final int PWD_STATUS_ALTERED=1;
	
	/**用户默认密码(000000)**/
	public static final String PWD_DEFAULT = "000000";  
	
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





	/**上传文件夹地址**/
	public static String UPLOAD_DIR="";
}
