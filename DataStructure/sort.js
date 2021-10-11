function builtInSort(arr) {
    function ascendSort(num1, num2) {
        return num1 - num2
    }
    function descendSort(num1, num2) {
        return num2 - num1
    }
    
    console.log(arr.sort(ascendSort))
    console.log(arr.sort((a,b) => a-b))
    
    console.log(arr.sort(descendSort))
    console.log(arr.sort((a,b) => b-a))
}

function bubbleSort(arr) {
    for (let i = arr.length; i > 0; i--) {
        for (let j = 0; j < i; j++) {
            if (arr[j] > arr[j+1]) {
                let temp = arr[j+1]
                arr[j+1] = arr[j]
                arr[j] = temp
            }
            console.log(arr, arr[j], arr[j+1])
        }
        
    }
    return arr
}

function bubbleSort2(arr) {
    let noSwaps;
    for (let i = arr.length; i > 0; i--) {
        noSwaps = true;
        for (let j = 0; j < i; j++) {
            if (arr[j] > arr[j+1]) {
                let temp = arr[j+1]
                arr[j+1] = arr[j]
                arr[j] = temp
                noSwaps = false;
            }
            console.log(arr, arr[j], arr[j+1], noSwaps)
        }
        if (noSwaps) break;
    }
    return arr
}

console.log(bubbleSort([3, 5, 2, 4, 1]))
console.log('end')
console.log(bubbleSort2([3, 5, 2, 4, 1]))