package com.DAO.file;

import java.util.ArrayList;

import com.VO.FilesVO;

public interface FileDAO {

	public ArrayList<FilesVO> selectFiles() throws Exception;

}
