package mediFind.model;


public class HealthCareFacility {

	protected int  mediFacilityId;
	protected String Name;
	protected Owners owner;
	protected Address address;
	protected boolean appointment;
	protected boolean parking;
	protected String hours;
	protected MyEnum timeliness;
	protected MyEnum effectiveness;
	protected MyEnum safety;
	
	public enum MyEnum {
		BELOW("Below the National average"),
		ABOVE("Above the National average"),
		SAME("Same as the National average");
		 
		private String name;
		 MyEnum(String name)
		 {
			 this.name=name;
		 }
		 
		 public String getName()
		 {
			 return this.name;
		 }
	}

	/**
	 *  Constructor
	 */
	public HealthCareFacility(int mediFacilityId,String mediFacilityName, Owners owner, Address address, boolean appointment, boolean parking,
			String hours, MyEnum timeliness, MyEnum effectiveness, MyEnum safety) {

		this.Name = mediFacilityName;
		this.mediFacilityId = mediFacilityId;
		this.owner = owner;
		this.address = address;
		this.appointment = appointment;
		this.parking = parking;
		this.hours = hours;
		this.timeliness = timeliness;
		this.effectiveness = effectiveness;
		this.safety = safety;
	}
	
	public HealthCareFacility(String mediFacilityName,Owners owner, Address address, boolean appointment, boolean parking, String hours,
			MyEnum timeliness, MyEnum effectiveness, MyEnum safety) {
		this.Name = mediFacilityName;
		this.owner = owner;
		this.address = address;
		this.appointment = appointment;
		this.parking = parking;
		this.hours = hours;
		this.timeliness = timeliness;
		this.effectiveness = effectiveness;
		this.safety = safety;
		
	}
	
	public HealthCareFacility(int mediFacilityId) {
		this.mediFacilityId = mediFacilityId;
	}

	public int getMediFacilityId() {
		return mediFacilityId;
	}

	public void setMediFacilityId(int mediFacilityId) {
		this.mediFacilityId = mediFacilityId;
	}
	

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Owners getOwner() {
		return owner;
	}

	public void setOwner(Owners owner) {
		this.owner = owner;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isAppointment() {
		return appointment;
	}

	public void setAppointment(boolean appointment) {
		this.appointment = appointment;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public MyEnum getTimeliness() {
		return timeliness;
	}

	public void setTimeliness(MyEnum timeliness) {
		this.timeliness = timeliness;
	}

	public MyEnum getEffectiveness() {
		return effectiveness;
	}

	public void setEffectiveness(MyEnum effectiveness) {
		this.effectiveness = effectiveness;
	}

	public MyEnum getSafety() {
		return safety;
	}

	public void setSafety(MyEnum safety) {
		this.safety = safety;
	}
	
	
	
	
	
	
	
	
}