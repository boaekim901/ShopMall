package com.ezen.biz.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.ProductDAO;
import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.dto.SalesQuantity;

import utils.Criteria;

@Service("productService") 
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO pDao;
	
	@Override
	public List<ProductVO> getNewProductList(){
		return pDao.getNewProductList();
	}
	@Override
	public List<ProductVO> getBestProductList(){
		return pDao.getBestProductList();
	}
	
	@Override
	public ProductVO getProduct(ProductVO vo) {
		return pDao.getProduct(vo);
	}
	
	@Override
	public List<ProductVO> getProductListByKind(String kind){
		return pDao.getProductListByKind(kind);
	}
	@Override
	public int getcountProduct(String name) {
		
		return pDao.countProductList(name);
	}
	@Override
	public void insertProduct(ProductVO vo) {
		pDao.insertProduct(vo);
		
	}
	@Override
	public List<ProductVO> getlistProduct(String name) {
		
		return pDao.listProduct(name);
	}
	@Override
	public void updateProduct(ProductVO vo) {
		pDao.updateProduct(vo);
		
	}
	@Override
	public List<ProductVO> getlistProductWithPaging(Criteria criteria, String name) {
		
		return pDao.listProductWithPaging(criteria, name);
	}
	@Override
	public List<SalesQuantity> getProductSales() {
		
		return pDao.getProductSales();
	}
}