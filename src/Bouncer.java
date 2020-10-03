
public class Bouncer extends Component{
	private double value; //Height
	private double reduction;
	private Integrator integrator;
	
	public Bouncer() {
		init();
		setName("Bouncer");
		setValue(0.0);
	}
	
	public Bouncer(String name) {
		init();
		setName(name);
		setValue(0.0);
	}
	
	public Bouncer(String name, double value) {
		init();
		setName(name);
		setValue(value);
	}
	
	public Bouncer(double value) {
		init();
		setName("Bouncer");
		setValue(value);
	}
	
	public double getReduction() {
		return reduction;
	}

	public void setReduction(double reduction) {
		this.reduction = reduction;
	}

	public void setIntegrator(Integrator integrator) {
		this.integrator = integrator;
	}

	@Override
	public boolean external(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 2:
			isSwitched(true);
			setNext_state(3);
			break;
		}
		return swapState(show);
	}

	@Override
	public boolean internal(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			isSwitched(true);
			setNext_state(1);
			break;
		case 1:
			isSwitched(true);
			setNext_state(2);
			break;
		case 3:
			isSwitched(true);
			setNext_state(2);
			break;
		}
		return swapState(show);
	}

	@Override
	public void lambda(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			getOutputs().get(0).setValue(0.0, show);
			break;
		case 1:
			getOutputs().get(0).setValue(value, show);
			break;
		}
	}

	@Override
	public double ta() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			return 0.0;
		case 1:
			return 0.0;
		case 2:
			return Double.POSITIVE_INFINITY;
		case 3:
			return 0.0;
		}
		return 0;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}

}
