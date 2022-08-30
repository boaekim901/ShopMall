package com.ezen.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.QnaVO;
import com.ezen.biz.qna.QnaService;

@Controller
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	/*
	 * id를 조건으로 QnA 목록 조회
	 */
	@RequestMapping(value="/qna_list", method=RequestMethod.GET)
	public String qnaList(HttpSession session, Model model) {
		//세션에서 사용자 정보를 읽어와 사용자가 로그인 상태인지 확인
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		if(loginUser == null) {
			return "member/login";
		}else {
			List<QnaVO> qnaList = qnaService.getListQna(loginUser.getId());
			
			model.addAttribute("qnaList",qnaList);
			
			return "qna/qnaList";
		}
	}
	
	@RequestMapping(value="/qna_view", method=RequestMethod.GET)
	public String qnaView(HttpSession session, QnaVO vo, Model model) {
		//세션에서 사용자 정보를 읽어와 사용자가 로그인 상태인지 확인
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		if(loginUser == null) {
			return "member/login";
		}else {
			
			QnaVO qna = qnaService.getQna(vo.getQseq());
			model.addAttribute("qnaVO", qna);
			
			return "qna/qnaView";
		}
	}
	
	@RequestMapping(value="/qna_write_form",method=RequestMethod.GET )
	public String qnaWriteView(HttpSession session) {
		//세션에서 사용자 정보를 읽어와 사용자가 로그인 상태인지 확인
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		if(loginUser == null) {
			return "member/login";
		}else {
			return "qna/qnaWrite";
		}
	}
	/*
	 * Qna글쓰기 처리
	 */
	@RequestMapping(value="/qna_write", method=RequestMethod.POST)
	public String qnaWriteAction(HttpSession session, QnaVO vo) {
		//세션에서 사용자 정보를 읽어와 사용자가 로그인 상태인지 확인
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		if(loginUser == null) {
			return "member/login";
		}else {
			vo.setId(loginUser.getId());
			
			qnaService.insertQna(vo);
			
			return "redirect:qna_list";
		}
	}
}