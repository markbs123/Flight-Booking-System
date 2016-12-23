package functions;


import entity.Airport2;
import entity.Flight;
/**
 * Connecting flight
 * @author Team 04
 *
 */
public class ConnectingFlight {
	/** flight from departing airport to connecting flight*/
	private Flight flight;
	/** Code of connecting airport*/
	private Airport2 connectingAp;
	/** Code of departing airport*/
	private Airport2 sourceAp;
	/** Code of destination airport*/
	private Airport2 destinationAp;
	/** total distance from depart airport to connecting airport and from connecting airport to destination airport*/
	private double distance;
	
/**
 * set distance attribute of the connecting flight
 * @param connectingAp Code of connecting airport
 * @param sourceAp Code of departing airport
 * @param destinationAp Code of destination airport
 */
	public void setDistance(Airport2 connectingAp, Airport2 sourceAp, Airport2 destinationAp){
		double distance1, distance2, distance;
		distance1 = Math.sqrt(Math.pow(sourceAp.getLatitude()-connectingAp.getLatitude(), 2) + Math.pow(sourceAp.getLongitude()-connectingAp.getLongitude(),2));
		distance2 = Math.sqrt(Math.pow(connectingAp.getLatitude()-destinationAp.getLatitude(), 2) + Math.pow(connectingAp.getLongitude()-destinationAp.getLongitude(),2));
		distance = distance1+distance2;
		this.distance = distance;
	}

/**
 * Get flight
 * @return flight
 */
	public Flight getFlight() {
		return flight;
	}

/**
 * Set flight from departing airport to connecting airport
 * @param flight from departing airport to connecting airport
 */
	public void setFlight(Flight flight) {
		this.flight = flight;
	}

/**
 * Get code of connecting airport
 * @return Code of departing airport
 */
	public Airport2 getConnectingAp() {
		return connectingAp;
	}

/**
 * Get code of departing airport
 * @return code of departing airport
 */
	public Airport2 getSourceAp() {
		return sourceAp;
	}

/**
 * Get code of destination airport
 * @return code of destination airport
 */
	public Airport2 getDestinationAp() {
		return destinationAp;
	}

/**
 * Get total distance from depart airport to connecting airport and from connecting airport to destination airport
 * @return total distance from depart airport to connecting airport and from connecting airport to destination airport
 */
	public double getDistance() {
		return distance;
	}

/**
 * Set code of connecting airport
 * @param connectingAp code of connecting airport
 */
	public void setConnectingAp(Airport2 connectingAp) {
		this.connectingAp = connectingAp;
	}

/**
 * Set code of departing airport
 * @param sourceAp code of departing airport
 */
	public void setSourceAp(Airport2 sourceAp) {
		this.sourceAp = sourceAp;
	}

/**
 * Set code of destination airport
 * @param destinationAp code of destination airport
 */
	public void setDestinationAp(Airport2 destinationAp) {
		this.destinationAp = destinationAp;
	}



	
	

}
