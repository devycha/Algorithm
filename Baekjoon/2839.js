const fs = require("fs");
let n = +fs.readFileSync("input.txt").toString();
let result = [];
if (n % 5 == 0) {
  console.log(n / 5);
} else {
  let count = 0;
  while (n > 0) {
    if (n % 3 == 0) {
      result.push(count + n / 3);
    }
    n -= 5;
    count++;
  }
  console.log(result.length ? Math.min(...result) : -1);
}
