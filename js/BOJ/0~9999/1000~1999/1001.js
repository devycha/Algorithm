const fs = require('fs');
const input = fs.readFileSync('../input.txt').toString().trim().split(' ').map(Number);
console.log(input.reduce((sum, el) => sum - el));