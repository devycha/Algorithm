const fs = require('fs');
const [N, M] = fs.readFileSync("./input.txt").toString().trim().split(" ").map(Number)
let arr = [];
for (let i = 1; i <= N; i++) {
    arr.push(i)
}
back(arr, [], M)

function back(arr1, arr2, M) {
    if (arr2.length == M) {
        return console.log(arr2.join(" "))
    }
    if (arr1.length == 0) return;
    for (let i = 0; i < arr1.length; i++) {
        back([...arr1.slice(i)], [...arr2, arr1[i]], M)
    }
}
