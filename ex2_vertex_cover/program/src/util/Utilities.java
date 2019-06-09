package util;

public class Utilities {
	public static long timer;
	
	public static void startTimer() {
		timer = System.currentTimeMillis();
	}
	
	// timeout set to 5 minutes
	public static boolean isTimeOver () {
		long currentTime = System.currentTimeMillis();
		double diffSec = ((double) currentTime - timer)/1000;
		return diffSec > 60;
	}	
}
