// 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.

// 예를 들어 array가 [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3이라면

// array의 2번째부터 5번째까지 자르면 [5, 2, 6, 3]입니다.
// 1에서 나온 배열을 정렬하면 [2, 3, 5, 6]입니다.
// 2에서 나온 배열의 3번째 숫자는 5입니다.

// 나의 풀이
function solution(array, commands) {
    var answer = [];
    commands.forEach((arr, i) => {
        let arr1 = array.slice(arr[0] - 1, arr[1]).sort((a, b) => a - b);
        answer.push(arr1[arr[2] - 1]);

    });
    return answer;
}

// 후기: javascript  Array prototype의 sort함수는 기본으로 문자열 기준으로 정렬한다 따라서 리턴값으로 옵션을 넣어줘야 숫자를 기준으로 정렬이 가능하다.