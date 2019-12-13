package com.VO;

import java.sql.Date;

public class BoardListVO {
	private int BOARD_CODE;
	private String TITLE;
	private int COUNT_COMMENT;
	private String NAME;
	private Date CREATE_DATE;
	private int COUNT_VIEW;

	public BoardListVO() {
	}

	public BoardListVO(int bOARD_CODE, String tITLE, int cOUNT_COMMENT, String nAME, Date cREATE_DATE,
			int cOUNT_VIEW) {
		super();
		BOARD_CODE = bOARD_CODE;
		TITLE = tITLE;
		COUNT_COMMENT = cOUNT_COMMENT;
		NAME = nAME;
		CREATE_DATE = cREATE_DATE;
		COUNT_VIEW = cOUNT_VIEW;
	}

	public int getBOARD_CODE() {
		return BOARD_CODE;
	}

	public void setBOARD_CODE(int bOARD_CODE) {
		BOARD_CODE = bOARD_CODE;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public int getCOUNT_COMMENT() {
		return COUNT_COMMENT;
	}

	public void setCOUNT_COMMENT(int cOUNT_COMMENT) {
		COUNT_COMMENT = cOUNT_COMMENT;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public int getCOUNT_VIEW() {
		return COUNT_VIEW;
	}

	public void setCOUNT_VIEW(int cOUNT_VIEW) {
		COUNT_VIEW = cOUNT_VIEW;
	}

}
