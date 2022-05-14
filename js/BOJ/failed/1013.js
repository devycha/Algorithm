const fs = require("fs");
let [T, ...signals] = fs
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n");

let result = [];
let regExp1 = /10(0+)(1+)/;
let regExp2 = /(01)+/;

function dfs(str) {
  console.log(str);
  if (str.length == 0) {
    return result.push("YES");
  }

  if (str.search(regExp1) !== 0 && str.search(regExp2) !== 0) {
    return result.push("NO");
  }

  if (str.search(regExp1) == 0) {
    let newStr = str.replace(regExp1, "");
    dfs(newStr);
  }

  if (str.search(regExp2) == 0) {
    let newStr = str.replace(regExp2, "");
    dfs(newStr);
  }
}

signals.forEach((signal) => {
  signal = signal.trim();
  dfs(signal);
});

console.log(result.join("\n"));
