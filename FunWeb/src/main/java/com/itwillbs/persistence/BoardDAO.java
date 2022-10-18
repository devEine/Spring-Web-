package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

public interface BoardDAO {
	//추상메서드
	public Integer getMaxNo();
	
	public void boardWrite(BoardVO vo);

	public List<BoardVO> getBoardList(PageVO vo);

	public int getBoardCount();

	public List<BoardVO> getBoardListSearch(PageVO vo);


	public int getBoardCountSearch(PageVO vo);
	
	
}
