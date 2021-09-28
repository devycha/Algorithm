function solution(orders, course) {
  var answer = [];
  let arr = [];
  let obj = {};
  let o = [];
  // 메뉴의 모든 종류를 배열에 담음
  orders.forEach(order => {
      let orderSplit = order.split('')
      o.push(orderSplit);
      orderSplit.forEach(menu => {
          if (!arr.includes(menu)) arr.push(menu)
      })
  })
  arr.sort();
  course.forEach(num => {
      let comb = [];
      // 모든 종류로 만들 수 있는 조합을 만드는데 course의 갯수에 따라서 반복함 2개셋트 3개셋트 4개셋트...
      comb.push(...getCombinations(arr, num));
      let maxCount = [];
      // 각각의 셋트요리가 orders를 돌면서 포함되어있는지 확인하고 그 카운트를 셈
      for (let i = 0; i < comb.length; i++) {
          let count = 0;
          for (let j = 0; j < o.length; j++) {
              if (check(o[j], comb[i])) count++
          }
          if (count >= 2) maxCount.push([comb[i].join(""), count])
      }
      
      // 셋트요리가 가장 많이 포함된 순으로 정렬
      maxCount.sort((a,b) => b[1] - a[1])
      // 셋트요리가 가장 많이 포함된 메뉴들만 추려서 배열에 넣음
      if (maxCount.length) {
          let max = maxCount[0][1];
          for (let i = 0; i < maxCount.length; i++) {
              if (maxCount[i][1] != max) break;
              else answer.push(maxCount[i][0])
          }
      }
      
  })
  // 셋트메뉴를 오름차순정렬
  return answer.sort()
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

function check(arr1, arr2) {
  for (let i = 0; i < arr2.length; i++) {
      if (!arr1.includes(arr2[i])) return false
  }
  return true;
}