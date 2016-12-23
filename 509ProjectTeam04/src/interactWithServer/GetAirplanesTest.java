package interactWithServer;
/**
 * Test_GetAirplanes.java
 * @author Team04
 */
public class GetAirplanesTest {
	/**
	 * Get all the airplane information 
	 * @return
	 */
	
	public static String getairplane(){
		
		
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
		  GetAirplanesTest.getairplane();
		 }

}
