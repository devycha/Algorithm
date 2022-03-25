const fs = require("fs");
let [N, ...arr] = fs.readFileSync("input.txt").toString().split("\n");
let max = 0;
arr = arr.map(a => {
  max = Math.max(+a, max);
  return +a;
});

let prime = new Array(max).fill(true);

for (let i = 2; i <= Math.sqrt(max); i++) {
  for (let j = i ** 2; j <= max; j += i) {
    prime[j] = false;
  }
}

let primeList = [];
for (let i = 3; i < prime.length; i++) {
  if (prime[i]) {
    primeList.push(i);
  }
}
console.log(primeList);

let obj = {
  4: "2 2",
};
for (let i = 0; i < primeList.length; i++) {
  for (let j = i; j < primeList.length; j++) {
    let prime1 = primeList[i];
    let prime2 = primeList[j];
    let sum = prime1 + prime2;
    obj[sum] = `${prime1} ${prime2}`;
  }
}

let result = [];
for (let i = 0; i < arr.length; i++) {
  result.push(obj[arr[i]]);
}
console.log(result.join("\n"));

// for (let i = 4; i <= 100; i += 2) {
//   try {
//     console.log(obj[i]);
//   } catch (e) {
//     console.log(e);
//   }
// }
