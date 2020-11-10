package proj.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import proj.contact.mvc.ContactDAO;
import proj.contact.mvc.ContactDAOImple;
import proj.finance.mvc.FinanceDAO;
import proj.finance.mvc.FinanceDAOImple;
import proj.finance.mvc.FinanceVO;
import proj.member.mvc.MemberDAO;
import proj.member.mvc.MemberDAOImple;
import proj.member.mvc.MemberVO;

public class MainFrame extends JFrame implements Runnable {
//
	// MainFrame Singleton ����
	private static MainFrame instance = null;

	public static synchronized MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	// *************************************************************

	private Container c;

	private JPanel menuPanel, upperPanel, infoPanel, showPanel;
	private JButton btnHome, btnPolitic, btnSearch, btnShowFinance, btnInfo, btnMemo, btnGoPass;
	private JLabel lblPlayTime;

	public JPanel packagePanel;
	public JButton btnUpgrade, btnDegrade;

	// MVC ------------------------------------------------------------
	private static ContactDAO cdao;
	private static FinanceDAO fdao;
	private static MemberDAO mdao;

//	private static FinanceGraph fg = null;
	public static Calendar cal;
	public static FinanceGui finPanel;
	public static SearchGui sg;
	public static PoliticGui pg;
	public static SearchGui sg1 = null;
	public static GoNext gonext;
	public static InfoGui ig;
	public static Memo memo;
	private static MemberVO vovo;

	// ������Ʈ Ȯ�� ����
	public static Component currentComponent;

	// ����
	private static final String photo = "res/unnamed.png";
	
	// ����, ���� Ȯ�ο� ����
	public static int upgradeRs;

	// ShowMember() ***************************************************

	private JLabel lblMemCompCur;
	private JLabel lblMemId, lblMemDepWant, lblMemName, lblAllInfo, lblMemComp, lblMemDepInfo, lblMemDepCur,
			lblMemGrdInfo, lblMemGrdCur, lblMemCharInfo, lblCharMath, lblMemCharMath, lblCharServ, lblMemCharServ,
			lblCharHand, lblMemCharHand, lblCharIq, lblCharScien, lblMemCharScien, lblMemCharIq;

	
	public JPanel panel;
	
	private JLabel lblInfoOverall, lblMemOverall;
	private JButton btnGetOut, btnTakeHim, btnNegative_Mem, btnPositive_Mem, btnDaccord;

	// ****************************************************************
	public MainFrame() {
		initialize();
	}

	private void initialize() {
		cdao = ContactDAOImple.getInstance();
		fdao = FinanceDAOImple.getInstance();
		mdao = MemberDAOImple.getInstance();
		cal = new Calendar();
		pg = new PoliticGui();
		memo = Memo.getInstance();

		c = getContentPane();
		
		setBounds(550, 250, 560, 477);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);

		// PANELS ---------------------------------------------------
		// �޴��� ���� �ǳ�
		menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 130, 438);
		c.add(menuPanel);
		menuPanel.setLayout(null);

		// ������ ������ �ǳ�
		upperPanel = new JPanel();
		upperPanel.setBounds(130, 0, 414, 83);
		c.add(upperPanel);
		upperPanel.setLayout(null);

		// ������ ������ �ǳ�
		infoPanel = new JPanel();
		infoPanel.setBounds(130, 84, 414, 354);
		c.add(infoPanel);
		infoPanel.setLayout(null);
		infoPanel.setVisible(true);
		currentComponent = infoPanel;

		// ShowMember
		showPanel = new JPanel();
		showPanel.setBounds(130, 84, 414, 354);
//		c.add(showPanel);
		showPanel.setLayout(null);
//		showPanel.setVisible(false);

		
		// -----------------------------------------------------------------

		Font smallFont = new Font("����ü", Font.BOLD, 16);
		
		// ���� �޴� ��
		// Ȩ ��ư
		btnHome = new JButton("Ȩ");
		btnHome.setBounds(12, 77, 106, 40);
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.remove(currentComponent);
				c.revalidate();
				c.repaint();

				cal = new Calendar();
				c.add(infoPanel);
				infoPanel.setLayout(null);
				c.revalidate();
				c.repaint();

				currentComponent = infoPanel;
				showPanel.setVisible(false);
			}
		});
		btnHome.setContentAreaFilled(false);
		btnHome.setFont(smallFont);
		menuPanel.add(btnHome);

		// ��å��ư
		btnPolitic = new JButton("��å");
		btnPolitic.setBounds(12, 127, 106, 40);
		btnPolitic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.remove(currentComponent);
				c.revalidate();
				c.repaint();

				c.add(pg.panel);
				pg.panel.setLayout(null);
				c.revalidate();
				c.repaint();

				currentComponent = pg.panel;
				showPanel.setVisible(false);
			}
		});
		btnPolitic.setContentAreaFilled(false);
		btnPolitic.setFont(smallFont);
		menuPanel.add(btnPolitic);

		// �˻� ��ư
		btnSearch = new JButton("�˻�");
		btnSearch.setBounds(12, 177, 106, 37);
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.remove(currentComponent);
				c.revalidate();
				c.repaint();

				c.add(showPanel);
				sg1 = SearchGui.getInstance(showPanel);
				sg1.setVisible(true);
				
				c.add(showPanel);
				c.revalidate();
				c.repaint();
				showPanel.setVisible(true);

				System.out.println(">>>>>>> ���� ���̴� ��");
				currentComponent = showPanel;
				
//				showPanel.setVisible(true);
			}
		});
		btnSearch.setContentAreaFilled(false);
		btnSearch.setFont(smallFont);
		menuPanel.add(btnSearch);

		// ���� ��ư
		btnShowFinance = new JButton("����");
		btnShowFinance.setBounds(12, 247, 106, 40);
		btnShowFinance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.remove(currentComponent);
				c.revalidate();
				c.repaint();

				finPanel = new FinanceGui();

				c.add(finPanel.panel);
				finPanel.panel.setLayout(null);
				c.revalidate();
				c.repaint();

				currentComponent = finPanel.panel;
				showPanel.setVisible(false);
			}
		});
		btnShowFinance.setContentAreaFilled(false);
		btnShowFinance.setFont(smallFont);
		menuPanel.add(btnShowFinance);

		// �޸�
		btnMemo = new JButton("�޸�");
		btnMemo.setBounds(12, 297, 106, 40);
		btnMemo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.remove(currentComponent);
				c.revalidate();
				c.repaint();
				
				c.add(memo.panel);
				memo.panel.setLayout(null);
				c.revalidate();
				c.repaint();
				
				currentComponent = memo.panel;
			}
		});
		btnMemo.setContentAreaFilled(false);
		btnMemo.setFont(smallFont);
		menuPanel.add(btnMemo);

		// ���� ��ư
		btnInfo = new JButton("����");
		btnInfo.setBounds(12, 347, 106, 40);
		btnInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.remove(currentComponent);
				c.revalidate();
				c.repaint();

				ig = new InfoGui();
				
				c.add(ig.panel);
				ig.panel.setLayout(null);
				c.revalidate();
				c.repaint();

				currentComponent = ig.panel;
				showPanel.setVisible(false);
			}
		});
		btnInfo.setContentAreaFilled(false);
		btnInfo.setFont(smallFont);
		menuPanel.add(btnInfo);

		// --------------------------------------------------------------------
		// ��� PANEL

		// ���� ����
		btnGoPass = new JButton("���� ����");
		btnGoPass.setBounds(287, 10, 115, 62);
		btnGoPass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.remove(currentComponent);
				c.revalidate();
				c.repaint();

				gonext = new GoNext();
				
				c.add(gonext.panel);
				gonext.panel.setLayout(null);
				c.revalidate();
				c.repaint();

				currentComponent = gonext.panel;
				btnGoPass.setEnabled(false);
				btnMemo.setEnabled(false);
				btnHome.setEnabled(false);
				btnInfo.setEnabled(false);
				btnPolitic.setEnabled(false);
				btnSearch.setEnabled(false);
				btnShowFinance.setEnabled(false);
				
				gonext.btnDaccord.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						c.remove(currentComponent);
						c.revalidate();
						c.repaint();

						c.add(infoPanel);
						infoPanel.setLayout(null);
						c.revalidate();
						c.repaint();

						currentComponent = infoPanel;
						showPanel.setVisible(false);
						btnGoPass.setEnabled(true);
						btnMemo.setEnabled(true);
						btnHome.setEnabled(true);
						btnInfo.setEnabled(true);
						btnPolitic.setEnabled(true);
						btnSearch.setEnabled(true);
						btnShowFinance.setEnabled(true);
						cal = new Calendar();
						returnDate();
					}
				});
			}
		});
		btnGoPass.setContentAreaFilled(false);
		upperPanel.add(btnGoPass);

		// playtime set
		lblPlayTime = new JLabel();
		lblPlayTime.setText("1");
		lblPlayTime.setBounds(135, 28, 120, 15);
		upperPanel.add(lblPlayTime);
		returnDate();

		// �̴��� ���
		btnPositive_Mem = new JButton("���� ������ ���Ҵ� �μ�");
		btnPositive_Mem.setBounds(221, 167, 181, 82);
		infoPanel.add(btnPositive_Mem);

		// ���� ����
		btnNegative_Mem = new JButton("Negative Mem");
		btnNegative_Mem.setBounds(221, 259, 181, 82);
		infoPanel.add(btnNegative_Mem);

		// ������ �̺�Ʈ
		JLabel mainPhoto = new JLabel();
		mainPhoto.setBounds(12, 10, 382, 125);
		mainPhoto.setIcon(new ImageIcon(photo));
		infoPanel.add(mainPhoto);

		// �޷� �ڸ�
		cal.setVisible(true);
		cal.setBounds(12, 141, 200, 210);
		infoPanel.add(cal);

		
		// *************************************************
		// ShowMemberGui
		
		Font name = new Font("����", Font.BOLD, 33);

		lblMemId = new JLabel(""); // 0
		lblMemId.setFont(new Font("����", Font.BOLD, 27));
		lblMemId.setBounds(12, 10, 79, 30);
		showPanel.add(lblMemId);

		lblMemDepWant = new JLabel(""); // 1
		lblMemDepWant.setFont(name);
		lblMemDepWant.setBounds(103, 10, 116, 60);
		showPanel.add(lblMemDepWant);

		lblMemName = new JLabel(); // 2
		lblMemName.setFont(name);
		lblMemName.setBounds(231, 10, 159, 60);
		showPanel.add(lblMemName);

		Font Plain15 = new Font("����", Font.PLAIN, 15);

		lblAllInfo = new JLabel("������ >"); 
		lblAllInfo.setBounds(12, 49, 77, 21);
		showPanel.add(lblAllInfo);

		lblMemComp = new JLabel("�Ҽ�");
		lblMemComp.setFont(new Font("����", Font.BOLD, 15));
		lblMemComp.setBounds(12, 80, 57, 21);
		showPanel.add(lblMemComp);

		lblMemCompCur = new JLabel("ȸ���"); // 5
		lblMemCompCur.setFont(new Font("����", Font.PLAIN, 12));
		lblMemCompCur.setBounds(79, 83, 77, 15);
		showPanel.add(lblMemCompCur);

		lblMemDepInfo = new JLabel("�μ�");
		lblMemDepInfo.setFont(new Font("����", Font.BOLD, 15));
		lblMemDepInfo.setBounds(12, 111, 57, 21);
		showPanel.add(lblMemDepInfo);

		lblMemDepCur = new JLabel("�μ���"); // 7
		lblMemDepCur.setFont(new Font("����", Font.PLAIN, 12));
		lblMemDepCur.setBounds(79, 114, 88, 15);
		showPanel.add(lblMemDepCur);

		lblMemGrdInfo = new JLabel("����");
		lblMemGrdInfo.setFont(new Font("����", Font.BOLD, 15));
		lblMemGrdInfo.setBounds(12, 142, 57, 21);
		showPanel.add(lblMemGrdInfo);

		lblMemGrdCur = new JLabel("���޸�"); // 9
		lblMemGrdCur.setFont(new Font("����", Font.PLAIN, 12));
		lblMemGrdCur.setBounds(79, 145, 79, 15);
		showPanel.add(lblMemGrdCur);
		
		// ---------------------------------------
		// ShowPanel�� ������Ʈ��
		Font FontShow = new Font("����", Font.BOLD, 22);

		lblMemCharInfo = new JLabel("�ɷ�ġ >");
		lblMemCharInfo.setFont(new Font("����", Font.BOLD, 13));
		lblMemCharInfo.setBounds(12, 184, 57, 15);
		showPanel.add(lblMemCharInfo);
		// Math
		lblCharMath = new JLabel("���"); 
		lblCharMath.setFont(new Font("����", Font.BOLD | Font.ITALIC, 15));
		lblCharMath.setBounds(38, 209, 77, 30);
		showPanel.add(lblCharMath);

		lblMemCharMath = new JLabel(""); // 12
		lblMemCharMath.setFont(FontShow);
		lblMemCharMath.setBounds(143, 209, 57, 30);
		showPanel.add(lblMemCharMath);
		// ����
		lblCharServ = new JLabel("����");
		lblCharServ.setFont(new Font("����", Font.BOLD | Font.ITALIC, 15));
		lblCharServ.setBounds(212, 209, 88, 30);
		showPanel.add(lblCharServ);

		lblMemCharServ = new JLabel(""); // 14
		lblMemCharServ.setFont(FontShow);
		lblMemCharServ.setBounds(333, 209, 57, 30);
		showPanel.add(lblMemCharServ);
		// ������
		lblCharHand = new JLabel("������");
		lblCharHand.setFont(new Font("����", Font.BOLD | Font.ITALIC, 15));
		lblCharHand.setBounds(38, 249, 77, 30);
		showPanel.add(lblCharHand);

		lblMemCharHand = new JLabel(""); // 16
		lblMemCharHand.setFont(FontShow);
		lblMemCharHand.setBounds(143, 249, 57, 30);
		showPanel.add(lblMemCharHand);
		// IQ
		lblCharIq = new JLabel("����");
		lblCharIq.setFont(new Font("����", Font.BOLD | Font.ITALIC, 15));
		lblCharIq.setBounds(212, 249, 88, 30);
		showPanel.add(lblCharIq);

		lblMemCharIq = new JLabel(""); // 18
		lblMemCharIq.setFont(FontShow);
		lblMemCharIq.setBounds(333, 249, 57, 30);
		showPanel.add(lblMemCharIq);
		// ����
		lblCharScien = new JLabel("����");
		lblCharScien.setFont(new Font("����", Font.BOLD | Font.ITALIC, 15));
		lblCharScien.setBounds(38, 289, 77, 30);
		showPanel.add(lblCharScien);

		lblMemCharScien = new JLabel(); // 20
		lblMemCharScien.setFont(FontShow);
		lblMemCharScien.setBounds(143, 289, 57, 30);
		showPanel.add(lblMemCharScien);
		
		lblInfoOverall = new JLabel("OVERALL");
		lblInfoOverall.setFont(new Font("����", Font.BOLD | Font.ITALIC, 12));
		lblInfoOverall.setBounds(223, 114, 67, 15);
		showPanel.add(lblInfoOverall);
		
		lblMemOverall = new JLabel("");
		lblMemOverall.setFont(new Font("����", Font.BOLD | Font.ITALIC, 33));
		lblMemOverall.setBounds(302, 86, 88, 46);
		showPanel.add(lblMemOverall);
		
		btnGetOut = new JButton("��������"); // 23
		btnGetOut.setBounds(305, 321, 97, 23);
		showPanel.add(btnGetOut);
		btnGetOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int memCid = Integer.parseInt(lblMemId.getText());
				MemberVO vo = mdao.select(memCid);
				int grd = vo.getPs_grd();
				int overall = (vo.getChar_hand() + vo.getChar_iq() + vo.getChar_math()+
						vo.getChar_scien() + vo.getChar_serv()) / 5;
				int byeMoney = overall * 100 * (7 - grd);
				
				/* �� �˸�â >
				 * ������  -> wage * 10  > YES or NO */
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "������ " + byeMoney + "�� �䱸�մϴ�."
						+ "\n��� ó�� �Ͻðڽ��ϱ�?");
				// (result ���ϰ�)
				// JOptionPane.YES_OPTION, JOptionPane.NO_OPTION,
				// JOptionPane.CANCEL_OPTION ��
				
				if (result == 0) {
					vo.setPs_comp(5); // 5 > FA
					int resultUpdate = mdao.update(vo.getPs_id(), vo);
					
					FinanceVO vo2 = fdao.select(0);
					int curMoney = vo2.getCurMoney();
					int changedMon = curMoney - byeMoney;
					vo2.setCurMoney(changedMon);
					int result1 = fdao.insert(vo2);
					if (resultUpdate == 1) {
						System.out.println("���ó���� ���������� �Ǿ����ϴ�.");
					}
					if (result1 == 1) {
						System.out.println("�ܰ� ���������� ����Ǿ����ϴ�.");
					}
					/* FA�� ������ �Ǿ����ϴ�. */
					if (grd == 5 || grd == 6) {
						JOptionPane.showMessageDialog(null, vo.getPs_name() + " ��(��) ���� �ϸ� �������ϴ�. " 
								+ "\n�ܰ� " + changedMon + "�� �ٲ�����ϴ�.");
					}
					else {						
						JOptionPane.showMessageDialog(null, vo.getPs_name() + " ��(��) �뷡�� �θ��� �������ϴ�. " 
								+ "\n�ܰ� " + changedMon + "�� �ٲ�����ϴ�.");
					}
					sg1.dispose();
					sg1 = SearchGui.getInstance(showPanel);
					sg1.updateJTable();
					btnGetOut.setEnabled(false);
					c.remove(currentComponent);
					c.validate();
					c.repaint();
				}
			}
		});
		btnGetOut.setContentAreaFilled(false);
		btnGetOut.setEnabled(false);
		
		btnTakeHim = new JButton("����"); // 24
		btnTakeHim.setBounds(305, 288, 97, 23);
		showPanel.add(btnTakeHim);
		btnTakeHim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int memCid = Integer.parseInt(lblMemId.getText());
				MemberVO vo = mdao.select(memCid);
				ArrayList<MemberVO> list = mdao.select();
				int alloverall = 0;
				int nameValue = 0;
				
				
				for (int i = 0; i < list.size(); i++) {
					int overall = (vo.getChar_hand() + vo.getChar_iq() + vo.getChar_math()+
							vo.getChar_scien() + vo.getChar_serv()) / 5 + 1; // ���� overall���� ���� �� ���� å��
					alloverall += overall;
				}
				nameValue = alloverall / list.size();
				
				int overall = (vo.getChar_hand() + vo.getChar_iq() + vo.getChar_math()+
						vo.getChar_scien() + vo.getChar_serv()) / 5;
				
				if (nameValue < overall) {
					/* ���� ������ �ʴ´ٴ� ���� */
					JOptionPane.showMessageDialog(null, vo.getPs_name() 
							+ "��(��) �� ȸ��� ������ ���ٰ� �����մϴ�.");
				} else {
					vo.setPs_comp(1);
					int resultUpdate = mdao.update(vo.getPs_id(), vo);
					if (resultUpdate == 1) {
						System.out.println("�Ի簡 ���������� ó��");
					}
					JOptionPane.showMessageDialog(null, vo.getPs_name()
							+ "��(��) �츮ȸ��� �Ի��Ͽ����ϴ�! ");
					btnGetOut.setEnabled(true);
					
					btnTakeHim.setEnabled(false);
					/* vo.getPs_name �̰� ���� ȸ��� ���Դٴ� ���� */
					
					sg1.dispose();
					sg1.updateJTable();
					sg1 = SearchGui.getInstance(showPanel);

					c.remove(currentComponent);
					c.validate();
					c.repaint();
					
				}
				
				
			}
		});
		btnTakeHim.setContentAreaFilled(false);
		btnTakeHim.setEnabled(false);
		
		calGoodDep();
		btnPositive_Mem.setContentAreaFilled(false);
		btnNegative_Mem.setContentAreaFilled(false);
		
		// ����, ���� �ɻ� > button 3�� (���� / ���� / Ȯ��)
		
		btnUpgrade = new JButton("UP");          // 25
		btnUpgrade.setBounds(143, 141, 67, 23);
		showPanel.add(btnUpgrade);
		btnUpgrade.addActionListener(new ActionListener() {
			
			@Override // ����
			public void actionPerformed(ActionEvent e) {
				int memCid = Integer.parseInt(lblMemId.getText());
				vovo = new MemberVO();
				vovo = mdao.select(memCid);
				
				upgradeRs = vovo.getPs_grd();
				upgradeRs--;
				btnUpgrade.setEnabled(false);
				btnDegrade.setEnabled(false);
				btnDaccord.setEnabled(true);
			}
		});
		btnUpgrade.setEnabled(false);
		
		btnDegrade = new JButton("DWN");
		btnDegrade.setBounds(143, 163, 67, 23);
		btnDegrade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int memCid = Integer.parseInt(lblMemId.getText());
				vovo = new MemberVO();
				vovo = mdao.select(memCid);
				
				upgradeRs = vovo.getPs_grd();
				upgradeRs++;
				btnDegrade.setEnabled(false);
				btnUpgrade.setEnabled(false);
				btnDaccord.setEnabled(true);
			}
		});
		showPanel.add(btnDegrade);
		btnDegrade.setEnabled(false);
		
		btnDaccord = new JButton("OK");
		btnDaccord.setBounds(212, 142, 57, 44);
		btnDaccord.addActionListener(new ActionListener() {
			
			@Override // OK
			public void actionPerformed(ActionEvent e) {
				int memCid = Integer.parseInt(lblMemId.getText());
				
				vovo = mdao.select(memCid);
				
				vovo.setPs_grd(upgradeRs);
				int result = mdao.updateGrd(memCid, vovo);
				if (result == 1) { // ��� ����
					JOptionPane.showMessageDialog(null, "���������� ó���Ǿ����ϴ�.");
					
					sg1.dispose();
					sg1.updateJTable();
					sg1 = SearchGui.getInstance(showPanel);

					c.remove(currentComponent);
					c.validate();
					c.repaint();
					upgradeRs = 0;
				}
				btnDaccord.setEnabled(false);
			}
		});
		showPanel.add(btnDaccord);
		btnDaccord.setEnabled(false);
		btnUpgrade.setContentAreaFilled(false);
		btnDegrade.setContentAreaFilled(false);
		btnDaccord.setContentAreaFilled(false);
		
	}

	// ID(������) ��ȯ �޼ҵ�
	public String returnID() {
		String id = cdao.returnID(1);
		return id;
	}
	
	// Fin_date�� ������ �޼ҵ�
	public void returnDate() {
		int result = 0;
		FinanceVO vo = null;
		
		vo = fdao.select(0);
		result = vo.getFin_date();
		
		String str = (2020 + (result / 54)) + "��  " + ((result / 5) % 12 + 1) + "�� " + ((result % 5) + 1) + "��° ��";
		lblPlayTime.setText(str);
	}
	
	// �ְ� ������ ����ϴ� �޼ҵ�
	public void calGoodDep() {
		gonext = GoNext.getInstance();
		
		int mak = gonext.compMakOA;
		int cal = gonext.compCalOA;
		int sel = gonext.compSelOA;
		int man = gonext.compManOA;
		int dev = gonext.compDevOA;
		int mar = gonext.compMarOA;
		int max = 0;
		int min = Integer.MAX_VALUE;
		int[] arr = {mak, cal, sel, man, dev, mar};
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		
		if (max == mak) {
			btnPositive_Mem.setText("������ ���� - �������");
		} else if (max == cal) {
			btnPositive_Mem.setText("������ ���� - �ѹ�");
		} else if (max == sel) {
			btnPositive_Mem.setText("������ ���� - ����");
		} else if (max == man) {
			btnPositive_Mem.setText("������ ���� - �λ�");
		} else if (max == dev){
			btnPositive_Mem.setText("������ ���� - ��������");
		} else if (max == mar){
			btnPositive_Mem.setText("������ ���� - ������");
		}
		
		if (min == mak) {
			btnNegative_Mem.setText("������ ���� - �������");
		} else if (min == cal) {
			btnNegative_Mem.setText("������ ���� - �ѹ�");
		} else if (min == sel) {
			btnNegative_Mem.setText("������ ���� - ����");
		} else if (min == man) {
			btnNegative_Mem.setText("������ ���� - �λ�");
		} else if (min == dev){
			btnNegative_Mem.setText("������ ���� - ��������");
		} else {
			btnNegative_Mem.setText("������ ���� - ������");
		}
		
	}
	// infoGui �ٽ� �����Ű�� �޼ҵ�
	public void reStartInfoGui() {
		c.remove(currentComponent);
		c.revalidate();
		c.repaint();

		ig = new InfoGui();
		
		c.add(ig.panel);
		ig.panel.setLayout(null);
		c.revalidate();
		c.repaint();

		currentComponent = ig.panel;
		showPanel.setVisible(false);
	}


	@Override
	public void run() {
		synchronized (this) {
			notify();
		}
	}
}
