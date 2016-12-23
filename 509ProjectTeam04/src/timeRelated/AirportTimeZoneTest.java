package timeRelated;

import java.util.HashMap;
/**
 * Test_AirportTimeZone.java
 * @author Team04
 */

public class AirportTimeZoneTest {
	/**
	 * Output the hash map of time difference
	 */
	public static void getAirportTimeZoneTest(){
		HashMap<String, Integer> airportTimeZone = AirportTimeZone.getAirportTimeZone();
		for( String airportCode : airportTimeZone.keySet()){
			System.out.print("Airport Code:"+airportCode+"--------");
			System.out.println("Difference of local time with GMT time: "+airportTimeZone.get(airportCode));
		}
	}

	public static void main(String[] args) {
		AirportTimeZoneTest.getAirportTimeZoneTest();

	}

}
