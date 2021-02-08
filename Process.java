
public class Process implements Comparable<Process> {
	private int priority;
	private int arrivalTime;
	private int timeRemaining;
	private int timeNotProcessed;

	public Process(int priorityLevel, int requiredProcessingTime, int currentTime) {
		priority = priorityLevel;
		timeNotProcessed = 0;
		timeRemaining = requiredProcessingTime;
		arrivalTime = currentTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void resetTimeNotProcessed() {
		timeNotProcessed = 0;
	}

	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public void reduceTimeRemaining() {
		timeRemaining--;
	}

	public int getTimeNotProcessed() {
		return timeNotProcessed;
	}

	public void setTimeNotProcessed(int timeNotProcessed) {
		this.timeNotProcessed = timeNotProcessed;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean finish() {
		return timeRemaining == 0;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int compareTo(Process comp) {
		if (this.priority != comp.getPriority()) {
			int result = -1;
			if (this.priority > comp.getPriority()) {
				result = 1;
			}
			return result;
		} else {
			int result = 0;
			if (this.arrivalTime < comp.getArrivalTime()) {
				result = 1;
			} else if (this.arrivalTime > comp.arrivalTime) {
				result = -1;
			}
			return result;
		}
	}
}
