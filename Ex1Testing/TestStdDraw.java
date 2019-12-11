package Ex1Testing;

import java.io.IOException;
import java.util.Iterator;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.StdDraw;
import Ex1.complex_function;
import Ex1.function;

public class TestStdDraw {
	public static void main(String[] args) {
		//test1();
		//test2();
		Testboaz();
		//Functions_GUI f = new Functions_GUI();
		//f.add(new Polynom("-x^3+8"));
		//f.add(new Polynom("x^2"));
		//f.add(new Polynom("x"));
		//f.add(new Polynom("x^4+10"));
		//f.add(new Polynom("x^2+3x-7"));
		Range rx = new Range(-10, 10);
		Range ry = new Range(-10, 15);
		//f.drawFunctions(1000, 600, rx , ry , 200);
		ComplexFunction cf = new ComplexFunction(new Polynom("x^2+4x"));
		//f.add(cf);
		//System.out.println(Operation.Plus.toString());
		//f.drawFunctions("GUI_params.txt");
		//Iterator<function> iter = f.iterator();
		//function fun = iter.next();
	//	ComplexFunction f1 = new ComplexFunction(fun);
		//System.out.println(f1.toString());
		
		String[] s3 = {"x+3", "x-2", "x-4"};
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		ComplexFunction cf4 = new ComplexFunction("Divid", new Polynom("x+1"),cf3);
		cf4.plus(new Monom("2"));
		//f.add(cf4);
		//f.add(cf4.copy());
		//f.drawFunctions("GUI_params.txt");

	}
	
	public static void Testboaz() {
		Functions_GUI f = new Functions_GUI();
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "5+2x-3.3x+0.1x^5";
		String[] s3 = {"x+3", "x-2", "x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		
		ComplexFunction cf = new ComplexFunction("Plus", p1,p2);
		ComplexFunction cf4 = new ComplexFunction("Divid", new Polynom("x+1"),cf3);
		cf4.plus(new Monom("2"));
		f.add(cf.copy());
		f.add(cf4);
		cf.div(p1);
		f.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		f.add(cf5);
		f.add(cf6.copy());
		Iterator<function> iter = f.iterator();
		function f1 = iter.next();
		ComplexFunction max = new ComplexFunction(f1);
		ComplexFunction min = new ComplexFunction(f1);
		while(iter.hasNext()) {
			f1 = iter.next();
			max.max(f1);
			min.min(f1);
		}
		function cf7 = cf4.copy(); 
		f.add(max);
		f.add(min);	
		f.drawFunctions("GUI_params.txt");
		System.out.println(cf7.toString());
		System.out.println(cf4.toString());
		System.out.println(cf4.copy().toString());
		System.out.println("calc cf4 " + cf4.f(4.000001));
		System.out.println("calc cf7 " + cf7.f(4.000001));
		
		System.out.println(f.list.toString());
		try {
			f.saveToFile("maor.txt");
			
		}
		catch(IOException e){
			e.printStackTrace();
			System.err.println("file Not found");
		}
	}

	public static void test1() {
		for (double t = 0.0; true; t += 0.02) {
			double x = Math.sin(t);
			double y = Math.cos(t);
			StdDraw.clear();
			StdDraw.filledCircle(x, y, 0.05);
			StdDraw.filledCircle(-x, -y, 0.05);
			StdDraw.show();
			StdDraw.pause(20);
		}
	}

	public static void test2() {
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(0.5, 0.5);
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.line(0.2, 0.2, 0.8, 0.2);
	}
}
