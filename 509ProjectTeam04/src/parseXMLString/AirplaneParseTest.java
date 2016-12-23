package parseXMLString;

import interactWithServer.GetAirplanes;

import java.util.HashMap;

import entity.Airplane;

/**
 * Test_AirplaneParse.java
 * @author Team04
 */

public class AirplaneParseTest {
	/**
	 * Specify the input airplane model of test
	 */
	
	public static void main(String[] args) {
		GetAirplanes ty1 = new GetAirplanes();
		String ticketAgency = "Team04";
		String xml= ty1.getData(ticketAgency);
		System.out.println(xml);
		
		AirplaneParse allAirplanes = new AirplaneParse();
		try {
			HashMap<String, Airplane> airplaneMap = allAirplanes.loadXMLFromString(xml);
			System.out.println("No of airplanes: "+airplaneMap.size());
			//int i =12;
			String k = "A380";
		    Airplane some = airplaneMap.get(k);
		    System.out.println("______________________________________________________");
			System.out.println("Key: model: "+k);
			System.out.println("value searched: manufacturer:"+some.getManufacturer());
			System.out.println("value searched: first-class seats:"+some.getFirstClassSeats());
			System.out.println("value searched: coach seats:"+some.getCoachSeats());
			System.out.println("*******************************************************");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		}

}
