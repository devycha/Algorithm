const fs = require("fs");
let N = +fs.readFileSync("input.txt").toString();

function solve(N) {
  //////// wrong solution ///////
  let fin = N;
  let arr = [];
  for (let i = 1; i ** 2 <= N; i++) {
    arr.push(i ** 2);
  }

  let count = 0;
  while (N > 0) {
    let dividor = arr[arr.length - 1];
    if (dividor == 1) {
      count += N;
      N = 0;
    } else {
      if (dividor > N) {
        arr.pop();
      } else if (dividor < N) {
        count += Math.floor(N / dividor);
        N = N % dividor;
      } else {
        count++;
        N = 0;
      }
    }
  }
  console.log(count);

  ////// correct solution ///////
  let answer = Array.from({ length: fin + 1 }, (v, i) => i);

  for (let i = 1; i <= fin; i++) {
    for (let j = 1; j ** 2 <= i; j++) {
      answer[i] = Math.min(answer[i], answer[i - j ** 2] + 1);
    }
  }

  console.log(fin, count, answer[answer.length - 1]);
}

for (let i = 1; i <= N; i++) {
  solve(i);
}
