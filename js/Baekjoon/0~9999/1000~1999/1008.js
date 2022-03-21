const fs = require('fs');
const input = fs.readFileSync('../input.txt').toString().trim().split(' ').map(Number);
console.log(input[0]/input[1]);