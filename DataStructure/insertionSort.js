function insertSort(arr) {
  for (let i = 1; i < arr.length; i++) {
    let currentVal = arr[i]
    console.log("currentVal: ", currentVal)
    for (let j = i-1; j >= 0; j--) {
      if (arr[j] > currentVal) { // currentVal 보다 큰 경우
        arr[j+1] = arr[j]
        if (j == 0) { // 배열의 맨앞까지 온 경우
          arr[0] = currentVal
        }
      } else { // currentVal 보다 작은 경우
        arr[j+1] = currentVal
        break;
      }
      
    }
    console.log(arr)
  }
}

function insertionSort(arr){
	var currentVal;
    for(var i = 1; i < arr.length; i++){
        currentVal = arr[i];
        console.log(currentVal)
        for(var j = i - 1; j >= 0 && arr[j] > currentVal; j--) {
            arr[j+1] = arr[j]
        }
        arr[j+1] = currentVal;
        console.log(arr)
    }

    return arr;
}

let arr = [5, 3, 1, 4, 2]
insertSort(arr)