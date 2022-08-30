package com.ezen.biz.comment;

import java.util.List;

import com.ezen.biz.dto.ProductCommentVO;

import utils.Criteria;

public interface CommentService {

	int saveComment(ProductCommentVO vo);
	
	List<ProductCommentVO> getcommentList(int pseq);
	
	List<ProductCommentVO> getcommnetListWithPaging(Criteria criteria, int pseq);
	
	int countCommentList(int pseq);
}
