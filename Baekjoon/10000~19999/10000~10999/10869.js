const fs = require('fs');
const input = fs.readFileSync('../input.txt').toString().trim().split(' ').map(Number);
console.log(input[0]+input[1]);
console.log(input[0]-input[1]);
console.log(input[0]*input[1]);
console.log(Math.floor(input[0]/input[1]));
console.log(input[0]%input[1]);