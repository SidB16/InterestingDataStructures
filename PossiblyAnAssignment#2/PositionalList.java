package A3;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author sid16
 *
 * @param <E>
 */
public interface PositionalList<E> extends Iterable<E> {
	 int size();
	 boolean isEmpty();
	 Position<E> first();
	 Position<E> last();
	 Position<E> before(Position<E> p);
	 Position<E> after(Position<E> p);
	 Position<E> addFirst(E e);
	 Position<E> addLast(E e);
	 Position<E> addBefore(Position<E> p, E e)
	 throws IllegalArgumentException;
	 Position<E> addAfter(Position<E> p, E e)
	 throws IllegalArgumentException;
	 E set(Position<E> p, E e) throws IllegalArgumentException;
	 E remove(Position<E> p) throws IllegalArgumentException;
	 Iterator<E> iterator();
	 Iterable<Position<E>> positions();
}
