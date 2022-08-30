package com.ezen.biz.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCommentVO {
	private int comment_seq;
	private int pseq;
	private String content;
	private String writer;
	private Date regDate;
	private Date modifyDate;
	
}
