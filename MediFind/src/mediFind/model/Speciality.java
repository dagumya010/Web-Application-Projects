package mediFind.model;

public class Speciality {

	protected int specialtyId;
	protected String speciality;
	
	public Speciality(int specialtyId, String speciality) {
		this.specialtyId = specialtyId;
		this.speciality = speciality;
	}
	
	public Speciality(int specialtyId) {
		this.specialtyId = specialtyId;
	}

	/** Getters and setters. */
	
	public int getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
	}
	
	public String getSpeciality() {
		return speciality;
	}

	public void setSpecialty(String specialty) {
		this.speciality = speciality;
	}  
}


