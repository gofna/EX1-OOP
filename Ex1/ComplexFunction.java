package Ex1;

public class ComplexFunction implements complex_function{
	
	private function left;
	private function right;
	private Operation op;
	
	public ComplexFunction(String op , function left , function right ) {
		         //do a *new* left and right
		
		this.left = left;
		this.right = right;
		this.op = Operation.valueOf(op);
	}
	
	
	@Override
	public double f(double x) {
		
		return 0;
	}

	@Override
	public function initFromString(String s) {
		String temp = "";
		String operation = "";
		String left = "";
		String right = "";
		
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(') {
				operation = temp + "";
				temp = "";
			}
			
			if(s.charAt(i) == ',') {
				left = temp + "";
				temp = "";
			}
			
			if(s.charAt(i) == ')') {
				right = temp + "";
				temp = "";
			}
		}
		
		if(temp.equals("")) {
			operation = "None";
			
		}
		function left1 = new Polynom(left);
		function right1 = new Polynom(right);
		function ans = new ComplexFunction(operation,left1 ,right1);
		return ans;
	}

	@Override
	public function copy() {
		function m = new ComplexFunction(this.op.toString(), this.left,this.right);
		return m;
	}

	@Override
	public void plus(function f1) {
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
		
		
	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comp(function f1) {
		String left = this.left.toString().replace("x", f1.toString());
		function l = new Polynom(left);
		String right = this.right.toString().replace("x", f1.toString());
		function r = new Polynom();
		this.left = l;
		this.right = r;
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
		return  this.op + "(" + this.left.toString() + " , " + this.right.toString() + ")";
	}

}
