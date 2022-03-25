const fs = require("fs");
let [N, arr] = fs.readFileSync("input.txt").toString().split("\n");
arr = arr.split(" ").map(Number);
let count = new Array(+N).fill(1);
let max = 1;

for (let i = arr.length - 2; i >= 0; i--) {
  for (let j = arr.length - 1; j > i; j--) {
    if (arr[j] < arr[i]) {
      count[i] = Math.max(count[i], count[j] + 1);
      max = Math.max(max, count[i]);
    }
  }
}
console.log(max);
