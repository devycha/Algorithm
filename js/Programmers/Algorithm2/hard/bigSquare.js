// https://programmers.co.kr/learn/courses/30/lessons/12905?language=javascript
// 나의 풀이
function solution(board)
{
    for (let i = 0; i < board.length; i++) {
        for (let j = 0; j < board[0].length; j++) {
            if (i == 0 || j == 0) continue;
            if (board[i][j]) {
                board[i][j] = Math.min(board[i-1][j-1], Math.min(board[i-1][j], board[i][j-1])) + 1;
            }
        }
    }
    let answer = 0;
    for (let i = 0; i < board.length; i++) {
        for (let j = 0; j < board[0].length; j++) {
            answer = Math.max(answer, board[i][j])
        }
    }
    return answer ** 2;
}

// 시행 착오 풀이
function solution(board)
{
    let answer = 0;
    let newBoard = board.slice();
    for (let i = 0; i < newBoard.length; i++) {
        if (i == 0) {
            newBoard[i] = board[i];
        } else {
            for (let j = 0; j < newBoard[0].length; j++) {
                if (board[i][j] == 0) newBoard[i][j] = 0;
                else {
                    newBoard[i][j] = board[i][j] + newBoard[i-1][j]
                }
            }
        }
    }
    newBoard.forEach(arr => {
        if (arr.includes(1)) answer = Math.max(answer, 1);
        let min = Math.min(...arr);
        let max = Math.max(...arr);
        for (let i = min ? min : 1; i <= max; i++) {
            let sequence = 0;
            for (let j = 0; j < arr.length; j++) {
                if (arr[j] >= i) sequence++;
                if (sequence >= i) {
                    answer = Math.max(answer, sequence);
                    break;
                }
                if (arr[j] < i) sequence = 0;
            }
        }
    })
    return answer**2;
}
// 위의 풀이는 테스트케이스는 모두 통과하지만 시간초과 혹은 파라미터 갯수의 초과로 런타임 에러가 일어났다.
// 후기: 다이나믹 프로그래밍 너무 어렵다....