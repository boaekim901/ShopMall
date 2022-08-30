package com.ezen.biz.product;

import java.util.List;

import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.dto.SalesQuantity;

import utils.Criteria;

public interface ProductService {

	List<ProductVO> getNewProductList();

	List<ProductVO> getBestProductList();

	ProductVO getProduct(ProductVO vo);

	List<ProductVO> getProductListByKind(String kind);

	int getcountProduct(String name);
	
	void insertProduct(ProductVO vo);
	
	List<ProductVO> getlistProduct(String name);
	
	void updateProduct(ProductVO vo);
	
	List<ProductVO> getlistProductWithPaging(Criteria criteria, String name);
	
	List<SalesQuantity> getProductSales();
	
}