package com.ezen.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.member.MemberService;

@Controller
@SessionAttributes("loginUser")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String loginView() {
		
		return "member/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(MemberVO vo, Model model) {
		MemberVO logingUser = null;
		int userCheck = memberService.loginID(vo);
		
		if(userCheck == 1) {
			logingUser = memberService.getMember(vo.getId());
			//session.setAttribute("loginUser", logingUser);
			model.addAttribute("loginUser", logingUser);

			return "redirect:index";
			
		}else { 
			return "member/login_fail";
		}
	}
	
	@RequestMapping(value="/contract", method = RequestMethod.GET)
	public String contractView() {
		
		return "member/contract";
	}
	
	@RequestMapping(value="/join_form", method = RequestMethod.POST)
	public String joinView() {
		
		return "member/join";
	}
	
	/*
	 * ID중복 체크화면 표시
	 */
	@RequestMapping(value="/id_check_form", method = RequestMethod.GET)
	public String idCheckView(MemberVO vo, Model model) {
		model.addAttribute("id", vo.getId());
		
		//id중복확인
		int result = memberService.confirmID(vo.getId());
		model.addAttribute("message",result);
		
		return "member/idcheck";
	}
	
	@RequestMapping(value="/id_check_form", method = RequestMethod.POST)
	public String idCheckAction(MemberVO vo, Model model) {
		model.addAttribute("id",vo.getId());
		
		int result = memberService.confirmID(vo.getId());
		model.addAttribute("message",result);
		
		return "member/idcheck";
	}
/*
 * 	

	@RequestMapping(value="/id_check_confirmed",method = RequestMethod.GET)
	public String idCheckConfirmed(MemberVO vo, Model model) {
		model.addAttribute("id", vo.getId());
		model.addAttribute("reid",vo.getId()); // id 중복 확인 필드
		
		return "member/join";
	}
*/
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinAction(@RequestParam(value="addr1")String addr1,
			                 @RequestParam(value="addr2")String addr2, MemberVO vo) {
		vo.setAddress(addr1+ " " + addr2);
		memberService.insertMember(vo);
		
		return "member/login";
	}
	
	//우편번호 찾기 화면 출력
	@RequestMapping(value="/find_zip_num", method=RequestMethod.GET)
	public String findZipNumView() {
		return "member/findZipNum";
	}
	
	//동이름으로 우편번호 찾기 조회 처리
	@RequestMapping(value="/find_zip_num", method=RequestMethod.POST)
	public String findZipNumAction(AddressVO vo, Model model) {
		
		model.addAttribute("addressList", memberService.selectAddressByDong(vo.getDong()));
		return "member/findZipNum";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status) {
		
		status.setComplete(); // 현재 세션을 종료
		return "member/login";
	}
	//아이디 찾기 화면 출력
	@RequestMapping(value="/find_id_form", method=RequestMethod.GET)
	public String findIdFormView() {
		
		return "member/findIdAndPassword";
	}
	
	// 아이디 찾기 처리
	@RequestMapping(value="/find_id", method=RequestMethod.POST)
	public String findIdAction(MemberVO vo, Model model) {
		String id = memberService.findIdByName(vo);
		
		if (id != null) { // 이름과 이메일을 조건으로 아이디 조회 성공
			model.addAttribute("message", 1);
			model.addAttribute("id", id);
		} else {
			model.addAttribute("message", -1);
		}
		
		return "member/findResult";   // 아이디 조회결과 화면 호출
	}
	/*
	 * 비밀번호찾기
	 */
	@RequestMapping(value="/find_pwd", method=RequestMethod.POST)
	public String findPwdAction(MemberVO vo, Model model) {
		String pwd = memberService.findIdByName(vo);
		
		if (pwd != null) { // 사용자 아이디 이름 암호를 조건응로 사용자 조회 성공
			model.addAttribute("message", 1);
			model.addAttribute("id", vo.getId());
		} else {
			model.addAttribute("message", -1);
		}
		
		return "member/findPwdResult";   // 아이디 조회결과 화면 호출
		
	}
	
	@RequestMapping(value="/change_pwd", method=RequestMethod.POST)
	public String changePwd(MemberVO vo) {
		
		memberService.changePwd(vo);
		
		return "member/changePwdOk";
		
	}
}

