
//create a new methods for replacing the original class "appointment" for easily using the methods to getappointment

import java.util.Date;

public class MyAppointment implements Appointment {

	private String description;
	private Date when;
	private String location;

	public MyAppointment(String description, Date when, String location) {
		this.description = description;
		this.when = when;
		this.location = location;

	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public Date getStartTime() {
		return when;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setStartTime(Date when) {
		this.when = when;
	}
}
