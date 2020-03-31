package domain;

public class Reason extends Entity{
	private Double number;
	private String note;
	private int maxCoef;
	
	public Reason() {
		super();
	}
	public Reason(int id) {
		super(id);
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getMaxCoef() {
		return maxCoef;
	}

	public void setMaxCoef(int maxCoef) {
		this.maxCoef = maxCoef;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCoef;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Reason other = (Reason) obj;
		if (maxCoef != other.maxCoef)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reason [number=" + number + ", note=" + note + ", max_coef=" + maxCoef + "]";
	}
	
	
}
