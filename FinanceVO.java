package proj.finance.mvc;

// ȸ���� ������Ȳ�� �ǽð����� ����

// ���� �ܰ� / �޿� / �޿��� ���� / �����ܾ�  > MVC
public class FinanceVO {
	
	// ������� ����
	private int curMoney;
	private int wage;
	private double notWage;
	private double confidence;
	private int income;
	private int cid;
	private int fin_date;
	
	// ������
	public FinanceVO() {}
	public FinanceVO(int curMoney, int wage, double notWage, int income, double confidence, int fin_date) {
		super();
		this.curMoney = curMoney;
		this.wage = wage;
		this.notWage = notWage;
		this.income = income;
		this.confidence = confidence;
		this.fin_date = fin_date;
	}
	public FinanceVO(int curMoney, int wage, double notWage, double confidence, int income, int cid, int fin_date) {
		super();
		this.curMoney = curMoney;
		this.wage = wage;
		this.notWage = notWage;
		this.confidence = confidence;
		this.income = income;
		this.cid = cid;
		this.fin_date = fin_date;
	}
	
	// getter / setter
	
	public int getCurMoney() {
		return curMoney;
	}
	public void setCurMoney(int curMoney) {
		this.curMoney = curMoney;
	}
	public int getWage() {
		return wage;
	}
	public void setWage(int wage) {
		this.wage = wage;
	}
	public double getNotWage() {
		return notWage;
	}
	public void setNotWage(double notWage) {
		this.notWage = notWage;
	}
	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getFin_date() {
		return fin_date;
	}
	public void setFin_date(int fin_date) {
		this.fin_date = fin_date;
	}
	@Override
	public String toString() {
		String str = "���� �ܾ� : " + curMoney + " ���� �޿� : " + wage + " �޿� �� ���� : "
				+ notWage + " ���� ���� : " + income + " ���� �ܾ� : " + confidence; 
		return str; // ��¥, �õ� �߰�
	}
	
}
