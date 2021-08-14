// 문제 설명: 뉴스 클러스터링
// https://programmers.co.kr/learn/courses/30/lessons/17677?language=javascript
// 나의 풀이
function solution(str2, str1) {
    var answer = 0;
    let arr1 = [];
    let arr2 = [];
    
    let obj1 = {};
    let obj2 = {};
    
    let A = str1.toUpperCase().split('');
    let B = str2.toUpperCase().split('');
    
    // 교집합, 합집합 갯수
    let intersection = 0;
    let union = 0;
    
    // 입력형식에 의거하여 문자열을 배열에 추가
    for (let i = 0 ; i < A.length-1; i++) {
     if (A[i].match(/[A-Z]/g) && A[i+1].match(/[A-Z]/g)) arr1.push(A.slice(i, i+2).join('').toUpperCase())
    }
    for (let i = 0 ; i < B.length-1; i++) {
     if (B[i].match(/[A-Z]/g) && B[i+1].match(/[A-Z]/g)) arr2.push(B.slice(i, i+2).join('').toUpperCase())
    }
    
    // 객체를 이용하여 중복 갯수 파악
    arr1.forEach(word => {
        if (obj1[word]) obj1[word] += 1;
        else obj1[word] = 1;
    })
    
    arr2.forEach(word => {
        if (obj2[word]) obj2[word] += 1;
        else obj2[word] = 1;
    })
    // 교집합 합집합의 갯수 판단
    for (let key in obj1) {
        if (obj2[key]) {
            intersection += Math.min(obj1[key], obj2[key])
            union += Math.max(obj1[key], obj2[key])
            
        }
        else {
            union += obj1[key]
        }
    }
    for (let key in obj2) {
        if (!obj1[key]) union += obj2[key];
    }
    
    
    // 리턴
    return union ? Math.floor(intersection/union*65536) : 65536;
}
// 후기: 문제 설명이 간결하게 나와있기 때문에 꼼꼼하게 문제 조건을 더 잘 파악해야겠다.
// 시작부터 특수문자, 공백, 숫자를 지우고 시작해버려서 테스트케이스 몇개를 틀리게 되었다.