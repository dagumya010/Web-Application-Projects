package mediFind.model;

public class Pharmacy extends HealthCareFacility{
	
	
	
	public Pharmacy(Integer mediId,String name,Owners owner, Address address, boolean appointment, boolean parking, String hours,
			MyEnum timeliness, MyEnum effectiveness, MyEnum safety) {
		super(mediId,name,owner, address, appointment, parking, hours, timeliness,	effectiveness, safety);		
	}
	


}