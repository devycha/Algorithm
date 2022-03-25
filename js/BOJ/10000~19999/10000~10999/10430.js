const fs = require('fs');
const [a, b, c] = fs.readFileSync('../input.txt').toString().trim().split(' ').map(Number);
console.log((a+b)%c);
console.log(((a%c)+(b%c))%c);
console.log((a*b)%c);
console.log(((a%c)*(b%c))%c);