const fs = require('fs')
const [a, b] = fs.readFileSync('../input.txt', 'utf8').split("\n")
let num = +a.split(" ")[1].split('\r')[0]
let arr = b.split(" ").map(o=>+o)
let sum = [];
for (let i = 0; i < arr.length-2; i++) {
  for (let j = i+1; j < arr.length-1; j++) {
    for (let k = j+1; k < arr.length; k++) {
      let total = arr[i] + arr[j] + arr[k]
      if (total <= num) sum.push(total)
    }
  }
}
sum.sort((a,b) => b-a)
console.log(sum[0])