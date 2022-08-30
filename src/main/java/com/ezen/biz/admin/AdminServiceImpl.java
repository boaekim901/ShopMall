package com.ezen.biz.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.AdminDAO;
import com.ezen.biz.dto.AdminVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO aDao;
	
	/*
	 * 리턴값 :
	 *     -1: id가 존재하지 않음
	 *      0: 비밀번호가 맞지않음
	 *      1:정상로그인
	 */
	@Override
	public int adminCheck(AdminVO vo) {
		int result = -1;
		
	   String adminrPwd = aDao.adminCheck(vo.getId());
	   if(adminrPwd != null) { //로그인 됨
		   if(adminrPwd.equals(vo.getPwd())) {
			   result = 1;
		   }else {
			   result = 0;
		   }
	   }else {
		   result = -1;
	   }
	   
		return result;
	}

	@Override
	public AdminVO getAdmin(String id) {
		return aDao.getEmployee(id);
	}

}
