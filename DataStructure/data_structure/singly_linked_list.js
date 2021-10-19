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
}

let ssl = new SinglyLinkedList()
ssl.push('1')
ssl.push('2')
ssl.push('3')
ssl.shift()
ssl.unshift('10')
console.log(ssl)