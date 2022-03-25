const fs = require("fs");
const [A, B] = fs.readFileSync("input.txt").toString().split(" ").map(Number);
const g = gcd(A, B);
const l = lcm(A, B, g);
console.log(g);
console.log(l);

function gcd(a, b) {
  if (a % b == 0) return b;
  return gcd(b, a % b);
}

function lcm(a, b, gcd) {
  return (a * b) / gcd;
}
