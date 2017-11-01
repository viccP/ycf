package com.ycf.bean;

public class TodoForm {
	private String applyId;
	private String applyTitle;
	private String applyUser;
	private String applyTime;
	private Integer status;
	private String rejectMsg;
	private boolean isAdmin;
	private int page;
	private int rows;

	/**
	 * applyId.
	 * 
	 * @return the applyId
	 * @since JDK 1.6
	 */
	public String getApplyId() {
		return applyId;
	}

	/**
	 * applyId.
	 * 
	 * @param applyId
	 *            the applyId to set
	 * @since JDK 1.6
	 */
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	/**
	 * applyTitle.
	 * 
	 * @return the applyTitle
	 * @since JDK 1.6
	 */
	public String getApplyTitle() {
		return applyTitle;
	}

	/**
	 * applyTitle.
	 * 
	 * @param applyTitle
	 *            the applyTitle to set
	 * @since JDK 1.6
	 */
	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}

	/**
	 * applyUser.
	 * 
	 * @return the applyUser
	 * @since JDK 1.6
	 */
	public String getApplyUser() {
		return applyUser;
	}

	/**
	 * applyUser.
	 * 
	 * @param applyUser
	 *            the applyUser to set
	 * @since JDK 1.6
	 */
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	/**
	 * applyTime.
	 * 
	 * @return the applyTime
	 * @since JDK 1.6
	 */
	public String getApplyTime() {
		return applyTime;
	}

	/**
	 * applyTime.
	 * 
	 * @param applyTime
	 *            the applyTime to set
	 * @since JDK 1.6
	 */
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	/** 
	* status. 
	* 
	* @return  the status 
	* @since   JDK 1.6 
	*/
	public Integer getStatus() {
		return status;
	}

	/** 
	 * status. 
	 * 
	 * @param   status    the status to set 
	 * @since   JDK 1.6 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 
	* rejectMsg. 
	* 
	* @return  the rejectMsg 
	* @since   JDK 1.6 
	*/
	public String getRejectMsg() {
		return rejectMsg;
	}

	/** 
	 * rejectMsg. 
	 * 
	 * @param   rejectMsg    the rejectMsg to set 
	 * @since   JDK 1.6 
	 */
	public void setRejectMsg(String rejectMsg) {
		this.rejectMsg = rejectMsg;
	}


	/** 
	* page. 
	* 
	* @return  the page 
	* @since   JDK 1.6 
	*/
	public int getPage() {
		return page;
	}

	/** 
	 * page. 
	 * 
	 * @param   page    the page to set 
	 * @since   JDK 1.6 
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/** 
	* rows. 
	* 
	* @return  the rows 
	* @since   JDK 1.6 
	*/
	public int getRows() {
		return rows;
	}

	/** 
	 * rows. 
	 * 
	 * @param   rows    the rows to set 
	 * @since   JDK 1.6 
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/** 
	* isAdmin. 
	* 
	* @return  the isAdmin 
	* @since   JDK 1.6 
	*/
	public boolean isAdmin() {
		return isAdmin;
	}

	/** 
	 * isAdmin. 
	 * 
	 * @param   isAdmin    the isAdmin to set 
	 * @since   JDK 1.6 
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
