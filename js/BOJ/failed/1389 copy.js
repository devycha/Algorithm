const fs = require("fs");
let [NM, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
const [N, M] = NM.split(" ").map(Number);
arr = arr.map(a => a.split(" ").map(Number));

let friends = [];
for (let i = 0; i <= N; i++) {
  friends.push(new Array(N + 1).fill(undefined));
}

arr.forEach(friend => {
  friends[friend[0]][friend[1]] = 1;
  friends[friend[1]][friend[0]] = 1;
});

let i = 0;
let friend1;
let friend2;
while (i < arr.length) {
  friend1 = arr[i][0];
  friend2 = arr[i][1];
  for (let j = 1; j <= N; j++) {
    if (j == friend1 || j == friend2) continue;
    if (friends[friend2][j]) {
      if (!friends[friend1][j]) {
        friends[friend1][j] = friends[friend2][j] + friends[friend1][friend2];
        arr.push([friend1, j]);
      } else {
        if (friends[friend1][j] > friends[friend2][j] + 1) {
          friends[friend1][j] = friends[friend2][j] + friends[friend1][friend2];
          arr.push([friend1, j]);
        }
      }
    }
    if (friends[friend1][j]) {
      if (!friends[friend2][j]) {
        friends[friend2][j] = friends[friend1][j] + friends[friend1][friend2];
        arr.push([friend2, j]);
      } else {
        if (friends[friend2][j] > friends[friend1][j] + 1) {
          friends[friend2][j] = friends[friend1][j] + friends[friend1][friend2];
          arr.push([friend2, j]);
        }
      }
    }
  }
  i++;
}

friends = friends.map(a =>
  a.reduce((sum, el) => {
    if (Number.isInteger(el)) {
      return sum + el;
    }
    return sum;
  }, 0)
);

let min = Infinity;
let minIndex = 0;
for (let i = 1; i < friends.length; i++) {
  if (friends[i] < min) {
    min = friends[i];
    minIndex = i;
  }
}
console.log(minIndex);
