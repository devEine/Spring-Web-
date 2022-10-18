package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 스프링(root-context.xml)에서 파일을 DAO 파일로 인식하도록 설정

@Repository
public class MemberDAOImpl implements MemberDAO {

	// DB에 관련된 동작을 수행
	private static final Logger log 
	= LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// 디비연결 정보 필요 => 의존관계 
	// sqlSessionFactory 객체 필요함(주입)
	// 이미 생성된 객체  [root-context.xml - sqlSessionFactory객체(빈)]
	//	@Inject
	//	private SqlSessionFactory factory;
	
	// 디비연결 + MyBatis설정(mapper) + 자원해제
	@Autowired
	private SqlSession sqlSession;   // DI
	
	// mapper의 주소(이름) - 상수화
	private static final String NAMESPACE 
	     ="com.itwillbs.mapper.MemberMapper";
	
	
	// alt shift s + v
	@Override
	public String getTime() {
		// 1.2. 디비연결 		// 3. sql 작성
		// 4. sql 실행		// 5. 데이터 처리	
		//SqlSession sqlSession = factory.openSession();
		
		String now
		   =sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		
		return now;
	}


	@Override
	public void insertMember(MemberVO vo) {
		log.info("@@@@@ 1.2. 디비 연결-sqlSession(DI 객체)");
		log.info("@@@@@ 3. sql 작성 - (membmerMapper.xml)");
		log.info("@@@@@ 3. pstmt 객체 생성 - sqlSession(DI 객체)");
		log.info("@@@@@ 4. SQL 실행 - sqlSession(DI 객체)");
		// sqlSession.insert(SQL구문,전달할 객체);
		sqlSession.insert(NAMESPACE+".insertMember",vo);
		//com.itwillbs.mapper.MemberMapper.insertMember
		log.info("@@@@@  전달하는 VO 객체는 mapper에서 자동으로 매핑후 정보 전달 ");
		log.info("@@@@@  DAOImpl -> mapper 이동 -> MySQL 이동");
		
		log.info("@@@@@  자원해제 - sqlSession(DI 객체)");
	}


	@Override
	public MemberVO loginMember(MemberVO vo) {
		log.info("loginMember(vo) 호출"); 
		
		MemberVO resultVO 
		   = sqlSession.selectOne(NAMESPACE+".loginMember",vo);
		
		// mapper에서 쿼리 실행 결과 저장해서 리턴
		
		return resultVO;
	}


	@Override
	public MemberVO loginMember(String userid, String userpw) {
		log.info("loginMember(userid,userpw) 호출");
		
		// mapper에 정보를 1개만 전달 가능
		//sqlSession.selectOne(NAMESPACE+".loginMember",userid,userpw);(x)
		
		// 전달된 정보를 하나의 도메인 객체에 저장후 처리
		//		MemberVO vo = new MemberVO();
		//		vo.setUserid(userid);
		//		vo.setUserpw(userpw);
		//	
		//		sqlSession.selectOne(NAMESPACE+".loginMember",vo);
		
		// 회원정보  + 게시판정보 => 하나의 도메인(MemberVO) 저장 X
		// => 컬렉션을 사용 (연관없는 데이터를 한번에 저장)
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("컬럼명", 데이터값);
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		return sqlSession.selectOne(NAMESPACE+".loginMember",paramMap);
	}


	@Override
	public MemberVO getMember(String id) {

		log.info(" getMember(String id) 호출 ");
		log.info(" mapper-sql 구문 호출 동작 ");
		
		String userid=id;
		
		MemberVO resultVO 
		   = sqlSession.selectOne(NAMESPACE+".getMember",userid);
		//"com.itwillbs.mapper.MemberMapper.getMember"
		log.info(resultVO+"");
		log.info("테스트 파일로 이동");
		   
		return resultVO;
	}


	@Override
	public Integer updateMember(MemberVO uvo) {
		
		log.info(" 테스트 -> updateMember(MemberVO uvo) 호출 ");
		
		int result = sqlSession.update(NAMESPACE+".updateMember",uvo);
		
		log.info(" 회원 정보 수정 완료 ");
		// result => 0 (수정x),1 (수정o)	
		log.info(" updateMember -> 테스트 호출 ");
		
		return result;
	}


	@Override
	public Integer deleteMember(MemberVO dvo) {
		// mapper - sql 호출
		return sqlSession.delete(NAMESPACE+".deleteMember",dvo);
	}


	@Override
	public List<MemberVO> getMemberList() {

		// DB에서 VO형태의 객체가 전달되면,
		// List형태로 저장 
		List<MemberVO> memberList 
		     = sqlSession.selectList(NAMESPACE+".getMemberList");
		
		return memberList;
		
		//return sqlSession.selectList(NAMESPACE+".getMemberList");
	}
	
	
	
	
	
	

	
}
