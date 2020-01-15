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

	private int GROUP_NO;
	private int GROUP_ORDER;
	private int GROUP_DEPTH;

	private String NAME;

	public CommentsVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentsVO(int cOMMENT_CODE, int bOARD_CODE, int uSER_CODE, String cONTEXT, int cOUNT_GOOD, int cOUNT_BAD,
			Date cREATE_DATE, Date uPDATE_DATE, int gROUP_NO, int gROUP_ORDER, int gROUP_DEPTH, String nAME) {
		super();
		COMMENT_CODE = cOMMENT_CODE;
		BOARD_CODE = bOARD_CODE;
		USER_CODE = uSER_CODE;
		CONTEXT = cONTEXT;
		COUNT_GOOD = cOUNT_GOOD;
		COUNT_BAD = cOUNT_BAD;
		CREATE_DATE = cREATE_DATE;
		UPDATE_DATE = uPDATE_DATE;
		GROUP_NO = gROUP_NO;
		GROUP_ORDER = gROUP_ORDER;
		GROUP_DEPTH = gROUP_DEPTH;
		NAME = nAME;
	}

	public int getCOMMENT_CODE() {
		return COMMENT_CODE;
	}

	public void setCOMMENT_CODE(int cOMMENT_CODE) {
		COMMENT_CODE = cOMMENT_CODE;
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

	public Date getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(Date uPDATE_DATE) {
		UPDATE_DATE = uPDATE_DATE;
	}

	public int getGROUP_NO() {
		return GROUP_NO;
	}

	public void setGROUP_NO(int gROUP_NO) {
		GROUP_NO = gROUP_NO;
	}

	public int getGROUP_ORDER() {
		return GROUP_ORDER;
	}

	public void setGROUP_ORDER(int gROUP_ORDER) {
		GROUP_ORDER = gROUP_ORDER;
	}

	public int getGROUP_DEPTH() {
		return GROUP_DEPTH;
	}

	public void setGROUP_DEPTH(int gROUP_DEPTH) {
		GROUP_DEPTH = gROUP_DEPTH;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	@Override
	public String toString() {
		return "CommentsVO [COMMENT_CODE=" + COMMENT_CODE + ", BOARD_CODE=" + BOARD_CODE + ", USER_CODE=" + USER_CODE
				+ ", CONTEXT=" + CONTEXT + ", COUNT_GOOD=" + COUNT_GOOD + ", COUNT_BAD=" + COUNT_BAD + ", CREATE_DATE="
				+ CREATE_DATE + ", UPDATE_DATE=" + UPDATE_DATE + ", GROUP_NO=" + GROUP_NO + ", GROUP_ORDER="
				+ GROUP_ORDER + ", GROUP_DEPTH=" + GROUP_DEPTH + ", NAME=" + NAME + "]";
	}

}
