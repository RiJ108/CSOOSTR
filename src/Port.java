

public abstract class Port {
	private String name;
	protected Object value;
	
	abstract public void init();
	abstract public void setValue(Object value, boolean show);
	abstract public int getForState();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
}
