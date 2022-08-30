package com.ezen.biz.member;

import java.util.List;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	//회원 상세조회
	MemberVO getMember(String id);

	//회원 ID 존재 확인
	int confirmID(String id);

	//회원 인증
	int loginID(MemberVO vo);
	
	void insertMember(MemberVO vo);
	
	List<AddressVO> selectAddressByDong(String dong);
	
	String findIdByName(MemberVO vo);
	
	String findPwdByNameEmail(MemberVO vo);
	
	void changePwd(MemberVO vo);
			
}