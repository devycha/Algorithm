const fs = require('fs');
let [h, m] = fs.readFileSync('../input.txt').toString().trim().split(' ').map(Number);
h = m >= 45 ? h : h ? h-1 : 23;
m = m >= 45 ? m-45: m+15;
console.log(h, m);