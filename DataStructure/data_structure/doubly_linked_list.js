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
  get(idx) {
    if (idx < 0 || idx >= this.length) return null;
    let count = 0;
    if (idx < this.length/2) {
      let current = this.head;
      while (count != idx) {
        current = current.next;
        count++;
      }
      return current;
    } else {
      let current = this.tail;
      while (count != this.length-idx-1) {
        current = current.prev;
        count++;
      }
      return current;
    }
  }
  set(idx, val) {
    let node = this.get(idx);
    if (node) {
      node.val = val;
      return true;
    }
    return false;
  }
  insert(idx, val) {
    if (idx < 0 || idx > this.length) return false;
    if (idx == 0) return this.unshift(val);
    if (idx == this.length) return this.push(val);
    let newNode = new Node(val)
    let prevNode = this.get(idx-1);

    newNode.next = prevNode.next;
    prevNode.next.prev = newNode;

    prevNode.next = newNode;
    newNode.prev = prevNode;

    this.length++;
    return newNode;
  }
  remove(idx) {
    if (idx < 0 || idx >= this.length) return undefined;
    if (idx == 0) return this.shift();
    if (idx == this.length-1) return this.pop()
    let removeNode = this.get(idx)
    // let prevNode = removeNode.prev;
    // let nextNode = removeNode.next;
    // prevNode.next = nextNode;
    // nextNode.prev = prevNode;
    removeNode.prev.next = removeNode.next;
    removeNode.next.prev = removeNode.prev;
    
    removeNode.next = null;
    removeNode.prev = null;
    this.length--;
    return removeNode;
  }
}

let dll = new DoublyLinkedList()
dll.push(1)
dll.push(2)
dll.push(3)
dll.push(4)
dll.push(5)
dll.push(6)
dll.push(7)

console.log(dll.remove(6))
console.log(dll.get(1).val)
console.log(dll.get(2).val)
console.log(dll.get(3).val)
console.log(dll.get(4).val)
console.log(dll.get(5).val)
console.log(dll.get(6))