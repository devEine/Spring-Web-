package com.itwillbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	
	//객체생성
//	MemberDAO memberDAO=new MemberDAOImpl();
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public void insertMember(MemberVO vo) {
		System.out.println("MemberServiceImpl insertMember()");
		memberDAO.insertMember(vo);
	}

	@Override
	public MemberVO loginMember(MemberVO vo) {
		System.out.println("MemberServiceImpl loginMember()");
		return memberDAO.loginMember(vo);
	}

	@Override
	public MemberVO loginMember(String userid, String userpw) {
		return null;
	}

	@Override
	public MemberVO getMember(String id) {
		//메서드 호출
		return memberDAO.getMember(id);
	}

	@Override
	public Integer updateMember(MemberVO uvo) {
		//메서드 호출
		return memberDAO.updateMember(uvo);
	}

	@Override
	public Integer deleteMember(MemberVO dvo) {
		return null;
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memberDAO.getMemberList();
	}

}
