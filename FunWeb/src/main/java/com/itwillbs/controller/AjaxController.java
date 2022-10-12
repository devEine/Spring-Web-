package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@RestController
//스프링 버전과 jdk버전이 안맞아서 자동완성이 안되면?
//pom.xml에서 자바(1.8버전), Spring(pom.xml에서 4.3.8버전)으로 변경
public class AjaxController {
	
	//객체생성 대신에 inject주입으로 DI
	@Inject
	private MemberService memberService;
	
	//http://localhost:8088/FunWeb/member/idcheck
	//http://localhost:8088/FunWeb/member/idcheck?id=itwill
	//http://localhost:8088/FunWeb/member/idcheck?id=kim 등으로 검색하면 아이디중복체크 결과 나옴 
	@RequestMapping(value = "/member/idcheck", method = RequestMethod.GET)


	public ResponseEntity<String> idcheck(HttpServletRequest request) {
		//값 넘길 때 request매개변수로 사용 
			String id = request.getParameter("id");
			//파라미터값으로 id변수에 담아줌
			
			MemberVO memberVO = memberService.getMember(id);
			
			String result="";
			if(memberVO != null){
				//아이디 있음, 아이디 중복
				result="iddup";
			}else{
				//아이디 없음, 아이디 사용가능
				result="idok";
			}
			//데이터를 리턴할 것이기에 ResponseEntity사용 
			ResponseEntity<String> entity = new ResponseEntity<>(result,HttpStatus.OK);
			return entity;
	}
}
