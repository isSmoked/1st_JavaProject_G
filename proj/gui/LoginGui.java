package proj.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import proj.contact.mvc.ContactDAO;
import proj.contact.mvc.ContactDAOImple;
import proj.contact.mvc.ContactVO;


// GUIMain에 로그인 창을 만들고, 새 창으로 회원가입 창을 띄우고
//							새 창으로 main 창을 새로 띄운다.

public class LoginGui {
	private MainFrame frame1;

	private JFrame frame;
	private NewRegist register;
	private static Scanner sc;
	private JTextArea textArea_ID, textArea_PW;
	private JLabel lblInsertId, lblinsertPassword;
	private JButton btnLogIn, btnNewAccount;
	private JLabel textArea;
	private static ContactDAO dao;
	public static int check = 0;
	public int logCheck = 0;
	private static String photo = "res/unnamed3.jpg";
	private JLabel lblNewLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LoginGui window = new LoginGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginGui() {
		initialize();
	}

	private void initialize() {
		sc = new Scanner(System.in);
		dao = ContactDAOImple.getInstance();
		
		frame = new JFrame();
		frame.setBounds(600, 250, 441, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		// button 1
		btnLogIn = new JButton("\uB85C\uADF8\uC778"); // 메인
		btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 프레임을 호출
				loginAccess();
				if (check == 1) {
					dao.insertID(textArea_ID.getText());
					frame1 = MainFrame.getInstance();
					
					frame1.setVisible(true);
					logCheck = 1;
					textArea_ID.setText("");
				}
			}
		});
		frame.getContentPane().setLayout(null);
		btnLogIn.setBounds(304, 124, 90, 87);
//		btnLogIn.setContentAreaFilled(false);
		btnLogIn.setBackground(new Color(220, 220, 220));
		frame.getContentPane().add(btnLogIn);
		
		
		// button 2
		btnNewAccount = new JButton("\uD68C\uC6D0\uAC00\uC785"); // 회원가입
		btnNewAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원가입 창 호출
				register = new NewRegist();
				register.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				register.setVisible(true);
				logCheck = 0;
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		btnNewAccount.setBounds(304, 219, 108, 32);
		btnNewAccount.setBackground(new Color(220, 220, 220));
//		btnNewAccount.setContentAreaFilled(false);
		frame.getContentPane().add(btnNewAccount);
		
		textArea_ID = new JTextArea();
		textArea_ID.setBounds(126, 124, 154, 32);
		frame.getContentPane().add(textArea_ID);
		
		textArea_PW = new JTextArea();
		textArea_PW.setBounds(126, 179, 154, 32);
		frame.getContentPane().add(textArea_PW);
		
		lblInsertId = new JLabel("\uC544\uC774\uB514");
		lblInsertId.setFont(new Font("굴림", Font.BOLD, 16));
		lblInsertId.setBounds(28, 124, 90, 32);
		frame.getContentPane().add(lblInsertId);
		
		lblinsertPassword = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblinsertPassword.setFont(new Font("굴림", Font.BOLD, 16));
		lblinsertPassword.setBounds(28, 171, 90, 40);
		frame.getContentPane().add(lblinsertPassword);
		
		textArea = new JLabel();
		textArea.setBounds(28, 223, 260, 28);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setBounds(0, 81, 425, 32);
		btnNewButton.setBackground(new Color(230, 230, 230));
		btnNewButton.setText("직원관리 시뮬레이션게임");
		btnNewButton.setEnabled(false);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 425, 108);
		lblNewLabel.setIcon(new ImageIcon(photo));
		frame.getContentPane().add(lblNewLabel);

	}
	
	private void loginAccess() {
		System.out.println("로그인 >>>>> ");
		
		int result = 0;
		String id = textArea_ID.getText();
		String pw = textArea_PW.getText();
		int n = textArea_PW.getText().length(); // TODO 별찍기 개선
		String star = "*";
		String str = "*";
		
		for (int i = 1; i < n; i++) {
			str += star;
		}
		
		ContactVO vo = new ContactVO(id, pw);
		result = dao.select1(vo);
		
		
		
		// 등록된 아이디인지 확인
		if (result == 1) {
			// 로그인 가능
			check = 1;
			JOptionPane.showMessageDialog(null,textArea_ID.getText() + " 님 환영합니다.");
			textArea_PW.setText("");
		} else if (result == 0) {
			// 아이디 오류
			String getID = textArea_ID.getText();
			if (getID.isEmpty()) {
				JOptionPane.showMessageDialog(null,"아이디를 입력해 주세요");
			} else {
				JOptionPane.showMessageDialog(null,"등록되지 않은 아이디 입니다.");
				check = 0;
			}
		} else if (result == 2) {
			// 비밀번호 오류
			String getPW = textArea_PW.getText();
			if (getPW.isEmpty()) {
				JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요");
			} else {
				JOptionPane.showMessageDialog(null,"비밀번호가 다릅니다.");
				check = 0;
			}
		}
	}
}
