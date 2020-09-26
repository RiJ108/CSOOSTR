
public class Processor extends Component{
	
	public Processor() {
		init();
	}
	public Processor(String name) {
		init();
		setName(name);
	}

	@Override
	public boolean external() {
		// TODO Auto-generated method stub
		boolean tmp;
		int current_state = getCurrent_state();
		if(current_state == 0 && getInputs().get(0).getValue() != null) {
			isSwitched(true);
			setNext_state(1);
		}
		tmp = isSwitched();
		swapState();
		return tmp;
	}

	@Override
	public void internal() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 1:
			isSwitched(true);
			setNext_state(0);
		}
		swapState();
	}

	@Override
	public void lambda() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 1:
			getOutputs().get(0).setValue(true, true);
		}
	}

	@Override
	public double ta() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			return Double.POSITIVE_INFINITY;
		case 1:
			return 3.0;
		}
		return 0;
	}

	@Override
	public int getQ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
