const fs = require("fs");
let [nmk, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m, k] = nmk.split(" ").map(Number);
arr = arr.map(a => a.trim().split("").map(Number));
let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];

let checkList = Array.from({ length: n }, () =>
  Array.from({ length: m }, () => new Array(k + 1).fill(0))
);

checkList[0][0][0] = 1;
let queue = [[1, 1, 0]];
let pointer = 0;
let result = -1;

while (pointer < queue.length) {
  let [x, y, breaks] = queue[pointer++];

  if (x == n && y == m) {
    result = checkList[x - 1][y - 1][breaks];
    break;
  }

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (0 < nx && nx <= n && 0 < ny && ny <= m) {
      if (
        arr[nx - 1][ny - 1] == 1 &&
        breaks < k &&
        !checkList[nx - 1][ny - 1][breaks + 1]
      ) {
        checkList[nx - 1][ny - 1][breaks + 1] =
          checkList[x - 1][y - 1][breaks] + 1;
        queue.push([nx, ny, breaks + 1]);
      } else if (
        arr[nx - 1][ny - 1] == 0 &&
        !checkList[nx - 1][ny - 1][breaks]
      ) {
        checkList[nx - 1][ny - 1][breaks] = checkList[x - 1][y - 1][breaks] + 1;
        queue.push([nx, ny, breaks]);
      }
    }
  }
}

console.log(result);
