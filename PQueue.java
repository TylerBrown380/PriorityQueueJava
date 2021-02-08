import java.util.NoSuchElementException;

public class PQueue {
	private MaxHeap<Process> maxHeap = new MaxHeap<>();
	public void enPQueue(Process process) {
		if (process != null) {
			maxHeap.insert(new MaxHeap.Node<>(process));
		}
	}

	public boolean isEmpty() {
		return maxHeap.isEmpty();
	}

	public Process dePQueue() {
		if (maxHeap.isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		return maxHeap.remove().getElement();
	}

	public void update(int timeToIncrementPriority, int maxPriorityLevel) {
		// if queue isn't empty
		if (!maxHeap.isEmpty()) {
			// visit every node 
			for (MaxHeap.Node<Process> node : maxHeap) {
				Process process = node.getElement();
				// increment the time not processed
				int timeNotProcessed = process.getTimeNotProcessed();
				timeNotProcessed++;
				// checking to see if there are any starving processes. poor processes.
				if (timeNotProcessed >= timeToIncrementPriority) {
					if (process.getPriority() < maxPriorityLevel) {
						// raise the priority level and reset time not processed
						process.setPriority(process.getPriority() + 1);
						timeNotProcessed = 0;
					}
				}
				//updating time not processed
				process.setTimeNotProcessed(timeNotProcessed);
			}
			maxHeap.buildMaxHeap();
		}
	}
}
