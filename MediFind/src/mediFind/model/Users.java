package mediFind.model;

public class Users {

	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String phoneNumber;
	protected String email;
	protected Address address;
	protected Insurance insurance;
	

	// This constructor can be used for reading records from MySQL, where we
	// have all fields,
	// including the userName.
	public Users(String user, String password, String firstName, String lastName, String phoneNumber, String email,
			Address address, Insurance insurance) {
		this.userName = user;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.insurance = insurance;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Insurance getInsurance() {
		return insurance;
	}


	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
