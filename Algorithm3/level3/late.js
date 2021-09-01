// 1
function solution(n, works) {
    let result = [];
    works.sort((a,b) => b-a);
    let min = 999999999;
    let sum = works.reduce((a,b) => a+b);
    if (sum <= n) return 0;
    function recurse(time, left, fatigability) {
        console.log(fatigability);
        if (left.length == 0 || fatigability >= min) {
            min = Math.min(min, fatigability);
            return;
        } 
        if (time == 0) {
            let ful = 0;
            for (let i = 0; i < left.length; i++) {
                ful += left[i]**2;
            }
            min = Math.min(min, fatigability + ful);
            return;
        }
        let ex = time >= left[0] ? left[0] : time;
        for (let i = ex; i >= 0; i--) {
            if (i >= left[0]) {
                recurse(time-i, left.slice(1), fatigability)
            } else recurse(time-i, left.slice(1), fatigability + (left[0] - i)**2)
        }   
    }
    recurse(n, works, 0)
    return min;
}

// 2
function solution(n, works) {
    while (n > 0) {
        works.sort((a,b) => b-a)
        works[0] -= 1;
        if (works[0] == -1) return 0;
        n--;
    }
    return works.reduce((a,b,i) => {
        if (i == 1) return a**2 + b**2
        else return a + b**2;
    });
}
