const fs = require("fs");
let n = fs.readFileSync("input.txt").toString();
let isMirco = false;
let sum = 0;
for (let i = 0; i < n.length; i++) {
  if (n[i] == "0") isMirco = true;
  sum = ((sum % 3) + (+n[i] % 3)) % 3;
}
if (!isMirco) {
  console.log(-1);
} else {
  if (sum == 0) {
    console.log(
      n
        .split("")
        .sort((a, b) => b - a)
        .join("")
    );
  } else {
    console.log(-1);
  }
}

// if (n.indexOf("0") == -1) {
//   console.log(-1);
// } else {
//   if (n.reduce((a, b) => +a + +b, 0) % 3 == 0) {
//     n = +n.sort((a, b) => b - a).join("");
//     console.log(n);
//   } else {
//     console.log(-1);
//   }
// }
