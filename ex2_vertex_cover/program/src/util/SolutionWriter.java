package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import models.Solution;

public class SolutionWriter {
	
	public static void write(String path, Solution solution) throws IOException {
		try(Writer w = new BufferedWriter(new FileWriter(path))) {
			write(w, solution);
		}
	}
	
	private static void write(Writer w, Solution solution) throws IOException {
		w.write("SOLUTION:\n");
		w.write(solution.result());
	}

}
