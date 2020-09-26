import java.util.ArrayList;

public class Scheduler {
	private static double t;
	private static double tf;
	private static double tr_min;
	private static ArrayList<Component> components = new ArrayList<Component>();
	private static ArrayList<Component> imms;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		run();
	}
	
	public static void init() {
		Output job_o0 = new Output("Gen_job(out)", 0);
		Input job_i0 = new Input("Buf_job(in)", job_o0);
		
		Output req_o0 = new Output("Buf_req(out)", 1);
		Input req_i0 = new Input("Proc_req(in)", req_o0);
		
		Output done_o0 = new Output("Proc_done(out)", 1);
		Input done_i0 = new Input("Buf_done(in)", done_o0);
		
		Generator generator = new Generator("Generator");
		generator.addOutput(job_o0);
		
		Buffer buffer = new Buffer("Buffer");
		buffer.addInput(job_i0);
		buffer.addInput(done_i0);
		buffer.addOutput(req_o0);
		
		Processor processor = new Processor("Processor");
		processor.addInput(req_i0);
		processor.addOutput(done_o0);
		
		components.add(generator);
		components.add(buffer);
		components.add(processor);
		
		tf = 6.0;
	}
	
	public static void run() {
		while(t <= tf) {
			System.out.printf("____________________________________________________________________________________________________"
					+ "\n____________________________________________________________________________________________________"
					+ "\nt=%.1f\n", t);
			getTr_min();
			updateImms();
			stepTime();
			showBufferQ();
			updateOuts();
			showOuts();
			updateE();
			updateTr();
			lambdaCalls();
			updateIns();
			refresh();
			clearInputs();
		}
	}
	
	public static void stepTime() {
		t += tr_min;
	}
	
	public static void getTr_min() {
		tr_min = Double.POSITIVE_INFINITY;
		System.out.println("___tr_min loop___");
		for(Component comp : components) {
			System.out.printf("%s(%d)\ttr=%.1f",comp.getName(), comp.getCurrent_state(), comp.getTr());
			System.out.printf("  \te=%.1f   \ttl=%.1f  \ttn=%.1f", comp.getE(), comp.getTl(), comp.getTn());
			System.out.println("");
			if(comp.getTr() < tr_min) {
				tr_min = comp.getTr();
			}
		}
		System.out.printf("\ntr_min=%.1f\n", tr_min);
	}
	
	public static void updateImms(){
		imms = null;
		for(Component comp : components) {
			if(comp.getTr() == tr_min) {
				if(imms == null) {
					imms = new ArrayList<Component>();
				}
				imms.add(comp);
			}
		}
	}
	
	public static void updateE() {
		for(Component comp : components) {
			comp.setE(comp.getE() + tr_min);
		}
	}
	
	public static void updateTr() {
		for(Component comp : components) {
			comp.setTr(comp.getTr() - tr_min);
		}
	}
	
	public static void lambdaCalls() {
		System.out.println("\n___Lambda calls loop(t+tr_min)___");
		for(Component comp : imms) {
			comp.lambda();
		}
	}
	
	public static void updateIns() {
		for(Component comp : components) {
			comp.setIns();
		}
	}
	
	public static void updateOuts() {
		for(Component comp : components) {
			comp.setOuts();
		}
	}
	
	public static void refresh() {
		System.out.println("\n___Refresh loop___");
		for(Component comp : components) {
			if(imms.contains(comp) && comp.getIns() == null) {
				System.out.printf("(case 0)imms containt %s and his .ins is null.\n", comp.getName());
				comp.internal();
				comp.updateTimers(t);
			}else if(!imms.contains(comp) && comp.getIns() != null) {
				System.out.printf("(case 1)imms doesn't containt %s and his .ins is not null.\n", comp.getName());
				comp.external();
				comp.updateTimers(t);
			}else if(imms.contains(comp) && comp.getIns() != null) {
				System.out.printf("(case 2)imms containt %s and his .ins is null.\n", comp.getName());
				if(!comp.external()) {
					comp.internal();
				}
				comp.updateTimers(t);
			}
		}
	}
	
	public static void showOuts() {
		System.out.println("\n___Next event(s)___");
		for(Component comp : components) {
			if(comp.getOuts() != null) {
				for(Port out : comp.getOuts()) {
					System.out.printf("%s|%.1f\t", out.getName(), comp.getTr());
				}
				System.out.println("");
			}
		}
	}
	
	public static double getLowestTr(ArrayList<Component> array) {
		double min = Double.POSITIVE_INFINITY;
		for(Component comp : array) {
			if(comp.getTr() < min) {
				min = comp.getTr();
			}
		}
		return min;
	}
	
	public static void clearInputs() {
		for(Component comp : components) {
			comp.clearInputs();
		}
	}
	
	public static void showBufferQ() {
		System.out.printf("q=%d\n", components.get(1).getQ());
	}
}
