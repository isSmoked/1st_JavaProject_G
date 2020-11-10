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
	
	// 1. private static �ڱ� �ڽ� Ÿ���� ��� ���� ����
	private static MemberDAOImple instance = null;
	
	// 2. private ������
	private MemberDAOImple() {
		// DB ����̹� ���
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 3. public static �޼ҵ� - �ν��Ͻ��� �����ϴ� �޼ҵ� ����
	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAOImple();
		}
		return instance;
	}
	// ---------------------------------------------------------------
	
	// �ű� ���
	@Override
	public int insert(MemberVO vo) { // vo�� DB�� ����
		int result1 = 0; 
		
		Connection conn = null;
		PreparedStatement pstmt = null; // ������ �Է¿�
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DB�� ����
			System.out.println("DB�� ���� ����!");
			
			pstmt = conn.prepareStatement(SQL_INSERT); // SQL�� �޾ƿ�
			pstmt.setString(1, vo.getPs_name()); // ? 4���� vo���� �Է¹ޱ�
			pstmt.setInt(2, vo.getPs_dep());
			pstmt.setInt(3, vo.getPs_grd());
			pstmt.setInt(4, vo.getPs_comp());
			pstmt.setInt(5, vo.getChar_math());
			pstmt.setInt(6, vo.getChar_serv());
			pstmt.setInt(7, vo.getChar_hand());
			pstmt.setInt(8, vo.getChar_iq());
			pstmt.setInt(9, vo.getChar_scien());
			
			result1 = pstmt.executeUpdate(); // result ����� �޾ƿ���
			
			System.out.println(result1 + "���� �����Ͱ� �߰���");
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

	// �� �˻�
	@Override
	public MemberVO select(int index) { // DB���� �ε��� ������(vo)�� ����
		MemberVO vo = null; // null�� �ʱ�ȭ
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
			pstmt = conn.prepareStatement(SQL_SELECT_BY_CID); // ����ǥ 1��
			pstmt.setInt(1, index); // ù��° ����ǥ�� index�� ���� �ְڴ�.
			rs = pstmt.executeQuery();
			System.out.println(index + " index ");
			
			if (rs.next()) {
				System.out.println("ResultSet ����");
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

	// ��ü �˻�
	@Override
	public ArrayList<MemberVO> select() { // DB���� ��ü ������(list)�� ����
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
			pstmt = conn.prepareStatement(SQL_SELECT);
			
			rs = pstmt.executeQuery(); // ����ǥ�� �����Ƿ� �߰��� �����ʹ� ����.
			
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
						service, hand, iq, science); // ���� ������
				list.add(vo); // vo�� ��� >>> �� �ݺ���Ų��.
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

	// ����
	@Override
	public int update(int index, MemberVO vo) {//  Ư�� �ε����� �����͸� DB���� ����
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB���� ����");
			pstmt = conn.prepareStatement(SQL_MEMBER_UPDATE);
			
			pstmt.setInt(1, vo.getPs_comp());
			pstmt.setInt(2, vo.getPs_id());
			
			result = pstmt.executeUpdate();
			System.out.println(result + "���� �����Ͱ� �����.");
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

	// ����
	@Override
	public int delete(int index) { // Ư�� �ε��� �����͸� DB���� ����
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB���� ����");
			pstmt = conn.prepareStatement(SQL_DELETE); // ����ǥ�� �ϳ��� ������ 
			
			pstmt.setInt(1, index); // �ε������� �Է½�Ų��.
			
			result = pstmt.executeUpdate();
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
	
	// ��� �� ��ȯ �޼ҵ�
	@Override
	public int getSize() {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
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

	
	// �Ű������� 3���޴� �޼ҵ�
	public ArrayList<MemberVO> showAll(int compa, int dep, int grd) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_ALL);
			
			pstmt.setInt(1, compa);
			pstmt.setInt(2, dep);
			pstmt.setInt(3, grd);
			
			rs = pstmt.executeQuery(); // ����ǥ�� �����Ƿ� �߰��� �����ʹ� ����.
			
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
				list.add(vo); // vo�� ��� >>> �� �ݺ���Ų��.
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

	// 2���� //
	// ȸ���, �μ��� �޴� ����
	@Override 
	public ArrayList<MemberVO> showCompDep(int compa, int dep) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_CD);
			
			pstmt.setInt(1, compa);
			pstmt.setInt(2, dep);
			
			rs = pstmt.executeQuery(); // ����ǥ�� �����Ƿ� �߰��� �����ʹ� ����.
			
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
				list.add(vo); // vo�� ��� >>> �� �ݺ���Ų��.
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
	
	// ȸ���� ������ �޴� ����
	@Override
	public ArrayList<MemberVO> showCompGrd(int compa, int grd) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
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
				list.add(vo); // vo�� ��� >>> �� �ݺ���Ų��.
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

	// �μ��� ������ �޴�����
	@Override
	public ArrayList<MemberVO> showDepGrd(int dep, int grd) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_GD);
			
			pstmt.setInt(1, dep);
			pstmt.setInt(2, grd);
			
			rs = pstmt.executeQuery(); // ����ǥ�� �����Ƿ� �߰��� �����ʹ� ����.
			
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
				list.add(vo); // vo�� ��� >>> �� �ݺ���Ų��.
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

	// ȸ���
	@Override
	public ArrayList<MemberVO> showComp(int compa) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
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
						service, hand, iq, science); // ���� ������
				list.add(vo); // vo�� ��� >>> �� �ݺ���Ų��.
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

	// �μ���
	@Override
	public ArrayList<MemberVO> showDep(int dep) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_DEPARTMENT);
			
			pstmt.setInt(1, dep);
			
			rs = pstmt.executeQuery(); // ����ǥ�� �����Ƿ� �߰��� �����ʹ� ����.
			
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
				list.add(vo); // vo�� ��� >>> �� �ݺ���Ų��.
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

	// ���޸�
	@Override  
	public ArrayList<MemberVO> showGrd(int grd) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
			pstmt = conn.prepareStatement(SQL_SELECT_SORT_GRADE);
			
			pstmt.setInt(1, grd);
			
			rs = pstmt.executeQuery(); // ����ǥ�� �����Ƿ� �߰��� �����ʹ� ����.
			
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
				list.add(vo); // vo�� ��� >>> �� �ݺ���Ų��.
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
	// �̸�(���ڿ�)�� �˻�
	@Override
	public ArrayList<MemberVO> showString(String str) {
		ArrayList<MemberVO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
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
	// cid���� �����ϴ� �޼ҵ�
	@Override
	public int input(int cid) {
		int result1 = 0; 
		
		Connection conn = null;
		PreparedStatement pstmt = null; // ������ �Է¿�
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // DB�� ����
			System.out.println("DB�� ���� ����!");
			
			pstmt = conn.prepareStatement(SQL_MEMBER_INPUTCID); // SQL�� �޾ƿ�
			pstmt.setInt(1, cid); // ? 4���� vo���� �Է¹ޱ�
			
			
			result1 = pstmt.executeUpdate(); // result ����� �޾ƿ���
			
			System.out.println(result1 + "���� �����Ͱ� �߰���");
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

	// cid���� ������ �޼ҵ�
	@Override
	public int output() {
		int result = 0 ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB ���� ����");
			
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
	
	// cid���� ����� ��
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

	// ���޸� �޾ƿͼ� �����ϴ� �޼ҵ�
	@Override
	public int updateGrd(int index, MemberVO vo) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB���� ����");
			pstmt = conn.prepareStatement(SQL_MEMBER_GRD_UPDATE);
			
			pstmt.setInt(1, vo.getPs_grd());
			pstmt.setInt(2, vo.getPs_id());
			
			result = pstmt.executeUpdate();
			System.out.println(result + "���� �����Ͱ� �����.");
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
