package com.VO;

import java.util.Date;

public class CommentsVO {
	private int BOARD_CODE;
	private int USER_CODE;
	private String CONTEXT;
	private int COUNT_GOOD;
	private int COUNT_BAD;
	private Date CREATE_DATE;

	public CommentsVO() {
		super();
	}

	public CommentsVO(int bOARD_CODE, int uSER_CODE, String cONTEXT, int cOUNT_GOOD, int cOUNT_BAD, Date cREATE_DATE) {
		super();
		BOARD_CODE = bOARD_CODE;
		USER_CODE = uSER_CODE;
		CONTEXT = cONTEXT;
		COUNT_GOOD = cOUNT_GOOD;
		COUNT_BAD = cOUNT_BAD;
		CREATE_DATE = cREATE_DATE;
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

	public String getCONTEXT() {
		return CONTEXT;
	}

	public void setCONTEXT(String cONTEXT) {
		CONTEXT = cONTEXT;
	}

	public int getCOUNT_GOOD() {
		return COUNT_GOOD;
	}

	public void setCOUNT_GOOD(int cOUNT_GOOD) {
		COUNT_GOOD = cOUNT_GOOD;
	}

	public int getCOUNT_BAD() {
		return COUNT_BAD;
	}

	public void setCOUNT_BAD(int cOUNT_BAD) {
		COUNT_BAD = cOUNT_BAD;
	}

	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

}
