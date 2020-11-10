package proj.etc;


public interface Constants {
	public static final String GAMEVERSION = 
			"0.9";
	
	public static final String URL = 
			"jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";

	// 테이블 명
	public static final String TABLE_NAME_ALL = "emplo_all1";              // 테이블 명 -> 모든 직원들
	public static final String TABLE_NAME_CHAR = "emplo_char";             // 테이블 명 -> 능력치 테이블
	
	public static final String TABLE_NAME_LOGIN = "emplo_account";         // 테이블 명 -> 로그인 테이블
	public static final String TABLE_NAME_ID = "emplo_id";                 // 테이블 명 -> 아이디 저장용 테이블
	public static final String TABLE_NAME_FINANCE = "emplo_finance";       // 테이블 명 -> 잔고 상황 변동 저장용
	public static final String TABLE_NAME_FORCID = "emplo_forcid";         // 테이블 명 -> Member cid저장용 
	
	// 각 변수들
	// ALL 테이블
	public static final String COL_CID = "ps_id";             // 사번
	public static final String COL_NAME = "ps_name";          // 이름
	public static final String COL_DEP = "ps_dep";            // 부서번호 > 부서 테이블과 연결
	public static final String COL_GRD = "ps_grd";            // 직급 > 직급 테이블과 연결
	public static final String COL_COMP = "ps_comp";          // 회사 > 회사명 테이블과 연결 
	
	public static final String COL_CHAR_MATH = "char_math";     // 산술 능력치 
	public static final String COL_CHAR_SERV = "char_serv";     // 서비스 능력치
	public static final String COL_CHAR_HAND = "char_hand";     // 손재주 능력치
	public static final String COL_CHAR_IQ = "char_iq";         // 지능 능력치
	public static final String COL_CHAR_SCIEN = "char_scien";   // 과학 능력치
	
	// TODO : 전체 구상 후 창의성 , 계획성 추가하기
	
	// DEPARTMENT 테이블
	public static final String COL_DEP_NAME = "dep_name";     // 부서 이름
	
	// GRADE 테이블
	public static final String COL_GRADE_NAME = "grade_name"; // 직급 이름
	
	// COMPANY 테이블
	public static final String COL_COMP_NAME = "comp_name";   // 회사 이름
	
	
	// LOGIN 테이블
	public static final String COL_LOG_ID = "id";             // 아이디
	public static final String COL_LOG_PW = "password";       // 비밀번호
	public static final String COL_LOG_CID = "cid";           // 시드값
	
	// ID 테이블 (LOGIN하고 들어온 ID를 저장하여 MAIN으로 반환하는 테이블)
	public static final String COL_ID_CID = "cid";			  // 시드값
	public static final String COL_ID_ID = "id";              // 아이디
	
	// FINANCE 테이블
	public static final String COL_FIN_CID = "cid";           // 시드값
	public static final String COL_FIN_CURMON = "curmoney";   // 현재 잔고
	public static final String COL_FIN_WAGE = "wage";  		  // 급여
	public static final String COL_FIN_NOTWAGE = "notwage";   // 급여 변동량 저장
	public static final String COL_FIN_CONFIDENCE = "exmoney";   // 사기
	public static final String COL_FIN_INCOME = "income";     // 수입
	public static final String COL_FIN_DATE = "fin_date";     // 날짜
	
	// FORCID 테이블
	public static final String COL_FOR_CID = "cid";           // 시드 /* 숫자값만 저장 하여 넘겨주면 된다.
	//==========================================================================
	// 기타 상수

	//==========================================================================
	
	// MEMBER SQL-----------------------------------------------------------------
	// 등록 SQL 
	public static final String SQL_INSERT =
			"insert into " + TABLE_NAME_ALL + "(" 
			+ COL_CID + ", " + COL_NAME + ", " + COL_DEP + ", " 
			+ COL_GRD + ", " + COL_COMP + ", " + COL_CHAR_MATH + ", " + COL_CHAR_SERV + ", " + COL_CHAR_HAND + ", " 
			+ COL_CHAR_IQ + ", " + COL_CHAR_SCIEN +  ") "
			+ "values (contact_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 

	
	// 상세검색 SQL > 인덱스로 검색
	public static final String SQL_SELECT_BY_CID =
			"select * from " + TABLE_NAME_ALL + " where " + COL_CID + " = ?"; // where cid의 하나를 보여준다.

	// 전체검색 SQL
	public static final String SQL_SELECT =
			"select * from " + TABLE_NAME_ALL + " order by " + COL_CID; // cid를 하나씩 까며 전체를 보여준다.

	// 수정 SQL
	public static final String SQL_UPDATE = 
			"update " + TABLE_NAME_ALL + " set " 
			+ COL_NAME +  " = ?, " + COL_DEP + " = ?, " + COL_GRD + " = ?, "
			+ COL_COMP + " = ?, " + COL_CHAR_MATH + " = ?," + COL_CHAR_SERV + " = ?, " 
			+ COL_CHAR_HAND + " = ?," + COL_CHAR_IQ + " = ?," 
			+ COL_CHAR_SCIEN + " = ?"
			+ " where " + COL_CID + " = ?";
	
	// 수정 SQL
	public static final String SQL_MEMBER_UPDATE =
			"update " + TABLE_NAME_ALL + " set "
			+ COL_COMP + " = ? " + " where " + COL_CID + " = ?";

	// 삭제 SQL
	public static final String SQL_DELETE =
			"delete from " + TABLE_NAME_ALL + " where " + COL_CID + " = ?";
	
	// 수정 SQL > 직급관리
	public static final String SQL_MEMBER_GRD_UPDATE = 
			"update " + TABLE_NAME_ALL + " set "
			+ COL_GRD + " = ? " + " where " + COL_CID + " = ?";
	// 상세 검색 // COMBOBOX를 이용한 검색 SQL ---------------------------------------------------------------
	
	// 상세검색 SQL > 세부 사항으로 검색 (모두 입력)
	public static final String SQL_SELECT_SORT_ALL = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_COMP + " = ? and " 
					+ COL_DEP + " = ? and " + COL_GRD + " = ?";
	
	// 상세검색 SQL > 세부 사항 (회사명 , 부서)
	public static final String SQL_SELECT_SORT_CD = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_COMP + " = ? and " 
					+ COL_DEP + " = ?";
	
	// 상세검색 SQL > 세부 사항 (회사명, 직급)
	public static final String SQL_SELECT_SORT_CG = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_COMP + " = ? and " 
					+ COL_GRD + " = ?";
	
	// 상세검색 SQL > 세부 사항(직급, 부서)
	public static final String SQL_SELECT_SORT_GD = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_DEP + " = ? and " 
					+ COL_GRD + " = ?";
	//*******************************************************
	// 상세검색 SQL > 세부 사항(소속)
	public static final String SQL_SELECT_SORT_COMPANY = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_COMP + " = ?";
	
	// 상세검색 SQL > 세부 사항(직급)
	public static final String SQL_SELECT_SORT_GRADE = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_GRD + " = ?";
	
	// 상세검색 SQL > 세부 사항(부서)
	public static final String SQL_SELECT_SORT_DEPARTMENT =
			"select * from " + TABLE_NAME_ALL + " where " + COL_DEP + " = ?"; 
	
	//********************************************************
	
	// 상세검색 SQL > 이름(문자열)으로 검색
	
	public static final String SQL_SELECT_STRING_NAME = 
			"select * from " + TABLE_NAME_ALL + " where " + COL_NAME + " like ?";

	// CONTACT SQL-------------------------------------------------------------------------
	// 등록 SQL
	public static final String SQL_ID_INSERT =
			"insert into " + TABLE_NAME_LOGIN + "(" + COL_LOG_CID + ", "
			+ COL_LOG_ID + ", " + COL_LOG_PW + ") "
			+ "values (member_seq.nextval, ?, ?)";
	
	// 삭제 SQL
	public static final String SQL_ID_DELETE =
			"delete from " + TABLE_NAME_LOGIN + " where " + COL_LOG_ID + " = ?";
	
	// 전체검색 SQL
	public static final String SQL_ID_SELECT =
			"select * from " + TABLE_NAME_LOGIN + " order by " + COL_LOG_ID;
	
	// 상세검색 SQL
	public static final String SQL_ID_SELECT_BY_CID =
			"select * from " + TABLE_NAME_LOGIN + " where " + COL_LOG_ID + " = ?";

	// 로그인 SQL -------------------------------------------------------------------------
	// 아이디 저장 SQL
	public static final String SQL_LOGIN_INSERT = 
			"insert into " + TABLE_NAME_ID + "(" + COL_ID_CID + ", "
			+ COL_ID_ID + ") "
			+ "values (1 , ?)";
	
	public static final String SQL_LOGIN_SELECT =
			"select * from " + TABLE_NAME_ID + " where " + COL_ID_CID + " = 1";
	
	// Finance SQL -----------------------------------------------------------------------
	// insert SQL // 6번째가 cid
	public static final String SQL_FINANCE_INSERT =
			"insert into " + TABLE_NAME_FINANCE + "(" 
			+ COL_FIN_CURMON + ", "	+ COL_FIN_WAGE + ", " 
			+ COL_FIN_NOTWAGE + ", " + COL_FIN_CONFIDENCE + ", " 
			+ COL_FIN_INCOME + ", " + COL_FIN_CID + ", " + COL_FIN_DATE + ") "
			+ "values (?, ?, ?, ?, ?, FIN_SEQ.nextval, ?)";
	
	// select SQL // 마지막 줄만
	// select * from(select * from emplo_finance ORDER BY ROWNUM DESC) WHERE ROWNUM = 1;
	public static final String SQL_FINANCE_SELECT_LAST =
			"select * from (select * from " + TABLE_NAME_FINANCE + " order by rownum desc) where rownum = 1";
	
	// selectAll SQL // 전체
	public static final String SQL_FINANCE_SELECT_ALL =
			"select * from " + TABLE_NAME_FINANCE + " order by rownum";
	
	// delete SQL // 2번째줄 - 마지막까지 지운다. > 첫번째줄은 초기값
	public static final String SQL_FINANCE_DELETE =
			"delete from " + TABLE_NAME_FINANCE + " order by " + COL_LOG_ID + " = ?";

	
	
	// Member SQL -----------------------------------------------------------------------
	// select SQL  // DB내 데이터와 비교용
	// select * from emplo_all1	where ps_id = 37;
	public static final String SQL_MEMBER_CLICKED =
			"select * from " + TABLE_NAME_ALL + " where " + COL_CID + " = ?";
	
	// inputCid // cid값만 저장하기 위함
	public static final String SQL_MEMBER_INPUTCID = 
			"insert into " + TABLE_NAME_FORCID + " values (?)";
	
	// outputCid // cid값을 빼오기 위함
	public static final String SQL_MEMBER_OUTPUTCID =  // 마지막 줄만
			"select * from(select * from " + TABLE_NAME_FORCID + " order by rownum desc) where rownum = 1";
	
	// deleteCid // cid값을 지우기 위함
	public static final String SQL_MEMBER_DELETECID =
			"delete from " + TABLE_NAME_FORCID;
	
	
	
	
}
