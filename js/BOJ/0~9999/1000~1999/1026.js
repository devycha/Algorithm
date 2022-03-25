const fs = require("fs");
const [N, A, B] = fs.readFileSync("input.txt").toString().split("\n");
const a = A.split(" ")
  .sort((a, b) => a - b)
  .map(Number);
const b = B.split(" ")
  .sort((a, b) => b - a)
  .map(Number);
let total = 0;
for (let i = 0; i < +N; i++) {
  total += a[i] * b[i];
}
console.log(total);
