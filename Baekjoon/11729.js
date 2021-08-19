const fs = require('fs');
const a = +fs.readFileSync('../input.txt', 'utf8');
const hanoi = (n) => n == 1 ? 1 : 2*hanoi(n-1)+1;
console.log(hanoi(a));

