function solution(s) {
    var answer = 0;
    let length = [];
    for (let i = 1; i <= s.length; i++) {
        let arr = [];
        for (let j = 0; j < s.length; j += i) {
            if (j + i > s.length - 1) {
                arr.push(s.substring(j, s.length))
            } else {
                arr.push(s.substring(j, j + i))
            }

        }
        length.push(rmDuplicationLength(arr))
    }
    return Math.min(...length);
}

function rmDuplicationLength(arr) {
    let s = '';
    let count = 1;
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] == arr[i + 1]) {
            count += 1;
        } else {
            if (count > 1) {
                s += count + arr[i];
            } else {
                s += arr[i];
            }
            count = 1;
        }
    }
    return s.length;
}
// 후기: 함수형 풀이로 힘겹게 풀었다... 문자열을 원하는 만큼 쪼개서 자유롭게 계산하는 것에 익숙해져야겠다.