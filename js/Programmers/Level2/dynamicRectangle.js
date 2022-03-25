// 2xn을 1x2 2x1로 채우는 경우의 수
function count(n) {
    if (n <= 2) {
        return n;
    } else {
        return count(n-1) + count(n-2);
    }
}
console.log(count(5));
// 2xn 을 1x2 2x1 2x2로 채우는 방법
function count(n) {
    if (n==1) {
        return 1;
    } else if (n == 2) {
        return 3;
    } else {
        return count(n-1) + 2*count(n-2);
    }
}
console.log(count(5));