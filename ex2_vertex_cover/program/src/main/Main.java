package main;

import java.io.FileNotFoundException;

import construction.IConstruction;
import construction.Worst;
import construction.Randomized;
import construction.Greedy;
import models.Solution;
import parser.KPMPInstance;

public class Main {

	public static void main(String[] args) {	
		String instanceN = args[0];
		String constrType = args[1];
		
		System.out.println("\nMain.main()");
		System.out.println("command line args:");
		System.out.println("instance number: "+instanceN+"");
		System.out.println("contruction heuritic type: "+constrType+"\n");
		
		// path for the bin
		// String readPath = "../../heuropttechinstances/instance-"+instanceN+".txt";
		String readPath = "file:/../../heuropttechinstances/instance-"+instanceN+".txt";
		
		try {
			KPMPInstance k = KPMPInstance.readInstance(readPath);
						
			IConstruction constr = constrType.equals("random") ? new Randomized()
					: (constrType.equals("greedy") ? new Greedy()
							: (constrType.equals("wrost") ? new Worst() : new Worst()));
			
			Solution initialSolution = constr.generateSolution(k);
			
			System.out.println(initialSolution);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
