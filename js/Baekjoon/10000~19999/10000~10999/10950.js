const fs = require('fs');
const [T, ...arr] = fs.readFileSync("./input.txt").toString().trim().split("\n")
arr.forEach(num => console.log(+num.split(" ")[0] + +num.split(" ")[1]))