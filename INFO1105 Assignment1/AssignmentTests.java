import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AssignmentTests {

	// Set up JUnit to be able to check for expected exceptions
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// This will make it a bit easier for us to make Date objects
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	// Helper method to build the example calendar
	private Calendar BuildAppointments() {
		Calendar calendar = new Assignment();
		try {
			calendar.add("B", df.parse("2016/09/03 16:00:00"), "SIT lab 117");
			calendar.add("E", df.parse("2016/09/03 09:00:00"), "SIT lab 117");
			calendar.add("C", df.parse("2016/09/03 18:00:00"), "SIT lab 117");
			calendar.add("D", df.parse("2016/09/03 16:00:00"), "SIT lab 118");
			calendar.add("A", df.parse("2016/09/04 18:00:00"), "SIT lab 119");
			calendar.add("G", df.parse("2016/09/04 21:00:00"), "SIT lab 116");
			calendar.add("F", df.parse("2016/09/04 16:00:00"), "SIT lab 117");
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}
		return calendar;
	}

	@Test
	public void testGetonespecificAppointment() {

		Calendar calendar = BuildAppointments();

		// This should be only "A"
		List<Appointment> appointmentsA = calendar.getAppointments("SIT lab 119");
		// we'll just check the descriptions.
		List<String> descriptionsA = new ArrayList<String>();
		for (Appointment A : appointmentsA) {
			descriptionsA.add(A.getDescription());
		}
		// No need to be sorted because there is only one appointment
		Collections.sort(descriptionsA);
		// as we check the description matches the result we expect,
		// it represents the time of the appointments match the output we expect
		// as well.
		assertEquals(Arrays.asList("A"), descriptionsA);

		// This should be only "D"
		List<Appointment> appointmentsD = calendar.getAppointments("SIT lab 118");
		// we'll just check the descriptions.
		List<String> descriptionsD = new ArrayList<String>();
		for (Appointment D : appointmentsD) {
			descriptionsD.add(D.getDescription());
		}
		// No need to be sorted because there is only one appointment
		Collections.sort(descriptionsD);
		// as we check the description matches the result we expect,
		// it represents the time of the appointments match the output we expect
		// as well.
		assertEquals(Arrays.asList("D"), descriptionsD);

		// This should be only "G"
		List<Appointment> appointmentsG = calendar.getAppointments("SIT lab 116");
		// we'll just check the descriptions.
		List<String> descriptionsG = new ArrayList<String>();
		for (Appointment G : appointmentsG) {
			descriptionsG.add(G.getDescription());
		}
		// No need to be sorted because there is only one appointment
		Collections.sort(descriptionsG);
		// as we check the description matches the result we expect,
		// it represents the time of the appointments match the output we expect
		// as well.
		assertEquals(Arrays.asList("G"), descriptionsG);

	}

	@Test
	public void testGetAppointmentswithsamelocation() {

		Calendar calendar = BuildAppointments();

		// This should be a list containing Appointments B, E, C and F
		List<Appointment> appointments = calendar.getAppointments("SIT lab 117");

		// we'll just check the descriptions.
		// Good testing should be more thorough!
		List<String> descriptions = new ArrayList<String>();
		for (Appointment a : appointments) {
			descriptions.add(a.getDescription());
		}
		// Sorting the objects before we compare the list, since the assignment
		// doesn't require the output to be in any particular order
		Collections.sort(descriptions);
		// as we check the description matches the result we expect,
		// it represents the time of the appointments match the output we expect
		// as well.
		assertEquals(Arrays.asList("B", "C", "E", "F"), descriptions);

	}

	@Test
	public void testEmptyGetAppointment() {

		Calendar calendar = BuildAppointments();
		// test the appointment when the parameter is null
		List<Appointment> nullappointments = calendar.getAppointments("null");
		// get the description
		List<String> descriptions = new ArrayList<String>();
		for (Appointment b : nullappointments) {
			descriptions.add(b.getDescription());
		}
		// the expected Appointment List suppose to be empty.
		assertEquals(Arrays.asList(), descriptions);

	}

	@Test
	public void testAppointmentAfterRemove() {
		try {
			Calendar calendar = BuildAppointments();
			Appointment removetargetF = new MyAppointment("F", df.parse("2016/09/04 16:00:00"), "SIT lab 117");
			Appointment removetargetC = new MyAppointment("C", df.parse("2016/09/03 18:00:00"), "SIT lab 117");
			// this case we remove "F" first
			calendar.remove(removetargetF);
			// test the new appointments by using getDescription
			List<String> descriptions1 = new ArrayList<String>();
			List<Appointment> appointments = calendar.getAppointments("SIT lab 117");
			for (Appointment a : appointments) {
				descriptions1.add(a.getDescription());
			}
			// sorting the appointments
			Collections.sort(descriptions1);
			// the output suppose to be B,C,E
			assertEquals(Arrays.asList("B", "C", "E"), descriptions1);
			// this case we remove "C"
			calendar.remove(removetargetC);
			List<String> descriptions2 = new ArrayList<String>();
			List<Appointment> appointments2 = calendar.getAppointments("SIT lab 117");
			for (Appointment a : appointments) {
				descriptions2.add(a.getDescription());
			}
			// this time the new appointments suppose to be only B and E
			Collections.sort(descriptions2);
			assertEquals(Arrays.asList("B", "E"), descriptions2);
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}

	}

	@Test
	public void testGetNextAppointment() {

		Calendar calendar = BuildAppointments();
		// this should return "B" or "D".
		try {
			Appointment appointment = calendar.getNextAppointment(df.parse("2016/09/03 16:00:00"));
			String description = appointment.getDescription();
			assertTrue(description.equals("B") || description.equals("D"));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}

		// This should return null
		try {
			assertNull(calendar.getNextAppointment(df.parse("2020/01/01 13:00:00")));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}
	}

	@Test
	public void testGetNextAppointmentLocation() {

		Calendar calendar = BuildAppointments();

		// This should return Appointment B
		try {
			// This should return Appointment B
			Appointment appointmentB = calendar.getNextAppointment(df.parse("2016/09/03 16:00:00"), "SIT lab 117");
			String descriptionB = appointmentB.getDescription();
			assertTrue(descriptionB.equals("B"));
			// This should return Appointment A
			Appointment appointmentA = calendar.getNextAppointment(df.parse("2016/09/04 18:00:00"), "SIT lab 119");
			String descriptionA = appointmentA.getDescription();
			assertTrue(descriptionA.equals("A"));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}
	}

	@Test
	public void testIllegalArgument() {

		Calendar calendar = BuildAppointments();

		// Tell JUnit to expect an IllegalArgumentException
		thrown.expect(IllegalArgumentException.class);

		// This should cause an IllegalArgumentException to be thrown
		calendar.getNextAppointment(null);

	}

}