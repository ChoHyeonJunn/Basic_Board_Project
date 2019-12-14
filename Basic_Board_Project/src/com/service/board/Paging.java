package com.service.board;

import com.DAO.board.BoardDAO;
import com.DAO.board.BoardDAOImpl;

public class Paging {
	private final static int pageCount = 5;
	private int blockStartNum = 0;
	private int blockLastNum = 0;
	private int lastPageNum = 0;

	public int getBlockStartNum() {
		return blockStartNum;
	}

	public void setBlockStartNum(int blockStartNum) {
		this.blockStartNum = blockStartNum;
	}

	public int getBlockLastNum() {
		return blockLastNum;
	}

	public void setBlockLastNum(int blockLastNum) {
		this.blockLastNum = blockLastNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	// block을 생성
	// 현재 페이지가 속한 block의 시작 번호, 끝 번호를 계산
	public void makeBlock(int curPage) {
		int blockNum = 0;

		blockNum = (int) Math.floor((curPage - 1) / pageCount);
		blockStartNum = (pageCount * blockNum) + 1;
		blockLastNum = blockStartNum + (pageCount - 1);
	}

	// 총 페이지의 마지막 번호
	public void makeLastPageNum() {
		BoardDAO dao = new BoardDAOImpl();
		int total = dao.getCount();
		System.out.println("total : " + total);

		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}

	// 검색을 했을 때 총 페이지의 마지막 번호
	public void makeLastPageNum(int how, String kwd) {
		BoardDAO dao = new BoardDAOImpl();
		int total = dao.getCount(how, kwd);

		if (total % pageCount == 0) {
			lastPageNum = (int) Math.floor(total / pageCount);
		} else {
			lastPageNum = (int) Math.floor(total / pageCount) + 1;
		}
	}
}
