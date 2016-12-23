package entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Total duration, total price, taking off time, landing time, a list of connecting flights, and a list 
 * of stop-over time from source airport to destination airport
 * @author Team04
 * 
 */
public class Line {
	/** the direct flight or the list of connecting flights from source airport to destination airport */
	private ArrayList<Flight> flights;
	/** list of lay-over time between two connecting legs from source airport to destination airport */
	private ArrayList<Integer> layoverTime;
	/** total fly duration time */
	private Integer duration;
	/** total price  */
	private double price;
	/** taking off time */
	Date takeoffTime;
	/** landing time */
	Date landingTime;

	public Line(){
		
	}

	/**
	 * Get a list of connecting flights from source airport to destination airport
	 * @return a list of connecting flights from source airport to destination airport
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * Get a list of layoverTime
	 * @return a list of layoverTime
	 */
	public ArrayList<Integer> getLayoverTime() {
		return layoverTime;
	}

	/**
	 * Get total duration
	 * @return total duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * Get total price
	 * @return total price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Get taking off time when departing from source airport
	 * @return taking off time when departing from source airport 
	 */
	public Date getTakeoffTime() {
		return takeoffTime;
	}
	
	/**
	 * Get landing time when landing at destination airport
	 * @return landing time when landing at destination airport
	 */
	public Date getLandingTime() {
		return landingTime;
	}

	/**
	 * Set a list of connecting flights from source airport to destination airport
	 * @param flights a list of connecting flights from source airport to destination airport
	 */
	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}

	/**
	 * Set a list of layoverTime
	 * @param layoverTime a list of layoverTime
	 */
	public void setLayoverTime(ArrayList<Integer> layoverTime) {
		this.layoverTime = layoverTime;
	}

	/**
	 * Set total duration
	 * @param duration total duration
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * Set total price
	 * @param price total price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Set taking off time when departing from source airport
	 * @param takeoffTime taking off time when departing from source airport
	 */
	public void setTakeoffTime(Date takeoffTime) {
		this.takeoffTime = takeoffTime;
	}
	
	/**
	 * Set landing time when arriving at destination airport
	 * @param landingTime landing time when arriving at destination airport
	 */
	public void setLandingTime(Date landingTime) {
		this.landingTime = landingTime;
	}
	
	
}
