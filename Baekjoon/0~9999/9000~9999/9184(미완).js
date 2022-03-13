const w = (a, b, c) => {
  if (a <= 0 || b <= 0 || c <= 0) {
    return 1;
  } else if (a > 20 || b > 20 || c > 20) {
    return w(20, 20, 20);
  } else if (a < b && b < c) {
    return w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
  } else {
    return (
      w(a - 1, b, c) +
      w(a - 1, b - 1, c) +
      w(a - 1, b, c - 1) -
      w(a - 1, b - 1, c - 1)
    );
  }
};

const wResult = (a, b, c, value) => `w(${a}, ${b}, ${c}) = ${value}`;

const fs = require("fs");
const inputs = fs.readFileSync("../input.txt").toString().trim().split("\r\n");
inputs.forEach((input) => {
  const [a, b, c] = input.split(" ").map(Number);
  console.log(wResult(a, b, c, w(a, b, c)));
});
