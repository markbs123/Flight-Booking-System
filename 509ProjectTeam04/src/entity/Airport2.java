package entity;
/**
 * The difference between Airport.java and Airport2.java is:  
 * in Airport.java, attributes including airport code, airport name, latitude and longitude
 * in Airport2.java, attributes only including airport name, latitude and longitude. <br>
 * Airport is used in ArrayList of Airport, 
 * Airport2 is used in HashMap in which Airport2 is the value of HashMap,
 * while Airport code is used as the key.
 * @author Team04
 *
 */
public class Airport2 {
	/** Name of the airport  */
	private String Name;
	/** Latitude of the airport */
	private double Latitude;
	/** Longitude of the airport */
	private double Longitude;
	
	public Airport2(){
		
	}

	/**
	 * Get name of the airport
	 * @return name of the airport
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Set name of the airport
	 * @param name name of the airport
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Get latitude of the airport
	 * @return latitude of the airport
	 */
	public double getLatitude() {
		return Latitude;
	}

	/**
	 * Set latitude of the airport
	 * @param latitude latitude of the airport
	 */
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	/**
	 * Get longitude of the airport
	 * @return longitude of the airport
	 */
	public double getLongitude() {
		return Longitude;
	}

	/**
	 * Set longitude of the airport
	 * @param longitude longitude of the airport
	 */
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
}
