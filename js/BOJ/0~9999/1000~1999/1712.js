const [a, b, c] = require('fs').readFileSync('../input.txt').toString().trim().split(' ').map(Number);
console.log(b >= c ? -1 : Math.floor(a / (c-b)) + 1);