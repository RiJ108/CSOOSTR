import java.util.ArrayList;

public class Adder extends Component{
	
	private double sum;
	private ArrayList<Double> values;
	
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public Adder() {
		init();
		values = new ArrayList<Double>();
	}
	
	public Adder(String name) {
		init();
		setName(name);
		values = new ArrayList<Double>();
	}
	
	@Override
	public void addInput(Port input) {
		super.addInput(input);
		values.add(0.0);
	}

	@Override
	public boolean external(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			isSwitched(true);
			setNext_state(1);
		}
		return swapState(show);
	}

	@Override
	public boolean internal(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 1:
			isSwitched(true);
			setNext_state(0);
		}
		return swapState(show);
	}

	@Override
	public void lambda(boolean show) {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 1:
			sumupInputs();
			break;
		}
	}
	
	public void sumupInputs() {
		System.out.println("SUMUP function****");
		double sum = 0.0;
		for(Port in : getInputs()) {
			sum += (double)in.getValue();
		}
		this.sum = sum;
		
		for(Double value : values) {
			System.out.println(value);
		}
	}

	@Override
	public double ta() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			return Double.POSITIVE_INFINITY;
		case 1:
			return 0.0;
		}
		return 0;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return sum;
	}

}
