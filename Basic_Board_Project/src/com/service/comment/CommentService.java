package com.service.comment;

import java.util.ArrayList;

import com.VO.CommentsVO;

public interface CommentService {

	public ArrayList<CommentsVO> selectCommentsListData();
}
