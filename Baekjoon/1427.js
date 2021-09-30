console.log(+require('fs').readFileSync('./input.txt').toString().trim().split("").sort((a,b) => +b - +a).join(""))
