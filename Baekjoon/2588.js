const fs = require('fs');
const [a, b] = fs.readFileSync('../input.txt').toString().trim().split('\n');
for (let i = b.length-1; i >= 0; i--) {
  console.log(parseInt(a) * b[i]);
}
console.log(parseInt(a)*parseInt(b));


