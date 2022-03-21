const fs = require('fs');
let [N, ...arr] = fs.readFileSync("./input.txt").toString().split("\n")
const set = new Set(arr)
let answer = Array.from(set).sort().sort((a,b) => a.length - b.length)
answer.forEach(a => console.log(a))