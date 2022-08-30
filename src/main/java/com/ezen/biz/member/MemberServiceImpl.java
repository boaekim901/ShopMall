package com.ezen.biz.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.MemberDAO;
import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO mDao;
	
	@Override
	public MemberVO getMember(String id) {

		return mDao.getMember(id) ;
	}

	@Override
	public int confirmID(String id) {
		
		return mDao.confirmID(id);
	}

	@Override
	public int loginID(MemberVO vo) {
		return mDao.loginID(vo);
	}

	
	@Override
	public void insertMember(MemberVO vo) {
		mDao.insertMember(vo);
	}

	@Override
	public List<AddressVO> selectAddressByDong(String dong) {
		return mDao.selectAddressByDong(dong);

	}

	@Override
	public String findIdByName(MemberVO vo) {
		return mDao.findIdByName(vo);
	}

	@Override
	public String findPwdByNameEmail(MemberVO vo) {

		return mDao.findPwdByNameEmail(vo);
	}

	@Override
	public void changePwd(MemberVO vo) {
		mDao.changePwd(vo);
		
	}
	
}
