package proj.contact.mvc;

// ȸ�����Կ� VO
public class ContactVO {
	
	// ��� ���� ����
	private String id;
	private String pw;
	
	// ������
	public ContactVO() {}
	public ContactVO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	public ContactVO(String id) {
		super();
		this.id = id;
	}
	
	// getter / setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
}
