package interactWithServer;
/**
 * Test_GetAirports.java
 * @author Team04
 */

public class GetAirportsTest {
   public static String getairport(){
		/**
		 * Get all the airports information 
		 * @throws Exception
		 */
		
	    GetAirplanes ty=new GetAirplanes();
	    String xml = ty.getData("Team04");
        System.out.println(xml);  
        
       if(xml!=null){
    	   System.out.println("test success!");
       }
       else{
    	   System.out.println("test fail!");
       }
       
        return null;				
		
	}

	  public static void main(String[] args){
		  GetAirportsTest.getairport();
		 }


}
