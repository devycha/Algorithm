const fs = require('fs');
const a = +fs.readFileSync('../input.txt', 'utf8');
const fact = (n) =>  n <= 1 ? 1 : n*fact(n-1);
console.log(fact(a));