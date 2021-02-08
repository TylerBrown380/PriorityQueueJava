import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxHeap<E extends Comparable<E>> implements Iterable<MaxHeap.Node<E>> {

	private Node<E>[] nodeObjects;
	private int size;
	private static final int arraySize = 10;

	public MaxHeap() {
		this(arraySize);
	}

	@SuppressWarnings("unchecked")
	public MaxHeap(int size) {
		nodeObjects = new Node[size];
	}

	public void expandIfNecessary() {
		if (isFull()) {
			// increase array if it is full
			nodeObjects = Arrays.copyOf(nodeObjects, nodeObjects.length + arraySize);
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == nodeObjects.length;
	}

	private int parent(int index) {
		// gets the parent
		return (index - 1) / 2;
	}

	public void buildMaxHeap() {
		for (int index = (size / 2) - 1; index >= 0; index--) {
			maxHeapifyDown(index);
		}
	}

	private void swap(int node1, int node2) {
		Node<E> temp = nodeObjects[node1];
		nodeObjects[node1] = nodeObjects[node2];
		nodeObjects[node2] = temp;
	}

	public Node<E> remove() {
		if (size > 0) {
			Node<E> root = nodeObjects[0];
			// swapping head with last root
			nodeObjects[0] = nodeObjects[--size];
			maxHeapifyDown(0);
			// returns node that got CPU time
			return root;
		}
		throw new NoSuchElementException("No processes to remove.");
	}
	
	public void insert(Node<E> node) {
		expandIfNecessary();
		// add the object to the end of the array
		int currentObject = size++;
		nodeObjects[currentObject] = node;
		// maxHeapifyUp
		maxHeapifyUp(currentObject);
	}
	
	private void maxHeapifyUp(int nodeIndex) {
		int currentNodeIndex = nodeIndex;
		while (nodeObjects[currentNodeIndex].compareTo(nodeObjects[parent(currentNodeIndex)]) > 0) {
			// swap if larger than parent
			swap(currentNodeIndex, parent(currentNodeIndex));
			// move up and continue checking until parent is no longer bigger
			currentNodeIndex = parent(currentNodeIndex);
		}
	}

	private void maxHeapifyDown(int parentNodeIndex) {
		int largest = parentNodeIndex;
		// calculate children of passed in parent node
		int rightChild = 2 * parentNodeIndex + 2;
		int leftChild = 2 * parentNodeIndex + 1;
		// compares the left child against its parent
		if (leftChild < size && nodeObjects[leftChild].compareTo(nodeObjects[parentNodeIndex]) > 0) {
			// updates largest if left child is larger
			largest = leftChild;
		}
		// compares parent with its right child.
		if (rightChild < size && nodeObjects[rightChild].compareTo(nodeObjects[largest]) > 0) {
			// updates largest if right child is larger
			largest = rightChild;
		}
		if (parentNodeIndex != largest) {
			// swap the largest with the parent
			swap(parentNodeIndex, largest);
			// recursive call for the rest of the children
			maxHeapifyDown(largest);
		}
	}

	// Node class used to keep track of maxHeap
	public static class Node<E extends Comparable<E>> {
		private E element;

		public Node(E element) {
			this.element = element;
		}

		public E getElement() {
			return element;
		}

		public int compareTo(Node<E> comp) {
			return element.compareTo(comp.getElement());
		}
	}

	// Iterator used to step over all processes
	public Iterator<Node<E>> iterator() {
		return new Iterator<Node<E>>() {
			int next = 0;

			public boolean hasNext() {
				return next < size;
			}

			public Node<E> next() {
				if (next < size) {
					return nodeObjects[next++];
				}
				throw new NoSuchElementException("No more processes to iterate over.");
			}
		};
	}
}
