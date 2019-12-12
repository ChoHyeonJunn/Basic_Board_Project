package com.service.file;

import java.util.ArrayList;

import com.DAO.file.FileDAO;
import com.DAO.file.FileDAOImpl;
import com.VO.FilesVO;

public class FileServiceImpl implements FileService {
	FileDAO fileDAO = new FileDAOImpl();

	@Override
	public ArrayList<FilesVO> selectFilesListData() {
		ArrayList<FilesVO> filesList = null;
		try {
			filesList = fileDAO.selectFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filesList;
	}

}
