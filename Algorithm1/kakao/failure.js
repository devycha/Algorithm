// 전체 스테이지의 개수 N, 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages가 매개변수로 주어질 때, 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return 하도록 solution 함수를 완성하라.

// 제한사항
// 스테이지의 개수 N은 1 이상 500 이하의 자연수이다.
// stages의 길이는 1 이상 200,000 이하이다.
// stages에는 1 이상 N + 1 이하의 자연수가 담겨있다.
// 각 자연수는 사용자가 현재 도전 중인 스테이지의 번호를 나타낸다.
// 단, N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
// 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
// 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
// 입출력 예
// N	stages	result
// 5	[2, 1, 2, 6, 2, 4, 3, 3]	[3,4,2,1,5]
// 4	[4,4,4,4,4]	                [4,1,2,3]

// 나의 풀이
function solution(N, stages) {
    let answer = [];
    let clear = [];
    let fail = {};
    let failRank = {};
    stages.forEach((num) => {
        for (let i = 0; i < num; i++) {
            if (!clear[i]) {
                clear.push(1);
            } else {
                clear[i] += 1;
            }

        }
    })
    for (let i = 0; i < N; i++) {
        fail[`${i+1}`] = (clear[i + 1] > 0) ? (clear[i] - clear[i + 1]) / clear[i] : (clear[i] - 0) / clear[i];
    }
    failRank = Object.entries(fail).sort(([, a], [, b]) => b - a);
    failRank.forEach(([num, ]) => {
        answer.push(parseInt(num));
    })

    return answer;
}

// 후기: 문제푸는데 시간이 굉장히 오래 걸렸다. 기존에 존재하는 primitive 함수에 대한 의존도가 높은 것 같다.
// 다른 사람 풀이 해석: 다른 사람들의 풀이 또한 마찬가지로 수식이 굉장히 길다. for문의 중첩으로 지연시간도 오래 걸렸다.
function solution(N, stages) {
    let records = [];
    let i;
    for (i = 0; i < N + 1; i++) records.push([0, 0, i + 1]); //수, 실패율, 스테이지
    stages.forEach(num => records[num - 1][0]++);
    let ppl = 0;
    for (i = 0; i < N + 1; i++) {
        records[i][1] = records[i][0] / (stages.length - ppl);
        ppl += records[i][0];
    }
    return records.slice(0, N).sort((a, b) => b[1] - a[1]).map(el => el[2]);
}