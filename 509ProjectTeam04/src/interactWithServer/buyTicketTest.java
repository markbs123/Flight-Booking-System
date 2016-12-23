package interactWithServer;

/**
 * Test_buyTicket.java
 * @author Team04
 */
public class buyTicketTest {
	/**
	 * Specify the input flight number and seat class of test
	 * @return The new XML which contains the input information
	 */
	
	   public static String getxmlStringTest(){

			String ticketAgency = "Team04";
			String[] flightNum = {"12100","12950"};
			String[] flightSeat= {"FirstClass","Coach"};
			
			
			String result = buyTicket.getxmlString(flightNum, flightSeat, ticketAgency);
			
			String expected ="<Flights><Flight number=\"12100\" seating=\"FirstClass\" /><Flight number=\"12950\" seating=\"Coach\" /></Flights>";
			
			System.out.println("expected result:"+expected);
			System.out.println("actual result:"+result);
		    if(expected.equals(result)){
		    	System.out.println("test success!");
		    }
		    else
		    	 System.out.println("test fail!");
			
		    return result;
		    	
		}
	    
	    public static void main(String[] args){
	    	buyTicketTest.getxmlStringTest();
	  
		
	  }


}
