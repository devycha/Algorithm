// const fs = require('fs');
// const arr = fs.readFileSync('../input.txt', 'utf8').split('\r\n').map(o=>+o)
// console.log(x > 0 ? y > 0 ? '1' : '4' : y > 0 ? '2' : '3')

const rl = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout
})

let input = [];
rl.on('line', (line) => {
  input.push(parseInt(line));
}).on('close', () => {
  const [x, y] = input;
  console.log(x > 0 ? y > 0 ? '1' : '4' : y > 0 ? '2' : '3')
  process.exit()
})