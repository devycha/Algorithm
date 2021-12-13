// https://programmers.co.kr/learn/courses/30/lessons/86971?language=javascript
// 포스팅 완료
function solution(n, wires) {
    let min = Infinity;
    
    for (let i = 0; i < wires.length; i++) {
        let obj = {};
        let arr = [];
        let wiretop = [];
        
        arr = [...wires.slice(0, i), ...wires.slice(i+1)]
        arr.forEach(arrow => {
            if (!obj[arrow[0]]) obj[arrow[0]] = [];
            obj[arrow[0]].push(arrow[1])
            if (!obj[arrow[1]]) obj[arrow[1]] = [];
            obj[arrow[1]].push(arrow[0])
        })
        
        for (let key in obj) {
            wiretop.push(key)
        }
        let count = 0;
        let split_wires = count_wire(wiretop, obj)
        if (split_wires.length == 1) {
            count = split_wires[0] - 1;
        } else {
            count = Math.abs(split_wires[0] - split_wires[1])
        }
        if (min > count) min = count
        // console.log(i+1, obj, count_wire(wiretop, obj))
        // console.log(obj)
        // console.log()
    }
    return min;
}

function count_wire(arr, obj) {
    // console.log(arr)
    let count = [];
    let check = {};
    arr.forEach(wire => {
        if (!obj[wire]) return count.push(1)
        if (check[wire] != 1) {
            let stack = [wire];
            check[wire] = 1;
            let c = 0;
            while (stack.length) {
                c++;
                let pop = stack.pop();
                if (obj[pop]) {
                    obj[pop].forEach(a => {
                        if (!check[a]) {
                            check[a] = 1;
                            stack.push(a)    
                        }
                    })    
                }
            }
            count.push(c)
        }
    })
    return count
}

// 리팩토링
function solution(n, wires) {
    let min = Infinity;
    
    for (let i = 0; i < wires.length; i++) {
        let obj = {};
        let arr = [];
        
        arr = [...wires.slice(0, i), ...wires.slice(i+1)]
        arr.forEach(arrow => {
            if (!obj[arrow[0]]) obj[arrow[0]] = [];
            obj[arrow[0]].push(arrow[1])
            if (!obj[arrow[1]]) obj[arrow[1]] = [];
            obj[arrow[1]].push(arrow[0])
        })
    
        let count = 0;
        let split_wires = count_wire(obj)
        if (split_wires.length == 1) {
            count = split_wires[0] - 1;
        } else {
            count = Math.abs(split_wires[0] - split_wires[1])
        }
        if (min > count) min = count
        
    }
    return min;
}

function count_wire(obj) {
    let count = [];
    let check = {};
    
    for (let wire in obj) {
        if (check[wire] != 1) {
            let stack = [wire];
            check[wire] = 1;
            let c = 0;
            while (stack.length) {
                c++;
                let pop = stack.pop();
                if (obj[pop]) {
                    obj[pop].forEach(a => {
                        if (!check[a]) {
                            check[a] = 1;
                            stack.push(a)    
                        }
                    })    
                }
            }
            count.push(c)
        }
    }
    return count
}