//for creating the tree 

public class Node {
	MyAppointment data;
	Node left;
	Node right;	
	
	public Node(MyAppointment data){
		this.data = data;
		left = null;
		right = null;
	}
	// this is used for checking whether the expected appointments match the actual appointments
	public boolean AppointmentEqual(Appointment appointment){
		if(data.getDescription().equals(appointment.getDescription())
				&& data.getLocation().equals(appointment.getLocation())
						&& data.getStartTime().compareTo(appointment.getStartTime())==0)
			return true;
		else 
			return false;
	}
}
