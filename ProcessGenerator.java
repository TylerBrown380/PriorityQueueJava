import java.util.Random;
public class ProcessGenerator {
	private final double probability;
	private final Random rand;

	public ProcessGenerator(double probability) {
		rand = new Random();
		this.probability = Math.min(Math.abs(probability), 1);
	}

	public boolean query() {
		return Math.random() < probability;
	}
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxPriority) {
		// generate a random priority level between 1 and maxLevel
		int priorityLevel = rand.nextInt(maxPriority) + 1;
		// generate a random processing time between 1 and maxProcessTime
		int requiredProcessingTime = rand.nextInt(maxProcessTime) + 1;
		return new Process(priorityLevel, requiredProcessingTime, currentTime);
	}
}
