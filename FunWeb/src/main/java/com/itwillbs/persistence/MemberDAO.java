package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {
	
	// 테스트용 메서드 : DB 시간조회
	public String getTime();
	
	// 회원가입 동작
	public void insertMember(MemberVO vo);
	
	// 로그인 동작
	public MemberVO loginMember(MemberVO vo);
	
	// 로그인 동작2 (메서드 오버로딩)
	public MemberVO loginMember(String userid, String userpw);
	
	// 회원정보 조회
	public MemberVO getMember(String id);
	
	// 회원정보 수정
	public Integer updateMember(MemberVO uvo);
	
	// 회원정보 삭제
	public Integer deleteMember(MemberVO dvo);
	
	// 회원목록 리스트 조회
	public List<MemberVO> getMemberList();
	
	
	

}
