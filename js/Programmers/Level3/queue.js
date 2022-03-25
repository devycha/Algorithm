class Node {
    constructor(val) {
        this.value = val;
        this.next = null;
    }
}

class Queue {
    constructor() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    enqueue(val) {
        let newNode = new NOde(val);
        if(!this.first) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode; // linked list!!
            this.last = newNode;
        }
        this.size++;
        return this.last.value;
    }

    dequeue(val) {
        if (!this.first) return null; // 큐가 비었을 때

        let temp = this.first;
        if (this.first === this.last) {  // 큐에 하나만 있을 때
            this.last = null // this.first를 써야해서 this.null을 지움
        } 
        this.first = this.first.next; // 맨 앞에 있는 요소의 다음 요소를 큐의 맨 앞요소로 바꿈
        this.size--;
        return temp.value;
    }
}