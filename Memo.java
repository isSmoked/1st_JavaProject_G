package proj.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// ���� �׷��� �׸���
public class Memo extends JPanel{
	
	private static Memo instance = null;
	
	public static Memo getInstance() {
		if (instance == null) {
			instance = new Memo();
		}
		return instance;
	}
	// .................................................
	
	
	public JPanel panel;
	public JLabel lblMemo;
	
	public Memo() {
		initialize();
	}

	
	private void initialize() {
		panel = new JPanel();
		panel.setBounds(130, 84, 414, 354);
		panel.setLayout(null);
		
		lblMemo = new JLabel();
		lblMemo.setText("Memo");
		lblMemo.setBounds(12, 10, 126, 27);
		panel.add(lblMemo);
		
		JLabel lblWarning = new JLabel("Warning > ������ ������ ������� �ʽ��ϴ�.");
		lblWarning.setBounds(12, 47, 367, 27);
		panel.add(lblWarning);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 320, 263, 24);
		textArea.setText("�̰��� �Է�");
		panel.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(12, 84, 390, 220);
//		textArea_1.setText("���� ��� >> "
//				+ "\n ���� = (((������� * 10) * ������ + 150) + (�������� * 1.1) * (���� * 0.1)"
//				+ "\n\n"
//				+ "���� ��� >> "
//				+ "\n ���� = ((�޿� * �߰��޿�) - (�λ� * ���� �� * 5)) - (�ѹ� * 0.1)");
		panel.add(textArea_1);
		
		JButton btnNewButton = new JButton("�Է�");
		btnNewButton.setBounds(305, 321, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str1 = textArea_1.getText();
				String str2 = textArea.getText();
				
				String str3 = str1 + "\n" + str2;
				
				textArea_1.setText(str3);
				textArea.setText("");
			}
		});
		panel.add(btnNewButton);
		
	}
}
