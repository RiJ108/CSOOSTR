
public class Constante extends Component{
	private double value;
	
	public Constante() {
		init();
		setName("Constante");
		setValue(0.0);
	}
	
	public Constante(String name) {
		init();
		setName(name);
		setValue(0.0);
	}
	
	public Constante(String name, double value) {
		init();
		setName(name);
		setValue(value);
	}
	
	public Constante(double value) {
		init();
		setName("Constante");
		setValue(value);
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
