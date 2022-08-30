package com.ezen.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.product.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService; 
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String home(Model model) {
		// 신상품 조회 서비스 호출
		List<ProductVO> newProdList = productService.getNewProductList();
		
		//베스트 상품 조회 서비스 호출
		List<ProductVO> bestProdList = productService.getBestProductList();
		
		model.addAttribute("newProductList", newProdList);
		model.addAttribute("bestProductList", bestProdList);
		return "index"; //index.jsp 호출
	}
}
