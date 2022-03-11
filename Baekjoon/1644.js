function isPrime(num) {
  if (num % 2 == 0) return false;
  for (let i = 3; i ** 2 <= num; i += 2) {
    if (num % i == 0) return false;
  }
  return true;
}

const fs = require("fs");
const num = +fs.readFileSync("input.txt").toString();
let arr = [2];
let sum = 9;
for (let i = 2; i <= num; i++) {
  if (isPrime(i)) {
    sum += i;
    arr.push(i);
  }
}

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
const generatePrime = value => {
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
