import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestMethodsandAppointments {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static void main(String[] args) throws ParseException {

		Assignment calendar = new Assignment();
		calendar.add("B", df.parse("2016/09/03 16:00:00"), "SIT lab 117");
		calendar.add("E", df.parse("2016/09/03 09:00:00"), "SIT lab 117");
		calendar.add("C", df.parse("2016/09/03 18:00:00"), "SIT lab 117");
		calendar.add("D", df.parse("2016/09/03 16:00:00"), "SIT lab 118");
		calendar.add("A", df.parse("2016/09/04 18:00:00"), "SIT lab 119");
		calendar.add("G", df.parse("2016/09/04 21:00:00"), "SIT lab 116");
		calendar.add("F", df.parse("2016/09/04 16:00:00"), "SIT lab 117");

		// This tree suppose to print out ordered appointment list: E, B, D, C,
		// F, A, G
		System.out.print("The Appointments are: ");
		calendar.display(calendar.root);
		System.out.println();
		System.out.println();
		// Get next appointment (Time)
		// Case1
		System.out.println("Tets the getnextAppointment:");
		System.out.println("1.The next node is: "
				+ calendar.getNextAppointment(df.parse(" 2016/09/03 18:00:00 ")).getDescription());
		// Case2
		System.out.println("2.The next node is: "
				+ calendar.getNextAppointment(df.parse(" 2016/09/04 18:00:00 ")).getDescription());
		// Get next appointment (Time,Location)
		// Case3
		System.out.println("3.The next node is: "
				+ calendar.getNextAppointment(df.parse(" 2016/09/03 16:00:00 "), "SIT lab 118").getDescription());
		// Case4
		System.out.println("4.The next node is: "
				+ calendar.getNextAppointment(df.parse(" 2016/09/04 16:00:00 "), "SIT lab 117").getDescription());
		System.out.println();

		// Test the Appointments after remove some nodes.
		System.out.println("Test the remove method: ");
		// Delete the node: C,G and D
		Appointment removetarget1 = new MyAppointment("C", df.parse("2016/09/03 18:00:00"), "SIT lab 117");
		Appointment removetarget2 = new MyAppointment("G", df.parse("2016/09/04 21:00:00"), "SIT lab 116");
		Appointment removetarget3 = new MyAppointment("D", df.parse("2016/09/03 16:00:00"), "SIT lab 118");
		calendar.remove(removetarget1);
		calendar.remove(removetarget3);
		calendar.remove(removetarget2);
		System.out.print("The new Appointments are: ");
		calendar.display(calendar.root);
		System.out.println();
		//
		System.out.println("Add two more appointments: ");
		calendar.add("K", df.parse("2016/09/05 19:00:00"), "SIT lab 113");
		calendar.add("P", df.parse("2016/09/03 12:00:00"), "SIT lab 119");
		//
		// This appointment list suppose to print: E, P, B, F, A, K
		System.out.print("The new Appointments are: ");
		calendar.display(calendar.root);

	}

}
