package mediFind.model;

import java.sql.Time;
import java.sql.Date;

public class Appointment {
	protected int appointmentId;
	protected HealthCareFacility mediFacility;
	protected Users userName;
	protected Date appointmentDate;
	protected Time appointmentTime;
	protected String prescription;
	
	
	public Appointment(int appointmentId, HealthCareFacility mediFacility, Users userName, Date appointmentDate,
			Time appointmentTime, String prescription) {
		super();
		this.appointmentId = appointmentId;
		this.mediFacility = mediFacility;
		this.userName = userName;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.prescription = prescription;
	}
	


	public Appointment(HealthCareFacility mediFacility, Users userName, Date appointmentDate, Time appointmentTime,
			String prescription) {
		super();
		this.mediFacility = mediFacility;
		this.userName = userName;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.prescription = prescription;
	}



	public int getAppointmentId() {
		return appointmentId;
	}


	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}


	public HealthCareFacility getMediFacility() {
		return mediFacility;
	}


	public void setMediFacility(HealthCareFacility mediFacility) {
		this.mediFacility = mediFacility;
	}


	public Users getUserName() {
		return userName;
	}


	public void setUserName(Users userName) {
		this.userName = userName;
	}


	public Date getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public Time getAppointmentTime() {
		return appointmentTime;
	}


	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}


	public String getPrescription() {
		return prescription;
	}


	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
	
	
	
		
}