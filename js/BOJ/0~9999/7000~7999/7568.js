// 1
const fs = require("fs");
const N = fs
  .readFileSync("./input.txt", "utf8")
  .split("\n")
  .map((o) => o.split("\r")[0])
  .map((o) => o.split(" "));
console.log(N);
let result = [];
for (let i = 1; i < N.length; i++) {
  let sum = 1;
  for (let j = 1; j < N.length; j++) {
    if (i == j) continue;
    if (+N[i][0] > +N[j][0] && +N[i][1] > +N[j][1]) sum++;
  }
  result.push(sum);
}
console.log(result.join(" "));

// 2
const fs = require("fs");
let input = fs.readFileSync("/dev/stdin").toString().split("\n");
let count = input[0];
let people = [];
let rank = [];

for (let i = 1; i <= count; i++) {
  let person = {};
  person["x"] = input[i].split(" ")[0] * 1;
  person["y"] = input[i].split(" ")[1] * 1;
  people.push(person);
}

for (let i = 0; i < people.length; i++) {
  rank.push(
    people.reduce((sum, p) => {
      if (p.x > people[i].x && p.y > people[i].y) {
        return ++sum;
      } else {
        return sum;
      }
    }, 0) + 1
  );
}

console.log(rank.join(" "));

// 3
// let fs = require('fs');
// let input = fs.readFileSync('/dev/stdin').toString().split('\n');
// let N = parseInt(input.shift());

// function solution(){
//   let result = []
//   let cur, temp;
//   let max = 0;

//   for(let i = 0; i < N; i++){
//     cur = input[i].split(' ');

//     for(let j = 0; j < N; j++){
//       if(i !== j){
//         temp = input[j].split(' ');
//         if(temp[0] > cur[0] && temp[1] > cur[1]){
//           max++;
//         }
//       }
//     }
//     result.push(max+1);
//     max = 0;
//   }
//   console.log(result.join(' '));
// }

// solution()
