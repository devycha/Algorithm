const fs = require("fs");
const [a, b] = fs.readFileSync("./input.txt").toString().split(":").map(Number);
const gcd = (a, b) => {
  if (a >= b) {
    return a % b == 0 ? b : gcd(b, a % b);
  }
  return b % a == 0 ? a : gcd(a, b % a);
};
const c = gcd(a, b);
console.log(`${a / c}:${b / c}`);
