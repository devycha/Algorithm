// 수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

// 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
// 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
// 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

// 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.

// 제한 조건
// 시험은 최대 10,000 문제로 구성되어있습니다.
// 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
// 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
// 입출력 예
// answers	return
// [1,2,3,4,5]	[1]
// [1,3,2,4,2]	[1,2,3]
// 입출력 예 설명
// 입출력 예 #1

// 수포자 1은 모든 문제를 맞혔습니다.
// 수포자 2는 모든 문제를 틀렸습니다.
// 수포자 3은 모든 문제를 틀렸습니다.
// 따라서 가장 문제를 많이 맞힌 사람은 수포자 1입니다.

// 입출력 예 #2

// 모든 사람이 2문제씩을 맞췄습니다.

// 나의 풀이
const answer = function(answer_arr, test_arr) {
    let count = 0;
    answer_arr.forEach((num, i) => {
        if (num == test_arr[i % test_arr.length]) {
            count += 1;
        }
    });
    return count;
}

function solution(answers) {
    let maxPoint = [];
    let point = []
    let first = [1, 2, 3, 4, 5];
    let second = [2, 1, 2, 3, 2, 4, 2, 5];
    let third = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];

    let point1 = answer(answers, first);
    let point2 = answer(answers, second);
    let point3 = answer(answers, third);

    point.push(point1);
    point.push(point2);
    point.push(point3);

    let max = Math.max(point1, point2, point3);

    point.forEach((num, i) => {
        if (num == max) {
            maxPoint.push(i + 1);
        }
    })

    return maxPoint;
}

// 완료 후 리펙토링 answer 함수를 filter함수를 통해 간략화
function solution(answers) {
    let answer = [];

    let first = [1, 2, 3, 4, 5];
    let second = [2, 1, 2, 3, 2, 4, 2, 5];
    let third = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];

    let p1 = answers.filter((num, i) => num === first[i % first.length]).length;
    let p2 = answers.filter((num, i) => num === second[i % second.length]).length;
    let p3 = answers.filter((num, i) => num === third[i % third.length]).length;

    let max = Math.max(p1, p2, p3);
    if (max == p1) { answer.push(1); }
    if (max == p2) { answer.push(2); }
    if (max == p3) { answer.push(3); }

    return answer;
}

// 후기: 함수형 풀이도 괜찮다고 생각했지만 primitive함수 filter를 이용한 방법이 수행시간이 더 짧았다.

// 다른 사람 풀이 해석
function solution(answers) {
    var answer = [];
    const man1 = [1, 2, 3, 4, 5];
    const man2 = [2, 1, 2, 3, 2, 4, 2, 5];
    const man3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];
    let count = [0, 0, 0];

    for (let i = 0; i < answers.length; i++) {
        if (answers[i] == man1[i % man1.length]) count[0]++;
        if (answers[i] == man2[i % man2.length]) count[1]++;
        if (answers[i] == man3[i % man3.length]) count[2]++;
    }

    const max = Math.max(count[0], count[1], count[2]);
    for (let i = 0; i < count.length; i++) {
        if (max == count[i]) answer.push(i + 1);
    }

    return answer;
}