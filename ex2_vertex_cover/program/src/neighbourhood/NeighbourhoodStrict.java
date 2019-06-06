package neighbourhood;

import java.util.concurrent.ThreadLocalRandom;

import models.Solution;
import util.NeighbourhoodStructureEnum;

public class NeighbourhoodStrict implements INeighbourhood {
	private NeighbourhoodStructureEnum neighbourhoodType = NeighbourhoodStructureEnum.STRICT;

	// STRICT STEP FUNCTION (solution invalidating vertex picks are not considered)
	public Solution move(Solution currentSolution) {
		/*
		int[][] matrix = solution.getAdjacencyMatrix();
		int vertexN = matrix.length;
		int pageN = solution.getPageNumber();
		int randomV1 = -1;
		int randomV2 = -1;
		int randomPage = -1;
		do {
			randomV1 = ThreadLocalRandom.current().nextInt(0, vertexN);
			randomV2 = ThreadLocalRandom.current().nextInt(0, vertexN);
		} while (matrix[randomV1][randomV2] == -1);
		do {
			randomPage = ThreadLocalRandom.current().nextInt(0, pageN);
		} while (randomPage == matrix[randomV1][randomV2]);

		Solution solutionNew = solution.copy();
		solutionNew.getAdjacencyMatrix()[randomV1][randomV2] = randomPage;

		this.selectedV1 = randomV1;
		this.selectedV2 = randomV2;
		this.selectedPage = randomPage;

		return solutionNew;
		*/
		
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
