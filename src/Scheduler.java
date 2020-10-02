import java.util.ArrayList;

import chart.Chart;
import chart.ChartFrame;

public class Scheduler {
	private static double t;
	private static double tf;
	private static double tr_min;
	private static ArrayList<Component> components = new ArrayList<Component>();
	private static ArrayList<Component> imms;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//exo_1();
		exo_2();
	}
	
	public static void exo_2() {
		init_exo_2();
		run_exo_2(true);
	}
	
	public static void init_exo_2() {
		Output y_o0 = new Output("Stepper_0_y(out)");
		Input y_i0 = new Input("Adder_y0(in)", y_o0);
		
		Output y_o1 = new Output("Stepper_0_y(out)");
		Input y_i1 = new Input("Adder_y1(in)", y_o1);
		
		Output y_o2 = new Output("Stepper_0_y(out)");
		Input y_i2 = new Input("Adder_y2(in)", y_o2);
		
		Output y_o3 = new Output("Stepper_0_y(out)");
		Input y_i3 = new Input("Adder_y3(in)", y_o3);
		
		Stepper stepper_0 = new Stepper("Stepper_0", 1.0, -3.0, 0.65);
		stepper_0.addOutput(y_o0);
		
		Stepper stepper_1 = new Stepper("Stepper_1", 0.0, 1.0, 0.35);
		stepper_1.addOutput(y_o1);
		
		Stepper stepper_2 = new Stepper("Stepper_2", 0.0, 1.0, 1.0);
		stepper_2.addOutput(y_o2);
		
		Stepper stepper_3 = new Stepper("Stepper_3", 0.0, 4.0, 1.5);
		stepper_3.addOutput(y_o3);
		
		Adder adder = new Adder("Adder");
		adder.addInput(y_i0);
		adder.addInput(y_i1);
		adder.addInput(y_i2);
		adder.addInput(y_i3);
		
		components.add(stepper_0);
		components.add(stepper_1);
		components.add(stepper_2);
		components.add(stepper_3);
		components.add(adder);
		
		tf= 1.5;
	}
	
	public static void run_exo_2(boolean show) {
		ChartFrame chartframe = new ChartFrame("Resultat", "Stepper Adder");
		Chart cS = new Chart("sum");
		chartframe.addToLineChartPane(cS);
		while(t <= tf) {
			System.out.printf("____________________________________________________________________________________________________"
					+ "\n____________________________________________________________________________________________________"
					+ "\nt=%.1f\n", t);
			cS.addDataToSeries(t, (double)components.get(4).getValue());
			getTr_min(show);
			updateImms();
			stepTime();
			showBufferQ();
			updateOuts();
			showOuts();
			updateE();
			updateTr();
			lambdaCalls(show);
			updateIns();
			refresh(show);
			//clearInputs();
		}
	}
	
	public static void exo_1() {
		init_exo_1();
		run_exo_1(false);
	}
	
	public static void init_exo_1() {
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
		
		tf = 20.0;
	}
	
	public static void run_exo_1(boolean show) {
		ChartFrame chartframe = new ChartFrame("Resultat", "GenBufProc");
		Chart cQ = new Chart("q");
		chartframe.addToLineChartPane(cQ);
		while(t <= tf) {
			System.out.printf("____________________________________________________________________________________________________"
					+ "\n____________________________________________________________________________________________________"
					+ "\nt=%.1f\n", t);
			cQ.addDataToSeries(t, (int)components.get(1).getValue());
			getTr_min(show);
			updateImms();
			stepTime();
			showBufferQ();
			updateOuts();
			showOuts();
			updateE();
			updateTr();
			lambdaCalls(show);
			updateIns();
			refresh(show);
			clearInputs();
		}
	}
	
	public static void stepTime() {
		t += tr_min;
	}
	
	public static void getTr_min(boolean show) {
		tr_min = Double.POSITIVE_INFINITY;
		if(show)
			System.out.println("___tr_min loop___");
		for(Component comp : components) {
			if(show) {
				System.out.printf("%s(%d)\ttr=%.1f",comp.getName(), comp.getCurrent_state(), comp.getTr());
				System.out.printf("  \te=%.1f   \ttl=%.1f  \ttn=%.1f", comp.getE(), comp.getTl(), comp.getTn());
				System.out.println("");
			}
			if(comp.getTr() < tr_min) {
				tr_min = comp.getTr();
			}
		}
		if(tr_min == Double.POSITIVE_INFINITY)
			tr_min = 0.1;
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
	
	public static void lambdaCalls(boolean show) {
		if(show)
			System.out.println("\n___Lambda calls loop(t+tr_min)___");
		if(imms != null) {
			for(Component comp : imms) {
				comp.lambda(show);
			}
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
	
	public static void refresh(boolean show) {
		if(show)
			System.out.println("\n___Refresh loop___");
		if(imms != null) {
			for(Component comp : components) {
				if(imms.contains(comp) && comp.getIns() == null) {
					if(show)
						System.out.printf("(case 0)imms containt %s and his .ins is null.\n", comp.getName());
					comp.internal(show);
					comp.updateTimers(t);
				}else if(!imms.contains(comp) && comp.getIns() != null) {
					if(show)
						System.out.printf("(case 1)imms doesn't containt %s and his .ins is not null.\n", comp.getName());
					comp.external(show);
					comp.updateTimers(t);
				}else if(imms.contains(comp) && comp.getIns() != null) {
					if(show)
						System.out.printf("(case 2)imms containt %s and his .ins is null.\n", comp.getName());
					if(!comp.external(show)) {
						comp.internal(show);
					}
					comp.updateTimers(t);
				}
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
		System.out.printf("q=%d\n", components.get(1).getValue());
	}
}
