package proj.member.mvc;

import java.util.ArrayList;

public interface MemberDAO {
	
	// public abstract ����Ÿ�� �޼ҵ� �̸�(�Ű�����)
	
	// �ű� ���
	public abstract int insert (MemberVO vo);
	
	// �� �˻�
	public abstract MemberVO select(int index);
	
	// ��ü �˻�
	public abstract ArrayList<MemberVO> select();
	
	// ����
	public abstract int update(int index, MemberVO vo);
	
	// ���� (���� > ����)
	public abstract int delete(int index);

	// ��� �� ��ȯ
	public abstract int getSize();
	
	// cid�� �Ѱ��ֱ�
	public abstract int input(int cid);
	
	// cid�� �޾ƿ���
	public abstract int output();
	
	// cid�� �����
	public abstract int deleteCid();
	
	// ���� GRADE
	public abstract int updateGrd(int index, MemberVO vo);
	
	// ========================================
	// 3������ �ٹ޴� �޼ҵ�
	public abstract ArrayList<MemberVO> showAll(int compa, int dep, int grd);
	
	// 2����
	public abstract ArrayList<MemberVO> showCompDep(int compa, int dep);
	public abstract ArrayList<MemberVO> showCompGrd(int compa, int grd);
	public abstract ArrayList<MemberVO> showDepGrd(int dep, int grd);
	
	// 1����
	public abstract ArrayList<MemberVO> showComp(int compa);
	public abstract ArrayList<MemberVO> showDep(int dep);
	public abstract ArrayList<MemberVO> showGrd(int grd);
	
	// �̸� ���ڿ��� �˻��ϴ� �޼ҵ�
	public abstract ArrayList<MemberVO> showString(String str);
	
} // end MemberDAO
