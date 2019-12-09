package Ex1Testing;

import Ex1.Functions_GUI;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.StdDraw;

public class TestStdDraw {
	public static void main(String[] args) {
		//test1();
		//test2();
		Functions_GUI f = new Functions_GUI();
		f.add(new Polynom("-x^3+8"));
		f.add(new Polynom("x^2"));
		f.add(new Polynom("x"));
		f.add(new Polynom("x^4+10"));
		f.add(new Polynom("x^2+3x-7"));
		Range rx = new Range(-10, 10);
		Range ry = new Range(-10, 15);
		f.drawFunctions(1000, 600, rx , ry , 200);


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
