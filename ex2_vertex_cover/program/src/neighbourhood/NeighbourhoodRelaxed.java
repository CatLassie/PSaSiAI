package neighbourhood;

import java.util.concurrent.ThreadLocalRandom;

import models.Solution;
import util.NeighbourhoodStructureEnum;

public class NeighbourhoodRelaxed implements INeighbourhood {

	// RELAXED STEP FUNCTION (solution invalidating vertex picks have all their adjacent vertices picked)
	public Solution move(Solution solution) {
		
		return solution;
	}
}
