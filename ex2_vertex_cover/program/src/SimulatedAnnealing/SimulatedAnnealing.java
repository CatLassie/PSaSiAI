package SimulatedAnnealing;

import java.util.concurrent.ThreadLocalRandom;

import models.Solution;
import neighbourhood.INeighbourhood;
import neighbourhood.NeighbourhoodRelaxed;
import neighbourhood.NeighbourhoodStrict;
import util.NeighbourhoodStructureEnum;
import util.Utilities;

public class SimulatedAnnealing {

	private Solution currentSolution;
	private INeighbourhood neighbourhood;
	private double temperature; // // f-max - f-min (which is the vertex number)
	private double coolingRate; // temperature cooling rate (e.g. 0.95)
	private int equilibriumCondition; // number of moves before temperature adjustment (multiple of vertex number, e.g. n(n-1))
	private double stoppingCondition; // minimum temperature (e.g. 0.25)
	
	public SimulatedAnnealing (Solution solution, NeighbourhoodStructureEnum neighbourhoodType,
							   double coolingRate, int equilibriumCoefficient, double stoppingCondition) {
		this.currentSolution = solution;
		this.temperature = solution.getVertexN();
		this.coolingRate = coolingRate;
		this.equilibriumCondition = equilibriumCoefficient * solution.getVertexN();
		this.stoppingCondition = stoppingCondition;

		if (neighbourhoodType == NeighbourhoodStructureEnum.STRICT) {
			this.neighbourhood = new NeighbourhoodStrict();
		}
		if (neighbourhoodType == NeighbourhoodStructureEnum.RELAXED) {
			this.neighbourhood = new NeighbourhoodRelaxed();
		}
	}
	
	public Solution search() {
		Utilities.startTimer();
		while((temperature > stoppingCondition) && !Utilities.isTimeOver()) {
			oneTemperatureLevelSearch();

			// System.out.println(temperature);
			temperature = temperature * coolingRate; // TODO: cool it down once or for every iteration in equilibrium cycle?	
		}
		return currentSolution;
	}
	
	private void oneTemperatureLevelSearch() {
		for(int i = 0; i < equilibriumCondition; i++) { 
			Solution nextSolution = neighbourhood.move(currentSolution);
			if(nextSolution.getCost() < currentSolution.getCost()) {
				currentSolution = nextSolution;
			} else {
				double e = java.lang.Math.E;
				double costDifference = Math.abs(nextSolution.getCost() - currentSolution.getCost());
				double metrolopisCriterion = Math.pow(e, -(costDifference / temperature));
				double probability = (double)ThreadLocalRandom.current().nextInt(0, 1000)/1000;
				
				// System.out.println(metrolopisCriterion);
				if(probability < metrolopisCriterion) {
					currentSolution = nextSolution;
				}
			}
		}
		
	}

	public NeighbourhoodStructureEnum getNeighbourhoodType() {
		return neighbourhood.getNeighbourhoodType();
	}
	
	public String toString() {
		String annealing = "neighbourhood type: " + neighbourhood.getNeighbourhoodType() + "\n";
		annealing += "initial temperature: " + temperature + "\n";
		annealing += "cooling rate: " + coolingRate + "\n";
		annealing += "equilibrium condition: " + equilibriumCondition + " iterations\n";
		annealing += "stopping condition: " + stoppingCondition + " minimum temperature";
		return annealing;
	}
}
