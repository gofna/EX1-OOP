
package myMath;

import java.util.Comparator;
import java.util.concurrent.ExecutionException;

import javax.management.RuntimeErrorException;

/**
 * This class represents a simple "Monom" of shape ax^b, where a is a real
 * number and b is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Boaz
 *
 */
public class Monom implements function {
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	/**
	 * constructor that get the coefficient and the power and build the Monom
	 * 
	 * @param a the coefficient we get
	 * @param b the power we get
	 */
	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	/**
	 * copy constructor
	 * @param ot the Monom to copy to new one
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * get function 
	 * @return this coefficient
	 */
	public double get_coefficient() {
		return this._coefficient;
	}

	/**
	 * get function
	 * @return this power
	 */

	public int get_power() {
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 * 
	 * @return derivative monom
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}

	/**
	 * this function return the result of simple function of type y=f(x) when the
	 * function is the monom.
	 * 
	 * @param x is the number to insert in f
	 */
	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	/**
	 * the function check if the monom is 0.
	 * 
	 * @return true if the monom is 0.
	 */

	public boolean isZero() {

		return this.get_coefficient() == 0;
	}

	/**
	 * check if coefficient is negative
	 * @return true if the coefficient is negative
	 */
	public boolean minus() {
		if (this._coefficient < 0) {
			return true;
		}
		return false;
	}

	/**
	 * this constructor build monom ONLY from strings of shape - ax^b , or ax , or
	 * x^b , or a. (when a can be negative or decimal number) any string of
	 * different shape, throw exception.
	 * 
	 * @param s present the monom to build.
	 */
	public Monom(String s) {
		double a = 0;
		int b = 0;
		int indexX = 0; // which index is x
		int indexP = 0; // which index is '^'
		boolean minus = false;
		String temp = s; // to save the coefficient
		String temp2 = s; // to save the power
		boolean legal = true;
		if (s.charAt(0) == '-') { // check if a negative
			minus = true;
			s = s.substring(1);

		}
		if (s.charAt(0) == 'x') {
			a = 1;
		}
		if (isANumber(s)) { // check if we got just a without x
			try {
				a = Double.parseDouble(s);
			} catch (Exception e) {
				legal = false;
			}
			b = 0;
		}
		if (s.charAt(s.length() - 1) == '^') {
			legal = false;
		}

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'x' && i != 0) { // split the string for a
				indexX = i;
				temp = s.substring(0, i);
				try {
					a = Double.parseDouble(temp);
				} catch (Exception e) {
					i = s.length() - 1;
					legal = false;
				}

			}

			if (s.charAt(i) == '^' && i != s.length() - 1) { // split the string for b
				indexP = i;
				temp2 = s.substring(i + 1);
				try {
					b = Integer.parseInt(temp2);
				} catch (Exception e) {
					i = s.length();
					legal = false;
				}

			}
		}
		if (s.charAt(s.length() - 1) == 'x') { // if the power is 1
			b = 1;

		} else if (indexP - indexX != 1 && !isANumber(s)) { // if the monom from type - axa^b or ax^ or 1^x
			legal = false;

		}
		if (minus) {
			a = a * -1;
		}
		if (legal) {
			this.set_coefficient(a);
			this.set_power(b);
		} else
			throw new RuntimeException("invalid monom ");
	}

	/**
	 * auxiliary function to check if the string is a number
	 * 
	 * @param temp the string to check
	 * @return true if the string present a number
	 */

	private boolean isANumber(String temp) { // check if the string is a number
		boolean num = true;
		if (temp.charAt(0) == '.') {
			return false;
		}
		for (int i = 0; i < temp.length(); i++) {
			if (temp.charAt(i) >= '0' && temp.charAt(i) <= '9') {
				num = true;
			} else if (temp.charAt(i) == '.') {
				num = true;
			} else
				return false;

		}
		return num;
	}

	/**
	 * the function add monom only if the monom's power is same as the current monom
	 * to get legal monom.
	 * 
	 * @param m the monom to add
	 */
	public void add(Monom m) {
		if (this.get_power() == m.get_power()) {
			double res = this.get_coefficient() + m.get_coefficient();
			this.set_coefficient(res);
		} else
			throw new RuntimeException("invalid operation ");

	}

	/**
	 * the function multiply the monom with another monom
	 * 
	 * @param d the monom to multiply
	 */

	public void multiply(Monom d) {
		double resC = this.get_coefficient() * d.get_coefficient();
		int resP = this.get_power() + d.get_power();
		this.set_coefficient(resC);
		this.set_power(resP);

	}

	/**
	 * the function compare between this monom and m
	 * 
	 * @param m the Monom we want to compare with this monom
	 * @return true if the the monoms is Logically identical
	 */

	public boolean equals(Monom m) {
		if (Math.abs(this.get_coefficient() - m.get_coefficient()) < EPSILON && this.get_power() == m.get_power()) {
			return true;
		} else if (this.get_coefficient() == 0 && m.get_coefficient() == 0) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		if (this.isZero())
			return "0";
		if (this.get_power() == 0)
			return this.get_coefficient() + "";
		if (this.get_power() == 1) {
			return this.get_coefficient() + "x";
		}
		String ans = this.get_coefficient() + "x" + "^" + this.get_power();
		return ans;
	}

	// ****************** Private Methods and Data *****************
	/**
	 * 
	 * @param a the coefficient to set
	 */
	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	/**
	 * set function if the the power is negative throw exception
	 * 
	 * @param p the power to set
	 */
	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}

	/**
	 * craet a zero Monom
	 * @return zero Monom
	 */
	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;

}
