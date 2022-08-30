package com.ezen.biz.mypage;

import java.util.List;

import com.ezen.biz.dto.CartVO;

public interface CartService {

	void insertCart(CartVO vo);

	List<CartVO> getlistCart(String id);

	void deleteCart(int cseq);
	
	void updateCart(int cseq);

}