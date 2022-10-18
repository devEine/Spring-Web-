package com.itwillbs.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	//객체생성
	@Autowired
	private BoardDAO boardDAO;
	
	//글쓰기 메서드 
	@Override
	public void boardWrite(BoardVO vo) {
//		pass name subject content
//		bno,readcount,re_ref,re_lev,re_seq,date,ip
		vo.setReadcount(0);
		vo.setRe_lev(0);
		vo.setRe_seq(0);
		vo.setDate(new Date(System.currentTimeMillis()));
		// bno
		if(boardDAO.getMaxNo()==null) {
			//게시판 글 없는 경우
			vo.setBno(1);
			vo.setRe_ref(1);
		}else {
			// 게시판 글 있음 최대번호 +1
			vo.setBno(boardDAO.getMaxNo()+1);
			vo.setRe_ref(boardDAO.getMaxNo()+1);
		}
		//메서드호출
		boardDAO.boardWrite(vo);
	}
	
	//글 리스트 메서드
	@Override
	public List<BoardVO> getBoardList(PageVO vo) {
		//startRow, endRow 구하기 
		int startRow = (vo.getCurrentPage()-1)*vo.getPageSize()+1;
		int endRow = vo.getCurrentPage()*vo.getPageSize();
		
		vo.setStartRow(startRow-1); //sql에서는 -1못해서 service에서 미리 -1 해줌 
		vo.setEndRow(endRow);
		
		return boardDAO.getBoardList(vo);
	}

	// 글 개수 구하는 메서드
	@Override
	public int getBoardCount() {

		return boardDAO.getBoardCount();
	}

	@Override
	public List<BoardVO> getBoardListSearch(PageVO vo) {
		// startRow, endRow 구하기
		int startRow = (vo.getCurrentPage() - 1) * vo.getPageSize() + 1;
		int endRow = vo.getCurrentPage() * vo.getPageSize();

		vo.setStartRow(startRow - 1); // sql에서는 -1못해서 service에서 미리 -1 해줌
		vo.setEndRow(endRow);
		return boardDAO.getBoardListSearch(vo);
	}
	
	@Override
	public int getBoardCountSearch(PageVO vo) {
		return boardDAO.getBoardCountSearch(vo);
	}

}
