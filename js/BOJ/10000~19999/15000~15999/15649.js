const fs = require('fs');
const [N, M] = fs.readFileSync('./input.txt').toString().trim().split(" ").map(Number)
let num = [];
for (let i = 1; i <= N; i++) num.push(i)
function back(arr1, arr2) {
  if (arr1.length == M) {
    return console.log(arr1.join(" "))
  }
  for (let i = 0; i < arr2.length; i++) {
    let arr3 = [...arr2]
    arr3.splice(i, 1)
    back([...arr1, arr2[i]], arr3)
  }
}
back([], num)