package com.ezen.biz.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.CommentDAO;
import com.ezen.biz.dto.ProductCommentVO;

import utils.Criteria;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDAO cDao;

	@Override
	public int saveComment(ProductCommentVO vo) {
		
		 return cDao.saveComment(vo);
	}

	@Override
	public List<ProductCommentVO> getcommentList(int pseq) {
		
		return cDao.commentList(pseq);
	}

	@Override
	public List<ProductCommentVO> getcommnetListWithPaging(Criteria criteria, int pseq) {

		return cDao.commnetListWithPaging(criteria, pseq);
	}

	@Override
	public int countCommentList(int pseq) {
		
		return cDao.countCommentList(pseq);
	}

}
