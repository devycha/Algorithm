// 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다. 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다. 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다. 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다. 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.

// 전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때, 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.

// 제한사항
// 전체 학생의 수는 2명 이상 30명 이하입니다.
// 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
// 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
// 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
// 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
function solution(n, lost, reserve) {
    var answer = 0;
    let arr = [];
    for (let i = 0; i < n; i++) {
        arr.push(1);
    };
    lost.forEach((num) => {
        arr[num - 1] -= 1;
    })
    reserve.forEach((num) => {
        arr[num - 1] += 1;
    });
    arr.forEach((num, i) => {
        if (num == 2) {
            if (arr[i - 1] == 0) {
                arr[i - 1] += 1;
                arr[i] -= 1;
            } else if (arr[i + 1] == 0) {
                arr[i + 1] += 1;
                arr[i] -= 1;
            }
        }
    });
    arr.forEach((num) => {
        if (num > 0) {
            answer += 1;
        }
    })
    return answer;
}

// 완료 후 리펙토링

// 다른 사람의 풀이 해석
function solution(n, lost, reserve) {
    return n - lost.filter(a => {
            const b = reserve.find(r => Math.abs(r - a) <= 1) // 잃어버린 사람의 앞,뒤 번호 사람이 여분의 체육복이 있는 경우
            if (!b) return true // 여분의 체육복이 없으면 lost명단에 남겨두고 lost.filter에 true값을 보낸다.
            reserve = reserve.filter(r => r !== b) // 여분의 체육복을 빌려준 사람을 reserve명단에서 제외하는 filter함수
        }).length // lost명단에 속해있는 사람 수
}
// 전체명단수(n) - lost명단에 속해있는 사람 수(lost.filter(~~))