// 자연수 n이 매개변수로 주어집니다. n을 3진법 상에서 앞뒤로 뒤집은 후, 이를 다시 10진법으로 표현한 수를 return 하도록 solution 함수를 완성해주세요.

// 제한사항
// n은 1 이상 100,000,000 이하인 자연수입니다.
// 입출력 예
// n	result
// 45	7
// 125	229
// 입출력 예 설명
// 입출력 예 #1

// 답을 도출하는 과정은 다음과 같습니다.
// n (10진법)	n (3진법)	앞뒤 반전(3진법)	10진법으로 표현
// 45	1200	0021	7
// 따라서 7을 return 해야 합니다.
////////////////////////////////////////////////////////////////
// 나의 풀이
function solution(n) {
    let n3 = [];
    let answer = 0;
    let quotient = 0;
    let msb = 0;
    let num = n;
    for (let i = 0; n >= 3 ** i; i++) {
        msb = i;
    }
    for (let j = msb; j >= 0; j--) {
        let div = 3 ** j;
        quotient = parseInt(num / div);
        num -= div * quotient;
        n3.push(quotient);
    }
    n3.forEach((num, i) => {
        answer += (3 ** i) * num;
    })
    return answer;
}

// 다른 사람 풀이 해석
// 진법변환 내장함수를 사용함
const solution = (n) => {
        return parseInt([...n.toString(3)].reverse().join(""), 3);
    }
    // 후기
    // 두 함수에 진법에 관한 정보가 파라미터로 들어가 있음을 잘 활용해야겠다.
    // toString(intNumber, radix)
    // parsInt(string, radix)

// 진법변환 내장함수를 사용하지 않은 풀이 해석
function solution(n) {
    const answer = [];
    while (n !== 0) {
        answer.unshift(n % 3);
        n = Math.floor(n / 3);
    }
    return answer.reduce((acc, v, i) => acc + (v * Math.pow(3, i)), 0);
}

// Math.pow(3,i) 와 3**i 는 동일한 방식으로 적용되지만 3**i는 bigInt를 다룰 수 있다는 점에서 더 좋다는 것을 알게됨
// Math.pow 보다 **를 활용