// https://programmers.co.kr/learn/courses/30/lessons/67257?language=javascript
// 나의 풀이
function solution(expression) {
    let arr = [];
    let answer = [];
    let calc = ['*', '+', '-']
    let a = '';
    for (let i = 0; i < expression.length; i++) {
        if (i == expression.length-1) arr.push(parseInt(a+expression[i]));
        if (calc.includes(expression[i])) {
            arr.push(parseInt(a));
            a = '';
            arr.push(expression[i]);
        } else a += expression[i];
    }
    answer.push(w(arr.slice(), calc[0], calc[1], calc[2]));
    answer.push(w(arr.slice(), calc[1], calc[0], calc[2]));
    answer.push(w(arr.slice(), calc[1], calc[2], calc[0]));
    answer.push(w(arr.slice(), calc[2], calc[0], calc[1]));
    answer.push(w(arr.slice(), calc[2], calc[1], calc[0]));
    
    return Math.max(...answer);
}

const CALC = (a, b, calc) => calc == "*" ? a*b : calc=="+" ? a+b : a-b;

function w(arr, a, b, c) {
    
    while (arr.includes(a)) {
        let i = arr.indexOf(a);
        arr.splice(i-1, 3, CALC(arr[i-1], arr[i+1], a));
    }
    while (arr.includes(b)) {
        let i = arr.indexOf(b);
        arr.splice(i-1, 3, CALC(arr[i-1], arr[i+1], b));
    }
    while (arr.includes(c)) {
        let i = arr.indexOf(c);
        arr.splice(i-1, 3, CALC(arr[i-1], arr[i+1], c));
    }
    return Math.abs(arr[0]);
}
