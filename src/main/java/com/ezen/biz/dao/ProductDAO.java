package com.ezen.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.dto.SalesQuantity;

import utils.Criteria;

@Repository("productDAO")
public class ProductDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<ProductVO> getNewProductList(){
		System.out.println("======= mybatis로 신상품목록 가져오기 ======");
		return mybatis.selectList("productMapper.getNewProductList");
	}
	
	public List<ProductVO> getBestProductList(){
		System.out.println("======= mybatis로 베스트상품목록 가져오기 ======");
		return mybatis.selectList("productMapper.getBestProductList");
	}
	
	public ProductVO getProduct(ProductVO vo) {
		System.out.println("======= mybatis로 상품정보 가져오기 ======");
		return mybatis.selectOne("productMapper.getProduct", vo);
	}
	
	public List<ProductVO> getProductListByKind(String kind){
		System.out.println("======= mybatis로 상품목록 가져오기 ======");
		return mybatis.selectList("productMapper.getProductListByKind", kind);
	}
	
	public int countProductList(String name) {
		return mybatis.selectOne("productMapper.countProductList", name);
	}
	
	public void insertProduct(ProductVO vo) {
		mybatis.insert("productMapper.insertproduct" , vo);
	}
	
	public List<ProductVO> listProduct(String name) {
		return mybatis.selectList("productMapper.listProduct", name);
		
	}
	
	public void updateProduct(ProductVO vo) {
		mybatis.update("productMapper.updateProduct", vo);
	}
	/*
	 * 페이지별 상품 조회
	 */
	public List<ProductVO> listProductWithPaging(Criteria criteria, String name) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("name", name);
		return mybatis.selectList("productMapper.listProductWithPaging", map);
		
	}
	
	/*
	 * 제품별 실적 조회
	 */
	public List<SalesQuantity> getProductSales(){
		return mybatis.selectList("productMapper.listProductSales");
	}
}
