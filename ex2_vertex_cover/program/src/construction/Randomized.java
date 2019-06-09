package construction;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import models.Solution;
import parser.KPMPInstance;
import util.InputParser;

public class Randomized implements IConstruction {
	
	public Solution generateSolution(InputParser p) {
		int vertexNumber = p.getNumVertices();
		//boolean[][] matrix = kpmpInstance.getAdjacencyMatrix();
		List<List<Integer>> adjacencyList = p.getAdjacencyList();
		
		Solution solution = new Solution(vertexNumber, adjacencyList);
		
		while(!solution.isValid()) {
			int randomVertex = ThreadLocalRandom.current().nextInt(0, vertexNumber);
			if(solution.getVertexCover().get(randomVertex) == false) {
				solution.pickVertex(randomVertex);
			}
		}
		return solution;
	}
	
	public String toString(){
		return "RANDOMIZED";
	}

}
