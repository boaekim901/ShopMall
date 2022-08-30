package com.ezen.view.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.biz.comment.CommentService;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.ProductCommentVO;

import utils.Criteria;
import utils.PageMaker;

//@RestController - 메소드에서 조회한 데이터를 반환할때 사용
@RequestMapping("/comments")
@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String SaveCommentAction(ProductCommentVO vo,HttpSession session) {
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "not_logedin";
		}else {
			vo.setWriter(loginUser.getId());
			if(commentService.saveComment(vo) >0) {
				return "success";
			}else {
				return "fail";
			}
		}			
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET, produces="application/json; charset=UTF8")
	public Map<String, Object> commentList(ProductCommentVO vo, Criteria criteria){
		//화면으로 반환할 데이터를 저장할 Map을 작성
		Map<String, Object> commentInfo = new HashMap<>();
		
		//상품에 대한 댓글 목록 조회
		List<ProductCommentVO> commentList = commentService.getcommnetListWithPaging(criteria,vo.getPseq());
		
		//페이지 정보 설정
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		
		int totalComment = commentService.countCommentList(vo.getPseq());
		pageMaker.setTotalCount(totalComment);
		
		System.out.println("total = "+ totalComment);
		System.out.println("pageInfo = "+ pageMaker);
		
		commentInfo.put("total", totalComment);
		commentInfo.put("pageInfo", pageMaker);
		commentInfo.put("commentList", commentList);
		
		return commentInfo;
		
	}
}
