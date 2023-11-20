package model;

public class MinHeap {
	private HuffBaseNode[] heapArray;
	private int size;
	private int capacity;

	public MinHeap(int capacity) {
		this.capacity = capacity;
		this.heapArray = new HuffBaseNode[capacity];
		this.size = 0;
	}

	// insert node
	public void insert(HuffBaseNode value) {
		if (size == capacity - 1) {
			resizeHeap();
		}
		size++;
		heapArray[size - 1] = value;
		int current = size - 1;
		while (current > 0 && heapArray[current].weight() < heapArray[(current - 1) / 2].weight()) {
			swap(current, (current - 1) / 2);
			current = (current - 1) / 2;
		}
	}

	// gets root
	public HuffBaseNode removeMin() {
		if (size == 0) {
			return null;
		}
		HuffBaseNode min = heapArray[0];
		heapArray[0] = heapArray[size - 1];
		size--;
		heapify(0);
		return min;
	}

	// Other methods:
	private void heapify(int index) {
		int smallest = index;
		int left = 2 * index + 1;
		int right = 2 * index + 2;

		if (left < size && heapArray[left].weight() < heapArray[smallest].weight()) {
			smallest = left;
		}

		if (right < size && heapArray[right].weight() < heapArray[smallest].weight()) {
			smallest = right;
		}

		if (smallest != index) {
			swap(index, smallest);
			heapify(smallest);
		}
	}

	private void swap(int a, int b) {
		HuffBaseNode temp = heapArray[a];
		heapArray[a] = heapArray[b];
		heapArray[b] = temp;
	}

	private void resizeHeap() {
		int newCapacity = capacity * 2;
		HuffBaseNode[] newHeapArray = new HuffBaseNode[newCapacity];
		// Copy elements from the old array
		for (int i = 0; i < size; i++) {
			newHeapArray[i] = heapArray[i];
		}
		// Update the capacity
		capacity = newCapacity;
		heapArray = newHeapArray;
	}

	// returns heap size
	public int size() {
		return this.size;
	}
}
