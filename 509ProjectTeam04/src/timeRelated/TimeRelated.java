package timeRelated;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * Tools for dealing with time
 * @author Team04
 */

/*use this class to decide if a date is before 3:59pm or after 11:59 or between 3:59~11:59
if it's before 3:59pm, only search flights on the same day
if it's after 11:59pm, only search flights on the next day("Read-eye"Flights)
if it's between 3:59pm--11:59pm, search both flights on the same day and the next day
*/
public class TimeRelated {

	/**
	 * Given a Date like "2015_05_03 13:30:00", find the String of the day "2015_5_3"
	 * @param date
	 * @return String of the day
	 */
	public static String findTheDay(Date date){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = (calendar.get(Calendar.DATE));
		calendar.set(Calendar.DATE, day);
		String dateString;
		dateString = Integer.toString(year) + "_" + Integer.toString(month)+ "_" + Integer.toString(day);
		return dateString;    	
	}
	
	/**
	 * convert string like "13:58   5/13/2015" to date
	 * @param dateString 
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String dateString) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(("HH:mm   MM/dd/yyyy"));
		Date date = sdf.parse(dateString);
		return date;	
	}

	/**
	 * convert string like "2015_5_17" to date "2015_05_17 00:00"
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Date findTheDate(String day) throws ParseException{  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		Date date = sdf.parse(day);
		return date;
	}

	/**
	 * Given a string of day like "2015_5_17", find the string of next day "2015_5_18"
	 * @param day
	 * @return
	 * @throws ParseException
	 */
		public static String findNextDayString(String day) throws ParseException{  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
			Date date1 = sdf.parse(day);
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date1);
			int day2 = (calendar.get(Calendar.DATE)) + 1;
			calendar.set(Calendar.DATE, day2);
			Date date2 = calendar.getTime();
			return(TimeRelated.findTheDay(date2));
		}
	
    /**
     * Convert a date to string in the format of "HH:mm   MM/dd/yyyy"
     * @param date
     * @return
     */
	public static String dateFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm   MM/dd/yyyy");
		String str = sdf.format(date);
		return str;
	}
	
	/**
	 * Find the list of date between two dates
	 * @param min all dates in the list should be later than the date specified by "min"
	 * @param max all dates in the list should be earlier than the date specified by "max"
	 * @return
	 */
	public static ArrayList<Date> dateBetweenMinAndMax(Date min, Date max){
		ArrayList<Date> dates = new ArrayList<Date>();
		Calendar calendar= GregorianCalendar.getInstance();  
		calendar.setTime(min);
		calendar.set(Calendar.MINUTE, 0);
		Date date = calendar.getTime();
		dates.add(date);	
		do{
		    date = TimeRelated.findNextTime(date, 60);
			dates.add(date);	
		}while(date.compareTo(max)<=0);
		return dates;
	}
	
	/**
	 * Given a time like "2015_05_17 15:00", find the time which is certain minutes later than the time, like "2015_05_17 15:30"
	 * @param date1
	 * @param minutes 
	 * @return
	 */
	//Method4. add 30minutes to a time: give a time like "2015_05_17 15:00", find next time "2015_05_17 15:30"
	public static Date findNextTime(Date date1, int minutes){
		Calendar calendar2 = GregorianCalendar.getInstance();  
		calendar2.setTime(date1);
		int minute2 = calendar2.get(Calendar.MINUTE) + minutes;
		calendar2.set(Calendar.MINUTE, minute2);
		Date date2 = calendar2.getTime();
		return date2;
	}
	
	/**
	 * Given a date in GMT format and the airport Code, get the date in local time format
	 * @param date
	 * @param airportCode
	 * @return
	 */
		public static Date findLocalTime(Date date, String airportCode){
			HashMap<String, Integer> airportTimeZone = AirportTimeZone.getAirportTimeZone();
			int hour = airportTimeZone.get(airportCode);

			Calendar calendar = GregorianCalendar.getInstance();  
			calendar.setTime(date);
			int hour2 = calendar.get(Calendar.HOUR) + hour;
			calendar.set(Calendar.HOUR, hour2);
			Date date2 = calendar.getTime();
			return date2;
			}
	
		/**
		 * Given a date in local time and the airport Code, get the date in GMT time
		 * @param date
		 * @param airportCode
		 * @return
		 */
		public static Date findGMTTime(Date date, String airportCode){
			HashMap<String, Integer> airportTimeZone = AirportTimeZone.getAirportTimeZone();
			int hour = airportTimeZone.get(airportCode);

			Calendar calendar = GregorianCalendar.getInstance();  
			calendar.setTime(date);
			int hour2 = calendar.get(Calendar.HOUR) - hour;
			calendar.set(Calendar.HOUR, hour2);
			Date date2 = calendar.getTime();
			return date2;
		}
    /**
     * Given a date like "2015_05_17", find the String of the next day "2015_05_18"
     * @param date1
     * @return
     */
		public static String findNextDayDate(Date date1){
		    Calendar calendar2 = GregorianCalendar.getInstance();
		    calendar2.setTime(date1);
		    int year = calendar2.get(Calendar.YEAR);
		    int month = calendar2.get(Calendar.MONTH) + 1;
		    int day = (calendar2.get(Calendar.DATE)) + 1;
		    calendar2.set(Calendar.DATE, day);
		    String date2 = Integer.toString(year) + "_" + Integer.toString(month)+ "_" + Integer.toString(day);
		    return date2;    	
		}
	
	/**
	 * Compare two dates. One of the date is specified by "date1", the other date is on the same day with date1, but
	 * its hour and minutes are specified by integer "hours" and "minutes".<br>
	 * @param date1 
	 * @param hours
	 * @param minutes
	 * @return if return value is larger than zero, date1 is later; else, date1 is earlier.
	 */
	public static int doCompare(Date date1, int hours, int minutes){
		  /*step 1. create a new Date "date2", whose month/year/day are the same as date2,
                   but hours:minutes are specified by the function parameters */
			Calendar calendar2 = GregorianCalendar.getInstance();  
			calendar2.setTime(date1);
			calendar2.set(Calendar.HOUR_OF_DAY, hours);  
			calendar2.set(Calendar.MINUTE, minutes);
			Date date2 = calendar2.getTime();
		 /*step 2. compare date 1 and date 2*/
			int i = date1.compareTo(date2);
			return i;  // i>0, date1 is later hours:minutes; i<0, date1 is before hours:minutes
	}
	
	/**
	 * Subtract two dates, get the time difference in minutes
	 * @param date1
	 * @param date2
	 * @return difference of minutes of the two dates
	 */
	public static Integer subtractTwoDate(Date date1, Date date2){
		int diff = (int) (((Math.abs( (date2.getTime())- (date1.getTime())))/1000)/60);
		return(diff);
	}
	
	
}
