const fs = require('fs');
let [N, arr] = fs.readFileSync('./input.txt').toString().trim().split("\n")
arr = arr.split(" ").map(Number)
// let set = new Set(arr.slice().sort((a,b) => a-b))
// let arr2 = Array.from(set)
// let answer = [];
// arr.forEach(num => {
//   answer.push(arr2.indexOf(num))
// })
// console.log(answer.join(" "))
// 시간초과
let obj = {};
arr.forEach(num => {
  if (!obj[num]) obj[num] = 1;
})
let count = 0;
let keys = Object.keys(obj).sort((a,b) => +a - +b)
let obj2 = {};
keys.forEach(key => {
  obj2[key] = count;
  count += obj[key]
})
let answer = [];
arr.forEach(num => answer.push(obj2[num]))
console.log(answer.join(" "))
// 클리어