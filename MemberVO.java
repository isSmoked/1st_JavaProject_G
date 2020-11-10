package proj.member.mvc;


public class MemberVO {
	//--------------------------------------------------------------------------------
	// 멤버 선언 (private)
	private int ps_id;
	private String ps_name;
	private int ps_dep;
	private int ps_grd;
	private int ps_comp;

	
	// 능력치 멤버 선언
	private int char_math;
	private int char_serv;
	private int char_hand;
	private int char_iq;
	private int char_scien;
	
	
	
	//----------------------------------------------------------------------------------
	// 생성자 생성
	public MemberVO() {}
	
	
	public MemberVO(int ps_id, String ps_name, int ps_dep, int ps_grd, int ps_comp, 
			int char_math, int char_serv, int char_hand, int char_iq, int char_scien) {
		super();
		this.ps_id = ps_id;
		this.ps_name = ps_name;
		this.ps_dep = ps_dep;
		this.ps_grd = ps_grd;
		this.ps_comp = ps_comp;
		
		this.char_math = char_math;
		this.char_serv = char_serv;
		this.char_hand = char_hand;
		this.char_iq = char_iq;
		this.char_scien = char_scien;
		
	}
	
	public MemberVO(String ps_name, int ps_dep, int ps_grd, int ps_comp, 
			int char_math, int char_serv, int char_hand, int char_iq, int char_scien) {
		super();
		this.ps_name = ps_name;
		this.ps_dep = ps_dep;
		this.ps_grd = ps_grd;
		this.ps_comp = ps_comp;
		
		this.char_math = char_math;
		this.char_serv = char_serv;
		this.char_hand = char_hand;
		this.char_iq = char_iq;
		this.char_scien = char_scien;
		
	}

	
	// getter / setter
	
	public int getPs_id() {
		return ps_id;
	}
	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}

	public String getPs_name() {
		return ps_name;
	}

	public void setPs_name(String ps_name) {
		this.ps_name = ps_name;
	}

	public int getPs_dep() {
		return ps_dep;
	}

	public void setPs_dep(int ps_dep) {
		this.ps_dep = ps_dep;
	}

	public int getPs_grd() {
		return ps_grd;
	}

	public void setPs_grd(int ps_grd) {
		this.ps_grd = ps_grd;
	}

	public int getPs_comp() {
		return ps_comp;
	}

	public void setPs_comp(int ps_comp) {
		this.ps_comp = ps_comp;
	}
	
	// 능력치 getter / setter
	
	public int getChar_math() {
		return char_math;
	}

	public void setChar_math(int char_math) {
		this.char_math = char_math;
	}

	public int getChar_serv() {
		return char_serv;
	}

	public void setChar_serv(int char_serv) {
		this.char_serv = char_serv;
	}

	public int getChar_hand() {
		return char_hand;
	}

	public void setChar_hand(int char_hand) {
		this.char_hand = char_hand;
	}

	public int getChar_iq() {
		return char_iq;
	}

	public void setChar_iq(int char_iq) {
		this.char_iq = char_iq;
	}

	public int getChar_scien() {
		return char_scien;
	}

	public void setChar_scien(int char_scien) {
		this.char_scien = char_scien;
	}
	
	
	

}
