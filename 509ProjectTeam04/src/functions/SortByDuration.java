package functions;

import java.util.Comparator;
import entity.Line;
/**
 * Sort Flights according to fly duration (shorter to longer)
 * @author Team04
 *
 */
public class SortByDuration implements Comparator<Line>{
	@Override
	public int compare(Line o1, Line o2) {
		return o1.getDuration().compareTo(o2.getDuration());	
	}
	
}
