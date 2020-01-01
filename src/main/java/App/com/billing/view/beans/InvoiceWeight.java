package App.com.billing.view.beans;

public class InvoiceWeight {
	
	private double amount;
	private double unitePrice;
	private double weight;
	
	
	public InvoiceWeight(  double amount,	  double unitePrice,	  double weight) {
	
	this.amount=amount;
	this.unitePrice=unitePrice;
	this.weight=weight;
	
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getUnitePrice() {
		return unitePrice;
	}
	public void setUnitePrice(double unitePrice) {
		this.unitePrice = unitePrice;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	
	

}
