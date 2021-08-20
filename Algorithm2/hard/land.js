// https://programmers.co.kr/learn/courses/30/lessons/12913?language=javascript
// 나의 풀이
function solution(land) {
    let answer = land;
    for (let i = 1; i < answer.length; i++) {
        land[i].forEach((a, j) => {
            if (j == 0) answer[i][j] += Math.max(land[i-1][1], land[i-1][2], land[i-1][3])
            if (j == 1) answer[i][j] += Math.max(land[i-1][0], land[i-1][2], land[i-1][3])
            if (j == 2) answer[i][j] += Math.max(land[i-1][0], land[i-1][1], land[i-1][3])
            if (j == 3) answer[i][j] += Math.max(land[i-1][0], land[i-1][1], land[i-1][2])
        })
    }
    return Math.max(...answer[answer.length-1]);
}

// 풀이 2
function solution(land) {
    let answer = land;
    for (let i = 1; i < answer.length; i++) {
        land[i].forEach((a, j) => {
            if (j == 0) answer[i][j] = a + Math.max(land[i-1][1], land[i-1][2], land[i-1][3])
            if (j == 1) answer[i][j] = a + Math.max(land[i-1][0], land[i-1][2], land[i-1][3])
            if (j == 2) answer[i][j] = a + Math.max(land[i-1][0], land[i-1][1], land[i-1][3])
            if (j == 3) answer[i][j] = a + Math.max(land[i-1][0], land[i-1][1], land[i-1][2])
        })
    }
    return Math.max(...answer[answer.length-1]);
}

// 후기 += 을 쓰면 시간은 전체적으로 줄어들지만 효율성이 떨어지는 결과가 나왔고
//  = a + 를 통해서 이미 있는 변수를 써주게 되면 효율성은 좋아지지만 시간이 좀더 걸리는 결과가 나오게 되었다
// 다른 사람 풀이 해석 : 풀이에 대한 접근 방법이 모두 비슷해서 생략하겠다.
