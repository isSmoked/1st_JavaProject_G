package proj.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel; ////////////////////

import proj.member.mvc.MemberDAO;
import proj.member.mvc.MemberDAOImple;
import proj.member.mvc.MemberVO;

public class SearchGui extends JFrame implements MouseListener, Runnable{
	// SearchGui Singleton
	private static SearchGui instance = null;
	
	public static SearchGui getInstance(JPanel panel) {
		if (instance == null) {
			instance = new SearchGui(panel);
		}
		return instance;
	}
	
	private JPanel panel_1;
	public JPanel panel_1() {
		return panel_1;
	}
	
	private JPanel panel;
	private static MemberDAO mdao;
	
	private JLabel lblSearchMember; 
	private JComboBox cBoxComp, cBoxDepartment, cBoxGrade;
	private JTextArea textAreaSetSearch;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private String[] nameComp = new String[] {"소속", "내 회사", "NAVER", "GOOGLE", "은퇴" , "FA"};
	private String[] nameDep = new String[] {"부서", "인사", "총무", "영업", "마케팅", "생산관리", "연구개발"};
	private String[] nameGra = new String[] {"직급", "부장", "차장", "과장", "대리", "주임", "인턴"};
	private String compString, depString, graString;
	private int compInt = 0, depInt = 0, graInt = 0;
	private JRadioButton rdbtnSort, rdbtnName; 
	
	/* 스윙 테이블을 사용하기 위한 멤버 변수 선언 */
	private JTable table;
	private String[] colNames = {"사번", "이름", "부서", "직급", "소속"}; // 테이브 헤더에 들어갈 이름들
	private Object[] records = new Object[colNames.length]; // 테이블 데이터를 저장할 배열객체
	private DefaultTableModel tableModel; // 테이블 형태를 만들 모델 변수
	private JTextArea textAreaLog;

	private int cid;
	private int depNum = 0;
	private String depStr;
	private int grdNum = 0; 
	private String grdStr;
	private int compNum = 0; 
	private String compStr;
	private int count = 0;
	
	private String Memid, MemDepWant, MemName, MemGrdCur, MemCompCur, MemDepCur, MemCharMath, MemCharServ, MemCharHand,
	MemCharIq, MemCharScien;
	private int overall;
	private String strOverall;
	
	
	public SearchGui(JPanel panel) {
		this.panel = panel;
		
		mdao = MemberDAOImple.getInstance();
		
		setBounds(1100, 250, 300, 477);
		getContentPane().setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 284, 457); // 0, 0, 284, 618
		
		setContentPane(panel_1);
		getContentPane().setLayout(null);
		panel_1.setLayout(null);
		
		
		Font smallFont = new Font("굴림체", Font.BOLD, 18);
		
		lblSearchMember = new JLabel("고용 시장");
		lblSearchMember.setBounds(12, 10, 121, 33);
		lblSearchMember.setFont(smallFont);
		panel_1.add(lblSearchMember);
		
		// 체크박스 대신 라디오헤드
		rdbtnSort = new JRadioButton("속성으로 검색");
		rdbtnSort.setBounds(22, 53, 121, 23);
		panel_1.add(rdbtnSort);
		
		rdbtnName = new JRadioButton("이름으로 검색");
		rdbtnName.setBounds(22, 109, 121, 23);
		panel_1.add(rdbtnName);
		
		ButtonGroup Group = new ButtonGroup();
		Group.add(rdbtnName);
		Group.add(rdbtnSort);
		
		
		
		
		// 회사명 콤보박스
		cBoxComp = new JComboBox(); 
		cBoxComp.setModel(new DefaultComboBoxModel(nameComp));
		cBoxComp.setBounds(22, 82, 67, 21);
		cBoxComp.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
			
				// "소속", "내 회사", "NAVER", "GOOGLE", "은퇴" , "FA"
				if ((String) cBoxComp.getSelectedItem() == "소속") {
					compInt = 0;
				} else if ((String) cBoxComp.getSelectedItem() == "내 회사") {
					compInt = 1;
				} else if ((String) cBoxComp.getSelectedItem() == "NAVER") {
					compInt = 2;
				} else if ((String) cBoxComp.getSelectedItem() == "GOOGLE") {
					compInt = 3;
				} else if ((String) cBoxComp.getSelectedItem() == "은퇴") {
					compInt = 4;
				} else {	
					compInt = 5;
				}
			
			}
		});
	
		panel_1.add(cBoxComp);
		
		// 부서명 콤보박스
		cBoxDepartment = new JComboBox(); 
		cBoxDepartment.setModel(new DefaultComboBoxModel(nameDep));
		cBoxDepartment.setBounds(108, 82, 67, 21);
		cBoxDepartment.addItemListener(new ItemListener() {
			
			// "부서", "인사", "총무", "영업", "마케팅", "생산관리", "연구개발"
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((String) cBoxDepartment.getSelectedItem() == "부서") {
					depInt = 0;
				} else if ((String) cBoxDepartment.getSelectedItem() == "인사") {
					depInt = 1;
				} else if ((String) cBoxDepartment.getSelectedItem() == "총무") {
					depInt = 2;
				} else if ((String) cBoxDepartment.getSelectedItem() == "영업") {
					depInt = 3;
				} else if ((String) cBoxDepartment.getSelectedItem() == "마켓") {
					depInt = 4;
				} else if ((String) cBoxDepartment.getSelectedItem() == "생산"){	
					depInt = 5;
				} else if ((String) cBoxDepartment.getSelectedItem() == "연구"){	
					depInt = 6;
				}
			}
		});
		
		panel_1.add(cBoxDepartment);
		
		// 직급명 콤보박스
		cBoxGrade = new JComboBox();
		cBoxGrade.setModel(new DefaultComboBoxModel(nameGra));
		cBoxGrade.setBounds(187, 82, 67, 21);
		cBoxGrade.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// "직급", "부장", "차장", "과장", "대리", "주임", "인턴"
				if ((String) cBoxGrade.getSelectedItem() == "직급") {
					graInt = 0;
				} else if ((String) cBoxGrade.getSelectedItem() == "부장") {
					graInt = 1;
				} else if ((String) cBoxGrade.getSelectedItem() == "차장") {
					graInt = 2;
				} else if ((String) cBoxGrade.getSelectedItem() == "과장") {
					graInt = 3;
				} else if ((String) cBoxGrade.getSelectedItem() == "대리"){	
					graInt = 4;
				} else if ((String) cBoxGrade.getSelectedItem() == "주임"){	
					graInt = 5;
				} else {
					graInt = 6;
				}
			}
		});
		
		panel_1.add(cBoxGrade);
		
		
		// 검색창
		textAreaSetSearch = new JTextArea();
		textAreaSetSearch.setBounds(22, 138, 232, 23);
		panel_1.add(textAreaSetSearch);
		
		// 검색버튼 
		btnSearch = new JButton("검색");
		btnSearch.setBounds(175, 171, 97, 23);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0); // 테이블 데이터 초기화
				// comboBox 검색 로그용
				if (rdbtnSort.isSelected()) {
					System.out.println("회사 이름 : " + compInt + " 부서명 : " + depInt + " 직급 : " + graInt);
					
					if (compInt != 0 && depInt != 0 && graInt != 0) {
						showAllMembers();
						textAreaLog.setText("조건으로 검색결과 입니다.");
					} else if (compInt != 0 && depInt != 0 && graInt == 0) {
						showMembersCompDep();
						textAreaLog.setText("조건으로 검색결과 입니다.");
					} else if (compInt != 0 && depInt == 0 && graInt != 0) {
						showMembersCompGrd();
						textAreaLog.setText("조건으로 검색결과 입니다.");
					} else if (compInt != 0 && depInt == 0 && graInt == 0) {
						showMembersComp();
						textAreaLog.setText("조건으로 검색결과 입니다.");
					} else if (compInt == 0 && depInt != 0 && graInt != 0) {
						showMembersDepGrd();
						textAreaLog.setText("조건으로 검색결과 입니다.");
					} else if (compInt == 0 && depInt != 0 && graInt == 0) {
						showMembersDep();
						textAreaLog.setText("조건으로 검색결과 입니다.");
					} else if (compInt == 0 && depInt == 0 && graInt != 0) {
						showMembersGrd();
						textAreaLog.setText("조건으로 검색결과 입니다.");
					} else {
						System.out.println("log . 없는 조건");
						textAreaLog.setText("없는 조건입니다.");
					}
					
					
				} else if (rdbtnName.isSelected()) {
					System.out.println("이름으로 검색결과 : " + textAreaSetSearch.getText());
					
					searchingByString();
				}
			}
		});
		btnSearch.setEnabled(false);
		btnSearch.setContentAreaFilled(false);
		panel_1.add(btnSearch);
		
		
		// JscrollPane / JTABLE
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 204, 260, 186);
		panel_1.add(scrollPane);
		
		tableModel = new DefaultTableModel(colNames, 0); // 모델 이름과 행 개수 설정
		table = new JTable(tableModel); // 테이블 모델에 테이블 적용
		table.setFont(new Font("굴림", Font.PLAIN, 15));
		table.getColumn("사번").setPreferredWidth(4);
		table.getColumn("이름").setPreferredWidth(30);
		table.getColumn("부서").setPreferredWidth(13);
		table.getColumn("직급").setPreferredWidth(13);
		table.getColumn("소속").setPreferredWidth(25);
		table.setRowHeight(25);

		scrollPane.setViewportView(table);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 행의 선택을 여러개 할 수 없고 하나의 행만 선택할 수 있도록 지정
		table.addMouseListener(this); // TODO
		
		
		// Log 출력 창
		textAreaLog = new JTextArea();
		textAreaLog.setBounds(22, 585, 250, 23);
		panel_1.add(textAreaLog);
		
		if (rdbtnSort.isSelected()) {
			rdbtnName.setEnabled(false);
			btnSearch.setEnabled(true);
		} else {
			rdbtnName.setEnabled(true);
			btnSearch.setEnabled(true);
		}
		
	}
	
	// 3가지 조건==========================================================
	public void showAllMembers() {
		tableModel.setRowCount(0); // 테이블 데이터 초기화
		
		ArrayList<MemberVO> list = mdao.showAll(compInt, depInt, graInt);
		textAreaSetSearch.setText("");
		int size = list.size();
		if (size == 0) {
			System.out.println("없는 조건입니다.");
		}
		for (int i = 0; i < size; i++) { 
			records[0] = list.get(i).getPs_id();
			records[1] = list.get(i).getPs_name();
			
			int num = list.get(i).getPs_dep();
			depTOString(num);
			records[2] = depStr;
			
			num = list.get(i).getPs_grd();
			grdTOString(num);
			records[3] = grdStr;
			
			num = list.get(i).getPs_comp();
			compToString(num);
			records[4] = compStr;
			
			tableModel.addRow(records);
		}
	}
	
	// 2가지 조건==========================================================
	public void showMembersCompDep() { // 회사명, 부서
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showCompDep(compInt, depInt);
		int size = list.size();
		
		if (size == 0) {
			System.out.println("log . 없는 조건");
		}
		for (int i = 0; i < size; i++) {
			records[0] = list.get(i).getPs_id();
			records[1] = list.get(i).getPs_name();

			int num = list.get(i).getPs_dep();
			depTOString(num);
			records[2] = depStr;
			
			num = list.get(i).getPs_grd();
			grdTOString(num);
			records[3] = grdStr;
			
			num = list.get(i).getPs_comp();
			compToString(num);
			records[4] = compStr;
			
			tableModel.addRow(records);
		}
	}
	
	public void showMembersCompGrd() { // 회사명, 직급
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showCompGrd(compInt, graInt);
		int size = list.size();
		
		if (size == 0) {
			System.out.println("log . 없는 조건");
		}
		for (int i = 0; i < size; i++) {
			records[0] = list.get(i).getPs_id();
			records[1] = list.get(i).getPs_name();

			int num = list.get(i).getPs_dep();
			depTOString(num);
			records[2] = depStr;
			
			num = list.get(i).getPs_grd();
			grdTOString(num);
			records[3] = grdStr;
			
			num = list.get(i).getPs_comp();
			compToString(num);
			records[4] = compStr;
			
			tableModel.addRow(records);
		}
	}
	
	public void showMembersDepGrd() { // 부서명, 직급
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showDepGrd(depInt, graInt);
		int size = list.size();
		
		if (size == 0) {
			System.out.println("log . 없는 조건");
		}
		for (int i = 0; i < size; i++) {
			records[0] = list.get(i).getPs_id();
			records[1] = list.get(i).getPs_name();

			int num = list.get(i).getPs_dep();
			depTOString(num);
			records[2] = depStr;
			
			num = list.get(i).getPs_grd();
			grdTOString(num);
			records[3] = grdStr;
			
			num = list.get(i).getPs_comp();
			compToString(num);
			records[4] = compStr;
			
			tableModel.addRow(records);
		}
	}
	
	// 1가지 ========================================================
	public void showMembersComp() { // 회사명
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showComp(compInt);
		int size = list.size();
		
		if (size == 0) {
			System.out.println("log . 없는 조건");
		}
		for (int i = 0; i < size; i++) {
			records[0] = list.get(i).getPs_id();
			records[1] = list.get(i).getPs_name();

			int num = list.get(i).getPs_dep();
			depTOString(num);
			records[2] = depStr;
			
			num = list.get(i).getPs_grd();
			grdTOString(num);
			records[3] = grdStr;
			
			num = list.get(i).getPs_comp();
			compToString(num);
			records[4] = compStr;
			
			tableModel.addRow(records);
		}
	}
	
	public void showMembersDep() {
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showDep(depInt);
		int size = list.size();
		
		if (size == 0) {
			System.out.println("log . 없는 조건");
		}
		for (int i = 0; i < size; i++) {
			records[0] = list.get(i).getPs_id();
			records[1] = list.get(i).getPs_name();

			int num = list.get(i).getPs_dep();
			depTOString(num);
			records[2] = depStr;
			
			num = list.get(i).getPs_grd();
			grdTOString(num);
			records[3] = grdStr;
			
			num = list.get(i).getPs_comp();
			compToString(num);
			records[4] = compStr;
			
			tableModel.addRow(records);
		}
	}
	
	public void showMembersGrd() {
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showGrd(graInt);
		int size = list.size();
		
		if (size == 0) {	
			System.out.println("log . 없는 조건");
		}
		for (int i = 0; i < size; i++) {
			records[0] = list.get(i).getPs_id();
			records[1] = list.get(i).getPs_name();

			int num = list.get(i).getPs_dep();
			depTOString(num);
			records[2] = depStr;
			
			num = list.get(i).getPs_grd();
			grdTOString(num);
			records[3] = grdStr;
			
			num = list.get(i).getPs_comp();
			compToString(num);
			records[4] = compStr;
			
			tableModel.addRow(records);
		}
	}
	
	// 이름으로 검색 ---------------------------------------------------------
	public void searchingByString() {
		tableModel.setRowCount(0);
		String str = "%" + textAreaSetSearch.getText() + "%";
		System.out.println(str);
		
		ArrayList<MemberVO> list = mdao.showString(str);
		int size = list.size();
		for (int i = 0; i < size ; i++) {
			if (list.get(i) != null) {
				records[0] = list.get(i).getPs_id();
				records[1] = list.get(i).getPs_name();

				int num = list.get(i).getPs_dep();
				depTOString(num);
				records[2] = depStr;
				
				num = list.get(i).getPs_grd();
				grdTOString(num);
				records[3] = grdStr;
				
				num = list.get(i).getPs_comp();
				compToString(num);
				records[4] = compStr;
				
				tableModel.addRow(records);
				textAreaLog.setText("이름으로 검색결과 입니다.");
			} else {
				textAreaLog.setText("검색결과가 없습니다.");
			}
		}
	}
	// JTable 새로고침
	public void updateJTable() {
		tableModel.setRowCount(0);
		
		((JLabel) panel.getComponent(0)).setText("");
		((JLabel) panel.getComponent(1)).setText("");
		((JLabel) panel.getComponent(2)).setText("");
		((JLabel) panel.getComponent(5)).setText("");
		((JLabel) panel.getComponent(7)).setText("");
		((JLabel) panel.getComponent(9)).setText("");
		((JLabel) panel.getComponent(12)).setText("");
		((JLabel) panel.getComponent(14)).setText("");
		((JLabel) panel.getComponent(16)).setText("");
		((JLabel) panel.getComponent(18)).setText("");
		((JLabel) panel.getComponent(20)).setText("");
		((JLabel) panel.getComponent(22)).setText("");
		
		
	}
	
	// MouseClick 메소드 오버라이딩
	@Override
	public void mouseClicked(MouseEvent e) {
		
			int row = table.getSelectedRow(); // 선택된 셀의 row값을 int형으로 변환
			Object a = table.getValueAt(row, 0);
			System.out.println("table이 눌렸습니다 " + a);
			/* Object a를 int 형식으로 형 변환 시킨 후, 
			 * SQL을 이용하여 DB내의 데이터와 대조 후
			 * SQL을 이용하여 해당 ROW의 데이터를 출력
			 * 출력은 새로운 창으로 */ /* 더블클릭 추가 */
			// 1. Object a를 int 형식으로 변환

			deleteCid(); // forcid테이블 내 데이터 청소
			cid = Integer.parseInt(a.toString());
			System.out.println("a 변환 후 " + cid);
			inputCid(); // cid 값을 저장한다.
			
			// 2. SQL을 이용하여 DB내의 데이터와 대조
			
			/* 새 프레임 구현 자리  TODO  */
			System.out.println("새 프레임(ShowMember)을 구현");
			count = 1;
			
			if (count == 1) {cid = mdao.output(); // cid값 넘겨 받기
			/*
			 * SQL을 이용하여 DB내의 데이터와 대조 후 SQL을 이용하여 해당 ROW의 데이터를 출력 출력은 새로운 창으로
			 */ /* 더블클릭 추가 */

			// 2. SQL을 이용하여 DB내의 데이터와 대조
			MemberVO vo = mdao.select(cid);
			grdNum = vo.getPs_grd(); /**/
			grdTOString(grdNum);
			depNum = vo.getPs_dep();
			depTOString(depNum);
			compNum = vo.getPs_comp();
			compToString(compNum);

			Memid = vo.getPs_id() + "";
			MemDepWant = depStr;
			MemName = vo.getPs_name();
			MemGrdCur = grdStr;
			MemCompCur = compStr;
			MemDepCur = depStr;

			MemCharMath = vo.getChar_math() + "";
			MemCharServ = vo.getChar_serv() + "";
			MemCharHand = vo.getChar_hand() + "";
			MemCharIq = vo.getChar_iq() + "";
			MemCharScien = vo.getChar_scien() + "";
			
			overall = ((vo.getChar_math() + vo.getChar_serv() + vo.getChar_hand() 
					+ vo.getChar_iq() + vo.getChar_scien()) / 5);
				
			strOverall = overall + "";
			AcceptComponent(panel);
//				frame.outputCid();
				count = 0;
			}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
	
	/* 멤버 비교는 새 프레임에서 구현 */
	
	// 시드 값 저장 메소드
	public void inputCid () {
		int result = mdao.input(cid); // 시드값 저장
		System.out.println(" sys.SearchGui.inputCid.log cid값 > " + cid);
		if (result == 1) {
			System.out.println("inputCid의 cid는 정상적으로 input 되었습니다.");
		} else {
			System.out.println("inputCid 작동 오류");
		}
	}
	
	// cid 삭제 메소드
	public void deleteCid() {
		int result = 0;
		
		result = mdao.deleteCid();
		if (result == 1) {
			System.out.println("cid는 성공적으로 삭제");
		} else {
			 System.out.println("cid 삭제 오류");
		}
	}
	
	// 부서 toString 메소드
	public String depTOString(int num) {
		switch (num) {
		case 1 :
			return depStr = "인사";
		case 2 :
			return depStr = "총무";
		case 3 :
			return depStr = "영업";
		case 4 :
			return depStr = "마케팅";
		case 5 :
			return depStr = "생산";
		case 6 :
			return depStr = "연구";
			default : 
				return "nothing"; // TODO (나중에 개정)
		}
	}
	
	// 직급 toString 메소드
	public String grdTOString(int num) {
		switch (num) {
		case 1:
			return grdStr = "부장";
		case 2:
			return grdStr = "차장";
		case 3:
			return grdStr = "과장";
		case 4:
			return grdStr = "대리";
		case 5:
			return grdStr = "주임";
		case 6:
			return grdStr = "인턴";
		default:
			return ""; // TODO (나중에 개선)
		}
	}
	
	// 소속 toString 메소드
	public String compToString(int num) {
		switch(num) {
		case 1 : 
			return compStr = "내 회사";
		case 2 : 
			return compStr = "NAVER";
		case 3 : 
			return compStr = "GOOGLE";
		case 4 : 
			return compStr = "은퇴";
		case 5 : 
			return compStr = "FA";
			default : 
				return "nothing";
		}
	}

	public void AcceptComponent(JPanel panel) {
//		String btnText1 = ((JButton)nineRoom.getComponent(0)).getText();
		
		((JLabel) panel.getComponent(0)).setText(Memid);
		((JLabel) panel.getComponent(1)).setText(MemDepWant);
		((JLabel) panel.getComponent(2)).setText(MemName);
		((JLabel) panel.getComponent(5)).setText(MemCompCur);
		((JLabel) panel.getComponent(7)).setText(MemDepCur);
		((JLabel) panel.getComponent(9)).setText(MemGrdCur);
		((JLabel) panel.getComponent(12)).setText(MemCharMath);
		((JLabel) panel.getComponent(14)).setText(MemCharServ);
		((JLabel) panel.getComponent(16)).setText(MemCharHand);
		((JLabel) panel.getComponent(18)).setText(MemCharIq);
		((JLabel) panel.getComponent(20)).setText(MemCharScien);
		((JLabel) panel.getComponent(22)).setText(strOverall);
		
		if (MemCompCur == "내 회사") {
			((JButton) panel.getComponent(23)).setEnabled(true);
			((JButton) panel.getComponent(24)).setEnabled(false);
			((JButton) panel.getComponent(25)).setEnabled(true);
			((JButton) panel.getComponent(26)).setEnabled(true);
		} else {
			((JButton) panel.getComponent(23)).setEnabled(false);
			((JButton) panel.getComponent(24)).setEnabled(true);
		}
		if (MemGrdCur == "부장") {
			((JButton) panel.getComponent(25)).setEnabled(false); // 진급
		} else if (MemGrdCur == "인턴") {
			((JButton) panel.getComponent(26)).setEnabled(false); // 강등
		}
	}

	@Override
	public void run() {
		
	}
}
