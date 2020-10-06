
public class Bouncer extends Component{
	private double value;
	private Integrator_ED integrator;
	private double reduction;
	private boolean flag;
	
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

	public boolean isFlag() {
		return flag;
	}

	public void isFlag(boolean flag) {
		this.flag = flag;
	}

	public double getReduction() {
		return reduction;
	}

	public void setReduction(double reduction) {
		this.reduction = reduction;
	}

	public Integrator_ED getIntegrator() {
		return integrator;
	}

	public void setIntegrator(Integrator_ED integrator) {
		this.integrator = integrator;
	}

	@Override
	public boolean external(boolean show) {
		// TODO Auto-generated method stub
		if(isFlag()) {
			if((double)getInputs().get(0).getValue() <= 0.0) {
				isFlag(false);
				integrator.setValue(-(double)integrator.getValue() * getReduction());
			}
		}else {
			if((double)getInputs().get(0).getValue() > 0.0) {
				isFlag(true);
			}
		}
		return false;
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
		}
		return 0;
	}
	
	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
