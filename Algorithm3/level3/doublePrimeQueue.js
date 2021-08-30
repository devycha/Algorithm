// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/42628
function solution(operations) {
    var answer = [];
    let heap = [];
    operations.forEach((op) => {
        if (op[0] == "I") {
            let a = +op.split(' ')[1];
            if (heap[0] && heap[0] < a) heap.unshift(a);
            else heap.push(a);
        }
        if (op == "D 1") heap.shift();
        if (op == "D -1") heap.splice(heap.indexOf(Math.min(...heap)), 1);
    })
    return heap.length ? [heap[0], heap[heap.indexOf(Math.min(...heap))]] : [0, 0];
}

// 다른 사람 풀이 해석
function solution(operations) {
    // 값을 저장할 변수
    var values = [];

    for(var oper of operations) {
        var opers = oper.split(' ');

        switch(opers[0]) {
            case 'I' :
                // 값을 추가하고 정렬한다.
                values.push(opers[1] * 1);
                values.sort((l,r) => l - r);
                break;
            case 'D' :
                // 최댓값 혹은 최솟값을 제거한다.
                opers[1] === '1' ? values.pop() : values.shift();
                break;
        }
    }
    if(values.length) {
        return [ values[values.length - 1], values[0] ]
    }
    return [0,0]
}