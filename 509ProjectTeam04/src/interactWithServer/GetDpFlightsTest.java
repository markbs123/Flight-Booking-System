package interactWithServer;
/**
 * Test_GetAvFlights.java
 * @author Team04
 */
public class GetDpFlightsTest {
	  public static String getdpflight(){
     /**
      * Get the xml document which contains the departure flight information on the specific airport code and date
	  * @throws Exception
	  */
		String ticketAgency = "Team04";
  		String airportCode = "JFK";
  		String day= "2015_05_17";
  		
  	    GetDpFlights ty=new GetDpFlights();
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
		  GetDpFlightsTest.getdpflight();
		 }

}
