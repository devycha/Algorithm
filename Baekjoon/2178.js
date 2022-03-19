const fs = require("fs");
let [NM, ...maze] = fs.readFileSync("input.txt").toString().split("\n");
const [N, M] = NM.split(" ").map(Number);
maze = maze.map((m) => m.split("").map(Number));

let check = [];
let distance = [];
for (let i = 0; i < N; i++) {
  check.push(new Array(M).fill(false));
  distance.push(new Array(M).fill(Infinity));
}

let dx = [1, 0, -1, 0];
let dy = [0, 1, 0, -1];
distance[0][0] = 1;

let queue = [[0, 0]];
while (queue.length) {
  let shift = queue.shift();
  let x = shift[0];
  let y = shift[1];
  if (check[x][y]) {
    continue;
  }
  check[x][y] = true;

  for (let i = 0; i < 4; i++) {
    let X = x + dx[i];
    let Y = y + dy[i];
    if (0 <= X && X < N && 0 <= Y && Y <= M) {
      if (!check[X][Y] && maze[X][Y]) {
        queue.push([X, Y]);
        distance[X][Y] = distance[x][y] + 1;
      }
    }
  }
}
console.log(distance[N - 1][M - 1]);

// function solution(maze, count, current, before) {
//   console.log(current);
//   if (current[0] == N - 1 && current[1] == M - 1)
//     return (min = Math.min(min, count));

//   if (current[0] < 0 || current[0] >= N || current[1] < 0 || current[1] >= M)
//     return;

//   for (let i = 0; i < 4; i++) {
//     let x = current[0] + dx[i];
//     let y = current[1] + dy[i];

//     if (
//       0 <= x &&
//       x < N &&
//       0 <= y &&
//       y < M &&
//       maze[x][y] &&
//      !check[x][y] &&
//       (x != before[0] || y != before[1])
//     ) {
//       solution(maze, count + 1, [x, y], [current[0], current[1]]);
//     }
//   }
// }

// function solution(maze, count, check, current) {
//   if (current[0] == N - 1 && current[1] == M - 1)
//     return (min = Math.min(min, count));

//   for (let i = 0; i < 4; i++) {
//     let x = current[0] + dx[i];
//     let y = current[1] + dy[i];

//     if (0 <= x && x < N && 0 <= y && y < M && maze[x][y] && !check[x][y]) {
//       let newCheck = copy2DArray(check);
//       newCheck[x][y] = true;
//       solution(maze, count + 1, newCheck, [x, y]);
//     }
//   }
// }

// function copy2DArray(arr) {
//   let newArr = [];
//   arr.forEach((a) => {
//     newArr.push(a.slice());
//   });
//   return newArr;
// }

// let arr = [
//   [1, 2, 3],
//   [4, 5, 6],
//   [7, 8, 9],
// ];
// let arr2 = arr.slice();
// arr2[2][2] = 100;
// console.log(arr);
