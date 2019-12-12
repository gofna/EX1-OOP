package Ex1Testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;

class PolynomTest {
	
	@Test
	void DerivativeTest() {
	
	Polynom p1 = new Polynom("2x+2x^2+7");
		
	Polynom expected = new Polynom("2+4x");
	assertEquals(expected, p1.derivative());
	}
	
	@Test 
	void testZero() {
		Polynom p1 = new Polynom("0");
		assertTrue("check if the polynom is zero",p1.isZero());

		Polynom p2 = new Polynom("5x-5x");
		assertTrue("check if the polynom is zero",p2.isZero());	
		
		Polynom p3 = new Polynom("5x^2");
		assertFalse("check if the polynom is not zero",p3.isZero());	
	}
	
	@Test
	void testAddMonom() {
		Polynom p1 = new Polynom();
		String monoms[] =	{"3","3x","2x","x^7"}; 
		for (int i = 0; i < monoms.length; i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		Polynom expected = new Polynom("3+5x+x^7");
		assertEquals(expected, p1);
	}
	
	@Test
	void testAddPolynom() {
		Polynom p1 = new Polynom("2x+2x^3-x^2-10");
		Polynom p2 = new Polynom("5x+5+x^2");
		Polynom expected = new Polynom("7x+2x^3-5");
		p1.add(p2);
		assertEquals(expected,p1);
	}
	
	@Test
	void testMultiplyMonom() {
		Polynom p1 = new Polynom("2+3x+5x^3-x^2");
		Polynom expected = new Polynom("4x+6x^2+10x^4-2x^3");
		Monom m1 = new Monom("2x");
		p1.multiply(m1);
		assertEquals(expected,p1);
	}
	
	@Test 
	void testMuiltiplyPolynom() {
		Polynom p1 = new Polynom("2+x^2");
		Polynom p2 = new Polynom("x+3");
		Polynom expected = new Polynom("2x+6+x^3+3x^2");
		p1.multiply(p2);
		assertEquals(p1, expected);
	}
	
	@Test
	void testCopy() {
		Polynom p1 = new Polynom("2+3x+5x^3-x^2");
		function p2 = p1.copy();
		assertTrue(p1.equals(p2), "check the copy in the polynom class" );	
	}
	
	@Test
	void testArea() {
		Polynom p1 = new Polynom("1.7x^2+1.7x-2.0");
		double actual = p1.area(1,2, Monom.EPSILON);
		double expected = 4.516666328817058;
		assertEquals(expected, actual, "test the area function in polynom");
	}
	
	@Test
	void testRoot() {
		Polynom p1 = new Polynom("1.7x^2+1.7x-2.0");
		double actual = p1.root(0,2 ,Monom.EPSILON);
		double expected = 0.6943494379520416;
		assertEquals(expected, actual);
	}
	
	@Test
	void testPolynomException() {
		
		 Assertions.assertThrows(RuntimeException.class, () -> {
			 new Polynom("4tx^3+50");
			  }); 
	}
	

}