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
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/product_detail", method=RequestMethod.GET)
	public String productDetailAction(ProductVO vo, Model model){
		
		//상품 상세조외 서비스 호출
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO",product);
		return "product/productDetail";
	}
	
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String productKindAction(ProductVO vo, Model model) {
		
		List<ProductVO> productKindList = productService.getProductListByKind(vo.getKind());
		model.addAttribute("productKindList", productKindList);
		
		return "product/productKind";
	}
}
