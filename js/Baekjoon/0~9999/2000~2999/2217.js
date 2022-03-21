const fs = require("fs");
let [N, ...input] = fs
  .readFileSync("input.txt")
  .toString()
  .split("\n")
  .map(Number);

input = input.sort((a, b) => b - a);
let weight = 0;
for (let i = 1; i < input.length; i++) {
  weight = Math.max(weight, input[i - 1] * i);
}
console.log(weight);
