
import java.util.Calendar;

public class check {
	private int accountnumber;
	private double checkamount;
	private Calendar date;

//no-arg check constructor
	public check() {
		accountnumber = 0;
		checkamount = 0.0;
		date = Calendar.getInstance();
		date.clear();
	}

//3-arg check constructor
	public check(int i, double j, String k) {
		accountnumber = i;
		checkamount = j;
		date = Calendar.getInstance();
		date.clear();
		String[] dateArray = k.split("/");
		date.set(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[0]) - 1, Integer.parseInt(dateArray[1]));

	}

//2-arg check constructor
	public check(double j, String k) {
		checkamount = j;
		date = Calendar.getInstance();
		date.clear();
		String[] dateArray = k.split("/");
		date.set(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[0]) - 1, Integer.parseInt(dateArray[1]));

	}

//returns check account number
	public int getacc() {
		return accountnumber;
	}

//returns double amount of check
	public double getcheckamount() {
		return checkamount;
	}

//allows the setting of a check account number
	private void setacc(int i) {
		accountnumber = i;
	}

//allows the setting of the check amount
	private void setcheckamount(double i) {
		checkamount = i;
	}

//returns the calendar date of a check
	public Calendar getCheckDate() {
		return date;
	}
//allows the setting of the check date from a string

	private void setdate(String i) {
		date.clear();
		String[] dateArray = i.split("/");
		date.set(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[0]) - 1, Integer.parseInt(dateArray[1]));

	}
}
