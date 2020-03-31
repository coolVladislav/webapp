package domain;

public class OrderDoc extends Entity{

	private Order order;
	private ReasonDoc reasonDoc;
	private Boolean isAccepted;

	
	public OrderDoc() {
		super();
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public ReasonDoc getReasonDoc() {
		return reasonDoc;
	}


	public void setReasonDoc(ReasonDoc reasonDoc) {
		this.reasonDoc = reasonDoc;
	}


	public Boolean getIsAccepted() {
		return isAccepted;
	}


	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((isAccepted == null) ? 0 : isAccepted.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((reasonDoc == null) ? 0 : reasonDoc.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDoc other = (OrderDoc) obj;
		if (isAccepted == null) {
			if (other.isAccepted != null)
				return false;
		} else if (!isAccepted.equals(other.isAccepted))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (reasonDoc == null) {
			if (other.reasonDoc != null)
				return false;
		} else if (!reasonDoc.equals(other.reasonDoc))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OrderDoc [order=" + order + ", reasonDoc=" + reasonDoc + ", isAccepted=" + isAccepted + "]";
	}
	
	
}
