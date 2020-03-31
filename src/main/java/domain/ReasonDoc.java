package domain;

public class ReasonDoc extends Entity{
	private Double number;
	private String docName;
	

	public ReasonDoc() {}
	
	public ReasonDoc(int id) {
		super(id);
	}
	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String doc_name) {
		this.docName = doc_name;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((docName == null) ? 0 : docName.hashCode());
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
		ReasonDoc other = (ReasonDoc) obj;
		if (docName == null) {
			if (other.docName != null)
				return false;
		} else if (!docName.equals(other.docName))
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
		return "ReasonDocs [number=" + number + ", doc_name=" + docName + "]";
	}
	
	
	
}
