const fs = require('fs');
const [N, ...arr] = fs.readFileSync("./input.txt").toString().trim().split("\n")
const zero = [1, 0]
const one = [0, 1]
let fibo = [zero, one]
for (let i = 2; i <= Math.max(...arr); i++) {
  fibo.push([fibo[i-2][0] + fibo[i-1][0], fibo[i-2][1] + fibo[i-1][1]])
}
arr.map(Number).forEach(num => {
  console.log(fibo[num].join(" "))
})
