package proj.contact.mvc;

// ȸ��(�α��� ����) ������ DAO
public interface ContactDAO {
	// ���� ���
	public abstract int insert1(ContactVO vo);
	
	// ���� ����
	public abstract int delete1(ContactVO vo);
	
	// id���� �޾ƿ� ��
	public abstract int select1(String id);
	
	// VO������ Ÿ������ �޾ƿ� ��
	public abstract int select1(ContactVO vo);
	
	// id�� ����
	public abstract int insertID(String id);
	
	// DB�� ����� cid������ ���̵� ����
	public abstract String returnID(int cid);
}
