package functions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import entity.Flight;
import entity.Line;
/**
 * Test various Sort classes
 * @author Team04
 *
 */
public class SortTest {
	/**
	 * Specify the input data of test
	 * @param seat "FirstClass" or "Coach"
	 * @return An list of all possible flights from source airport to destination airport
	 * @throws Exception
	 */
	public static ArrayList<Line> input(String seat) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 13, 00, 00, 00);
		Date desireDpTime = calendar.getTime();
		String sourceApCd = "JFK";
		String destinationCd = "PHL";
		String departDay = "2015_5_13";
		ArrayList<ArrayList<Flight>> allokFlights = Search.doSearch(sourceApCd, destinationCd, departDay, desireDpTime);
		ArrayList<Line> allokLines =  manageLineData.getAllLines(allokFlights, seat);
		return allokLines;
		
	}
	
	/**
	 * Print out the information of flights from source airport to destination airport
	 * @param sortedLines An list of all possible flights from source airport to destination airport
	 * @param seat "FirstClass" or "Coach"
	 */
	public static void printOutput(ArrayList<Line> sortedLines, String seat){
		for(Line line:sortedLines){
			System.out.println("________________________________________________________________");
			System.out.println("Duration:"+manageLineData.timeToString(line.getDuration()));
		    System.out.println("Price:"+manageLineData.priceToString(line.getPrice()));
		    System.out.println("Taking off:"+line.getTakeoffTime());
		    System.out.println("Landing:"+line.getLandingTime());
	        ArrayList<Flight> fls = line.getFlights();
			ArrayList<Integer> layoverTime = line.getLayoverTime();
			for(int i=0; i<fls.size(); i++){
				System.out.println(fls.get(i).getDpApCode()+"----->"+fls.get(i).getAvApCode());
				System.out.println(fls.get(i).getDpTime()+"----->"+fls.get(i).getAvTime());
				if(seat.equals("FirstClass")){
					System.out.println("FirstClass: number of seats:"+fls.get(i).getFirstClassSeats());
					System.out.println("FirstClass: price of seats:"+fls.get(i).getFirstClassPrice());
					if(fls.get(i).getFirstClassSeats()==0){
			        	System.out.println("Coach: number of seats:"+fls.get(i).getCoachSeats());
						System.out.println("Coach: price of seats:"+fls.get(i).getCoachPrice());
			        }
				}
				else {
					System.out.println("Coach: number of seats:"+fls.get(i).getCoachSeats());
					System.out.println("Coach: price of seats:"+fls.get(i).getCoachPrice());
					if(fls.get(i).getCoachSeats()==0){
			        	System.out.println("FirstClass: number of seats:"+fls.get(i).getFirstClassSeats());
						System.out.println("FirstClass: price of seats:"+fls.get(i).getFirstClassPrice());
			        }
				}
				if(i<(fls.size()-1))
				    System.out.println("----------------------stopover:"+manageLineData.timeToString(layoverTime.get(i))+"------------------------");
			}
			System.out.println("***************************************************************");
			System.out.println("");
		}
		
	}
	
	/**
	 * Test sortByDuration.java, sort flights according to fly duration time (shorter to longer).
	 * @throws Exception
	 */
	public static void sortByDurationTest() throws Exception{
		String seat = "FirstClass";
		ArrayList<Line> allokLines = SortTest.input(seat);
		System.out.println(allokLines.size());
		SortByDuration sort = new SortByDuration();
		Collections.sort(allokLines, sort);
    	SortTest.printOutput(allokLines, seat);
	}
	
	/**
	 * Test sortByDuration.java, sort flights according to fly duration time (longer to shorter).
	 * @throws Exception
	 */
	public static void sortByDurationTest2() throws Exception{
		String seat = "FirstClass";
		ArrayList<Line> allokLines = SortTest.input(seat);
		System.out.println(allokLines.size());
		SortByDuration sort = new SortByDuration();
		Collections.sort(allokLines, sort);
		Collections.reverse(allokLines);
    	SortTest.printOutput(allokLines, seat);
	}
	
	/**
	 * Test sortByPrice.java, sort flights according to price (lower to higher)
	 * @throws Exception
	 */
	public static void sortByPriceTest() throws Exception{
		String seat = "FirstClass";
		ArrayList<Line> allokLines = SortTest.input(seat);
		System.out.println(allokLines.size());
		SortByPrice sort = new SortByPrice();
		Collections.sort(allokLines, sort);
    	SortTest.printOutput(allokLines, seat);
	}
	
	/**
	 * Test sortByTakeoffTime.java, sort flights according to take off time (earlier to later)
	 * @throws Exception
	 */
	public static void sortByTakeoffTimeTest() throws Exception{
		String seat = "FirstClass";
		ArrayList<Line> allokLines = SortTest.input(seat);
		System.out.println(allokLines.size());
		SortByTakeoffTime sort = new SortByTakeoffTime();
		Collections.sort(allokLines, sort);
    	SortTest.printOutput(allokLines, seat);
	}

	/**
	 * Test sortByLandingTime.java, sort flights according to landing time (earlier to later)
	 * @throws Exception
	 */
	public static void sortByLandingTimeTest() throws Exception{
		String seat = "FirstClass";
		ArrayList<Line> allokLines = SortTest.input(seat);
		System.out.println(allokLines.size());
		SortByLandingTime sort = new SortByLandingTime();
		Collections.sort(allokLines, sort);
    	SortTest.printOutput(allokLines, seat);
	}
	
	public static void main(String[] args) throws Exception {
	    //SortTest.sortByDurationTest();
		SortTest.sortByDurationTest2();
	    //SortTest.sortByPriceTest();
		//SortTest.sortByTakeoffTimeTest();
		//SortTest.sortByLandingTimeTest();

	}

}
