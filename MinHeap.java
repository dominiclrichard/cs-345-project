// Minheap class for implementation of buildTree function
class MinHeap {
    HuffTree[] arr;
    int size;
    int lastOccupiedInd;
  
    // Constructor for MinHeap
    MinHeap() {
      size = 10;
      lastOccupiedInd = 0;
      arr = new HuffTree[10];
    }
  
    // Removes the minimum value of minHeap
    void removeMin() {
      if (size <= 0) {
          return;
      }
      else if (size == 1) {
        arr[1] = null;
        size --;
        lastOccupiedInd--;
        return;
      }
      else {
        arr[1] = arr[lastOccupiedInd];
        this.bubbleDown(1);
        size--;
        lastOccupiedInd--;
        return;
      }
    }
  
    // Insearts a value into MinHeap and bubbles it up to the correct position
    void insert(HuffTree ins) {
       arr[lastOccupiedInd + 1] = ins;
       lastOccupiedInd ++;
       size++;
  
       bubbleUp(lastOccupiedInd);
      
       if (lastOccupiedInd >= arr.length - 1) {
          temp = arr[arr.length * 2];
          for (int i = 1; i < arr.length; i++) {
            temp[i] = arr[i];
          }
       }
  
    }
  
    // Bubbles up the value at index ind
    void bubbleUp(int ind) {
      int parent = ind /2;
      if (parent > 0 && arr[ind].weight() < arr[parent].weight()) {
        HuffTree tmp = arr[parent];
        arr[parent] = arr[ind];
        arr[ind] = tmp;
        bubbleUp(parent);
      } 
    }
  
    // bubbles down the value at index ind
    void bubbleDown(int ind) {
      int left = 2*ind;
      int right = (2 * ind) + 1;
  
      if (right <= lastOccupiedInd) {
        int testSwap;
        HuffTree temp = arr[ind];
        if (arr[right].weight() < arr[left].weight()) {
          testSwap = right;
        }
        else {
          testSwap = left;
        }
        
        if (arr[testSwap].weight() < temp.weight()) {
          arr[ind] = arr[testSwap];
          arr[testSwap] = temp;
          bubbleDown(testSwap);
        }
      }
    }
  }
  