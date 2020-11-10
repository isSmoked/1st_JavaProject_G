package proj.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import proj.contact.mvc.ContactDAO;
import proj.contact.mvc.ContactDAOImple;
import proj.etc.Constants;
import proj.finance.mvc.FinanceDAO;
import proj.finance.mvc.FinanceDAOImple;
import proj.finance.mvc.FinanceVO;
import proj.member.mvc.MemberDAO;
import proj.member.mvc.MemberDAOImple;
import proj.member.mvc.MemberVO;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;

public class InfoGui extends JPanel implements Constants {

//	private static InfoGui instance = null;
//	
//	public static InfoGui getInstance() {
//		if (instance == null) {
//			instance = new InfoGui();
//		}
//		return instance;
//	}

	public JPanel panel;
	private ContactDAO cdao;
	private FinanceDAO fdao;
	private MemberDAO mdao;
	private MainFrame mf;
	
	private int fin_date;
	
	public JLabel lblSetPassedTime, lblSetcountMems, lblSetInsertedMem;
	private JButton btnNewRegist;
	private JTextArea textAreaSetName;
	private String compareName;
	
	public InfoGui() {
		
		
		initialize();
	}

	
	private void initialize() {
		cdao = ContactDAOImple.getInstance();
		fdao = FinanceDAOImple.getInstance();
		mdao = MemberDAOImple.getInstance();
		mf = MainFrame.getInstance();
		
		panel = new JPanel();
		panel.setBounds(130, 84, 414, 354);
		panel.setLayout(null);
		
		JLabel lblShowName = new JLabel();
		lblShowName.setBounds(12, 10, 156, 37);
		panel.add(lblShowName);
		lblShowName.setText(returnID() + " ���� ȸ��");
		
		JLabel lblShowPassedTime = new JLabel("PassedTime");
		lblShowPassedTime.setBounds(12, 57, 125, 24);
		panel.add(lblShowPassedTime);
		
		lblSetPassedTime = new JLabel();
		lblSetPassedTime.setBounds(205, 57, 180, 24);
		panel.add(lblSetPassedTime);
		
		JLabel lblShowCountMem = new JLabel("ShowCountMembers");
		lblShowCountMem.setBounds(12, 91, 125, 24);
		panel.add(lblShowCountMem);
		
		lblSetcountMems = new JLabel();
		lblSetcountMems.setBounds(205, 91, 136, 24);
		panel.add(lblSetcountMems);
		
		JLabel lblShowInsertedMem = new JLabel("InsertedMems");
		lblShowInsertedMem.setBounds(12, 125, 125, 24);
		panel.add(lblShowInsertedMem);
		
		lblSetInsertedMem = new JLabel();
		lblSetInsertedMem.setBounds(205, 125, 156, 20);
		panel.add(lblSetInsertedMem);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textArea.setBounds(12, 159, 390, 115);
		textArea.setText("�� ������ ������ " + GAMEVERSION);
		panel.add(textArea);
		
		// �ű� ��� ��ư
		btnNewRegist = new JButton("\uC2E0\uADDC \uC0DD\uC131");
		btnNewRegist.setBounds(305, 307, 97, 37);
		btnNewRegist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �̸� , �μ�, ����, �Ҽ� / ����, ����, ������, ����, ����
				int check = 0;
				if (textAreaSetName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է��ϼ���");
				} else if (textAreaSetName.getText().length() < 3) {
					JOptionPane.showMessageDialog(null, "�� �� �̸��� �Է��ϼ���");
				} else if (textAreaSetName.getText().length() > 4) {
					JOptionPane.showMessageDialog(null, "�ʹ� ����");
				} else {
					ArrayList<MemberVO> list = mdao.select();
					for (int i = 0; i < list.size(); i++) {
						compareName = list.get(i).getPs_name();
						if (textAreaSetName.getText().equals(compareName)) {
							JOptionPane.showMessageDialog(null, "�ߺ��� �̸��Դϴ�!");
							break;
						}
					}
					if (!textAreaSetName.getText().equals(compareName)) {
						String name = textAreaSetName.getText();
						int grd = (int)(Math.random() * 10 % 6) + 1;
						int dep = (int)(Math.random() * 10 % 8) + 1;
						int comp = 5;
						
						int math = (int)(Math.random() * 9) + 1;
						int serv = (int)(Math.random() * 9) + 1;
						int hand = (int)(Math.random() * 9) + 1;
						int iq = (int)(Math.random() * 9) + 1;
						int scien = (int)(Math.random() * 9) + 1;
						MemberVO vo = new MemberVO(name, dep, grd,
								comp, math, serv, hand, iq, scien);
						
						int result = mdao.insert(vo);
						if (result == 1) {
							JOptionPane.showMessageDialog(null, name + "(��)�� ����Ȱ���� �����մϴ�!");
							check = 0;
							textAreaSetName.setText("");
							
							mf.reStartInfoGui();
							textArea.setText("�̸� : " + name + " / ���� : " + grd + " / �μ� : " + dep
									+ "/ ���� : " + math + " / ���� : " + serv + " / ������ " + hand
									+ "/ ������ : " + iq + "/ ���� : " + scien);
						}
					} else {
						return;
					}
				}
			}
		});
		panel.add(btnNewRegist);
		
		// �̸� �Է� â
		textAreaSetName = new JTextArea();
		textAreaSetName.setBounds(113, 307, 180, 37);
		panel.add(textAreaSetName);
		
		JLabel lblShowSetName = new JLabel("\uC774\uB984 \uC785\uB825");
		lblShowSetName.setBounds(12, 307, 91, 37);
		panel.add(lblShowSetName);
		
		JLabel lblNewRegist = new JLabel("\uC0C8\uB85C\uC6B4 \uC9C1\uC6D0 \uC0DD\uC131");
		lblNewRegist.setBounds(12, 284, 104, 15);
		panel.add(lblNewRegist);
		
		
		
		returnPlayTime();
		returnCountingMems();
		returnAllMems();
	}
	
	// ID(������) ��ȯ �޼ҵ�
	public String returnID() {
		String id = cdao.returnID(1);
		return id;
	}
	
	// �����ð� ���� �޼ҵ�
	public void returnPlayTime() {
		FinanceVO vo = fdao.select(1);
		fin_date = vo.getFin_date();
		
		String dateString = ((fin_date / 54 )) + " �� �ϰ� " + ((fin_date / 5) % 12) +
					" ���� �ϰ� " + ((fin_date % 5) + 1) + " ��";
		lblSetPassedTime.setText(dateString);
	}
	
	// �츮 ������ ���� �޼ҵ�
	public int returnCountingMems() {
		ArrayList<MemberVO> list = mdao.select();
		
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i).getPs_comp() == 1) { // �츮 ȸ�� ���
				count++; // �츮 ȸ�� ���!
			}
		}
		lblSetcountMems.setText(count + " ���� ������ �ֽ��ϴ�.");
		return count;
	}
	
	// ��ü ������ ���� �޼ҵ�
	public int returnAllMems() {
		ArrayList<MemberVO> list = mdao.select();
		
		int count = 0;
		for (int i = 0 ; i < list.size(); i++) {
			count++;
		}
		lblSetInsertedMem.setText(count + " ���� ��ϵǾ� �ֽ��ϴ�.");
		return count;
	}
}
