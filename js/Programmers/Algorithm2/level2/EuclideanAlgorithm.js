function solution(a,b) {
  if (b == 0) return a;
  else {
    let r = a%b;
    a = b;
    b = r;
    return solution(a,b);
  }
}

console.log(solution(100, 10));
console.log(solution(48, 36));
console.log(solution(12, 48));

// 유클리드 호제법 : a와 b의 최대공약수는 a를 b로 나눈 나머지와 b의 최대공약수와 같다.
// GCD(a,b) = GCD(b, a%b) 이때 한 수가 0이 되면 그 때 다른 수를 최대공약수라고 보면된다.
