package neighbourhood;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import models.Solution;
import util.NeighbourhoodStructureEnum;

public class NeighbourhoodRelaxed implements INeighbourhood {
	private NeighbourhoodStructureEnum neighbourhoodType = NeighbourhoodStructureEnum.RELAXED;

	// RELAXED STEP FUNCTION (solution invalidating vertex picks have all their adjacent vertices picked)
	public Solution move(Solution currentSolution) {
		
		Solution nextSolution = currentSolution.copy();
		int randomVertex = ThreadLocalRandom.current().nextInt(0, nextSolution.getVertexN());
		// System.out.println(randomVertex);
		// System.out.println(solution.getAdjacencyList().get(randomVertex));
		// System.out.println(solution.isMoveValid(randomVertex));
		
		if(!nextSolution.isMoveValid(randomVertex)) {
			List<Integer> adjacentToRandomVertex = nextSolution.getAdjacencyList().get(randomVertex);
			for(int i = 0; i < adjacentToRandomVertex.size(); i++) {
				if(!nextSolution.getVertexCover().get(adjacentToRandomVertex.get(i))) {
					nextSolution.pickVertex(adjacentToRandomVertex.get(i));
				}
			}
		}

		nextSolution.pickVertex(randomVertex);
		return nextSolution;
	}
	
	public NeighbourhoodStructureEnum getNeighbourhoodType() {
		return neighbourhoodType;
	}
}
