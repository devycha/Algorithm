class Node {
  constructor(val){
      this.val = val;
      this.next = null;
  }
}

class Stack {
  constructor(){
      this.first = null;
      this.last = null;
      this.size = 0;
  }
  push(val){
      let newNode = new Node(val);
      if(!this.first){
          this.first = newNode;
          this.last = newNode;
      } else {
          let temp = this.first;
          this.first = newNode;
          this.first.next = temp;
      }
      this.size++;
      return true;
  }
  pop(){
      if(!this.first) return null;
      let temp = this.first;
      if(this.first === this.last){
          this.last = null;
      }
      this.first = this.first.next;
      this.size--;
      return temp.val;
  }
}

let stack = [];
stack.push('너무')
stack.push('어렵다')
stack.pop()
stack.push('쉽다')
console.log(stack)

let stack2 = new Stack()
stack2.push('너무')
stack2.push('어렵다')
stack2.pop()
stack2.push('쉽다')
console.log(stack2)