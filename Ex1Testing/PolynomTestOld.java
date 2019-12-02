package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

public class PolynomTestOld {
	public static void main(String[] args) {
		
		//test1();
		//test2();
		
		Polynom p1 = new Polynom("2x-2");
		Polynom p2 = new Polynom("4x+2");
		p1.add(p2);
		System.out.println(p1.toString());
	}

	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = { "5x^2", "x", "2" };
		for (int i = 0; i < monoms.length; i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		// System.out.println(p1.toString());
		p1.iteretor();

		Polynom p2 = new Polynom();
		String[] monoms2 = { "-2x^2", "2" };
		for (int i = 0; i < monoms2.length; i++) {
			Monom m2 = new Monom(monoms2[i]);
			p2.add(m2);
			// double aa = p1.area(0, 1, 0.0001);
			// System.out.println(aa);

		}
		System.out.println("\np1 : " + p1 + "\tisZero: " + p1.isZero() + "\tderivative: " + p1.derivative());
		System.out.println("\np2 : " + p2  + "\tisZero: " + p2.isZero() + "\tderivative: " + p2.derivative());
	}

	public static void test2() {
		System.out.println("\n\n-------------test2------------------"+ "\n");
		Polynom p1 = new Polynom(), p2 = new Polynom();
		String[] monoms1 = { "2", "-x", "-3.2x^2^", "4", "-1.5x^2" }; //throw exception even if one of the monoms is invalid
		String[] monoms2 = { "5", "1.7x", "3.2x^2", "-7", "-1.5x^2" }; //because in this test we test Polynoms as a whole unit.
		try {
		for (int i = 0; i < monoms1.length; i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		System.out.println("p1: " + p1);
		}
		catch(RuntimeException e){
			p1= new Polynom("");
			System.err.println("p1 : invalid Polynom");
			e.printStackTrace();
		}
		try {
		for (int i = 0; i < monoms2.length; i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p2: " + p2);
		}
		catch(RuntimeException e){
			p2= new Polynom("");
			System.err.println("p2 : invalid Polynom");
			e.printStackTrace();
		}

		Polynom p3 = new Polynom("1.7x^2+1.7x-2.0");
		System.out.println("\np3: " + p3);
		Polynom p4 = new Polynom("2.5x+2");
		System.out.println("\np4: " + p4);
		try {
		System.out.println("\nthe area in p3 above x-exis between x0=1 and x1=3 is  :  " + p3.area(1,2, Monom.EPSILON));
		}
		catch(RuntimeException e) {
			System.err.println("area function:");
			e.printStackTrace();
		}
		try {
		System.out.println("\nroot in p3 approximately is :  " + p3.root(0,2 ,Monom.EPSILON));
		}
		catch(RuntimeException e) {
			System.err.println("root function:");
				e.printStackTrace();
		}
		System.out.println("\nafter comper p2 with p3 :  " + p2.equals(p3)); //to check the consideration of the deviation in doubles variables
		p2.add(p3);
		System.out.println("\nafter p2+p3 , the new p2 is: " + p2);
		p3.multiply(p4); 
		System.out.println("\nafter p3*p4, p3 is now : " + p3);
		String s1 = p3.toString();
		Polynom_able pp1 = new Polynom(s1);
		System.out.println("\nfrom string p3 is: " + pp1);
		
	}
}