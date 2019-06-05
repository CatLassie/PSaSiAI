package neighbourhood;

import models.Solution;
import util.NeighbourhoodStructureEnum;

public interface INeighbourhood {
	public Solution move(Solution solution);
	public NeighbourhoodStructureEnum getNeighbourhoodType();
}
