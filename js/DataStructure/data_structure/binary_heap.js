class MaxBinaryHeap {
  constructor() {
    this.values = [];
  }
  insert(val) {
    this.values.push(val); // 1. 가장 끝에 요소를 추가한다.
    this.bubbleUp();
  }
  bubbleUp() {
    let idx = this.values.length - 1; // 가장 끝 INDEX
    const element = this.values[idx];
    while(true) {
      let parent = Math.floor((idx-1)/2)
      if (this.values[parent] < element) {
        this.values[idx] = this.values[parent];
        this.values[parent] = element;
        idx = parent
      } else {
        break;
      }
    }
  }
  extractMax() {
    const max = this.values[0];
    const end = this.values.pop();
    this.values[0] = end;
    this.sinkDown();
    return max;
  }
  sinkDown() {
    let idx = 0;
    const length = this.values.length;
    const element = this.values[0];
    while (true) {
      let left = 2*idx + 1;
      let right = 2*idx + 2;
      let leftChild, rightChild;
      let swap = null;
      if (left < length) {
        leftChild = this.values[left];
        if (leftChild > element) {
          swap = left;
        }
      }

      if (right < length) {
        rightChild = this.values[right];
        if (rightChild > element) {
          swap = leftChild > rightChild ? left: right;
        }
      }

      if (swap) {
        this.values[idx] = this.values[swap]
        this.values[swap] = element
        idx = swap
      } else {
        break;
      }
    }
  }
}

let heap = new MaxBinaryHeap()
heap.insert(1)
heap.insert(2)
heap.insert(3)
heap.insert(4)
heap.insert(5)
heap.insert(6)

heap.insert(7)
console.log(1)
heap.extractMax()
heap.extractMax()
console.log(heap.values)