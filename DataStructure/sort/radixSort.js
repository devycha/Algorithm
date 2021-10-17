function getDigit(num, i) {
  return Math.floor(Math.abs(num) / (10**i)) % 10;
}

function digitCount(num) {
  return num ? Math.floor(Math.log10(Math.abs(num))) + 1 : 1;
}

function maxDigit(nums) {
  let max = 0;
  for (let i = 0; i < nums.length; i++) {
    max = Math.max(max, digitCount(nums[i]))
  }
  return max
}

function radixSort(nums) {
  let maxCipher = maxDigit(nums); // 가장 큰 자릿수 구하기
  for (let i = 0; i < maxCipher; i++) { // 1의자릿수 부터 가장 큰 자릿수까지 반복
    let digitBuckets = Array.from({length: 10}, () => []) // 10개의 서로다른 배열 생성
    for(let j = 0; j < nums.length; j++) {
      let digit = getDigit(nums[j], i) // i번째 자리에 있는 수 받아오기
      digitBuckets[digit].push(nums[j]) // 10개의 배열 중 i번째 자리에 있는 수 배열에 push
    }
    nums = [].concat(...digitBuckets) // 10개의 배열을 순서대로 합치기
  }
  return nums // 정렬된 배열 리턴
}

console.log(radixSort([1556, 4, 3556, 593, 408, 3486, 902, 7, 8157, 86, 9637, 29]))