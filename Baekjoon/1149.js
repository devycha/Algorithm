// const fs = require("fs");
// let [N, ...prices] = fs.readFileSync("input.txt").toString().split("\n");
// prices = prices.map(price => price.split(" ").map(Number));

// // back-tracking
// let min = Infinity;
// minPrice(prices, 0, "start");
// console.log(min);

// function minPrice(prices, totalPrice, color) {
//   if (prices.length == 0) return (min = Math.min(min, totalPrice));
//   if (color == "start") {
//     minPrice(prices.slice(1), totalPrice + prices[0][0], 0);
//     minPrice(prices.slice(1), totalPrice + prices[0][1], 1);
//     minPrice(prices.slice(1), totalPrice + prices[0][2], 2);
//   } else {
//     if (color == 0) {
//       minPrice(prices.slice(1), totalPrice + prices[0][1], 1);
//       minPrice(prices.slice(1), totalPrice + prices[0][2], 2);
//     } else if (color == 1) {
//       minPrice(prices.slice(1), totalPrice + prices[0][0], 0);
//       minPrice(prices.slice(1), totalPrice + prices[0][2], 2);
//     } else {
//       minPrice(prices.slice(1), totalPrice + prices[0][0], 0);
//       minPrice(prices.slice(1), totalPrice + prices[0][1], 1);
//     }
//   }
// }

// dp
const fs = require("fs");
let [N, ...prices] = fs.readFileSync("input.txt").toString().split("\n");
prices = prices.map(price => price.split(" ").map(Number));
let arr = [];
for (let i = 0; i < +N; i++) {
  arr.push(new Array(3));
}
arr[0][0] = prices[0][0];
arr[0][1] = prices[0][1];
arr[0][2] = prices[0][2];

for (let i = 1; i < arr.length; i++) {
  arr[i][0] = prices[i][0] + Math.min(arr[i - 1][1], arr[i - 1][2]);
  arr[i][1] = prices[i][1] + Math.min(arr[i - 1][0], arr[i - 1][2]);
  arr[i][2] = prices[i][2] + Math.min(arr[i - 1][0], arr[i - 1][1]);
}
let min = Math.min(...arr[arr.length - 1]);
console.log(min);
