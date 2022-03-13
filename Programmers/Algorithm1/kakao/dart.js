// 다트 게임은 총 3번의 기회로 구성된다.
// 각 기회마다 얻을 수 있는 점수는 0점에서 10점까지이다.
// 점수와 함께 Single(S), Double(D), Triple(T) 영역이 존재하고 각 영역 당첨 시 점수에서 1제곱, 2제곱, 3제곱 (점수1 , 점수2 , 점수3 )으로 계산된다.
// 옵션으로 스타상(*) , 아차상(#)이 존재하며 스타상(*) 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배로 만든다. 아차상(#) 당첨 시 해당 점수는 마이너스된다.
// 스타상(*)은 첫 번째 기회에서도 나올 수 있다. 이 경우 첫 번째 스타상(*)의 점수만 2배가 된다. (예제 4번 참고)
// 스타상(*)의 효과는 다른 스타상(*)의 효과와 중첩될 수 있다. 이 경우 중첩된 스타상(*) 점수는 4배가 된다. (예제 4번 참고)
// 스타상(*)의 효과는 아차상(#)의 효과와 중첩될 수 있다. 이 경우 중첩된 아차상(#)의 점수는 -2배가 된다. (예제 5번 참고)
// Single(S), Double(D), Triple(T)은 점수마다 하나씩 존재한다.
// 스타상(*), 아차상(#)은 점수마다 둘 중 하나만 존재할 수 있으며, 존재하지 않을 수도 있다.
// 0~10의 정수와 문자 S, D, T, *, #로 구성된 문자열이 입력될 시 총점수를 반환하는 함수를 작성하라.

// 입력 형식
// "점수|보너스|[옵션]"으로 이루어진 문자열 3세트.
// 예) 1S2D*3T

// 점수는 0에서 10 사이의 정수이다.
// 보너스는 S, D, T 중 하나이다.
// 옵선은 *이나 # 중 하나이며, 없을 수도 있다.
// 출력 형식
// 3번의 기회에서 얻은 점수 합계에 해당하는 정수값을 출력한다.
// 예) 37

// 입출력 예제
// 예제	dartResult	answer	설명
// 1	1S2D*3T	37	11 * 2 + 22 * 2 + 33
// 2	1D2S#10S	9	12 + 21 * (-1) + 101
// 3	1D2S0T	3	12 + 21 + 03
// 4	1S*2T*3S	23	11 * 2 * 2 + 23 * 2 + 31
// 5	1D#2S*3S	5	12 * (-1) * 2 + 21 * 2 + 31
// 6	1T2D3D#	-4	13 + 22 + 32 * (-1)
// 7	1D2S3T*	59	12 + 21 * 2 + 33 * 2

// 나의 풀이
function solution(dartResult) {
    var answer = 0;
    let start = 0;
    let arr = [];
    for (let i = 0; i < dartResult.length; i++) {
        if (dartResult[i] == 'S' || dartResult[i] == 'D' || dartResult[i] == 'T' || dartResult[i] == '*' || dartResult[i] == '#') {
            arr.push(dartResult.slice(start, i + 1));
            start = i + 1;
        }
    }

    arr.forEach((str, i) => {
        if (str.length !== 1) {
            arr.splice(i, 1, parseInt(str.slice(0, -1)) ** (str[str.length - 1] == 'S' ? 1 : str[str.length - 1] == 'D' ? 2 : 3));
        }
    });

    console.log(arr);
    arr.forEach((point, i) => {
        if (point == '*') {
            arr[i - 1] *= 2;
            if (!Number.isInteger(arr[i - 2])) {
                if (arr[i - 3]) arr[i - 3] *= 2;
            } else {
                if (arr[i - 2]) arr[i - 2] *= 2;
            }
        } else if (point == '#') {
            if (arr[i - 1]) arr[i - 1] *= -1;
        }
    })
    arr.forEach((point) => {
        if (Number.isInteger(point)) answer += point;
    })
    console.log(arr);
    return answer;
}
// 후기 : 정규식표현 \d 숫자인것, \D 숫자 아닌것, 
// 다른 사람 풀이 해석
function solution(dartResult) {
    const bonus = { 'S': 1, 'D': 2, 'T': 3 },
        options = { '*': 2, '#': -1, undefined: 1 };

    let darts = dartResult.match(/\d.?\D/g);

    for (let i = 0; i < darts.length; i++) {
        // 정규표현식에서 다음과 같이 조건에 괄호 ()를 묶어주면 각각 조건에 맞는 인자가 따로따로 배열에 들어가게 되는 꿀팁을 알게 되었다.
        let split = darts[i].match(/(^\d{1,})(S|D|T)(\*|#)?/),
            score = Math.pow(split[1], bonus[split[2]]) * options[split[3]];

        if (split[3] === '*' && darts[i - 1]) darts[i - 1] *= options['*'];
        darts[i] = score;
    }

    return darts.reduce((a, b) => a + b);
}