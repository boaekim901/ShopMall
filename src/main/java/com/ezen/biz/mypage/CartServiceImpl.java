package com.ezen.biz.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.CartDAO;
import com.ezen.biz.dto.CartVO;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;
	@Override
	public void insertCart(CartVO vo) {
		
		cartDAO.insertCart(vo);
	}

	@Override
	public List<CartVO> getlistCart(String id) {
		
		return cartDAO.listCart(id);
	}

	@Override
	public void deleteCart(int cseq) {

		 cartDAO.deleteCart(cseq);
	}

	@Override
	public void updateCart(int cseq) {
		
		cartDAO.updateCart(cseq);
	}

}
