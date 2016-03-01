package homework3;

public class HashMap {

	private Map[] hashMap;

	public HashMap() {
		this.hashMap = new Map[5000];
	}

	public Object get(Integer key) {
		Map map = new Map(key, null);
		int mapHash = map.hashCode();
		for (int i = 0; i <= map.getCollision(); i++) {
			Map map2 = new Map(mapHash, null);
			mapHash = map2.hashCode();
		}
		Object value = hashMap[mapHash].getValue();
		return value;
	}

	public void put(Integer key, Object value) {
		Map map = new Map(key, value);
		int mapHash = map.hashCode();
		while (hashMap[mapHash] != null) {
			Map map2 = new Map(mapHash, value);
			mapHash = map2.hashCode();
			map.collision();
		}
		hashMap[mapHash] = map;
	}

	public void remove(Object key) {
	}
}
