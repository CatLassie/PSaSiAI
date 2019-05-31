package construction;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import models.Solution;
import parser.KPMPInstance;

public class Randomized implements IConstruction {
	
	public Solution generateSolution(KPMPInstance kpmpInstance) {
		int vertexNumber = kpmpInstance.getNumVertices();
		//boolean[][] matrix = kpmpInstance.getAdjacencyMatrix();
		List<List<Integer>> adjacencyList = kpmpInstance.getAdjacencyList();
		
		Solution solution = new Solution(vertexNumber, adjacencyList);
		int edgeNumber = 0;
		/*
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[i].length; j++) {

				boolean currentEdge = matrix[i][j];
				if (currentEdge) {
					edgeNumber++;									
					int randomPage = ThreadLocalRandom.current().nextInt(0, pageNumber);
					// System.out.println("page #" + randomPage);
					int crossingIncrease = solution.calculateCrossingIncrease(i,j,randomPage);
					// System.out.println(i+" "+j+" p: "+randomPage+" increase: "+crossingIncrease);
 
					solution.addEdge(i, j, randomPage);
					solution.addNewCrossings(crossingIncrease, randomPage);
					// System.out.println(minCrossingIncrease);
				}
				
			}
		}
		*/
		solution.setEdgeN(edgeNumber);
		return solution;
	}
	
	public String toString(){
		return "Randomized construction heuristic";
	}

}
