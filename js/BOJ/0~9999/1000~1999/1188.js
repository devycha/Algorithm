const fs = require("fs");
const [a, b] = fs.readFileSync("input.txt").toString().split(" ").map(Number);
if (a % b == 0) {
  console.log(0);
} else {
  console.log(foodSlice(a, b));
}

function foodSlice(food, judge) {
  let sliceQuantity = food;
  let totalFood = food;
  let rest = judge;
  let count = 0;
  while (totalFood > 0) {
    if (rest == sliceQuantity) {
      rest = judge;
      sliceQuantity = food;
      totalFood--;
    } else if (rest > sliceQuantity) {
      rest -= sliceQuantity;
      sliceQuantity = food;
      count++;
    } else {
      sliceQuantity -= rest;
      rest = judge;
      totalFood--;
    }
  }
  return count;
}

// console.log(
//     "남은빵 ",
//     totalFood,
//     " 남은 조각수 ",
//     rest,
//     " 잘라야될 크기 ",
//     sliceQuantity,
//     " 카운트 ",
//     count
//   );
