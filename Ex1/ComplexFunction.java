package Ex1;

/** This class create a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions), 
 * y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).
 * 
 * @author Gofna and Maor
**/
public class ComplexFunction implements complex_function {

	private function left;
	private function right;
	private Operation op;

	public ComplexFunction(String op, function left, function right) {
			try {
				this.left = left;
				this.right = right;
				this.op = Operation.valueOf(op);							
			}catch(Exception e) {
				this.op = Operation.Error;
				throw new RuntimeException("invalid complex function");
			}
	}
	

	public ComplexFunction(function left) { // consturctor 
		this.left =left;
		this.right = null;
		this.op = Operation.None;
	}
	
	public ComplexFunction(Operation op, function left, function right) { // constructor 
		new ComplexFunction(op.toString(), left, right);
	}
	
	@Override
	public function copy() {
		function r;
		function l = this.left.initFromString(this.left.toString());
		if(this.right != null) {
			r = this.right.initFromString(this.right.toString());
		}
		else {
			r = null;
		}
		Operation op2 = this.op;
		//return new ComplexFunction(this.op, this.left, this.right);
		function res = new ComplexFunction(op2.toString(), l, r);
		return res;	
	}
	
	@Override
	public double f(double x) {
		return f(x, this); // call help function
	}

	private double f(double x, ComplexFunction fun) { // help function use recursion to go in the complex 
			if (fun.op == Operation.Times) { // ask witch operation it is 
				return fun.left.f(x) * fun.right.f(x);
			} else if (fun.op == Operation.Plus) {
				return fun.left.f(x) + fun.right.f(x);
			} else if (fun.op == Operation.Divid) {
				return fun.left.f(x) / fun.right.f(x);
			} else if (fun.op == Operation.Comp) {
				return left.f(right.f(x));
			} else if (fun.op == Operation.Max) {
				return Math.max(fun.left.f(x), fun.right.f(x));
			} else if (fun.op == Operation.Min) {
				return Math.min(fun.left.f(x), fun.right.f(x));
			} else { // operation none 
				return fun.left.f(x);
			}

	}

	@Override
	public function initFromString(String s) {
		String temp = ""; // to catch the operation
		String operation = ""; 
		String left = ""; //to catch the left function
		int leftIndex = 0; //the index the left start 
		int rightIndex = 0; //the index the right start
		int closeIndex = 0; //the index the complex end
		int sighn = 0; // count ')' to find the right side of the complex function
		int open = 0; // count '('

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				if (open == 0) {
					operation = temp;
					temp = "";
					leftIndex = i + 1;
					sighn++;
					open++;
				} else
					sighn++;
			}

			else if (s.charAt(i) == ',') {
				if (sighn == 1) {
					left = temp;
					temp = "";
					rightIndex = i + 1;
				}

			} else if (s.charAt(i) == ')') {
				closeIndex = i;
				sighn--;
			}

			else {
				temp = temp + s.charAt(i);
			}
		}

		if (operation.equals("")) { // all the function is a Polynom type
			left = temp;
			Polynom l = new Polynom(left);
			return new ComplexFunction(l);
		}

		function ans = new ComplexFunction(operation, initFromString(s.substring(leftIndex, rightIndex - 1)), //recursive 
				initFromString(s.substring(rightIndex, closeIndex)));
		return ans;
	}


	@Override
	public void plus(function f1) { // the left is this complex function and the right is what we add
		this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
		this.right = f1;
		this.op = Operation.Plus;

	}

	@Override
	public void mul(function f1) {
		this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
		this.right = f1;
		this.op = Operation.Times;

	}

	@Override
	public void div(function f1) {
		this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
		this.right = f1;
		this.op = Operation.Divid;

	}

	@Override
	public void max(function f1) {
		this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
		this.right = f1;
		this.op = Operation.Max;

	}

	@Override
	public void min(function f1) {
		this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
		this.right = f1;
		this.op = Operation.Min;

	}

	@Override
	public void comp(function f1) {
		this.left = new ComplexFunction(this.op.toString(), this.left, this.right);
		this.right = f1;
		this.op = Operation.Comp;
	}

	/**
	 * the function can not do a perfect compare. the function check the values of x
	 * from -50 to 50 only, and return true if the values y=f(x) is equals in the both functions. because there is no way to check infinity number of
	 * values.
	 * 
	 * @param obj the function to check with this Complex Function.
	 * @return true if the two function represent the same values.
	 */

	public boolean equals(Object obj) {
		String s = obj.toString();
		function cf = new ComplexFunction(new Polynom());
		cf = cf.initFromString(s);
		double[] yObj = new double[101];
		double[] yThis = new double[101];
		int x = -50; // the value of x for f(x).
		for (int i = 0; i < 101; i++) {
			yObj[i] = cf.f(x); // put the results in arrays  start from -50 to 50
			yThis[i] = this.f(x);
			if (yObj[i] != yThis[i]) {  // compare between the functions 
				return false;
			}
			x++;
		}
		return true;
	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.op;
	}

	public String toString() {
		if (this.right == null) { //to avoid from print null if there is
			return this.left.toString();
		}
		if (this.left == null) {
			return this.right.toString();
		}
		return this.op + "(" + this.left.toString() + "," + this.right.toString() + ")";
	}

}
