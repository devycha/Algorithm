let queue = [];
queue.push('어렵다')
queue.push('너무')
queue.push('쉽다')
queue.shift()
console.log(queue)

let queue2 = [];
queue2.unshift('어렵다')
queue2.unshift('쉽다')
queue2.unshift('너무')
queue2.pop()
console.log(queue2)

class Node {
  constructor(value){
      this.value = value;
      this.next = null;
  }
}

class Queue {
  constructor(){
      this.first = null;
      this.last = null;
      this.size = 0;
  }
  enqueue(val){
      let newNode = new Node(val);
      if(!this.first){
          this.first = newNode;
          this.last = newNode;
      } else {
          this.last.next = newNode;
          this.last = newNode;
      }
      this.size++;
      return true;
  }

  dequeue(){
      if(!this.first) return null;

      let temp = this.first;
      if(this.first === this.last) {
          this.last = null;
      }
      this.first = this.first.next;
      this.size--;
      return temp.value;
  }
}