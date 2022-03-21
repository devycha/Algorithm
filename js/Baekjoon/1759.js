const fs = require("fs");
let [LC, alphabet] = fs.readFileSync("input.txt").toString().split("\n");
const [L, C] = LC.split(" ").map(Number);
alphabet = alphabet.split(" ").sort();

let obj = {
  a: "a",
  e: "e",
  i: "i",
  o: "o",
  u: "u",
};

let result = combination(alphabet, L);
result = result.map((r) => r.join(""));

let answer = [];
result.forEach((r) => {
  if (isPassword(r)) answer.push(r);
});

console.log(answer.join("\n"));

function isPassword(str) {
  let vowelCount = 0;
  let consonantCount = 0;

  for (let i = 0; i < str.length; i++) {
    if (obj[str[i]]) {
      vowelCount++;
    } else {
      consonantCount++;
    }

    if (vowelCount >= 1 && consonantCount >= 2) {
      return true;
    }
  }
}

function combination(arr, i) {
  if (i === 1) return arr.map((value) => [value]);

  let result = [];

  arr.forEach((fixed, index, origin) => {
    const rest = origin.slice(index + 1);
    const combinations = combination(rest, i - 1);
    const attached = combinations.map((combination) => [fixed, ...combination]);
    result.push(...attached);
  });
  return result;
}

// 다른 사람 풀이 해석
// 백트래킹으로 풀었음
var input = require("fs")
  .readFileSync("input.txt")
  .toString()
  .trim()
  .split("\n");
function checkRule(password) {
  var ix;
  var vowels = ["a", "e", "i", "o", "u"];
  var cc = 0,
    vc = 0; // 자음 모음
  var pass = password;

  for (ix = 0; ix < password.length; ix++) {
    if (vowels.indexOf(password[ix]) > -1) {
      vc++;
    } else {
      cc++;
    }
  }

  return !!(cc >= 2 && vc >= 1);
}

function makePassword(L, charset, password, ix) {
  if (password.length === L) {
    if (checkRule(password)) {
      console.log(password);
    }
    return;
  }

  if (ix < charset.length) {
    makePassword(L, charset, password + charset[ix], ix + 1);
    makePassword(L, charset, password, ix + 1);
  }
}

var L1 = +input[0].split(" ")[0],
  C1 = +input[0].split(" ")[1];
var charset = input[1]
  .trim()
  .split(" ")
  .sort(function (a, b) {
    return a < b ? -1 : 1;
  });
makePassword(L1, charset, "", 0);
