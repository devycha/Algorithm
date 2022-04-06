// https://www.acmicpc.net/problem/1753
/**
 * 입력값 예시
 * 5 6
 * 1
 * 5 1 1
 * 1 2 2
 * 1 3 3
 * 2 3 4
 * 2 4 5
 * 3 4 6
 *
 * 출력값 예시
 * 0
 * 2
 * 3
 * 7
 * INF
 *
 * 파싱
 * V = 5
 * E = 6
 * start = 1
 * arr = [[5, 1, 1], [1, 2, 2], [1, 3, 3], [2, 3, 4], [2, 4, 5], [3, 4, 6]]
 *
 * 초기 설정
 * queue = [start] = [1]
 * distance = ["INF", "INF", ...]
 * distance[start] = distance[1] = 0;
 * let i = 0 (pointer)
 * obj: adjacency object = {}
 */
const fs = require("fs");
let [VE, S, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
const [V, E] = VE.split(" ").map(Number);
const start = +S;
arr = arr.map(a => a.split(" ").map(Number));

let queue = [start];
let distance = new Array(V + 1).fill("INF");
distance[start] = 0;
let i = 0;

let obj = {};
arr.forEach(edge => {
  if (!obj[edge[0]]) obj[edge[0]] = [];
  obj[edge[0]].push([edge[1], edge[2]]);
});

/**
 * BFS
 */
while (i < queue.length) {
  let first = queue[i];
  if (obj[first]) {
    for (let i = 0; i < obj[first].length; i++) {
      let adjacentNode = obj[first][i];
      queue.push(adjacentNode[0]);

      if (distance[adjacentNode[0]] === "INF") {
        distance[adjacentNode[0]] = distance[first] + adjacentNode[1];
      } else {
        distance[adjacentNode[0]] = Math.min(
          distance[adjacentNode[0]],
          distance[first] + adjacentNode[1]
        );
      }
    }
  }

  i++;
}
console.log(distance.join("\n"));
/**
 * 채점 결과
 * 메모리 초과: 수정 예정
 * 언어: JS
 */
