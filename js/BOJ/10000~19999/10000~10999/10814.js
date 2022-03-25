const fs = require('fs');
const [N, ...arr] = fs.readFileSync('./input.txt').toString().trim().split("\n")
arr.sort((a,b) => +a.split(' ')[0] - +b.split(' ')[0])
console.log(arr.join("\n"))