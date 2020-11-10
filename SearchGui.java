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
	private String[] nameComp = new String[] {"�Ҽ�", "�� ȸ��", "NAVER", "GOOGLE", "����" , "FA"};
	private String[] nameDep = new String[] {"�μ�", "�λ�", "�ѹ�", "����", "������", "�������", "��������"};
	private String[] nameGra = new String[] {"����", "����", "����", "����", "�븮", "����", "����"};
	private String compString, depString, graString;
	private int compInt = 0, depInt = 0, graInt = 0;
	private JRadioButton rdbtnSort, rdbtnName; 
	
	/* ���� ���̺��� ����ϱ� ���� ��� ���� ���� */
	private JTable table;
	private String[] colNames = {"���", "�̸�", "�μ�", "����", "�Ҽ�"}; // ���̺� ����� �� �̸���
	private Object[] records = new Object[colNames.length]; // ���̺� �����͸� ������ �迭��ü
	private DefaultTableModel tableModel; // ���̺� ���¸� ���� �� ����
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
		
		
		Font smallFont = new Font("����ü", Font.BOLD, 18);
		
		lblSearchMember = new JLabel("��� ����");
		lblSearchMember.setBounds(12, 10, 121, 33);
		lblSearchMember.setFont(smallFont);
		panel_1.add(lblSearchMember);
		
		// üũ�ڽ� ��� �������
		rdbtnSort = new JRadioButton("�Ӽ����� �˻�");
		rdbtnSort.setBounds(22, 53, 121, 23);
		panel_1.add(rdbtnSort);
		
		rdbtnName = new JRadioButton("�̸����� �˻�");
		rdbtnName.setBounds(22, 109, 121, 23);
		panel_1.add(rdbtnName);
		
		ButtonGroup Group = new ButtonGroup();
		Group.add(rdbtnName);
		Group.add(rdbtnSort);
		
		
		
		
		// ȸ��� �޺��ڽ�
		cBoxComp = new JComboBox(); 
		cBoxComp.setModel(new DefaultComboBoxModel(nameComp));
		cBoxComp.setBounds(22, 82, 67, 21);
		cBoxComp.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
			
				// "�Ҽ�", "�� ȸ��", "NAVER", "GOOGLE", "����" , "FA"
				if ((String) cBoxComp.getSelectedItem() == "�Ҽ�") {
					compInt = 0;
				} else if ((String) cBoxComp.getSelectedItem() == "�� ȸ��") {
					compInt = 1;
				} else if ((String) cBoxComp.getSelectedItem() == "NAVER") {
					compInt = 2;
				} else if ((String) cBoxComp.getSelectedItem() == "GOOGLE") {
					compInt = 3;
				} else if ((String) cBoxComp.getSelectedItem() == "����") {
					compInt = 4;
				} else {	
					compInt = 5;
				}
			
			}
		});
	
		panel_1.add(cBoxComp);
		
		// �μ��� �޺��ڽ�
		cBoxDepartment = new JComboBox(); 
		cBoxDepartment.setModel(new DefaultComboBoxModel(nameDep));
		cBoxDepartment.setBounds(108, 82, 67, 21);
		cBoxDepartment.addItemListener(new ItemListener() {
			
			// "�μ�", "�λ�", "�ѹ�", "����", "������", "�������", "��������"
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((String) cBoxDepartment.getSelectedItem() == "�μ�") {
					depInt = 0;
				} else if ((String) cBoxDepartment.getSelectedItem() == "�λ�") {
					depInt = 1;
				} else if ((String) cBoxDepartment.getSelectedItem() == "�ѹ�") {
					depInt = 2;
				} else if ((String) cBoxDepartment.getSelectedItem() == "����") {
					depInt = 3;
				} else if ((String) cBoxDepartment.getSelectedItem() == "����") {
					depInt = 4;
				} else if ((String) cBoxDepartment.getSelectedItem() == "����"){	
					depInt = 5;
				} else if ((String) cBoxDepartment.getSelectedItem() == "����"){	
					depInt = 6;
				}
			}
		});
		
		panel_1.add(cBoxDepartment);
		
		// ���޸� �޺��ڽ�
		cBoxGrade = new JComboBox();
		cBoxGrade.setModel(new DefaultComboBoxModel(nameGra));
		cBoxGrade.setBounds(187, 82, 67, 21);
		cBoxGrade.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// "����", "����", "����", "����", "�븮", "����", "����"
				if ((String) cBoxGrade.getSelectedItem() == "����") {
					graInt = 0;
				} else if ((String) cBoxGrade.getSelectedItem() == "����") {
					graInt = 1;
				} else if ((String) cBoxGrade.getSelectedItem() == "����") {
					graInt = 2;
				} else if ((String) cBoxGrade.getSelectedItem() == "����") {
					graInt = 3;
				} else if ((String) cBoxGrade.getSelectedItem() == "�븮"){	
					graInt = 4;
				} else if ((String) cBoxGrade.getSelectedItem() == "����"){	
					graInt = 5;
				} else {
					graInt = 6;
				}
			}
		});
		
		panel_1.add(cBoxGrade);
		
		
		// �˻�â
		textAreaSetSearch = new JTextArea();
		textAreaSetSearch.setBounds(22, 138, 232, 23);
		panel_1.add(textAreaSetSearch);
		
		// �˻���ư 
		btnSearch = new JButton("�˻�");
		btnSearch.setBounds(175, 171, 97, 23);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0); // ���̺� ������ �ʱ�ȭ
				// comboBox �˻� �α׿�
				if (rdbtnSort.isSelected()) {
					System.out.println("ȸ�� �̸� : " + compInt + " �μ��� : " + depInt + " ���� : " + graInt);
					
					if (compInt != 0 && depInt != 0 && graInt != 0) {
						showAllMembers();
						textAreaLog.setText("�������� �˻���� �Դϴ�.");
					} else if (compInt != 0 && depInt != 0 && graInt == 0) {
						showMembersCompDep();
						textAreaLog.setText("�������� �˻���� �Դϴ�.");
					} else if (compInt != 0 && depInt == 0 && graInt != 0) {
						showMembersCompGrd();
						textAreaLog.setText("�������� �˻���� �Դϴ�.");
					} else if (compInt != 0 && depInt == 0 && graInt == 0) {
						showMembersComp();
						textAreaLog.setText("�������� �˻���� �Դϴ�.");
					} else if (compInt == 0 && depInt != 0 && graInt != 0) {
						showMembersDepGrd();
						textAreaLog.setText("�������� �˻���� �Դϴ�.");
					} else if (compInt == 0 && depInt != 0 && graInt == 0) {
						showMembersDep();
						textAreaLog.setText("�������� �˻���� �Դϴ�.");
					} else if (compInt == 0 && depInt == 0 && graInt != 0) {
						showMembersGrd();
						textAreaLog.setText("�������� �˻���� �Դϴ�.");
					} else {
						System.out.println("log . ���� ����");
						textAreaLog.setText("���� �����Դϴ�.");
					}
					
					
				} else if (rdbtnName.isSelected()) {
					System.out.println("�̸����� �˻���� : " + textAreaSetSearch.getText());
					
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
		
		tableModel = new DefaultTableModel(colNames, 0); // �� �̸��� �� ���� ����
		table = new JTable(tableModel); // ���̺� �𵨿� ���̺� ����
		table.setFont(new Font("����", Font.PLAIN, 15));
		table.getColumn("���").setPreferredWidth(4);
		table.getColumn("�̸�").setPreferredWidth(30);
		table.getColumn("�μ�").setPreferredWidth(13);
		table.getColumn("����").setPreferredWidth(13);
		table.getColumn("�Ҽ�").setPreferredWidth(25);
		table.setRowHeight(25);

		scrollPane.setViewportView(table);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ���� ������ ������ �� �� ���� �ϳ��� �ุ ������ �� �ֵ��� ����
		table.addMouseListener(this); // TODO
		
		
		// Log ��� â
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
	
	// 3���� ����==========================================================
	public void showAllMembers() {
		tableModel.setRowCount(0); // ���̺� ������ �ʱ�ȭ
		
		ArrayList<MemberVO> list = mdao.showAll(compInt, depInt, graInt);
		textAreaSetSearch.setText("");
		int size = list.size();
		if (size == 0) {
			System.out.println("���� �����Դϴ�.");
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
	
	// 2���� ����==========================================================
	public void showMembersCompDep() { // ȸ���, �μ�
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showCompDep(compInt, depInt);
		int size = list.size();
		
		if (size == 0) {
			System.out.println("log . ���� ����");
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
	
	public void showMembersCompGrd() { // ȸ���, ����
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showCompGrd(compInt, graInt);
		int size = list.size();
		
		if (size == 0) {
			System.out.println("log . ���� ����");
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
	
	public void showMembersDepGrd() { // �μ���, ����
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showDepGrd(depInt, graInt);
		int size = list.size();
		
		if (size == 0) {
			System.out.println("log . ���� ����");
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
	
	// 1���� ========================================================
	public void showMembersComp() { // ȸ���
		tableModel.setRowCount(0);
		
		ArrayList<MemberVO> list = mdao.showComp(compInt);
		int size = list.size();
		
		if (size == 0) {
			System.out.println("log . ���� ����");
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
			System.out.println("log . ���� ����");
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
			System.out.println("log . ���� ����");
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
	
	// �̸����� �˻� ---------------------------------------------------------
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
				textAreaLog.setText("�̸����� �˻���� �Դϴ�.");
			} else {
				textAreaLog.setText("�˻������ �����ϴ�.");
			}
		}
	}
	// JTable ���ΰ�ħ
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
	
	// MouseClick �޼ҵ� �������̵�
	@Override
	public void mouseClicked(MouseEvent e) {
		
			int row = table.getSelectedRow(); // ���õ� ���� row���� int������ ��ȯ
			Object a = table.getValueAt(row, 0);
			System.out.println("table�� ���Ƚ��ϴ� " + a);
			/* Object a�� int �������� �� ��ȯ ��Ų ��, 
			 * SQL�� �̿��Ͽ� DB���� �����Ϳ� ���� ��
			 * SQL�� �̿��Ͽ� �ش� ROW�� �����͸� ���
			 * ����� ���ο� â���� */ /* ����Ŭ�� �߰� */
			// 1. Object a�� int �������� ��ȯ

			deleteCid(); // forcid���̺� �� ������ û��
			cid = Integer.parseInt(a.toString());
			System.out.println("a ��ȯ �� " + cid);
			inputCid(); // cid ���� �����Ѵ�.
			
			// 2. SQL�� �̿��Ͽ� DB���� �����Ϳ� ����
			
			/* �� ������ ���� �ڸ�  TODO  */
			System.out.println("�� ������(ShowMember)�� ����");
			count = 1;
			
			if (count == 1) {cid = mdao.output(); // cid�� �Ѱ� �ޱ�
			/*
			 * SQL�� �̿��Ͽ� DB���� �����Ϳ� ���� �� SQL�� �̿��Ͽ� �ش� ROW�� �����͸� ��� ����� ���ο� â����
			 */ /* ����Ŭ�� �߰� */

			// 2. SQL�� �̿��Ͽ� DB���� �����Ϳ� ����
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
	
	/* ��� �񱳴� �� �����ӿ��� ���� */
	
	// �õ� �� ���� �޼ҵ�
	public void inputCid () {
		int result = mdao.input(cid); // �õ尪 ����
		System.out.println(" sys.SearchGui.inputCid.log cid�� > " + cid);
		if (result == 1) {
			System.out.println("inputCid�� cid�� ���������� input �Ǿ����ϴ�.");
		} else {
			System.out.println("inputCid �۵� ����");
		}
	}
	
	// cid ���� �޼ҵ�
	public void deleteCid() {
		int result = 0;
		
		result = mdao.deleteCid();
		if (result == 1) {
			System.out.println("cid�� ���������� ����");
		} else {
			 System.out.println("cid ���� ����");
		}
	}
	
	// �μ� toString �޼ҵ�
	public String depTOString(int num) {
		switch (num) {
		case 1 :
			return depStr = "�λ�";
		case 2 :
			return depStr = "�ѹ�";
		case 3 :
			return depStr = "����";
		case 4 :
			return depStr = "������";
		case 5 :
			return depStr = "����";
		case 6 :
			return depStr = "����";
			default : 
				return "nothing"; // TODO (���߿� ����)
		}
	}
	
	// ���� toString �޼ҵ�
	public String grdTOString(int num) {
		switch (num) {
		case 1:
			return grdStr = "����";
		case 2:
			return grdStr = "����";
		case 3:
			return grdStr = "����";
		case 4:
			return grdStr = "�븮";
		case 5:
			return grdStr = "����";
		case 6:
			return grdStr = "����";
		default:
			return ""; // TODO (���߿� ����)
		}
	}
	
	// �Ҽ� toString �޼ҵ�
	public String compToString(int num) {
		switch(num) {
		case 1 : 
			return compStr = "�� ȸ��";
		case 2 : 
			return compStr = "NAVER";
		case 3 : 
			return compStr = "GOOGLE";
		case 4 : 
			return compStr = "����";
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
		
		if (MemCompCur == "�� ȸ��") {
			((JButton) panel.getComponent(23)).setEnabled(true);
			((JButton) panel.getComponent(24)).setEnabled(false);
			((JButton) panel.getComponent(25)).setEnabled(true);
			((JButton) panel.getComponent(26)).setEnabled(true);
		} else {
			((JButton) panel.getComponent(23)).setEnabled(false);
			((JButton) panel.getComponent(24)).setEnabled(true);
		}
		if (MemGrdCur == "����") {
			((JButton) panel.getComponent(25)).setEnabled(false); // ����
		} else if (MemGrdCur == "����") {
			((JButton) panel.getComponent(26)).setEnabled(false); // ����
		}
	}

	@Override
	public void run() {
		
	}
}
