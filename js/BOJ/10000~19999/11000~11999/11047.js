const fs = require("fs");
let [NK, ...coins] = fs.readFileSync("input.txt").toString().split("\n");
coins = coins.map(Number);
let [N, K] = NK.split(" ").map(Number);
let count = 0;

while (K > 0) {
  let pop = coins.pop();
  if (pop > K) continue;
  count += Math.floor(K / pop);
  K = K % pop;
}
console.log(count);
