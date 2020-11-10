package proj.member.mvc;

import java.util.ArrayList;

public interface MemberDAO {
	
	// public abstract 리턴타입 메소드 이름(매개변수)
	
	// 신규 등록
	public abstract int insert (MemberVO vo);
	
	// 상세 검색
	public abstract MemberVO select(int index);
	
	// 전체 검색
	public abstract ArrayList<MemberVO> select();
	
	// 수정
	public abstract int update(int index, MemberVO vo);
	
	// 삭제 (재직 > 퇴직)
	public abstract int delete(int index);

	// 멤버 수 반환
	public abstract int getSize();
	
	// cid값 넘겨주기
	public abstract int input(int cid);
	
	// cid값 받아오기
	public abstract int output();
	
	// cid값 지욱기
	public abstract int deleteCid();
	
	// 수정 GRADE
	public abstract int updateGrd(int index, MemberVO vo);
	
	// ========================================
	// 3가지를 다받는 메소드
	public abstract ArrayList<MemberVO> showAll(int compa, int dep, int grd);
	
	// 2가지
	public abstract ArrayList<MemberVO> showCompDep(int compa, int dep);
	public abstract ArrayList<MemberVO> showCompGrd(int compa, int grd);
	public abstract ArrayList<MemberVO> showDepGrd(int dep, int grd);
	
	// 1가지
	public abstract ArrayList<MemberVO> showComp(int compa);
	public abstract ArrayList<MemberVO> showDep(int dep);
	public abstract ArrayList<MemberVO> showGrd(int grd);
	
	// 이름 문자열로 검색하는 메소드
	public abstract ArrayList<MemberVO> showString(String str);
	
} // end MemberDAO
