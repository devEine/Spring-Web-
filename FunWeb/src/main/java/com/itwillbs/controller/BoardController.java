package com.itwillbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	//객체생
	
	@Autowired
	private BoardService boardService;
	
	
	// 가상주소 http://localhost:8080/FunWeb/board/write
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
		// 가상주소 변경없이 jsp 이동
	//  /WEB-INF/views/center/write.jsp
		return "center/write";
	}
	
	// 가상주소 http://localhost:8080/FunWeb/board/writePro
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardVO vo) {
		System.out.println(vo.getName());
		//글쓰기 작업
//		BoardService/BoardServiceImpl
//		BoardDAO/BoardDAOImpl
//		boardMapper.xml
//		BoardVO
		boardService.boardWrite(vo);
		// 가상주소로 주소 변경하면서 이동
		return "redirect:/board/list";
	}
	
	// 가상주소 http://localhost:8080/FunWeb/board/list
	// 가상주소 http://localhost:8080/FunWeb/board/list?pageNum=2
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Model model) {
		//목록에서 데이터 들고 이동
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) { //페이지 번호 없으면 1으로 셋팅 
			pageNum = "1";
		}
		int pageSize = 15; //페이지 사이즈 설정
		int currentPage = Integer.parseInt(pageNum); //pageNum정수형으로 변경 
		//PageVO에 페이징 관련 정보를 담아서 감 
		PageVO vo = new PageVO();
		vo.setPageNum(pageNum);
		vo.setPageSize(pageSize);
		vo.setCurrentPage(currentPage);
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		//전체 글 개수 
		int cnt = boardService.getBoardCount();
		//페이징 처리 
		int pageCount = cnt/pageSize + (cnt%pageSize == 0? 0:1);
		int pageBlock = 10;
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage+pageBlock -1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		vo.setCnt(cnt);
		vo.setPageCount(pageCount);
		vo.setPageBlock(pageBlock);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("vo",vo);
		
		// 가상주소 변경없이 jsp 이동
	//  /WEB-INF/views/center/notice.jsp
		return "center/notice";
	}
	
	//검색 메서드 
	// 가상주소 http://localhost:8080/FunWeb/board/listSearch
	@RequestMapping(value = "/board/listSearch", method = RequestMethod.GET)
	public String listSearch(HttpServletRequest request,Model model) {
		//검색어 가져오기 
		String search = request.getParameter("search");
		String search2="%"+search+"%";
		System.out.println(search2);
		
		//목록에서 데이터 들고 이동
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) { //페이지 번호 없으면 1으로 셋팅 
			pageNum = "1";
		}
		int pageSize = 15; //페이지 사이즈 설정
		int currentPage = Integer.parseInt(pageNum); //pageNum정수형으로 변경 
		//PageVO에 페이징 관련 정보를 담아서 감 
		PageVO vo = new PageVO();
		vo.setPageNum(pageNum);
		vo.setPageSize(pageSize);
		vo.setCurrentPage(currentPage);
		//% 포함하는 검색어 -> DB상
		vo.setSearch(search2);
		
		List<BoardVO> boardList = boardService.getBoardListSearch(vo);
		
		//전체 글 개수 
		int cnt = boardService.getBoardCountSearch(vo);
		//페이징 처리 
		int pageCount = cnt/pageSize + (cnt%pageSize == 0? 0:1);
		int pageBlock = 10;
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage+pageBlock -1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		vo.setCnt(cnt);
		vo.setPageCount(pageCount);
		vo.setPageBlock(pageBlock);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		//검색어 %없는 검색어 
		vo.setSearch(search);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("vo",vo);
		
		// 가상주소 변경없이 jsp 이동
	//  /WEB-INF/views/center/noticSearch.jsp
		return "center/noticeSearch";
	}
	
	
}
