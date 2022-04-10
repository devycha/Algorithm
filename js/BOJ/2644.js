const fs = require("fs");
let [n, AB, m, ...arr] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n");
const [A, B] = AB.split(" ").map(Number);
(n = +n), (m = +m);
arr = arr.map((a) => a.trim().split(" ").map(Number));
let obj = {};
arr.forEach((a) => {
  obj[a[1]] = a[0];
});

let result1 = [A];
let result2 = [B];
dfs(result1, A);
dfs(result2, B);

let answer = check(result1, result2);

console.log(answer);

function check(result1, result2) {
  for (let i = 1; i < result1.length; i++) {
    for (let j = 1; j < result2.length; j++) {
      if (result1[i] === result2[j]) {
        return i + j;
      }
    }
  }
  return -1;
}

function dfs(result, current) {
  if (obj[current]) {
    result.push(obj[current]);
    dfs(result, obj[current]);
  }
}
