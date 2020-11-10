package proj.member.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;
import proj.etc.Constants;

public class MemberDAOImple implements MemberDAO , Constants{

	// Singleton -----------------------------------------------
	
	// 1. private static 자기 자신 타입의 멤버 변수 선언
	private static MemberDAOImple instance = null;
	
	// 2. private 생성자
	private MemberDAOImple() {
		// DB 드라이버 등록
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAOImple();
		}
		return instance;
	}
	// ---------------------------------------------------------------
	
	// 신규 등록
	@Override
	public int insert(MemberVO vo) { // vo를 DB에 저장
		int result1 = 0; 
		
		Connection conn = null;
		PreparedStatement pstmt = null; // 상세정보 입력용
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DB에 연결
			System.out.println("DB에 연결 성공!");
			
			pstmt = conn.prepareStatement(SQL_INSERT); // SQL을 받아옴
			pstmt.setString(1, vo.getPs_name()); // ? 4개를 vo에서 입력받기
			pstmt.setInt(2, vo.getPs_dep());
			pstmt.setInt(3, vo.getPs_grd());
			pstmt.setInt(4, vo.getPs_comp());
			pstmt.setInt(5, vo.getChar_math());
			pstmt.setInt(6, vo.getChar_serv());
			pstmt.setInt(7, vo.getChar_hand());
			pstmt.setInt(8, vo.getChar_iq());
			pstmt.setInt(9, vo.getChar_scien());
			
			result1 = pstmt.executeUpdate(); // result 결과값 받아오기
			
			System.out.println(result1 + "행의 데이터가 추가됨");
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
		return result1;
	}

	// 상세 검색
	@Override
	public MemberVO select(int index) { // DB에서 인덱스 데이터(vo)를 선택
		MemberVO vo = null; // null로 초기화
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT_BY_CID); // 물음표 1개
			pstmt.setInt(1, index); // 첫번째 물음표에 index를 집어 넣겠다.
			rs = pstmt.executeQuery();
			System.out.println(index + " index ");
			
			if (rs.next()) {
				System.out.println("ResultSet 실행");
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int departId = rs.getInt(COL_DEP);
				int gradeId = rs.getInt(COL_GRD);
				int compId = rs.getInt(COL_COMP);
				
				int charMath = rs.getInt(COL_CHAR_MATH);
				int charServ = rs.getInt(COL_CHAR_SERV);
				int charHand = rs.getInt(COL_CHAR_HAND);
				int charIq = rs.getInt(COL_CHAR_IQ);
				int charScien = rs.getInt(COL_CHAR_SCIEN);
				
				
				
				vo = new MemberVO(cid, name, departId, gradeId, compId,
						charMath, charServ, charHand, charIq, charScien);
				
				System.out.println(" sys.MemberDAOImple.select().id : " + vo.getPs_id());
			}
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
		return vo;
	}

	// 전체 검색
	@Override
	public ArrayList<MemberVO> select() { // DB에서 전체 데이터(list)를 선택
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT);
			
			rs = pstmt.executeQuery(); // 물음표가 없으므로 추가할 데이터는 없다.
			
			while(rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int depId = rs.getInt(COL_DEP);
				int grade = rs.getInt(COL_GRD);
				int comp = rs.getInt(COL_COMP);
				
				int math = rs.getInt(COL_CHAR_MATH);
				int service = rs.getInt(COL_CHAR_SERV);
				int hand = rs.getInt(COL_CHAR_HAND);
				int iq = rs.getInt(COL_CHAR_IQ);
				int science = rs.getInt(COL_CHAR_SCIEN);
				
				MemberVO vo = new MemberVO(cid, name, depId, grade, comp, math,
						service, hand, iq, science); // 한줄 꺼내서
				list.add(vo); // vo로 출력 >>> 을 반복시킨다.
			}
			
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
		return list;

	}

	// 수정
	@Override
	public int update(int index, MemberVO vo) {//  특정 인덱스의 데이터를 DB에서 변경
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB연결 성공");
			pstmt = conn.prepareStatement(SQL_MEMBER_UPDATE);
			
			pstmt.setInt(1, vo.getPs_comp());
			pstmt.setInt(2, vo.getPs_id());
			
			result = pstmt.executeUpdate();
			System.out.println(result + "행의 데이터가 변경됨.");
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

	// 삭제
	@Override
	public int delete(int index) { // 특정 인덱스 데이터를 DB에서 삭제
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB연결 성공");
			pstmt = conn.prepareStatement(SQL_DELETE); // 물음표가 하나기 때문에 
			
			pstmt.setInt(1, index); // 인덱스에만 입력시킨다.
			
			result = pstmt.executeUpdate();
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
	
	// 멤버 수 반환 메소드
	@Override
	public int getSize() {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			pstmt = conn.prepareStatement(SQL_SELECT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count++;
			}
			System.out.println(" sys.MemberDAOImple.getSIze() count : " + count);
			
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
		
		return count;
	}

	
	// 매개변수를 3개받는 메소드
	public ArrayList<MemberVO> showAll(int compa, int dep, int grd) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_ALL);
			
			pstmt.setInt(1, compa);
			pstmt.setInt(2, dep);
			pstmt.setInt(3, grd);
			
			rs = pstmt.executeQuery(); // 물음표가 없으므로 추가할 데이터는 없다.
			
			while(rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int depId = rs.getInt(COL_DEP);
				int grade = rs.getInt(COL_GRD);
				int comp = rs.getInt(COL_COMP);
				
				int math = rs.getInt(COL_CHAR_MATH);
				int service = rs.getInt(COL_CHAR_SERV);
				int hand = rs.getInt(COL_CHAR_HAND);
				int iq = rs.getInt(COL_CHAR_IQ);
				int science = rs.getInt(COL_CHAR_SCIEN);

				MemberVO vo = new MemberVO(cid, name, depId, grade, comp, math,
						service, hand, iq, science);
				list.add(vo); // vo로 출력 >>> 을 반복시킨다.
			}
			
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
		return list;

	}

	// 2가지 //
	// 회사명, 부서를 받는 조건
	@Override 
	public ArrayList<MemberVO> showCompDep(int compa, int dep) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_CD);
			
			pstmt.setInt(1, compa);
			pstmt.setInt(2, dep);
			
			rs = pstmt.executeQuery(); // 물음표가 없으므로 추가할 데이터는 없다.
			
			while(rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int depId = rs.getInt(COL_DEP);
				int grade = rs.getInt(COL_GRD);
				int comp = rs.getInt(COL_COMP);
				
				int math = rs.getInt(COL_CHAR_MATH);
				int service = rs.getInt(COL_CHAR_SERV);
				int hand = rs.getInt(COL_CHAR_HAND);
				int iq = rs.getInt(COL_CHAR_IQ);
				int science = rs.getInt(COL_CHAR_SCIEN);
				
				MemberVO vo = new MemberVO(cid, name, depId, grade, comp, math,
						service, hand, iq, science);
				list.add(vo); // vo로 출력 >>> 을 반복시킨다.
			}
			
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
		return list;

	}
	
	// 회사명과 직급을 받는 조건
	@Override
	public ArrayList<MemberVO> showCompGrd(int compa, int grd) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_CG);
			
			pstmt.setInt(1, compa);
			pstmt.setInt(2, grd);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int depId = rs.getInt(COL_DEP);
				int grade = rs.getInt(COL_GRD);
				int comp = rs.getInt(COL_COMP);
				
				int math = rs.getInt(COL_CHAR_MATH);
				int service = rs.getInt(COL_CHAR_SERV);
				int hand = rs.getInt(COL_CHAR_HAND);
				int iq = rs.getInt(COL_CHAR_IQ);
				int science = rs.getInt(COL_CHAR_SCIEN);
				
				MemberVO vo = new MemberVO(cid, name, depId, grade, comp, math,
						service, hand, iq, science);
				list.add(vo); // vo로 출력 >>> 을 반복시킨다.
			}
			
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
		return list;
	}

	// 부서와 직급을 받는조건
	@Override
	public ArrayList<MemberVO> showDepGrd(int dep, int grd) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_GD);
			
			pstmt.setInt(1, dep);
			pstmt.setInt(2, grd);
			
			rs = pstmt.executeQuery(); // 물음표가 없으므로 추가할 데이터는 없다.
			
			while(rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int depId = rs.getInt(COL_DEP);
				int grade = rs.getInt(COL_GRD);
				int comp = rs.getInt(COL_COMP);
				
				int math = rs.getInt(COL_CHAR_MATH);
				int service = rs.getInt(COL_CHAR_SERV);
				int hand = rs.getInt(COL_CHAR_HAND);
				int iq = rs.getInt(COL_CHAR_IQ);
				int science = rs.getInt(COL_CHAR_SCIEN);
				
				
				MemberVO vo = new MemberVO(cid, name, depId, grade, comp, math,
						service, hand, iq, science);
				list.add(vo); // vo로 출력 >>> 을 반복시킨다.
			}
			
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
		return list;
	}

	// 회사명
	@Override
	public ArrayList<MemberVO> showComp(int compa) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_COMPANY);
			
			pstmt.setInt(1, compa);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int depId = rs.getInt(COL_DEP);
				int grade = rs.getInt(COL_GRD);
				int comp = rs.getInt(COL_COMP);
				
				int math = rs.getInt(COL_CHAR_MATH);
				int service = rs.getInt(COL_CHAR_SERV);
				int hand = rs.getInt(COL_CHAR_HAND);
				int iq = rs.getInt(COL_CHAR_IQ);
				int science = rs.getInt(COL_CHAR_SCIEN);
				
				MemberVO vo = new MemberVO(cid, name, depId, grade, comp, math,
						service, hand, iq, science); // 한줄 꺼내서
				list.add(vo); // vo로 출력 >>> 을 반복시킨다.
			}
			
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
		return list;
	}

	// 부서명
	@Override
	public ArrayList<MemberVO> showDep(int dep) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_DEPARTMENT);
			
			pstmt.setInt(1, dep);
			
			rs = pstmt.executeQuery(); // 물음표가 없으므로 추가할 데이터는 없다.
			
			while(rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int depId = rs.getInt(COL_DEP);
				int grade = rs.getInt(COL_GRD);
				int comp = rs.getInt(COL_COMP);
				
				int math = rs.getInt(COL_CHAR_MATH);
				int service = rs.getInt(COL_CHAR_SERV);
				int hand = rs.getInt(COL_CHAR_HAND);
				int iq = rs.getInt(COL_CHAR_IQ);
				int science = rs.getInt(COL_CHAR_SCIEN);
				
				MemberVO vo = new MemberVO(cid, name, depId, grade, comp, math,
						service, hand, iq, science);
				list.add(vo); // vo로 출력 >>> 을 반복시킨다.
			}
			
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
		return list;
	}

	// 직급명
	@Override  
	public ArrayList<MemberVO> showGrd(int grd) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_GRADE);
			
			pstmt.setInt(1, grd);
			
			rs = pstmt.executeQuery(); // 물음표가 없으므로 추가할 데이터는 없다.
			
			while(rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int depId = rs.getInt(COL_DEP);
				int grade = rs.getInt(COL_GRD);
				int comp = rs.getInt(COL_COMP);
				
				int math = rs.getInt(COL_CHAR_MATH);
				int service = rs.getInt(COL_CHAR_SERV);
				int hand = rs.getInt(COL_CHAR_HAND);
				int iq = rs.getInt(COL_CHAR_IQ);
				int science = rs.getInt(COL_CHAR_SCIEN);
						
				
				MemberVO vo = new MemberVO(cid, name, depId, grade, comp, math,
						service, hand, iq, science);
				list.add(vo); // vo로 출력 >>> 을 반복시킨다.
			}
			
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
		return list;
	}

	// ***********************************************************
	// 이름(문자열)로 검색
	@Override
	public ArrayList<MemberVO> showString(String str) {
		ArrayList<MemberVO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_SELECT_STRING_NAME);
			
			pstmt.setString(1, str);
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				int cid = rs.getInt(COL_CID);
				String name = rs.getString(COL_NAME);
				int depId = rs.getInt(COL_DEP);
				int grade = rs.getInt(COL_GRD);
				int comp = rs.getInt(COL_COMP);
				
				int math = rs.getInt(COL_CHAR_MATH);
				int service = rs.getInt(COL_CHAR_SERV);
				int hand = rs.getInt(COL_CHAR_HAND);
				int iq = rs.getInt(COL_CHAR_IQ);
				int science = rs.getInt(COL_CHAR_SCIEN);
				
				MemberVO vo = new MemberVO(cid, name, depId, grade, comp, math,
						service, hand, iq, science);
				list.add(vo);
			}
			
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
		return list;
	}

	// **********************************************************************************
	// cid값만 저장하는 메소드
	@Override
	public int input(int cid) {
		int result1 = 0; 
		
		Connection conn = null;
		PreparedStatement pstmt = null; // 상세정보 입력용
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DB에 연결
			System.out.println("DB에 연결 성공!");
			
			pstmt = conn.prepareStatement(SQL_MEMBER_INPUTCID); // SQL을 받아옴
			pstmt.setInt(1, cid); // ? 4개를 vo에서 입력받기
			
			
			result1 = pstmt.executeUpdate(); // result 결과값 받아오기
			
			System.out.println(result1 + "행의 데이터가 추가됨");
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
		return result1;
	}

	// cid값을 빼오는 메소드
	@Override
	public int output() {
		int result = 0 ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_MEMBER_OUTPUTCID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(COL_FOR_CID);
			}
			
			
			System.out.println("cid : " + result);
		} catch (Exception e) {
			// TODO: handle exception
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
	
	// cid값을 지우는 곳
	@Override
	public int deleteCid() {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_MEMBER_DELETECID);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return result;
	}

	// 직급만 받아와서 수정하는 메소드
	@Override
	public int updateGrd(int index, MemberVO vo) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB연결 성공");
			pstmt = conn.prepareStatement(SQL_MEMBER_GRD_UPDATE);
			
			pstmt.setInt(1, vo.getPs_grd());
			pstmt.setInt(2, vo.getPs_id());
			
			result = pstmt.executeUpdate();
			System.out.println(result + "행의 데이터가 변경됨.");
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
	
	// *****************************************************************
	
	
}
