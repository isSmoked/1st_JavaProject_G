package proj.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import proj.contact.mvc.ContactDAO;
import proj.contact.mvc.ContactDAOImple;
import proj.contact.mvc.ContactVO;


public class NewRegist extends JDialog {
	private static ContactDAO dao;
	private static int count;
	
	JLabel lblNewLabel, lbl_InsertNewID, lbl_insertNewPW;
	JTextArea textField_NEWID, textField_NEWPW;
	JButton btnCheckSameID;
	JButton btnInsert, btnUpdate, btnDelete;
	JLabel textArea_Log;
	
	public NewRegist() {
		initialize();
	}

	private void initialize() {
		dao = ContactDAOImple.getInstance();
		setBounds(690, 260, 350, 291);
		getContentPane().setLayout(null);
		count = 0; // 아이디 중복확인용

		
		lblNewLabel = new JLabel("\uACC4\uC815 \uAD00\uB9AC");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 23));
		lblNewLabel.setBounds(12, 10, 158, 47);
		getContentPane().add(lblNewLabel);
		
		lbl_InsertNewID = new JLabel("NEW ID");
		lbl_InsertNewID.setFont(new Font("굴림", Font.BOLD, 15));
		lbl_InsertNewID.setBounds(48, 66, 73, 35);
		getContentPane().add(lbl_InsertNewID);
		
		lbl_insertNewPW = new JLabel("NEW PW");
		lbl_insertNewPW.setFont(new Font("굴림", Font.BOLD, 15));
		lbl_insertNewPW.setBounds(48, 146, 73, 35);
		getContentPane().add(lbl_insertNewPW);
	
		
		textField_NEWID = new JTextArea(); // 아이디 입력창
		textField_NEWID.setBounds(128, 88, 135, 30);
		getContentPane().add(textField_NEWID);
		
		textField_NEWPW = new JTextArea(); // 비밀번호 입력창
		textField_NEWPW.setBounds(128, 167, 135, 30);
		getContentPane().add(textField_NEWPW);
		
		btnCheckSameID = new JButton("\uC911\uBCF5\uD655\uC778"); // 중복확인
		btnCheckSameID.setFont(new Font("굴림", Font.PLAIN, 12));
		btnCheckSameID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 중복확인 메소드
				checkingID();
			}
		});
		
		btnCheckSameID.setBounds(237, 128, 85, 29);
		btnCheckSameID.setContentAreaFilled(false);
		getContentPane().add(btnCheckSameID);
		
		btnInsert = new JButton("\uC0DD\uC131"); // 생성버튼
		btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 계정 등록 메소드
				if (count == 1) {
					insertNewAccount();
				} else {
					textArea_Log.setText("");
					JOptionPane.showMessageDialog(null,"중복확인을 다시 해주세요");
				}
			}
		});
		btnInsert.setBounds(12, 207, 85, 35);
		btnInsert.setContentAreaFilled(false);
		getContentPane().add(btnInsert);
		btnInsert.setEnabled(false);
		
		
		btnDelete = new JButton("\uC0AD\uC81C"); // 삭제버튼
		btnDelete.setBounds(237, 207, 85, 35);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 계정 삭제 메소드
				deleteAccount();
			}
		});
		btnDelete.setContentAreaFilled(false);
		getContentPane().add(btnDelete);
		
		
		textArea_Log = new JLabel();
		textArea_Log.setBounds(182, 23, 126, 30);
		getContentPane().add(textArea_Log);
		

	}
	
	// 계정 등록 메소드
	private void insertNewAccount() {
		System.out.println("계정 등록");
		
		int result = 3;
		String id = textField_NEWID.getText();
		System.out.println("아이디 : " + id);
		String pw = textField_NEWPW.getText();
		System.out.println("비밀번호 : " + pw);
		
		if ((!id.equals("")) && (!pw.equals(""))) { // id, pw not null
			ContactVO vo = new ContactVO(id, pw);
			result = dao.insert1(vo);
		} else {
			result = 3;
		}
		if (result == 0) {
			JOptionPane.showMessageDialog(null,"(등록)중복된 아이디 입니다.");
		} else if (result == 1){
			JOptionPane.showMessageDialog(null,"회원등록 성공");
			btnInsert.setEnabled(false);
			btnCheckSameID.setEnabled(false);
			textField_NEWPW.setText("");
		} else {
			JOptionPane.showMessageDialog(null,"공백이 아닌 비밀번호를 입력해 주세요");
		}
	}
	
	// 중복 확인 메소드
	private void checkingID() {
		System.out.println("아이디 중복확인");
		int result = 0;
		String id = textField_NEWID.getText();
		if (!id.equals("")) {
			result = dao.select1(id);			
		} else {
			result = 3;
		}
		if (result == 1) {
			JOptionPane.showMessageDialog(null,"중복된 아이디 입니다.");
			count = 0;
		} else if (result == 0){
			JOptionPane.showMessageDialog(null,"정상적인 아이디입니다.");
			btnInsert.setEnabled(true);
			textField_NEWID.setEnabled(false);
			count = 1;
		} else {
			JOptionPane.showMessageDialog(null,"아이디는 공백으로 설정할 수 없습니다.");
			count = 0;
		}
	}
	
	// 계정 삭제 메소드
	private void deleteAccount() {
		System.out.println("계정 삭제");
		
		int result = 0;
		String id = textField_NEWID.getText();
		String pw = textField_NEWPW.getText();
		ContactVO vo = new ContactVO(id, pw);
		if (!id.equals("")) { // 아이디가 공백일 때
			result = dao.delete1(vo);
			if (pw.equals("")) { // 비밀번호가 공백
				result = 4;
			}
		} else {
			result = 3;
		}
		
		if (result == 1) {
			JOptionPane.showMessageDialog(null,"삭제가 처리되었습니다.");
			textField_NEWID.setText("");
			textField_NEWPW.setText("");
			textField_NEWID.setEnabled(true);
			btnCheckSameID.setEnabled(true);
		} else if (result == 4) {
			JOptionPane.showMessageDialog(null,"(삭제)비밀번호를 입력해 주세요.");
		} else if (result == 3) { // result => 3 // 아이디가 공백 일때
			JOptionPane.showMessageDialog(null,"(삭제)아이디를 입력해 주세요.");
		} else {
			JOptionPane.showMessageDialog(null,"아이디와 비밀번호가 다릅니다.");
		}
	}
}
