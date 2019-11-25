 MyMath


MyMath is a java package that contain interfaces and classes to implement a math function and help us to calculate them. 

classes


Monom classthat implement the interface "function" and represents a simple Monom of shape ax^b, where 'a' is a real number and 'b' is a positive integer. 
any different shape (such as: a*x^b , ax^^, axx^b, negative or decimal b) will cause to exception. The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 

Polynom class that implement the interface "Polynom_able" and represents a Polynom with arrayList – each monom in different cell, arranged by the powers from the biggest to the smallest.
We use the arrayList's method "sort" and the Monom_comperator's method "compare" to sort the Polynom. 
The class support the functions- add, multiply, substract , equals functionality, it also support the following:
1. Riemann's Integral 
2. Finding a numerical value between two values (currently support root only f(x)=0).
3. Derivative 

Monom_comperator class contain a compare function between the two powers of the monoms. We use it to sort the polynom (in the Polynom class) by the power- from the biggest to the smallest in the arrayList. 
