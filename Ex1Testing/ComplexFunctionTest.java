package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Ex1.ComplexFunction;
import Ex1.Polynom;
import Ex1.function;

/**
 * partial JUnit test for ComplexFunction class.
 * 
 * @author Gofna and Maor
 *
 */
class ComplexFunctionTest {

	@Test
	void testF() {
		ComplexFunction cf = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), new Polynom("7"));
		double expected = 64; 
		double actual = cf.f(3);
		assertEquals(expected, actual,"test if the result is what we expect with x = 3");
		
		ComplexFunction cf2 = new ComplexFunction("Times",new Polynom("x^2+3.5x-9"), cf);
		
		double expected2 = 1806; 
		double actual2 = cf2.f(4);
		
		assertEquals(expected2, actual2, "test f for  x = 4");
	}
	
	@Test
	void testComplexFunctionException() {
		 Assertions.assertThrows(RuntimeException.class, () -> {
			 new ComplexFunction("Plu",new Polynom("x^2+3+15x"), new Polynom("7")); //invalid operation
			  }); 
	}
	
	@Test
	void testCopy() { 
		ComplexFunction cf = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), new Polynom("7"));
		function cf2 =  cf.copy();
		
		assertEquals(cf, cf2, "we expected true beacase the two functions represent the same functions");
	}
	
	@Test
	void testInitFromString() {
		ComplexFunction cf = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), new Polynom("7"));
		ComplexFunction cf2 = new ComplexFunction(new Polynom("5+7.5x-x^2"));
		
		assertEquals(cf2, cf.initFromString("5+7.5x-x^2"));
	}

	@Test
	void testEquals() { 
		ComplexFunction cf = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), new Polynom("7"));
		ComplexFunction cf2 = new ComplexFunction(new Polynom("7"));
		ComplexFunction cf3 = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), cf2);
		
		assertEquals(cf, cf3,"test to check if cf and cf3 are equals");
	}
	
	@Test
	void testPlus() {
		ComplexFunction actual = new ComplexFunction("Plus",new Polynom("x^2+3+15x"), new Polynom("7"));
		ComplexFunction expected = new ComplexFunction(new Polynom("x^2+3+15x+7"));
		
		assertEquals(expected, actual,"cf2 is what we expexted to be after operation Plus");
		
	}
	
	@Test
	void testMul() {
		ComplexFunction actual = new ComplexFunction("Times",new Polynom("x^2+3+15x"), new Polynom("7"));
		ComplexFunction expected = new ComplexFunction(new Polynom("7x^2+21+105x"));
		
		assertEquals(expected, actual,"cf2 is what we expexted to be after operation Times");
	}
	
	@Test
	void testDiv() {
		ComplexFunction cf = new ComplexFunction("Divid",new Polynom("x^2+3+15x"), new Polynom("7x"));
		double expected = 2.8214285714285716;
		double actual = cf.f(4);
				
		assertEquals(expected, actual,"test for operation div ");
	}
	
	@Test
	void testMax() {
		ComplexFunction cf = new ComplexFunction("Max",new Polynom("x^2+3+15x"), new Polynom("x^3-x^2+5"));
		
		double expected = Math.max(cf.left().f(3.3), cf.right().f(3.3));
		double actual = cf.f(3.3);
		
		assertEquals(expected, actual,"test the operation Max with x = 3.3");
	}
	
	@Test
	void testMin() {
		ComplexFunction cf = new ComplexFunction("Min",new Polynom("x^2+3+x"), new Polynom("x^4-x^2+5"));
		
		double expected = Math.min(cf.left().f(3.3), cf.right().f(3.3));
		double actual = cf.f(3.3);
		
		assertEquals(expected, actual,"test the operation Min with x = 3.3");
	}
	
	@Test
	void testComp() {
		ComplexFunction cf = new ComplexFunction("Comp",new Polynom("x^2+3+x"), new Polynom("x^2"));
		
		double expected = 132.48209999999997;
		double actual = cf.f(3.3);
		
		assertEquals(expected, actual,"test the operation Comp with x = 3.3");
		
	}
	
	
}
