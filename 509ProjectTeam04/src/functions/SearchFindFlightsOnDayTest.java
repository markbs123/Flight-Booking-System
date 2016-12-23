package functions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entity.Flight;
/**
 * Test for searching flight of some specific dates 
 * @author Team 04
 */
public class SearchFindFlightsOnDayTest {
/**
 * Test Search.findFlightsOnDay(). 
 * Case 1: when the day is valid to our database
 * @throws Exception
 */
	public static void findFlightsOnDayTest1() throws Exception{
		/*step1. input*/
		String ticketAgency = "Team04";
		String departApCd = "JFK";
		//String departDay = "2015_5_15";
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 13, 00, 00, 00);
		Date desireDpTimeMin = calendar.getTime();
		calendar.set(2015, Calendar.MAY, 13, 23, 00, 00);
		Date desireDpTimeMax = calendar.getTime();
		
		/*step2. call function*/
		ArrayList<Flight> flights = Search.findFlightsOnDay(ticketAgency, departApCd, desireDpTimeMin, desireDpTimeMax);
		
		/*step3. print output*/
		System.out.println("There are "+flights.size()+"flights in all.");
		for(Flight fl:flights){
			System.out.println("________________________________________________________________");
			System.out.println(fl.getDpApCode()+"----->"+fl.getAvApCode());
			System.out.println(fl.getDpTime()+"----->"+fl.getAvTime());
			System.out.println("***************************************************************");
		}
	}
	
	
	/**
	 * Test Search.findFlightsOnDay(). 
	 * Case 2: when the day is one of the border day "2015_5_8" of our database
	 * @throws Exception
	 */
	public static void findFlightsOnDayTest2() throws Exception{
		/*step1. input*/
		String ticketAgency = "Team04";
		String departApCd = "JFK";
		//String departDay = "2015_5_8";
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 8, 00, 00, 00);
		Date desireDpTimeMin = calendar.getTime();
		calendar.set(2015, Calendar.MAY, 8, 24, 00, 00);
		Date desireDpTimeMax = calendar.getTime();
		
		/*step2. call function*/
		ArrayList<Flight> flights = Search.findFlightsOnDay(ticketAgency, departApCd, desireDpTimeMin,desireDpTimeMax);
		
		/*step3. print output*/
		System.out.println("There are "+flights.size()+"flights in all.");
		for(Flight fl:flights){
			System.out.println("________________________________________________________________");
			System.out.println(fl.getDpApCode()+"----->"+fl.getAvApCode());
			System.out.println(fl.getDpTime()+"----->"+fl.getAvTime());
			System.out.println("***************************************************************");
		}
	}
	
	/**
	 * Test Search.findFlightsOnDay(). 
	 * Case 3: when the day is one of the border day "2015_5_17" of our database
	 * @throws Exception
	 */
	public static void findFlightsOnDayTest3() throws Exception{
		/*step1. input*/
		String ticketAgency = "Team04";
		String departApCd = "JFK";
		//String departDay = "2015_5_17";
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 17, 00, 00, 00);
		Date desireDpTimeMin = calendar.getTime();
		calendar.set(2015, Calendar.MAY, 17, 24, 00, 00);
		Date desireDpTimeMax = calendar.getTime();
		
		/*step2. call function*/
		ArrayList<Flight> flights = Search.findFlightsOnDay(ticketAgency, departApCd, desireDpTimeMin, desireDpTimeMax);
		
		/*step3. print output*/
		System.out.println("There are "+flights.size()+"flights in all.");
		for(Flight fl:flights){
			System.out.println("________________________________________________________________");
			System.out.println(fl.getDpApCode()+"----->"+fl.getAvApCode());
			System.out.println(fl.getDpTime()+"----->"+fl.getAvTime());
			System.out.println("***************************************************************");
		}
	}
	
	
	/**
	 * Test Search.findFlightsOnDay(). 
	 * Case 4: when the day is "2015_5_7"
	 * @throws Exception
	 */
	public static void findFlightsOnDayTest4() throws Exception{
		/*step1. input*/
		String ticketAgency = "Team04";
		String departApCd = "JFK";
		//String departDay = "2015_5_7";
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 7, 00, 00, 00);
		Date desireDpTimeMin = calendar.getTime();
		calendar.set(2015, Calendar.MAY, 7, 24, 00, 00);
		Date desireDpTimeMax = calendar.getTime();
		
		/*step2. call function*/
		ArrayList<Flight> flights = Search.findFlightsOnDay(ticketAgency, departApCd, desireDpTimeMin,desireDpTimeMax);
		
		/*step3. print output*/
		System.out.println("There are "+flights.size()+"flights in all.");
		for(Flight fl:flights){
			System.out.println("________________________________________________________________");
			System.out.println(fl.getDpApCode()+"----->"+fl.getAvApCode());
			System.out.println(fl.getDpTime()+"----->"+fl.getAvTime());
			System.out.println("***************************************************************");
		}
	}
	
	
	/**
	 * Test Search.findFlightsOnDay(). 
	 * Case 5: when the day is an invalid day to our database
	 * @throws Exception
	 */
	public static void findFlightsOnDayTest5() throws Exception{
		/*step1. input*/
		String ticketAgency = "Team04";
		String departApCd = "JFK";
		//String departDay = "2015_5_19";
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 19, 00, 00, 00);
		Date desireDpTimeMin = calendar.getTime();
		calendar.set(2015, Calendar.MAY, 19, 24, 00, 00);
		Date desireDpTimeMax = calendar.getTime();
		
		
		/*step2. call function*/
		ArrayList<Flight> flights = Search.findFlightsOnDay(ticketAgency, departApCd, desireDpTimeMin,desireDpTimeMax);
		
		/*step3. print output*/
		System.out.println("There are "+flights.size()+"flights in all.");
		for(Flight fl:flights){
			System.out.println("________________________________________________________________");
			System.out.println(fl.getDpApCode()+"----->"+fl.getAvApCode());
			System.out.println(fl.getDpTime()+"----->"+fl.getAvTime());
			System.out.println("***************************************************************");
		}
	}
	

	public static void main(String[] args) throws Exception {
		//SearchTest.findFlightsOnDayTest1();
		//SearchTest.findFlightsOnDayTest2();
		//SearchTest.findFlightsOnDayTest3();
		//SearchTest.findFlightsOnDayTest4();
		//SearchTest.findFlightsOnDayTest5();
		
	}

}
