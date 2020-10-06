
public class Integrator_ED extends Component{
	private double dQ;
	private double dT;
	private double q;
	private double deri;
	
	public Integrator_ED() {
		init();
		setName("Integrator_ED");
		setValue(0.0);
		setdQ(0.1);
		setDeri(0.0);
		compute_dT();
	}
	
	public Integrator_ED(String name) {
		init();
		setName(name);
		setValue(0.0);
		setdQ(0.1);
		setDeri(0.0);
		compute_dT();
	}
	
	public Integrator_ED(String name, double step) {
		init();
		setName(name);
		setValue(0.0);
		setdQ(step);
		setDeri(0.0);
		compute_dT();
	}
	
	public Integrator_ED(double step) {
		init();
		setName("Integrator_ED");
		setValue(0.0);
		setdQ(step);
		setDeri(0.0);
		compute_dT();
	}

	public double getdQ() {
		return dQ;
	}

	public void setdQ(double dQ) {
		this.dQ = dQ;
	}

	public double getdT() {
		return dT;
	}

	public void setdT(double dT) {
		this.dT = dT;
	}
	
	public void integrate(double delta) {
		setValue((double)getValue() + delta);
	}
	
	public void compute_dT() {
		if(getDeri() == 0.0)
			setdT(Double.POSITIVE_INFINITY);
		else
			setdT((double)getdQ()/Math.abs(getDeri()));
	}

	public double getDeri() {
		return deri;
	}

	public void setDeri(double deri) {
		this.deri = deri;
	}

	@Override
	public boolean external(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			setDeri((double)getInputs().get(0).getValue());
			integrate(getDeri() * getE());
			compute_dT();
			isSwitched(true);
			setNext_state(0);
			break;
		}
		return swapState(show);
	}

	@Override
	public boolean internal(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			integrate(getdQ() * Math.signum(getDeri()));
			compute_dT();
			isSwitched(true);
			setNext_state(0);
			break;
		}
		return swapState(show);
	}

	@Override
	public void lambda(boolean show) {
		// TODO Auto-generated method stub
		if(getOutputs().size() != 0)
			getOutputs().get(0).setValue(q, show);
	}

	@Override
	public double ta() {
		// TODO Auto-generated method stub
		return dT;
	}
	
	public void setValue(double value) {
		q = value;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return q;
	}

}
