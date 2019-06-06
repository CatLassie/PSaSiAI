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
		double coolingRate = args.length > 3 ? Double.parseDouble(args[3]) : 0.95;
		int equilibriumCoefficient = 0;
		if(args.length > 4) {
			equilibriumCoefficient = Integer.parseInt(args[4]);
		}
		int stoppingCondition = args.length > 5 ? Integer.parseInt(args[5]) : 5;
				
		// path for the bin
		// String readPath = "../../heuropttechinstances/instance-"+instanceN+".txt";
		String readPath = "file:/../../heuropttechinstances/instance-"+instanceN+".txt";
		
		try {
			KPMPInstance k = KPMPInstance.readInstance(readPath);
			
			if(args.length <= 4) {
				equilibriumCoefficient = k.getNumVertices()-1;
			}
			
			System.out.println("\nMain.main()");
			System.out.println("command line args:");
			System.out.println("instance number: "+instanceN+"");
			System.out.println("contruction heuritic type: "+constrType+"");
			System.out.println("neighbourhood type: "+neighbourhoodType+"");
			System.out.println("cooling rate: "+coolingRate+"");
			System.out.println("equilibrium coefficient: "+equilibriumCoefficient+"");
			System.out.println("stopping condition: "+stoppingCondition+"\n");
						
			
			
			IConstruction construction = constrType.equals("random") ? new Randomized()
					: (constrType.equals("greedy") ? new Greedy() : new Worst());
			
			System.out.println("CONSTRUCTION HEURISTIC:");
			System.out.println("type: "+construction+"");
			double constructionStartCPU = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			Solution initialSolution = construction.generateSolution(k);
			double constructionEndCPU = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			double constructionDiffCPU = (constructionEndCPU - constructionStartCPU)/1000000000;
			System.out.println("CPU time: "+constructionDiffCPU+"\n");
			
			System.out.println("INITIAL CONSTRUCTED SOULTION:");
			System.out.println(initialSolution.result()+"");
			
			
			
			NeighbourhoodStructureEnum neighbourhoodEnumType = neighbourhoodType.equals("strict")
					? NeighbourhoodStructureEnum.STRICT : NeighbourhoodStructureEnum.RELAXED;
			
			SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(initialSolution, neighbourhoodEnumType,
																		   coolingRate, equilibriumCoefficient, stoppingCondition);
			
			System.out.println("SIMULATED ANNEALING:");
			System.out.println(simulatedAnnealing+"");
			double annealingStartCPU = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			Solution bestSolution = simulatedAnnealing.search();
			double annealingEndCPU = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			double annealingDiffCPU = (annealingEndCPU - annealingStartCPU)/1000000000;
			System.out.println("CPU time: "+annealingDiffCPU+"\n");
			
			
			
			System.out.println("SOLUTION:");
			// System.out.println(bestSolution.graphData()+"");
			System.out.println(bestSolution.result()+"");
			// System.out.println(bestSolution);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
