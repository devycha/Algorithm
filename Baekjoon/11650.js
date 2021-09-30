console.log(require('fs').readFileSync('./input.txt').toString().trim().split("\n").slice(1).sort((a,b)=>{
  let num1 = a.split(" ")
  let num2 = b.split(" ")
  if (num2[0] == num1[0]) return +num1[1] - +num2[1]
  return +num1[0] - num2[0]
}).join("\n"))