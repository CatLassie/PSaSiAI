package main;

import java.io.FileNotFoundException;
import java.lang.management.ManagementFactory;

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
						
			IConstruction construction = constrType.equals("random") ? new Randomized()
					: (constrType.equals("greedy") ? new Greedy() : new Worst());

			NeighbourhoodStructureEnum neighbourhoodEnumType = neighbourhoodType.equals("strict")
					? NeighbourhoodStructureEnum.STRICT : NeighbourhoodStructureEnum.RELAXED;
			
			double constructionStartCPU = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			Solution initialSolution = construction.generateSolution(k);
			double constructionEndCPU = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			double constructionDiffCPU = (constructionStartCPU - constructionEndCPU)/1000000000;
			System.out.println("Construction Heuristic CPU time: "+constructionDiffCPU+"\n");
			
			SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(initialSolution, neighbourhoodEnumType);
			// System.out.println("Neighbourhood type is set to: " + simulatedAnnealing.getNeighbourhoodType() + "\n");
			double annealingStartCPU = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			Solution bestSolution = simulatedAnnealing.search();
			double annealingEndCPU = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			double annealingDiffCPU = (annealingStartCPU - annealingEndCPU)/1000000000;
			System.out.println("Simulated Annealing CPU time: "+annealingDiffCPU+"\n");
			
			System.out.println(bestSolution);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
