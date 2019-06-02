package main;

import java.io.FileNotFoundException;

import SimulatedAnnealing.SimulatedAnnealing;
import construction.IConstruction;
import construction.Worst;
import construction.Randomized;
import construction.Greedy;
import models.Solution;
import parser.KPMPInstance;
import util.NeighbourhoodStructureEnum;

public class Main {

	public static void main(String[] args) {	
		String instanceN = args[0];
		String constrType = args[1];
		String neighbourhoodType = args[2] ;
		
		System.out.println("\nMain.main()");
		System.out.println("command line args:");
		System.out.println("instance number: "+instanceN+"");
		System.out.println("contruction heuritic type: "+constrType+"");
		System.out.println("neighbourhood type: "+neighbourhoodType+"\n");
		
		// path for the bin
		// String readPath = "../../heuropttechinstances/instance-"+instanceN+".txt";
		String readPath = "file:/../../heuropttechinstances/instance-"+instanceN+".txt";
		
		try {
			KPMPInstance k = KPMPInstance.readInstance(readPath);
						
			IConstruction constr = constrType.equals("random") ? new Randomized()
					: (constrType.equals("greedy") ? new Greedy() : new Worst());

			NeighbourhoodStructureEnum neighbourhoodEnumType = neighbourhoodType.equals("strict")
					? NeighbourhoodStructureEnum.STRICT : NeighbourhoodStructureEnum.RELAXED;
			
			Solution initialSolution = constr.generateSolution(k);
			
			SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(initialSolution, neighbourhoodEnumType);
			System.out.println("Neighbourhood type is set to: " + simulatedAnnealing.getNeighbourhoodType() + "\n");
			Solution bestSolution = simulatedAnnealing.search();
			
			System.out.println(bestSolution);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
