package parseXMLString;

import interactWithServer.GetAirplanes;


import java.io.StringReader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.HashMap;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import timeRelated.TimeRelated;
import entity.Airplane;
import entity.Flight;

/**
 * Parse the XML to get the specific information of each flight
 * @author Team04
 */
public class FlightParse {
	/**
	 * Build array list to save the flight information which load from XML
	 * @return allFlights A array list of all fights
	 * @throws Exception
	 */	
	
	public ArrayList<Flight> loadXMLFromString(String xml) throws Exception
	{	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    Document flights = builder.parse(is);
	
	    ArrayList<Flight> allFlights = new ArrayList<Flight>();
	    
	    NodeList flightlist = flights.getElementsByTagName("Flight");
	    /*System.out.println("no. of flights in the string:"+flightlist.getLength());*/
	   

		 for(int i=0; i<flightlist.getLength(); i++){
	       Flight oneFlight = new Flight();
           Element flight=(Element)flightlist.item(i);
           /*parse attributes*/
           String airplaneKey = flight.getAttribute("Airplane");
	       oneFlight.setAirplaneModel(airplaneKey);
	       int flightTime = Integer.parseInt(flight.getAttribute("FlightTime"));
	       /*int flightTimeInMin = Integer.parseInt(flight.getAttribute("FlightTime"));// Integer.parseInt() convert string into integer
	       String hours = String.valueOf(flightTimeInMin / 60); // two way for converting integer to string: "String.valueOf()" and "Integer.toString()"
	       String mins = Integer.toString(flightTimeInMin % 60);
	       String flightTime = hours+"h "+mins+"min";*/
	       oneFlight.setFlightTime(flightTime);  // Integer.parseInt() convert string into integer
	       oneFlight.setFlightNumber(flight.getAttribute("Number"));
	   
	       
	       /*parse child nodes--departure*/
	       NodeList departInfo = flight.getElementsByTagName("Departure"); 
	       Element dpInfo = (Element)departInfo.item(0);
	       //oneFlight.setDpApCode(dpInfo.getElementsByTagName("Code").item(0).getTextContent());
	       String dpApCode = dpInfo.getElementsByTagName("Code").item(0).getTextContent();
	       oneFlight.setDpApCode(dpApCode);
	       String dpTimeString = dpInfo.getElementsByTagName("Time").item(0).getTextContent();
	       dpTimeString = dpTimeString.replace(" GMT", "");
	       Locale.setDefault(Locale.US);
	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		   Date dpTimeGMT = sdf.parse(dpTimeString);
		   //convert departing time from GMT time-zone to local time-zone of the departing airport 
		   Date dpTime = TimeRelated.findLocalTime(dpTimeGMT, dpApCode);             
	       oneFlight.setDpTime(dpTime);
	       	       
	       /*parse child nodes--arrival*/
	       NodeList arriveInfo = flight.getElementsByTagName("Arrival"); 
	       Element avInfo = (Element)arriveInfo.item(0);
	       //oneFlight.setAvApCode(avInfo.getElementsByTagName("Code").item(0).getTextContent());
	       String avApCode = avInfo.getElementsByTagName("Code").item(0).getTextContent();
	       oneFlight.setAvApCode(avApCode);
	       String avTimeString = avInfo.getElementsByTagName("Time").item(0).getTextContent();
	       avTimeString = avTimeString.replace(" GMT", "");
		   Date avTimeGMT = sdf.parse(avTimeString);
		   /*oneFlight.setAvTime(avTimeGMT);*/
		   Date avTime = TimeRelated.findLocalTime(avTimeGMT, avApCode); 
	       oneFlight.setAvTime(avTime);
	       
	       /*find Airplane according to AirplaneModel, get the total number of seats on that airplane*/
	       GetAirplanes getAirplanes = new GetAirplanes();
		   String ticketAgency = "Team04";
		   String xmlAirplanes= getAirplanes.getData(ticketAgency);
		   AirplaneParse airplaneParse = new AirplaneParse();
	       HashMap<String, Airplane> airplaneMap = airplaneParse.loadXMLFromString(xmlAirplanes);
	       Airplane foundAirplane = airplaneMap.get(airplaneKey);
	       int firstClassSeats = foundAirplane.getFirstClassSeats();
	       int coachSeats = foundAirplane.getCoachSeats();
	       
	       /*parse child nodes--seating*/
	       NodeList seatInfo = flight.getElementsByTagName("Seating");
	       Element stInfo = (Element)seatInfo.item(0);
	       NodeList firstClassInfo = stInfo.getElementsByTagName("FirstClass");
	       Element firstClass = (Element)firstClassInfo.item(0);
	       oneFlight.setFirstClassPrice(firstClass.getAttribute("Price"));
	       int remainFirstClassSeats = firstClassSeats-Integer.parseInt(firstClassInfo.item(0).getTextContent());
	       oneFlight.setFirstClassSeats(remainFirstClassSeats);
	       
	       NodeList coachInfo = stInfo.getElementsByTagName("Coach");
	       Element coach = (Element)coachInfo.item(0);
	       oneFlight.setCoachPrice(coach.getAttribute("Price"));
	       int remainCoachSeats = coachSeats-Integer.parseInt(coachInfo.item(0).getTextContent());
	       oneFlight.setCoachSeats(remainCoachSeats);
	              
	       allFlights.add(oneFlight);
	       
           }
	
	    return allFlights;
	}
	


}
