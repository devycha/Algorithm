const fs = require('fs');
const [a, b] = fs.readFileSync('../input.txt', 'utf8').split(' ').map(Number);
let arr = Array(b+1), arr2 = [];
for (let i = 2; i <= b; i++) {
  if (!arr[i]) {
    for (let j = i+i; j <= b; j+=i) {
      arr[j] = 1;
    }
  }
}
for (let i = Math.max(2, a); i <= b; i++) {
  if (!arr[i]) arr2.push(i);
}
console.log(arr2.join('\n'));



const [m, n] = require("fs").readFileSync('../input.txt', 'utf8').split(' ').map(Number);
let np = Array(n + 1), p = []; 
np[0] = 1, np[1] = 1;
for(let i = 2; i <= n; i++) if(!np[i]) for(let j=i*i; j<=n; j+=i) np[j] = 1
for(let i=m; i<=n; i++) if(!np[i]) p.push(i)
console.log(p.join('\n'));
