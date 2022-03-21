class Node {
  constructor(val, priority) {
    this.val = val;
    this.priority = priority
  } 
}

class PriorityQueue {
  constructor() {
    this.values = [];
  }
  enqueue(val, priority) {
    let newNode = new Node(val, priority);
    this.values.push(newNode);
    this.bubbleUp();
    return newNode;
  }
  bubbleUp() {
    let idx = this.values.length-1;
    while (idx > 0) {
      let parent = Math.floor((idx-1)/2);
      if (this.values[parent].priority <= this.values[idx].priority) break;
      [this.values[parent], this.values[idx]] = [this.values[idx], this.values[parent]];
      idx = parent;
    }
  }
  dequeue() {
    const extractNode = this.values[0]
    this.values[0] = this.values.pop()
    this.sinkDown();
    return extractNode
  }
  sinkDown() {
    let idx = 0;
    while (true) {
      let left = 2*idx + 1;
      let right = 2*idx + 2;
      let swap = null;

      if (left < this.values.length) {
        if (this.values[left].priority < this.values[idx].priority) {
          swap = left
        }
      }
      if (right < this.values.length) {
        if (this.values[right].priority < this.values[idx].priority) {
          swap = this.values[left].priority < this.values[right].priority ? left : right;
        }
      }
      if (swap) {
        [this.values[swap], this.values[idx]] = [this.values[idx], this.values[swap]]
        idx = swap
      } else {
        break;
      }
    }
  }
}

let pq = new PriorityQueue();
pq.enqueue('안녕하세요', 6)
pq.enqueue('저는', 5)
pq.enqueue('자바스크립트를', 4)
pq.enqueue('공부중인', 3)
pq.enqueue('20대', 2)
pq.enqueue('학생입니다', 1)
console.log(pq)
pq.dequeue()
console.log(pq)