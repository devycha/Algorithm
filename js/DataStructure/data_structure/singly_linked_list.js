class Node {
  constructor(val) {
    this.val = val;
    this.next= null;
  }
}

class SinglyLinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }
  push(val) {
    const newNode = new Node(val)
    if (!this.head) {
      this.head = newNode;
      this.tail = this.head;
    } else {
      this.tail.next = newNode;
      this.tail = newNode;
    }
    this.length++;
  }
  pop() {
    if (!this.head) return undefined
    let current = this.head;
    let preTail = current;
    while (current.next) {
      preTail = current
      current = current.next;
    }
    this.tail = preTail
    this.tail.next = null;
    this.length--;
    if (this.length === 0) {
      this.head = null;
      this.tail = null;
    }
    return current
  }
  shift() {
    if (!this.head) return undefined
    let currentHead = this.head;
    this.head = currentHead.next
    this.length--;
    return currentHead;
  }
  unshift(val) {
    let newNode = new Node(val);
    if (!this.head) {
      this.head = newNode;
      this.tail = this.head;
    } else {
      newNode.next = this.head;
      this.head = newNode;
    }
    this.length++;
    return this
  }
  get(idx) {
    if (idx < 0 || idx >= this.length) return null
    let current = this.head;
    for (let i = 0; i < idx; i++) {
      current = current.next
    }
    return current
  }
  set(idx, val) {
    let node = this.get(idx)
    if (node) {
      node.val = val
      return node
    }
    return null
  }
  insert(idx, val) {
    if(idx < 0 || idx > this.length) return false;
    if (idx == 0) return this.unshift(val);
    if (idx == this.length) return this.push(val); // this.length or this.length-1
    let newNode = new Node(val)
    let prevNode = this.get(idx-1)
    let nextNode = prevNode.next
    prevNode.next = newNode
    newNode.next = nextNode
    this.length++;
    return newNode
  }
  remove(idx) {
    if (idx < 0 || idx >= this.length) return null
    if (index == this.length-1) this.pop()
    if (index == 0) return this.shift()
    let prevNode = this.get(idx-1)
    let removeNode = prevNode
    prevNode.next = removeNode.next
    this.length--;
    return removeNode
  }
  reverse() {
    let node = this.head; // 맨 처음에 Head였던 노드
    this.head = this.tail;
    this.tail = node;

    let next;
    let prev = null

    for (let i = 0; i < this.length; i++)  {
      next = node.next // 다음노드
      node.next = prev; // 현재노드의 next를 이전노드로 지정
      prev = node; // 이전노드를 현재노드로 업데이트
      node = next // 현재노드를 다음노드로 업데이트
    }
    return this;
  }
}

let ssl = new SinglyLinkedList()
ssl.push('1')
ssl.push('2')
ssl.push('3')
ssl.insert(1, 4)