// 문제 설명
// 1부터 입력받은 숫자 n 사이에 있는 소수의 개수를 반환하는 함수, solution을 만들어 보세요.

// 소수는 1과 자기 자신으로만 나누어지는 수를 의미합니다.
// (1은 소수가 아닙니다.)

// 제한 조건
// n은 2이상 1000000이하의 자연수입니다.
// 입출력 예
// n	result
// 10	4
// 5	3
// 나의 풀이
function solution(n) {
    var answer = 0;
    let arr = [];
    for (let i = 2; i < Math.sqrt(n) + 1; i++) {
        for (let j = 2; i * j <= n; j++) {
            arr[i * j] = 1;
        }
    }
    arr.forEach((num) => {
        if (num) answer += 1;
    })
    return n - answer - 1;
}
// 후기: 효율성측면에서 계속 실패가 떠서 당황했다. 아마 해답이 에라토스테네스의 체를 쓰는 방법밖에 없는 듯 하다.
// 다른 사람 풀이 해석
function solution(n) {
    const s = new Set();
    for (let i = 1; i <= n; i += 2) {
        s.add(i);
    }
    s.delete(1);
    s.add(2);
    for (let j = 3; j < Math.sqrt(n); j++) {
        if (s.has(j)) {
            for (let k = j * 2; k <= n; k += j) {
                s.delete(k);
            }
        }
    }
    return s.size;
}
// 짝수는 애초에 2의배수이기때문에 소수가 될수 없어서 배제하고 시작함. (2만 따로 추가)
// 원래 나도 Set을 써서 풀려고 했지만 익숙하지 않아서 실패하고 말았다. Set 관련 메소드도 틈틈히 공부해야지..