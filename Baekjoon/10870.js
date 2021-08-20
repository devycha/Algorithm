const fs = require('fs');
const a = +fs.readFileSync('../input.txt', 'utf8');
const fibo = (n) => n <= 1 ? n : fibo(n-1) + fibo(n-2);
console.log(fibo(a));