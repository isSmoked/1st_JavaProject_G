package proj.contact.mvc;

// 회원 관리용 DAO
public interface ContactDAO {
	public abstract int insert1(ContactVO vo);
	
	public abstract int delete1(ContactVO vo);
	
	public abstract int select1(String id);
	
	public abstract int select1(ContactVO vo);
	
	public abstract int insertID(String id);
	
	public abstract String returnID(int cid);
}
