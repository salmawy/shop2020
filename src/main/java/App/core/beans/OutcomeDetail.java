 package App.core.beans;

public class OutcomeDetail extends BaseBean {
	private int id =-1;
	private String typeName;
	private Double amount;
	private String spenderName;
	private String notes;
	private int outcomeId;
	private Integer customerId;
	private Integer orderId;
	private int fridageId;
	private Outcome outcome;
	private int typeId;
	
	private OutcomeType type;
	private Fridage fridage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getSpenderName() {
		return spenderName;
	}
	public void setSpenderName(String spenderName) {
		this.spenderName = spenderName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getOutcomeId() {
		return outcomeId;
	}
	public void setOutcomeId(int outcomeId) {
		this.outcomeId = outcomeId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public int getFridageId() {
		return fridageId;
	}
	public void setFridageId(int fridageId) {
		this.fridageId = fridageId;
	}
	public Outcome getOutcome() {
		return outcome;
	}
	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public OutcomeType getType() {
		return type;
	}
	public void setType(OutcomeType type) {
		this.type = type;
	}
	public Fridage getFridage() {
		return fridage;
	}
	public void setFridage(Fridage fridage) {
		this.fridage = fridage;
	}

	
	

}
