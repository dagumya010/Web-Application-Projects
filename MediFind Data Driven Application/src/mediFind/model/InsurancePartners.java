package mediFind.model;


public class InsurancePartners {
	protected Insurance insurance;
	protected HealthCareFacility mediFacility;
		
	public InsurancePartners(Insurance insuranceId, HealthCareFacility mediFacilityId) {
		this.insurance = insuranceId;
		this.mediFacility = mediFacilityId;
		
	}
	
	public InsurancePartners(Insurance insuranceId) {
		this.insurance = insuranceId;
	}
	
	public Insurance getInsuranceIdd() {
		return insurance;
	}

	public void setInsuranceId(Insurance insuranceId) {
		this.insurance = insuranceId; 
	} 

	public HealthCareFacility getMediFacilityId() {
		return mediFacility;
	}

	public void setMediFacilityId(HealthCareFacility mediFacilityId) {
		this.mediFacility = mediFacilityId;
	}

	
}
