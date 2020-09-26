

public class Generator extends Component{
	
	public Generator() {
		init();
	}
	public Generator(String name) {
		init();
		setName(name);
	}
	
	@Override
	public boolean external() {
		// TODO Auto-generated method stub
		boolean tmp;
		tmp = isSwitched();
		swapState();
		return tmp;
	}

	@Override
	public void internal() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			isSwitched(true);
			setNext_state(0);
		}
		swapState();
	}

	@Override
	public void lambda() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			getOutputs().get(0).setValue(true, true);
		}
	}

	@Override
	public double ta() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			return 2.0;
		}
		return 0;
	}

	@Override
	public int getQ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
