
public class Stepper extends Component{

	private double Xi;
	private double Xf;
	private double ts;
	
	public Stepper() {
		init();
		Xi = 0.0;
		Xf = 0.0;
		ts = 0.0;
	}
	
	public Stepper(double Xi, double Xf, double ts) {
		init();
		setXi(Xi);
		setXf(Xf);
		setTs(ts);
	}
	
	public Stepper(String name, double Xi, double Xf, double ts) {
		init();
		setName(name);
		setXi(Xi);
		setXf(Xf);
		setTs(ts);
	}
	
	public Stepper(String name) {
		init();
		setName(name);
	}
	
	public double getXi() {
		return Xi;
	}

	public void setXi(double xi) {
		Xi = xi;
	}

	public double getXf() {
		return Xf;
	}

	public void setXf(double xf) {
		Xf = xf;
	}

	public double getTs() {
		return ts;
	}

	public void setTs(double ts) {
		this.ts = ts;
	}

	@Override
	public boolean external(boolean show) {
		// TODO Auto-generated method stub
		
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
			getOutputs().get(0).setValue(Xi, show);
			break;
		case 1:
			getOutputs().get(0).setValue(Xf, show);
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
			return ts;
		case 2:
			return Double.POSITIVE_INFINITY;
		}
		return 0;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
