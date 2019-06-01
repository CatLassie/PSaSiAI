package main;

import java.io.FileNotFoundException;

import construction.IConstruction;
import construction.Worst;
import construction.Randomized;
import construction.Greedy;
import models.Solution;
import parser.KPMPInstance;

public class Main {

	public static void main(String[] args) {	
		String instanceN = "01";
		String readPath = "D:/uni/2019SS/PSaSiAI/PSaSiAIrepo/ex2_vertex_cover/heuropttechinstances/instance-"+instanceN+".txt";
		
		try {
			KPMPInstance k = KPMPInstance.readInstance(readPath);
			//System.out.println(k.getNumVertices());
			
			IConstruction constr = new Randomized(); // Worst / Randomized / Greedy
			Solution initialSolution = constr.generateSolution(k);
			
			System.out.println(initialSolution);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
