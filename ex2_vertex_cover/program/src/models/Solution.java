package models;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private List<Boolean> vertexCover;
	private int vertexN;
	private int edgeN = 0;
	// private int[][] adjacencyMatrix;
	private List<List<Integer>> adjacencyList;
	private int cost;
	
	public Solution(int vertexN, List<List<Integer>> adjacencyList) {
		this.vertexN = vertexN;
		this.adjacencyList = adjacencyList;
		
		vertexCover = new ArrayList<Boolean>();
		for(int i = 0; i < vertexN; i++) {
			vertexCover.add(false);
		}
		
		cost = 0;
	}
	
	public boolean isMoveValid(int vertex) {
		boolean isValid = true;
		
		// return true immediately if move includes vertex
		if(vertexCover.get(vertex) == false) {
			return isValid;
		}
		
		// check if vertex can be excluded and still result in feasible solution
		// if any adjacent vertex is excluded -> selected cannot be excluded either
		List<Integer> selectedVertexList = adjacencyList.get(vertex);
		for(int i = 0; i < selectedVertexList.size(); i++) {
			int v = selectedVertexList.get(i);
			if(vertexCover.get(v) == false) {
				isValid = false;
				break; // TODO: check if break does what you think it does
			}
		}

		return isValid;
	}
	
	// TODO:
	// check if solution is a valid vertex cover
	public boolean isValid() {
		return false;
	}
	
	public int pickVertex(int vertex) {
		boolean newValue = !vertexCover.get(vertex);
		vertexCover.set(vertex, newValue);
		cost = newValue ? cost + 1 : cost - 1;
		return cost;
	}
	
	public List<Boolean> getVertexCover() {
		return vertexCover;
	}

	public int getVertexN() {
		return vertexN;
	}
	
	public int getEdgeN() {
		return edgeN;
	}
	
	public void setEdgeN(int edgeN) {
		this.edgeN = edgeN;
	}
	
	public List<List<Integer>> getAdjacencyList() {
		return adjacencyList;
	}

	public int getCost() {
		return cost;
	}
	
	public String toString() {
		// pickVertex(7);
		// pickVertex(5);
		String solution = "vertex cover is: [";
		for (int i = 0; i < vertexCover.size(); i++) {
			if(vertexCover.get(i) == true) {
				solution += i == vertexCover.size() - 1 ? i :  i + ", ";
			}
		}
		solution += "]\n\n";
		solution += "cost is: " + cost + "\n\n";
		solution += "vertex number is: " + vertexN + "\n\n";
		solution += "edge number is: " + edgeN + "\n\n";
		
		solution += "adjacency list is:\n";
		for (int i = 0; i < adjacencyList.size(); i++) {
			solution += i +": ";
			solution += adjacencyList.get(i) +"\n";
		}
		return solution;
	}

}
