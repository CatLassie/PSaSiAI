package neighbourhood;

import java.util.concurrent.ThreadLocalRandom;

import models.Solution;
import util.NeighbourhoodStructureEnum;

public class NeighbourhoodStrict implements INeighbourhood {
	private NeighbourhoodStructureEnum neighbourhoodType = NeighbourhoodStructureEnum.STRICT;

	// STRICT STEP FUNCTION (solution invalidating vertex picks are not considered)
	public Solution move(Solution currentSolution) {

		Solution nextSolution = currentSolution.copy();
		int randomVertex;

		do {
			randomVertex = ThreadLocalRandom.current().nextInt(0, nextSolution.getVertexN());
		} while(!nextSolution.isMoveValid(randomVertex));

		nextSolution.pickVertex(randomVertex);
		return nextSolution;
	}
	
	public NeighbourhoodStructureEnum getNeighbourhoodType() {
		return neighbourhoodType;
	}
}
