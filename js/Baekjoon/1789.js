const fs = require("fs");
const n = +fs.readFileSync("input.txt").toString();

// 마지막에서 오답: 시간은 빠름
// let count1 = 0;
// for (let i = 0; (i * (i + 1)) / 2 <= n; i++) {
//   if (n - (i * (i + 1)) / 2 > i) {
//     count1 = i;
//   }
// }
// console.log(count1);

// 정답: 시간은 느림
// let count2 = 0;
//   let sum = 0;
//   for (let i = 1; i < n; i++) {
//     if (n - sum - i > i) {
//       sum += i;
//       count2++;
//     }
//   }

// 차이점 비교
for (let k = 1; k <= 4294967295; k++) {
  let n = k;
  let count1 = 1;
  for (let i = 1; (i * (i + 1)) / 2 <= n; i++) {
    if (n - (i * (i + 1)) / 2 > i) {
      count1 = i;
    }
  }

  let count2 = 0;
  let sum = 0;
  for (let i = 1; sum + i < n; i++) {
    if (n - sum - i > i) {
      sum += i;
      count2++;
    }
  }
  if (count1 !== count2) {
    console.log(k, count1, count2);
    console.log("-------------");
  }
}
