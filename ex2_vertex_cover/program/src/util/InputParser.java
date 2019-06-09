package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputParser {
	private int K;
	private int numVertices;
	private List<List<Integer>> adjacencyList = new ArrayList<>();
	private boolean[][] adjacencyMatrix;
	
	private InputParser() {
		
	}
	
	public static InputParser readInstance(String path) throws FileNotFoundException {
		InputParser inst = new InputParser();
		Scanner s = new Scanner(new File(path));
		
		s.skip("p td ");
		inst.numVertices = s.nextInt();
		s.nextLine();
		
		for(int i=0; i<inst.numVertices; ++i) {
			inst.adjacencyList.add(new ArrayList<Integer>());
		}
		
		inst.adjacencyMatrix = new boolean[inst.numVertices][inst.numVertices];
		for(int i=0; i<inst.numVertices; ++i) {
			Arrays.fill(inst.adjacencyMatrix[i], false);
		}
		
		// System.out.println(inst.numVertices);
		
		
		while(s.hasNext()) {
			s.skip("(#.*[\r\n]+)*");
			int a = s.nextInt();
			int b = s.nextInt();
			inst.adjacencyList.get(a).add(b);
			inst.adjacencyList.get(b).add(a);
			inst.adjacencyMatrix[a][b] = true;
			inst.adjacencyMatrix[b][a] = true;
		}
		
		s.close();
		
		for(int i=0; i<inst.numVertices; ++i) {
			List<Integer> al = inst.adjacencyList.get(i);
			inst.adjacencyList.set(i, al.stream().distinct().collect(Collectors.toList()));
		}
		
		// System.out.println(inst.adjacencyList);
		
		return inst;
	}

	public int getK() {
		return K;
	}

	public int getNumVertices() {
		return numVertices;
	}

	public List<List<Integer>> getAdjacencyList() {
		return adjacencyList;
	}

	public final boolean[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}
}
