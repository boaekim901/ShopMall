package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.QnaVO;

@Repository
public class QnaDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	//전체 QnA목록 조회
	public List<QnaVO> getListQna(String id){
		return mybatis.selectList("qnaMapper.getListQna", id);
	}
	
	//Qna 게시글 조회
	public QnaVO getQna(int qseq) {
		return mybatis.selectOne("qnaMapper.getQna", qseq);
	}
	
	public void insertQna(QnaVO vo) {
		mybatis.insert("qnaMapper.insertQna",vo);
	}
}
