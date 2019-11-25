 package App.core.beans;

public class SellerOrderWeight extends BaseBean {
	private int id =-1;
	private Double grossQuantity;
	private Double netQuantity;
	private Double unitePrice;
	private Double amount;
	private int sellerOrderId;
	private int  productId;
	private int customerOrderId;
	private int packageNumber;
	private CustomerOrder customerOrder;
	private SellerOrder sellerOrder;
	private Product product;
	
	
	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}
	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}
	public SellerOrder getSellerOrder() {
		return sellerOrder;
	}
	public void setSellerOrder(SellerOrder sellerOrder) {
		this.sellerOrder = sellerOrder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getGrossQuantity() {
		return grossQuantity;
	}
	public void setGrossQuantity(Double grossQuantity) {
		this.grossQuantity = grossQuantity;
	}
	public Double getNetQuantity() {
		return netQuantity;
	}
	public void setNetQuantity(Double netQuantity) {
		this.netQuantity = netQuantity;
	}
	public Double getUnitePrice() {
		return unitePrice;
	}
	public void setUnitePrice(Double unitePrice) {
		this.unitePrice = unitePrice;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCustomerOrderId() {
		return customerOrderId;
	}
	public void setCustomerOrderId(int customerOrderId) {
		this.customerOrderId = customerOrderId;
	}
	public int getPackageNumber() {
		return packageNumber;
	}
	public void setPackageNumber(int packageNumber) {
		this.packageNumber = packageNumber;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getSellerOrderId() {
		return sellerOrderId;
	}
	public void setSellerOrderId(int sellerOrderId) {
		this.sellerOrderId = sellerOrderId;
	}
	
 

}
