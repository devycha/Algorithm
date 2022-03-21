// 문제 설명
// 문자열 s의 길이가 4 혹은 6이고, 숫자로만 구성돼있는지 확인해주는 함수, solution을 완성하세요. 예를 들어 s가 "a234"이면 False를 리턴하고 "1234"라면 True를 리턴하면 됩니다.

// 제한 사항
// s는 길이 1 이상, 길이 8 이하인 문자열입니다.
// 입출력 예
// s	return
// "a234"	false
// "1234"	true
function solution(s) {
    return (s.length == 4 || s.length == 6) && s.split(/[a-z]/gi).length == 1
}
// 후기: 정규표현식으로 길이를 판별하는 것도 있다는 것을 잘 사용해야겠다.
// 다른 사람 풀이 해석
function alpha_string46(s) {
    var regex = /^\d{6}$|^\d{4}$/; // 시작부터 숫자로 6자리 | 시작부터 숫자로 4자리 정규식표현
    return regex.test(s); // 정규식.test(s)
}