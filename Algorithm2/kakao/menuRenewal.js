// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/72411?language=javascript
function solution(orders, course) {
    var answer = [];
    let allMenu = {};
    orders.forEach(order => {
        course.forEach(num => {
            let set = getCombinations(order.split(""), num);
            set.forEach(setMenu => {
                let menu = setMenu.sort().join("")
                if (allMenu[menu]) allMenu[menu]++;
                else allMenu[menu] = 1;
            })
        })
        
    })
    let best = {};
    course.forEach(num => {
        let bestMenu = [];
        let max = 2;
        for (let key in allMenu) {
            if (key.length == num) {
                if (allMenu[key] > max) {
                    max = allMenu[key];
                    bestMenu = [key]
                }
                if (allMenu[key] == max) {
                    bestMenu.push(key)
                }    
            }
        }
        bestMenu.forEach(menu => {
            if (!answer.includes(menu)) answer.push(menu)
        })
    })
    
    return answer.sort()
}

const getCombinations = function (arr, selectNumber) {
    const results = [];
    if (selectNumber === 1) return arr.map((value) => [value]);
  
    arr.forEach((fixed, index, origin) => {
      const rest = origin.slice(index + 1); 
      const combinations = getCombinations(rest, selectNumber - 1); 
      const attached = combinations.map((combination) => [fixed, ...combination]); 
      results.push(...attached); 
    });
  
    return results; 
  }
