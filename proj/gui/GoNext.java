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
	// MVC 인스턴스 참조
	
	private static MemberDAO mdao;
	private static FinanceDAO fdao;
	
	
	// ***********************************************************
	
	
	// 예상정산용 변수
	private int curMoney, wage, fin_date;
	private double confidence, notWage;
	
	// NEW 정산용 변수
	private double exCompWage, realCompWage, exIncome, realIncome;
	
	// 정산결과용 변수
	private int realChanged;
	
	public double ChangeConfi = 0.7;

	// *************************************************************
	// 계산용 멤버 변수
	public int compManOA = 0;
	public int compCalOA = 0;
	public int compSelOA = 0;
	public int compMarOA = 0;
	public int compMakOA = 0;
	public int compDevOA = 0;
	
	// ******************************************************
	
	// 컴포넌트 선언

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
		
		lblprevMon = new JLabel("지난달 잔액");
		lblprevMon.setBounds(15, 15, 100, 24);
		panel.add(lblprevMon);
		
		lblFinOutput = new JLabel("지출 >");
		lblFinOutput.setBounds(15, 49, 100, 15);
		panel.add(lblFinOutput);
		
		lblshowExSpend = new JLabel();
		lblshowExSpend.setFont(new Font("굴림", Font.PLAIN, 14));
		lblshowExSpend.setBounds(68, 74, 107, 24);
		panel.add(lblshowExSpend);
		
		lblshowRealSpend = new JLabel();
		lblshowRealSpend.setFont(new Font("굴림", Font.BOLD, 14));
		lblshowRealSpend.setBounds(68, 108, 107, 31);
		panel.add(lblshowRealSpend);
		
		lblSetExSpend = new JLabel();
		lblSetExSpend.setBounds(187, 79, 100, 15);
		panel.add(lblSetExSpend);
		
		lblSetRealSpend = new JLabel();
		lblSetRealSpend.setBounds(187, 116, 100, 15);
		panel.add(lblSetRealSpend);
		
		lblFinIncome = new JLabel("수입 > ");
		lblFinIncome.setBounds(15, 149, 57, 15);
		panel.add(lblFinIncome);
		
		lblshowExIncom = new JLabel();
		lblshowExIncom.setFont(new Font("굴림", Font.PLAIN, 14));
		lblshowExIncom.setBounds(68, 174, 107, 26);
		panel.add(lblshowExIncom);
		
		lblshowRealIncome = new JLabel();
		lblshowRealIncome.setFont(new Font("굴림", Font.BOLD, 14));
		lblshowRealIncome.setBounds(68, 210, 107, 24);
		panel.add(lblshowRealIncome);
		
		lblSetExIncome = new JLabel();
		lblSetExIncome.setBounds(187, 180, 100, 15);
		panel.add(lblSetExIncome);
		
		lblSetRealIncome = new JLabel();
		lblSetRealIncome.setBounds(187, 215, 100, 15);
		panel.add(lblSetRealIncome);
		
		// 오차
		lblFinRealChanges = new JLabel();
		lblFinRealChanges.setFont(new Font("굴림", Font.BOLD, 14));
		lblFinRealChanges.setBounds(68, 244, 107, 35);
		panel.add(lblFinRealChanges);
		
		lblSetRealChanges = new JLabel();
		lblSetRealChanges.setBounds(187, 254, 100, 15);
		panel.add(lblSetRealChanges);
		
		lblShowResult = new JLabel("RESULT");
		lblShowResult.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 21));
		lblShowResult.setBounds(58, 289, 117, 33);
		panel.add(lblShowResult);
		
		lblSetResult = new JLabel();
		lblSetResult.setBounds(187, 289, 125, 33);
		panel.add(lblSetResult);
		
		lblSetPrevMon = new JLabel();
		lblSetPrevMon.setBounds(187, 20, 117, 15);
		panel.add(lblSetPrevMon);
		
		// 확인 버튼
		btnDaccord = new JButton("OK");
		btnDaccord.setBounds(295, 313, 107, 31);
		panel.add(btnDaccord);
		btnDaccord.setEnabled(false);
		
		// 작동
		importFinanceInfo();
		int curcurmon = curMoney;
		 if ((curcurmon / 1000) > 10) {
			 lblSetPrevMon.setText((curcurmon / 10000) + " 억 " + (curcurmon % 10000) + "만원");
		} else { 
			lblSetPrevMon.setText(curcurmon + "만원"); // 현재잔고 		
		}
		
		lblSetLog = new JLabel("");
		lblSetLog.setBounds(15, 323, 272, 24);
		panel.add(lblSetLog);
		calIncome();
		
		int delay1 = 1000; // delay in milliseconds 

		ActionListener taskPerformer1 = new ActionListener() { 
			public void actionPerformed(ActionEvent evt) { //...Perform a task...
		    	   lblSetExSpend.setText((int)exCompWage + " 만원");
		    	   lblshowExSpend.setText("예상 지출");
		    	   lblSetExIncome.setText((int)exIncome + " 만원");
		    	   lblshowExIncom.setText("예상 수입");
		    }
		};
		Timer timer = new Timer(delay1, taskPerformer1);
		timer.setRepeats(false); 
		timer.start(); // timer starts - after delay time your task gets executed
       
		int delay2 = 2000;
		ActionListener taskPerformer2 = new ActionListener() { 
			public void actionPerformed(ActionEvent evt) { //...Perform a task... // 여러개의 딜레이를 쓸때, 시간만 바꿔서 그룹으로 묶어서 사용
	    	   lblSetRealSpend.setText((int)realCompWage + " 만원");
	    	   lblshowRealSpend.setText("실 지출");
	    	   lblSetRealIncome.setText((int)realIncome + " 만원");
	    	   lblshowRealIncome.setText("실 수입");
			}
	    };
		Timer timer2 = new Timer(delay2, taskPerformer2);
		timer2.setRepeats(false); 
		timer2.start(); // timer starts - after delay time your task gets executed
		
		 
		int delay3 = 3000;
		ActionListener taskPerformer3 = new ActionListener() { 
			 public void actionPerformed(ActionEvent evt) { //...Perform a task... // 여러개의 딜레이를 쓸때, 시간만 바꿔서 그룹으로 묶어서 사용
				 lblSetRealChanges.setText((int)realChanged + " 만원");
				 lblFinRealChanges.setText("실 변동액");
			 }
	     };
	     Timer timer3 = new Timer(delay3, taskPerformer3);
		 timer3.setRepeats(false); 
		 timer3.start(); // timer starts - after delay time your task gets executed
		 
		 int delay4 = 5000;
		 ActionListener taskPerformer4 = new ActionListener() { 
			 public void actionPerformed(ActionEvent evt) { //...Perform a task... // 여러개의 딜레이를 쓸때, 시간만 바꿔서 그룹으로 묶어서 사용
				 if ((curMoney / 1000) > 10) {
					 	lblSetResult.setText((curMoney / 10000) + " 억 " + (curMoney % 10000) + "만원");
				} else { 
					lblSetResult.setText(curMoney + "만원"); // 현재잔고 		
				}
//				 lblSetResult.setText(curMoney + " 만원");
				 insertFinance();
				 if (realChanged > 100) {
					 lblSetLog.setText("괜찮은 실적 입니다.");
				 } else if (realChanged > 30 && realChanged <= 100) {
					 lblSetLog.setText("좋지 않은 결과입니다.");
				 } else if (realChanged <= 30 && realChanged > 0) {
					 lblSetLog.setText("주의가 필요합니다.");
				 } else if (realChanged <= 0) {
					 lblSetLog.setText("적자입니다.");
				 }
			 }
	     };
	     Timer timer4 = new Timer(delay4, taskPerformer4);
		 timer4.setRepeats(false); 
		 timer4.start(); // timer starts - after delay time your task gets executed
		 
		 
		 int delay5 = 7000;
		 ActionListener taskPerformer5 = new ActionListener() { 
			 public void actionPerformed(ActionEvent evt) { //...Perform a task... // 여러개의 딜레이를 쓸때, 시간만 바꿔서 그룹으로 묶어서 사용
				 btnDaccord.setEnabled(true);
			 }
	     };
	     Timer timer5 = new Timer(delay5, taskPerformer5);
		 timer5.setRepeats(false); 
		 timer5.start(); // timer starts - after delay time your task gets executed
	}
	
		
	// 재정 상태 저장용 메소드
	public void insertFinance() { 
		System.out.println(" sys.MainFrame.insertFinance()");
		
		int result = 0;
		FinanceVO vo = new FinanceVO(curMoney, (int)realCompWage, notWage, confidence, (int)realIncome, 0, fin_date);
		result = fdao.insert(vo);
		
		if (result == 1) {
			System.out.println(" sys.MainFrame.insertFinance() 정상 저장 완료");
		}
	}
	
	// 재정 상태 import용 메소드
	public void importFinanceInfo() { 
		System.out.println(" sys.MainFrame.importFinanceInfo()");
		FinanceVO vo = fdao.select(1);
		System.out.println(" sys.MainFrame.importFinanceInfo() 현재 잔액 : " + vo.getCurMoney());
		curMoney = vo.getCurMoney();
		wage = vo.getWage();
		notWage = vo.getNotWage();
		confidence = vo.getConfidence();
		fin_date = vo.getFin_date();
		
	}
	
	
	
	
	
	// 정산 계산 메소드
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
			if (list.get(i).getPs_comp() == 1) { // 우리 회사 사람
				
				switch (list.get(i).getPs_dep()) {
					case 1 : // 인사과
						compManOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd());
						System.out.println("인사overall >??> " + compManOA + " 인사 wage >>  " + wage);
						count++;
						break;
					case 2 : // 총무과 5 7
						compCalOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd());
						System.out.println("총무overall >??> " + compCalOA + " 총무 wage >>  " + wage);
						count++;
						break;
					case 3 : // 영업
						compSelOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd()); // 급여 메소드
						System.out.println("영업overall >??> " + compSelOA + " 영업 wage >>  " + wage);
						count++;
						break;
					case 4 : // 마케팅
						compMarOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd());
						System.out.println("마케팅overall >??> " + compMarOA + " 마케 wage >>  " + wage);
						count++;
						break;
					case 5 : // 생산관리
						compMakOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (7 - list.get(i).getPs_grd());
						System.out.println("생산overall >??> " + compMakOA + " 생산 wage >>  " + wage);
						count++;
						break;
					case 6 :  // 연구개발
						compDevOA += overall + (7 - list.get(i).getPs_grd());
						wage += overall * 20 * (8 - list.get(i).getPs_grd());
						System.out.println("연구overall >??> " + compDevOA + " 연구 wage >>  " + wage);
						count++;
						break;
					default : 
						break;
				}
				
			}
		}
		
		// 예상 수입 계산 // double
		exIncome = (((compMakOA * 10) * compMarOA + 150) + (compDevOA * 1.1)) * 
				(compSelOA * 0.1);  
		
		// 예상 지출 계산 // double // 3000 6 6
		exCompWage = (((wage * notWage * 3) - (compManOA * count)) - (compCalOA * 50 * notWage)) / 10;
		System.out.println("exCompWage >>>> " + exCompWage);
		
		
		// overall에 random 추가하기
		compManOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compCalOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compSelOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compMakOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compDevOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		compMarOA += ((int)((Math.random() - ChangeConfi + confidence) * 10));
		
		// 실 수입 계산 // double
		realIncome = (((compMakOA * 10) * compMarOA + 150) + (compDevOA * 1.1)) * 
				(compSelOA * 0.1); 
		
		// 실 지출 계산 // double
		realCompWage = (((wage * notWage * 3) - (compManOA * count)) - (compCalOA * 50 * notWage)) / 10;
									
		// 실 변동액 계산
		realChanged = (int)(realIncome - realCompWage);
		
		curMoney = curMoney + (int)realChanged;
		fin_date++;
		
		}
}
