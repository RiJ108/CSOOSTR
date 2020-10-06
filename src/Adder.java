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
		setName("Adder");
		values = new ArrayList<Double>();
	}
	
	public Adder(String name) {
		init();
		setName(name);
		values = new ArrayList<Double>();
	}
	
	public ArrayList<Double> getValues(){
		return values;
	}
	
	public void hold() {
		int index = 0;
		for(Port in : getInputs()) {
			if(in.getValue() != null)
				getValues().set(index, (double)in.getValue());
			index++;
		}
	}
	
	public void sumupInputs() {
		sum = 0.0;
		for(Double value : values)
			sum += value;
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
			hold();
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
			getOutputs().get(0).setValue(getValue(), show);
			break;
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
