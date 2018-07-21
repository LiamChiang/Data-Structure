import java.util.Date;
import java.util.List;

public class Assignment implements Calendar {
	
	// The default constructor for the class should be public
	// We will use this when we test your code!
	public Assignment() {
	}

	@Override
	public List<Appointment> getAppointments(String location) {
		// TODO Implement this! (then remove this TODO comment)
		return null;
	}

	@Override
	public Appointment getNextAppointment(Date when) {
		if(when == null) {
			throw new IllegalArgumentException("time was null");
		}
		// TODO Implement this! (then remove this TODO comment)
		return null;
	}

	@Override
	public Appointment getNextAppointment(Date when, String location) {
		// TODO Implement this! (then remove this TODO comment)
		return null;
	}

	@Override
	public void add(String description, Date when, String location) {
		// TODO Implement this! (then remove this TODO comment)
	}

	@Override
	public void remove(Appointment appointment) {
		// TODO Implement this! (then remove this TODO comment)
	}
}
