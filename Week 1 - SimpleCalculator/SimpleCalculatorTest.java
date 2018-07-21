import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleCalculatorTest {
	
	static final double epsilon = 0.000001;

	@Test
	public void testAddition() {
		
		SimpleCalculator calc = new SimpleCalculator();
		
		assertEquals(2.0, calc.add(1, 1), epsilon);
		assertEquals(1.0, calc.add(0.5, 0.5), epsilon);
		assertEquals(0.0, calc.add(0.5, -0.5), epsilon);
		assertEquals(0.0, calc.add(-0.5, 0.5), epsilon);
		assertEquals(-1.0, calc.add(-0.5, -0.5), epsilon);

		assertEquals(2.6, calc.add(2.6, 0.0), epsilon);
	}
	
	@Test
	public void testSubtraction() {
		fail("test not implemented");
	}
	
	@Test
	public void testMultiplication() {
		fail("test not implemented");
	}

	@Test
	public void testDivision() {
		fail("test not implemented");
	}
	
	@Test
	public void testFloatingPointError() {
		
		double epsilon = 0.0;
		SimpleCalculator calc = new SimpleCalculator();

		double x = 0.0;
		x = calc.add(x, 0.1);
		x = calc.add(x, 0.1);
		x = calc.add(x, 0.1);
		x = calc.add(x, 0.1);
		x = calc.add(x, 0.1);
		x = calc.add(x, 0.1);
		x = calc.add(x, 0.1);
		x = calc.add(x, 0.1);
		x = calc.add(x, 0.1);
		x = calc.add(x, 0.1);
		assertEquals(1.0, x, epsilon); //uses locally scoped epsilon

	}

}