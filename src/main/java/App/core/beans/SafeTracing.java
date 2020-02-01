package App.core.beans;

public class SafeTracing extends BaseBean {

	private int id=-1;
	private int safeId;
	private double amount;
	private double befaorAmount;
	private double afterAmount;
	private int transactionType;
	private int transactionId;
	private String  transactionName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSafeId() {
		return safeId;
	}
	public void setSafeId(int safeId) {
		this.safeId = safeId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBefaorAmount() {
		return befaorAmount;
	}
	public void setBefaorAmount(double befaorAmount) {
		this.befaorAmount = befaorAmount;
	}
	public double getAfterAmount() {
		return afterAmount;
	}
	public void setAfterAmount(double afterAmount) {
		this.afterAmount = afterAmount;
	}
 
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

 
	
	
	
}
