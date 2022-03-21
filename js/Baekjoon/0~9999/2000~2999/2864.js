const fs = require("fs");
const [A, B] = fs.readFileSync("input.txt").toString().split(" ");
const minA = +A.replaceAll("6", "5");
const maxA = +A.replaceAll("5", "6");
const minB = +B.replaceAll("6", "5");
const maxB = +B.replaceAll("5", "6");
console.log(`${minA + minB} ${maxA + maxB}`);
