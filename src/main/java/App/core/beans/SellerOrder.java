 package App.core.beans;

import java.util.Date;
import java.util.Set;


public class SellerOrder extends BaseBean {
	private int id =-1;
	private int sellerId;
	private Double totalCost;
	private Date orderDate;
	private String notes;
	private int fridageId;
	private int seasonId;
	private int sciencere;
	private Integer sellerLoanBagId;
	private Seller seller;
    private Set<SellerOrderWeight> orderWeights;

	private Fridage fridage;

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getFridageId() {
		return fridageId;
	}
	public void setFridageId(int fridageId) {
		this.fridageId = fridageId;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public int getSciencere() {
		return sciencere;
	}
	public void setSciencere(int sciencere) {
		this.sciencere = sciencere;
	}
	public Integer getSellerLoanBagId() {
		return sellerLoanBagId;
	}
	public void setSellerLoanBagId(Integer sellerLoanBagId) {
		this.sellerLoanBagId = sellerLoanBagId;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public Set<SellerOrderWeight> getOrderWeights() {
		return orderWeights;
	}
	public void setOrderWeights(Set<SellerOrderWeight> orderWeights) {
		this.orderWeights = orderWeights;
	}
	public Fridage getFridage() {
		return fridage;
	}
	public void setFridage(Fridage fridage) {
		this.fridage = fridage;
	}
		
	


}
