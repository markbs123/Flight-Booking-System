package interactWithServer;
/**
 * Test_GetAvFlights.java
 * @author Team04
 */
public class GetAvFlightsTest {
	/**
	 * Get the xml document which contains the arrival flight information on the specific airport code and date
	 * @return
	 */

     public static String getavflight(){
		    	 
    		String ticketAgency = "Team04";
    		String airportCode = "PHL";
    		String day= "2015_05_09";
    		
    	    GetAvFlights ty=new GetAvFlights();
    		String result = ty.getData(ticketAgency, airportCode, day);
    		System.out.println("actual result:"+result);
    		     
           if(result!=null){
    	       System.out.println("test success!");
        }
        else{
    	      System.out.println("test fail!");
       }
       
             return null;				
		
	}

	public static void main(String[] args){
		  GetAvFlightsTest.getavflight();
		 }
}
