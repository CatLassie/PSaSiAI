package models;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private List<Boolean> vertexCover;
	private int vertexNum;
	private int edgeNum;
	// private int[][] adjacencyMatrix;
	private List<List<Integer>> adjacencyList;
	private int cost;
	
	public Solution(int vertexNum, int edgeNum, List<List<Integer>> adjacencyList) {
		this.vertexNum = vertexNum;
		this.edgeNum = edgeNum;
		this.adjacencyList = adjacencyList;
		
		vertexCover = new ArrayList<Boolean>();
		for(int i = 0; i < vertexNum; i++) {
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

	public int getVertexNum() {
		return vertexNum;
	}

	public int getEdgeNum() {
		return edgeNum;
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
		String vCover = "vertex cover is:\n[";
		for (int i = 0; i < vertexCover.size(); i++) {
			if(vertexCover.get(i) == true) {
				vCover += i == vertexCover.size() - 1 ? i :  i + ", ";
			}
		}
		vCover += "]\n";
		vCover += "cost is: " + cost + "\n";
		vCover += "vertex number is: " + vertexNum + "\n";
		vCover += "edge number is: " + edgeNum + "\n";
		return vCover;
	}

}
