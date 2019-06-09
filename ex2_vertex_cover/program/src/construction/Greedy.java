package construction;

import java.util.List;

import models.Solution;
import parser.KPMPInstance;
import util.InputParser;

public class Greedy implements IConstruction {

	public Solution generateSolution(InputParser p) {
		int vertexNumber = p.getNumVertices();
		// boolean[][] matrix = kpmpInstance.getAdjacencyMatrix();
		List<List<Integer>> adjacencyList = p.getAdjacencyList();

		Solution solution = new Solution(vertexNumber, adjacencyList);
		
		while(!solution.isValid()) {
			// best vertex and number of edges it would cover
			int promisingVertex = -1;
			int maxCoveredEdgeN = -1;
			
			for(int i = 0; i < vertexNumber; i++) {
				if(solution.getVertexCover().get(i) == false) { // check only not yet assigned vertices
					List<Integer> currentVertexList = solution.getAdjacencyList().get(i);
					int coveredEdgeN = 0;
					
					// sum up all edges covered by potential candidate vertex
					for(int j = 0; j < currentVertexList.size(); j++) {
						if(solution.getVertexCover().get(j) == false) { // only none-assigned edges count
							coveredEdgeN++;
						}
					}
					
					// if its covered edge number is highest (so far) - make it the promising vertex
					if(coveredEdgeN > maxCoveredEdgeN) {
						promisingVertex = i;
						maxCoveredEdgeN = coveredEdgeN;
						// System.out.println(coveredEdgeN);
					}
				}
			}
			
			solution.pickVertex(promisingVertex);
		}
		return solution;
	}
	
	public String toString(){
		return "GREEDY";
	}

}
