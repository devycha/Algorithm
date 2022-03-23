const fs = require("fs");
let [ability, ...products] = fs
  .readFileSync("input.txt")
  .toString()
  .split("\n");
ability = +ability.split(" ")[1];
products = products
  .map(bag => bag.split(" ").map(Number))
  .sort((a, b) => {
    if (a[0] === b[0]) {
      return a[1] - b[1];
    }
    return a[0] - b[0];
  });
let max = 0;
let result = [];
worthy(0, 0, 0);
console.log(max);
console.log(result);

function worthy(pointer, currentWeight, currentWorth) {
  if (pointer == products.length) return;
  if (currentWeight > ability) return;

  let newWorth = currentWorth + products[pointer][1];
  let newWeight = currentWeight + products[pointer][0];

  if (newWeight <= ability) {
    max = Math.max(max, newWorth);
    worthy(pointer + 1, newWeight, newWorth);
    result.push([newWeight, newWorth]);
  }
  worthy(pointer + 1, currentWeight, currentWorth);
  result.push([currentWeight, currentWorth]);
}

// worthy(products, 0, 0);
// console.log(max);

// function worthy(allProducts, currentWeight, currentWorth) {
//   if (allProducts.length == 0) return (max = Math.max(max, currentWorth));

//   let leftProducts = allProducts.slice();
//   let pop = leftProducts.pop();

//   if (currentWeight + pop[0] <= ability[1]) {
//     worthy(leftProducts, currentWeight + pop[0], currentWorth + pop[1]);
//   }
//   worthy(leftProducts, currentWeight, currentWorth);
// }
