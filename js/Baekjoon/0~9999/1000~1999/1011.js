const [n, ...input] = require('fs').readFileSync('../input.txt').toString().trim().split('\n');
input.forEach(a => {
    let [s, e] = a.split(' ').map(Number);
    let answer = [0];
    for (let i = 1; i**2 <= e-s; i++) {
        answer.push(i**2)
    }
    if (answer.indexOf(e-s) == -1) console.log(2*answer.length - 1);
    else console.log(2*answer.indexOf(e-s)-1);
});

// let answer = [0];
// for (let i = 1; i**2 <= 100; i++) {
//     answer.push(i**2)
// }
// console.log(answer);

5
0 1 4 9 

1


