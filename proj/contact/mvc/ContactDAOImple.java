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
	
	// 1. private static 자기 자신 타입의 멤버 변수 선언
	private static ContactDAOImple instance = null;
	
	// 2. private 생성자
	private ContactDAOImple() {
		// DB 드라이버 등록
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
	public static ContactDAO getInstance() {
		if (instance == null) {
			instance = new ContactDAOImple();
		}
		return instance;
	}
	// ---------------------------------------------------------------

	// 계정 등록
	@Override
	public int insert1(ContactVO vo) {
		int result = 0;
		System.out.println("log");
		Connection conn = null;
		PreparedStatement pstmt = null; // 상세정보 입력용
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DB에 연결
			System.out.println("DB에 연결 성공!");
			pstmt = conn.prepareStatement(SQL_ID_INSERT); // SQL을 받아옴
			pstmt2 = conn.prepareStatement(SQL_ID_SELECT); // 비교 SQL
			rs = pstmt2.executeQuery();
			
			while (rs.next()) {
				String id2 = rs.getString(COL_LOG_ID);
				if (vo.getId().equals(id2)) { // 아이디 중복 확인
					result = 1; // 중복이다.
					System.out.println("\n sys : 중복된 아이디");
					break;
				} 
			} 
			if (result != 1) { // 아이디 비밀번호가 같지 않을 때 등록 가능
				pstmt.setString(1, vo.getId()); // ? 2개를 vo에서 입력받기
				pstmt.setString(2, vo.getPw()); // SQL_ID_INSERT에 저장
				System.out.println("result : " + result);
			}
			result = pstmt.executeUpdate(); // result 결과값 받아오기
			System.out.println(result + "행의 데이터가 추가됨");
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
	
	// 계정 삭제
	@Override
	public int delete1(ContactVO vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB연결 성공");
			pstmt = conn.prepareStatement(SQL_ID_DELETE); // 물음표가 하나기 때문에 
			pstmt2 = conn.prepareStatement(SQL_ID_SELECT);
			rs = pstmt2.executeQuery();
			
			while (rs.next()) {
				String id2 = rs.getString(COL_LOG_ID);
				String pw2 = rs.getString(COL_LOG_PW);
				if (vo.getId().equals(id2) && vo.getPw().equals(pw2)) { // 아이디 중복 확인
					result = 1;
					System.out.println(vo.getId()); /* ID확인 로그 */
					pstmt.setString(1, vo.getId()); // SQL에 아이디를 집어넣고 삭제를 하겠다는 말
					break;
				} 
			} 
			if (result != 1) { // 아이디 비밀번호가 같지 않을 때
				System.out.println("아이디랑 비밀번호 오류");
				return result;
			}
			// TODO : 비어있는 것을 삭제했을 때 예외처리 
			
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

	// id만을 받아올 때
	@Override
	public int select1 (String id) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공!");
			
			pstmt = conn.prepareStatement(SQL_ID_SELECT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id2 = rs.getString(COL_LOG_ID);
				if (id.equals(id2)) { // 아이디 중복 확인
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
	
	// VO데이터 타입으로 받아올 때
	@Override
	public int select1(ContactVO vo) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공!");
			
			pstmt = conn.prepareStatement(SQL_ID_SELECT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id2 = rs.getString(COL_LOG_ID);
				String pw2 = rs.getString(COL_LOG_PW);
				
				if (vo.getId().equals(id2)) { // 아이디 중복 확인
					if (vo.getPw().equals(pw2)) {						
						result = 1; // result => 1 로그인 가능
					} else {
						result = 2; // result => 2 비밀번호 오류
					}
				}
				// result => 0 아이디 오류
			}
			switch (result) {
				case 1 : 
					System.out.println(" sysLog : 로그인 가능");
					break;
				case 2 : 
					System.out.println(" sysLog : 비밀번호 오류");
					break;
				case 0 :
					System.out.println(" sysLog : 등록되지 않은 아이디입니다.");
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
	
	// 로그인 한 아이디를 저장해줄 함수
	@Override
	public int insertID(String id) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공!");
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
	
	// DB에 저장된 cid값으로 아이디를 리턴
	@Override
	public String returnID(int cid) {
		String str = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공!");
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
