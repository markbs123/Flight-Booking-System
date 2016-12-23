package interactWithServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Connect with server and Submit the select Flight information which contains the specific flight number and the seat class
 * @author Team04
 *
 */
public class buyTicket {
	/**
	 * Use HttpUrlConnection to post submission, give the requirement to the server to ensure the sever can receive it and response
	 * @param ticketAgency 
	 * @param xml
	 * @return
	 */
	public static boolean doBuyTicket(String ticketAgency, String xml){
		  URL url;
		  HttpURLConnection connection;
		  String mUrlBase="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";
		  
		  try{
			  url = new URL(mUrlBase);
			  connection = (HttpURLConnection) url.openConnection();
			  connection.setRequestMethod("POST");
			  
			  String params = QueryFactory.reserve(ticketAgency,xml);
			  System.out.println(params);
			  
			  connection.setDoOutput(true);
			  connection.setDoInput(true);
			  
			  DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			  writer.writeBytes(params);
			  writer.flush();
			  writer.close();
			  
			  int responseCode = connection.getResponseCode();
			  System.out.println("\nSending 'POST' to buy ticket");
			  System.out.println("\nResponse Code: " + responseCode);
			  
			  if((responseCode >= 200)&&(responseCode<=299)){
				  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				  String line;
				  StringBuffer response = new StringBuffer();
				  
				  while((line = in.readLine()) != null){
					  response.append(line);
				  }
				  in.close();
				  System.out.println(response.toString());
				  return true;
			  }
			  
		  }catch(IOException ex){
			  ex.printStackTrace();
			  return false;
		  }catch(Exception ex){
			  ex.printStackTrace();
			  return false;
		  }
		  return false;
	  }

	/**
	 * Build the new XML to save the information with the specific flight number and seat class 
	 * @param flightNum The user selected flight number
	 * @param flightSeat "FirstClass" or "Coach"
	 * @return xml
	 */
	public static String getxmlString(String[] flightNum, String[] flightSeat, String ticketAgency){
		String xml = "<Flights>";
		for(int i=0; i<flightNum.length; i++){
			xml += "<Flight number=\""+flightNum[i]+"\""
                              + " seating=\""+flightSeat[i]+"\""
                              + " />";	
		}
		xml += "</Flights>";
		return xml;
	}
	
	/**
	 * Merge the two array list to create a new array list 
	 * @return both
	 */
	public static String[] mergeArray(String[] first, String[] second){
		int newLength = first.length + second.length;
		String[] both = new String[newLength];
		for(int i=0; i<newLength; i++){
			if(i<first.length){
			   both[i] = first[i];
			}
			else
				both[i] = second[i-first.length];
		}
		return both;
	}
	
//  public static void main(String[] args){
//		
//		String ticketAgency = "Team04";
//		String[] flightNum={"12100","12950"};
//		String[] flightSeat={"Coach","FirstClass"};
//		
//		lock lk=new lock();
//		unlock ulk=new unlock();
//		
//		String xml = getxmlString(flightNum, flightSeat, ticketAgency);
//		System.out.println(xml);
//		/*lk.doLock(ticketAgency);
//		buyTicket.doBuyTicket(ticketAgency, xml);
//		ulk.doUnlock(ticketAgency);*/
//
//  }

}
