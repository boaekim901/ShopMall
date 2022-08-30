package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.OrderVO;

@Repository
public class OrderDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int selectMaxOseq() {
		
		return mybatis.selectOne("orderMapper.selectMaxOseq");
	}
	
	public void insertOrder(OrderVO vo) {
		
		mybatis.insert("orderMapper.insertOrder", vo);
	}
	
	public void insertOrderDetail(OrderVO vo) {
		
		mybatis.insert("orderMapper.insertOrderDetail", vo);
	}
	
	public List<OrderVO> listOrderById(OrderVO vo){
		
		return mybatis.selectList("orderMapper.listOrderById", vo);
	}
	
	public List<Integer> selectSeqOrdering(OrderVO vo) {
		
		return mybatis.selectList("orderMapper.selectSeqOrdering", vo);
	}

}
