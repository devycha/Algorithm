// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/85002?language=javascript
function solution(weights, head2head) {
    let winRate = {};
    head2head.forEach((rec, i) => winRate[+i+1] = win(weights, rec, weights[i]))
    let keys = Object.keys(winRate);
    keys.sort((a,b) => {
        if (winRate[a].win == winRate[b].win) {
            if (winRate[b].heavy == winRate[a].heavy) {
                return winRate[b].weight - winRate[a].weight;
            } return winRate[b].heavy - winRate[a].heavy;
        } return winRate[b].win - winRate[a].win;
    })
    return keys.map(o=>+o);
}

function win(weights, record, myWeight) {
    let score = {"win": 0, "heavy": 0, "light": 0, "weight": myWeight};
    let play = 0;
    let win = 0;
    for (let i = 0; i < record.length; i++) {
        if (record[i] == "W") {
            if (weights[i] > myWeight) {
                score.heavy++
            } else {
                score.light++
            }
            win++;
            play++;
        }
        if (record[i] == 'L') play++;
    }
    score.win = win ? win/play : 0;
    return score;
}

// 다른 사람 풀이 해석
function solution(weights, head2head) {
    return head2head.map((l, i) => l.split('').reduce((acc, v, j) => {
                acc[0] += v === 'W' ? 1 : 0;
                acc[1] += v === 'W' ? weights[i] < weights[j] ? 1 : 0 : 0;
                acc[2] += v === 'L' ? 1 : 0
                return acc;
            }, [0, 0, 0])
            )
            .map((v) => [v[0] / (v[0] + v[2]), v[1]])
            .map((v, i) => [i + 1, {t: v[0], s: v[1], w : weights[i]}])
            .sort((a, b) => b[1].t - a[1].t || b[1].s - a[1].s || b[1].w - a[1].w || a[0] - b[0])
            .map((v) => v[0]);
}