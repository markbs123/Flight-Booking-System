package interactWithServer;

/**Interact with database
 * @author Team04
 *
 */
public class QueryFactory {
	/**
     * Get all airports information
     * @param ticketAgency
     * @return s XML document of airports
     */
	
   public static String getAirports(String ticketAgency){
	   //http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team04&action=list&list_type=airports
	   String s="?team="+ticketAgency+"&action=list&list_type=airports";
	   return s;
   }
   /**
    * Get all airplanes information
    * @param ticketAgency
    * @return s XML document of airplanes
    */
   
   public static String getAirplanes(String ticketAgency){
	   //http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team04&action=list&list_type=airplanes
	   String s="?team="+ticketAgency+"&action=list&list_type=airplanes";
	   return s;
   }
   /**
    * Get departure flight information
    * @param ticketAgency
    * @param airportCode The code of all airports 
    * @param day The departure date
    * @return s XML document of departure flight in each day and each airport
    */
   public static String getDpFlights(String ticketAgency, String airportCode, String day){
	   //http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team04&action=list&list_type=departing&airport=JFK&day=2015_05_17
	   String s="?team="+ticketAgency+"&action=list&list_type=departing&airport="+airportCode+"&day="+day;
	   return s;
   }
   /**
    * Get arrival flight information
    * @param ticketAgency
    * @param airportCode The code of all airports 
    * @param day The arrival date
    * @return s XML document of arrival flight in each day and each airport
    */
   public static String getAvFlights(String ticketAgency, String airportCode, String day){
	   //http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=Team04&action=list&list_type=arriving&airport=SFO&day=2015_05_17
	   String s="?team="+ticketAgency+"&action=list&list_type=arriving&airport="+airportCode+"&day="+day;
	   return s;
   }
   /**
    * Lock the server
    * @param ticketAgency
    * @return s XML document of lock server
    */
   
   public static String lock(String ticketAgency){
	   String s="team="+ticketAgency+"&action=lockDB";
	   return s;
   }
   /**
    * Unlock the server
    * @param ticketAgency
    * @return s XML document of unlock server
    */
   
   public static String unlock(String ticketAgency){
	   String s="team="+ticketAgency+"&action=unlockDB";
	   return s;
   }
   /**
    * Reserve the booking information
    * @param ticketAgency
    * @param xmlFlights
    * @return s XML document of buy tickets
    */
   
   public static String reserve(String ticketAgency, String xmlFlights){
	   String s="team="+ticketAgency+"&action=buyTickets"+"&flightData="+xmlFlights;
	   return s;
   }
   
   /**
    * Reset the server
    * @param ticketAgency
    * @return s XML document of reset server
    */
     public static String reset(String ticketAgency){
	   String s="?team="+ticketAgency+"&action=resetDB";
	   return s;
   }
}
