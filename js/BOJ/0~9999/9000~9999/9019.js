/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/9019
 * 
 * 문제
 * 네 개의 명령어 D, S, L, R 을 이용하는 간단한 계산기가 있다. 이 계산기에는 레지스터가 하나 있는데, 이 레지스터에는 0 이상 10,000 미만의 십진수를 저장할 수 있다. 각 명령어는 이 레지스터에 저장된 n을 다음과 같이 변환한다. n의 네 자릿수를 d1, d2, d3, d4라고 하자(즉 n = ((d1 × 10 + d2) × 10 + d3) × 10 + d4라고 하자)
 * D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
 * S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
 * L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
 * R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
 * 위에서 언급한 것처럼, L 과 R 명령어는 십진 자릿수를 가정하고 연산을 수행한다. 예를 들어서 n = 1234 라면 여기에 L 을 적용하면 2341 이 되고 R 을 적용하면 4123 이 된다.
 * 여러분이 작성할 프로그램은 주어진 서로 다른 두 정수 A와 B(A ≠ B)에 대하여 A를 B로 바꾸는 최소한의 명령어를 생성하는 프로그램이다. 예를 들어서 A = 1234, B = 3412 라면 다음과 같이 두 개의 명령어를 적용하면 A를 B로 변환할 수 있다.
 * 1234 →L 2341 →L 3412
 * 1234 →R 4123 →R 3412
 * 따라서 여러분의 프로그램은 이 경우에 LL 이나 RR 을 출력해야 한다.
 * n의 자릿수로 0 이 포함된 경우에 주의해야 한다. 예를 들어서 1000 에 L 을 적용하면 0001 이 되므로 결과는 1 이 된다. 그러나 R 을 적용하면 0100 이 되므로 결과는 100 이 된다.
 * 
 * 입력
 * 프로그램 입력은 T 개의 테스트 케이스로 구성된다. 테스트 케이스 개수 T 는 입력의 첫 줄에 주어진다. 각 테스트 케이스로는 두 개의 정수 A와 B(A ≠ B)가 공백으로 분리되어 차례로 주어지는데 A는 레지스터의 초기 값을 나타내고 B는 최종 값을 나타낸다. A 와 B는 모두 0 이상 10,000 미만이다.
 * 
    3
    1234 3412
    1000 1
    1 16
 * 
 * 출력
 * A에서 B로 변환하기 위해 필요한 최소한의 명령어 나열을 출력한다. 가능한 명령어 나열이 여러가지면, 아무거나 출력한다.
 * 
    LL
    L
    DDDD
 * 
 * 파싱
 * T = 3
 * arr = [[1234, 3412], [1000, 1], [1, 16]]
 * 
 * {{초기 설정}}
 * 결과
 * result = []
 * 
 * {{각 케이스마다}}
 * memoization
 * obj = [0 * 10000]
 * 
 * BFS용 큐, 포인터
 * queue = [start], j = 0
 */
const fs = require("fs");
let [T, ...arr] = fs.readFileSync("input.txt").toString().trim().split("\n");
T = +T;
arr = arr.map(a => a.split(" ").map(Number));
let result = [];

for (let i = 0; i < arr.length; i++) {
  let [start, end] = arr[i];
  let obj = new Array(10000).fill(0);
  let queue = [start];
  let j = 0;
  while (j < queue.length) {
    let current = queue[j++];
    if (current == end) {
      break;
    }

    let d = D(current),
      s = S(current),
      l = L(current),
      r = R(current);

    if (!obj[d] && d !== start) {
      obj[d] = obj[current] + "D";
      queue.push(d);
    }

    if (!obj[s] && s !== start) {
      obj[s] = obj[current] + "S";
      queue.push(s);
    }

    if (!obj[l] && l !== start) {
      obj[l] = obj[current] + "L";
      queue.push(l);
    }

    if (!obj[r] && r !== start) {
      obj[r] = obj[current] + "R";
      queue.push(r);
    }
  }
  result.push(obj[end]);
}

console.log(result.join("\n"));

function D(n) {
  return (2 * n) % 10000;
}

function S(n) {
  return n == 0 ? 9999 : n - 1;
}

function L(n) {
  return (n % 1000) * 10 + Math.floor(n / 1000);
}

function R(n) {
  return (n % 10) * 1000 + Math.floor(n / 10);
}
/**
 * 채점 결과
 * 메모리: 50336KB
 * 시간: 4628ms
 * 언어: JS
 */

// 2st
// function L(n) {
//   n = n.toString();
//   if (n.length == 4) {
//     return +(n[1] + n[2] + n[3] + n[0]);
//   } else if (n.length == 3) {
//     // 900 -> 0900 -> 9000
//     return +(n[0] + n[1] + n[2] + "0");
//   } else if (n.length == 2) {
//     // 90 -> 0090 -> 0900 -> 900
//     return +(n[0] + n[1] + "0");
//   } else if (n.length == 1) {
//     // 9 -> 0009 -> 0090 -> 90
//     return +(n[0] + "0");
//   } else {
//     return +n;
//   }
// }

// function R(n) {
//   n = n.toString();
//   if (n.length == 4) {
//     return +(n[3] + n[0] + n[1] + n[2]);
//   } else if (n.length == 3) {
//     // 912 -> 2091
//     return +(n[2] + "0" + n[0] + n[1]);
//   } else if (n.length == 2) {
//     // 91 -> 1009
//     return +(n[1] + "00" + n[0]);
//   } else if (n.length == 1) {
//     // 9 -> 9000
//     return +(n[0] + "000");
//   } else {
//     return +n;
//   }
// }
/**
 * 채점 결과
 * 메모리: 79812KB
 * 시간: 16880ms
 * 언어: JS
 */
