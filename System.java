import java.time.Duration;
import java.time.LocalTime;

public class System {
	String title, displayOption, title2, operation;
	int numCount, min, max, maxDigit, inclusiveRange, answer, interval, index;
	int[] numbers;
	public System()
	{
		interval =1;
		title = "Welcome to Speed Arithmetic";
		title2 = "";
		index =0;
	}
	public System(String option, int numCount, int min, int max, int interval, String operation)
	{
		displayOption = option; 
		this.numCount = numCount;
		this.min = min;
		this.max = max;
		this.interval = interval;
		this.operation = operation;
	}
	public String getTitle(){ return title;}
	public String getTitle2() {return title2;}
	public void displayOption(String option)
	{
		displayOption = option;
		if( option == "all")
		{
			title = "Display all numbers at once ";
		}
		else
		{
			title = "Display one number at a time ";
		}
	}
	public String getDisplayOption(){return displayOption;}
	public boolean numCount(String num)
	{
		try {
			numCount = Integer.parseInt(num);
			if(numCount<2 || numCount>50)
			{
				return false;
			}
		}
		catch(NumberFormatException excep){
			return false;
		}
		title2 = numCount + " numbers,  "; 
		return true;
	}
	public int getNumCount() {return numCount;}
	public boolean range(String a, String b)
	{
		try{
			min = Integer.parseInt(a);
			max = Integer.parseInt(b);
		}
		
		catch(NumberFormatException excep){
			return false;
		}
		 
		title2 = title2 + "From " + min +" to " + max ;
		return true;
	}
	public int getMin() {return min;}
	public int getMax() {return max;}
	public boolean interval(String a,String b)
	{
		int num1 =  Integer.parseInt(a);
		int num2 = Integer.parseInt(b);
		interval = num1*1000 + num2*100;
		if (interval == 0 && displayOption.equals("one"))
		{
			return false;
		}
		else {
			if(displayOption.equals("one")) {title2 = title2 + ",  " + num1 + "." + num2 +" seconds interval";}
			return true;
		}
	}
	public int getInterval() {return interval;}
	public void operation(String op)
	{
		operation = op;
		title2 = title2 + ",  " +  operation;
	}
	public int getMaxDigit()
	{
		maxDigit = 1;
		int newMax = max;
		boolean check = max/10 >= 1;
		while(check)
		{
			newMax = newMax/10;
			maxDigit++;
			check = newMax/10 >= 1;
		}
		return maxDigit;
	}
	public int[] getNumbers()
	{
		inclusiveRange = max - min +1;
		numbers = new int[numCount];
		for (int i =0; i<numCount; i++)
		{
			int newNum = (int)(Math.random()*inclusiveRange) +1;
			numbers[i] = newNum;
		}
		return numbers;
	}
	public int getAnswer()
	{
		if(operation.equals("add"))
		{
			answer = 0;
		}
		else
		{
			answer =1;
		}
		for(int num:numbers)
		{
			if (operation.equals("add"))
			{
				answer = answer + num;
			}
			else
			{
				answer = answer* num;
			}
		}
		return answer; 
	}
}
