import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//  Program:    Calendar Displayer
//  Developer:  Dylan Mackey
//  Date:       1/22/2019
//  Purpose:    Show different months based off of user input

public class CalendarView 
{
	//main method
	public static void main(String[] args)
	{
		//set up variables
		String choice = "View a Calendar Month";
		String question = "What do you want to do?";
		
		//welcome the user
		JOptionPane.showMessageDialog(null, "Welcome to the Calendar Displayer!");
		
		//continue to ask for a month until the user wants to stop
		while(true)
		{
			//show menu
			Object[] options = {choice, "Exit"};
			int n = JOptionPane.showOptionDialog(null, question, "Calendar Displayer", JOptionPane.YES_NO_OPTION,
													JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			//update menu
			choice = "View another Calendar Month";
			question = "What else do you want to do?";
			
			//if the user wants to view another calendar month, continue
			if(n == 0)
			{
				viewACalendar();
			}
			
			//otherwise exit program
			else
				System.exit(0);
		}
	}
	
	public static void viewACalendar()
	{
		//initialize variables
		int isNull = 0;
		int month = 0;
		int year = 0;
		
		while(true)
		{
			//ask for a month number
			while(true)
			{
				month = isInt("Enter the Month Number. (Ex. May = 5)");
				if(month == -99)
				{
				}
				else if((month < 1 || month > 12) && month != -100)
				{
					JOptionPane.showMessageDialog(null, "Error! Months use the numbers 1-12, not " + month + ".");
				}
				else if(month == -100)
				{
					isNull++;
					break;
				}
				else
				{
					break;
				}
			}
			if(isNull > 0)
			{
				month = 0;
				break;
			}
			
			//ask for the year
			while(true)
			{
				year = isInt("Enter the year.");
				if(year == -99)
				{
				}
				else if(year == -100)
				{
					isNull++;
					break;
				}
				else
				{
					break;
				}
			}
			if(isNull > 0)
			{
				year = 0;
				break;
			}
			
			//tell user where the calendar will be printed
			JOptionPane.showMessageDialog(null, "The Calendar will be printed in the Console");
			
			//setup more variables
			String theMonth = getMonth(month);
			
			//crate a Calendar object
			Calendar calendar = new GregorianCalendar(year, month - 1, 1);
	
			//add calendar top
			printTop(theMonth, year);
			
			//create blank spaces before the first number
			for (int i = 1; i < calendar.get(Calendar.DAY_OF_WEEK); i++) 
				System.out.print("    ");

			//add the days
			while (calendar.get(Calendar.DATE) <
				calendar.getActualMaximum(Calendar.DATE)) {

				if (calendar.get(Calendar.DAY_OF_WEEK) == 7)
					System.out.printf("%4d\n", calendar.get(Calendar.DATE));
				else
					System.out.printf("%4d", calendar.get(Calendar.DATE));

				//add one day
				calendar.add(Calendar.DATE, 1);
			}
			System.out.printf("%4d\n", calendar.get(Calendar.DATE));
			
			//calculate the number of days in the month
			int days = 0;
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				days = 31;
			else if(month != 2)
				days = 30;
			else
			{
				if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
					days = 29;
				else
					days = 28;
			}
			
			//show the number of days
			System.out.println("\n" + theMonth + ", " + year + " contains " + days + " days");
			
			//add extra lines and break
			System.out.println("\n\n");
			break;
		}
	}
	
	//method to create the top of the calendar
	public static void printTop(String month, int year)
	{
		//center date
		int width = 29 / 2 - (month.length() / 2 + 3);
		for (int i = 0; i < width; i++)
			System.out.print(" ");
		System.out.print(month + ", " + year + "\n");

		//add the rest
		System.out.println("-----------------------------");
		System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
	}
	
	//method to get the month name from a number
	public static String getMonth(int monthNumber)
	{
		String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		return months[monthNumber - 1];
	}
	
	//method to check if an input is an int
	public static Integer isInt(String question)
	{
		//set up variables
		String in = null;
		int out = 0;
		
		in = JOptionPane.showInputDialog(null, question, "Enter a number", JOptionPane.INFORMATION_MESSAGE);
		try
		{
			if(in != null && !in.isEmpty())
			{
			}
			else
			{
				out = -100;
			}
		}
		catch(NullPointerException exc)
		{
			exc.printStackTrace();
		}
		if(out != -100)
		{
			try
			{
				out = Integer.parseInt(in);
			}
			catch(NumberFormatException ex)
			{
				JFrame parent = new JFrame();
				JOptionPane.showMessageDialog(parent, "Error! Wrong or no input.");
				out = -99;
			}
		}
		return out;
	}
}
