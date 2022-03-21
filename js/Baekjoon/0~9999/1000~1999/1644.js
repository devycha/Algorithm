function isPrime(num) {
  if (num % 2 == 0) return false;
  for (let i = 3; i ** 2 <= num; i += 2) {
    if (num % i == 0) return false;
  }
  return true;
}

function primeList(num) {
  const list = [];
  const primeOrNot = Array(num + 1).fill(true);
  for (let i = 2; i ** 2 <= num; i++) {
    for (let j = i ** 2; j <= num; j += i) {
      primeOrNot[j] = false;
    }
  }

  for (let i = 2; i < primeOrNot.length; i++) {
    if (primeOrNot[i]) list.push(i);
  }
  return list;
}

const fs = require("fs");
const num = +fs.readFileSync("input.txt").toString();
let arr = primeList(num);
console.log(arr);
// let arr = [2];
// let sum = 9;
// for (let i = 2; i <= num; i++) {
//   if (isPrime(i)) {
//     sum += i;
//     arr.push(i);
//   }
// }

let arr2 = Array(num + 1);
for (let i = 0; i < arr.length; i++) {
  arr2[arr[i]] = 1;
}

for (let i = 0; i < arr.length - 1; i++) {
  let sum = arr[i];
  for (let j = i + 1; j < arr.length; j++) {
    sum += arr[j];
    if (sum > num) break;
    arr2[sum] = arr2[sum] ? arr2[sum] + 1 : 1;
  }
}

console.log(arr2[num] ? arr2[num] : 0);

// obj써서 메모리초과
// 배열 길이 할당 => 합이 존재하지않는 수(undefined)를 처리안함.

// 우수 문제 풀이
// Run by Node.js
// 소수의 연속합

const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];
const generatePrime = (value) => {
  const primes = [];
  const check = new Array(value + 1).fill(true);
  for (let i = 2; i * i <= value; i++) {
    if (!check[i]) continue;
    for (let j = i * i; j <= value; j += i) {
      check[j] = false;
    }
  }
  for (let i = 2; i <= value; i++) {
    if (check[i]) primes.push(i);
  }
  return primes;
};

rl.on("line", function (line) {
  input.push(line);
}).on("close", function () {
  const target = Number(input[0]);
  const primeList = generatePrime(target);

  let left = 0;
  let right = 0;
  let sum = primeList[0];
  let count = 0;

  while (left <= right) {
    if (left === primeList.length || right === primeList.length) break;
    // 투 포인터: sum값이 num값보다 크면 왼쪽부터 하나씩 지워나가는 방법. 지운 후에 left 증가 (이게 문제가 요구한 진짜 답인듯.)
    // sum값이 num보다 작으면 오른쪽을 늘려서 더하는 방법. right를 증가 후 더함.
    // sum값이 num과 같으면 count를 증가시킴
    // 나와 다른 점: 나는 그 수까지 경우의 수를 다 구했지만 이 방법은 num이라는 숫자의 경우만 구함.(이것이 시간 단축의 포인트)
    if (sum < target) {
      sum += primeList[++right];
    } else if (sum > target) {
      sum -= primeList[left++];
    } else {
      count++;
      sum -= primeList[left++];
    }
  }

  console.log(count);
  process.exit();
});
