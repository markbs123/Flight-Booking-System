package functions;

import java.util.Comparator;
import entity.Line;
/**
 * Sort Flights according to take off time (earlier to later)
 * @author Team04
 *
 */
public class SortByTakeoffTime implements Comparator<Line>{
	@Override
	public int compare(Line o1, Line o2) {
		return o1.getTakeoffTime().compareTo(o2.getTakeoffTime());	
	}
	
}
