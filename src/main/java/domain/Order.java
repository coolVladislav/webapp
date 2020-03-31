package domain;


public class Order extends Entity{
	
	private StudentYear studentYear;
	private Reason reason;
	private Integer dateOfOrder;
	
	public Order() {}
	
	public Order(int id) {
		super(id);
	}

	public StudentYear getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(StudentYear studentYear) {
		this.studentYear = studentYear;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public Integer getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Integer dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	};
	
	
}
