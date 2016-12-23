package entity;
/**
 * Airplane
 * @author Team04
 *
 */
public class Airplane {
	/**Manufacturer of the airplane */
	private String Manufacturer;
	/**Total number of first-class seats on the airplane*/
	private int FirstClassSeats;
	/**Total number of coach seats on the airplane*/
	private int CoachSeats;
	
	public Airplane(){
		
	}
	
    /**
     * Get manufacturer of the airplane
     * @return manufacturer of the airplane
     */
	public String getManufacturer() {
		return Manufacturer;
	}

    /**
     * Set manufacturer of the airplane
     * @param manufacturer
     */
	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}
    
	/**
	 * Get total number of first-class seats of the airplane
	 * @return total number of first-class seats of the airplane
	 */
	public int getFirstClassSeats() {
		return FirstClassSeats;
	}
    
	/**
	 * Set total number of first-class seats of the airplane
	 * @param firstClassSeats total number of first-class seats of the airplane
	 */
	public void setFirstClassSeats(int firstClassSeats) {
		FirstClassSeats = firstClassSeats;
	}
	
    /**
     * Get total number of coach seats of the airplane
     * @return total number of coach seats of the airplane
     */
	public int getCoachSeats() {
		return CoachSeats;
	}
    
	/**
	 * Set total number of coach seats of the airplane
	 * @param coachSeats total number of coach seats of the airplane
	 */
	public void setCoachSeats(int coachSeats) {
		CoachSeats = coachSeats;
	}
	
	

}
