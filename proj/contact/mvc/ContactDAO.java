package proj.contact.mvc;

// 회원(로그인 계정) 관리용 DAO
public interface ContactDAO {
	// 계정 등록
	public abstract int insert1(ContactVO vo);
	
	// 계정 삭제
	public abstract int delete1(ContactVO vo);
	
	// id만을 받아올 때
	public abstract int select1(String id);
	
	// VO데이터 타입으로 받아올 때
	public abstract int select1(ContactVO vo);
	
	// id를 저장
	public abstract int insertID(String id);
	
	// DB에 저장된 cid값으로 아이디를 리턴
	public abstract String returnID(int cid);
}
