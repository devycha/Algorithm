// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/84512?language=javascript
function solution(word) {
    let arr = ["A", "E", "I", "O", "U"];
    let a = "A"
    let count = 1;
    while (a != word) {
        if (a.length < 5) a += "A"
        else {
            let arr2 = a.split("");
            a = change(arr2);
        }
        count++;
    }
    return count;
}

function change(arr) {
    let arr1 = ["A", "E", "I", "O", "U"];
    for (let i = arr.length - 1; i >= 0; i--) {
        if (arr[i] != 'U') {
            let index = arr1.indexOf(arr[i]) + 1;
            return arr.slice(0, i).join("") + arr1[index];
        }
    }
    return "UUUUU"
}

// 다른 사람 풀이 해석
function solution(word) {
    return word.split('')
            .reduce((a,b,i) => a + ('AEIOU'.indexOf(b)) * (5**(5 - i) - 1) / 4 + 1, 0)
}