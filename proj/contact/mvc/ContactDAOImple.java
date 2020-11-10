package proj.contact.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;
import proj.etc.Constants;

public class ContactDAOImple implements ContactDAO, Constants{

	// Singleton -----------------------------------------------
	
	// 1. private static �ڱ� �ڽ� Ÿ���� ��� ���� ����
	private static ContactDAOImple instance = null;
	
	// 2. private ������
	private ContactDAOImple() {
		// DB ����̹� ���
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 3. public static �޼ҵ� - �ν��Ͻ��� �����ϴ� �޼ҵ� ����
	public static ContactDAO getInstance() {
		if (instance == null) {
			instance = new ContactDAOImple();
		}
		return instance;
	}
	// ---------------------------------------------------------------

	// ���� ���
	@Override
	public int insert1(ContactVO vo) {
		int result = 0;
		System.out.println("log");
		Connection conn = null;
		PreparedStatement pstmt = null; // ������ �Է¿�
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DB�� ����
			System.out.println("DB�� ���� ����!");
			pstmt = conn.prepareStatement(SQL_ID_INSERT); // SQL�� �޾ƿ�
			pstmt2 = conn.prepareStatement(SQL_ID_SELECT); // �� SQL
			rs = pstmt2.executeQuery();
			
			while (rs.next()) {
				String id2 = rs.getString(COL_LOG_ID);
				if (vo.getId().equals(id2)) { // ���̵� �ߺ� Ȯ��
					result = 1; // �ߺ��̴�.
					System.out.println("\n sys : �ߺ��� ���̵�");
					break;
				} 
			} 
			if (result != 1) { // ���̵� ��й�ȣ�� ���� ���� �� ��� ����
				pstmt.setString(1, vo.getId()); // ? 2���� vo���� �Է¹ޱ�
				pstmt.setString(2, vo.getPw()); // SQL_ID_INSERT�� ����
				System.out.println("result : " + result);
			}
			result = pstmt.executeUpdate(); // result ����� �޾ƿ���
			System.out.println(result + "���� �����Ͱ� �߰���");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt2.close();
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// ���� ����
	@Override
	public int delete1(ContactVO vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB���� ����");
			pstmt = conn.prepareStatement(SQL_ID_DELETE); // ����ǥ�� �ϳ��� ������ 
			pstmt2 = conn.prepareStatement(SQL_ID_SELECT);
			rs = pstmt2.executeQuery();
			
			while (rs.next()) {
				String id2 = rs.getString(COL_LOG_ID);
				String pw2 = rs.getString(COL_LOG_PW);
				if (vo.getId().equals(id2) && vo.getPw().equals(pw2)) { // ���̵� �ߺ� Ȯ��
					result = 1;
					System.out.println(vo.getId()); /* IDȮ�� �α� */
					pstmt.setString(1, vo.getId()); // SQL�� ���̵� ����ְ� ������ �ϰڴٴ� ��
					break;
				} 
			} 
			if (result != 1) { // ���̵� ��й�ȣ�� ���� ���� ��
				System.out.println("���̵�� ��й�ȣ ����");
				return result;
			}
			// TODO : ����ִ� ���� �������� �� ����ó�� 
			
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
				rs.close();
				pstmt2.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	
	}

	// id���� �޾ƿ� ��
	@Override
	public int select1 (String id) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����!");
			
			pstmt = conn.prepareStatement(SQL_ID_SELECT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id2 = rs.getString(COL_LOG_ID);
				if (id.equals(id2)) { // ���̵� �ߺ� Ȯ��
					result = 1;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (NumberFormatException e){
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// VO������ Ÿ������ �޾ƿ� ��
	@Override
	public int select1(ContactVO vo) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����!");
			
			pstmt = conn.prepareStatement(SQL_ID_SELECT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id2 = rs.getString(COL_LOG_ID);
				String pw2 = rs.getString(COL_LOG_PW);
				
				if (vo.getId().equals(id2)) { // ���̵� �ߺ� Ȯ��
					if (vo.getPw().equals(pw2)) {						
						result = 1; // result => 1 �α��� ����
					} else {
						result = 2; // result => 2 ��й�ȣ ����
					}
				}
				// result => 0 ���̵� ����
			}
			switch (result) {
				case 1 : 
					System.out.println(" sysLog : �α��� ����");
					break;
				case 2 : 
					System.out.println(" sysLog : ��й�ȣ ����");
					break;
				case 0 :
					System.out.println(" sysLog : ��ϵ��� ���� ���̵��Դϴ�.");
					break;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (NumberFormatException e){
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// �α��� �� ���̵� �������� �Լ�
	@Override
	public int insertID(String id) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����!");
			pstmt = conn.prepareStatement(SQL_LOGIN_INSERT);
			
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
			System.out.println(" sysLog : " + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// DB�� ����� cid������ ���̵� ����
	@Override
	public String returnID(int cid) {
		String str = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����!");
			pstmt = conn.prepareStatement(SQL_LOGIN_SELECT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {				
				str = rs.getString(COL_ID_ID);
			}
			
			
			System.out.println(" sysLog  : " + str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return str;
	}
}
