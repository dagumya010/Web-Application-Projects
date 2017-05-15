package mediFind.model;

public class Hospital extends HealthCareFacility{
	
	protected boolean erAvailability;
	
	public Hospital (Integer mediId, String name,Owners owner, Address address, boolean appointment, boolean parking, String hours,
			MyEnum timeliness, MyEnum effectiveness, MyEnum safety, boolean erAvailability) {
		super(mediId, name,owner, address, appointment, parking, hours, timeliness,	effectiveness, safety);
		this.erAvailability = erAvailability;
	}
	
	public boolean getErAvailability() {
		return erAvailability;
		
	}

	public void erAvailability(boolean erAvailability) {
		this.erAvailability = erAvailability;
	}	

}