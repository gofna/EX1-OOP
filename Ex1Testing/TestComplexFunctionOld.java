package Ex1Testing;

import java.io.IOException;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

public class TestComplexFunctionOld {

	public static void main(String[] args) {
		Functions_GUI f = new Functions_GUI();
		f.add(new Polynom("3x^5"));
		
		ComplexFunction cf = new ComplexFunction("Diveid",new Polynom("x^2+3+15x"), new Polynom("7x"));
		
		System.out.println(cf.toString());
				
		//test3();
		//test1();
		//test2();
//		String s = "Times(3,Plus(Times(x+3,x-2),Plus(x^2,x)))";
//		Polynom f1 = new Polynom("x^2");
//		Polynom f2 = new Polynom("x^3");
//		ComplexFunction maor = new ComplexFunction("Plus",f1, f2 );
//		maor.mul(f1);
//		function cf5 = maor.initFromString(s);
		//System.out.println("cf maor is : " + maor.toString());
		//System.out.println( "complex init from string : "+ cf5 );
//		ComplexFunction gofna = new ComplexFunction("Plus",f1, maor );
//		System.out.println("complex is " + gofna + "\n");
//		gofna.mul(f1);
//		System.out.println("gofna is now : " + gofna + "\n");
//		System.out.println(gofna.f(2));
//		
//		gofna.mul(f1);
//		
//		System.out.println("gofna is now : " + gofna + "\n");
//		System.out.println(gofna.f(2));
		
	}
	public static void test3() {
		Functions_GUI f = new Functions_GUI();
		f.add(new Polynom("3x^2"));
		f.add(new ComplexFunction("Plus", new Polynom("x^2-2"), new Polynom("7")));
		System.out.println(f.list.toString());
//		try {
//			f.saveToFile("maor.txt");
//		}
//		catch(IOException e){
//			e.printStackTrace();
//			System.err.println("file Not found");
//		}
		try {
			f.initFromFile("maor.txt");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("file Not found");
		}
	}
	
	public static void test1() {
		function f1 = new Polynom("x^2");
		function f2 = new Polynom("x^3+2x");
		function f3 = new Polynom("x");
		ComplexFunction gofna = new ComplexFunction("Plus",f1, f2 );
		System.out.println(gofna.toString());
		gofna.mul(f3);
		System.out.println(gofna.toString());
		gofna.mul(new Polynom("2"));
		System.out.println(gofna.toString());
		System.out.println();
		
	}
	
	public static void test2() {
		
		Polynom f1 = new Polynom("x^2");
		Polynom f2 = new Polynom("x^3+2x");
		ComplexFunction cf = new ComplexFunction("Divid",f1,f2);
		System.out.println("cf is " + cf.toString());
		System.out.println();
		ComplexFunction cf2 = new ComplexFunction("Max",f1,f2);
		//cf.plus(cf2);
		System.out.println("cf2 is: " + cf2.toString());
		System.out.println();
		
		cf.mul(cf2);
		System.out.println("after mul cf:  " + cf.toString());
		
		ComplexFunction cf3 = new ComplexFunction("None",null,f2);
		System.out.println(cf3.toString());
		
		System.out.println("_________test one build______________");
		
		Polynom f3 = new Polynom("x^2+2");
		ComplexFunction cf4 = new ComplexFunction(f3);
		System.out.println("cf4 is : " + cf4.toString());
		
		cf4.plus(f1);
		cf4.mul(f1);
		
		System.out.println("after cf4 is : " + cf4.toString());
	}

}
