package com.ezen.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.biz.admin.AdminService;
import com.ezen.biz.dto.AdminVO;
import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.dto.SalesQuantity;
import com.ezen.biz.product.ProductService;

import utils.Criteria;
import utils.PageMaker;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/admin_login_form",method=RequestMethod.GET)
	public String adminLoginView() {
		
		return "admin/main";
	}
	
	@RequestMapping(value="/admin_login", method=RequestMethod.POST)
	public String adminLoginAction(AdminVO vo, Model model) {
		//(1) 관리자 ID 인증
		int adminCheck = adminService.adminCheck(vo);
		String url = null;
		//(2) 정상관리자이면 관리자 정보 조회
		//     -상품목록 화면으로 이동(redirect:admin_product_list)
		if(adminCheck == 1 ) {
			adminService.getAdmin(vo.getId());
			
			url= "redirect:admin_product_list";
		}else if(adminCheck == 0) {
		//(3) 비정상 로그인이면 
		// message를 설정하고 로그인 페이지로 이동
			model.addAttribute("message", "비밀번호를 확인해주세요");
			url=  "admin/main";
			
		}else if(adminCheck == -1) {
			model.addAttribute("message", "존재하지 않는 ID입니다.");
			url=  "admin/main";
		}
		return url;
	}
	
	@RequestMapping(value="admin_logout", method = RequestMethod.GET)
	public String adminLogOut(SessionStatus status) {
		
		status.setComplete();
		
		return "admin/main";
	}
	/*
	 * 페이징 기능을 추가한 전체 상품 목록 조회 처리 
	 */
	@RequestMapping(value="admin_product_list", method=RequestMethod.GET)
	public String adminProductList(@RequestParam(value="key", defaultValue="")String name,
			Criteria criteria, Model model) {
		//prodList - 각페이지에 해당하는 게시물이 저장되어 있음
		List<ProductVO> prddList = productService.getlistProductWithPaging(criteria,name); // 전체 상품 목록 조회하기위해 빈문자열 넣어주기
		
		//화면에 표시할 페이지 버튼의 정보설정(PageMaker 객체 이용)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria); //현재 페이지와 페이지당 항목수 저장
		pageMaker.setTotalCount(productService.getcountProduct(name)); //전체항목수를 조회하여 저장
		System.out.println("[adminProductList]pageMaker ="+pageMaker);
		
		model.addAttribute("productList", prddList);
		model.addAttribute("productListSize", prddList.size());
		model.addAttribute("pageMaker", pageMaker);
		return "admin/product/productList";
	}
	
//	/*
//	 * 전체상품 목록 조회
//	 */
//	@RequestMapping(value="admin_product_list", method=RequestMethod.GET)
//	public String adminProductList(Model model) {
//		List<ProductVO> prddList = productService.getlistProduct(""); // 전체 상품 목록 조회하기위해 빈문자열 넣어주기
//		
//		model.addAttribute("productList", prddList);
//		model.addAttribute("productListSize", prddList.size());
//		
//		return "admin/product/productList";
//	}	
	
	/*
	 * 상품등록페이지 표시
	 */
	@RequestMapping(value="admin_product_write_form", method=RequestMethod.POST)
	public String adminPorductWriteView(Model model) {
		String kindList[] = {"힐", "부츠", "샌달", "슬리퍼", "스니커즈","세일즈"};
		model.addAttribute("kindList", kindList);
		return "admin/product/productWrite";
	}
	/*
	 * 상품등록 처리
	 */
	@RequestMapping(value="admin_product_write", method=RequestMethod.POST)
	public String adminProductWirte(@RequestParam(value="product_image")MultipartFile uploadFile, 
									ProductVO vo, HttpSession session, Model model) {
		if(!uploadFile.isEmpty()) {//이미지가 등록 되었으면
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);
			
			//이미지 파일을 이동할 실제경로 구하기
			String img_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
			
			try {
				uploadFile.transferTo(new File(img_path+fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		productService.insertProduct(vo);
		
		return "redirect:admin_product_list";
	}
	/*
	 * 상품 상세정보 출력
	 */
	@RequestMapping(value="admin_product_detail")
	public String adminProducDetailtAction(ProductVO vo, Criteria criteria, Model model) {
		String[] kindList = {"","힐", "부츠", "샌달", "슬리퍼", "스니커즈","세일즈"};
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);

		int index = Integer.parseInt(product.getKind());
		model.addAttribute("kind", kindList[index]);
		model.addAttribute("criteria", criteria);
		
		return "admin/product/productDetail";
	}
	/*
	 * 상품 수정화면 출력
	 */
	@RequestMapping(value="admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		String[] kindList = {"힐", "부츠", "샌달", "슬리퍼", "스니커즈","세일즈"};

		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productUpdate";
	}
	/*
	 * 상품 수정
	 */
	@RequestMapping(value="admin_product_update",method=RequestMethod.POST)
	public String adminProductUpdate(@RequestParam(value="product_image")MultipartFile uploadFile,
			                        @RequestParam(value="nonmakeImg")String org_image,
										ProductVO vo, Model model, HttpSession session) {
		if(!uploadFile.isEmpty()) {//상품이미지 수정한 경우
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);
			//이미지 파일을 이동할 실제경로 구하기
			String img_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
			
			try {
				uploadFile.transferTo(new File(img_path+fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();				
			}
		}else { //상품이미지를 수정하지 않은 경우
			vo.setImage(org_image);
		}
		
		if(vo.getUseyn() == null) {
			vo.setUseyn("n");
		}
//		else {
//			vo.setUseyn("y");
//		}
		if(vo.getBestyn() == null) {
			vo.setBestyn("n");
		}
//		else {
//			vo.setBestyn("y");
//		}
		
		productService.updateProduct(vo);			
		
		return "redirect:admin_product_list";
	}
	
	/*
	 * 상품별 판매 실적 화면 출력
	 */
	@RequestMapping(value="admin_sales_record_form")
	public String adminProductSalesChart() {
		
		return "admin/order/salesRecords";
	}
	
	/*
	 * 상품별 판매 실적 조회 및 데이터 전송(JSON포맷)
	 */
	@RequestMapping(value="sales_record_chart", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<SalesQuantity> salesRecordChart(){
		List<SalesQuantity> listSales = productService.getProductSales();
		
		return listSales;
	}
	
}