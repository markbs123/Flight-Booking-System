package functions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entity.Flight;
import entity.Line;
/**
 * Test Filter.java
 * @author Team04
 *
 */
public class FilterTest {
	/**
	 * Specify the input data of test
	 * @return An list of all possible flights from source airport to destination airport
	 * @throws Exception
	 */
	public static ArrayList<ArrayList<Flight>> input() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 13, 00, 00, 00);
		Date desireDpTime = calendar.getTime();
		String sourceApCd = "JFK";
		String destinationCd = "PHL";
		String departDay = "2015_5_13";
		ArrayList<ArrayList<Flight>> allokFlights = Search.doSearch(sourceApCd, destinationCd, departDay, desireDpTime);
		return allokFlights;
	}
	
	/**
	 * Print out the information of flights from source airport to destination airport
	 * @param allokLines An list of flights from source airport to destination airport
	 * @param seat "FirstClass" or "Coach"
	 */
	public static void printOutput(ArrayList<Line> allokLines, String seat){
		for(Line line:allokLines){
			ArrayList<Flight> fls = line.getFlights();
			ArrayList<Integer> layoverTime = line.getLayoverTime();
			System.out.println("________________________________________________________________");
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
	 * Test "Filter.doFilterBySeats1()" and "Filter.doFilterBySeats2()"
	 * @throws Exception
	 */
	public static void doFilterBySeatsTest() throws Exception{
		ArrayList<ArrayList<Flight>> allokFlights = FilterTest.input();
		String seat = "FirstClass";	
		ArrayList<ArrayList<Flight>> filterBySeat1 = Filter.doFilterBySeats1(allokFlights,seat);
		ArrayList<Line> filterBySeatLine1 = new ArrayList<Line>();
		for(ArrayList<Flight> fls:filterBySeat1){
		   Line line = manageLineData.getLineInfo(fls, seat);
		   filterBySeatLine1.add(line);
		}
		
		ArrayList<ArrayList<Flight>> filterBySeat2 = Filter.doFilterBySeats2(allokFlights,seat);
		ArrayList<Line> filterBySeatLine2 = new ArrayList<Line>();
		for(ArrayList<Flight> fls:filterBySeat2){
		   Line line = manageLineData.getLineInfo(fls, seat);
		   filterBySeatLine2.add(line);
		}
		
		FilterTest.printOutput(filterBySeatLine1, seat);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		FilterTest.printOutput(filterBySeatLine2, seat);
		
	}
	
	/**
	 * Test "Filter.doFilterByStop()"
	 * @throws Exception
	 */
	public static void doFilterByStopTest() throws Exception{
		String seat = "FirstClass";	
		ArrayList<ArrayList<Flight>> allokFlights = FilterTest.input();
		ArrayList<Line> allokLines = new ArrayList<Line>();
		for(ArrayList<Flight> fls:allokFlights){
			   Line line = manageLineData.getLineInfo(fls, seat);
			   allokLines.add(line);
			}
		String stopInfo = "NonStopOneStop";
		ArrayList<Line> filterByStop = Filter.doFilterByStop(allokLines,stopInfo);
		FilterTest.printOutput(filterByStop, seat);
	}
	
	/**
	 * Test "Filter.doFilterByDATime()"
	 * @throws Exception
	 */
	public static void doFilterByDATimeTest() throws Exception{
		String seat = "FirstClass";	
		ArrayList<ArrayList<Flight>> allokFlights = FilterTest.input();
		ArrayList<Line> allokLines = new ArrayList<Line>();
		for(ArrayList<Flight> fls:allokFlights){
			   Line line = manageLineData.getLineInfo(fls, seat);
			   allokLines.add(line);
			}
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 13, 00, 00, 00);
		Date dpMinTime = calendar.getTime();
		calendar.set(2015, Calendar.MAY, 13, 12, 00, 00);
		Date dpMaxTime = calendar.getTime();
		calendar.set(2015, Calendar.MAY, 13, 12, 00, 00);
		Date avMinTime = calendar.getTime();
		calendar.set(2015, Calendar.MAY, 13, 24, 00, 00);
		Date avMaxTime = calendar.getTime();
	    ArrayList<Line> filterByTime = Filter.doFilterByDATime(allokLines, dpMinTime, dpMaxTime, avMinTime, avMaxTime);
	    FilterTest.printOutput(filterByTime, seat);
	}

	public static void main(String[] args) throws Exception {
		//FilterTest.doFilterBySeatsTest();
		//FilterTest.doFilterByStopTest();
		FilterTest.doFilterByDATimeTest();
	

	}

}
