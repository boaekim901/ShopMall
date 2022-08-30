package com.ezen.biz.qna;

import java.util.List;

import com.ezen.biz.dto.QnaVO;

public interface QnaService {

	//전체 QnA목록 조회
	List<QnaVO> getListQna(String id);

	//Qna 게시글 조회
	QnaVO getQna(int qseq);

	void insertQna(QnaVO vo);

}