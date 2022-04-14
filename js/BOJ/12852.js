const fs = require("fs");
let N = +fs.readFileSync("input.txt").toString();

/**
 * DP
 */

/**
 * BFS 메모리초과 풀이
 */
let result;
let stack = [[N]];

let i = 0;
while (i < stack.length) {
  let shift = stack[i];
  let lastNum = shift[shift.length - 1];
  if (lastNum == 1) {
    result = shift;
    break;
  }

  if (lastNum % 3 == 0) {
    stack.push([...shift, lastNum / 3]);
  }

  if (lastNum % 2 == 0) {
    stack.push([...shift, lastNum / 2]);
  }
  stack.push([...shift, lastNum - 1]);

  i++;
}

console.log(result.length - 1 + "\n" + result.join(" "));

/**
 * DFS 시간초과 풀이.
 */
// dfs(stack);
// console.log(min - 1 + "\n" + result.join(" "));

// function dfs(nums) {
//   let lastNum = nums[nums.length - 1];

//   if (lastNum <= 0) {
//     return;
//   }
//   if (lastNum == 1) {
//     if (min > nums.length) {
//       min = nums.length;
//       result = nums;
//     }
//     return;
//   }
//   if (lastNum % 3 == 0) {
//     dfs([...nums, lastNum / 3]);
//   }
//   if (lastNum % 2 == 0) {
//     dfs([...nums, lastNum / 2]);
//   }
//   dfs([...nums, lastNum - 1]);
// }
