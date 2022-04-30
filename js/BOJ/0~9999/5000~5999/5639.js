/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/5639
 * 
 * 시간제한: 1초
 * 메모리제한: 256MB
 * 
 * 문제
 * 이진 검색 트리는 다음과 같은 세 가지 조건을 만족하는 이진 트리이다.
 * 노드의 왼쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 작다.
 * 노드의 오른쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 크다.
 * 왼쪽, 오른쪽 서브트리도 이진 검색 트리이다.
 * 전위 순회 (루트-왼쪽-오른쪽)은 루트를 방문하고, 왼쪽 서브트리, 오른쪽 서브 트리를 순서대로 방문하면서 노드의 키를 출력한다. 후위 순회 (왼쪽-오른쪽-루트)는 왼쪽 서브트리, 오른쪽 서브트리, 루트 노드 순서대로 키를 출력한다. 예를 들어, 위의 이진 검색 트리의 전위 순회 결과는 50 30 24 5 28 45 98 52 60 이고, 후위 순회 결과는 5 28 24 45 30 60 52 98 50 이다.
 * 이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 트리를 전위 순회한 결과가 주어진다. 노드에 들어있는 키의 값은 106보다 작은 양의 정수이다. 모든 값은 한 줄에 하나씩 주어지며, 노드의 수는 10,000개 이하이다. 같은 키를 가지는 노드는 없다.
 * 
    50
    30
    24
    5
    28
    45
    98
    52
    60
 * 
 * 출력
 * 입력으로 주어진 이진 검색 트리를 후위 순회한 결과를 한 줄에 하나씩 출력한다.
 * 
    5
    28
    24
    45
    30
    60
    52
    98
    50
 * 
 * 파싱
 * arr = [50, 30, 24, 5, 28, 45, 98, 52, 60]
 * * Node 클래스의 parent, left, right 포인터를 이용하여 
 * * 풀이를 했지만 여러가지 테스트 케이스에서는 통과했지만
 * * 제출하면 틀렸다는 결과를 받음.
 */
const fs = require("fs");
let arr = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n")
  .map(Number);

class Node {
  constructor(parent, value) {
    this.parent = parent;
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

let checkList = new Array(arr.length).fill(0);
let root = new Node(null, arr[0]);
let queue = [[root, 0]];
checkList[0] = 1;
let i = 0;
console.log(arr);

while (i < queue.length) {
  let [node, pointer] = queue[i++];
  if (!node.parent) {
    for (let j = pointer + 1; j < arr.length; j++) {
      if (arr[j] < node.value && !checkList[j]) {
        checkList[j] = 1;
        let newNode = new Node(node, arr[j]);
        node.left = newNode;
        queue.push([newNode, j]);
        break;
      }
    }

    for (let j = pointer + 1; j < arr.length; j++) {
      if (arr[j] > node.value && !checkList[j]) {
        checkList[j] = 1;
        let newNode = new Node(node, arr[j]);
        node.right = newNode;
        queue.push([newNode, j]);
        break;
      }
    }
  } else if (node.value < node.parent.value) {
    for (let j = pointer + 1; j < arr.length; j++) {
      if (arr[j] < node.value && arr[j] < node.parent.value && !checkList[j]) {
        if (node.value < root.value && arr[j] > root.value) continue;
        if (node.value > root.value && arr[j] < root.value) continue;
        checkList[j] = 1;
        let newNode = new Node(node, arr[j]);
        node.left = newNode;
        queue.push([newNode, j]);
        break;
      }
    }

    for (let j = pointer + 1; j < arr.length; j++) {
      if (arr[j] > node.value && arr[j] < node.parent.value && !checkList[j]) {
        if (node.value < root.value && arr[j] > root.value) continue;
        if (node.value > root.value && arr[j] < root.value) continue;
        checkList[j] = 1;
        let newNode = new Node(node, arr[j]);
        node.right = newNode;
        queue.push([newNode, j]);
        break;
      }
    }
  } else if (node.value > node.parent.value) {
    for (let j = pointer + 1; j < arr.length; j++) {
      if (arr[j] < node.value && arr[j] > node.parent.value && !checkList[j]) {
        if (node.value < root.value && arr[j] > root.value) continue;
        if (node.value > root.value && arr[j] < root.value) continue;
        checkList[j] = 1;
        let newNode = new Node(node, arr[j]);
        node.left = newNode;
        queue.push([newNode, j]);
        break;
      }
    }

    for (let j = pointer + 1; j < arr.length; j++) {
      if (arr[j] > node.value && arr[j] > node.parent.value && !checkList[j]) {
        if (node.value < root.value && arr[j] > root.value) continue;
        if (node.value > root.value && arr[j] < root.value) continue;
        checkList[j] = 1;
        let newNode = new Node(node, arr[j]);
        node.right = newNode;
        queue.push([newNode, j]);
        break;
      }
    }
  }
}

let obj = {};
let result = [];
search(root);
console.log(result.join("\n"));

function search(node) {
  if (!node.left && !node.right) {
    obj[node.value] = 1;
    result.push(node.value);
    if (!node.parent) return;
    return search(node.parent);
  }

  if (node.left && !obj[node.left.value]) {
    return search(node.left);
  } else if (node.right && !obj[node.right.value]) {
    return search(node.right);
  } else if (node.left && obj[node.left.value]) {
    obj[node.value] = 1;
    result.push(node.value);
    if (!node.parent) return;
    return search(node.parent);
  } else if (node.right && obj[node.right.value]) {
    obj[node.value] = 1;
    result.push(node.value);
    if (!node.parent) return;
    return search(node.parent);
  }
}
