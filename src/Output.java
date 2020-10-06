import java.util.ArrayList;

public class Output extends Port{
	private int forState;
	private ArrayList<Input> links;
	
	public Output() {
		init();
		setName("out");
	}
	public Output(String name) {
		init();
		setName(name);
	}
	public Output(String name, int forState) {
		init();
		setName(name);
		setForState(forState);
	}
	public Output(int forState) {
		init();
		setForState(forState);
	}
	public void init() {
		forState = -1;
		this.value = null;
		links = new ArrayList<Input>();
	}
	public ArrayList<Input> getLinks(){
		return links;
	}
	public void addLink(Input link) {
		links.add(link);
	}
	public void setValue(Object value, boolean show) {
		this.value = value;
		if(show) {
			System.out.printf("%s to ", getName());
			System.out.println(value);
		}
		for(Input link : links) {
			link.setValue(value, show);
		}
		if(show)
			System.out.println("");
	}
	public int getForState() {
		return forState;
	}
	public void setForState(int forState) {
		this.forState = forState;
	}
	
}
