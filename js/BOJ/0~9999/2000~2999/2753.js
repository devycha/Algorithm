const fs = require('fs')
const year = fs.readFileSync('/dev/stdin', 'utf8')
console.log(year%4==0 ? (year%100==0 ? (year%400==0 ? 1 : 0) : 1) : 0)