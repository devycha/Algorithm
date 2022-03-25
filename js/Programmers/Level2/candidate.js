function solution(relation) {
    let arr = [];
    for (let i = 0; i < relation[0].length; i++) {
        arr.push(i)
    }
    // console.log(arr)
    let keys = [];
    for (let i = 1; i <= relation[0].length; i++) {
        keys.push(...getCombinations(arr, i))    
    }
    // console.log(keys)
    let answer = [];
    for (let i = 0; i < keys.length; i++) {
        let obj = {};    
        for (let k = 0; k < relation.length; k++) {
            let word = ""
            for (let j = 0; j < keys[i].length; j++) {
                word += relation[k][keys[i][j]]
            }
            // console.log(word)
            if (obj[word]) break;
            obj[word] = 1;
            if (k == relation.length-1) answer.push(keys[i])
        }
    }
    // console.log(answer)
    let cand = [];
    cand.push(answer[0])
    answer.forEach(key => {
        for (let i = 0; i < cand.length; i++) {
            if (!candable(cand[i], key)) break;
            if (i == cand.length-1) cand.push(key)
        }
    })
    // console.log(cand)
    return cand.length;             
}

const getCombinations = function (arr, selectNumber) {
    const results = [];
    if (selectNumber === 1) return arr.map((value) => [value]); // 1개씩 택할 때, 바로 모든 배열의 원소 return
  
    arr.forEach((fixed, index, origin) => {
      const rest = origin.slice(index + 1); // 해당하는 fixed를 제외한 나머지 뒤
      const combinations = getCombinations(rest, selectNumber - 1); // 나머지에 대해서 조합을 구한다.
      const attached = combinations.map((combination) => [fixed, ...combination]); //  돌아온 조합에 떼 놓은(fixed) 값 붙이기
      results.push(...attached); // 배열 spread syntax 로 모두다 push
    });
  
    return results; // 결과 담긴 results return
}

const candable = function (arr1, arr2) {
    // console.log(arr1, arr2)
    for (let i = 0; i < arr1.length; i++) {
        // console.log(arr2.includes(arr1[i]), arr1, arr2)
        if (!arr2.includes(arr1[i])) return true
        if (arr2.includes(arr1[i]) && i == arr1.length-1) return false
    }
    return true
}