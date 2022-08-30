package com.ezen.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.AdminVO;

@Repository
public class AdminDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public String adminCheck(String id) {
		
		return mybatis.selectOne("adminMapper.adminCheck", id);
	
	}
	
	public AdminVO getEmployee(String id) {
		
		return mybatis.selectOne("adminMapper.getAdmin", id);
	}
}
