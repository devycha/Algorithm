function solution(gems) {
    let left1 = 0;
    let right1 = gems.length-1;
    let left_fix = true
    let right_fix = true
    while (right_fix) {
        let right_word = gems[right1]
        if (right_fix) {
                if (gems.slice(left1, right1).includes(right_word)) right1--;
                else right_fix = false;
        }    
    }
    while (left_fix) {
        let left_word = gems[left1]
        if (left_fix) {
            if (gems.slice(left1+1, right1+1).includes(left_word)) left1++;
            else left_fix = false;
        }    
    }
    left_fix = true;
    right_fix = true;
    let left2 = 0;
    let right2 = gems.length-1;
    while (left_fix) {
        let left_word = gems[left2]
        if (left_fix) {
            if (gems.slice(left2+1, right2+1).includes(left_word)) left2++;
            else left_fix = false;
        }    
    }
    while (right_fix) {
        let right_word = gems[right2]
        if (right_fix) {
                if (gems.slice(left2, right2).includes(right_word)) right2--;
                else right_fix = false;
        }    
    }
    console.log(left1+1, right1+1)
    console.log(left2+1, right2+1)
    return right1-left1 <= right2-left2 ? [left1+1, right1+1] : [left2+1, right2+1]
}