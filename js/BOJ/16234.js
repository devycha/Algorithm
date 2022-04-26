const fs = require("fs");
let [nlr, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
let [n, l, r] = nlr.trim().split(" ").map(Number);
arr = arr.map((a) => a.split(" ").map(Number));

let dx = [1, -1, 0, 0];
let dy = [0, 0, 1, -1];
let checkList = [];
let aliasList = [];
for (let i = 0; i < n; i++) {
  checkList.push(new Array(n).fill(0));
  aliasList.push(new Array(n).fill(0));
}
let count = 0;

for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    checkList[i][j] = arr[i][j];
    aliasList[i][j] = 1;
    dfs(i, j, i, j);
    if (aliasList[i][j] > 1) {
      count++;
      let avg = Math.floor(checkList[i][j] / aliasList[i][j]);
      alias(arr, aliasList, avg);
    } else {
      aliasList[i][j] = 0;
    }
    checkList[i][j] = 0;
  }
}
console.log(count);

function dfs(ox, oy, x, y) {
  for (let i = 0; i < 4; i++) {
    let nx = x + dx[i];
    let ny = y + dy[i];

    if (0 <= nx && nx < n && 0 <= ny && ny < n && !aliasList[nx][ny]) {
      let gap = Math.abs(arr[x][y] - arr[nx][ny]);
      if (l <= gap && gap <= r) {
        checkList[ox][oy] += arr[nx][ny];
        aliasList[ox][oy]++;
        aliasList[nx][ny] = 1;
        dfs(ox, oy, nx, ny);
      }
    }
  }
}

function alias(arr1, arr2, avg) {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (arr2[i][j]) {
        arr1[i][j] = avg;
        arr2[i][j] = 0;
      }
    }
  }
}
