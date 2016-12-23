package functions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import timeRelated.TimeRelated;
import entity.Flight;
import entity.Line;
/**
 * Deal with related data of flights from one airport to another (direct flights and connecting flights), 
 * these data including total price, total flight duration, taking off time, landing time,
 * flight detail of each leg, lay-over time of each leg
 * @author Team04
 *
 */
public class manageLineData {
	/**
	 * Given one ArrayList of Flight from one airport to another, find related data of this ArrayLis of Flight, 
	 * including total price, total flight duration, taking off time, landing time, flight detail of each leg, 
	 * lay-over time of each leg, convert this ArrayList of Flight to Line
	 * @param okFlights An arrayList of Flight from one airport to another
	 * @param seat "FirstClass" or  "Coach".
	 * @return a Line from one airport to another
	 */
	public static Line getLineInfo(ArrayList<Flight> okFlights, String seat){
		Line line = new Line();
		ArrayList<Integer> layoverTime = new ArrayList<Integer>();
		int duration;
		double price;
		Date takeoffTime;
		Date landingTime;
	
		/*1. get layoverTime and total duration*/
		int totalFlyTime=0;
		for(Flight fl:okFlights){
			totalFlyTime += fl.getFlightTime();	
		}
		
		if(okFlights.size()==1) { 
			layoverTime = null; 
			duration = totalFlyTime;
			}
		else if(okFlights.size()==2){
			TimeRelated.subtractTwoDate(okFlights.get(1).getDpTime(), okFlights.get(0).getAvTime());
			layoverTime.add(TimeRelated.subtractTwoDate(okFlights.get(1).getDpTime(), okFlights.get(0).getAvTime()));
			duration = totalFlyTime + layoverTime.get(0);
			
		}
		else {
			layoverTime.add(TimeRelated.subtractTwoDate(okFlights.get(1).getDpTime(), okFlights.get(0).getAvTime()));
			layoverTime.add(TimeRelated.subtractTwoDate(okFlights.get(2).getDpTime(), okFlights.get(1).getAvTime()));
			duration = totalFlyTime + layoverTime.get(0) + layoverTime.get(1);
		}
		
		
		/*2. get total price*/
		if(seat.equals("FirstClass")){
			price  = 0;
			for(Flight fl:okFlights){
				if(fl.getFirstClassSeats()!=0){
					double firstClassPrice = Double.parseDouble((fl.getFirstClassPrice()).replace("$", "").replace(",",""));
					price += firstClassPrice ;}
				else{
					double coachPrice = Double.parseDouble((fl.getCoachPrice()).replace("$", ""));
				    price += coachPrice; }
			}
		}
		else{
			price  = 0;
			for(Flight fl:okFlights){
				String priceStr;
				if(fl.getCoachSeats()!=0){
					priceStr = ((fl.getCoachPrice()).replace("$", "")).replace(",", "");
					double coachPrice = Double.parseDouble(priceStr);
					price += coachPrice ;}
				else{
					priceStr = ((fl.getFirstClassPrice()).replace("$", "")).replace(",", "");
					double firstClassPrice = Double.parseDouble(priceStr);
				    price += firstClassPrice; }
			}
		}
		
		/*3. get takeoffTime and landingTime*/
		takeoffTime = okFlights.get(0).getDpTime();
		int size = okFlights.size();
		landingTime = okFlights.get(size-1).getAvTime();
		
		
		line.setDuration(duration); 
		line.setLayoverTime(layoverTime);
		line.setFlights(okFlights);
		line.setPrice(price);
		line.setTakeoffTime(takeoffTime);
		line.setLandingTime(landingTime);
		return line;
	}
	
	/**
	 * Convert an integer minute like "80" to a String of time like "1h 20min".
	 * @param minutes 
	 * @return
	 */
	public static String timeToString(int minutes){
		String hourAndMinute;
		if(minutes<60) 
			hourAndMinute = Integer.toString(minutes)+"min";
		else 
			hourAndMinute = Integer.toString(minutes/60)+"h "+ Integer.toString(minutes%60)+"min";
		return hourAndMinute;
	}

	/**
	 * Convert a double price like "36.06" to a String of price like "$36.02"
	 * @param price
	 * @return
	 */
	public static String priceToString(double price){
		//String priceString = "$"+Double.toString(price);
		DecimalFormat df = new DecimalFormat("#.##");
		String priceString = "$"+df.format(price);
		return priceString;
	}



	/**
	 * Create an ArrayList of line to store all possible fly schemes from airport A to airport B. 
	 * @param allokFlights  ArrayList of ArrayList of Flight from airport A to airport B, including direct and connecting flights
	 * @param seat "FirstClass" or "Coach".
	 * @return ArrayList of line from airport A to airport B
	 */
	public static ArrayList<Line> getAllLines(ArrayList<ArrayList<Flight>> allokFlights, String seat){
		ArrayList<Line> allokLine = new ArrayList<Line>();
		for(ArrayList<Flight> fls:allokFlights){
			Line line = manageLineData.getLineInfo(fls,seat);
			allokLine.add(line);
		}
		return allokLine;
	}
	
}
