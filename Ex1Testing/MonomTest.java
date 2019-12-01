package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.Monom;


class MonomTest {
	
	@BeforeEach
	void makeMonom() {
		System.out.println("@Before  Each test : ");
	}
	
//	@Test ???????????????(fail)
//	void makeMonomException() {
//		Monom m1 = new Monom("4tx^3");
//		Monom m2 = new Monom("2x^3.2");
//		Monom m3 = new Monom("wx^3");
//		//fail("we dont accept Monom in this shape");
//	}

	@Test
	void equal() {
		Monom m1 = new Monom("4x^3");
		Monom m2 = new Monom("4x^3");
		assertEquals(m1,m2, "test if the two Monom are equals"); // check if equals
	}
	@Test
	void equalException() {
		Monom m1 = new Monom("x^3");
		Monom m2 = new Monom("4x^3");
		try {
			//assertEquals(m1, m2); // will fail the test 
			//fail("should throw exception");
		}
		catch (Exception e) {	
		}
	}
	@Test
	void testIsZero() {
		Monom m1 = new Monom("0"); //Monom with String 
		Monom m2 = new Monom(0,0); // Monom (0*x^0)
		Monom m3 = new Monom(0,5); // Monom (0*x^5)
		assertEquals(m1, Monom.ZERO); // use the zero in the Monom class
		assertEquals(m2, Monom.ZERO); 
		assertEquals(m3, Monom.ZERO); 
	}
	
	@Test
	void mul() {
		Monom m1 = new Monom("4x^3"); 
		Monom m2 = new Monom("2x^3");
		Monom expected = new Monom("8x^6"); // the Monom we should expect 
		m1.multiply(m2); // m1 is the actual
		assertEquals(m1,expected , "test multiply with two Monoms");
	}
	
	@Test
	void testAdd() {
		Monom m1 = new Monom("4x");
		Monom m2 = new Monom("2x");
		Monom expected = new Monom("6x"); // the Monom we should expect (must have the same power)
		m1.add(m2); // m1 is the actual
		assertEquals(m1,expected , "test add with two Monoms");
	}
	
//	@Test ????
//	void testAddException() {
//		
//		try {
//			Monom m1 = new Monom("4x");
//			Monom m2 = new Monom("2x");
//			Monom expected = new Monom("9x"); // the Monom we should expect (must have the same power)
//			m1.add(m2);
//			assertEquals(expected,m1 , "test add with two Monoms"); // will fail the test
//			fail("the add do not throw exception by add two monoms with different power");
//		}
//		catch (Exception e){	
//		}
//	}
	
	@Test
	void testDerivative() {
		Monom m1 = new Monom("2x^3");
		Monom expected1 = new Monom("6x^2");
		 
		assertEquals(expected1,m1.derivative()); //expected and the actual that is m1.derivative 
		
		Monom m2 = new Monom("8x");
		Monom expected2 = new Monom("8");
		
		assertEquals(expected2, m2.derivative()); //expected and the actual that is m2.derivative 
		
		Monom m3 = new Monom("10");
		
		assertEquals(Monom.ZERO,m3.derivative()); //use the 0 Monom the expected should be zero
	}
	
	@Test
	void testF() {
		double x = 2; // the x ,if the x changes have to change all the expected too
		Monom m1 = new Monom("10x^2");
		double expected1 = 40;
		double actual1 = m1.f(x);
		assertEquals(expected1, actual1 , "compare the result of f(x) and the number we expected");  
		
		Monom m2 = new Monom("8x");
		double expected2 = 16;
		double actual2 = m2.f(x);
		assertEquals(expected2, actual2);
		
		Monom m3 = new Monom("10");
		int expected3 = 10;
		double actual3 = m3.f(x);
		assertEquals(expected3, actual3);
	}
	
	
	
}
