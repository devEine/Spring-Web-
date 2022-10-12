package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	//객체생성
	@Inject //또는 @Autowired
	private MemberDAO memberDAO;
	
	
	@Override
	public Integer deleteMember(MemberVO dvo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberVO getMember(String id) {
		//메서드 호출                                                                    
		return memberDAO.getMember(id);
	}
	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		return null;
	}@Override
	public void insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		System.out.println("MemberSerciecImpl insertMember()");
		memberDAO.insertMember(vo);
		
	}@Override
	public MemberVO loginMember(MemberVO vo) {
		System.out.println("MemberServiceImpl LoginMember()");
		return memberDAO.loginMember(vo);
		
	}@Override
	public MemberVO loginMember(String userid, String userpw) {
		// TODO Auto-generated method stub
		return null;
	}@Override
	public Integer updateMember(MemberVO uvo) {
		// TODO Auto-generated method stub
		return null;
	}public MemberServiceImpl() {
		// TODO Auto-generated constructor stub
	}
}
