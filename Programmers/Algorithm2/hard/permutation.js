// 문제 설명
// 한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

// 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

// 제한사항
// numbers는 길이 1 이상 7 이하인 문자열입니다.
// numbers는 0~9까지 숫자만으로 이루어져 있습니다.
// "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
// 입출력 예
// numbers	return
// "17"	3
// "011"	2
// 나의 풀이
function solution(numbers) {
    let all = [];
    let answer =[];
    let arr = numbers.split('');
    for (let i = 1; i <= numbers.length; i++) {
        let permutationArr = getPermutations(arr, i).map(o => parseInt(o.join('')));
        permutationArr.forEach(num => {
            if (!all.includes(num)) all.push(num);
        })
    }
    all.forEach(num => {
        if (prime(num)) answer.push(num);
    })
    return answer.length;
}

const getPermutations= function (arr, selectNumber) {
  const results = [];
  if (selectNumber === 1) return arr.map((value) => [value]); 

  arr.forEach((fixed, index, origin) => {
    const rest = [...origin.slice(0, index), ...origin.slice(index+1)] 
    const permutations = getPermutations(rest, selectNumber - 1); 
    const attached = permutations.map((permutation) => [fixed, ...permutation]); 
    results.push(...attached); 
  });
  return results; 
};

function prime(num) {
    if (num < 2) return false;
        
    for (let i = 2; i*i <= num; i++) {
        if (num % i == 0) return false;
    }
    return true;
}

// 조합 구하기
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