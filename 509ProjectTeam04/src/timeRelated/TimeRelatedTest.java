package timeRelated;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Test_TimeRelated.java
 * @author Team04
 */
public class TimeRelatedTest {

	public static void findTheDayTest(){
		/**
		 *  Find the specific date
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 2, 11, 30, 00);
		Date date = calendar.getTime();
		String expected = "2015_5_2";
		String result = TimeRelated.findTheDay(date);
		System.out.println("expected result:"+expected);
		System.out.println("actual result:"+result);
		if(result.equals(expected)){
			System.out.println("Test success");
		}
		else
			System.out.println("Test fail");	
	}
	
	/**
	 * Test "TimeRelated.stringToDateTest()"
	 * @throws ParseException
	 */
	public static void stringToDateTest() throws ParseException{
		String input ="13:58   5/5/2015";
		Date result = TimeRelated.stringToDate(input);
		System.out.println("actual result:"+result);
	}

	/**
	 * Test "TimeRelated.findTheDateTest()"
	 * @throws ParseException
	 */
	public static void findTheDateTest() throws ParseException{
		String input = "2015_5_13";
		Date result = TimeRelated.findTheDate(input);
		System.out.println("actual result:"+result);
	}
	
	
	/**
	 *  Test "TimeRelated.findTheNextDayTest()"
	 * @throws ParseException
	 */
	public static void findTheNextDayTest() throws ParseException{
		String day = "2015_5_2";
		String expected = "2015_5_3";
		String result = TimeRelated.findNextDayString(day);
		System.out.println("expected result:"+expected);
		System.out.println("actual result:"+result);
		if(result.equals(expected)){
			System.out.println("Test success");
		}
		else
			System.out.println("Test fail");
	}
	
    /**
     * Test "TimeRelated.dateFormatTest()"
     */
	public static void dateFormatTest(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MARCH, 27, 23, 39, 00);
		Date input = calendar.getTime();
		String expected = "23:39   03/27/2015";
		String result = TimeRelated.dateFormat(input);
		System.out.println("expected result:"+expected);
		System.out.println("actual result:"+result);
		if(result.equals(expected)){
			System.out.println("Test success");
		}
		else
			System.out.println("Test fail");
	}
	
	/**
	 * Test TimeRelated.dateBetweenMinAndMax()
	 */
	public static void dateBetweenMinAndMaxTest(){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2015, Calendar.MARCH, 27, 23, 39, 00);
		Date datemin = calendar1.getTime();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2015, Calendar.MARCH, 28, 10, 46, 00);
		Date datemax = calendar2.getTime();
		ArrayList<Date> datelist = TimeRelated.dateBetweenMinAndMax(datemin,datemax);
		for(Date date:datelist){
		   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
		   System.out.println(sdf.format(date));
		}
	}

	/**
	 * Test TimeRelated.findNextTime()
	 */
	public static void findNextTimeTest(){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2015, Calendar.MARCH, 27, 23, 39, 00);
		Date inputdate= calendar1.getTime();
		int inputminutes = 40;
		Date result = TimeRelated.findNextTime(inputdate,inputminutes);
		System.out.println(result);
	}

	/**
	 * Test TimeRelated.findLocalTime()
	 */
	public static void findLocalTimeTest(){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2015, Calendar.MARCH, 27, 23, 39, 00);
		Date inputdate= calendar1.getTime();
		String inputAirport = "BOS";
		Date result = TimeRelated.findLocalTime(inputdate, inputAirport);
		System.out.println(result);
	}
	/**
	 * Test TimeRelated.findGMTTime()
	 */
	public static void findGMTTimeTest(){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2015, Calendar.MARCH, 27, 23, 39, 00);
		Date inputdate= calendar1.getTime();
		String inputAirport = "BOS";
		Date result = TimeRelated.findGMTTime(inputdate, inputAirport);
		System.out.println(result);
	}
	/**
	 * Test TimeRelated.doCompareTest()
	 */
	public static void doCompareTest(){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2015, Calendar.MARCH, 27, 23, 39, 00);
		Date inputdate= calendar1.getTime();
		int inputhours = 3;
		int inputminutes = 25;
		int result = TimeRelated.doCompare(inputdate, inputhours, inputminutes);
		int expected = 1;
		System.out.println("expected result:"+expected);
		System.out.println("actual result:"+result);
		if(result==expected){
			System.out.println("Test success");
		}
		else
			System.out.println("Test fail");
	}
	
	/**
	 * Test TimeRelated.subtractTwoDate()
	 */
	public static void subtractTwoDateTest(){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2015, Calendar.MARCH, 27, 23, 39, 00);
		Date input1 = calendar1.getTime();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2015, Calendar.MARCH, 28, 10, 46, 00);
		Date input2 = calendar2.getTime();
		int result = TimeRelated.subtractTwoDate(input1, input2);
		int expected = 667;
		System.out.println("expected result:"+expected);
		System.out.println("actual result:"+result);
		if(result==expected){
			System.out.println("Test success");
		}
		else
			System.out.println("Test fail");
		
	}
	
	public static void main(String[] args) throws ParseException {
		//TimeRelatedTester.findTheDayTest();
		//TimeRelatedTester.stringToDateTest();
		//TimeRelatedTester.findTheDateTest();
		//TimeRelatedTester.findTheNextDayTest();
		//TimeRelatedTester.dateFormatTest();
		//TimeRelatedTester.dateBetweenMinAndMaxTest();
		//TimeRelatedTester.findNextTimeTest();
		//TimeRelatedTester.findLocalTimeTest();
		TimeRelatedTest.findGMTTimeTest();
		//TimeRelatedTester.doCompareTest();
		//TimeRelatedTester.subtractTwoDateTest();
	}
		

}
