const fs = require("fs");
const [N, arr1, M, arr2] = fs.readFileSync("input.txt").toString().split("\n");
let obj = {};
let result = [];
arr1.split(" ").forEach((el) => (obj[+el] = 1));
arr2.split(" ").forEach((el) => {
  if (obj[+el]) {
    result.push(1);
  } else {
    result.push(0);
  }
});
console.log(result.join("\n"));
