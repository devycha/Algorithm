const fs = require("fs");
const [N, money] = fs.readFileSync("input.txt").toString().split("\n");
let total = 0;
money
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b)
  .reduce((a, b) => {
    total += a + b;
    return a + b;
  }, 0);
console.log(total);
