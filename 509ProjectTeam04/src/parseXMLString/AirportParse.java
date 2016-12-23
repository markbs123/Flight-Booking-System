package parseXMLString;


import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import entity.Airport;

/**
 * Parse the XML to get the specific information of each airplane
 * @author Team04
 */
public class AirportParse {
	/**
	 * Build array list to save the airports information which load from XML
	 * @return allAirports A array list of all airports
	 * @throws Exception
	 */	
			public ArrayList<Airport> loadXMLFromString(String xml) throws Exception
			{
			    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			    DocumentBuilder builder = factory.newDocumentBuilder();
			    InputSource is = new InputSource(new StringReader(xml));
			    Document airports = builder.parse(is);
			    
			    //Airport oneAirport = new Airport();
			    ArrayList<Airport> allAirports = new ArrayList<Airport>();
			    
			    NodeList airportlist = airports.getElementsByTagName("Airport");
			   
			    for(int i=0; i<airportlist.getLength(); i++){
			       Airport oneAirport = new Airport();
	               Element airport=(Element)airportlist.item(i);
	               /*parse attributes*/
			       oneAirport.setCode(airport.getAttribute("Code"));
			       oneAirport.setName(airport.getAttribute("Name"));
			       /*parse child nodes*/
			       oneAirport.setLatitude(Double.parseDouble(airport.getElementsByTagName("Latitude").item(0).getTextContent()));
			       oneAirport.setLongitude(Double.parseDouble(airport.getElementsByTagName("Longitude").item(0).getTextContent()));
			       allAirports.add(oneAirport);
		           }
			   
			    return allAirports;
			}
			

}
