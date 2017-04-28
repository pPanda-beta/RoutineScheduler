package routine;

import java.util.Collection;
import java.util.function.BiConsumer;

public class Helper {
	
	static <T> void forEachPair(Collection<T> collection, BiConsumer<T, T> biConsumer) {
		for (T o1 : collection) {
			for (T o2 : collection) {
				if (o1 != o2) {
					biConsumer.accept(o1, o2);
				}
			}
		}
	}
}
