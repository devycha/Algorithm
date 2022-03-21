// 문제 설명
// https://programmers.co.kr/learn/courses/30/lessons/68645?language=javascript
function solution(n) {
    let answer = [];
    let arr1 = [];
    for (let i = 0; i < n; i++) {
        let arr2 = [];
        for (let j = 0; j <= i; j++) {
            arr2.push(0)
        }
        arr1.push(arr2);
    }
    arr1[0][0] = 1;
    let num = 1;
    let mode = 1;
    let count = n;
    let a = -1;
    let b = 0;
    while (count >= 1) {
        if (mode == 1) {
            for (let i = 0 ; i < count; i++) {
                arr1[a+1][b] = num++;
                a++;
            }
            mode = 2;
            count--;
        } else if (mode == 2) {
            for (let i = 0; i < count; i++) {
                arr1[a][b+1] = num++;
                b++;
            }
            mode = 3;
            count--;
        } else if (mode == 3) {
            for (let i = 0 ; i < count; i++) {
                arr1[a-1][b-1] = num++;
                a--;
                b--;
            }
            mode = 1;
            count--;
        }
    }
    arr1.forEach(arr => {
        answer.push(...arr)
    })
    return answer;
    
}
// cannot read property '0' of undefined 과 같은 error handle
// 1
let arr = [];
if (!arr[3]) {
    arr[3] = [];
}
arr[3][3] = 1
console.log(arr);
// 2 메소드를 이용한 초기화 방법
let a = Array(n).fill().map((a, i) => Array(m).fill()) 
////////////////////////////////////////////////////////////////
// 2차원 배열의 모든 요소들을 0으로 초기화하는 수고를 줄일 수 있음.
function solution(n) {
    let answer = [];
    let arr1 = [];
    let num = 1;
    let mode = 1;
    let count = n;
    let a = -1;
    let b = 0;
    while (count >= 1) {
        if (mode == 1) {
            for (let i = 0 ; i < count; i++) {
                if (!arr1[a+1]) arr1[a+1] = [];
                arr1[a+1][b] = num++;
                a++;
            }
            mode = 2;
            count--;
        } else if (mode == 2) {
            for (let i = 0; i < count; i++) {
                arr1[a][b+1] = num++;
                b++;
            }
            mode = 3;
            count--;
        } else if (mode == 3) {
            for (let i = 0 ; i < count; i++) {
                arr1[a-1][b-1] = num++;
                a--;
                b--;
            }
            mode = 1;
            count--;
        }
    }
    arr1.forEach(arr => {
        answer.push(...arr)
    })
    return answer;
    
}
// 다른 사람 풀이 해석
function solution(n) {
    let a = Array(n).fill().map((_, i) => Array(i + 1).fill())
    let row = -1
    let col = 0
    let fill = 0
    for (let i = n; i > 0; i -= 3) {
      a[++row][col] = ++fill
      for (let j = 0; j < i - 1; j++) a[++row][col] = ++fill
      for (let j = 0; j < i - 1; j++) a[row][++col] = ++fill
      for (let j = 0; j < i - 2; j++) a[--row][--col] = ++fill
    }
    return a.flat()
  }