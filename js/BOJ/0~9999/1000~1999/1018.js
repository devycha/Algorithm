const fs = require('fs');
const arr = fs.readFileSync('../input.txt', 'utf8').split("\n")
let chess = [];
arr.pop()
for (let i = 1; i < arr.length; i++) {
    let line = [];
    for (let j = 0; j < arr[i].length; j++) {
        if (arr[i][j] == 'W') line.push(0)
        else line.push(1)
    }
    chess.push(line)
}
let min = 32;
for (let i = 0; i <= chess.length-8; i++) {
    for (let j = 0; j <= chess[0].length-8; j++) {
        let count1 = 0;
        let count2 = 0;
        for (let a = i; a < i+8; a++) {
            for (let b = j; b < j+8; b++) {
                if ((a+b)%2) {
                    if (chess[a][b] == 0) count1++;
                    else count2++;
                } else {
                    if (chess[a][b] == 1) count1++;
                    else count2++;
                }
            }
        }
        min = Math.min(min, Math.min(count1, count2))
    }
}
console.log(min)