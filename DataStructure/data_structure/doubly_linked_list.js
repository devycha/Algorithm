class Node {
  constructor(val) {
    this.val = val;
    this.next = null;
    this.prev = null;
  }
}

class DoublyLinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }
  push(val) {
    let newNode = new Node(val);
    if (this.length == 0) {
      this.head = newNode;
      this.tail = this.head;
    } else {
      this.tail.next = newNode;
      newNode.prev = this.tail;
      this.tail = newNode;
    }
    this.length++;
    return this;
  }
  pop() {
    let popNode = this.tail;
    if (this.length == 0) return undefined;
    if (this.length == 1) {
      this.head = null;
      this.tail = null;
    } else {
      this.tail = popNode.prev;
      this.tail.next = null;
      popNode.prev = null
    }
    this.length--
    return popNode;
  }
  shift() {
    if (this.length == 0) return undefined;
    let shiftNode = this.head;
    if (this.length == 1) {
      this.head = null;
      this.tail = null;
    } else {
      this.head = shiftNode.next;
      shiftNode.next = null;
      this.head.prev = null;
    }
    this.length--;
    return shiftNode;
  }
  unshift(val) {
    if (this.length == 0) return this.push(val);
    let newNode = new Node(val);
    newNode.next = this.head;
    this.head.prev = newNode;
    this.head = newNode
    this.length++;
    return this;
  }
}

let dll = new DoublyLinkedList()
dll.push(1)
dll.push(2)
dll.push(3)
dll.pop()
console.log(dll)