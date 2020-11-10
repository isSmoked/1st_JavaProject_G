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
		count = 0; // ���̵� �ߺ�Ȯ�ο�

		
		lblNewLabel = new JLabel("\uACC4\uC815 \uAD00\uB9AC");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 23));
		lblNewLabel.setBounds(12, 10, 158, 47);
		getContentPane().add(lblNewLabel);
		
		lbl_InsertNewID = new JLabel("NEW ID");
		lbl_InsertNewID.setFont(new Font("����", Font.BOLD, 15));
		lbl_InsertNewID.setBounds(48, 66, 73, 35);
		getContentPane().add(lbl_InsertNewID);
		
		lbl_insertNewPW = new JLabel("NEW PW");
		lbl_insertNewPW.setFont(new Font("����", Font.BOLD, 15));
		lbl_insertNewPW.setBounds(48, 146, 73, 35);
		getContentPane().add(lbl_insertNewPW);
	
		
		textField_NEWID = new JTextArea(); // ���̵� �Է�â
		textField_NEWID.setBounds(128, 88, 135, 30);
		getContentPane().add(textField_NEWID);
		
		textField_NEWPW = new JTextArea(); // ��й�ȣ �Է�â
		textField_NEWPW.setBounds(128, 167, 135, 30);
		getContentPane().add(textField_NEWPW);
		
		btnCheckSameID = new JButton("\uC911\uBCF5\uD655\uC778"); // �ߺ�Ȯ��
		btnCheckSameID.setFont(new Font("����", Font.PLAIN, 12));
		btnCheckSameID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �ߺ�Ȯ�� �޼ҵ�
				checkingID();
			}
		});
		
		btnCheckSameID.setBounds(237, 128, 85, 29);
		btnCheckSameID.setContentAreaFilled(false);
		getContentPane().add(btnCheckSameID);
		
		btnInsert = new JButton("\uC0DD\uC131"); // ������ư
		btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ��� �޼ҵ�
				if (count == 1) {
					insertNewAccount();
				} else {
					textArea_Log.setText("");
					JOptionPane.showMessageDialog(null,"�ߺ�Ȯ���� �ٽ� ���ּ���");
				}
			}
		});
		btnInsert.setBounds(12, 207, 85, 35);
		btnInsert.setContentAreaFilled(false);
		getContentPane().add(btnInsert);
		btnInsert.setEnabled(false);
		
		
		btnDelete = new JButton("\uC0AD\uC81C"); // ������ư
		btnDelete.setBounds(237, 207, 85, 35);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ���� �޼ҵ�
				deleteAccount();
			}
		});
		btnDelete.setContentAreaFilled(false);
		getContentPane().add(btnDelete);
		
		
		textArea_Log = new JLabel();
		textArea_Log.setBounds(182, 23, 126, 30);
		getContentPane().add(textArea_Log);
		

	}
	
	// ���� ��� �޼ҵ�
	private void insertNewAccount() {
		System.out.println("���� ���");
		
		int result = 3;
		String id = textField_NEWID.getText();
		System.out.println("���̵� : " + id);
		String pw = textField_NEWPW.getText();
		System.out.println("��й�ȣ : " + pw);
		
		if ((!id.equals("")) && (!pw.equals(""))) { // id, pw not null
			ContactVO vo = new ContactVO(id, pw);
			result = dao.insert1(vo);
		} else {
			result = 3;
		}
		if (result == 0) {
			JOptionPane.showMessageDialog(null,"(���)�ߺ��� ���̵� �Դϴ�.");
		} else if (result == 1){
			JOptionPane.showMessageDialog(null,"ȸ����� ����");
			btnInsert.setEnabled(false);
			btnCheckSameID.setEnabled(false);
			textField_NEWPW.setText("");
		} else {
			JOptionPane.showMessageDialog(null,"������ �ƴ� ��й�ȣ�� �Է��� �ּ���");
		}
	}
	
	// �ߺ� Ȯ�� �޼ҵ�
	private void checkingID() {
		System.out.println("���̵� �ߺ�Ȯ��");
		int result = 0;
		String id = textField_NEWID.getText();
		if (!id.equals("")) {
			result = dao.select1(id);			
		} else {
			result = 3;
		}
		if (result == 1) {
			JOptionPane.showMessageDialog(null,"�ߺ��� ���̵� �Դϴ�.");
			count = 0;
		} else if (result == 0){
			JOptionPane.showMessageDialog(null,"�������� ���̵��Դϴ�.");
			btnInsert.setEnabled(true);
			textField_NEWID.setEnabled(false);
			count = 1;
		} else {
			JOptionPane.showMessageDialog(null,"���̵�� �������� ������ �� �����ϴ�.");
			count = 0;
		}
	}
	
	// ���� ���� �޼ҵ�
	private void deleteAccount() {
		System.out.println("���� ����");
		
		int result = 0;
		String id = textField_NEWID.getText();
		String pw = textField_NEWPW.getText();
		ContactVO vo = new ContactVO(id, pw);
		if (!id.equals("")) { // ���̵� ������ ��
			result = dao.delete1(vo);
			if (pw.equals("")) { // ��й�ȣ�� ����
				result = 4;
			}
		} else {
			result = 3;
		}
		
		if (result == 1) {
			JOptionPane.showMessageDialog(null,"������ ó���Ǿ����ϴ�.");
			textField_NEWID.setText("");
			textField_NEWPW.setText("");
			textField_NEWID.setEnabled(true);
			btnCheckSameID.setEnabled(true);
		} else if (result == 4) {
			JOptionPane.showMessageDialog(null,"(����)��й�ȣ�� �Է��� �ּ���.");
		} else if (result == 3) { // result => 3 // ���̵� ���� �϶�
			JOptionPane.showMessageDialog(null,"(����)���̵� �Է��� �ּ���.");
		} else {
			JOptionPane.showMessageDialog(null,"���̵�� ��й�ȣ�� �ٸ��ϴ�.");
		}
	}
}
