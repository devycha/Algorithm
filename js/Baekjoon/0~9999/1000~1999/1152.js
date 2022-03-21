const fs = require('fs');
const a = fs.readFileSync('../input.txt').toString().trim().split(' ');
let count = 0;
a.forEach(word => {
  if (word == "") count++;
})
console.log(a.length-count);