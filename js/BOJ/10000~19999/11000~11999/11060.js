const fs = require("fs");
let [n, arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.split(" ").map(Number);

let queue = [[0, 0]];
let i = 0;
let result = -1;
let checkList = new Array(+n + 1).fill(0);

while (i < queue.length) {
  let current = queue[i][0];
  let count = queue[i][1];

  if (current == +n - 1) {
    result = count;
    break;
  }

  for (let j = 1; j <= arr[current]; j++) {
    if (current + j < +n && !checkList[current + j]) {
      checkList[current + j] = 1;
      queue.push([current + j, count + 1]);
    }
  }
  i++;
}

console.log(result);
/**
 * 채점 결과
 * 메모리: 9828KB
 * 시간: 180ms
 * 언어: JS
 */
