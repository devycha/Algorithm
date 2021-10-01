const fs = require('fs');
const score = fs.readFileSync('../input.txt').toString().trim().split(' ').map(Number);
console.log(score >= 90 ? 'A' : score >= 80 ? 'B' : score >= 70 ? 'C' : score >= 60 ? 'D' : 'F');