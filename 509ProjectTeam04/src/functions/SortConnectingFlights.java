package functions;

import java.util.Comparator;


/**
 * sort connecting flights according to their distance attribute(total distance from departing airport and to destination airport
 * @author Team04
 *
 */
public class SortConnectingFlights implements Comparator<ConnectingFlight>{
	public int compare(ConnectingFlight o1, ConnectingFlight o2) {
		if(o1.getDistance() > o2.getDistance()) return 1;
		else if(o1.getDistance() == o2.getDistance()) return 0;
		else return -1;	
	}

	
}
