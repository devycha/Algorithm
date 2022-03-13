const fs = require("fs");
const [N, ...rest] = fs.readFileSync("input.txt").toString().split("\n");

let arr = rest.map((spec) => {
  return spec.split(" ").map(Number);
});

let teamComposition = [];
let wholeTeam = Array.from({ length: +N }, (v, i) => i);
let min = Infinity;
perm([...wholeTeam], []);

teamComposition.forEach((team) => {
  let anotherTeam = [];
  for (let i = 0; i < wholeTeam.length; i++) {
    if (team.indexOf(wholeTeam[i]) == -1) {
      anotherTeam.push(wholeTeam[i]);
    }
  }

  let point1 = score(arr, team);
  let point2 = score(arr, anotherTeam);

  min = Math.min(min, Math.abs(point1 - point2));
});

console.log(min);

function perm(team, arr) {
  if (arr.length == +N / 2) return teamComposition.push(arr);
  if (team.length == 0) return;

  let pop = team.pop();
  perm([...team], [...arr, pop]);
  perm([...team], [...arr]);
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
