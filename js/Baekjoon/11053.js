const fs = require("fs");
let [N, arr] = fs.readFileSync("input.txt").toString().split("\n");
arr = arr.split(" ").map(Number);
let count = new Array(arr.length).fill(1);
let maxLength = 1;

for (let i = 0; i < arr.length; i++) {
  let num = arr[i];
  for (let j = 0; j < i; j++) {
    if (num > arr[j]) {
      let bigCount = Math.max(count[i], count[j] + 1);
      maxLength = Math.max(bigCount, maxLength);
      count[i] = bigCount;
    }
  }
}
console.log(maxLength);
