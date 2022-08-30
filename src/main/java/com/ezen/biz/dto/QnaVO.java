package com.ezen.biz.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {

	private int qseq;
	private String subject;   //제목
	private String content;   //문의내용
	private String reply;     //답변 내용
	private String id;
	private String rep;       //답변유무
	private Date indate;
}
