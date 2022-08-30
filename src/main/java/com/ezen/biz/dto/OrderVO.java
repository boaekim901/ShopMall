package com.ezen.biz.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderVO {

	private int odseq;    //주문상세일련번호
	private int oseq;     //주문일련번호
	private String id;    //사용자의 로그인 아이디
	private Date indate;
	private String mname;
	private String zip_num;
	private String address;
	private String phone;
	private int pseq;
	private String pname;
	private int quantity;
	private int price2;
	private String result;
	
}
