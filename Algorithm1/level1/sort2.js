// 문제 설명
// 문자열 s에 나타나는 문자를 큰것부터 작은 순으로 정렬해 새로운 문자열을 리턴하는 함수, solution을 완성해주세요.
// s는 영문 대소문자로만 구성되어 있으며, 대문자는 소문자보다 작은 것으로 간주합니다.

// 제한 사항
// str은 길이 1 이상인 문자열입니다.
// 입출력 예
// s	return
// "Zbcdefg"	"gfedcbZ"
// 나의 풀이
function solution(s) {
    var answer = '';
    let arr = s.split("").sort((a, b) => b.charCodeAt() - a.charCodeAt());
    arr.forEach(a => {
        answer += a;
    })
    return answer;
}
// 완료 후 리펙토링
function solution(s) {
    return s.split("").sort((a, b) => b.charCodeAt() - a.charCodeAt()).join("");
}
// 후기: 굳이 charCodeAt을 안써도 오름차순으로 정렬해서 reverse를 써서 뒤집어주면 편하게 풀 수 있었는데 살짝 아쉽다.
// 다른 사람 풀이 해석
function solution(s) {
    return s
        .split("")
        .sort()
        .reverse()
        .join("");
}