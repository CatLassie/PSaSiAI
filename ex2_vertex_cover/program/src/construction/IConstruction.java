package construction;

import models.Solution;
import parser.KPMPInstance;
import util.InputParser;

public interface IConstruction {
	public Solution generateSolution(InputParser p);
	public String toString();	
}
