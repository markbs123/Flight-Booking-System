package functions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import timeRelated.TimeRelated;
import entity.Flight;
import entity.Line;
/**
 * Test manageLineData.java
 * @author Team04
 *
 */
public class manageLineDataTest {
	/**
	 * Test manageLineData getLineInfo() method
	 * @throws Exception
	 */
	public static void getLineInfoTest() throws Exception{
		/*step1. input*/
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, Calendar.MAY, 13, 00, 00, 00);
		Date desireDpTime = calendar.getTime();
		String sourceApCd = "JFK";
		String destinationCd = "PHL";
		String departDay = "2015_5_13";
		ArrayList<ArrayList<Flight>> allokFlights = Search.doSearch(sourceApCd, destinationCd, departDay, desireDpTime);
		String seat = "FirstClass";
		/*step2. call function*/
		ArrayList<Line> allokLines = new ArrayList<Line>();
		for(ArrayList<Flight> okFlights:allokFlights){
		   Line line = manageLineData.getLineInfo(okFlights, seat);
		   allokLines.add(line);
		}
		/*step3. output*/
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

	public static void main(String[] args) throws Exception {
		manageLineDataTest.getLineInfoTest();

	}

}
