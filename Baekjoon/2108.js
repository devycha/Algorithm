const arr = require('fs').readFileSync('./input.txt').toString().trim().split("\n").slice(1).sort((a,b)=>a-b)
const main = (arr) => {
  let obj = {};
  let numMax = -4000
  let numMin = 4000
  let sum = 0;
  arr.forEach(num => {
    sum += +num
    numMax = Math.max(numMax, num)
    numMin = Math.min(numMin, num)
    if (obj[num]) obj[num]++;
    else obj[num] = 1;
  })
  let keys = Object.keys(obj)
  keys.sort((a,b)=>obj[b]-obj[a])
  let max = obj[keys[0]]
  let answer = [];
  keys.forEach(key => {
    if (obj[key] == max) answer.push(+key)
  })
  console.log([Math.round(sum/arr.length), +arr[(arr.length-1)/2], answer.length > 1 ? answer.sort((a,b) => a-b)[1] : answer[0], numMax - numMin].join("\n"))
}
main(arr)