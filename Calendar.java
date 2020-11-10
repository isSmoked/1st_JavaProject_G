package proj.gui;
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JLabel;

import proj.finance.mvc.FinanceDAO;
import proj.finance.mvc.FinanceDAOImple;
import proj.finance.mvc.FinanceVO;

public class Calendar extends Panel {
	
//	private static Calendar instance = null;
//	
//	public static Calendar getInstance() {
//		if (instance == null) {
//			instance = new Calendar();
//		}
//		return instance;
//	}
	
	private FinanceDAO dao = FinanceDAOImple.getInstance();

    private static final long serialVersionUID = 1L;
    private Panel panel = null;
    private Panel panel1 = null;
    private Choice choice = null;
    private Choice choice1 = null;
    private Label label = null;
    private Label label1 = null;
    private Button[] bt2 = new Button[42];
    private int year=0;
    private int month=0;
    private int yearSel;
    private int monthSel;

    private Panel getPanel() {
        if (panel == null) {
            label1 = new Label();
            label1.setText("년");
            label = new Label();
            label.setText("월");
            panel = new Panel();
            panel.setLayout(new FlowLayout());
            panel.add(getChoice(), null);
            panel.add(label1, null);
            panel.add(getChoice1(), null);
            panel.add(label, null);
        }
        return panel;
    }

    private Panel getPanel1() {
        if (panel1 == null) {
            panel1= new Panel();
            panel1.setLayout(new GridLayout(7,7));   
            JLabel[] bt1 = new JLabel[7];
            String[] day = {"일","월","화","수","목","금","토"};
            for(int i=0 ; i<7 ; i++){
                bt1[i] = new JLabel(day[i]);
                panel1.add(bt1[i]);
            }
           
            bt2 = new Button[42];
           
            for(int i=0 ; i<42 ; i++){
                bt2[i] = new Button("");
                panel1.add(bt2[i]);
            }

            year = Integer.parseInt(choice.getSelectedItem());
            month = Integer.parseInt(choice1.getSelectedItem());
           
            int startDay = 0;
            int endDay = 0;
           
            java.util.Calendar sDay=java.util.Calendar.getInstance();
            java.util.Calendar eDay=java.util.Calendar.getInstance();
           
            sDay.set(year,month-1,1);
            eDay.set(year, month,1);
            eDay.add(java.util.Calendar.DATE, -1);
           
            startDay=sDay.get(java.util.Calendar.DAY_OF_WEEK);
            endDay=eDay.get(java.util.Calendar.DATE);
           
            for(int i=1; i<=endDay ; i++){
                bt2[i+startDay-2].setLabel(i+"");
            }
        }
        return panel1;
    }

    private Choice getChoice() {
        if (choice == null) {
        	returnDate();
            choice = new Choice();
            choice.add("2020");
            choice.add("2021");
            choice.add("2022");
            choice.add("2023");
            choice.add("2024");
            choice.add("2025");
            choice.add("2026");
            choice.add("2027");
            choice.add("2028");
            choice.add("2029");
            choice.add("2030");
            choice.add("2031");

            int ChoiceYear;
            choice.select(yearSel); // TODO : 년 선택
            System.out.println(yearSel);
        }
        return choice;
    }

    private Choice getChoice1() {
        if (choice1 == null) {
        	returnDate();
            choice1 = new Choice();

            choice1.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent e) {
   
                    for(int i=0 ; i<42 ; i++){
                        bt2[i].setLabel("");
                    }
                   
                    year = Integer.parseInt(choice.getSelectedItem());
                    month = Integer.parseInt(choice1.getSelectedItem());
                   
                    int startDay = 0;
                    int endDay = 0;
                   
                    java.util.Calendar sDay=java.util.Calendar.getInstance();
                    java.util.Calendar eDay=java.util.Calendar.getInstance();
                   
                    sDay.set(year,month-1,1);
                    eDay.set(year, month,1);
                    eDay.add(java.util.Calendar.DATE, -1);
                   
                    startDay=sDay.get(java.util.Calendar.DAY_OF_WEEK);
                    endDay=eDay.get(java.util.Calendar.DATE);

                    for(int i=1; i<=endDay ; i++){
                        bt2[i+startDay-2].setLabel(i+"");
                    }
                }
            });
            choice1.add("1");
            choice1.add("2");
            choice1.add("3");
            choice1.add("4");
            choice1.add("5");
            choice1.add("6");
            choice1.add("7");
            choice1.add("8");
            choice1.add("9");
            choice1.add("10");
            choice1.add("11");
            choice1.add("12");

            int choiceMon;
            choice1.select(monthSel);     // TODO 월 선택
           
        }
        return choice1;
    }



    public Calendar() {
        super();
        initialize();
    }

    private void initialize() {

        this.add(getPanel());
        this.add(getPanel1());
    }
    
    private void returnDate() {
    	int result = 0;
		FinanceVO vo = dao.select(0);
		result = vo.getFin_date();
		
		yearSel = result / 54;
		monthSel = (result / 5) % 12;
    }
}