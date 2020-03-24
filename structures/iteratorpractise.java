import java.util.ArrayList;
import java.util.Iterator;

public class iteratorpractise {

	public static void main(String[] args) {
	
		ArrayList<Integer> value = new ArrayList<Integer>();
		value.add(1);
		value.add(2);
		value.add(3);

		Iterator<Integer> i = value.iterator();
		System.out.println(i.next());
		/*while (i.hasNext()) {
			System.out.println(i.next());
		}*/
		for ( Iterator<Integer> i1 = value.iterator(); i.hasNext() ; ) {
			System.out.println(i.next());
		}
	}

}
