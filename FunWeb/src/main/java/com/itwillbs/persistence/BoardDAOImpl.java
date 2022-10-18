package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	// 마이바티스 객체생성
	@Autowired
	private SqlSession sqlSession;
	// boardMapper 가상이름 정의
	public static final String namespace="com.itwillbs.mapper.BoardMapper";
	
	@Override
	public Integer getMaxNo() {
		return sqlSession.selectOne(namespace+".getMaxNo");
	}
	
	@Override
	public void boardWrite(BoardVO vo) {
		sqlSession.insert(namespace+".boardWrite", vo);
	}
	
	//글 리스트 메서드 
	@Override
	public List<BoardVO> getBoardList(PageVO vo) {
		return sqlSession.selectList(namespace + ".getBoardList", vo);
	}
	
	//글 개수 구하기 메서드
	@Override
	public int getBoardCount() {

		return sqlSession.selectOne(namespace + ".getBoardCount");
	}

	@Override
	public List<BoardVO> getBoardListSearch(PageVO vo) {
		
		return sqlSession.selectList(namespace+".getBoardListSearch",vo);
	}

	@Override
	public int getBoardCountSearch(PageVO vo) {
		
		return sqlSession.selectOne(namespace+".getBoardCountSearch",vo);
	}

}
