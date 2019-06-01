package construction;

import java.util.List;

import models.Solution;
import parser.KPMPInstance;

public class Worst implements IConstruction {
	
	public Solution generateSolution(KPMPInstance kpmpInstance) {
		int vertexNumber = kpmpInstance.getNumVertices();
		//boolean[][] matrix = kpmpInstance.getAdjacencyMatrix();
		List<List<Integer>> adjacencyList = kpmpInstance.getAdjacencyList();
		
		Solution solution = new Solution(vertexNumber, adjacencyList);
				
		for(int i = 0; i < vertexNumber; i++) {
			solution.pickVertex(i);
		}
		return solution;
	}
	
	public String toString(){
		return "Worst construction heuristic (take all vertices)";
	}

}
