package proj.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import proj.finance.mvc.FinanceDAO;
import proj.finance.mvc.FinanceDAOImple;
import proj.finance.mvc.FinanceVO;
import java.awt.Font;

public class PoliticGui extends JPanel {

//	private static PoliticGui instance = null;
//	
//	public static PoliticGui getInstance() {
//		if (instance == null) {
//			instance = new PoliticGui();
//		}
//		return instance;
//	}
	// **************************************************************
	public JPanel panel;
	private JLabel lblPolitical;
	private JButton btnDecrease, btnIncrease;
	private JLabel lblShowCurrentWage, lblSetCurrentWage, lblShowChangedWage
		, lblSetChangedWage, lblShowCurrentConfi, lblSetCurrentConfi
		, lblShowChangedConfi, lblSetChangedConfi;
	private JButton btnDaccord;
	// ...............................................................
	public static FinanceDAO fdao;
	public FinanceVO vo;
	public double notwage;
	public double changeConfi;
	private int curMoney, wage, income, fin_date;
	private int check;
	// ..............................................................
	public PoliticGui() {
		
		initialize();
	}

	private void initialize() {
		fdao = FinanceDAOImple.getInstance();
		setLayout(null);
		check = 0;
		
		panel = new JPanel();
		panel.setBounds(130, 84, 414, 354);
//		add(panel);
		panel.setLayout(null);
		
		lblPolitical = new JLabel();
		lblPolitical.setFont(new Font("����", Font.PLAIN, 17));
		lblPolitical.setBounds(35, 33, 112, 31);
		lblPolitical.setText("\uC0AC\uB0B4 \uC815\uCC45 >");
		panel.add(lblPolitical);
		
		// wage�� 10% �ø���, confidence�� 0.1��ŭ �ø���
		btnIncrease = new JButton(">>>");
		btnIncrease.setBounds(271, 91, 112, 52);
		btnIncrease.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 1. FinanceDAOImple > (wage)import
				// 2. wage���� ����
				if (changeConfi <= 0.8) {
					notwage += 0.3;
					changeConfi += 0.1;
					lblSetChangedConfi.setText((Math.round(changeConfi*100)/100.0) + "");
					lblSetChangedWage.setText((Math.round(notwage*100)/100.0) + "");
					btnDaccord.setEnabled(true);
					check = 1;
					btnDaccord.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if (check == 1) {
								setWage();
								JOptionPane.showMessageDialog(null, "���������� ����Ǿ����ϴ�");
								btnDaccord.setEnabled(false);
								
								lblSetCurrentConfi.setText((Math.round(changeConfi*100)/100.0) + "");
								lblSetChangedConfi.setText("");
								lblSetCurrentWage.setText((Math.round(notwage*100)/100.0) + "");
								lblSetChangedWage.setText("");
								check = 0;
							}
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "�� ������ �� �����ϴ�!");
					btnIncrease.setEnabled(true);
					btnDaccord.setEnabled(false);
					check = 0;
				}
				
			}
		});
		btnIncrease.setContentAreaFilled(false);
		panel.add(btnIncrease);

		btnDecrease = new JButton("<<<");
		btnDecrease.setBounds(35, 91, 112, 52);
		btnDecrease.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (changeConfi >= 0.3) {
					notwage -= 0.3;
					changeConfi -= 0.1;
					lblSetChangedConfi.setText((Math.round(changeConfi*100)/100.0) + "");
					lblSetChangedWage.setText((Math.round(notwage*100)/100.0) + "");
					btnDaccord.setEnabled(true);
					check = 1;
					// Ȯ�� ��ư�� ������ ��.
					btnDaccord.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if (check == 1) {
								setWage();
								JOptionPane.showMessageDialog(null, "���������� ����Ǿ����ϴ�");
								btnDaccord.setEnabled(false);
								lblSetCurrentConfi.setText((Math.round(changeConfi*100)/100.0) + "");
								lblSetChangedConfi.setText("");
								lblSetCurrentWage.setText((Math.round(notwage*100)/100.0) + "");
								lblSetChangedWage.setText("");
								check = 0;
							}
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "�� ������ �� �����ϴ�!");
					btnIncrease.setEnabled(true);
					btnDaccord.setEnabled(false);
					check = 0;
				}
				// 3. wage�� ���ο� �� ����
				
			}
		});
		btnDecrease.setContentAreaFilled(false);
		panel.add(btnDecrease);
		
		// ------------------------------------------------------
		lblShowCurrentWage = new JLabel("���� �޷�");
		lblShowCurrentWage.setFont(new Font("����", Font.PLAIN, 14));
		lblShowCurrentWage.setBounds(35, 164, 80, 37);
		panel.add(lblShowCurrentWage);
		
		lblSetCurrentWage = new JLabel();
		lblSetCurrentWage.setFont(new Font("����", Font.PLAIN, 14));
		lblSetCurrentWage.setBounds(147, 175, 57, 15);
		panel.add(lblSetCurrentWage);
		
		lblShowChangedWage = new JLabel(">>>");
		lblShowChangedWage.setFont(new Font("����", Font.PLAIN, 14));
		lblShowChangedWage.setBounds(243, 175, 57, 15);
		panel.add(lblShowChangedWage);
		
		lblSetChangedWage = new JLabel();
		lblSetChangedWage.setFont(new Font("����", Font.BOLD, 14));
		lblSetChangedWage.setBounds(326, 175, 57, 15);
		panel.add(lblSetChangedWage);
		
		lblShowCurrentConfi = new JLabel("���");
		lblShowCurrentConfi.setFont(new Font("����", Font.PLAIN, 14));
		lblShowCurrentConfi.setBounds(35, 219, 80, 32);
		panel.add(lblShowCurrentConfi);
		
		lblSetCurrentConfi = new JLabel();
		lblSetCurrentConfi.setFont(new Font("����", Font.PLAIN, 14));
		lblSetCurrentConfi.setBounds(147, 228, 57, 15);
		panel.add(lblSetCurrentConfi);
		
		lblShowChangedConfi = new JLabel(">>>");
		lblShowChangedConfi.setFont(new Font("����", Font.PLAIN, 14));
		lblShowChangedConfi.setBounds(243, 228, 57, 15);
		panel.add(lblShowChangedConfi);
		
		lblSetChangedConfi = new JLabel();
		lblSetChangedConfi.setFont(new Font("����", Font.BOLD, 14));
		lblSetChangedConfi.setBounds(326, 228, 57, 15);
		panel.add(lblSetChangedConfi);
		
		btnDaccord = new JButton("Agree");
		btnDaccord.setFont(new Font("����", Font.PLAIN, 16));
		btnDaccord.setBounds(290, 273, 112, 52);
		btnDaccord.setEnabled(false);
		btnDaccord.setContentAreaFilled(false);
		panel.add(btnDaccord);
		
		
		importWage();
	}
	
	// wage�� import�ؿ��� �޼ҵ�
	public void importWage() {
		FinanceVO vo = new FinanceVO(); 
		vo = fdao.select(1);
		notwage = vo.getNotWage();
		lblSetCurrentWage.setText((Math.round(notwage*100)/100.0) + "");
		changeConfi = vo.getConfidence();
		lblSetCurrentConfi.setText((Math.round(changeConfi*100)/100.0) + "");
		
		curMoney = vo.getCurMoney();
		wage = vo.getWage();
		fin_date = vo.getFin_date();
		income = vo.getIncome();
		
		if (changeConfi >= 0.8) {
			btnIncrease.setEnabled(false);
		} else if (changeConfi <= 0.3) {
			btnDecrease.setEnabled(false);
		}
		
		this.vo = vo;
	}
	
	// wage�� �����Ű�� �޼ҵ�
	public void setWage() {
		vo.setNotWage(notwage);
		vo.setConfidence(changeConfi);
		vo.setCurMoney(curMoney);
		vo.setFin_date(fin_date);
		vo.setIncome(income);
		vo.setWage(wage);
		
		int result = fdao.insert(vo);
		
		if (result == 1) {
			System.out.println("vo�� notwage, confidence ���� ����");
		}
	}
}













