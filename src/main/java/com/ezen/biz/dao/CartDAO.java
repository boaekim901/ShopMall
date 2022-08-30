package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.CartVO;

@Repository("carDAO")
public class CartDAO  {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertCart(CartVO vo) {
		
		mybatis.insert("cartMapper.insertCart", vo);
	}
	
	public List<CartVO> listCart(String id){
		
		return mybatis.selectList("cartMapper.listCart", id);
	}
	
	public void deleteCart(int cseq) {
		
		mybatis.delete("cartMapper.deleteCart", cseq);
	}
	
	//장바구니 항목을 '처리' 로 업데이트
	public void updateCart(int cseq) {
		mybatis.update("cartMapper.updateCart", cseq);
	}
}
