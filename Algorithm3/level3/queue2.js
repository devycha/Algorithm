class Queue {
    constructor() {
        this.arr = [];
    }

    enqueue(val) {
        this.arr.push.push(val);
        return val
    }

    dequeue() {
        let temp = this.arr.shift();
        return temp;
    }
}