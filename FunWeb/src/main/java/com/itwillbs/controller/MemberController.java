package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
//어노테이션 추가함으로써 주소 매핑하는 자바 파일로 역할 부여됨
public class MemberController {
	

	//객체생성 대신에 inject주입으로 DI
	@Inject
	private MemberService memberService;
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	//가상주소 : http://localhost:8088/FunWeb/insert로 검색 시, web-inf/views/insert.jsp페이지로 연결(매핑됨)
	//회원가입하는 주소매핑 -> "/insert"로 설정 
	
	public String insert() {
	// 회원가입 메서드 -> 주소매핑
	// web-inf/views/insert.jsp으로 가도록 연결
		return "insert";
	//web-inf/views/ㅁㅁㅁ.jsp로 자동으로 연결됨 -> web.xml파일에 매핑 정의되어 있음
	}
	
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	//가상주소 : http://localhost:8088/FunWeb/member/insert로 검색 시, web-inf/FunWeb/member/join.jsp페이지로 연결(매핑됨)
	//회원가입하는 주소매핑 -> "/member/insert"로 설정 
	
	public String insert2() {
	// web-inf/views/member/join.jsp으로 가도록 연결
		return "member/join";
	//가상주소 변경없이 페이지 이동
	//실제 프로젝트 안의 페이지
	//web-inf/views/aaa/ㅁㅁㅁ.jsp로 자동으로 연결됨 -> web.xml파일에 매핑 정의되어 있음
	}
	
	// 가상주소 http://localhost:8080/FunWeb/member/insertPro
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro(MemberVO memberVO) {
		System.out.println("MemberController insertPro()");
		System.out.println(memberVO.getUserid());
		System.out.println(memberVO.getUserpw());
		System.out.println(memberVO.getUsername());
		System.out.println(memberVO.getUseremail());
		//회원가입 
		memberService.insertMember(memberVO);
		// 로그인 페이지로 이동
		// 주소가 변경되면서 가상주소 이동
//		response.sendRedirect() 
		return "redirect:/member/login";
	}
	
	// 가상주소 : http://localhost:8088/FunWeb/member/login
	// => /web-inf/views/member/login.jsp로 이동
	@RequestMapping(value= "/member/login", method = RequestMethod.GET)
	// 가상주소 : http://localhost:8088/FunWeb/member/login로 검색 시,
	public String login() {
		System.out.println("MemberController login()");
	// 회원가입 페이지에서 로그인 페이지로 이동 -> 페이지 연결
		return "member/login";
	// 실제 프로젝트 안의 페이지
	// web-inf/views/aaa/ㅁㅁㅁ.jsp로 자동으로 연결됨 -> web.xml파일에 매핑 정의되어 있음
	}
	
	// 가상주소 : http://localhost:8088/FunWeb/member/loginPro -> POST방식
	// => 로그인처리 메세지 출력 "MemberController loginPro()"
	// => 주소 변경하면서 가상주소이동 redirect:/main/main
	@RequestMapping(value= "/member/loginPro", method = RequestMethod.POST)
	// 가상주소 : http://localhost:8088/FunWeb/member/loginPro로 검색 시,
	public String loginPro(HttpSession session,MemberVO vo) {
		//(스프링: 메서드 안에 매개변수로 session넣으면 자동으로 session값 받아와짐)
		System.out.println("MemberController loginPro()");
		//로그인 처리 
		MemberVO vo2 = memberService.loginMember(vo);
		if(vo2!=null) {
			// 아이디 비밀번호 일치 
			// 로그인 처리 후 메인 페이지로 이동
			session.setAttribute("loginID", vo.getUserid());
			// vo에서 바로 getUserid부름 
			
			// 주소가 변경되면서 가상주소 이동 -> redirect:/
			return "redirect:/main/main";
		}else {
			// 아이디 비밀번호 틀림
			System.out.println("틀림");
			return "member/msg";
		}
		
	}
	
	// 가상주소 : http://localhost:8088/FunWeb/main/main
	// => /web-inf/views/main/main.jsp로 이동
	@RequestMapping(value= "/main/main", method = RequestMethod.GET)
	public String main() {
	// 가상주소 변경없이 jsp 이동
	// web-inf/views/main/main.jsp
		return "main/main";
	
	}
	
	// 가상주소 : http://localhost:8088/FunWeb/member/logout
	// 로그아웃 처리메세지 "MemberController logout()"
	// 가상주소 redirect:/main/main 이동
	@RequestMapping(value= "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//(스프링: 메서드 안에 매개변수로 session넣으면 자동으로 session값 받아와짐)
		System.out.println("MemberController logout()");
		session.invalidate();
		//로그아웃 -> 세션값 초기화 
	//메인 페이지로 이동 -> 주소가 변경되면서 가상주소 이동 (reponse.sendRedirect)
		return "redirect:/main/main";
	
	}
	
	
	// 가상주소 : http://localhost:8088/FunWeb/member/update
	// => /web-inf/views/member/update.jsp로 이동
	@RequestMapping(value= "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		//세션값 가져오기
		String id = (String)session.getAttribute("loginID");
		//수정할 정보 가지고 감 
		MemberVO vo = memberService.getMember(id);
		// 기존: request.setAttribure("vo",vo);
		// Moldel -> setAttribute와 같은 역할을 함 (데이터를 담아서 전송할 수 있게)
		model.addAttribute("vo",vo);
		
		//가상주소 변경없이 jsp페이지 이동
		//web-inf/views/member/update.jsp 이동
		return "/member/update";
	
	}
	
	
	
	// 가상주소 : http://localhost:8088/FunWeb/member/updatePro
	// 수정처리 메세지 "MemberController updatePro()"
	// 가상주소 redirect:/main/main 이동
	@RequestMapping(value= "/member/updatePro", method = RequestMethod.POST)
	public String updatePro(HttpSession session) {
		System.out.println("MemberController updatePro()");
		//loginMember 비밀번호 일치 여부 확인
		
		// 일치하면 수정처리 -> /main/main 이동
		
		// 불일치하면 -> msg.jsp 뒤로이동 
		
		return "redirect:/main/main";
	}

}
