package parseXMLString;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import entity.Airplane;

/**
 * Parse the XML to get the specific information of each airplane
 * @author Team04
 */
public class AirplaneParse { 
	/**
	 * Build hash map to save the airplane information which load from XML
	 * @return airplaneMap A hash map of all airplanes
	 * @throws Exception
	 */
	public HashMap<String, Airplane> loadXMLFromString(String xml) throws Exception{
    	HashMap<String, Airplane> airplaneMap = new HashMap<String, Airplane>();
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    Document airplanes = builder.parse(is);
	    
	    NodeList airplanelist = airplanes.getElementsByTagName("Airplane");
	    for(int i=0; i<airplanelist.getLength(); i++){
	       Airplane oneAirplane = new Airplane();
	       Element airplane = (Element)airplanelist.item(i);
	       String airplaneModel = airplane.getAttribute("Model");
	       oneAirplane.setManufacturer(airplane.getAttribute("Manufacturer"));
	       oneAirplane.setFirstClassSeats(Integer.parseInt(airplane.getElementsByTagName("FirstClassSeats").item(0).getTextContent()));
	       oneAirplane.setCoachSeats(Integer.parseInt(airplane.getElementsByTagName("CoachSeats").item(0).getTextContent()));
	       airplaneMap.put(airplaneModel, oneAirplane);
	    }
	    
	    return airplaneMap;
    }
	
//	public static void main(String[] args) {
//		GetAirplanes ty1 = new GetAirplanes();
//		String ticketAgency = "Team04";
//		String xml= ty1.getData(ticketAgency);
//		System.out.println(xml);
//		
//		AirplaneParse allAirplanes = new AirplaneParse();
//		try {
//			HashMap<String, Airplane> airplaneMap = allAirplanes.loadXMLFromString(xml);
//			System.out.println("No of airplanes: "+airplaneMap.size());
//			int i =12;
//			String k = "A380";
//			Airplane some = airplaneMap.get(k);
//			System.out.println("Key: model: "+k);
//			System.out.println("value searched: manufacturer:"+some.getManufacturer());
//			System.out.println("value searched: first-class seats:"+some.getFirstClassSeats());
//			System.out.println("value searched: coach seats:"+some.getCoachSeats());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//
//	}

}
