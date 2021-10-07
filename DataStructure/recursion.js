// function three() {
//   console.log(1)
// }

// function two() {
//   console.log(2)
//   three()
// }

// function one() {
//   console.log(3)
//   two()
// }

// one()

function recursiveSum(n) {
  // if (n == -10) return -10;
  return n + recursiveSum(n-1)
}

console.log(recursiveSum(10))