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
	
		// 1. private static �ڱ� �ڽ� Ÿ���� ��� ���� ����
		private static FinanceDAOImple instance = null;
		
		// 2. private ������
		private FinanceDAOImple() {
			// DB ����̹� ���
			try {
				DriverManager.registerDriver(new OracleDriver());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 3. public static �޼ҵ� - �ν��Ͻ��� �����ϴ� �޼ҵ� ����
		public static FinanceDAO getInstance() {
			if (instance == null) {
				instance = new FinanceDAOImple();
			}
			return instance;
		}
	// ---------------------------------------------------------------
		
	
	@Override // �ܰ� DB�� �ִ� ����
	public int insert(FinanceVO vo) {
		int result = 0;
		System.out.println(" sys.FinanceDAOImple.insert() LOG\n"); // �α�
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DB�� ����
			System.out.println("DB�� ���� ����!");
			pstmt = conn.prepareStatement(SQL_FINANCE_INSERT); // SQL�� �޾ƿ�
		
			// FinanceVO�� �������� �޾ƿ��� // SQL_FINANCE_INSERT�� ���� // ? 6���� vo���� �Է¹ޱ�
			pstmt.setInt(1, vo.getCurMoney()); // ���� �ܰ� ����
			pstmt.setInt(2, vo.getWage()); // �޿� ���� 
			pstmt.setDouble(3, vo.getNotWage()); // ��޿����� ����
			pstmt.setDouble(4, vo.getConfidence()); // ����ݾ� ����
			pstmt.setInt(5, vo.getIncome()); // ���� ����
			pstmt.setInt(6, vo.getFin_date()); // ��¥ ����
			
			System.out.println("result : " + result + "\n");
			
			result = pstmt.executeUpdate(); // result ����� �޾ƿ���
			System.out.println("EMPLO_FINANCE ���̺� " + result + "���� �����Ͱ� �߰���\n");
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

	@Override // DB���� �ܰ� ������ ������ ���� - �ϳ�
	public FinanceVO select(int index) {
		FinanceVO vo = null; // null�� �ʱ�ȭ
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
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

	@Override // DB���� �ܰ� ������ ������ ���� - ��ü  
	public ArrayList<FinanceVO> select() {
		ArrayList<FinanceVO> list = new ArrayList<FinanceVO>();
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
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

	@Override // DB�� �����͵��� ����� ���� 
	// �� ù���� ������ �� �����ٺ��� ������������ �����Ѵ�.
	public int delete(int index) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB���� ����");
			pstmt = conn.prepareStatement(SQL_ID_DELETE); // ����ǥ�� �ϳ��� ������
			
			try {				
				result = pstmt.executeUpdate();  
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println(result + "���� �����Ͱ� ������.");
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
