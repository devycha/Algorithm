// 리팩터링
const re = require("./14889.js"); // ctrl + click
// 리팩터링 이유: 기존 방식은 팀의 가지수를 모두 구해서 그 가지수마다 또 짝지어진 팀을 또 구한 뒤에 점수를 합산했음.
// 이번 방법은 team1과 team2에 나누어서 분배하는 방법으로 두 팀 모두 인원이 찬 경우에 점수의 차이를 구해서 최솟값을 구함.
// 776 => 668

const fs = require("fs");
const [N, ...rest] = fs.readFileSync("input.txt").toString().split("\n");

let arr = rest.map((spec) => {
  return spec.split(" ").map(Number);
});

let teamComposition = [];
let wholeTeam = Array.from({ length: +N }, (v, i) => i);
let min = Infinity;
perm([...wholeTeam], [], []);

console.log(min);

function perm(team, team1, team2) {
  if (team1.length == +N / 2 && team2.length == +N / 2) {
    let team1Point = score(arr, team1);
    let team2Point = score(arr, team2);
    let difference = Math.abs(team1Point - team2Point);
    min = Math.min(min, difference);
    return;
  }
  if (team1.length == +N / 2) {
    perm([], team1, [...team2, ...team]);
  } else if (team2.length == +N / 2) {
    perm([], [...team1, ...team], team2);
  } else {
    let pop = team.pop();
    perm([...team], [...team1, pop], [...team2]);
    perm([...team], [...team1], [...team2, pop]);
  }
}

function score(scores, team) {
  let result = 0;
  for (let i = 0; i < team.length - 1; i++) {
    for (let j = i + 1; j < team.length; j++) {
      result += scores[team[i]][team[j]] + scores[team[j]][team[i]];
    }
  }
  return result;
}
