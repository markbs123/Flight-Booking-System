package functions;

import java.util.Comparator;
import entity.Line;
/**
 * Sort Flights according to landing time (earlier to later)
 * @author Team04
 *
 */
public class SortByLandingTime implements Comparator<Line>{
	@Override
	public int compare(Line o1, Line o2) {
		return o1.getLandingTime().compareTo(o2.getLandingTime());	
	}
	
}
