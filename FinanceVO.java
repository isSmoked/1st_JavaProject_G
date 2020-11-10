package proj.finance.mvc;

// 회사의 재정상황을 실시간으로 저장

// 현재 잔고 / 급여 / 급여외 지출 / 예상잔액  > MVC
public class FinanceVO {
	
	// 멤버변수 선언
	private int curMoney;
	private int wage;
	private double notWage;
	private double confidence;
	private int income;
	private int cid;
	private int fin_date;
	
	// 생성자
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
		String str = "현재 잔액 : " + curMoney + " 현재 급여 : " + wage + " 급여 외 지출 : "
				+ notWage + " 예상 수입 : " + income + " 예상 잔액 : " + confidence; 
		return str; // 날짜, 시드 추가
	}
	
}
