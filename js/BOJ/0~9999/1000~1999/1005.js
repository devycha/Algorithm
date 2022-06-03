/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/1005
 * 
 * 입력
 * 첫째 줄에는 테스트케이스의 개수 T가 주어진다. 각 테스트 케이스는 다음과 같이 주어진다. 첫째 줄에 건물의 개수 N과 건물간의 건설순서 규칙의 총 개수 K이 주어진다. (건물의 번호는 1번부터 N번까지 존재한다) 
 * 둘째 줄에는 각 건물당 건설에 걸리는 시간 D1, D2, ..., DN이 공백을 사이로 주어진다. 셋째 줄부터 K+2줄까지 건설순서 X Y가 주어진다. (이는 건물 X를 지은 다음에 건물 Y를 짓는 것이 가능하다는 의미이다) 
 * 마지막 줄에는 백준이가 승리하기 위해 건설해야 할 건물의 번호 W가 주어진다.
 * 
    2
    4 4
    10 1 100 10
    1 2
    1 3
    2 4
    3 4
    4
    8 8
    10 20 1 5 8 7 1 43
    1 2
    1 3
    2 4
    2 5
    3 6
    5 7
    6 7
    7 8
    7
 * 
 * 출력
 * 건물 W를 건설완료 하는데 드는 최소 시간을 출력한다. 편의상 건물을 짓는 명령을 내리는 데는 시간이 소요되지 않는다고 가정한다.
 * 건설순서는 모든 건물이 건설 가능하도록 주어진다.
 * 
    120
    39
 * 
 * 제한
 * 2 ≤ N ≤ 1000
 * 1 ≤ K ≤ 100,000
 * 1 ≤ X, Y, W ≤ N
 * 0 ≤ Di ≤ 100,000, Di는 정수
 * 
 * 파싱 
 * t = 2
 * arr = [
    [ 4, 4 ],
    [ 10, 1, 100, 10 ],
    [ 1, 2 ],
    [ 1, 3 ],
    [ 2, 4 ],
    [ 3, 4 ],
    [ 4 ],
    [ 8, 8 ],
    [ 10, 20, 1, 5, 8,  7, 1, 43 ],
    [ 1, 2 ],
    [ 1, 3 ],
    [ 2, 4 ],
    [ 2, 5 ],
    [ 3, 6 ],
    [ 5, 7 ],
    [ 6, 7 ],
    [ 7, 8 ],
    [ 7 ]
  ]
 * 
 * 초기 설정
 * 건물의 개수
 * n
 * 
 * 건설 규칙의 개수
 * k
 * 
 * 해당 건물을 짓는데 걸리는 시간
 * buildingTime
 * 
 * 건설규칙(선행 - 후행리스트 객체)
 * obj
 * 
 * 위상
 * degree
 * 
 * 해당 건물까지 짓는데 걸리는 최소 시간
 * times
 * 
 * 건설해야할 건물의 번호
 * w
 */
const fs = require("fs");
let [t, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.map((a) => a.trim().split(" ").map(Number));

let result = [];
let n, k, buildingTime, obj, degree, w, times;

for (let i = 0; i < arr.length; i += arr[i][1] + 3) {
  [n, k] = arr[i];
  buildingTime = [0];
  buildingTime.push.apply(buildingTime, arr[i + 1]);
  obj = Array.from({ length: n + 1 }, () => new Array());
  degree = new Array(n + 1).fill(0);
  times = [...buildingTime];

  for (let j = i + 2; j < i + 2 + arr[i][1]; j++) {
    obj[arr[j][0]].push(arr[j][1]);
    degree[arr[j][1]]++;
  }

  w = arr[i + 2 + arr[i][1]][0];

  let queue = [];
  for (let i = 1; i <= n; i++) {
    if (degree[i] == 0) {
      queue.push([i, buildingTime[i]]);
    }
  }

  let pointer = 0;
  while (pointer < queue.length) {
    let [building, time] = queue[pointer++];

    if (obj[building]) {
      obj[building].forEach((nextBuilding) => {
        times[nextBuilding] = Math.max(
          times[nextBuilding],
          buildingTime[nextBuilding] + time
        );

        if (--degree[nextBuilding] == 0) {
          queue.push([nextBuilding, times[nextBuilding]]);
        }
      });
    }
  }

  result.push(times[w]);
}

console.log(result.join("\n"));
/**
 * 채점 결과
 * 메모리: 214588KB
 * 시간: 1648ms
 * 언어: JS
 */
