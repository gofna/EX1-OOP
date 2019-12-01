package Ex1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


/**
 * This class represents a Polynom used ArrayList with add, multiply
 * functionality, it also should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {
	private ArrayList<Monom> arrayList = new ArrayList<Monom>();

	/**
	 * build empty Polynom
	 */
	public Polynom() {
		this.arrayList = new ArrayList<Monom>();
	}

	/**
	 * init a Polynom from a String such as: {"x", "3+1.4X^3-34x"};
	 * 
	 * @param s is a string represents a Polynom
	 */
	public Polynom(String s) {
		String term = s.replace("-", "+-"); // to mark "+" as a token for split the polynom
		this.arrayList = new ArrayList<Monom>();
		StringTokenizer st = new StringTokenizer(term, "+"); // to split the polynom to monoms
		while (st.hasMoreTokens()) {
			String q = st.nextToken();
			Monom m = new Monom(q);

			add(m);
			sort(arrayList);
		}

	}
	
	public function initFromString(String s) {
		function m = new Polynom(s);
		return m;
	}
	
	private void sort(ArrayList<Monom> arr) { // use the Monom_comperator to sort the polynom by the power
		Monom_Comperator x = new Monom_Comperator();
		arr.sort(x);

	}

	/**
	 * 
	 * the function calculate f(x) when f is this Monom
	 * 
	 * @param x the x to place in this Monom
	 * @return the result after placing x in the Monom
	 * 
	 **/
	@Override
	public double f(double x) {
		double sum = 0;
		for (int i = 0; i < arrayList.size(); i++) {
			sum = sum + arrayList.get(i).f(x);
		}
		return sum;
	}

	/**
	 * Add p1 to this Polynom
	 * 
	 * @param p1 the Polynom to add to this Polynom
	 * 
	 */
	@Override
	public void add(Polynom_able p1) { // going through all the Polynom list and add one by one (we use the add Monom
										// to polynom)
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()) {
			this.add(it.next());
		}

	}

	/**
	 * Add m1 to this Polynom
	 * 
	 * @param m1 the Monom to add to this Polynom
	 * 
	 */
	@Override
	public void add(Monom m1) { // going through all the Polynom list and check where to add the Monom (check if
		Monom temp = new Monom(m1);						// the power is equals)
		this.arrayList.add(temp);
		for (int i = 0; i < arrayList.size() - 1; i++) {
			if (this.arrayList.get(i).get_power() == m1.get_power()) {
				this.arrayList.get(i).add(m1);
				this.arrayList.remove(arrayList.size() - 1);
			}
		}
		sort(this.arrayList);
	}

	/**
	 * Subtract p1 from this Polynom
	 * 
	 * @param p1 the Polynom to subtract from this Polynom
	 * 
	 */
	@Override
	public void substract(Polynom_able p1) { // copy the Polynom we want to add to new Polynom and
		Polynom_able p2 = new Polynom(); // we multiply by -1 and then we use the function add Polynom
		p2 = p1.copy(); // delete the new Polynom and multiply by -1 again !!!!!!!!!!!!!!!!!!!!!!!!!!
		p2.multiply(Monom.MINUS1);
		this.add(p2);

	}

	/**
	 * Multiply this Polynom by p1
	 * 
	 * @param p1 the Polynom to multyply eith this Polynom
	 * 
	 */
	@Override
	public void multiply(Polynom_able p1) { // going through all the Polynom and multiply every Monom by the Polynom
		Polynom_able res = new Polynom();
		Polynom_able temp = new Polynom();
		Iterator<Monom> it = p1.iteretor();
		temp = this.copy();
			while (it.hasNext()) {
				temp.multiply(it.next());
				res.add(temp);
			temp = new Polynom("");
			temp = this.copy();
			}
		
		if (p1.isZero()) { // if the Polynom we want to multiply is 0 we clear all the Polynom and add 0
							// (to avoid polynom like 0+0+0)
			this.arrayList.clear();
			this.add(Monom.ZERO);
		}
		this.arrayList.clear();
		this.add(res);
	}

	/**
	 * Multiply this Polynom by Monom m1
	 * 
	 * @param m1 the Monom to multiply with this Polynom
	 * 
	 */
	@Override
	public void multiply(Monom m1) {
		for (int i = 0; i < this.arrayList.size(); i++) { // going through all the Monoms in the Polynom and Multiply
															// one by one in the Monom
			this.arrayList.get(i).multiply(m1);
		}
		if (m1.isZero()) {
			this.arrayList.clear();
			this.add(Monom.ZERO);
		}

	}
	

	/**
	 * Test if this Polynom is logically equals to p1.
	 * 
	 * @param p1 the polynom to check with this Polynom
	 * @return true if this polynom represents the same function as p1
	 * 
	 */
	@Override
	public boolean equals (Object p1) {
		if(p1 instanceof Polynom) {
		Iterator<Monom> it = ((Polynom) p1).iteretor();
		int count = 0;
		while (it.hasNext()) {
			it.next();
			count++;
		}
		Iterator<Monom> it2 = ((Polynom) p1).iteretor();
		if (count != this.arrayList.size()) { // check if the size is the same
			return false;
		} else {
			for (int i = 0; i < this.arrayList.size(); i++) { // use the equals from the Monom class
				if (!this.arrayList.get(i).equals(it2.next())) {
					return false;
				}
			}
		}
		return true;
		}
		else {return false;}
	}

	/**
	 * Test if this is the Zero Polynom
	 * 
	 * @return true if it's the zero Polynom
	 * 
	 */
	@Override
	public boolean isZero() {
		if (this.arrayList.size() == 0 || this.arrayList.get(0).isZero()) { // if the first Monom is zero and if the
																			// list is empty its zero
			return true;
		}

		return false;
	}

	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps assuming
	 * (f(x0)*f(x1)<=0, else should throws runtimeException computes f(x') such
	 * that: (i) x0<=x'<=x1 && (ii) |f(x')|<eps
	 * 
	 * @param x0 starting point
	 * @param x1 end point
	 * @param    eps>0 (positive) representing the epsilon range the solution should
	 *           be within.
	 * @return an approximated value (root) for this (cont.) function
	 * 
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if (eps <= 0 || this.f(x0) * this.f(x1) > 0 || x0 > x1) { // check if eps is negative , f(x0)*f(x1) is positive
			throw new RuntimeException("can't find f(x) = 0. try another variabels");
		}
		double mid = (x0 + x1) / 2;
		while (Math.abs(this.f(mid)) >= eps) { // get close to 0 (like binary serach)
			if (this.f(mid) > 0) { // get close to 0 with the positive side
				if (this.f(x0) > 0) {
					x0 = mid;
				} else
					x1 = mid;
			}
			if (this.f(mid) < 0) { // get close to 0 with the negative side
				if (this.f(x0) < 0) {
					x0 = mid;
				} else
					x1 = mid;
			}
			mid = (x0 + x1) / 2;
		}
		return mid;
	}

	/**
	 * create a deep copy of this Polynom
	 * 
	 * @return the copy of this Polynom
	 * 
	 */
	@Override
	public Polynom_able copy() { // creat a new Polynom and use add function to the new Polynom
		Polynom_able po = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while (it.hasNext()) { // deep copy over one by one
			Monom m = new Monom(it.next());
			po.add(m);
		}
		return po;
	}
//	public function copy1() { // clone
//		function m = new Polynom(this.toString());
//		return m;
//	}

	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * 
	 * @return the new Polynom after derivative this Polynom
	 * 
	 */
	@Override
	public Polynom_able derivative() { //
		Polynom_able p = new Polynom();
		int length = this.arrayList.size();
		if (this.arrayList.get(this.arrayList.size() - 1).get_power() == 0) {// if the last monom is a number we want to
																				// avoid derivative such as - ax^b+0
																				// (with 0 in the end)
			if (length == 1) {
				p.add(Monom.ZERO);
				return p;
			} else {

				length--;
			}
		}
		for (int i = 0; i < length; i++) { // going through all the Polynom and use the derivative function in the Monom
											// class
			p.add(this.arrayList.get(i).derivative());
		}
		return p;
	}

	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps. assuming eps is
	 * smaller the the difference between x1 and x0. else should throws
	 * runtimeException.
	 * 
	 * @param x0  starting point
	 * @param x1  end point
	 * @param eps positive step value
	 * @return the approximated area above X-axis below this function bounded in the
	 *         range of [x0,x1]
	 * 
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if (x1 <= x0) {
			return 0;// there is no area between
		}
		if (eps <= 0 || eps > x1 - x0) { // if epsilon negative or bigger then the difference between x1 and x0
			throw new RuntimeException("epsilon is not legal");
		}
		double ans = 0;
		for (double i = x0; i <= x1; i = i + eps) {// Compute a Riman's integral from x0 to x1 in eps steps.
			if (this.f(i) > 0) { // we add just the area above X-axis
				ans = ans + eps * this.f(i);
			}
		}
		return ans;
	}

	/**
	 * use the ArrayList's Iterator in java
	 * 
	 * @return an Iterator (of Monoms) over this Polynom
	 * 
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> iter = arrayList.iterator();
		return iter;
	}

	@Override
	public String toString() {
		String ans = "";
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).get_coefficient() < 0) {
				ans = ans + arrayList.get(i);
			} else {
				if (i == 0) {
					ans = ans + arrayList.get(i);
				} else {
					ans = ans + "+" + arrayList.get(i);

				}
			}
		}
		return ans;
	}

}
