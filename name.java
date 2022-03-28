
public class name {

	private String firstname;
	private String lastname;

	// no-arg constructor for name object
	public name() {
		firstname = "";
		lastname = "";
	}

	// two-arg constructor for name object
	public name(String i, String j) {
		firstname = i;
		lastname = j;
	}

	// returns depositer firstname
	public String getFirstName() {
		return firstname;
	}

	// returns depositer lastname
	public String getLastName() {
		return lastname;
	}

	// allows setting of depositer firstname
	public void setfirstname(String i) {
		firstname = i;
	}

	// allows setting of depositer lastname
	public void setlastname(String i) {
		lastname = i;
	}

}
