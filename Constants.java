package proj.etc;


public interface Constants {
	public static final String GAMEVERSION = 
			"0.9";
	
	public static final String URL = 
			"jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";

	// ���̺� ��
	public static final String TABLE_NAME_ALL = "emplo_all1";              // ���̺� �� -> ��� ������
	public static final String TABLE_NAME_CHAR = "emplo_char";             // ���̺� �� -> �ɷ�ġ ���̺�
	
	public static final String TABLE_NAME_LOGIN = "emplo_account";         // ���̺� �� -> �α��� ���̺�
	public static final String TABLE_NAME_ID = "emplo_id";                 // ���̺� �� -> ���̵� ����� ���̺�
	public static final String TABLE_NAME_FINANCE = "emplo_finance";       // ���̺� �� -> �ܰ� ��Ȳ ���� �����
	public static final String TABLE_NAME_FORCID = "emplo_forcid";         // ���̺� �� -> Member cid����� 
	
	// �� ������
	// ALL ���̺�
	public static final String COL_CID = "ps_id";             // ���
	public static final String COL_NAME = "ps_name";          // �̸�
	public static final String COL_DEP = "ps_dep";            // �μ���ȣ > �μ� ���̺�� ����
	public static final String COL_GRD = "ps_grd";            // ���� > ���� ���̺�� ����
	public static final String COL_COMP = "ps_comp";          // ȸ�� > ȸ��� ���̺�� ���� 
	
	public static final String COL_CHAR_MATH = "char_math";     // ��� �ɷ�ġ 
	public static final String COL_CHAR_SERV = "char_serv";     // ���� �ɷ�ġ
	public static final String COL_CHAR_HAND = "char_hand";     // ������ �ɷ�ġ
	public static final String COL_CHAR_IQ = "char_iq";         // ���� �ɷ�ġ
	public static final String COL_CHAR_SCIEN = "char_scien";   // ���� �ɷ�ġ
	
	// TODO : ��ü ���� �� â�Ǽ� , ��ȹ�� �߰��ϱ�
	
	// DEPARTMENT ���̺�
	public static final String COL_DEP_NAME = "dep_name";     // �μ� �̸�
	
	// GRADE ���̺�
	public static final String COL_GRADE_NAME = "grade_name"; // ���� �̸�
	
	// COMPANY ���̺�
	public static final String COL_COMP_NAME = "comp_name";   // ȸ�� �̸�
	
	
	// LOGIN ���̺�
	public static final String COL_LOG_ID = "id";             // ���̵�
	public static final String COL_LOG_PW = "password";       // ��й�ȣ
	public static final String COL_LOG_CID = "cid";           // �õ尪
	
	// ID ���̺� (LOGIN�ϰ� ���� ID�� �����Ͽ� MAIN���� ��ȯ�ϴ� ���̺�)
	public static final String COL_ID_CID = "cid";			  // �õ尪
	public static final String COL_ID_ID = "id";              // ���̵�
	
	// FINANCE ���̺�
	public static final String COL_FIN_CID = "cid";           // �õ尪
	public static final String COL_FIN_CURMON = "curmoney";   // ���� �ܰ�
	public static final String COL_FIN_WAGE = "wage";  		  // �޿�
	public static final String COL_FIN_NOTWAGE = "notwage";   // �޿� ������ ����
	public static final String COL_FIN_CONFIDENCE = "exmoney";   // ���
	public static final String COL_FIN_INCOME = "income";     // ����
	public static final String COL_FIN_DATE = "fin_date";     // ��¥
	
	// FORCID ���̺�
	public static final String COL_FOR_CID = "cid";           // �õ� /* ���ڰ��� ���� �Ͽ� �Ѱ��ָ� �ȴ�.
	//==========================================================================
	// ��Ÿ ���

	//==========================================================================
	
	// MEMBER SQL-----------------------------------------------------------------
	// ��� SQL 
	public static final String SQL_INSERT =
			"insert into " + TABLE_NAME_ALL + "(" 
			+ COL_CID + ", " + COL_NAME + ", " + COL_DEP + ", " 
			+ COL_GRD + ", " + COL_COMP + ", " + COL_CHAR_MATH + ", " + COL_CHAR_SERV + ", " + COL_CHAR_HAND + ", " 
			+ COL_CHAR_IQ + ", " + COL_CHAR_SCIEN +  ") "
			+ "values (contact_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 

	
	// �󼼰˻� SQL > �ε����� �˻�
	public static final String SQL_SELECT_BY_CID =
			"select * from " + TABLE_NAME_ALL + " where " + COL_CID + " = ?"; // where cid�� �ϳ��� �����ش�.

	// ��ü�˻� SQL
	public static final String SQL_SELECT =
			"select * from " + TABLE_NAME_ALL + " order by " + COL_CID; // cid�� �ϳ��� ��� ��ü�� �����ش�.

	// ���� SQL
	public static final String SQL_UPDATE = 
			"update " + TABLE_NAME_ALL + " set " 
			+ COL_NAME +  " = ?, " + COL_DEP + " = ?, " + COL_GRD + " = ?, "
			+ COL_COMP + " = ?, " + COL_CHAR_MATH + " = ?," + COL_CHAR_SERV + " = ?, " 
			+ COL_CHAR_HAND + " = ?," + COL_CHAR_IQ + " = ?," 
			+ COL_CHAR_SCIEN + " = ?"
			+ " where " + COL_CID + " = ?";
	
	// ���� SQL
	public static final String SQL_MEMBER_UPDATE =
			"update " + TABLE_NAME_ALL + " set "
			+ COL_COMP + " = ? " + " where " + COL_CID + " = ?";

	// ���� SQL
	public static final String SQL_DELETE =
			"delete from " + TABLE_NAME_ALL + " where " + COL_CID + " = ?";
	
	// ���� SQL > ���ް���
	public static final String SQL_MEMBER_GRD_UPDATE = 
			"update " + TABLE_NAME_ALL + " set "
			+ COL_GRD + " = ? " + " where " + COL_CID + " = ?";
	// �� �˻� // COMBOBOX�� �̿��� �˻� SQL ---------------------------------------------------------------
	
	// �󼼰˻� SQL > ���� �������� �˻� (��� �Է�)
	public static final String SQL_SELECT_SORT_ALL = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_COMP + " = ? and " 
					+ COL_DEP + " = ? and " + COL_GRD + " = ?";
	
	// �󼼰˻� SQL > ���� ���� (ȸ��� , �μ�)
	public static final String SQL_SELECT_SORT_CD = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_COMP + " = ? and " 
					+ COL_DEP + " = ?";
	
	// �󼼰˻� SQL > ���� ���� (ȸ���, ����)
	public static final String SQL_SELECT_SORT_CG = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_COMP + " = ? and " 
					+ COL_GRD + " = ?";
	
	// �󼼰˻� SQL > ���� ����(����, �μ�)
	public static final String SQL_SELECT_SORT_GD = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_DEP + " = ? and " 
					+ COL_GRD + " = ?";
	//*******************************************************
	// �󼼰˻� SQL > ���� ����(�Ҽ�)
	public static final String SQL_SELECT_SORT_COMPANY = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_COMP + " = ?";
	
	// �󼼰˻� SQL > ���� ����(����)
	public static final String SQL_SELECT_SORT_GRADE = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_GRD + " = ?";
	
	// �󼼰˻� SQL > ���� ����(�μ�)
	public static final String SQL_SELECT_SORT_DEPARTMENT =
			"select * from " + TABLE_NAME_ALL + " where " + COL_DEP + " = ?"; 
	
	//********************************************************
	
	// �󼼰˻� SQL > �̸�(���ڿ�)���� �˻�
	
	public static final String SQL_SELECT_STRING_NAME = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_NAME + " like ?";

	// CONTACT SQL-------------------------------------------------------------------------
	// ��� SQL
	public static final String SQL_ID_INSERT =
			"insert into " + TABLE_NAME_LOGIN + "(" + COL_LOG_CID + ", "
			+ COL_LOG_ID + ", " + COL_LOG_PW + ") "
			+ "values (member_seq.nextval, ?, ?)";
	
	// ���� SQL
	public static final String SQL_ID_DELETE =
			"delete from " + TABLE_NAME_LOGIN + " where " + COL_LOG_ID + " = ?";
	
	// ��ü�˻� SQL
	public static final String SQL_ID_SELECT =
			"select * from " + TABLE_NAME_LOGIN + " order by " + COL_LOG_ID;
	
	// �󼼰˻� SQL
	public static final String SQL_ID_SELECT_BY_CID =
			"select * from " + TABLE_NAME_LOGIN + " where " + COL_LOG_ID + " = ?";

	// �α��� SQL -------------------------------------------------------------------------
	// ���̵� ���� SQL
	public static final String SQL_LOGIN_INSERT = 
			"insert into " + TABLE_NAME_ID + "(" + COL_ID_CID + ", "
			+ COL_ID_ID + ") "
			+ "values (1 , ?)";
	
	public static final String SQL_LOGIN_SELECT =
			"select * from " + TABLE_NAME_ID + " where " + COL_ID_CID + " = 1";
	
	// Finance SQL -----------------------------------------------------------------------
	// insert SQL // 6��°�� cid
	public static final String SQL_FINANCE_INSERT =
			"insert into " + TABLE_NAME_FINANCE + "(" 
			+ COL_FIN_CURMON + ", "	+ COL_FIN_WAGE + ", " 
			+ COL_FIN_NOTWAGE + ", " + COL_FIN_CONFIDENCE + ", " 
			+ COL_FIN_INCOME + ", " + COL_FIN_CID + ", " + COL_FIN_DATE + ") "
			+ "values (?, ?, ?, ?, ?, FIN_SEQ.nextval, ?)";
	
	// select SQL // ������ �ٸ�
	// select * from(select * from emplo_finance ORDER BY ROWNUM DESC) WHERE ROWNUM = 1;
	public static final String SQL_FINANCE_SELECT_LAST =
			"select * from (select * from " + TABLE_NAME_FINANCE + " order by rownum desc) where rownum = 1";
	
	// selectAll SQL // ��ü
	public static final String SQL_FINANCE_SELECT_ALL =
			"select * from " + TABLE_NAME_FINANCE + " order by rownum";
	
	// delete SQL // 2��°�� - ���������� �����. > ù��°���� �ʱⰪ
	public static final String SQL_FINANCE_DELETE =
			"delete from " + TABLE_NAME_FINANCE + " order by " + COL_LOG_ID + " = ?";

	
	
	// Member SQL -----------------------------------------------------------------------
	// select SQL  // DB�� �����Ϳ� �񱳿�
	// select * from emplo_all1	where ps_id = 37;
	public static final String SQL_MEMBER_CLICKED =
			"select * from " + TABLE_NAME_ALL + " where " + COL_CID + " = ?";
	
	// inputCid // cid���� �����ϱ� ����
	public static final String SQL_MEMBER_INPUTCID = 
			"insert into " + TABLE_NAME_FORCID + " values (?)";
	
	// outputCid // cid���� ������ ����
	public static final String SQL_MEMBER_OUTPUTCID =  // ������ �ٸ�
			"select * from(select * from " + TABLE_NAME_FORCID + " order by rownum desc) where rownum = 1";
	
	// deleteCid // cid���� ����� ����
	public static final String SQL_MEMBER_DELETECID =
			"delete from " + TABLE_NAME_FORCID;
	
	
	
	
}
