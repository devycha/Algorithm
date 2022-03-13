// 문제 설명
// 문자열 s를 숫자로 변환한 결과를 반환하는 함수, solution을 완성하세요.

// 제한 조건
// s의 길이는 1 이상 5이하입니다.
// s의 맨앞에는 부호(+, -)가 올 수 있습니다.
// s는 부호와 숫자로만 이루어져있습니다.
// s는 "0"으로 시작하지 않습니다.
// 입출력 예
// 예를들어 str이 "1234"이면 1234를 반환하고, "-1234"이면 -1234를 반환하면 됩니다.
// str은 부호(+,-)와 숫자로만 구성되어 있고, 잘못된 값이 입력되는 경우는 없습니다.
function solution(s) {
    return parseInt(s[0]) ? parseInt(s) : (s[0] == "-") ? -parseInt(s.substr(1, s.length - 1)) : parseInt(s.substr(1, s.length - 1));
}
// 후기: 아래 방법들은 생각지도 못한 방법이라 매우 놀라웠다.. 한줄에 끝내려고 해서 복잡해지기만 했다...
function strToInt(str) {
    return str / 1
}

function strToInt(str) {
    return +str;
}