import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import labs.TheQueue;
import labs.TheStack;
import java.io.*;
/* 
 * Name: Brian Jesse Gatukui Kimani
 * NetID: bkimani (bkimani@u.rochester.edu)
 * partner:rtusiime (rtusiime@u.rochester.edu)
 * project 1
 * Lab TR 14:00 - 15:15 
 * I collaborated with Kevin Tusiime on this assignment.		
 */

public class Calculator {
	static char[] operators = { '(', ')', '^', '*', '/','+', '-', '&', '|', '%','=','!','s', 'c', 't','<','>','i','n','o','a'};
	static char[] numbers = { '0','1','2','3','4','5','6','7','8','9', '.'};
	static ArrayList<Character> operator = new ArrayList<Character>(); //stores the operators for easy acces to check containment
	static ArrayList<Character> operand = new ArrayList<Character>();  //stores the operands for easy acces to check containment
	public static void main(String args[]) throws Exception {
	
	//The string in question to be evaluated
	String q;
	//variable for output file
	File file = new File(args[0]);
	//initiate output file
	PrintWriter writer = new PrintWriter(args[1],"UTF-8");
	//Create reader variable
	BufferedReader read = new BufferedReader(new FileReader(file));
	//read first line
	q = read.readLine();
	while(q!=null) {// evaluate the input and send to output file
		double result = shuntingAlg(q);
		q = read.readLine();
		if(q!=null) {
			writer.format("%.2f\n", result);
		}
		else {
			writer.format("%.2f", result);
		}
		
		}
	writer.close();
	
	}
	public static double shuntingAlg(String line) { // main function that checks if operator or operand and calls superPush and evaluate
		
		char[] charIn =line.toCharArray();
		TheQueue<String> queueStore = new TheQueue<String>();
		TheStack<String> stackStore = new TheStack<String>();
	
		for(int i=0; i<operators.length; i++)operator.add(operators[i]);
		for(int i=0; i<numbers.length; i++)operand.add(numbers[i]);
		
		String function = "";
		String numeral ="";
		for(int i=0; i<charIn.length; i++) {
		
		if(charIn[i]==' ')continue;
		
		else if(operator.contains(charIn[i])){ // checks for a sin/cos/tan sequence of characters
			if(((charIn[i]=='s')&&function=="")||(charIn[i]=='c')||(charIn[i]=='t')) {
			function = Character.toString(charIn[i]);
			continue;
			}
			else if((charIn[i]=='i'&&function.equals("s"))||(charIn[i]=='o'&&function.equals("c"))||(charIn[i]=='a'&&function.equals("t"))) {
			function += charIn[i];
			continue;
			}
			else if((charIn[i]=='n'&&function.equals("si"))||(charIn[i]=='s'&&function.equals("co"))||(charIn[i]=='n'&&function.equals("ta"))) {
			function += charIn[i];
			superPush(function, stackStore,queueStore);
			function = "";
			continue;
			}
			else {
			superPush(Character.toString(charIn[i]), stackStore, queueStore);
			}
		}
		else if(operand.contains(charIn[i])) {
			int j=i;
			numeral+=Character.toString(charIn[i]);
			while(j<charIn.length-1&&operand.contains(charIn[++j])) { //concatenation of consecutive numerals
				numeral+=Character.toString(charIn[j]);
				i=j;
				}
			
			queueStore.enqueue(numeral);
			numeral="";
		}
		else {
		System.out.println("Invalid Input");//in the event that the input is not either a number or operator
			}
		}
		
		while(!stackStore.isEmpty()) queueStore.enqueue(stackStore.pop()); //pop every remaining token into the queue
		
		double answer = evaluate(queueStore);
		System.out.println("Answer: " +answer);
		return answer;
	}
	public static double evaluate(TheQueue<String> queue) {
		TheStack<String> stack = new TheStack<>();// evaluation stack
		double a,b,result=0;
		while(!queue.isEmpty()) {
			String x = queue.dequeue();
			char x1 = x.charAt(0);
			if(operator.contains(x1)) { //cases for various operators
				if(x1=='!') {
					a = Double.parseDouble(stack.pop());
					if(a==1.0) result=0;
					else result=1.0;
					stack.push(String.valueOf(result));
				}
				else if(x1=='&') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					if(a==b) result = 1;						
					else result=0;
					stack.push(String.valueOf(result));
				}
				else if(x1=='|') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					if(a==b) result=a;
					else if(a>b) result=a;
					else result = b;
					stack.push(String.valueOf(result));	
				}
				else if(x1=='=') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					if(a==b)result=1;
					else result=0;
					stack.push(String.valueOf(result));
				}
				else if(x1=='+') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					result = b+a;
					stack.push(String.valueOf(result));
				}
				else if(x1=='-') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					result = b-a;
					stack.push(String.valueOf(result));
				}
				else if(x1=='*') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					result = b*a;
					stack.push(String.valueOf(result));
				}
				else if(x1=='/') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					result = b/a;
					stack.push(String.valueOf(result));
				}
				else if(x1=='%') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					result = b%a;
					stack.push(String.valueOf(result));
				}
				else if(x1=='^') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					result =  Math.pow(b, a);
					stack.push(String.valueOf(result));
				}
				else if(x1=='s') {
					a = Double.parseDouble(stack.pop());
					result = Math.sin(a);
					stack.push(String.valueOf(result));
				}
				else if(x1=='c') {
					a = Double.parseDouble(stack.pop());
					result =  Math.cos(a);
					stack.push(String.valueOf(result));
				}
				else if(x1=='t') {
					a = Double.parseDouble(stack.pop());
					result =  Math.tan(a);
					stack.push(String.valueOf(result));
				}
				else if(x1=='>') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					if(b>a)result=1;
					else result=0;
					stack.push(String.valueOf(result));
				}
				else if(x1=='<') {
					a = Double.parseDouble(stack.pop());
					b = Double.parseDouble(stack.pop());
					if(b<a)result=1;
					else result=0;
					stack.push(String.valueOf(result));
				}

			}
			else stack.push(x); // pushes all operands onto the stack
				
		}
		return result;
	}
	public static void superPush(String str, TheStack<String> stack, TheQueue<String> queue) {
		if(stack.isEmpty()) 
			stack.push(str);
			
		else if(str.equals("(")||str.equals(")")){ // location of other closing or opening bracket
			if(str.equals("(")) {
				stack.push(str);}
			else {
				while(stack.peek() != null && !(stack.peek().equals("("))) { //pop until other bracket is found
					queue.enqueue(stack.pop());
				}
				stack.pop();
			}
		}
		else {
			boolean bool = false;
			while((!stack.isEmpty())&&rank(str)<rank(stack.peek())) {
				queue.enqueue(stack.pop());
				bool = true;
			}
			if(bool==true) { //pop operators of equal precedence
				while((!stack.isEmpty())&&(rank(str)==rank(stack.peek()))) {
					queue.enqueue(stack.pop());
					bool = false;
				}
			}
			stack.push(str); //push the operator onto the stack
		}
	}
	public static int rank(String str) { //rank method to set order of precedence for the operators
		int value = 0;
		switch(str) {
		case ">": value =-1;break;
		case "<": value =-1;break;
		case "&": value =-1;break;
		case "=": value =-1;break;
		case "|": value =-1;break;
		case "!": value =-1;break;
		case "+": value =0;break;
		case "-": value =0;break;
		case "/": value =1;break;
		case "*": value =1;break;
		case "%": value =1;break;
		case "^": value =2;break;
		case "(": value =0;break;
		case ")": value =3;break;
		case "sin":value =4;break;
		case "cos":value =4;break;
		case "tan":value =4;break;

	}
	return value;
	}
	public static void printStack(TheStack stack) { //helper methods for debugging 
	while(!stack.isEmpty())
	System.out.print("\t"+ stack.pop());
	}
	public static void printQueue(TheQueue que) {
	while(!que.isEmpty())
	System.out.print(que.dequeue() + " ");
	System.out.println();
	}
}