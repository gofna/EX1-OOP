package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

public class TestComplexFunctionOld {

	public static void main(String[] args) {
		//test1();
		String s = "";
		Polynom f1 = new Polynom("x^2");
		Polynom f2 = new Polynom("x^3+2x");
		ComplexFunction cf = new ComplexFunction("Divid",f1,f2);
		System.out.println(cf.toString());
		//ComplexFunction cf2 = new ComplexFunction("Plus",cf,f1);
		//System.out.println(cf2);
		cf.comp(f1);
		System.out.println(cf.toString());
		
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

}
