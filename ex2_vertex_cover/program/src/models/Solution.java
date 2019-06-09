package models;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private List<Boolean> vertexCover;
	private int vertexN;
	// private int edgeN = 0;
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
		
	public Solution() {}
	
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
			
			// if there is a loop // input vertex equals current (v) from adjacency list
			if(v == vertex) {
				isValid = false;
				break; // TODO: check if break does what you think it does
			}
		}

		return isValid;
	}

	// check if solution is a valid vertex cover (COSTLY OPERATION, USE WISELY)
	public boolean isValid() {		
		for(int i = 0; i < adjacencyList.size(); i++) {
			if(vertexCover.get(i) == false) {
				List<Integer> innerList = adjacencyList.get(i);
				for(int j = 0; j < innerList.size(); j++) {
					if(vertexCover.get(innerList.get(j)) == false) {
						return false;
					}
				}
			}
		}
		return true;
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
	
	public void setVertexCover(List<Boolean> vertexCover) {
		this.vertexCover = vertexCover;
	}

	public int getVertexN() {
		return vertexN;
	}
	
	public void setVertexN(int vertexN) {
		this.vertexN = vertexN;
	}
	/*
	public int getEdgeN() {
		return edgeN;
	}
	
	public void setEdgeN(int edgeN) {
		this.edgeN = edgeN;
	}
	*/
	public List<List<Integer>> getAdjacencyList() {
		return adjacencyList;
	}
	
	public void setAdjacencyList(List<List<Integer>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public Solution copy() {
		Solution copy = new Solution();
		copy.setVertexN(vertexN);
		copy.setAdjacencyList(adjacencyList);
		copy.setVertexCover(new ArrayList<Boolean>(vertexCover));
		copy.setCost(cost);
		return copy;
	}
	
	public String toString() {
		String solution = "vertex number: " + vertexN + "\n";
		// solution += "edge number is: " + edgeN + "\n\n";
		
		solution += "adjacency list:\n";
		for (int i = 0; i < adjacencyList.size(); i++) {
			solution += i +": ";
			solution += adjacencyList.get(i) +"\n";
		}
		
		solution += "\nvertex cover: [";
		for (int i = 0; i < vertexCover.size(); i++) {
			if(vertexCover.get(i) == true) {
				solution += i == vertexCover.size() - 1 ? i :  i + ", ";
			}
		}
		solution += "]\n";
		solution += "cost: " + cost + "\n";
		solution += "solution is " + (isValid() ? "VALID" : "INVALID") + "\n";
		
		return solution;
	}

	public String graphData() {
		String graph = "vertex number: " + vertexN + "\n";
		// solution += "edge number is: " + edgeN + "\n\n";
		
		graph += "adjacency list:\n";
		for (int i = 0; i < adjacencyList.size(); i++) {
			graph += i +": ";
			graph += adjacencyList.get(i) +"\n";
		}
		
		return graph;
	}
	
	public String result() {
		String result = "cost: " + cost + "\n";
		result += "solution is " + (isValid() ? "VALID" : "INVALID") + "\n";
		/*
		result += "vertex cover: [";
		for (int i = 0; i < vertexCover.size(); i++) {
			if(vertexCover.get(i) == true) {
				result += i == vertexCover.size() - 1 ? i :  i + ", ";
			}
		}
		result += "]\n";
		*/
		
		return result;
	}
	
}
