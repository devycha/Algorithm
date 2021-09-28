const fs = require('fs')
const N = +fs.readFileSync('./input.txt', 'utf8')
let result = [];
for (let i = 0; i < N; i++) {
  let arr = i.toString().split("").map(o=>+o)
  let sum = i + arr.reduce((a,b) => a+b)
  if (sum == N) {
    console.log(i)
    break;
  }
  if (i == N-1) {
    console.log(0)
  }
}