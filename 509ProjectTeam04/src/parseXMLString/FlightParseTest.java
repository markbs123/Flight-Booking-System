package parseXMLString;

import interactWithServer.GetDpFlights;

import java.net.MalformedURLException;
import java.util.ArrayList;

import entity.Flight;
/**
 * Test_FlightParse.java
 * @author Team04
 */
public class FlightParseTest {
	/**
	 * Specify the input day and airport code of test
	 * @throws MalformedURLException
	 */
	
   public static void main(String[] args) throws MalformedURLException{
		
		GetDpFlights ty1 = new GetDpFlights();
		String ticketAgency = "Team04";
		String airportCode = "JFK";
		String day = "2015_05_13";
		String flights= ty1.getData(ticketAgency, airportCode, day);

		
		FlightParse ps = new FlightParse();
		ArrayList<Flight> list = new ArrayList<Flight>();
		try {
			list = ps.loadXMLFromString(flights);
			System.out.println("length of the list: "+list.size());

		    System.out.println("_____________________________");
			System.out.println("Airplane Model: "+list.get(0).getAirplaneModel());
			System.out.println("Flight Time: "+list.get(0).getFlightTime());
			System.out.println("Flight Number: "+list.get(0).getFlightNumber());
			System.out.println("Departure Airport Code: "+list.get(0).getDpApCode());
			System.out.println("Departure Time: "+list.get(0).getDpTime());
			System.out.println("Arrival Airport Code: "+list.get(0).getAvApCode());
			System.out.println("Arrival Time: "+list.get(0).getAvTime());
		    System.out.println("First Class: price "+list.get(0).getFirstClassPrice()+"    seats: "+list.get(0).getFirstClassSeats());
			System.out.println("Coach: price "+list.get(0).getCoachPrice()+"    seats: "+list.get(0).getCoachSeats());
			System.out.println("*******************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
