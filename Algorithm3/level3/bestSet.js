// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/12938?language=javascript#
function solution(n, s) {
    let a = Math.floor(s / n);
    let left = s - n*a;
    let answer = Array(n).fill(a);
    let i = answer.length-1;
    if (s < n) return [-1]
    while (left > 0) {
        answer[i] += 1;
        left--;
        i--
        if (i == 0) i = answer.length-1;
    }
    return answer;
}

