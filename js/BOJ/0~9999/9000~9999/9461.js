const fs = require("fs");
let max = 0;
const [N, ...nums] = fs
  .readFileSync("input.txt")
  .toString()
  .split("\n")
  .map((a) => {
    if (+a > max) {
      max = +a;
    }
    return +a;
  });

const arr = [1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12];

for (let i = 11; i < max; i++) {
  arr.push(arr[i - 1] + arr[i - 5]);
}
const result = [];
for (let i = 0; i < nums.length; i++) {
  result.push(arr[nums[i] - 1]);
}
console.log(result.join("\n"));

// 12 + 4
// 16 + 5
// 21 + 7
// 28 + 9
