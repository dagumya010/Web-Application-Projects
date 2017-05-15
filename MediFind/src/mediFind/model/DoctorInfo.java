package mediFind.model;

public class DoctorInfo {

	
	protected int doctorId;
	protected HealthCareFacility mediFacility;
	protected Speciality speciality;
	protected String firstName;
	protected String lastName;
	protected String hour;
	protected Gender gender;
	
	public enum Gender {
		M,F
	}
	
	public DoctorInfo(int doctorId, HealthCareFacility mediFacility, Speciality speciality, String firstName, String lastName,
			String hour, Gender gender) {
		this.doctorId = doctorId;
		this.mediFacility = mediFacility;
		this.speciality = speciality;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hour = hour;
		this.gender = gender;
		
	}
	
	public DoctorInfo(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public HealthCareFacility getMediFacility() {
		return mediFacility;
	}

	public void setMediFacility(HealthCareFacility mediFacility) {
		this.mediFacility = mediFacility;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
