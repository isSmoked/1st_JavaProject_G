package proj.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BankGui extends JPanel{

	private JPanel panel;
	
	public BankGui() {
		initialize();
	}

	private void initialize() {
		panel = new JPanel();
		panel.setBounds(130, 84, 414, 354);
		panel.setLayout(null);
		
		JLabel lblSetBankName = new JLabel();
		lblSetBankName.setText("\uC6B0\uB9AC\uAD6D\uBBFC\uC740\uD589");
		lblSetBankName.setBounds(27, 25, 126, 27);
		panel.add(lblSetBankName);
		
		JLabel lblSetRateofInterest = new JLabel("\uC774\uC790\uC728 > ");
		lblSetRateofInterest.setBounds(51, 86, 57, 15);
		panel.add(lblSetRateofInterest);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(144, 80, 140, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(27, 145, 57, 15);
		panel.add(lblNewLabel_1);
	}
}
