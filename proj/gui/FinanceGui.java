package proj.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import proj.finance.mvc.FinanceDAO;
import proj.finance.mvc.FinanceDAOImple;
import proj.finance.mvc.FinanceVO;

public class FinanceGui extends JPanel {

	private static FinanceDAO fdao;
	private static GoNext gn;
	
	public JPanel panel;
	
	private JLabel lblInfoCurrent, lblSetCurrent, lblShowIncome,
			lblSetIncome, lblShowWage, lblSetWage, lblShowLastDid,
			lblShowTeamMan, lblShowTeamCal, lblSetTeamMan, lblShowTeamMak, lblSetTeamDev, lblShowTeamDev,
			lblSetTeamMak, lblSetTeamMar, lblShowTeamMar, lblInfoFinReport;
	
	private int curMoney, wage, income;
	
	// ���� �������� ������
	// *************************************************************
	// ���� ��� ����
	public int compManOA = 0;
	public int compCalOA = 0;
	public int compSelOA = 0;
	public int compMakOA = 0;
	public int compDevOA = 0;
	public int compMarOA = 0;
	
	// *************************************************************
	
	// NEW ����� ����
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
		panel.setLayout(null);
		
		
		// ���� �ܾ�
		lblInfoCurrent = new JLabel("���� �ܾ�");
		lblInfoCurrent.setBounds(12, 57, 126, 27);
		lblInfoCurrent.setFont(new Font("����ü", Font.BOLD, 13));
		panel.add(lblInfoCurrent);
		// ��¿�
		lblSetCurrent = new JLabel("New label");
		lblSetCurrent.setBounds(150, 57, 126, 27);
		lblSetCurrent.setFont(new Font("����ü", Font.PLAIN, 13));
		panel.add(lblSetCurrent);
		
		// ����
		lblShowIncome = new JLabel("����");
		lblShowIncome.setBounds(12, 94, 126, 27);
		lblShowIncome.setFont(new Font("����ü", Font.BOLD, 13));
		panel.add(lblShowIncome);
		// ��¿�
		lblSetIncome = new JLabel("New label");
		lblSetIncome.setBounds(150, 94, 126, 27);
		lblSetIncome.setFont(new Font("����ü", Font.PLAIN, 13));
		panel.add(lblSetIncome);
		
		// �޷�
		lblShowWage = new JLabel("�޷�");
		lblShowWage.setBounds(12, 131, 126, 27);
		lblShowWage.setFont(new Font("����ü", Font.BOLD, 13));
		panel.add(lblShowWage);
		// ��¿�
		lblSetWage = new JLabel("New label");
		lblSetWage.setBounds(150, 131, 136, 27);
		lblSetWage.setFont(new Font("����ü", Font.PLAIN, 13));
		panel.add(lblSetWage);
		
		
		lblInfoFinReport = new JLabel("���� ����");
		lblInfoFinReport.setFont(new Font("����", Font.BOLD, 16));
		lblInfoFinReport.setBounds(12, 10, 210, 37);
		panel.add(lblInfoFinReport);
		
		// �������
		lblShowLastDid = new JLabel("\uC2E4\uC801 >");
		lblShowLastDid.setBounds(28, 180, 84, 27);
		lblShowLastDid.setFont(new Font("����ü", Font.BOLD, 13));
		panel.add(lblShowLastDid);
		
		lblShowTeamMan = new JLabel("�λ��");
		lblShowTeamMan.setBounds(44, 208, 94, 27);
		lblShowTeamMan.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
		panel.add(lblShowTeamMan);
		// ��¿�
		lblSetTeamMan = new JLabel("00");
		lblSetTeamMan.setBounds(150, 208, 49, 27);
		lblSetTeamMan.setFont(new Font("����ü", Font.PLAIN, 13));
		panel.add(lblSetTeamMan);
		
		lblShowTeamCal = new JLabel("�ѹ���");
		lblShowTeamCal.setBounds(44, 245, 94, 27);
		lblShowTeamCal.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
		panel.add(lblShowTeamCal);
		// ��¿�
		lblSetTeamCal = new JLabel("00");
		lblSetTeamCal.setFont(new Font("����", Font.PLAIN, 13));
		lblSetTeamCal.setBounds(150, 245, 49, 27);
		panel.add(lblSetTeamCal);
		
		lblShowTeamSel = new JLabel("������");
		lblShowTeamSel.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
		lblShowTeamSel.setBounds(44, 282, 94, 27);
		panel.add(lblShowTeamSel);
		
		lblSetTeamSel = new JLabel("00");
		lblSetTeamSel.setFont(new Font("����", Font.PLAIN, 13));
		lblSetTeamSel.setBounds(150, 282, 49, 27);
		panel.add(lblSetTeamSel);
		
		lblShowTeamMak = new JLabel("���������");
		lblShowTeamMak.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
		lblShowTeamMak.setBounds(211, 208, 94, 27);
		panel.add(lblShowTeamMak);
		
		lblSetTeamMak = new JLabel("00");
		lblSetTeamMak.setFont(new Font("����", Font.PLAIN, 13));
		lblSetTeamMak.setBounds(317, 208, 49, 27);
		panel.add(lblSetTeamMak);
		
		lblShowTeamDev = new JLabel("����������");
		lblShowTeamDev.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
		lblShowTeamDev.setBounds(211, 245, 75, 27);
		panel.add(lblShowTeamDev);
		
		lblSetTeamDev = new JLabel("00");
		lblSetTeamDev.setFont(new Font("����", Font.PLAIN, 13));
		lblSetTeamDev.setBounds(317, 245, 49, 27);
		panel.add(lblSetTeamDev);
		
		lblShowTeamMar = new JLabel("��������");
		lblShowTeamMar.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
		lblShowTeamMar.setBounds(211, 285, 75, 21);
		panel.add(lblShowTeamMar);
		
		lblSetTeamMar = new JLabel("00");
		lblSetTeamMar.setFont(new Font("����", Font.PLAIN, 13));
		lblSetTeamMar.setBounds(317, 285, 57, 21);
		panel.add(lblSetTeamMar);
		
		importFinanceInfo();
		System.out.println("FinanceGui�� �������Դϴ�.");
		
	}
	
	// ���� ���� import�� �޼ҵ�
	public void importFinanceInfo() { 
		System.out.println(" sys.MainFrame.importFinanceInfo()");
		FinanceVO vo = fdao.select(1);
		System.out.println(" sys.MainFrame.importFinanceInfo() ���� �ܾ� : " + vo.getCurMoney());
		curMoney = vo.getCurMoney();
		wage = vo.getWage();
		income = vo.getIncome();
		
		printFinanceInfo();
	}
	
	// ���� ���� �ܼ� ��� �޼ҵ�
	public void printFinanceInfo() {
		if ((curMoney / 1000) > 10) {
			lblSetCurrent.setText((curMoney / 10000) + " �� " + (curMoney % 10000) + "����");
		} else { 
			lblSetCurrent.setText(curMoney + "����"); // �����ܰ� 		
		}
		lblSetWage.setText(wage + "����"); // �޿�
		lblSetIncome.setText(income + "����"); // ����
		
		// ����
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
		
	}
}
