package functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import entity.Airport2;
import entity.Flight;

/**
 * Filter list of connecting flights, choose the first 10 connecting flights in the list if the list size is larger than 10
 * @author Team04
 *
 */
public class FilterConnectingFlights {
	/**
	 * convert ArrayList of flight to ArrayList of connecting flight
	 * @param flights ArrayList of flight
	 * @param airports HashMap of all airports in our database
	 * @param departAp depart Airport
	 * @param destinationAp destination Airport
	 * @return ArrayList of connecting-flight
	 */
	public static ArrayList<ConnectingFlight> createConnectingFlights(ArrayList<Flight> flights, HashMap<String, Airport2> airports, Airport2 departAp, Airport2 destinationAp){
		ArrayList<ConnectingFlight> connectingFlights = new ArrayList<ConnectingFlight>();
		for(Flight flight:flights){
			ConnectingFlight connectingFlight = new ConnectingFlight();
			connectingFlight.setFlight(flight);
			connectingFlight.setConnectingAp(airports.get(flight.getAvApCode()));
			connectingFlight.setDestinationAp(destinationAp);
			connectingFlight.setSourceAp(departAp);
			connectingFlight.setDistance(connectingFlight.getConnectingAp(), departAp, destinationAp);
			connectingFlights.add(connectingFlight);
		}
		return connectingFlights;
	}
	
	/**
	 * Filter list of connecting flights, choose the first 10 connecting flights in the list if the list size is larger than 10
	 * @param flights ArrayList of flight
	 * @param airports HashMap of all airports in our database
	 * @param departAp depart Airport
	 * @param destinationAp  destination Airport
	 * @return filtered ArrayList of flight
	 */
	public static ArrayList<Flight> doFilter(ArrayList<Flight> flights, HashMap<String, Airport2> airports, Airport2 departAp, Airport2 destinationAp){
		ArrayList<ConnectingFlight> connectingFlights = FilterConnectingFlights.createConnectingFlights(flights, airports, departAp, destinationAp);
		
		ArrayList<Flight> filteredFlights = new ArrayList<Flight>();
		
		SortConnectingFlights sort = new SortConnectingFlights();
		Collections.sort(connectingFlights, sort);
	    for(int i=0; i<10; i++){
			filteredFlights.add((connectingFlights.get(i)).getFlight());
		}
		return filteredFlights;
	}

	
}
