package com.ezen.biz.order;

import java.util.List;

import com.ezen.biz.dto.OrderVO;

public interface OrderService {

	int getMaxOseq();

	/*
	 * 주문하기 처리
	 * -주문 저장후 주문번호를 반환
	 */
	int insertOrder(OrderVO vo);

	void insertOrderDetail(OrderVO vo);

	List<OrderVO> getListOrderById(OrderVO vo);

	List<Integer> getSeqOrdering(OrderVO vo);

}