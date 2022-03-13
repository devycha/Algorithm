// 문제 설명
// 두 수를 입력받아 두 수의 최대공약수와 최소공배수를 반환하는 함수, solution을 완성해 보세요. 배열의 맨 앞에 최대공약수, 그다음 최소공배수를 넣어 반환하면 됩니다. 예를 들어 두 수 3, 12의 최대공약수는 3, 최소공배수는 12이므로 solution(3, 12)는 [3, 12]를 반환해야 합니다.

// 제한 사항
// 두 수는 1이상 1000000이하의 자연수입니다.
// 입출력 예
// n	m	return
// 3	12	[3, 12]
// 2	5	[1, 10]
// 나의 풀이
function solution(n, m) {
    let GCD = 0;
    let LCM = 0;
    for (let i = 1; i <= n; i++) {
        if (!((n % i) || (m % i))) GCD = i;
    }
    for (let i = 1; i <= m; i++) {
        if (!((n * i) % m)) {
            LCM = n * i;
            break;
        }
    }
    return [GCD, LCM];
}
// 다른 사람 풀이 해석: var의 허점을 이용한 풀이인 것 같다 lexcial scope를 생각하면 기피해야 할 것 같다.
function gcdlcm(a, b) {
    var r;
    for (var ab = a * b; r = a % b; a = b, b = r) {}
    return [b, ab / b];
}