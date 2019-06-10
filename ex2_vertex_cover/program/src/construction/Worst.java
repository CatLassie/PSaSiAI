package construction;

import java.util.List;

import models.Solution;
import util.InputParser;

public class Worst implements IConstruction {
	
	public Solution generateSolution(InputParser p) {
		int vertexNumber = p.getNumVertices();
		List<List<Integer>> adjacencyList = p.getAdjacencyList();
		
		Solution solution = new Solution(vertexNumber, adjacencyList);
				
		for(int i = 0; i < vertexNumber; i++) {
			solution.pickVertex(i);
		}
		return solution;
	}
	
	public String toString(){
		return "WORST (take all vertices)";
	}

}
