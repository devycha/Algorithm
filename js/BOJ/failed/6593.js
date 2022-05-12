const fs = require("fs");
let arr = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n")
  .map((a) => a.trim().split(""));

let result = [];
let dx = [1, -1, 0, 0, 0, 0];
let dy = [0, 0, 1, -1, 0, 0];
let dz = [0, 0, 0, 0, 1, -1];
let test = 0;

for (let t = 0; t < arr.length; t += +arr[t][0] * (+arr[t][2] + 1) + 1) {
  let x = +arr[t][0],
    y = +arr[t][2],
    z = +arr[t][4];

  if (t >= arr.length - 1) {
    break;
  }

  let checkList = Array.from({ length: x }, () =>
    Array.from({ length: y }, () => new Array(z).fill(0))
  );

  let building = [];
  for (let i = 0; i < x; i++) {
    let floor = [];
    for (let j = 0; j < y; j++) {
      floor.push(arr[t + 1 + i * (y + 1) + j]);
    }
    building.push(floor);
  }

  let queue = [];
  for (let i = 0; i < x; i++) {
    for (let j = 0; j < y; j++) {
      for (let k = 0; k < z; k++) {
        if (building[i][j][k] == "S") {
          checkList[i][j][k] = 1;
          queue.push([i, j, k]);
        }
      }
    }
  }
  let pointer = 0;

  while (pointer < queue.length) {
    let [cx, cy, cz] = queue[pointer++];

    if (building[cx][cy][cz] == "E") {
      result.push(`Escaped in ${checkList[cx][cy][cz] - 1} minute(s).`);
      break;
    }

    for (let i = 0; i < 6; i++) {
      let nx = cx + dx[i];
      let ny = cy + dy[i];
      let nz = cz + dz[i];

      if (
        0 <= nx &&
        nx < x &&
        0 <= ny &&
        ny < y &&
        0 <= nz &&
        nz < z &&
        !checkList[nx][ny][nz]
      ) {
        if (building[nx][ny][nz] == ".") {
          checkList[nx][ny][nz] = checkList[cx][cy][cz] + 1;
          queue.push([nx, ny, nz]);
        } else if (building[nx][ny][nz] == "E") {
          checkList[nx][ny][nz] = checkList[cx][cy][cz] + 1;
          queue.push([nx, ny, nz]);
        }
      }
    }
  }

  if (!result[test]) {
    result.push("Trapped!");
  }
  test++;
}

console.log(result.join("\n"));
