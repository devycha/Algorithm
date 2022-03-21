const fs = require("fs");
let [N, ...meetings] = fs.readFileSync("input.txt").toString().split("\n");
meetings = meetings
  .map((meeting) => meeting.split(" ").map(Number))
  .sort((a, b) => {
    if (a[1] === b[1]) {
      return a[0] - b[0];
    }
    return a[1] - b[1];
  });

let start = 0;
let count = 0;
for (let i = 0; i < meetings.length; i++) {
  if (meetings[i][0] >= start) {
    start = meetings[i][1];
    count++;
  }
}
console.log(count);
