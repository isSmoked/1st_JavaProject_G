package proj.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import proj.finance.mvc.FinanceDAO;
import proj.finance.mvc.FinanceDAOImple;
import proj.finance.mvc.FinanceVO;

public class FinanceGui extends JPanel {

//	private static FinanceGui instance = null;
//	
//	public static FinanceGui getInstance() {
//		if (instance == null) {
//			instance = new FinanceGui();
//		}
//		return instance;
//	}
	// TODO financeGUI 개선
	private static FinanceDAO fdao;
	private static GoNext gn;
	
	public JPanel panel;
	
	private JLabel lblInfoCurrent, lblSetCurrent, lblNewLabel_2,
			lblSetIncome, lblNewLabel_4, lblSetWage, lblShowLastDid,
			lblShowTeamMan, lblShowTeamCal, lblSetTeamMan;
	
	private JLabel lblShowTeamMak, lblSetTeamDev, lblShowTeamDev,
			lblSetTeamMak, lblSetTeamMar, lblShowTeamMar;
	
	private int curMoney, wage, income, fin_date;
	private double notWage, confidence;
	private JLabel lblNewLabel;
	
	// 전주 재정보고서 변수들
	// *************************************************************
	// 계산용 멤버 변수
	public int compManOA = 0;
	public int compCalOA = 0;
	public int compSelOA = 0;
	public int compMakOA = 0;
	public int compDevOA = 0;
	public int compMarOA = 0;
	
	// *************************************************************
	
	// NEW 정산용 변수
	private double exCompWage, realCompWage, exIncome, realIncome;
	private JLabel lblSetTeamCal;
	private JLabel lblShowTeamSel;
	private JLabel lblSetTeamSel;
		
	
	
	public FinanceGui() {
		initialize();
	}

	
	private void initialize() {
		fdao = FinanceDAOImple.getInstance();
		gn = GoNext.getInstance();
		
		panel = new JPanel();
		panel.setBounds(130, 84, 414, 354);
//		add(panel);
		panel.setLayout(null);
		
		Font smallFont = new Font("굴림체", Font.PLAIN, 16);
		
		// 현재 잔액
		lblInfoCurrent = new JLabel("현재 잔액");
		lblInfoCurrent.setBounds(12, 57, 126, 27);
		lblInfoCurrent.setFont(new Font("굴림체", Font.BOLD, 13));
		panel.add(lblInfoCurrent);
		// 출력용
		lblSetCurrent = new JLabel("New label");
		lblSetCurrent.setBounds(150, 57, 126, 27);
		lblSetCurrent.setFont(new Font("굴림체", Font.PLAIN, 13));
		panel.add(lblSetCurrent);
		
		// 수입
		lblNewLabel_2 = new JLabel("수입");
		lblNewLabel_2.setBounds(12, 94, 126, 27);
		lblNewLabel_2.setFont(new Font("굴림체", Font.BOLD, 13));
		panel.add(lblNewLabel_2);
		// 출력용
		lblSetIncome = new JLabel("New label");
		lblSetIncome.setBounds(150, 94, 126, 27);
		lblSetIncome.setFont(new Font("굴림체", Font.PLAIN, 13));
		panel.add(lblSetIncome);
		
		// 급료
		lblNewLabel_4 = new JLabel("급료");
		lblNewLabel_4.setBounds(12, 131, 126, 27);
		lblNewLabel_4.setFont(new Font("굴림체", Font.BOLD, 13));
		panel.add(lblNewLabel_4);
		// 출력용
		lblSetWage = new JLabel("New label");
		lblSetWage.setBounds(150, 131, 136, 27);
		lblSetWage.setFont(new Font("굴림체", Font.PLAIN, 13));
		panel.add(lblSetWage);
		
		
		lblNewLabel = new JLabel("재정 보고서");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel.setBounds(12, 10, 210, 37);
		panel.add(lblNewLabel);
		
		// 예상실적
		lblShowLastDid = new JLabel("\uC2E4\uC801 >");
		lblShowLastDid.setBounds(28, 180, 84, 27);
		lblShowLastDid.setFont(new Font("굴림체", Font.BOLD, 13));
		panel.add(lblShowLastDid);
		
		lblShowTeamMan = new JLabel("인사부");
		lblShowTeamMan.setBounds(44, 208, 94, 27);
		lblShowTeamMan.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		panel.add(lblShowTeamMan);
		// 출력용
		lblSetTeamMan = new JLabel("00");
		lblSetTeamMan.setBounds(150, 208, 49, 27);
		lblSetTeamMan.setFont(new Font("굴림체", Font.PLAIN, 13));
		panel.add(lblSetTeamMan);
		
		lblShowTeamCal = new JLabel("총무부");
		lblShowTeamCal.setBounds(44, 245, 94, 27);
		lblShowTeamCal.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		panel.add(lblShowTeamCal);
		// 출력용
		lblSetTeamCal = new JLabel("00");
		lblSetTeamCal.setFont(new Font("굴림", Font.PLAIN, 13));
		lblSetTeamCal.setBounds(150, 245, 49, 27);
		panel.add(lblSetTeamCal);
		
		lblShowTeamSel = new JLabel("영업부");
		lblShowTeamSel.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		lblShowTeamSel.setBounds(44, 282, 94, 27);
		panel.add(lblShowTeamSel);
		
		lblSetTeamSel = new JLabel("00");
		lblSetTeamSel.setFont(new Font("굴림", Font.PLAIN, 13));
		lblSetTeamSel.setBounds(150, 282, 49, 27);
		panel.add(lblSetTeamSel);
		
		lblShowTeamMak = new JLabel("생산관리팀");
		lblShowTeamMak.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		lblShowTeamMak.setBounds(211, 208, 94, 27);
		panel.add(lblShowTeamMak);
		
		lblSetTeamMak = new JLabel("00");
		lblSetTeamMak.setFont(new Font("굴림", Font.PLAIN, 13));
		lblSetTeamMak.setBounds(317, 208, 49, 27);
		panel.add(lblSetTeamMak);
		
		lblShowTeamDev = new JLabel("연구개발팀");
		lblShowTeamDev.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		lblShowTeamDev.setBounds(211, 245, 75, 27);
		panel.add(lblShowTeamDev);
		
		lblSetTeamDev = new JLabel("00");
		lblSetTeamDev.setFont(new Font("굴림", Font.PLAIN, 13));
		lblSetTeamDev.setBounds(317, 245, 49, 27);
		panel.add(lblSetTeamDev);
		
		lblShowTeamMar = new JLabel("마케팅팀");
		lblShowTeamMar.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 14));
		lblShowTeamMar.setBounds(211, 285, 75, 21);
		panel.add(lblShowTeamMar);
		
		lblSetTeamMar = new JLabel("00");
		lblSetTeamMar.setFont(new Font("굴림", Font.PLAIN, 13));
		lblSetTeamMar.setBounds(317, 285, 57, 21);
		panel.add(lblSetTeamMar);
		
		importFinanceInfo();
		System.out.println("FinanceGui가 실행중입니다.");
		
	}
	
	// 재정 상태 import용 메소드
	public void importFinanceInfo() { 
		System.out.println(" sys.MainFrame.importFinanceInfo()");
		FinanceVO vo = fdao.select(1);
		System.out.println(" sys.MainFrame.importFinanceInfo() 현재 잔액 : " + vo.getCurMoney());
		curMoney = vo.getCurMoney();
		wage = vo.getWage();
		notWage = vo.getNotWage();
		income = vo.getIncome();
		confidence = vo.getConfidence();
		fin_date = vo.getFin_date();
		
		printFinanceInfo();
	}
	
	// 재정 상태 단순 출력 메소드
	public void printFinanceInfo() {
		if ((curMoney / 1000) > 10) {
			lblSetCurrent.setText((curMoney / 10000) + " 억 " + (curMoney % 10000) + "만원");
		} else { 
			lblSetCurrent.setText(curMoney + "만원"); // 현재잔고 		
		}
		lblSetWage.setText(wage + "만원"); // 급여
		lblSetIncome.setText(income + "만원"); // 수입
		
		// 실적
		compMakOA = gn.compMakOA;
		compManOA = gn.compManOA;
		compCalOA = gn.compCalOA;
		compSelOA = gn.compSelOA;
		compDevOA = gn.compDevOA;
		compMarOA = gn.compMarOA;
		
		lblSetTeamCal.setText(compCalOA + "");
		lblSetTeamMan.setText(compManOA + "");
		lblSetTeamDev.setText(compDevOA + "");
		lblSetTeamMak.setText(compMakOA + "");
		lblSetTeamSel.setText(compSelOA + "");
		lblSetTeamMar.setText(compMarOA + "");
		
		
		String setDate = (2020 + (fin_date / 54 )) + "년 " + ((fin_date / 5) % 12 + 1) + "월" + ((fin_date % 5) + 1) + "번째 주";
	}
}
