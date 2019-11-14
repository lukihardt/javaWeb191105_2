package sopo.cn.utils;

import java.util.HashMap;

public class CastHash {
	public static <K, V> HashMap<K, V> castHash(Object input, Class<K> keyClass, Class<V> valueClass) {
		HashMap<K, V> output = new HashMap<K, V>();
		if (input == null)
			return output;
		@SuppressWarnings("unchecked")
		HashMap<K, V> inputHashMap = (HashMap<K, V>) input;
		for (Object key : inputHashMap.keySet().toArray()) {
			if ((key == null) || (keyClass.isAssignableFrom(key.getClass()))) {
				Object value = inputHashMap.get(key);
				if ((value == null) || (valueClass.isAssignableFrom(value.getClass()))) {
					K k = keyClass.cast(key);
					V v = valueClass.cast(value);
					output.put(k, v);
				} else {
					throw new AssertionError(
							"Cannot cast to HashMap<" + keyClass.getSimpleName() + ", " + valueClass.getSimpleName()
									+ ">" + ", value " + value + " is not a " + valueClass.getSimpleName());
				}
			} else {
				throw new AssertionError("Cannot cast to HashMap<" + keyClass.getSimpleName() + ", "
						+ valueClass.getSimpleName() + ">" + ", key " + key + " is not a " + keyClass.getSimpleName());
			}
		}
		return output;
	}
}
