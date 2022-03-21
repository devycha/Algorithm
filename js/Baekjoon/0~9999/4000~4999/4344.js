const fs = require('fs');
const [n, ...input] = fs.readFileSync('../input.txt').toString().trim().split('\n');
for (let i = 0; i < input.length; i++) {
    let [count, ...num] = input[i].split(' ').map(Number);
    let avg = num.reduce((sum, el) => sum + el) / count;
    console.log((num.filter((a) => a > avg).length * 100 / count).toFixed(3) + "%");
}