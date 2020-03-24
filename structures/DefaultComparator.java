import java.util.Comparator;

/**
 * 
 */

/**
 *There two ways to compare in java:
 *1) comparable interface ==> already implemneted in java.util.Comparable which uses natural order of object in question.
 *2) comparator interface ==> includes the int Compare method which can be defined by the user and works similar to compareTo
 * @author sid16
 *
 */
public class DefaultComparator<E> implements Comparator<E> {
	public int COMPARE(String a, String b) {//USELESS!
		if(b.length() > a.length())
			return -1;
		else if(b.length() == a.length())
			return 0;
		else
			return 1;
	}
	public int compare(E a, E b) throws ClassCastException {
		return ((Comparable<E>)a).compareTo(b);
	}
}
