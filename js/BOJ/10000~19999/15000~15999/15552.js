// const fs = require('fs');
// const [n, ...arr] = fs.readFileSync('../input.txt').toString().trim().split('\r\n');
// let answer = '';
// for (let i = 0; i < arr.length; i++) {
//   let [a, b] = arr[i].split(' ');
//   answer += (a*1)+(b*1) + '\n';
// }
// console.log(answer);

function FastScanner() { this._initialize.apply(this, arguments); }
FastScanner.prototype._initialize = function () { this._index = 0; }
FastScanner.prototype.readInt = function () {
    let result = 0;
    while (!(input[this._index] & 0x10)) ++this._index;
    while (input[this._index] & 0x10) { result = result * 10 + (input[this._index] & 0x0f); ++this._index; }
    return result;
}

function solution() {
    const scanner = new FastScanner();
    const T = scanner.readInt();
    const arr = new Array(T);

    for (let i = 0; i < T; i++) arr[i] = scanner.readInt() + scanner.readInt();
    return arr.join("\n");
}

const input = require("fs").readFileSync("dev/stdin");
process.stdout.write(solution());
process.exit(0);