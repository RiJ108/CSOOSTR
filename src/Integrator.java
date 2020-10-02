
public class Integrator extends Component{
	
	private double step;
	private double delta;
	private double value;
	private double deri;
	
	public Integrator() {
		init();
	}
	
	public Integrator(double step) {
		init();
		setStep(step);
	}
	
	public Integrator(String name, double step) {
		init();
		setStep(step);
		setName(name);
	}
	
	public Integrator(String name) {
		init();
		setName(name);
	}
	
	public double getDeri() {
		return deri;
	}

	public void setDeri(double deri) {
		this.deri = deri;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}
	
	public void integrate() {
		System.out.println("Integrate function***");
		System.out.println(getDeri());
		setValue((double)getValue() + getDelta()*getDeri());
	}

	@Override
	public boolean external(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			setDelta(getE());
			setDeri((double)getInputs().get(0).getValue());
			isSwitched(true);
			setNext_state(1);
		}
		return swapState(show);
	}

	@Override
	public boolean internal(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			setDelta(getE());
			isSwitched(true);
			setNext_state(1);
			break;
		case 1:
			integrate();
			isSwitched(true);
			setNext_state(0);
			break;
		}
		return swapState(show);
	}

	@Override
	public void lambda(boolean show) {
		// TODO Auto-generated method stub

	}

	@Override
	public double ta() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			return step;
		case 1:
			return 0.0;
		}
		return 0;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		this.value = (double)value;
	}
	
	public void setCI(Object value) {
		setValue(value);
	}

}
