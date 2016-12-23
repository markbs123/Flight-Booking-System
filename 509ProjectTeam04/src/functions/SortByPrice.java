package functions;

import java.util.Comparator;
import entity.Line;
/**
 * Sort Flights according to price (lower to higher)
 * @author Team04
 *
 */
public class SortByPrice implements Comparator<Line>{
	@Override
	public int compare(Line o1, Line o2) {
		if(o1.getPrice() > o2.getPrice()) return 1;
		else if(o1.getPrice() == o2.getPrice()) return 0;
		else return -1;	
	}
	
}
