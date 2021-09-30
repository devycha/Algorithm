const fs = require('fs');
const arr = fs.readFileSync('/dev/stdin', 'utf8').split("\n").map(o => +o.split('\r')[0])
arr.shift()
arr.sort((a,b)=>a-b)
arr.forEach(num => {
  console.log(num)
})

console.log(require('fs').readFileSync('/dev/stdin').toString().trim().split("\n").slice(1).sort((a,b)=>a-b).join('\n'))