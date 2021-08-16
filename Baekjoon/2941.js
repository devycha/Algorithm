const fs = require('fs');
let string = fs.readFileSync('../input.txt').toString().trim();
const croatia = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z='];
for (let i = 0; i < croatia.length; i++) {
    string = string.split(croatia[i]).join("1");
}
console.log(string.length);