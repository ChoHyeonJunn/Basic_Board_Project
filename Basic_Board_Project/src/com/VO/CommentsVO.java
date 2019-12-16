package com.VO;

import java.util.Date;

public class CommentsVO {

	private int COMMENT_CODE;
	private int BOARD_CODE;
	private int USER_CODE;
	private String CONTEXT;

	private int COUNT_GOOD;
	private int COUNT_BAD;
	private Date CREATE_DATE;
	private Date UPDATE_DATE;

	private boolean DEL_YN;
	private String NAME;

	@Override
	public String toString() {
		return "CommentsVO [COMMENT_CODE=" + COMMENT_CODE + ", BOARD_CODE=" + BOARD_CODE + ", USER_CODE=" + USER_CODE
				+ ", CONTEXT=" + CONTEXT + ", COUNT_GOOD=" + COUNT_GOOD + ", COUNT_BAD=" + COUNT_BAD + ", CREATE_DATE="
				+ CREATE_DATE + ", UPDATE_DATE=" + UPDATE_DATE + ", DEL_YN=" + DEL_YN + ", NAME=" + NAME + "]";
	}

	public CommentsVO() {
		super();
	}

	public CommentsVO(int cOMMENT_CODE, int bOARD_CODE, int uSER_CODE, String cONTEXT, int cOUNT_GOOD, int cOUNT_BAD,
			Date cREATE_DATE, Date uPDATE_DATE, boolean dEL_YN) {
		super();
		COMMENT_CODE = cOMMENT_CODE;
		BOARD_CODE = bOARD_CODE;
		USER_CODE = uSER_CODE;
		CONTEXT = cONTEXT;
		COUNT_GOOD = cOUNT_GOOD;
		COUNT_BAD = cOUNT_BAD;
		CREATE_DATE = cREATE_DATE;
		UPDATE_DATE = uPDATE_DATE;
		DEL_YN = dEL_YN;
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

	public int getCOMMENT_CODE() {
		return COMMENT_CODE;
	}

	public void setCOMMENT_CODE(int cOMMENT_CODE) {
		COMMENT_CODE = cOMMENT_CODE;
	}

	public Date getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(Date uPDATE_DATE) {
		UPDATE_DATE = uPDATE_DATE;
	}

	public boolean isDEL_YN() {
		return DEL_YN;
	}

	public void setDEL_YN(boolean dEL_YN) {
		DEL_YN = dEL_YN;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

}
