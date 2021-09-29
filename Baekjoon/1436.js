const fs = require('fs');
const num = +fs.readFileSync('../input.txt', 'utf8')
let i = 1;
let count = 0;
let result = [];
while (count < num) {
    if (i.toString().search("666") != -1) {
        result.push(i)
        count++;
    }
    i++;
}
console.log(result[num-1])