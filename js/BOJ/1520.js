const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.trim().split(" ").map(Number);
arr = arr.map(a => a.trim().split(" ").map(Number));

let dx = [1, 0, -1, 0];
let dy = [0, 1, 0, -1];
let checkList = [];

for (let i = 0; i < n; i++) {
  checkList.push(new Array(m).fill(-1));
}
console.log(dfs(0, 0));

function dfs(x, y) {
  if (x == n - 1 && y == m - 1) {
    return 1;
  }
  if (checkList[x][y] != -1) {
    return checkList[x][y];
  }

  checkList[x][y] = 0;

  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (0 <= nx && nx < n && 0 <= ny && ny < m && arr[nx][ny] < arr[x][y]) {
      checkList[x][y] += dfs(nx, ny);
    }
  }
  return checkList[x][y];
}
