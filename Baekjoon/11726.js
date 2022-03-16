const fs = require("fs");
const tile = +fs.readFileSync("input.txt").toString();
let result = [1, 1];
for (let i = 2; i <= tile; i++) {
  result[i] = ((result[i - 1] % 10007) + (result[i - 2] % 10007)) % 10007;
}
console.log(result[tile]);
