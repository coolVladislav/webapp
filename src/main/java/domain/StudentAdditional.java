package domain;


public class StudentAdditional extends Entity{
	private StudentYear studentYear;
	private Double sum;
	private Reason reason;
	private int month;
	private Boolean isProved;
	private Integer dateOfProve;
	
	

	public StudentAdditional() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentYear getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(StudentYear studentYear) {
		this.studentYear = studentYear;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Boolean getIsProved() {
		return isProved;
	}

	public void setIsProved(Boolean isproved) {
		this.isProved = isproved;
	}

	public Integer getDateOfProve() {
		return dateOfProve;
	}

	public void setDateOfProve(Integer dateOfProve) {
		this.dateOfProve = dateOfProve;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateOfProve == null) ? 0 : dateOfProve.hashCode());
		result = prime * result + ((isProved == null) ? 0 : isProved.hashCode());
		result = prime * result + month;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((studentYear == null) ? 0 : studentYear.hashCode());
		result = prime * result + ((sum == null) ? 0 : sum.hashCode());
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
		StudentAdditional other = (StudentAdditional) obj;
		if (dateOfProve == null) {
			if (other.dateOfProve != null)
				return false;
		} else if (!dateOfProve.equals(other.dateOfProve))
			return false;
		if (isProved == null) {
			if (other.isProved != null)
				return false;
		} else if (!isProved.equals(other.isProved))
			return false;
		if (month != other.month)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (studentYear == null) {
			if (other.studentYear != null)
				return false;
		} else if (!studentYear.equals(other.studentYear))
			return false;
		if (sum == null) {
			if (other.sum != null)
				return false;
		} else if (!sum.equals(other.sum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentAdditional [studentyear_id=" + studentYear + ", sum=" + sum + ", reason_id=" + reason
				+ ", month=" + month + ", isproved=" + isProved + ", date_of_prove=" + dateOfProve + "]";
	}
	
}
