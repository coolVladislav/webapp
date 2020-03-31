package domain;

public class StudentYear extends Entity{
	private int course;
	private int group;
	private Boolean isFreeibe;
	private Student student;
	private int year;

	
	
	public StudentYear() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentYear(int id) {
		super(id);
	}

	public int getCourse() {
		return course;
	}
	
	public void setCourse(int course) {
		this.course = course;
	}
	
	public int getGroup() {
		return group;
	}
	
	public void setGroup(int group) {
		this.group = group;
	}
	
	public Boolean getIsFreeibe() {
		return isFreeibe;
	}
	
	public void setIsFreeibe(Boolean isfreeibe) {
		this.isFreeibe = isfreeibe;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student newStudent) {
		this.student = newStudent;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + course;
		result = prime * result + group;
		result = prime * result + ((isFreeibe == null) ? 0 : isFreeibe.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + year;
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
		StudentYear other = (StudentYear) obj;
		if (course != other.course)
			return false;
		if (group != other.group)
			return false;
		if (isFreeibe == null) {
			if (other.isFreeibe != null)
				return false;
		} else if (!isFreeibe.equals(other.isFreeibe))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Studentyear [course=" + course + ", group=" + group + ", isfreeibe=" + isFreeibe
				+ ", students_id_record_book=" + student + ", year=" + year + "]";
	}
}
