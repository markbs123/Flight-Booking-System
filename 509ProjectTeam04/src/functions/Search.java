package functions;

import interactWithServer.GetAirports;
import interactWithServer.GetDpFlights;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import parseXMLString.AirportParse2;
import parseXMLString.FlightParse;
import timeRelated.TimeRelated;
import timeRelated.AirportTimeZone;
import entity.Airport2;
import entity.Flight;
import entity.Line;

/**
 * Search the target flight
 * @author Team 04
 */
public class Search {
	
/**
 * Calculate distance of two airport	
 * @param departAp Code of depart airport
 * @param destinationAp Code of arrive airport
 * @return distance of depart airport "departAp" and destination airport "destinationAp"
 */
	public static double getDistance(Airport2 departAp, Airport2 destinationAp){
		double distance;
		distance = Math.sqrt(Math.pow(departAp.getLatitude()-destinationAp.getLatitude(), 2) + Math.pow(departAp.getLongitude()-destinationAp.getLongitude(),2));
		return distance;
	}


	/**
	 * Get all flights depart from one airport to another between the time window specified by "desireDpTimeMin" and "desireDpTimeMax".
	 * Note that, the "desireDpTimeMin" and "desireDpTimeMax" are both in local time of the departing airport, 
	 * however, the flights depart from one airport on a specific day in our server is organized in GMT time.
	 * So we have to convert them to GMT time in order to find out which day should we request from the server.
	 * @param ticketAgency
	 * @param departApCd Code of depart airport
	 * @param deriseDpTimeMin the earliest time customer want to take off (local time of departing airport)
	 * @param desireDpTimeMax the latest time customer want to take off (local time of departing airport)
	 * @return
	 * @throws Exception
	 */
public static ArrayList<Flight> findFlightsOnDay(String ticketAgency, String departApCd, Date deriseDpTimeMin, Date desireDpTimeMax) throws Exception{
		Date dpTimeMinGMT = TimeRelated.findGMTTime(deriseDpTimeMin, departApCd);
	    String departDay1 = TimeRelated.findTheDay(dpTimeMinGMT);
	    Date dpTimeMaxGMT = TimeRelated.findGMTTime(desireDpTimeMax, departApCd);
	    String departDay2 = TimeRelated.findTheDay(dpTimeMaxGMT);
	   
	    GetDpFlights getFlights = new GetDpFlights();
	    FlightParse flightParse = new FlightParse();
	    ArrayList<Flight> flights = new ArrayList<Flight>();
	    ArrayList<Flight> okflights = new ArrayList<Flight>();
	    if(departDay1.equals(departDay2)){
	    	String xml = getFlights.getData(ticketAgency, departApCd, departDay1);
	    	flights= flightParse.loadXMLFromString(xml);
	    }
	    else{
	    	String xml1 = getFlights.getData(ticketAgency, departApCd, departDay1);
	    	ArrayList<Flight> flights1= flightParse.loadXMLFromString(xml1);
	    	String xml2 = getFlights.getData(ticketAgency, departApCd, departDay2);
			ArrayList<Flight> flights2= flightParse.loadXMLFromString(xml2);
			flights1.addAll(flights2);
			flights = flights1;
	    }
		for(Flight fl:flights){
			Date actualDpTime = fl.getDpTime();
			if((actualDpTime.compareTo(deriseDpTimeMin) >= 0) && (actualDpTime.compareTo(desireDpTimeMax) <= 0))
				     okflights.add(fl);
		}
		return okflights;
	}

/**
 * Get all possible Flights from one airport to another on a specific day during a specific time-window, 
 * including direct flights and connecting flights.
 * @param flag When flag=0, the possible stop-over airports' latitude should be between the latitude of depart airport and latitude of arrive airport,
 * if flag=1, the possible stop-over airports' longitude should be between the longitude of depart airport and longitude of arrive airport.
 * @param departApCd Code of depart airport
 * @param destinationCd Code of arrive airport


 * @param airports HashMap of all airports contained in our database, the key is airport Code, the value including airport latitude and longitude
 * @param ticketAgency In our case, this is "Team04"
 * @throws Exception
 */
public static ArrayList<Flight> getChildren(int flag, String departApCd, String destinationCd,
		   Date desireDpTimeMin, Date desireDpTimeMax, HashMap<String, Airport2> airports, String ticketAgency) throws Exception{
	        /*preparation: 1).create a new ArrayList to store all possible Flights, including direct flight and stop flight
	                       2).determine use latitude or longitude*/
	        ArrayList<Flight> children = new ArrayList<Flight>();
			double desCoordinate, dpCoordinate;
			Airport2 destinationAp = airports.get(destinationCd);
			Airport2 departAp = airports.get(departApCd);
			double distanceSD = getDistance(departAp, destinationAp);
			if(flag==0)  {desCoordinate = destinationAp.getLatitude();	
			              dpCoordinate = departAp.getLatitude();
			}
			else   {desCoordinate = destinationAp.getLongitude();
			        dpCoordinate = departAp.getLongitude();
			}
			
			 /*step 1. get all flights departing from "departAp" on a specific day*/
			ArrayList<Flight> flights = Search.findFlightsOnDay(ticketAgency, departApCd,desireDpTimeMin, desireDpTimeMax);
			
			// step 2. check each flight in "flights"
			Date actualDpTime;
			String arriveApCd;
			Airport2 arriveAp;
			
			double avCoordinate;
			double arriveApLatitude;
			double arriveApLongitude;
			
			for (Flight fl:flights){
				//ArrayList<Flight> children = new ArrayList<Flight>();
				actualDpTime = fl.getDpTime();
				arriveApCd = fl.getAvApCode();
				arriveAp = airports.get(arriveApCd);
				arriveApLatitude = arriveAp.getLatitude();
				arriveApLongitude = arriveAp.getLongitude();
				if(flag==0)  avCoordinate = arriveApLatitude;	
				else  avCoordinate = arriveApLongitude;
					
				double distance = getDistance(departAp, arriveAp) + getDistance(arriveAp, destinationAp);
				
				// only consider flights which depart later than specified time "desireDpTime"
				if((actualDpTime.compareTo(desireDpTimeMin) >= 0) && (actualDpTime.compareTo(desireDpTimeMax) <= 0)){
					// if arrive Airport is destination
					if(arriveApCd.equals(destinationCd)){
						children.add(fl);
					}
					// else if latitude of arrive Airport is between "departAirport" and "destination airport"
					else if(( ( (avCoordinate>dpCoordinate) && (avCoordinate<desCoordinate) ) || ((avCoordinate<dpCoordinate)
							&&(avCoordinate>desCoordinate))) && (distance<(2*distanceSD)) ){
						children.add(fl); 
					}//end "else if"	
				}// end outer "if"
			}//end for

			//System.out.println("no of possible flights: Before filter: "+children.size());	
			ArrayList<Flight> filterChildren = new ArrayList<Flight>();
			if(children.size()>10){
				filterChildren= FilterConnectingFlights.doFilter(children, airports, departAp, destinationAp);
			}
			else
				filterChildren = children;
			//System.out.println("no of possible flights: after filter:"+filterChildren.size());
			return filterChildren;
}
  
   
/**
 * Get all possible Flights from one airport to another on a specific day after a specific time, 
 * including direct flights and connecting flights. 
 * @param sourceApCd Code of depart airport
 * @param destinationCd Code of arrive airport
 * @param departDay Depart day
 * @param desireDpTime Customer want to depart after the time specified by "desireDpTime"
 * @return All flights from depart airport to arrive airport, including direct flights and connecting flights. 
 * @throws Exception
 */
   public static ArrayList<ArrayList<Flight>> doSearch(String sourceApCd, String destinationCd, String departDay, Date desireDpTime) throws Exception{
	   String ticketAgency = "Team04";
	   GetAirports getAirports = new GetAirports();
	   String xml_airport = getAirports.getData(ticketAgency);
	   AirportParse2 airportParse = new AirportParse2();
	   HashMap<String, Airport2> airports = airportParse.loadXMLFromString(xml_airport);

	   Airport2 sourceAp = airports.get(sourceApCd);
	   double sourceApLatitude = sourceAp.getLatitude();
	   double sourceApLongitude = sourceAp.getLongitude();

	   Airport2 destinationAp = airports.get(destinationCd);
	   double destinationApLatitude = destinationAp.getLatitude();
	   double destinationApLongitude = destinationAp.getLongitude();
	   
	   double diffLat = Math.abs(sourceApLatitude - destinationApLatitude); // difference of Latitude of departing and arriving airport
	   double diffLong = Math.abs(sourceApLongitude - destinationApLongitude); // difference of Latitude of departing and arriving airport
	   int flag;

	   if(diffLat>diffLong)  flag = 0; 
	   else  flag = 1;
		
	   ArrayList<ArrayList<Flight>> allokFlights = new ArrayList<ArrayList<Flight>>();
	   
	   Date desireDpTimeMin1 = desireDpTime;
	   Date desireDpTimeMax1 = TimeRelated.findNextTime(desireDpTime, 1440);

//	   ArrayList<Flight> children1 = Search4.getChildren(flag,sourceApCd, destinationCd, departDay, desireDpTimeMin1, desireDpTimeMax1, airports, ticketAgency);
	   ArrayList<Flight> children1 = Search.getChildren(flag,sourceApCd, destinationCd, desireDpTimeMin1, desireDpTimeMax1, airports, ticketAgency);

	   for(Flight fl:children1){
		   if(fl.getAvApCode().equals(destinationCd)==true){
			   ArrayList<Flight> okFlights = new ArrayList<Flight>(); 
			   okFlights.add(fl); 
			   allokFlights.add(okFlights);}
		   else {                                         //fl.getAvApCode().equals(destinationCd)==false
			   String departApCd2 = fl.getAvApCode();
			   Date desireDpTimeMin2 = TimeRelated.findNextTime(fl.getAvTime(), 30); //stop-over time should be more than 30 minutes
			   Date desireDpTimeMax2 = TimeRelated.findNextTime(fl.getAvTime(), 600);  // stop-over time should be less than 10hours (600 minutes)
			   //String departDay2 = TimeRelated.findTheDay(desireDpTimeMin2); 
			   ArrayList<Flight> children2 =  Search.getChildren(flag,departApCd2, destinationCd, desireDpTimeMin2, desireDpTimeMax2, airports, ticketAgency);
			   Flight preFlight2 = fl;  //each node of the tree contain a previous Flight, and a children List

			   for(Flight fl2:children2){
				   if(fl2.getAvApCode().equals(destinationCd)==true){
					   ArrayList<Flight> okFlights = new ArrayList<Flight>(); 
					   okFlights.add(preFlight2);
					   okFlights.add(fl2); 
					   allokFlights.add(okFlights);}
		   
				   else {                                 //fl2.getAvApCode().equals("HNL")==false
					   String departApCd3 = fl2.getAvApCode();
					   Date desireDpTimeMin3 = TimeRelated.findNextTime(fl2.getAvTime(), 30); //stop-over time should be more than 30 minutes
					   Date desireDpTimeMax3 = TimeRelated.findNextTime(fl2.getAvTime(), 600);  // stop-over time should be less than 10hours (600 minutes)
					  // String departDay3 = TimeRelated.findTheDay(desireDpTimeMin3);
					   ArrayList<Flight> children3 = Search.getChildren(flag,departApCd3, destinationCd, desireDpTimeMin3, desireDpTimeMax3, airports, ticketAgency);
					   Flight preFlight3 = fl2;

					   for(Flight fl3:children3){
						   if(fl3.getAvApCode().equals(destinationCd)==true){
							   ArrayList<Flight> okFlights = new ArrayList<Flight>();
							   okFlights.add(preFlight2);
							   okFlights.add(preFlight3);
							   okFlights.add(fl3);
							   allokFlights.add(okFlights);
					          } 
				       }  //end the last for
				   }
			   }
		   }
	   }
	   return allokFlights;	   
   }
   
   

}
