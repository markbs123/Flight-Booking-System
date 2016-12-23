package entity;

import java.util.Date;
/**
 * Flight
 * @author Team04
 *
 */
public class Flight {
	/** Model of airplane */
	private String airplaneModel;  
	/** code of departing airport */
	private String dpApCode; 
	/** code of arriving airport */
	private String avApCode;  
	/** departing time of the flight (this is local time of the departing airport)*/
	private Date dpTime; 
	/** arriving time of the flight (this is local time of the arriving airport)*/
	private Date avTime; 
	/** fly duration of the flight*/
	private int FlightTime;  
	/** Flight number*/
	private String FlightNumber;
	/** price of first-class seats of this flight*/
	private String FirstClassPrice;
	/** number of first-class seats of this flight*/
	private int FirstClassSeats;
	/** price of coach seats of this flight*/
	private String CoachPrice;  
	/** number of coach seats of this flight*/
	private int CoachSeats;
	
	public Flight(){
		
	}
	
    /**
     * Get model of the airplane
     * @return model of the airplane
     */
	public String getAirplaneModel() {
		return airplaneModel;
	}

	/**
	 * Set model of the airplane
	 * @param airplaneModel model of the airplane
	 */
	public void setAirplaneModel(String airplaneModel) {
		this.airplaneModel = airplaneModel;
	}

	/**
	 * Get code of departing airport
	 * @return code of departing airport
	 */
	public String getDpApCode() {
		return dpApCode;
	}

	/**
	 * Set code of departing airport
	 * @param dpApCode code of departing airport
	 */
	public void setDpApCode(String dpApCode) {
		this.dpApCode = dpApCode;
	}

	/**
	 * Get code of arriving airport
	 * @return code of arriving airport
	 */
	public String getAvApCode() {
		return avApCode;
	}

	/**
	 * Set code of arriving airport
	 * @param avApCode code of arriving airport
	 */
	public void setAvApCode(String avApCode) {
		this.avApCode = avApCode;
	}

	/**
	 * Get flight duration
	 * @return flight duration
	 */
	public int getFlightTime() {
		return FlightTime;
	}

	/**
	 * Set flight duration
	 * @param flightTime flight duration
	 */
	public void setFlightTime(int flightTime) {
		FlightTime = flightTime;
	}

	/**
	 * Get flight number
	 * @return flight number
	 */
	public String getFlightNumber() {
		return FlightNumber;
	}

	/**
	 * Set flight number
	 * @param flightNumber flight number
	 */
	public void setFlightNumber(String flightNumber) {
		FlightNumber = flightNumber;
	}

	/**
	 * Get price of first-class seats of this flight
	 * @return price of first-class seats of this flight
	 */
	public String getFirstClassPrice() {
		return FirstClassPrice;
	}

	/**
	 * Set price of first-class seats of this flight
	 * @param firstClassPrice price of first-class seats of this flight
	 */
	public void setFirstClassPrice(String firstClassPrice) {
		FirstClassPrice = firstClassPrice;
	}

	/**
	 * Get number of first-class seats of this flight
	 * @return number of first-class seats of this flight
	 */
	public int getFirstClassSeats() {
		return FirstClassSeats;
	}

	/**
	 * Set number of first-class seats of this flight
	 * @param firstClassSeats number of first-class seats of this flight
	 */
	public void setFirstClassSeats(int firstClassSeats) {
		FirstClassSeats = firstClassSeats;
	}

	/**
	 * Get price of coach seats of this flight
	 * @return price of coach seats of this flight
	 */
	public String getCoachPrice() {
		return CoachPrice;
	}

	/**
	 * Set price of coach seats of this flight
	 * @param coachPrice price of coach seats of this flight
	 */
	public void setCoachPrice(String coachPrice) {
		CoachPrice = coachPrice;
	}

	/**
	 * Get number of coach seats of this flight
	 * @return number of coach seats of this flight
	 */
	public int getCoachSeats() {
		return CoachSeats;
	}

	/**
	 * Set number of coach seats of this flight
	 * @param coachSeats number of coach seats of this flight
	 */
	public void setCoachSeats(int coachSeats) {
		CoachSeats = coachSeats;
	}

	/**
	 * Get departing time of this flight (local time of the departing airport)
	 * @return departing time of this flight (local time of the departing airport)
	 */
	public Date getDpTime() {
		return dpTime;
	}

	/**
	 * Set departing time of this flight (local time of the departing airport)
	 * @param dpTime departing time of this flight (local time of the departing airport)
	 */
	public void setDpTime(Date dpTime) {
		this.dpTime = dpTime;
	}

	/**
	 * Get arriving time of this flight (local time of the arriving airport)
	 * @return arriving time of this flight (local time of the arriving airport)
	 */
	public Date getAvTime() {
		return avTime;
	}

	/**
	 * Set arriving time of this flight (local time of the arriving airport)
	 * @param avTime arriving time of this flight (local time of the arriving airport)
	 */
	public void setAvTime(Date avTime) {
		this.avTime = avTime;
	}
	

}
