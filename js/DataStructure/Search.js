function linearSearch(arr, find){
  for (let i = 0; i < arr.length; i++) {
      if (arr[i] == find) return i
  }
  return -1
}

function binarySearch(arr, find) {
  let start = 0;
  let end = arr.length-1;
  while (start <= end) {
    let mid = Math.floor((start+end)/2)
    if (arr[mid] > find) {
      end = mid-1
    } else if (arr[mid] < find) {
      start = mid+1
    } else {
      return mid
    }
  }
  return -1
}

const bbb = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
console.log(binarySearch(bbb, 10))

function naiveStringSearch(str, findString) {
  for (let i = 0; i < str.length; i++) {
    for (let j = 0; j < findString.length; j++) {
      if (str[i+j] != findString[j]) break;
      if (j == findString.length-1) return i
    }
  }
  return -1
}

const string = "wowomgwowomg"
console.log(naiveStringSearch(string, "omg"))