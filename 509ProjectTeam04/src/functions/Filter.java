package functions;

import java.util.ArrayList;
import java.util.Date;

import entity.Flight;
import entity.Line;
/**
 * Given an list of all possible flights from source airport to destination airport, filter these list by specifying class of seat,
 * by specifying number of stops, and by specifying time window
 * @author Team04
 *
 */
public class Filter {
	/**
	 * Filter flights by desired seat class, get flights with desired seatClass
	 * @param allokFlights An list of all possible flights from source airport to destination airport
	 * @param seat "FirstClass" or "Coach"
	 * @return An list of all flights from source airport to destination airport with specified seat class
	 */
	public static ArrayList<ArrayList<Flight>> doFilterBySeats1(ArrayList<ArrayList<Flight>> allokFlights, String seat){
		ArrayList<ArrayList<Flight>> filterBySeat1 = new ArrayList<ArrayList<Flight>>(allokFlights);
		if(seat.equals("FirstClass")){
			 for(ArrayList<Flight> fls:allokFlights){
				 for(Flight fl:fls){
					 if(fl.getFirstClassSeats()==0) {
						 filterBySeat1.remove(fls); 
						 break;
					 } //end inner if
				 } //end inner for
			   }	// end outer for		 
		   }//end outer if
		else {
			 for(ArrayList<Flight> fls:allokFlights){
				 for(Flight fl:fls){
					 if(fl.getCoachSeats()==0) {
						 filterBySeat1.remove(fls); 
						 break;
					 } //end inner if
				 } //end inner for
			   }	// end outer for		 
		   }//end outer if
	    return filterBySeat1;
	}
	
	/**
	 * Filter flights by desired seat class, get flights without desired seatClass
	 * @param allokFlights An list of all possible flights from source airport to destination airport
	 * @param seat "FirstClass" or "Coach"
	 * @return An list of all flights from source airport to destination airport without specified seat class
	 */
	public static ArrayList<ArrayList<Flight>> doFilterBySeats2(ArrayList<ArrayList<Flight>> allokFlights, String seat){
		ArrayList<ArrayList<Flight>> filterBySeat2 = new ArrayList<ArrayList<Flight>>();
		if(seat.equals("FirstClass")){
			 for(ArrayList<Flight> fls:allokFlights){
				 for(Flight fl:fls){
					 if(fl.getFirstClassSeats()==0) {
						 filterBySeat2.add(fls); 
						 break;
					 } //end inner if
				 } //end inner for
			   }	// end outer for		 
		   }//end outer if
		else {
			 for(ArrayList<Flight> fls:allokFlights){
				 for(Flight fl:fls){
					 if(fl.getCoachSeats()==0) {
						 filterBySeat2.add(fls); 
						 break;
					 } //end inner if
				 } //end inner for
			   }	// end outer for		 
		   }//end outer if
	    return filterBySeat2;
	}
		
	/**
	 * Filter flights by number of stops
	 * @param allokLine An list of all possible flights from source airport to destination airport
	 * @param stopInfo "NonStop", "OneStop", "TwoStop" or any combination of them
	 * @return An list of flights from source airport to destination airport with specified number of stop-overs
	 */
	public static ArrayList<Line> doFilterByStop(ArrayList<Line> allokLine, String stopInfo){
		ArrayList<Line> filterByStop = new ArrayList<Line>();
		ArrayList<Line> nonstop = new ArrayList<Line>();
		ArrayList<Line> onestop = new ArrayList<Line>();
		ArrayList<Line> twostop = new ArrayList<Line>();
		for(Line line:allokLine){
			if(line.getFlights().size()==1)  nonstop.add(line);
			else if(line.getFlights().size()==2) onestop.add(line);
			else twostop.add(line);	
			}
		if(stopInfo.equals("NonStop"))  filterByStop = nonstop;
		if(stopInfo.equals("OneStop"))  filterByStop = onestop;
		if(stopInfo.equals("TwoStop"))  filterByStop = twostop;
		if(stopInfo.equals("NonStop"+"OneStop"))  {filterByStop.addAll(nonstop); filterByStop.addAll(onestop);}
		if(stopInfo.equals("NonStop"+"TwoStop"))  {filterByStop.addAll(nonstop); filterByStop.addAll(twostop);}
		if(stopInfo.equals("OneStop"+"TwoStop"))  {filterByStop.addAll(onestop); filterByStop.addAll(twostop);}
		if(stopInfo.equals("NonStop"+"OneStop"+"TwoStop"))  {filterByStop = allokLine;}
		return filterByStop;
	}

	/* filter flights by specified depart time window */
//	public static ArrayList<Line> doFilterByDpTime(ArrayList<Line> allokLine, Date dpMinTime, Date dpMaxTime){
//		// error: java.util.ConcurrentModificationException
//		// ArrayList<ArrayList<Flight>> filterByDpTime =  allokFlights;
//		// correct: ArrayList<ArrayList<Flight>> filterByDpTime = new ArrayList<ArrayList<Flight>>(allokFlights);
//		ArrayList<Line> filterByDpTime = new ArrayList<Line>();
//		for(Line line:allokLine){
//			if( ((line.getTakeoffTime()).compareTo(dpMinTime)>=0) && ((line.getTakeoffTime()).compareTo(dpMaxTime)<=0)){
//				filterByDpTime.add(line);
//			}	
//		}
//		return filterByDpTime;
//	}
	
	/* filter flights by specified arrive time window */
//	public static ArrayList<Line> doFilterByAvTime(ArrayList<Line> allokLine, Date avMinTime, Date avMaxTime){
//		//ArrayList<ArrayList<Flight>> filterByDpTime = allokFlights;
//		//ArrayList<ArrayList<Flight>> filterByDpTime = new ArrayList<ArrayList<Flight>>(allokFlights);
//		ArrayList<Line> filterByAvTime = new ArrayList<Line>();
//		for(Line line:allokLine){
//				if( ((line.getLandingTime()).compareTo(avMinTime)>=0) && ((line.getLandingTime()).compareTo(avMaxTime)<=0)){
//					filterByAvTime.add(line);
//				}		
//		}
//		return filterByAvTime;
//	}
	
	/**
	 * Filter flights by time window
	 * @param allokLine An list of all possible flights from source airport to destination airport
	 * @param dpMinTime The earliest time of departing from source airport
	 * @param dpMaxTime The latest time of landing at destination airport
	 * @param avMinTime The earliest time of departing from source airport
	 * @param avMaxTime The latest time of landing at destination airport
	 * @return An list of flights from source airport to destination airport in specified time window
	 */
	/* filter flights by specified depart and arrive time window */
	public static ArrayList<Line> doFilterByDATime(ArrayList<Line> allokLine, Date dpMinTime, Date dpMaxTime, Date avMinTime, Date avMaxTime){
		ArrayList<Line> filterByDATime = new ArrayList<Line>();
		for(Line line:allokLine){
				if( ((line.getLandingTime()).compareTo(avMinTime)>=0) && ((line.getLandingTime()).compareTo(avMaxTime)<=0) 
						&& ((line.getTakeoffTime()).compareTo(dpMinTime)>=0) && ((line.getTakeoffTime()).compareTo(dpMaxTime)<=0)){
					filterByDATime.add(line);
				}		
		}
		return filterByDATime;
	}
	


}
