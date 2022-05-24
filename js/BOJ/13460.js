const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map((a) => a.trim().split(""));

const rb = findRedAndBlue();
const hole = findHole();

console.log(rb);
console.log(hole);

const visited = {};
visited[rb.join("")] = 1;
let queue = [[...rb, 0]];
let pointer = 0;
let answer = -1;

while (pointer < queue.length) {
  let [rx, ry, bx, by, count] = queue[pointer++];

  const leftResult = left(rx, ry, bx, by);
  const rightResult = right(rx, ry, bx, by);
  const upResult = up(rx, ry, bx, by);
  const downResult = down(rx, ry, bx, by);

  if (leftResult) {
    if (leftResult == true) {
      answer = count + 1;
      break;
    }
    if (leftResult.length == 4 && !visited[leftResult.join("")]) {
      visited[leftResult.join("")] = 1;
      queue.push([...leftResult, count + 1]);
    }
  }

  if (rightResult) {
    if (rightResult == true) {
      answer = count + 1;
      break;
    }
    if (rightResult.length == 4 && !visited[rightResult.join("")]) {
      visited[rightResult.join("")] = 1;
      queue.push([...rightResult, count + 1]);
    }
  }

  if (upResult) {
    if (upResult == true) {
      answer = count + 1;
      break;
    }
    if (upResult.length == 4 && !visited[upResult.join("")]) {
      visited[upResult.join("")] = 1;
      queue.push([...upResult, count + 1]);
    }
  }

  if (downResult) {
    if (downResult == true) {
      answer = count + 1;
      break;
    }
    if (downResult.length == 4 && !visited[downResult.join("")]) {
      visited[downResult.join("")] = 1;
      queue.push([...downResult, count + 1]);
    }
  }
  // console.log(leftResult);
  // console.log(rightResult);
  // console.log(upResult);
  // console.log(downResult);
}

console.log(answer);

function findHole() {
  for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr[0].length; j++) {
      if (arr[i][j] == "O") {
        return [i, j];
      }
    }
  }
}

function findRedAndBlue() {
  let rb = [];
  for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr[0].length; j++) {
      if (arr[i][j] == "R") {
        rb.push(i);
        rb.push(j);
      }

      if (arr[i][j] == "B") {
        rb.push(i);
        rb.push(j);
      }
    }
  }
  return rb;
}

function left(rx, ry, bx, by) {
  if (ry < by) {
    while (arr[rx][ry - 1] == "." || arr[rx][ry - 1] == "O") ry--;
    while (arr[bx][by - 1] == "." || arr[bx][by - 1] == "O") by--;
  } else {
    while (arr[bx][by - 1] == "." || arr[bx][by - 1] == "O") by--;
    while (arr[rx][ry - 1] == "." || arr[rx][ry - 1] == "O") ry--;
  }
  if (arr[bx][by] == "O") return false;
  if (arr[rx][ry] == "O") return true;
  return [rx, ry, bx, by];
}

function right(rx, ry, bx, by) {
  if (ry > by) {
    while (arr[rx][ry + 1] == "." || arr[rx][ry + 1] == "O") ry++;
    while (arr[bx][by + 1] == "." || arr[bx][by + 1] == "O") by++;
  } else {
    while (arr[bx][by + 1] == "." || arr[bx][by + 1] == "O") by++;
    while (arr[rx][ry + 1] == "." || arr[rx][ry + 1] == "O") ry++;
  }
  if (arr[bx][by] == "O") return false;
  if (arr[rx][ry] == "O") return true;
  return [rx, ry, bx, by];
}

function up(rx, ry, bx, by) {
  if (rx < ry) {
    while (arr[rx - 1][ry] == "." || arr[rx - 1][ry] == "O") rx--;
    while (arr[bx - 1][ry] == "." || arr[bx - 1][ry] == "O") bx--;
  } else {
    while (arr[bx - 1][ry] == "." || arr[bx - 1][ry] == "O") bx--;
    while (arr[rx - 1][ry] == "." || arr[rx - 1][ry] == "O") rx--;
  }
  if (arr[bx][by] == "O") return false;
  if (arr[rx][ry] == "O") return true;
  return [rx, ry, bx, by];
}

function down(rx, ry, bx, by) {
  if (rx > ry) {
    while (arr[rx + 1][ry] == "." || arr[rx + 1][ry] == "O") rx++;
    while (arr[bx + 1][ry] == "." || arr[bx + 1][ry] == "O") bx++;
  } else {
    while (arr[bx + 1][ry] == "." || arr[bx + 1][ry] == "O") bx++;
    while (arr[rx + 1][ry] == "." || arr[rx + 1][ry] == "O") rx++;
  }
  if (arr[bx][by] == "O") return false;
  if (arr[rx][ry] == "O") return true;
  return [rx, ry, bx, by];
}
