/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/11060
 * 
 * 문제
 * 재환이가 1×N 크기의 미로에 갇혀있다. 미로는 1×1 크기의 칸으로 이루어져 있고, 각 칸에는 정수가 하나 쓰여 있다. i번째 칸에 쓰여 있는 수를 Ai라고 했을 때, 재환이는 Ai이하만큼 오른쪽으로 떨어진 칸으로 한 번에 점프할 수 있다. 예를 들어, 3번째 칸에 쓰여 있는 수가 3이면, 재환이는 4, 5, 6번 칸 중 하나로 점프할 수 있다.
 * 재환이는 지금 미로의 가장 왼쪽 끝에 있고, 가장 오른쪽 끝으로 가려고 한다. 이때, 최소 몇 번 점프를 해야 갈 수 있는지 구하는 프로그램을 작성하시오. 만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.
 * 
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄에는 Ai (0 ≤ Ai ≤ 100)가 주어진다.
 * 
    10
    1 2 0 1 3 2 1 5 4 2
 * 
 * 출력
 * 재환이가 최소 몇 번 점프를 해야 가장 오른쪽 끝 칸으로 갈 수 있는지 출력한다. 만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.
 * 
    5
 * 
 * 파싱
 * n = 10
 * arr = [1, 2, 0, 1, 3, 2, 1, 5, 4, 2]
 * 
 * {{초기 설정}}
 * BFS용 queue => [[위치, 카운트] * ...]
 * queue = [[0, 0]]
 * 
 * BFS용 포인터
 * i = 0
 * 
 * 결과 저장
 * result = -1
 * 
 * 이미 최단거리로 해당 위치를 온 경우에 queue에 푸쉬하는 것을 방지하여 메모리 절약
 * checkList = [0 * n+1] => index 편하게 접근하기 위해서 n+1 길이로 설정
 */

// 파싱
const fs = require("fs");
let [n, arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
arr = arr.split(" ").map(Number);

// 초기 설정
let queue = [[0, 0]];
let i = 0;
let result = -1;
let checkList = new Array(+n + 1).fill(0);

/**
 * BFS 시작
 * 현재 위치와 카운트 저장
 * 현재 위치가 맨 끝에 왔을 때 가장 최소로 온 것이므로 result에 현재 카운트를 저장 후 break
 *
 * 1부터 현재 위치의 숫자 까지 점프할 수 있는 경우에서
 * 범위를 벗어나지 않고
 * 이미 최단 횟수로 점프해서 도착한 구역을 제외한 위치에 대해서
 * queue에 [해당 위치, 현재 카운트 +1]을 push한다.
 *
 * 포인터 증가
 */
while (i < queue.length) {
  let current = queue[i][0];
  let count = queue[i][1];

  if (current == +n - 1) {
    result = count;
    break;
  }

  for (let j = 1; j <= arr[current]; j++) {
    if (current + j < +n && !checkList[current + j]) {
      checkList[current + j] = 1;
      queue.push([current + j, count + 1]);
    }
  }
  i++;
}

console.log(result);
/**
 * 채점 결과
 * 메모리: 9828KB
 * 시간: 180ms
 * 언어: JS
 */
