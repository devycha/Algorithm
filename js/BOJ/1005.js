const fs = require("fs");
let [t, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.map(a => a.trim().split(" ").map(Number));

// console.log(arr);
let n, k, buildingTime, condition, obj, degree, w, times;

for (let i = 0; i < arr.length; i += arr[i][1] + 3) {
  [n, k] = arr[i];
  buildingTime = [0];
  buildingTime.push.apply(buildingTime, arr[i + 1]);
  condition = [];
  for (let j = i + 2; j <= i + 2 + arr[i][1]; j++) {
    condition.push(arr[j]);
  }
  w = condition.pop()[0];

  obj = Array.from({ length: n + 1 }, () => new Array());
  degree = new Array(n + 1).fill(0);

  condition.forEach(c => {
    obj[c[0]].push(c[1]);
    degree[c[1]]++;
  });

  let queue = [];
  for (let i = 1; i <= n; i++) {
    if (degree[i] == 0) {
      queue.push([i, buildingTime[i]]);
    }
  }

  let pointer = 0;
  while (pointer < queue.length) {
    console.log(queue);
    let [building, time] = queue[pointer++];

    if (building == w) {
      console.log(time);
    }

    for (let key in obj) {
      if (current < +key) {
        minHeap.add([+key, distance + key - current]);
      }
    }
  }
}
