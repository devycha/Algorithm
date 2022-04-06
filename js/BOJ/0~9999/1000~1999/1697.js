// 문제 링크: https://www.acmicpc.net/problem/1697

/**
 * 입력값 예시
 * 5 17
 *
 * 출력값 예시
 * 4
 *
 * 파싱
 * N = 5
 * K = 17
 *
 * 초기 설정
 * 없음.
 */
const fs = require("fs");
let [N, K] = fs.readFileSync("input.txt").toString().split(" ").map(Number);

function jump(N, K) {
  // N이 K보다 크면 -1로 가는 방법밖에 없기 때문에
  if (N >= K) {
    return N - K;
  }

  /**
   * 특수 사례 N = 0, K = 1인 경우 리턴값 1
   * 위 조건문에서 N >= K 인 경우가 걸러지기 때문에
   * K가 1이면 N은 자동적으로 0
   */
  if (K === 1) {
    return 1;
  }

  /**
   * K가 홀수일 때 K-1 혹은 K+1 (짝수)로 접근한 뒤 +-1 해서 답을 구할 수 있음.
   * 둘 중에 최솟값을 구한 뒤 + 1하면 정답.
   * */
  if (K % 2) {
    return 1 + Math.min(jump(N, K - 1), jump(N, K + 1));
  }

  /**
   * K가 짝수일 때
   * 계속 + 1해서 가는 값과
   * K / 2 까지 접근하는 방법 + 1 중 최솟값
   * Math.min(K-N, jump(N, K/2))
   */
  if (K % 2 === 0) {
    return Math.min(K - N, 1 + jump(N, K / 2));
  }
}

console.log(jump(N, K));
/**
 * 채점 결과
 * 메모리: 11112KB
 * 시간: 124ms
 * 언어: JS
 * 문제 풀이 참고 링크: https://blog.naver.com/y2kdj9723/222683382426
 */
