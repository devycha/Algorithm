const fs = require('fs');
const [a, b] = fs.readFileSync('../input.txt').toString().trim().split(' ').map(Number);
console.log(a > b ? '>' : a < b ? '<' : "==");