package com.itwillbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;
import com.itwillbs.service.MemberService;

@RestController
public class AjaxController {

	// 객체생성
//	MemberService memberService=new MemberServiceImpl();
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	//http://localhost:8080/FunWeb/member/idcheck
	//http://localhost:8080/FunWeb/member/idcheck?id=itwill01
	//http://localhost:8080/FunWeb/member/idcheck?id=kim
	@RequestMapping(value = "/member/idcheck", method = RequestMethod.GET)
	public ResponseEntity<String> idcheck(HttpServletRequest request) {
		String id=request.getParameter("id");
		MemberVO memberVO=memberService.getMember(id);
		String result="";
		if(memberVO != null){
			//아이디 있음, 아이디 중복
//			result="아이디 중복";
			result="iddup";
		}else{
			//아이디 없음, 아이디 사용가능
//			result="아이디 사용가능";
			result="idok";
		}
		ResponseEntity<String> entity=new ResponseEntity<String>(result,HttpStatus.OK);
		return entity;
	}
	
	@RequestMapping(value = "/member/listJson", method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> list(HttpServletRequest request) {
		
		List<MemberVO> list=memberService.getMemberList();

		ResponseEntity<List<MemberVO>> entity=
				new ResponseEntity<List<MemberVO>>(list,HttpStatus.OK);
		// List<MemberVO> => 자동으로 json 변경하는 프로그램 설치
		// jackson-databind
		return entity;
	}
	
	@RequestMapping(value = "/board/listJson", method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> list2(HttpServletRequest request) {
		PageVO vo = new PageVO();
		vo.setPageNum("1");
		vo.setPageSize(5); //최근글 다섯 개 들고오기 
		vo.setCurrentPage(1);
		
		List<BoardVO> list=boardService.getBoardList(vo);
		
		ResponseEntity<List<BoardVO>> entity=
				new ResponseEntity<List<BoardVO>>(list,HttpStatus.OK);
		// List<MemberVO> => 자동으로 json 변경하는 프로그램 설치
		// jackson-databind
		return entity;
	}
	
}
