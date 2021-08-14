// 정사각형 형태의 2차원 배열 N x N 의 인형 배열 board
// 0은 인형이 없는 것을 의미하고 나머지 자연수는 인형의 종류를 의미한다 1번인형 2번인형 ...
// moves 배열에 각 레인의 번호가 적혀있다.

// board
//         0 0 0 0 0
//         0 0 1 0 3
//         0 2 5 0 1
//         4 2 4 4 2
//         3 5 1 3 1
// moves   1,2,3,4,5(레인 번호)

// 크레인 작동 시 인형이 집어지지 않는 경우는 없으나 만약 인형이 없는 곳에서 크레인을 작동시키는 경우에는 아무런 일도 일어나지 않습니다. 또한 바구니는 모든 인형이 들어갈 수 있을 만큼 충분히 크다고 가정합니다. (그림에서는 화면표시 제약으로 5칸만으로 표현하였음)

// 게임 화면의 격자의 상태가 담긴 2차원 배열 board와 인형을 집기 위해 크레인을 작동시킨 위치가 담긴 배열 moves가 매개변수로 주어질 때, 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 return 하도록 solution 함수를 완성해주세요.

// [제한사항]
// board 배열은 2차원 배열로 크기는 "5 x 5" 이상 "30 x 30" 이하입니다.
// board의 각 칸에는 0 이상 100 이하인 정수가 담겨있습니다.
// 0은 빈 칸을 나타냅니다.
// 1 ~ 100의 각 숫자는 각기 다른 인형의 모양을 의미하며 같은 숫자는 같은 모양의 인형을 나타냅니다.
// moves 배열의 크기는 1 이상 1,000 이하입니다.
// moves 배열 각 원소들의 값은 1 이상이며 board 배열의 가로 크기 이하인 자연수입니다.

// 나의 풀이
function solution(board, moves) {
    var answer = 0;
    let arr = [];
    moves.forEach((num) => {
        for (let i = 0; i < board.length; i++) {
            if (board[i][num - 1]) {
                arr.push(board[i][num - 1]);
                board[i][num - 1] = 0;
                if (arr.length >= 2) {
                    if (arr[arr.length - 1] == arr[arr.length - 2]) {
                        arr.splice(arr.length - 2, 2);
                        answer += 2;
                    }
                }
                break;
            }
        }
    });
    console.log(arr);
    return answer;
}

// 후기: 2차원 배열이라고 문제에서 주어졌지만 처음에 문제를 잘 안읽고 풀다가 board[0]이 맨 아랫줄에 자리하는 것으로 착각하고 문제를 풀음
// 문제를 잘 읽자!! 쉬운문제일수록 문제를 잘 읽어야 시간을 단축시킬 수 있다.

// 다른 사람 풀이 해석
const transpose = matrix =>
    matrix.reduce(
        (result, row) => row.map((_, i) => [...(result[i] || []), row[i]]), []
    );

const solution = (board, moves) => {
    const stacks = transpose(board).map(row =>
        row.reverse().filter(el => el !== 0)
    );
    const basket = [];
    let result = 0;

    for (const move of moves) {
        const pop = stacks[move - 1].pop();
        if (!pop) continue;
        if (pop === basket[basket.length - 1]) {
            basket.pop();
            result += 2;
            continue;
        }
        basket.push(pop);
    }

    return result;
};

// 코드의 길이는 더 길지만 중요한 함수를 잘 섞어서 쓴 것 같다. spread나 filter reduce등 알고리즘에서 많이 쓰는 함수의 사용법에 대해서 더 익힐 수 있었다.