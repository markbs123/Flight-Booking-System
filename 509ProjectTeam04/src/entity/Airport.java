package entity;

/**
 * Airport
 * @author Team04
 *
 */
public class Airport {
	/** Three-letter Code of the airport*/
	private String Code;
	/** Name of the airport  */
	private String Name;
	/** Latitude of the airport */
	private double Latitude;
	/** Longitude of the airport */
	private double Longitude;
	
	public Airport(){
		
	}

	/**
	 * Get code of the airport 
	 * @return code of the airport 
	 */
	public String getCode() {
		return Code;
	}

	/**
	 * Set code of the airport 
	 * @param code code of the airport 
	 */
	public void setCode(String code) {
		Code = code;
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
