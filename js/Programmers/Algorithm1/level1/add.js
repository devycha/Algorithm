// 두 정수 left와 right가 매개변수로 주어집니다. left부터 right까지의 모든 수들 중에서, 약수의 개수가 짝수인 수는 더하고, 약수의 개수가 홀수인 수는 뺀 수를 return 하도록 solution 함수를 완성해주세요.
// 나의 풀이
function isEven(num) {
    let count = 0;
    for (let i = 0; i < num; i++) {
        if (!(num % i)) {
            count += 1;
        }
    }
    return count % 2 ? true : false
}

function solution(left, right) {
    var answer = 0;
    for (let i = left; i < right + 1; i++) {
        answer += isEven(i) ? i : -i;
    }
    return -answer;
}
// 다른 사람 풀이 해석
function solution(left, right) {
    var answer = 0;
    for (let i = left; i <= right; i++) {
        if (Number.isInteger(Math.sqrt(i))) {
            answer -= i;
        } else {
            answer += i;
        }
    }
    return answer;
}