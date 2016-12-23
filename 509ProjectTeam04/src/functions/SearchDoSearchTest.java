package functions;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;

import timeRelated.TimeRelated;
import entity.Flight;
import entity.Line;

/**
 * Test the most important function do search 
 * @author Team 04
 */
public class SearchDoSearchTest {
	
	public static void printOutput(ArrayList<ArrayList<Flight>> allokFlights, String seat){
		ArrayList<Line> allokLines = manageLineData.getAllLines(allokFlights, seat);
		System.out.println("number of results:"+allokLines.size());
		for(Line line: allokLines){
			System.out.println("________________________________________________________________");
			System.out.println("Duration:"+manageLineData.timeToString(line.getDuration()));
			System.out.println("Price:"+manageLineData.priceToString(line.getPrice()));
			System.out.println("Take off Time:  "+TimeRelated.dateFormat(line.getTakeoffTime()));
			System.out.println("Landing Time:  "+TimeRelated.dateFormat(line.getLandingTime()));
			ArrayList<Flight> fls = line.getFlights();
			ArrayList<Integer> layoverTime = line.getLayoverTime();
			for(int i=0; i<fls.size(); i++){
				System.out.println(fls.get(i).getDpApCode()+"----->"+fls.get(i).getAvApCode());
				System.out.println(fls.get(i).getDpTime()+"----->"+fls.get(i).getAvTime());
				if(i<(fls.size()-1))
				    System.out.println("----------------------stopover:"+manageLineData.timeToString(layoverTime.get(i))+"------------------------");
			}
			System.out.println();
			System.out.println("***************************************************************");
		}
	}

		
		/**
		 * Test Search.doSearch() Normal Case
		 * Case 1: find the flights departing from one airport to another on a specific day after a specific time
		 * @throws Exception
		 */
		public static void doSearchTest1() throws Exception{
			/*step1. input*/
			Calendar calendar = Calendar.getInstance();
			calendar.set(2015, Calendar.MAY, 15, 00, 00, 00);
			Date desireDpTime = calendar.getTime();
			String sourceApCd = "JFK";
			String destinationCd = "MCO";
			String departDay = "2015_5_15";
			String seat = "Coach";
			/*step2. call function*/
			ArrayList<ArrayList<Flight>> allokFlights = Search.doSearch(sourceApCd, destinationCd, departDay, desireDpTime);
			/*step3. print output*/
			SearchDoSearchTest.printOutput(allokFlights, seat);
		}
		
		/**
		 * Test Search.doSearch() Special Case
		 * Case 3: find the flights departing from one airport to another on 2015_5_7
		 * @throws Exception
		 */
		public static void doSearchTest2() throws Exception{
			/*step1. input*/
			Calendar calendar = Calendar.getInstance();
			calendar.set(2015, Calendar.MAY, 7, 00, 00, 00);
			Date desireDpTime = calendar.getTime();
			String sourceApCd = "JFK";
			String destinationCd = "IAH";
			String departDay = "2015_5_7";
			String seat = "Coach";
			/*step2. call function*/
			ArrayList<ArrayList<Flight>> allokFlights = Search.doSearch(sourceApCd, destinationCd, departDay, desireDpTime);
			/*step3. print output*/
			SearchDoSearchTest.printOutput(allokFlights, seat);
		}
		
		/**
		 * Test Search.doSearch() Invalid Case
		 * Case 4: Test how the program responded when the input depart day is invalid to our database
		 * @throws Exception
		 */
		public static void doSearchTest3() throws Exception{
			/*step1. input*/
			Calendar calendar = Calendar.getInstance();
			calendar.set(2015, Calendar.MAY, 19, 00, 00, 00);
			Date desireDpTime = calendar.getTime();
			String sourceApCd = "JFK";
			String destinationCd = "IAH";
			String departDay = "2015_5_19";
			String seat = "Coach";
			/*step2. call function*/
			ArrayList<ArrayList<Flight>> allokFlights = Search.doSearch(sourceApCd, destinationCd, departDay, desireDpTime);
			/*step3. print output*/
			SearchDoSearchTest.printOutput(allokFlights, seat);
		}
		
		/**
		 * Test Search.doSearch() Red eye Case
		 * @throws Exception
		 */
	public static void doSearchTest4() throws Exception{
		/*step1. input*/
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 15, 00, 00, 00);
		Date desireDpTime = calendar.getTime();
		String sourceApCd = "JFK";
		String destinationCd = "MCO";
		String departDay = "2015_5_15";
		String seat = "Coach";
		/*step2. call function*/
		ArrayList<ArrayList<Flight>> allokFlights = Search.doSearch(sourceApCd, destinationCd, departDay, desireDpTime);
		/*step3. print output*/
		SearchDoSearchTest.printOutput(allokFlights, seat);
	}
	
	/**
	 * Test Search.doSearch() Time Out Case
	 * @throws Exception
	 */
	public static void doSearchTest5() throws Exception{
		/*step1. input*/
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 15, 00, 00, 00);
		Date desireDpTime = calendar.getTime();
		String sourceApCd = "JFK";
		String destinationCd = "MCO";
		String departDay = "2015_5_15";
		String seat = "Coach";
		/*step2. call function*/
		ArrayList<ArrayList<Flight>> allokFlights = Search.doSearch(sourceApCd, destinationCd, departDay, desireDpTime);
		/*step3. print output*/
		SearchDoSearchTest.printOutput(allokFlights, seat);	
	}
	
	
	
	public static void main(String[] args) throws Exception {
		//SearchTestTry.doSearchTest1();
		//SearchTestTry.doSearchTest2();
		//SearchTestTry.doSearchTest3();
		SearchDoSearchTest.doSearchTest4();

	}

}
