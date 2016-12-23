package parseXMLString;

import interactWithServer.GetAirports;

import java.util.ArrayList;

import entity.Airport;
/**
 * Test_AirportParse.java
 * @author Team04
 */
public class AirportParseTest {
	/**
	 * Traverse the entire array list to display all the airports
	 */
	
	public static void main(String[] args) {
		GetAirports ty1 = new GetAirports();
		String ticketAgency = "Team04";
		String xml= ty1.getData(ticketAgency);
//		System.out.println(xml);
		AirportParse ty2 = new AirportParse();
		
		//AirportParse allAirports = new AirportParse();
		try {
			ArrayList<Airport> airplanelist = ty2.loadXMLFromString(xml);
			System.out.println("no of airports:"+airplanelist.size());
			for(Airport some: airplanelist){
			System.out.println("______________________________");
			System.out.println("code: "+some.getCode());
			System.out.println("name: "+some.getName());
			System.out.println("Latitude: "+some.getLatitude());
			System.out.println("Longitude: "+some.getLongitude());
			System.out.println("*******************************");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	   }

}
