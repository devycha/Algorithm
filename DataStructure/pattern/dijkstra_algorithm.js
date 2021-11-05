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
    return extractNode;
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

class Graph {
  constructor() {
    this.adjacencyList = {};
  }
  addVertex(vertex) {
    if (!this.adjacencyList[vertex]) this.adjacencyList[vertex] = [];
  }
  addEdge(vertex1, vertex2, weight) {
    this.adjacencyList[vertex1].push({node: vertex2, weight}); // same as weight : weight
    this.adjacencyList[vertex2].push({node: vertex1, weight}); // same as weight : weight
  }
  Dijkstra(start, finish) {
    const nodes = new PriorityQueue();
    const distances = {};
    const previous = {};
    let path = [];
    let smallest;
    // 시작 전 설정
    // 시작노드의 거리는 0으로 지정, 나머지는 아직 탐색전이므로 Infinity
    for (let vertex in this.adjacencyList) {
      if (vertex === start) {
        distances[vertex] = 0;
        nodes.enqueue(vertex, 0);
      } else {
        distances[vertex] = Infinity;
        nodes.enqueue(vertex, Infinity);
      }
      previous[vertex] = null;
    }

    // 설정 후 시작
    while (nodes.values.length) {
      smallest = nodes.dequeue().val
      console.log("**************",smallest,"**************")
      // 목적지를 방문했을 경우
      if (smallest === finish) {
        while (previous[smallest]) {
          path.push(smallest);
          smallest = previous[smallest];
        }
        break;
      }
      // 목적지노드가 아닌 인접노드를 방문했을 경우
      if (smallest || distances[smallest] !== Infinity) {
        for (let neighbor in this.adjacencyList[smallest]) {
          let nextNode = this.adjacencyList[smallest][neighbor] // 인접노드를 방문하기 직전
          let candidate = distances[smallest] + nextNode.weight; 
          // 시작점부터 smallest까지의 거리 smallest(현재노드)의 인접리스트에서 nextNode.weight(다음방문할 노드의 가중치)의 합
          let nextNeighbor = nextNode.node; // 인접노드의 값
          if (candidate < distances[nextNeighbor]) {
            // nextNode의 지금까지의 최단거리보다 smallest를 거쳐서 nextNode를 가는 거리가 더 작을 경우
            distances[nextNeighbor] = candidate; // nextNode의 최단거리를 업데이트
            previous[nextNeighbor] = smallest; // nextNode를 최단거리로 가려고 할 때 지나야 하는 바로 직전 노드
            nodes.enqueue(nextNeighbor, candidate);  
          }
        }
      }
      console.log(nodes)
      console.log(previous)
      console.log(distances,"\n")
    }
    return path.concat(smallest).reverse()
  }

}

let graph = new Graph()
graph.addVertex("A");
graph.addVertex("B");
graph.addVertex("C");
graph.addVertex("D");
graph.addVertex("E");
graph.addVertex("F");

graph.addEdge("A","B", 4);
graph.addEdge("A","C", 2);
graph.addEdge("B","E", 3);
graph.addEdge("C","D", 2);
graph.addEdge("C","F", 4);
graph.addEdge("D","E", 3);
graph.addEdge("D","F", 1);
graph.addEdge("E","F", 1);


console.log(graph.Dijkstra("A", "E"));

