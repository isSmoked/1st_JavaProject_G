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
	// MainFrame Singleton 선언
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

	// 컴포넌트 확인 변수
	public static Component currentComponent;

	// 사진
	private static final String photo = "res/unnamed.png";
	
	// 진급, 강등 확인용 변수
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
		// 메뉴를 넣을 판넬
		menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 130, 438);
		c.add(menuPanel);
		menuPanel.setLayout(null);

		// 일정을 진행할 판넬
		upperPanel = new JPanel();
		upperPanel.setBounds(130, 0, 414, 83);
		c.add(upperPanel);
		upperPanel.setLayout(null);

		// 정보를 보여줄 판넬
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

		Font smallFont = new Font("굴림체", Font.BOLD, 16);
		
		// 좌측 메뉴 바
		// 홈 버튼
		btnHome = new JButton("홈");
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

		// 정책버튼
		btnPolitic = new JButton("정책");
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

		// 검색 버튼
		btnSearch = new JButton("검색");
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

				System.out.println(">>>>>>> 새로 붙이는 중");
				currentComponent = showPanel;
				
//				showPanel.setVisible(true);
			}
		});
		btnSearch.setContentAreaFilled(false);
		btnSearch.setFont(smallFont);
		menuPanel.add(btnSearch);

		// 재정 버튼
		btnShowFinance = new JButton("재정");
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

		// 메모
		btnMemo = new JButton("메모");
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

		// 정보 버튼
		btnInfo = new JButton("정보");
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
		// 상단 PANEL

		// 일정 진행
		btnGoPass = new JButton("일정 진행");
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

		// 이달의 사원
		btnPositive_Mem = new JButton("전주 성과가 좋았던 부서");
		btnPositive_Mem.setBounds(221, 167, 181, 82);
		infoPanel.add(btnPositive_Mem);

		// 최하 실적
		btnNegative_Mem = new JButton("Negative Mem");
		btnNegative_Mem.setBounds(221, 259, 181, 82);
		infoPanel.add(btnNegative_Mem);

		// 다음의 이벤트
		JLabel mainPhoto = new JLabel();
		mainPhoto.setBounds(12, 10, 382, 125);
		mainPhoto.setIcon(new ImageIcon(photo));
		infoPanel.add(mainPhoto);

		// 달력 자리
		cal.setVisible(true);
		cal.setBounds(12, 141, 200, 210);
		infoPanel.add(cal);

		
		// *************************************************
		// ShowMemberGui
		
		Font name = new Font("굴림", Font.BOLD, 33);

		lblMemId = new JLabel(""); // 0
		lblMemId.setFont(new Font("굴림", Font.BOLD, 27));
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

		Font Plain15 = new Font("굴림", Font.PLAIN, 15);

		lblAllInfo = new JLabel("상세정보 >"); 
		lblAllInfo.setBounds(12, 49, 77, 21);
		showPanel.add(lblAllInfo);

		lblMemComp = new JLabel("소속");
		lblMemComp.setFont(new Font("굴림", Font.BOLD, 15));
		lblMemComp.setBounds(12, 80, 57, 21);
		showPanel.add(lblMemComp);

		lblMemCompCur = new JLabel("회사명"); // 5
		lblMemCompCur.setFont(new Font("굴림", Font.PLAIN, 12));
		lblMemCompCur.setBounds(79, 83, 77, 15);
		showPanel.add(lblMemCompCur);

		lblMemDepInfo = new JLabel("부서");
		lblMemDepInfo.setFont(new Font("굴림", Font.BOLD, 15));
		lblMemDepInfo.setBounds(12, 111, 57, 21);
		showPanel.add(lblMemDepInfo);

		lblMemDepCur = new JLabel("부서명"); // 7
		lblMemDepCur.setFont(new Font("굴림", Font.PLAIN, 12));
		lblMemDepCur.setBounds(79, 114, 88, 15);
		showPanel.add(lblMemDepCur);

		lblMemGrdInfo = new JLabel("직급");
		lblMemGrdInfo.setFont(new Font("굴림", Font.BOLD, 15));
		lblMemGrdInfo.setBounds(12, 142, 57, 21);
		showPanel.add(lblMemGrdInfo);

		lblMemGrdCur = new JLabel("직급명"); // 9
		lblMemGrdCur.setFont(new Font("굴림", Font.PLAIN, 12));
		lblMemGrdCur.setBounds(79, 145, 79, 15);
		showPanel.add(lblMemGrdCur);
		
		// ---------------------------------------
		// ShowPanel의 컴포넌트들
		Font FontShow = new Font("굴림", Font.BOLD, 22);

		lblMemCharInfo = new JLabel("능력치 >");
		lblMemCharInfo.setFont(new Font("굴림", Font.BOLD, 13));
		lblMemCharInfo.setBounds(12, 184, 57, 15);
		showPanel.add(lblMemCharInfo);
		// Math
		lblCharMath = new JLabel("산술"); 
		lblCharMath.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblCharMath.setBounds(38, 209, 77, 30);
		showPanel.add(lblCharMath);

		lblMemCharMath = new JLabel(""); // 12
		lblMemCharMath.setFont(FontShow);
		lblMemCharMath.setBounds(143, 209, 57, 30);
		showPanel.add(lblMemCharMath);
		// 서비스
		lblCharServ = new JLabel("서비스");
		lblCharServ.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblCharServ.setBounds(212, 209, 88, 30);
		showPanel.add(lblCharServ);

		lblMemCharServ = new JLabel(""); // 14
		lblMemCharServ.setFont(FontShow);
		lblMemCharServ.setBounds(333, 209, 57, 30);
		showPanel.add(lblMemCharServ);
		// 손재주
		lblCharHand = new JLabel("손재주");
		lblCharHand.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblCharHand.setBounds(38, 249, 77, 30);
		showPanel.add(lblCharHand);

		lblMemCharHand = new JLabel(""); // 16
		lblMemCharHand.setFont(FontShow);
		lblMemCharHand.setBounds(143, 249, 57, 30);
		showPanel.add(lblMemCharHand);
		// IQ
		lblCharIq = new JLabel("지능");
		lblCharIq.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblCharIq.setBounds(212, 249, 88, 30);
		showPanel.add(lblCharIq);

		lblMemCharIq = new JLabel(""); // 18
		lblMemCharIq.setFont(FontShow);
		lblMemCharIq.setBounds(333, 249, 57, 30);
		showPanel.add(lblMemCharIq);
		// 과학
		lblCharScien = new JLabel("과학");
		lblCharScien.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblCharScien.setBounds(38, 289, 77, 30);
		showPanel.add(lblCharScien);

		lblMemCharScien = new JLabel(); // 20
		lblMemCharScien.setFont(FontShow);
		lblMemCharScien.setBounds(143, 289, 57, 30);
		showPanel.add(lblMemCharScien);
		
		lblInfoOverall = new JLabel("OVERALL");
		lblInfoOverall.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 12));
		lblInfoOverall.setBounds(223, 114, 67, 15);
		showPanel.add(lblInfoOverall);
		
		lblMemOverall = new JLabel("");
		lblMemOverall.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 33));
		lblMemOverall.setBounds(302, 86, 88, 46);
		showPanel.add(lblMemOverall);
		
		btnGetOut = new JButton("사직권유"); // 23
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
				
				/* 새 알림창 >
				 * 퇴직금  -> wage * 10  > YES or NO */
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "퇴직금 " + byeMoney + "를 요구합니다."
						+ "\n퇴사 처리 하시겠습니까?");
				// (result 리턴값)
				// JOptionPane.YES_OPTION, JOptionPane.NO_OPTION,
				// JOptionPane.CANCEL_OPTION 등
				
				if (result == 0) {
					vo.setPs_comp(5); // 5 > FA
					int resultUpdate = mdao.update(vo.getPs_id(), vo);
					
					FinanceVO vo2 = fdao.select(0);
					int curMoney = vo2.getCurMoney();
					int changedMon = curMoney - byeMoney;
					vo2.setCurMoney(changedMon);
					int result1 = fdao.insert(vo2);
					if (resultUpdate == 1) {
						System.out.println("퇴사처리가 정상적으로 되었습니다.");
					}
					if (result1 == 1) {
						System.out.println("잔고가 정상적으로 변경되었습니다.");
					}
					/* FA로 나가게 되었습니다. */
					if (grd == 5 || grd == 6) {
						JOptionPane.showMessageDialog(null, vo.getPs_name() + " 이(가) 욕을 하며 나갔습니다. " 
								+ "\n잔고가 " + changedMon + "로 바뀌었습니다.");
					}
					else {						
						JOptionPane.showMessageDialog(null, vo.getPs_name() + " 이(가) 노래를 부르며 나갔습니다. " 
								+ "\n잔고가 " + changedMon + "로 바뀌었습니다.");
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
		
		btnTakeHim = new JButton("영입"); // 24
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
							vo.getChar_scien() + vo.getChar_serv()) / 5 + 1; // 원래 overall보다 조금 더 높게 책정
					alloverall += overall;
				}
				nameValue = alloverall / list.size();
				
				int overall = (vo.getChar_hand() + vo.getChar_iq() + vo.getChar_math()+
						vo.getChar_scien() + vo.getChar_serv()) / 5;
				
				if (nameValue < overall) {
					/* 대충 원하지 않는다는 내용 */
					JOptionPane.showMessageDialog(null, vo.getPs_name() 
							+ "이(가) 이 회사는 비전이 없다고 생각합니다.");
				} else {
					vo.setPs_comp(1);
					int resultUpdate = mdao.update(vo.getPs_id(), vo);
					if (resultUpdate == 1) {
						System.out.println("입사가 정상적으로 처리");
					}
					JOptionPane.showMessageDialog(null, vo.getPs_name()
							+ "이(가) 우리회사로 입사하였습니다! ");
					btnGetOut.setEnabled(true);
					
					btnTakeHim.setEnabled(false);
					/* vo.getPs_name 이가 대충 회사로 들어왔다는 내용 */
					
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
		
		// 진급, 강등 심사 > button 3개 (진급 / 강등 / 확인)
		
		btnUpgrade = new JButton("UP");          // 25
		btnUpgrade.setBounds(143, 141, 67, 23);
		showPanel.add(btnUpgrade);
		btnUpgrade.addActionListener(new ActionListener() {
			
			@Override // 진급
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
				if (result == 1) { // 등록 성공
					JOptionPane.showMessageDialog(null, "정상적으로 처리되었습니다.");
					
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

	// ID(계정명) 반환 메소드
	public String returnID() {
		String id = cdao.returnID(1);
		return id;
	}
	
	// Fin_date를 빼오는 메소드
	public void returnDate() {
		int result = 0;
		FinanceVO vo = null;
		
		vo = fdao.select(0);
		result = vo.getFin_date();
		
		String str = (2020 + (result / 54)) + "년  " + ((result / 5) % 12 + 1) + "월 " + ((result % 5) + 1) + "번째 주";
		lblPlayTime.setText(str);
	}
	
	// 최고 실적을 계산하는 메소드
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
			btnPositive_Mem.setText("긍정적 성과 - 생산관리");
		} else if (max == cal) {
			btnPositive_Mem.setText("긍정적 성과 - 총무");
		} else if (max == sel) {
			btnPositive_Mem.setText("긍정적 성과 - 영업");
		} else if (max == man) {
			btnPositive_Mem.setText("긍정적 성과 - 인사");
		} else if (max == dev){
			btnPositive_Mem.setText("긍정적 성과 - 연구개발");
		} else if (max == mar){
			btnPositive_Mem.setText("긍정적 성과 - 마케팅");
		}
		
		if (min == mak) {
			btnNegative_Mem.setText("부정적 성과 - 생산관리");
		} else if (min == cal) {
			btnNegative_Mem.setText("부정적 성과 - 총무");
		} else if (min == sel) {
			btnNegative_Mem.setText("부정적 성과 - 영업");
		} else if (min == man) {
			btnNegative_Mem.setText("부정적 성과 - 인사");
		} else if (min == dev){
			btnNegative_Mem.setText("부정적 성과 - 연구개발");
		} else {
			btnNegative_Mem.setText("부정적 성과 - 마케팅");
		}
		
	}
	// infoGui 다시 실행시키는 메소드
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
