
public class tests {
	/* 
	 * Name: Brian Jesse Gatukui Kimani
	 * NetID: bkimani (bkimani@u.rochester.edu)
	 * partner:rtusiime (rtusiime@u.rochester.edu)
	 * project 1
	 * Lab TR 14:00 - 15:15 
	 * I collaborated with Kevin Tusiime on this assignment.		
	 */
	public static void main(String[] args) {//tests for all the extra credit
		Calculator calc = new Calculator();
		String test1 = "sin45";
		String test2 = "6*cos25";
		String test3 = "tan45";
		String test4 = "20%3";
		String test5 = "5^5";
		
		calc.shuntingAlg(test1);
		calc.shuntingAlg(test2);
		calc.shuntingAlg(test3);
		calc.shuntingAlg(test4);
		calc.shuntingAlg(test5);
		System.out.println(toJadenCase(null));
		

	}
	public static String toJadenCase(String phrase)  {
		// TODO put your code below this comment
		if(phrase==(null)) {
			return null;
		}
		if(phrase.isBlank()||phrase.equals(null)){return null;}
		String[] arr = phrase.split(" ");	
		String newString="";
		for(String s:arr) {
			char[] charr = s.toCharArray();
			int temp = charr[0];
			temp -=32;
			charr[0]= (char) temp;
			String st = new String(charr);
      if(arr[arr.length-1]==s)
			    newString+=st;
      else
          newString+=st+" "; 
		}
    return newString;
	}
}
