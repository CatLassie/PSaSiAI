package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import SimulatedAnnealing.SimulatedAnnealing;
import construction.IConstruction;
import construction.Worst;
import construction.Randomized;
import construction.Greedy;
import models.Solution;
import util.InputParser;
import util.NeighbourhoodStructureEnum;
import util.SolutionWriter;

public class Main {

	public static void main(String[] args) {
		String instanceN = args[0];
		String constrType = args[1];
		String neighbourhoodType = args[2] ;
		double coolingRate = args.length > 3 ? Double.parseDouble(args[3]) : 0.95;
		double stoppingCondition = args.length > 4 ? Double.parseDouble(args[4]) : 0.25;
		int equilibriumCoefficient = 0;
				
		String localDir = System.getProperty("user.dir").split("program")[0];
		// String readPath = localDir + "heuropttechinstances\\instance-"+instanceN+".txt";
		// String writePath = localDir + "solutions\\instance-"+instanceN+".txt";
		// System.out.println(readPath);
		
		 String readPath = localDir + "\\instances\\vc-exact_"+instanceN+".gr";
		 String writePath = localDir + "\\solutions\\vc-exact_"+instanceN+".gr";
		
		
		
		try {
			InputParser p = InputParser.readInstance(readPath);
			
			int defaultCoefficient = (int) Math.sqrt(p.getNumVertices());
			equilibriumCoefficient = args.length > 5 ? Integer.parseInt(args[5]) : defaultCoefficient;
			
			System.out.println("\nMain.main()");
			System.out.println("command line args:");
			System.out.println("instance number: "+instanceN+"");
			System.out.println("contruction heuritic type: "+constrType+"");
			System.out.println("neighbourhood type: "+neighbourhoodType+"");
			System.out.println("cooling rate: "+coolingRate+"");
			System.out.println("stopping condition: "+stoppingCondition+"");
			System.out.println("equilibrium coefficient: "+equilibriumCoefficient+"\n");
						
			
			
			IConstruction construction = constrType.equals("random") ? new Randomized()
					: (constrType.equals("greedy") ? new Greedy() : new Worst());
			
			System.out.println("CONSTRUCTION HEURISTIC:");
			System.out.println("type: "+construction+"");
			double constructionStartCPU = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			Solution initialSolution = construction.generateSolution(p);
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
			
			SolutionWriter.write(writePath, bestSolution);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
