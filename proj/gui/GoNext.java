package proj.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import proj.finance.mvc.FinanceDAO;
import proj.finance.mvc.FinanceDAOImple;
import proj.finance.mvc.FinanceVO;
import proj.member.mvc.MemberDAO;
import proj.member.mvc.MemberDAOImple;
import proj.member.mvc.MemberVO;
import javax.swing.JButton;

public class GoNext extends JPanel{

	
	private static GoNext instance = null;
	
	public static GoNext getInstance() {
		if (instance == null) {
			instance = new GoNext();
		}
		return instance;
	}
	
	// ***********************************************************
	// MVC �ν��Ͻ� ����
	
	private static MemberDAO mdao;
	private static FinanceDAO fdao;
	
	
	// ***********************************************************
	
	
	// ��������� ����
	private int curMoney, wage, fin_date;
	private double confidence, notWage;
	
	// NEW ����� ����
	private double exCompWage, realCompWage, exIncome, realIncome;
	
	// �������� ����
	private int realChanged;
	
	public double ChangeConfi = 0.7;

	// *************************************************************
	// ���� ��� ����
	public int compManOA = 0;
	public int compCalOA = 0;
	public int compSelOA = 0;
	public int compMarOA = 0;
	public int compMakOA = 0;
	public int compDevOA = 0;
	
	// ******************************************************
	
	// ������Ʈ ����

	public JPanel panel;
	
	private JLabel lblFinOutput, lblshowExSpend, lblshowRealSpend, lblSetExSpend, 
			lblSetRealSpend, lblFinIncome, lblshowExIncom, lblshowRealIncome, 
			lblSetExIncome, lblSetRealIncome, lblFinRealChanges, lblSetRealChanges, 
			lblShowResult, lblSetResult, lblSetPrevMon, lblSetLog, lblprevMon;
	public JButton btnDaccord;
	
	public GoNext() {
		initialize();
	}

	
	private void initialize() {
		
		mdao = MemberDAOImple.getInstance();
		fdao = FinanceDAOImple.getInstance();
		
		panel = new JPanel();
		panel.setBounds(130, 84, 414, 354);
//		add(panel);
		panel.setLayout(null);
		
		lblprevMon = new JLabel("������ �ܾ�");
		lblprevMon.setBounds(15, 15, 100, 24);
		panel.add(lblprevMon);
		
		lblFinOutput = new JLabel("���� >");
		lblFinOutput.setBounds(15, 49, 100, 15);
		panel.add(lblFinOutput);
		
		lblshowExSpend = new JLabel();
		lblshowExSpend.setFont(new Font("����", Font.PLAIN, 14));
		lblshowExSpend.setBounds(68, 74, 107, 24);
		panel.add(lblshowExSpend);
		
		lblshowRealSpend = new JLabel();
		lblshowRealSpend.setFont(new Font("����", Font.BOLD, 14));
		lblshowRealSpend.setBounds(68, 108, 107, 31);
		panel.add(lblshowRealSpend);
		
		lblSetExSpend = new JLabel();
		lblSetExSpend.setBounds(187, 79, 100, 15);
		panel.add(lblSetExSpend);
		
		lblSetRealSpend = new JLabel();
		lblSetRealSpend.setBounds(187, 116, 100, 15);
		panel.add(lblSetRealSpend);
		
		lblFinIncome = new JLabel("���� > ");
		lblFinIncome.setBounds(15, 149, 57, 15);
		panel.add(lblFinIncome);
		
		lblshowExIncom = new JLabel();
		lblshowExIncom.setFont(new Font("����", Font.PLAIN, 14));
		lblshowExIncom.setBounds(68, 174, 107, 26);
		panel.add(lblshowExIncom);
		
		lblshowRealIncome = new JLabel();
		lblshowRealIncome.setFont(new Font("����", Font.BOLD, 14));
		lblshowRealIncome.setBounds(68, 210, 107, 24);
		panel.add(lblshowRealIncome);
		
		lblSetExIncome = new JLabel();
		lblSetExIncome.setBounds(187, 180, 100, 15);
		panel.add(lblSetExIncome);
		
		lblSetRealIncome = new JLabel();
		lblSetRealIncome.setBounds(187, 215, 100, 15);
		panel.add(lblSetRealIncome);
		
		// ����
		lblFinRealChanges = new JLabel();
		lblFinRealChanges.setFont(new Font("����", Font.BOLD, 14));
		lblFinRealChanges.setBounds(68, 244, 107, 35);
		panel.add(lblFinRealChanges);
		
		lblSetRealChanges = new JLabel();
		lblSetRealChanges.setBounds(187, 254, 100, 15);
		panel.add(lblSetRealChanges);
		
		lblShowResult = new JLabel("RESULT");
		lblShowResult.setFont(new Font("����", Font.BOLD | Font.ITALIC, 21));
		lblShowResult.setBounds(58, 289, 117, 33);
		panel.add(lblShowResult);
		
		lblSetResult = new JLabel();
		lblSetResult.setBounds(187, 289, 125, 33);
		panel.add(lblSetResult);
		
		lblSetPrevMon = new JLabel();
		lblSetPrevMon.setBounds(187, 20, 117, 15);
		panel.add(lblSetPrevMon);
		
		// Ȯ�� ��ư
		btnDaccord = new JButton("OK");
		btnDaccord.setBounds(295, 313, 107, 31);
		panel.add(btnDaccord);
		btnDaccord.setEnabled(false);
		
		// �۵�
		importFinanceInfo();
		int curcurmon = curMoney;
		 if ((curcurmon / 1000) > 10) {
			 lblSetPrevMon.setText((curcurmon / 10000) + " �� " + (curcurmon % 10000) + "����");
		} else { 
			lblSetPrevMon.setText(curcurmon + "����"); // �����ܰ� 		
		}
		
		lblSetLog = new JLabel("");
		lblSetLog.setBounds(15, 323, 272, 24);
		panel.add(lblSetLog);
		calIncome();
		
		int delay1 = 1000; // delay in milliseconds 

		ActionListener taskPerformer1 = new ActionListener() { 
			public void actionPerformed(ActionEvent evt) { //...Perform a task...
		    	   lblSetExSpend.setText((int)exCompWage + " ����");
		    	   lblshowExSpend.setText("���� ����");
		    	   lblSetExIncome.setText((int)exIncome + " ����");
		    	   lblshowExIncom.setText("���� ����");
		    }
		};
		Timer timer = new Timer(delay1, taskPerformer1);
		timer.setRepeats(false); 
		timer.start(); // timer starts - after delay time your task gets executed
       
		int delay2 = 2000;
		ActionListener taskPerformer2 = new ActionListener() { 
			public void actionPerformed(ActionEvent evt) { //...Perform a task... // �������� �����̸� ����, �ð��� �ٲ㼭 �׷����� ��� ���
	    	   lblSetRealSpend.setText((int)realCompWage + " ����");
	    	   lblshowRealSpend.setText("�� ����");
	    	   lblSetRealIncome.setText((int)realIncome + " ����");
	    	   lblshowRealIncome.setText("�� ����");
			}
	    };
		Timer timer2 = new Timer(delay2, taskPerformer2);
		timer2.setRepeats(false); 
		timer2.start(); // timer starts - after delay time your task gets executed
		
		 
		int delay3 = 3000;
		ActionListener taskPerformer3 = new ActionListener() { 
			 public void actionPerformed(ActionEvent evt) { //...Perform a task... // �������� �����̸� ����, �ð��� �ٲ㼭 �׷����� ��� ���
				 lblSetRealChanges.setText((int)realChanged + " ����");
				 lblFinRealChanges.setText("�� ������");
			 }
	     };
	     Timer timer3 = new Timer(delay3, taskPerformer3);
		 timer3.setRepeats(false); 
		 timer3.start(); // timer starts - after delay time your task gets executed
		 
		 int delay4 = 5000;
		 ActionListener taskPerformer4 = new ActionListener() { 
			 public void actionPerformed(ActionEvent evt) { //...Perform a task... // �������� �����̸� ����, �ð��� �ٲ㼭 �׷����� ��� ���
				 if ((curMoney / 1000) > 10) {
					 	lblSetResult.setText((curMoney / 10000) + " �� " + (curMoney % 10000) + "����");
				} else { 
					lblSetResult.setText(curMoney + "����"); // �����ܰ� 		
				}
//				 lblSetResult.setText(curMoney + " ����");
				 insertFinance();
				 if (realChanged > 100) {
					 lblSetLog.setText("������ ���� �Դϴ�.");
				 } else if (realChanged > 30 && realChanged <= 100) {
					 lblSetLog.setText("���� ���� ����Դϴ�.");
				 } else if (realChanged <= 30 && realChanged > 0) {
					 lblSetLog.setText("���ǰ� �ʿ��մϴ�.");
				 } else if (realChanged <= 0) {
					 lblSetLog.setText("�����Դϴ�.");
				 }
			 }
	     };
	     Timer timer4 = new Timer(delay4, taskPerformer4);
		 timer4.setRepeats(false); 
		 timer4.start(); // timer starts - after delay time your task gets executed
		 
		 
		 int delay5 = 7000;
		 ActionListener taskPerformer5 = new ActionListener() { 
			 public void actionPerformed(ActionEvent evt) { //...Perform a task... // �������� �����̸� ����, �ð��� �ٲ㼭 �׷����� ��� ���
				 btnDaccord.setEnabled(true);
			 }
	     };
	     Timer timer5 = new Timer(delay5, taskPerformer5);
		 timer5.setRepeats(false); 
		 timer5.start(); // timer starts - after delay time your task gets executed
	}
	
		
	// ���� ���� ����� �޼ҵ�
	public void insertFinance() { 
		System.out.println(" sys.MainFrame.insertFinance()");
		
		int result = 0;
		FinanceVO vo = new FinanceVO(curMoney, (int)realCompWage, notWage, confidence, (int)realIncome, 0, fin_date);
		result = fdao.insert(vo);
		
		if (result == 1) {
			System.out.println(" sys.MainFrame.insertFinance() ���� ���� �Ϸ�");
		}
	}
	
	// ���� ���� import�� �޼ҵ�
	public void importFinanceInfo() { 
		System.out.println(" sys.MainFrame.importFinanceInfo()");
		FinanceVO vo = fdao.select(1);
		System.out.println(" sys.MainFrame.importFinanceInfo() ���� �ܾ� : " + vo.getCurMoney());
		curMoney = vo.getCurMoney();
		wage = vo.getWage();
		notWage = vo.getNotWage();
		confidence = vo.getConfidence();
		fin_date = vo.getFin_date();
		
	}
	
	
	
	
	
	// ���� ��� �޼ҵ�
	public void calIncome() {
		ArrayList<MemberVO> list = new ArrayList<>();
		
		list = mdao.select();
		int overall = 0;
		compManOA = 0;
		compMakOA = 0;
		compMarOA = 0;
		compSelOA = 0;
		compCalOA = 0;
		compDevOA = 0;
		int count = 0;
		wage = 0;
		
		
		for (int i = 0; i < list.size(); i++) {
			overall = (list.get(i).getChar_hand() + list.get(i).getChar_iq() +
					list.get(i).getChar_math() + list.get(i).getChar_scien() +
					list.get(i).getChar_serv()) / 5 ;
			if (list.get(i).getPs_comp() == 1) { // �츮 ȸ�� ���
				
				switch (list.get(i).getPs_dep()) {
					case 1 : // �λ��
						compManOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd());
						System.out.println("�λ�overall >??> " + compManOA + " �λ� wage >>  " + wage);
						count++;
						break;
					case 2 : // �ѹ��� 5 7
						compCalOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd());
						System.out.println("�ѹ�overall >??> " + compCalOA + " �ѹ� wage >>  " + wage);
						count++;
						break;
					case 3 : // ����
						compSelOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd()); // �޿� �޼ҵ�
						System.out.println("����overall >??> " + compSelOA + " ���� wage >>  " + wage);
						count++;
						break;
					case 4 : // ������
						compMarOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd());
						System.out.println("������overall >??> " + compMarOA + " ���� wage >>  " + wage);
						count++;
						break;
					case 5 : // �������
						compMakOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (7 - list.get(i).getPs_grd());
						System.out.println("����overall >??> " + compMakOA + " ���� wage >>  " + wage);
						count++;
						break;
					case 6 :  // ��������
						compDevOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd());
						System.out.println("����overall >??> " + compDevOA + " ���� wage >>  " + wage);
						count++;
						break;
					default : 
						break;
				}
				
			}
		}
		
		// ���� ���� ��� // double
		exIncome = (((compMakOA * 10) * compMarOA + 150) + (compDevOA * 1.1)) * 
				(compSelOA * 0.1);  
		
		// ���� ���� ��� // double // 3000 6 6
		exCompWage = (((wage * notWage * 3) - (compManOA * count)) - (compCalOA * 50 * notWage)) / 10;
		System.out.println("exCompWage >>>> " + exCompWage);
		
		
		// overall�� random �߰��ϱ�
		compManOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compCalOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compSelOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compMakOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compDevOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compMarOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		
		// �� ���� ��� // double
		realIncome = (((compMakOA * 10) * compMarOA + 150) + (compDevOA * 1.1)) * 
				(compSelOA * 0.1); 
		
		// �� ���� ��� // double
		realCompWage = (((wage * notWage * 3) - (compManOA * count)) - (compCalOA * 50 * notWage)) / 10;
									
		// �� ������ ���
		realChanged = (int)(realIncome - realCompWage);
		
		curMoney = curMoney + (int)realChanged;
		fin_date++;
		
		}
}
