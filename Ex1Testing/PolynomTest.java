package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Polynom;
import Ex1.Polynom_able;

class PolynomTest {

	@Test
	void DerivativeTest() {
	Polynom test = new Polynom("2x+2x^2");
	Polynom expected = new Polynom("2+4x");
	System.out.println("the origin Polynom : " +test);
	System.out.println("derivative we expecte : " +expected);
	Polynom_able actual = test.derivative();
	assertEquals(expected, actual);
	
	}
}
