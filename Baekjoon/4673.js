let answer = Array(10000).fill(1)
let i = 0;
let num = 0;
while (i < 10000) {
    answer[num] = 0;
    i++;
    num = i + i.toString().split('').map(Number).reduce((sum, el) => sum + el);
}
for (let i = 0; i < answer.length; i++) {
    if (answer[i]) console.log(i);
}