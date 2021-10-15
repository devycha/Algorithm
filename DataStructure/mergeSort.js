function mergeSort(arr) {
  if (arr.length <= 1) return arr;
  let mid = Math.floor(arr.length/2)
  let left = mergeSort(arr.slice(0, mid))
  let right = mergeSort(arr.slice(mid))
  return merge(left, right)
}


function merge(arr1, arr2) {
  let pointer1 = 0;
  let pointer2 = 0;
  let newArr = [];
  while (pointer1 <= arr1.length && pointer2 <= arr2.length) {
    if (pointer1 >= arr1.length) {
      newArr.push(...arr2.slice(pointer2))
      break;
    } else if (pointer2 >= arr2.length) {
      newArr.push(...arr1.slice(pointer1))
      break;
    }

    if (arr1[pointer1] >= arr2[pointer2]) {
      newArr.push(arr2[pointer2])
      pointer2++
    } else {
      newArr.push(arr1[pointer1])
      pointer1++
    }
  }
  return newArr
}

console.log(mergeSort([8, 3, 5, 4, 7, 6, 1, 2]))