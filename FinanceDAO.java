package proj.finance.mvc;

import java.util.ArrayList;

public interface FinanceDAO {

	// 잔고를 DB에 넣는 과정
	public abstract int insert(FinanceVO vo);
	
	// DB에서 잔고 정보를 빼오는 과정  - 하나만 빼오는 과정
	public abstract FinanceVO select(int index);
	
	// DB에서 잔고 정보를 빼오는 과정 - 전체를 빼오는 과정
	public abstract ArrayList<FinanceVO> select();
	
	// DB의 모든 정보를 지우는 과정
	public abstract int delete(int index);
}
