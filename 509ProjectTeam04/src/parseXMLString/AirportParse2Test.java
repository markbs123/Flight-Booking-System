package parseXMLString;

import java.util.HashMap;

import entity.Airport2;
import interactWithServer.GetAirports;
/**
 * Test_AirportParse2.java
 * @author Team04
 */
public class AirportParse2Test {
	/**
	 * Traverse the entire hash map to display all the airports
	 */
	public static void main(String[] args) {
		GetAirports ty1 = new GetAirports();
		String ticketAgency = "Team04";
		String xml= ty1.getData(ticketAgency);
		System.out.println(xml);
		AirportParse2 ty2 = new AirportParse2();
	
		try {
			HashMap<String,Airport2> airplanelist = ty2.loadXMLFromString(xml);
			HashMap<String, Airport2> map = new HashMap<String, Airport2>(ty2.loadXMLFromString(xml));
			System.out.println("no of airports:"+airplanelist.size());
			for( String key : map.keySet()){
		       Airport2 value = map.get(key);
			System.out.println("______________________________");
			System.out.println("code: "+key);
			System.out.println("name: "+((Airport2) value).getName());
			System.out.println("Latitude: "+((Airport2) value).getLatitude());
			System.out.println("Longitude: "+((Airport2) value).getLongitude());
			System.out.println("*******************************");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		

	}

}
