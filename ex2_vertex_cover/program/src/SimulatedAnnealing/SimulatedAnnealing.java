package SimulatedAnnealing;

import models.Solution;
import neighbourhood.INeighbourhood;
import neighbourhood.NeighbourhoodRelaxed;
import neighbourhood.NeighbourhoodStrict;
import util.NeighbourhoodStructureEnum;
// import util.Utilities;

public class SimulatedAnnealing {

	private Solution currentSolution;
	private INeighbourhood neighbourhood;
	// private double temperature;

	public SimulatedAnnealing (Solution solution, NeighbourhoodStructureEnum neighbourhoodType) {
		this.currentSolution = solution;

		if (neighbourhoodType == NeighbourhoodStructureEnum.STRICT) {
			this.neighbourhood = new NeighbourhoodStrict();
		}
		if (neighbourhoodType == NeighbourhoodStructureEnum.RELAXED) {
			this.neighbourhood = new NeighbourhoodRelaxed();
		}
	}
	
	// RANDOM EDGE
	public Solution search() {
		/*
		timeout:
		for (int i = 0; i < currentSolution.getEdgeNumber(); i++) {
			if(Utilities.isTimeOver()){
				System.out.println("Local Search time is up!");
				break timeout;
			}
			Solution solutionNew = neighbourhood.move(currentSolution);
			int v1 = neighbourhood.getSelectedV1();
			int v2 = neighbourhood.getSelectedV2();
			int fromPage = currentSolution.getAdjacencyMatrix()[v1][v2];
			int toPage = neighbourhood.getSelectedPage();

			int edgeRemovalCost = currentSolution.calculateCrossingIncrease(v1, v2, fromPage);
			if (edgeRemovalCost > 0) {
				int edgeAdditionCost = currentSolution.calculateCrossingIncrease(v1, v2, toPage);
				if (edgeAdditionCost < edgeRemovalCost) {
					currentSolution = solutionNew;
					int fromPageCrossings = currentSolution.getCrossingsList().get(fromPage);
					int toPageCrossings = currentSolution.getCrossingsList().get(toPage);
					currentSolution.getCrossingsList().set(fromPage, fromPageCrossings - edgeRemovalCost);
					currentSolution.getCrossingsList().set(toPage, toPageCrossings + edgeAdditionCost);
					// System.out.println("UPDATE! " + i);
				}
			}
		}
		*/
		
		for(int i = 0; i < 10; i++) {
			currentSolution = neighbourhood.move(currentSolution);
			// System.out.println("intermediate i: " + currentSolution.getVertexCover());
		}
		// System.out.println("\n");
		
		return currentSolution;
	}
	
	/*
	public Solution getBestSolution() {
		return currentSolution;
	}
	*/

	public NeighbourhoodStructureEnum getNeighbourhoodType() {
		return neighbourhood.getNeighbourhoodType();
	}
}
