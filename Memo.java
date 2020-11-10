package proj.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// 재정 그래프 그리기
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
		
		JLabel lblWarning = new JLabel("Warning > 게임이 꺼지면 저장되지 않습니다.");
		lblWarning.setBounds(12, 47, 367, 27);
		panel.add(lblWarning);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 320, 263, 24);
		textArea.setText("이곳에 입력");
		panel.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(12, 84, 390, 220);
//		textArea_1.setText("수입 계산 >> "
//				+ "\n 수입 = (((생산관리 * 10) * 마케팅 + 150) + (연구개발 * 1.1) * (영업 * 0.1)"
//				+ "\n\n"
//				+ "지출 계산 >> "
//				+ "\n 지출 = ((급여 * 추가급여) - (인사 * 직원 수 * 5)) - (총무 * 0.1)");
		panel.add(textArea_1);
		
		JButton btnNewButton = new JButton("입력");
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
