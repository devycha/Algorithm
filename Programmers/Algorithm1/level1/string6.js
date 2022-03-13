// 문제 설명
// 함수 solution은 정수 n을 매개변수로 입력받습니다. n의 각 자릿수를 큰것부터 작은 순으로 정렬한 새로운 정수를 리턴해주세요. 예를들어 n이 118372면 873211을 리턴하면 됩니다.

// 제한 조건
// n은 1이상 8000000000 이하인 자연수입니다.
// 입출력 예
// n	return
// 118372	873211
// 나의 풀이
function solution(n) {
    return parseInt(n.toString().split("").map(o => parseInt(o)).sort((a, b) => b - a).join(""));
}
// 후기: 숫자에 + "" 하면 문자열이되고 문자열 앞에 +를 붙이면 숫자 데이터형이 된다.
// 다른 사람 풀이 해석
function solution(n) {
    const newN = n + "";
    const newArr = newN
        .split("")
        .sort()
        .reverse()
        .join("");

    return +newArr;
}