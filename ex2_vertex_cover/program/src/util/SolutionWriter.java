package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import models.Solution;

public class SolutionWriter {
	
	public static void write(String path, Solution solution) throws IOException {
		try(Writer w = new BufferedWriter(new FileWriter(path))) {
			write(w, solution);
		}
	}
	
	private static void write(Writer w, Solution solution) throws IOException {
		w.write("SOLUTION:\n");
		// w.write(solution.result());
		
		w.write("solution is " + (solution.isValid() ? "VALID" : "INVALID") + "\n");
		w.write("cost: " + solution.getCost() + "\n");
		w.write("vertex cover:\n");
		
		List<Boolean> vertexCover =  solution.getVertexCover();
		for (int i = 0; i < vertexCover.size(); i++) {
			if(vertexCover.get(i) == true) {
				w.write(i+1 + "\n"); // VERTICES START FROM 1 (LIKE IN INPUT FORMAT)
			}
		}
	}

}
