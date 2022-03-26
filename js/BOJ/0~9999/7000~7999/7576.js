const fs = require("fs");
let [MN, ...tomatoes] = fs.readFileSync("input.txt").toString().split("\n");
const [M, N] = MN.split(" ").map(Number);
tomatoes = tomatoes.map(tomato => tomato.split(" ").map(Number));
let queue = [];

let count = 0;
let none = 0;
let days = 0;

for (let i = 0; i < tomatoes.length; i++) {
  for (let j = 0; j < tomatoes[0].length; j++) {
    if (tomatoes[i][j] == 1) {
      queue.push([i, j, 0]);
      count++;
    } else if (tomatoes[i][j] == -1) {
      none++;
    }
  }
}

if (count + none === M * N) {
  console.log(0);
} else {
  let dx = [1, -1, 0, 0];
  let dy = [0, 0, 1, -1];
  let pointer = 0;
  while (pointer < queue.length) {
    let shift = queue[pointer];
    let x = shift[0];
    let y = shift[1];
    let day = shift[2];

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (0 <= nx && nx < N && 0 <= ny && ny < M && tomatoes[nx][ny] == 0) {
        tomatoes[nx][ny] = 1;
        queue.push([nx, ny, day + 1]);
        days = day + 1;
        count++;
      }
    }

    pointer++;
  }
  console.log(count + none === N * M ? days : -1);
}
