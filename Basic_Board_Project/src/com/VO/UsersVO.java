package com.VO;

import java.util.Date;

public class UsersVO {
	private int USER_CODE;
	private String USERID;
	private String PASSWORD;
	private String NAME;
	private Date CREATE_DATE;

	public UsersVO() {
		super();
	}

	public UsersVO(int uSER_CODE, String uSERID, String pASSWORD, String nAME, Date cREATE_DATE) {
		super();
		USER_CODE = uSER_CODE;
		USERID = uSERID;
		PASSWORD = pASSWORD;
		NAME = nAME;
		CREATE_DATE = cREATE_DATE;
	}

	public int getUSER_CODE() {
		return USER_CODE;
	}

	public void setUSER_CODE(int uSER_CODE) {
		USER_CODE = uSER_CODE;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
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

}
