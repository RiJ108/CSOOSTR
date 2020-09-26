import java.util.ArrayList;

public abstract class Component {
	private ArrayList<Port> inputs;
	private ArrayList<Port> outputs;
	private ArrayList<Port> ins;
	private ArrayList<Port> outs;
	private double tr;
	private double e;
	private double tl;
	private double tn;
	private int current_state;
	private int next_state;
	private String name;
	private boolean switched;
	
	public double getTr() {
		return tr;
	}
	public void setTr(double tr) {
		this.tr = tr;
	}
	public double getE() {
		return e;
	}
	public void setE(double e) {
		this.e = e;
	}
	public double getTl() {
		return tl;
	}
	public void setTl(double tl) {
		this.tl = tl;
	}
	public double getTn() {
		return tn;
	}
	public void setTn(double tn) {
		this.tn = tn;
	}
	public int getCurrent_state() {
		return current_state;
	}
	public void setCurrent_state(int current_state) {
		this.current_state = current_state;
	}
	public int getNext_state() {
		return next_state;
	}
	public void setNext_state(int next_state) {
		this.next_state = next_state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Port> getInputs(){
		return inputs;
	}
	public void addInput(Port input) {
		inputs.add(input);
	}
	public ArrayList<Port> getOutputs(){
		return outputs;
	}
	public void addOutput(Port output) {
		outputs.add(output);
	}
	public boolean isSwitched() {
		return switched;
	}
	public void isSwitched(boolean switched) {
		this.switched = switched;
	}
	abstract public boolean external();
	abstract public void internal();
	abstract public void lambda();
	abstract public double ta();
	abstract public int getQ();
	
	public void init() {
		inputs = new ArrayList<Port>();
		outputs = new ArrayList<Port>();
		current_state = 0;
		resetTimers(0.0);
		isSwitched(false);
	}
	
	public void swapState() {
		if(isSwitched()) {
			System.out.printf("  %s switch from %d to %d\n", getName(), current_state, next_state);
			current_state = next_state;
		}
	}
	
	public ArrayList<Port> getIns(){
		return ins;
	}
	
	public void setIns(){
		ins = null;
		for(Port in : inputs) {
			if(in.getValue() != null) {
				if(ins == null) {
					ins = new ArrayList<Port>();
				}
				ins.add(in);
			}
		}
	}
	
	public ArrayList<Port> getOuts(){
		return outs;
	}
	
	public void setOuts(){
		outs = null;
		for(Port out : outputs) {
			if(out.getForState() == current_state) {
				if(outs == null) {
					outs = new ArrayList<Port>();
				}
				outs.add(out);
			}
		}
	}
	
	public void resetTimers(double t) {
		setTr(ta());
		setTl(t);
		setTn(ta() + t);
		setE(0);
	}
	
	public void clearInputs() {
		for(Port in : inputs) {
			in.setValue(null, false);
		}
	}
	
	public void updateTimers(double t) {
		if(isSwitched()) {
			setE(0.0);
			setTr(ta());
			setTl(t);
			setTn(t+ta());
			isSwitched(false);
		}
	}
}
