
public class depositer {
	private name nm;
	private String ssn;

//no-arg depositer contructor
	public depositer() {
		nm = new name();
		ssn = "";
	}

//1-arg depositer constructor
	public depositer(String i) {
		ssn = i;
		nm = new name();
	}

//3 arg depositer contructor
	public depositer(String i, String first, String last) {
		ssn = i;
		nm = new name(first, last);
	}

	public depositer(name i, String j) {
		nm = i;
		ssn = j;
	}

//returns the name object
	public name getName() {
		return nm;
	}

//returns the depositer ssn
	public String getSSN() {
		return ssn;
	}

//allows the setting of deposter ssn
	private void setssn(String i) {
		ssn = i;
	}

//allows the setting of depositer name object
	private void setname(name i) {
		nm = i;
	}
}
