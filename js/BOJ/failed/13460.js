const fs = require("fs");
let [nm, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [n, m] = nm.split(" ").map(Number);
arr = arr.map(a => a.trim().split(""));

const rb = findRedAndBlue();
arr[rb[0]][rb[1]] = ".";
arr[rb[2]][rb[3]] = ".";

const visited = {};
visited[rb.join("")] = 1;
let queue = [[...rb, 0]];
let pointer = 0;
let answer = -1;

while (pointer < queue.length) {
  let [rx, ry, bx, by, count] = queue[pointer++];

  let leftResult = left(rx, ry, bx, by);
  let rightResult = right(rx, ry, bx, by);
  let upResult = up(rx, ry, bx, by);
  let downResult = down(rx, ry, bx, by);

  // console.log(leftResult, rightResult, upResult, downResult);

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
}

console.log(answer);

function findRedAndBlue() {
  let red = [];
  let blue = [];
  for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr[0].length; j++) {
      if (arr[i][j] == "R") {
        red.push(i);
        red.push(j);
      }

      if (arr[i][j] == "B") {
        blue.push(i);
        blue.push(j);
      }
    }
  }
  return [...red, ...blue];
}

function left(rx, ry, bx, by) {
  // 왼쪽으로 기울이기
  // 높이가 같을 때
  if (rx == bx) {
    if (ry < by) {
      // <- R B
      // B는 R이 구멍에 빠지지 않는 이상 넘어갈 수 없음
      // R먼저 왼쪽으로
      while (arr[rx][ry - 1] == "." || arr[rx][ry - 1] == "O") {
        ry--;
        if (arr[rx][ry] == "O") break; // 구멍에 빠진 경우
      }
      // B가 왼쪽으로
      while (arr[bx][by - 1] == "." || arr[bx][by - 1] == "O") {
        if (ry == by - 1 && arr[bx][by - 1] != "O") break; // 구멍이 아니면서 RB가 붙어있을 때
        by--;
        if (arr[bx][by] == "O") break;
      }
    } else {
      // <- B R
      // R는 B이 구멍에 빠지지 않는 이상 넘어갈 수 없음
      // B먼저 왼쪽으로
      while (arr[bx][by - 1] == "." || arr[bx][by - 1] == "O") {
        by--;
        if (arr[bx][by] == "O") break; // 구멍에 빠진 경우
      }
      // R가 왼쪽으로
      while (arr[rx][ry - 1] == "." || arr[rx][ry - 1] == "O") {
        if (by == ry - 1 && arr[rx][ry - 1] != "O") break; // 구멍이 아니면서 BR가 붙어있을 때
        ry--;
        if (arr[rx][ry] == "O") break;
      }
    }
  } else {
    // 높이가 다를 때
    // 상관 없음
    while (arr[rx][ry - 1] == "." || arr[rx][ry - 1] == "O") {
      ry--;
      if (arr[rx][ry] == "O") break; // 구멍에 빠진 경우
    }
    while (arr[bx][by - 1] == "." || arr[bx][by - 1] == "O") {
      by--;
      if (arr[bx][by] == "O") break; // 구멍에 빠진 경우
    }
  }
  // B가 구멍에 빠진경우 실패
  if (arr[bx][by] == "O") return false;
  // B가 빠지지 않고 A가 빠진경우 성공
  if (arr[rx][ry] == "O") return true;
  // 공이 아무것도 빠지지 않고 모두 이동했을 때
  return [rx, ry, bx, by];
}

function right(rx, ry, bx, by) {
  // 오른쪽으로 기울이기
  // 높이가 같을 때
  if (rx == bx) {
    if (by < ry) {
      // B R ->
      // B는 R이 구멍에 빠지지 않는 이상 넘어갈 수 없음
      // R먼저 오른쪽으로
      while (arr[rx][ry + 1] == "." || arr[rx][ry + 1] == "O") {
        ry++;
        if (arr[rx][ry] == "O") break; // 구멍에 빠진 경우
      }
      // B가 오른쪽으로
      while (arr[bx][by + 1] == "." || arr[bx][by + 1] == "O") {
        if (ry == by + 1 && arr[bx][by + 1] != "O") break; // 구멍이 아니면서 RB가 붙어있을 때
        by++;
        if (arr[bx][by] == "O") break;
      }
    } else {
      // R B ->
      // R는 B이 구멍에 빠지지 않는 이상 넘어갈 수 없음
      // B먼저 오른쪽으로
      while (arr[bx][by + 1] == "." || arr[bx][by + 1] == "O") {
        by++;
        if (arr[bx][by] == "O") break; // 구멍에 빠진 경우
      }
      // R이 오른쪽으로
      while (arr[rx][ry + 1] == "." || arr[rx][ry + 1] == "O") {
        if (by == ry + 1 && arr[rx][ry + 1] != "O") break; // 구멍이 아니면서 BR가 붙어있을 때
        ry++;
        if (arr[rx][ry] == "O") break;
      }
    }
  } else {
    // 높이가 다를 때
    // 상관 없음
    while (arr[rx][ry + 1] == "." || arr[rx][ry + 1] == "O") {
      ry++;
      if (arr[rx][ry] == "O") break; // 구멍에 빠진 경우
    }
    while (arr[bx][by + 1] == "." || arr[bx][by + 1] == "O") {
      by++;
      if (arr[bx][by] == "O") break; // 구멍에 빠진 경우
    }
  }
  // B가 구멍에 빠진경우 실패
  if (arr[bx][by] == "O") return false;
  // B가 빠지지 않고 A가 빠진경우 성공
  if (arr[rx][ry] == "O") return true;
  // 공이 아무것도 빠지지 않고 모두 이동했을 때
  return [rx, ry, bx, by];
}

function up(rx, ry, bx, by) {
  // 세로축이 같을 때
  // R
  // B

  if (ry == by) {
    // R이 B보다 위에있는 경우
    if (rx < bx) {
      // R이 먼저 위로 이동
      while (arr[rx - 1][ry] == "." || arr[rx - 1][ry] == "O") {
        rx--;
        if (arr[rx][ry] == "O") break;
      }
      // B가 위로 이동
      while (arr[bx - 1][by] == "." || arr[bx - 1][by] == "O") {
        if (bx == rx + 1 && arr[bx - 1][by] != "O") break;
        bx--;
        if (arr[bx][by] == "O") break;
      }
    } else {
      // R이 B보다 아래에있는 경우
      // B 이동
      while (arr[bx - 1][by] == "." || arr[bx - 1][by] == "O") {
        bx--;
        if (arr[bx][by] == "O") break;
      }
      // R
      while (arr[rx - 1][ry] == "." || arr[rx - 1][ry] == "O") {
        if (rx == bx + 1 && arr[rx - 1][ry] != "O") break;
        rx--;
        if (arr[rx][ry] == "O") break;
      }
    }
  } else {
    // 세로축이 다를 때
    while (arr[rx - 1][ry] == "." || arr[rx - 1][ry] == "O") {
      rx--;
      if (arr[rx][ry] == "O") break;
    }
    while (arr[bx - 1][by] == "." || arr[bx - 1][by] == "O") {
      bx--;
      if (arr[bx][by] == "O") break;
    }
  }
  // B가 구멍에 빠진경우 실패
  if (arr[bx][by] == "O") return false;
  // B가 빠지지 않고 A가 빠진경우 성공
  if (arr[rx][ry] == "O") return true;
  // 공이 아무것도 빠지지 않고 모두 이동했을 때
  return [rx, ry, bx, by];
}

function down(rx, ry, bx, by) {
  // 세로축이 같을 때
  // R
  // B

  if (ry == by) {
    // R이 B보다 아래에있는 경우
    if (rx > bx) {
      // R이 먼저 아래로 이동
      while (arr[rx + 1][ry] == "." || arr[rx + 1][ry] == "O") {
        rx++;
        if (arr[rx][ry] == "O") break;
      }
      // B가 아래로 이동
      while (arr[bx + 1][by] == "." || arr[bx + 1][by] == "O") {
        if (bx + 1 == rx && arr[bx + 1][by] != "O") break;
        bx++;
        if (arr[bx][by] == "O") break;
      }
    } else {
      // R이 B보다 아래에있는 경우
      // B 이동
      while (arr[bx + 1][by] == "." || arr[bx + 1][by] == "O") {
        bx++;
        if (arr[bx][by] == "O") break;
      }
      // R
      while (arr[rx + 1][ry] == "." || arr[rx + 1][ry] == "O") {
        if (rx + 1 == bx && arr[rx + 1][ry] != "O") break;
        rx++;
        if (arr[rx][ry] == "O") break;
      }
    }
  } else {
    // 세로축이 다를 때
    while (arr[rx + 1][ry] == "." || arr[rx + 1][ry] == "O") {
      rx++;
      if (arr[rx][ry] == "O") break;
    }
    while (arr[bx + 1][by] == "." || arr[bx + 1][by] == "O") {
      bx++;
      if (arr[bx][by] == "O") break;
    }
  }

  // B가 구멍에 빠진경우 실패
  if (arr[bx][by] == "O") return false;
  // B가 빠지지 않고 A가 빠진경우 성공
  if (arr[rx][ry] == "O") return true;
  // 공이 아무것도 빠지지 않고 모두 이동했을 때
  return [rx, ry, bx, by];
}
