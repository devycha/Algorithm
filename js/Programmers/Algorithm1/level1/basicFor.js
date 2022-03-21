// 문제 설명
// 두 정수 a, b가 주어졌을 때 a와 b 사이에 속한 모든 정수의 합을 리턴하는 함수, solution을 완성하세요.
// 예를 들어 a = 3, b = 5인 경우, 3 + 4 + 5 = 12이므로 12를 리턴합니다.

// 제한 조건
// a와 b가 같은 경우는 둘 중 아무 수나 리턴하세요.
// a와 b는 -10,000,000 이상 10,000,000 이하인 정수입니다.
// a와 b의 대소관계는 정해져있지 않습니다.
// 입출력 예
// a	b	return
// 3	5	12
// 3	3	3
// 5	3	12
// 나의 풀이
function solution(a, b) {
    let answer = 0;
    if (a <= b) {
        for (let i = a; i <= b; i++) {
            answer += i;
        }
    } else {
        for (let i = b; i <= a; i++) {
            answer += i;
        }
    }
    return answer;

}
// 후기: 수학을 열심히 공부해야겠다.. 옛날에 배웠던 기본적인 등차수열의 합 공식을 통해 지연시간을 극적으로 단축시킬 수 있다.
function solution(a, b) {
    return (a + b) * (Math.abs(b - a) + 1) / 2;
}