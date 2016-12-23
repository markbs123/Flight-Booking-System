package timeRelated;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Define a hash map which contains all the time zone information of each airports  
 * @author Team04
 */
public class AirportTimeZone {
	/**
	 * Create a time zone hash map
	 * @return airportTimeZone The hash map which contains the data of time difference
	 */
	public static HashMap<String, Integer> getAirportTimeZone(){
		HashMap<String, Integer> airportTimeZone = new HashMap<String, Integer>();
		airportTimeZone.put("ATL", -4);
		airportTimeZone.put("ANC", -8);
		airportTimeZone.put("AUS", -5);
		airportTimeZone.put("BWI", -4);
		airportTimeZone.put("BOS", -4);
		airportTimeZone.put("CLT", -4);
		airportTimeZone.put("MDW", -5);
		airportTimeZone.put("ORD", -5);
		airportTimeZone.put("CVG", -5);
		airportTimeZone.put("CLE", -4);
		airportTimeZone.put("CMH", -4);
		airportTimeZone.put("DFW", -5);
		airportTimeZone.put("DEN", -4);
		airportTimeZone.put("DTW", -4);
		airportTimeZone.put("FLL", -4);
		airportTimeZone.put("RSW", -4);
		airportTimeZone.put("FLL", -4);
		airportTimeZone.put("RSW", -4);
		airportTimeZone.put("BDL", -4);
		airportTimeZone.put("HNL", -10);
		airportTimeZone.put("IAH", -5);
		airportTimeZone.put("HOU", -5);
		airportTimeZone.put("IND", -4);
		airportTimeZone.put("MCI", -5);
		airportTimeZone.put("LAS", -7);
		airportTimeZone.put("LAX", -7);
		airportTimeZone.put("MEM", -5);
		airportTimeZone.put("MIA", -4);
		airportTimeZone.put("MSP", -5);
		airportTimeZone.put("BNA", -5);
		airportTimeZone.put("MSY", -5);
		airportTimeZone.put("JFK", -4);
		airportTimeZone.put("LGA", -4);
		airportTimeZone.put("EWR", -4);
		airportTimeZone.put("OAK", -7);
		airportTimeZone.put("ONT", -7);
		airportTimeZone.put("MCO", -4);
		airportTimeZone.put("PHL", -4);
		airportTimeZone.put("PHX", -7);
		airportTimeZone.put("PIT", -4);
		airportTimeZone.put("PDX", -7);
		airportTimeZone.put("RDU", -4);
		airportTimeZone.put("SMF", -7);
		airportTimeZone.put("SLC", -6);
		airportTimeZone.put("SAT", -5);
		airportTimeZone.put("SAN", -7);
		airportTimeZone.put("SFO", -7);
		airportTimeZone.put("SJC", -7);
		airportTimeZone.put("SNA", -7);
		airportTimeZone.put("SEA", -7);
		airportTimeZone.put("STL", -5);
		airportTimeZone.put("TPA", -4);
		airportTimeZone.put("IAD", -4);
		airportTimeZone.put("DCA", -4);
		return airportTimeZone;
	}
	
	public static void main(String[] args) {
		AirportTimeZone zonetable = new AirportTimeZone();
		HashMap<String, Integer> hashmap = zonetable.getAirportTimeZone();
		System.out.println(hashmap.size());
	}
}
