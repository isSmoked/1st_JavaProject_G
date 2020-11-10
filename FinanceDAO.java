package proj.finance.mvc;

import java.util.ArrayList;

public interface FinanceDAO {

	// �ܰ� DB�� �ִ� ����
	public abstract int insert(FinanceVO vo);
	
	// DB���� �ܰ� ������ ������ ����  - �ϳ��� ������ ����
	public abstract FinanceVO select(int index);
	
	// DB���� �ܰ� ������ ������ ���� - ��ü�� ������ ����
	public abstract ArrayList<FinanceVO> select();
	
	// DB�� ��� ������ ����� ����
	public abstract int delete(int index);
}
