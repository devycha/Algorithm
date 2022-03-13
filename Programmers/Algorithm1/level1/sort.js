// 나의 풀이
function solution(strings, n) {
    return strings.sort().sort((a, b) => a.charCodeAt(n) - b.charCodeAt(n));
}
// 후기: n번째 인덱스의 값이 동일한 경우를 먼저 해결하기 위해서 
//      우선 먼저 있던 문자열들을 기본 sort()로 정렬한 후에 n번째 인덱스의 문자를 기준으로 다시 정렬하였음
// 다른 사람 풀이 해석