package parseXMLString;


import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import entity.Airport2;
/**
 * Note that AirportParse2 save all the information in hash map which AirportParse do this action in array list
 * @author Team04
 */
public class AirportParse2 {
	/**
	 * Build hash map to save the airports information which load from XML
	 * @return allAirports A hash map of all airports
	 * @throws Exception
	 */	
	public HashMap<String, Airport2>  loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    Document airports = builder.parse(is);
	    
	    //Airport oneAirport = new Airport();
	    //ArrayList<Airport2> allAirports = new ArrayList<Airport2>();
	    HashMap<String, Airport2> allAirports = new HashMap<String, Airport2>();
	    
	    NodeList airportlist = airports.getElementsByTagName("Airport");
	   
	    for(int i=0; i<airportlist.getLength(); i++){
	       Airport2 oneAirport = new Airport2();
           Element airport=(Element)airportlist.item(i);
           /*parse attributes*/
	       //oneAirport.setCode(airport.getAttribute("Code"));
           String airportCode = airport.getAttribute("Code");
	       oneAirport.setName(airport.getAttribute("Name"));
	       /*parse child nodes*/
	       oneAirport.setLatitude(Double.parseDouble(airport.getElementsByTagName("Latitude").item(0).getTextContent()));
	       oneAirport.setLongitude(Double.parseDouble(airport.getElementsByTagName("Longitude").item(0).getTextContent()));
	       //allAirports.add(oneAirport);
	       allAirports.put(airportCode, oneAirport);
           }
	   
	    return allAirports;
	}

}
