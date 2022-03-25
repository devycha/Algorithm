const fs = require("fs");
const [N, input] = fs.readFileSync("input.txt").toString().split("\n");
const arr = input.split(" ").map(Number);

let sum = arr.reduce((a, b) => a + b);
let arr2 = [sum - arr[0]];
for (let i = 1; i <= arr.length - 1; i++) {
  arr2.push(arr2[i - 1] - arr[i]);
}
let result = 0;
for (let i = 0; i < arr.length; i++) {
  result += arr[i] * arr2[i];
}
console.log(result);
