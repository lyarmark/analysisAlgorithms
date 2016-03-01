package homework3;

public class Map {
	private Integer key;
	private Object value;
	private int collision;
	private double A = (Math.sqrt(5) - 1) / 2;
	private double M = Math.pow(2, 32);

	public Map(Integer key, Object value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int hashCode() {
		double s = key * A;
		double x = s - (int) s; // fractional p/o s
		double hash = Math.floor(x * M);
		double hashReduced = (hash % 47) * 31;
		return (int) hashReduced;
	}

	public int getCollision() {
		return collision;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void collision() {
		collision++;
	}
}
