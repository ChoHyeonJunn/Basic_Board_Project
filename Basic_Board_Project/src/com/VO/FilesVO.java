package com.VO;

import java.sql.Date;

public class FilesVO {

	private int FILE_CODE;
	private int BOARD_CODE;
	private int USER_CODE;
	private String FILE_ORIGINAL_NAME;

	private String FILE_STORED_NAME;
	private String FILE_PATH;
	private String FILE_SIZE;
	private Date CREATE_DATE;

	private boolean DEL_YN;

	public FilesVO() {
		super();
	}

	public FilesVO(int fILE_CODE, int bOARD_CODE, int uSER_CODE, String fILE_ORIGINAL_NAME, String fILE_STORED_NAME,
			String fILE_PATH, String fILE_SIZE, Date cREATE_DATE, boolean dEL_YN) {
		super();
		FILE_CODE = fILE_CODE;
		BOARD_CODE = bOARD_CODE;
		USER_CODE = uSER_CODE;
		FILE_ORIGINAL_NAME = fILE_ORIGINAL_NAME;
		FILE_STORED_NAME = fILE_STORED_NAME;
		FILE_PATH = fILE_PATH;
		FILE_SIZE = fILE_SIZE;
		CREATE_DATE = cREATE_DATE;
		DEL_YN = dEL_YN;
	}

	public int getFILE_CODE() {
		return FILE_CODE;
	}

	public void setFILE_CODE(int fILE_CODE) {
		FILE_CODE = fILE_CODE;
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

	public String getFILE_ORIGINAL_NAME() {
		return FILE_ORIGINAL_NAME;
	}

	public void setFILE_ORIGINAL_NAME(String fILE_ORIGINAL_NAME) {
		FILE_ORIGINAL_NAME = fILE_ORIGINAL_NAME;
	}

	public String getFILE_STORED_NAME() {
		return FILE_STORED_NAME;
	}

	public void setFILE_STORED_NAME(String fILE_STORED_NAME) {
		FILE_STORED_NAME = fILE_STORED_NAME;
	}

	public String getFILE_PATH() {
		return FILE_PATH;
	}

	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}

	public String getFILE_SIZE() {
		return FILE_SIZE;
	}

	public void setFILE_SIZE(String fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}

	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public boolean isDEL_YN() {
		return DEL_YN;
	}

	public void setDEL_YN(boolean dEL_YN) {
		DEL_YN = dEL_YN;
	}

}
