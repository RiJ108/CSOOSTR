

public class Input extends Port{
	Input(){
		init();
		setName("in");
	}
	
	Input(String name){
		init();
		setName(name);
	}
	
	Input(String name, Output source){
		init();
		setName(name);
		source.addLink(this);
	}
	
	Input(Output source){
		init();
		source.addLink(this);
	}
	
	public void init() {
		this.value = null;
	}
	
	public void setValue(Object value, boolean show) {
		this.value = value;
		if(show) {
			System.out.printf("  %s to ", getName());
			System.out.print(value);
		}
	}

	@Override
	public int getForState() {
		// TODO Auto-generated method stub
		return 0;
	}
}
