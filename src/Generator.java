

public class Generator extends Component{
	
	public Generator() {
		init();
		setName("Generator");
	}
	public Generator(String name) {
		init();
		setName(name);
	}
	
	@Override
	public boolean external() {
		// TODO Auto-generated method stub
		return swapState();
	}

	@Override
	public boolean internal() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			isSwitched(true);
			setNext_state(0);
		}
		return swapState();
	}

	@Override
	public void lambda() {
		// TODO Auto-generated method stub
		switch(getCurrent_state()) {
		case 0:
			getOutputs().get(0).setValue(true);
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
	public Object getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
