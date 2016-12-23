package interactWithServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Check the database situation and try to lock the database to make sure that there is no other user to exchange data with the database 
 * @author Team04
 */
public class lock {
	/**
	 * Use HttpUrlConnection to interact with the server
	 * @param ticketAgency 
	 * @return
	 */
  public boolean doLock(String ticketAgency){
	  URL url;
	  HttpURLConnection connection;
	  String mUrlBase="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";
			  
	  try{
		  url = new URL(mUrlBase);
		  connection = (HttpURLConnection) url.openConnection();
		  connection.setRequestMethod("POST");
		  
		  String params = QueryFactory.lock(ticketAgency);
		  
		  connection.setDoOutput(true);
		  connection.setDoInput(true);
		  
		  DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
		  writer.writeBytes(params);
		  writer.flush();
		  writer.close();
		  
		  int responseCode = connection.getResponseCode();
		  System.out.println("\nSending 'POST' to lock database");
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
}
