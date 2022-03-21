const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let i = 0;
while (i < input.length) {
    console.log(input[i].split(' ').map(Number).reduce((sum, el) => sum + el));
    i++;
}