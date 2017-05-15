package mediFind.model;

public class Clinic extends HealthCareFacility{
	
  public Clinic(Integer mediId,String Name,Owners owner, Address address, boolean appointment, boolean parking,
			String hours, MyEnum timeliness, MyEnum effectiveness, MyEnum safety){
	  
	  super(mediId,Name,owner, address, appointment, parking,
				hours, timeliness, effectiveness, safety);
	  
  }


}
