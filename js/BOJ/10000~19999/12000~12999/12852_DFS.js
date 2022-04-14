/**
 * ***********************************
 * 시간초과로 틀린 문제.
 * DFS 시간초과 풀이.
 * ***********************************
 */

/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/12852
 * 
 * 문제
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 * X가 3으로 나누어 떨어지면, 3으로 나눈다
 * X가 2로 나누어 떨어지면, 2로 나눈다
 * 1을 뺀다
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 * 
 * 입력
 * 첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 자연수 N이 주어진다.
 * 
    10
 * 
 * 출력
 * 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 * 둘째 줄에는 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다. 정답이 여러 가지인 경우에는 아무거나 출력한다.
    3
    10 9 3 1
 * 
 * 
 * 파싱
 * N = 10
 * 
 * {{초기 설정}}
 * 1로 만들때 까지의 경로(방법)
 * result
 * 
 * BFS를 위한 배열
 * stack = [[N]]
 * 
 * 최소한의 방법으로 이미 만들 수 있는 경우가 있을 때
 * 중복을 방지하기 위한 obj - O(1)의 접근 방법을 사용하기 위함.
 * obj = {}
 */

// 파싱
const fs = require("fs");
let N = +fs.readFileSync("input.txt").toString();

let result;
let stack = [[N]];
let min = Infinity;

dfs(stack);
console.log(min - 1 + "\n" + result.join(" "));

function dfs(nums) {
  let lastNum = nums[nums.length - 1];

  if (lastNum <= 0) {
    return;
  }
  if (lastNum == 1) {
    if (min > nums.length) {
      min = nums.length;
      result = nums;
    }
    return;
  }
  if (lastNum % 3 == 0) {
    dfs([...nums, lastNum / 3]);
  }
  if (lastNum % 2 == 0) {
    dfs([...nums, lastNum / 2]);
  }
  dfs([...nums, lastNum - 1]);
}
/**
 * 시간 초과
 */
