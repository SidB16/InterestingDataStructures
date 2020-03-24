import java.util.ArrayList;
import java.util.Arrays;

public class VarArgsDemo {

	public static int f(int... a) {
		int l = a.length;
		/**
		 * do something with a[0], a[1], a[2], ..., a[l -1] Java treats the type of a
		 * (i.e., int...) basically the same way as it does an array so a can be used
		 * the same way as if it was defined as int[] a.
		 */
		return 0;
	}

	public static int g(int a, int b) {
		// do something with a and b
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[10000];
		for (int i = 0; i != a.length; ++i) {
			a[i] = i;
		}

		new ArrayList<Integer>();
		ArrayList<Integer>[] A = (ArrayList<Integer>[]) new ArrayList[10];
		
		Arrays.asList(a); // Arrays.asList can take variable arguments
		// For more info see declaration by doing ctrl+click from eclipse.

		// g must be called with exactly 2 ints.
		g(2, 3);

		// f can be called with any number of ints.
		f();
		f(2);
		f(2, 3);
		f(2, 3, 5);
		f(a);
	}

}
