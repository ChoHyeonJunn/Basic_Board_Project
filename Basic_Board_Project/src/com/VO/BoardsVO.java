package com.VO;

import java.util.Date;

public class BoardsVO {

	private int BOARD_CODE;
	private int USER_CODE;
	private String TITLE;
	private String CONTEXT;
	private int COUNT_VIEW;
	private Date CREATE_DATE;
	private Date UPDATE_DATE;

	public BoardsVO() {
		super();
	}

	public BoardsVO(int bOARD_CODE, int uSER_CODE, String tITLE, String cONTEXT, int cOUNT_VIEW, Date cREATE_DATE,
			Date uPDATE_DATE) {
		super();
		BOARD_CODE = bOARD_CODE;
		USER_CODE = uSER_CODE;
		TITLE = tITLE;
		CONTEXT = cONTEXT;
		COUNT_VIEW = cOUNT_VIEW;
		CREATE_DATE = cREATE_DATE;
		UPDATE_DATE = uPDATE_DATE;
	}

	public int getBOARD_CODE() {
		return BOARD_CODE;
	}

	public void setBOARD_CODE(int bOARD_CODE) {
		BOARD_CODE = bOARD_CODE;
	}

	public int getUSER_CODE() {
		return USER_CODE;
	}

	public void setUSER_CODE(int uSER_CODE) {
		USER_CODE = uSER_CODE;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getCONTEXT() {
		return CONTEXT;
	}

	public void setCONTEXT(String cONTEXT) {
		CONTEXT = cONTEXT;
	}

	public int getCOUNT_VIEW() {
		return COUNT_VIEW;
	}

	public void setCOUNT_VIEW(int cOUNT_VIEW) {
		COUNT_VIEW = cOUNT_VIEW;
	}

	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public Date getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(Date uPDATE_DATE) {
		UPDATE_DATE = uPDATE_DATE;
	}

}
