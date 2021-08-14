function Check(n) {
  for (let i = 2; i*i <=n; i++) {
    if (!(n % i)) {
      return false;
    }
  }
  return true;
}

// n이하의 모든 소수 찾기
function CheckNum(n) {
  let arr = []
  for (let i = 2; i < n; i++) {
    if (Check(i)) {
        arr.push(i);
    }
  }
}

// 에라토스테네스의 체 (가장 빠름)
function Era(n) {
  let arr = [];
  for (let i = 2; i**2 <= n; i++) {
    for (let j = 2; i*j <= n; j++) {
      arr[i*j] = 1;
    }
  }
  let arr2 = []; // 소수 x
  let arr3 = []; // 소수 o
  arr.forEach((num,i) => {
    if (i > 1) {
      if (num) arr2.push(i)
      else arr3.push(i)
    }
  })
  return arr
}
console.log(Check(13));
console.log(Check(4));
console.log(Era(100));

// 에라토스테네스의 체2
function solution(n) {
  const s = new Set();
  for (let i = 1; i <= n; i += 2) {
      s.add(i);
  }
  s.delete(1);
  s.add(2);
  for (let j = 3; j < Math.sqrt(n); j++) {
      if (s.has(j)) {
          for (let k = j * 2; k <= n; k += j) {
              s.delete(k);
          }
      }
  }
  return s.size;
}

// 골드바흐의 추측 : 2보다 큰 모든 짝수는 두 소수의 합으로 표현 가능하다. 추측일 뿐
// 10의 18승 이하에서는 참이라고 증명되어있음.
// 5보다 큰 모든 홀수는 세 소수의 합으로 표현 가능하다.

