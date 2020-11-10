package proj.finance.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;
import proj.etc.Constants;

public class FinanceDAOImple implements FinanceDAO , Constants{

	// Singleton -----------------------------------------------
	
		// 1. private static 자기 자신 타입의 멤버 변수 선언
		private static FinanceDAOImple instance = null;
		
		// 2. private 생성자
		private FinanceDAOImple() {
			// DB 드라이버 등록
			try {
				DriverManager.registerDriver(new OracleDriver());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
		public static FinanceDAO getInstance() {
			if (instance == null) {
				instance = new FinanceDAOImple();
			}
			return instance;
		}
	// ---------------------------------------------------------------
		
	
	@Override // 잔고를 DB에 넣는 과정
	public int insert(FinanceVO vo) {
		int result = 0;
		System.out.println(" sys.FinanceDAOImple.insert() LOG\n"); // 로그
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DB에 연결
			System.out.println("DB에 연결 성공!");
			pstmt = conn.prepareStatement(SQL_FINANCE_INSERT); // SQL을 받아옴
		
			// FinanceVO의 변수들을 받아오기 // SQL_FINANCE_INSERT에 저장 // ? 6개를 vo에서 입력받기
			pstmt.setInt(1, vo.getCurMoney()); // 현재 잔고 저장
			pstmt.setInt(2, vo.getWage()); // 급여 저장 
			pstmt.setDouble(3, vo.getNotWage()); // 비급여지출 저장
			pstmt.setDouble(4, vo.getConfidence()); // 예상금액 저장
			pstmt.setInt(5, vo.getIncome()); // 수입 저장
			pstmt.setInt(6, vo.getFin_date()); // 날짜 저장
			
			System.out.println("result : " + result + "\n");
			
			result = pstmt.executeUpdate(); // result 결과값 받아오기
			System.out.println("EMPLO_FINANCE 테이블에 " + result + "행의 데이터가 추가됨\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override // DB에서 잔고 정보를 빼오는 과정 - 하나
	public FinanceVO select(int index) {
		FinanceVO vo = null; // null로 초기화
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_FINANCE_SELECT_LAST);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int curMoney = rs.getInt(COL_FIN_CURMON);
				int wage = rs.getInt(COL_FIN_WAGE);
				double notWage = rs.getDouble(COL_FIN_NOTWAGE);
				double confidence = rs.getDouble(COL_FIN_CONFIDENCE);
				int income = rs.getInt(COL_FIN_INCOME);
				int FinDate = rs.getInt(COL_FIN_DATE);
				vo = new FinanceVO(curMoney, wage, notWage, income, confidence, FinDate);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return vo;
	}

	@Override // DB에서 잔고 정보를 빼오는 과정 - 전체  
	public ArrayList<FinanceVO> select() {
		ArrayList<FinanceVO> list = new ArrayList<FinanceVO>();
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_FINANCE_SELECT_ALL);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int curMoney = rs.getInt(COL_FIN_CURMON);
				int wage = rs.getInt(COL_FIN_WAGE);
				double notWage = rs.getDouble(COL_FIN_NOTWAGE);
				double confidence = rs.getDouble(COL_FIN_CONFIDENCE);
				int income = rs.getInt(COL_FIN_INCOME);
				int FinDate = rs.getInt(COL_FIN_DATE);
				FinanceVO vo = new FinanceVO(curMoney, wage, notWage, income, confidence, FinDate);
				list.add(vo);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}

	@Override // DB의 데이터들을 지우는 과정 
	// 맨 첫줄을 제외한 그 다음줄부터 마지막까지만 제거한다.
	public int delete(int index) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB연결 성공");
			pstmt = conn.prepareStatement(SQL_ID_DELETE); // 물음표가 하나기 때문에
			
			try {				
				result = pstmt.executeUpdate();  
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println(result + "행의 데이터가 삭제됨.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	

}
