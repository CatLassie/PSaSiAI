package neighbourhood;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import models.Solution;
import util.NeighbourhoodStructureEnum;

public class NeighbourhoodRelaxed implements INeighbourhood {

	// RELAXED STEP FUNCTION (solution invalidating vertex picks have all their adjacent vertices picked)
	public Solution move(Solution solution) {
		
		int randomVertex = ThreadLocalRandom.current().nextInt(0, solution.getVertexN());
		// System.out.println(randomVertex);
		// System.out.println(solution.getAdjacencyList().get(randomVertex));
		// System.out.println(solution.isMoveValid(randomVertex));
		
		if(!solution.isMoveValid(randomVertex)) {
			List<Integer> adjacentToRandomVertex = solution.getAdjacencyList().get(randomVertex);
			for(int i = 0; i < adjacentToRandomVertex.size(); i++) {
				if(!solution.getVertexCover().get(adjacentToRandomVertex.get(i))) {
					solution.pickVertex(adjacentToRandomVertex.get(i));
				}
			}
		}

		solution.pickVertex(randomVertex);
		return solution;
	}
}
