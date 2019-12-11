EX1

EX1 is a java package that contain interfaces and classes to implement a math functions and help us to calculate them.
in addition, EX1 can write or read functions from txt file and supports displaying them in a graphical window.

classes

Monom class that implement the interface "function" and represents a simple Monom of shape ax^b, where 'a' is a real number and 'b' is a positive integer. any different shape (such as: a*x^b , ax^^, axx^b, negative or decimal b) will cause to exception. The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.

Polynom class that implement the interface "Polynom_able" and represents a Polynom by using arrayList – each monom in different cell, arranged by the powers from the biggest to the smallest. We use the arrayList's method "sort" and the Monom_comperator's method "compare" to sort the Polynom. The class support the functions- add, multiply, substract , equals functionality, it also support the following:
1.Riemann's Integral
2.Finding a numerical value between two values (currently support root only f(x)=0).
3.Derivative

Monom_comperator class contain a compare function between the two powers of the monoms. We use it to sort the polynom (in the Polynom class) by the power- from the biggest to the smallest in the arrayList.

ComplexFunction thet implement the interface "complex_function" and represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions above polynoms, using the Polynom class (or complex functions),  y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).

Functions_GUI that implements the interface "functions" , and represents a collection of mathematical functions by using LinkedList and supports writing or reading collection of functions to/from txt file and supports displaying them in a graphical window by using StdDraw class.

StdDraw class  provides a basic capability for creating drawings with programs. It uses a simple graphics model that allows to create drawings consisting of points, lines, and curves in a window on the computer and to save the drawings to a file. we use this class to display collection of functions graphically in Functions_GUI class

Range class represents a simple 1D range of shape [min,max]. we use this class to send renge of numbers to creat the graphical window and display collection of functions in  Functions_GUI class.

Params class is a class with only fields , used to read parameters from json file in class Functions_GUI, to determine the width and the height - in pixels of the GUI window, the range of the horizontal and vertical axises and the resulution.



