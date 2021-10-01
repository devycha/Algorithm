function sumZero(arr) {
  for (let i = 0; i < arr.length-1; i++) {
    for (let j = i+1; j < arr.length; j++) {
      if (arr[i]+arr[j] == 0) return [arr[i], arr[j]]
    }
  }
  return -1;
}

function sumZero2(arr) {
  // left와 right를 배열의 양 끝 INDEX로 설정
  let left = 0
  let right = arr.length-1
  while (left < right) {
    let sum = arr[left] + arr[right]
    if (sum == 0) return [arr[left], arr[right]]
    if (sum < 0) left++
    if (sum > 0) right--
  }
  return -1;
}

function uniqueCount(arr) {
  let left = 0
  let right = 0;
  let count = 1;
  if (arr.length == 0) return 0;
  while (right < arr.length) {
    if (arr[left] !== arr[right]) {
      left = right
      count++
    }
    right++
  }
  return count;
}


let arr = []
console.log(sumZero2(arr))
console.log(uniqueCount(arr))