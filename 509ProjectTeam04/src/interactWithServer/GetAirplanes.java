package interactWithServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Connect with server to get the xml of Airplanes
 * @author Team04
 */
public class GetAirplanes {
	/**
	 * Use HttpUrlConnection to interact with the server
	 * @param ticketAgency 
	 * @return
	 */
	
	public String getData(String ticketAgency){
		URL url;
		String mUrlBase="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		try{
			url = new URL(mUrlBase + QueryFactory.getAirplanes(ticketAgency));
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			//connection.setRequestProperty("User-Agent", ticketAgency);
			
			int responseCode = connection.getResponseCode();
			if((responseCode)>=200 && (responseCode<=299)){
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);
				
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while((line = reader.readLine()) != null){
					result.append(line);	
				}
			    reader.close();
			}
		} catch (IOException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return result.toString();
	}
}
