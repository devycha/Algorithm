function selectionSort(arr) {
    for (let i = 0; i < arr.length-1; i++) {
        let min_position = i
        for (let j = i+1; j < arr.length; j++) {
            if (arr[j] <= arr[min_position]) {
                min_position = j
            }
        }
        let temp = arr[i]
        arr[i] = arr[min_position]
        arr[min_position] = temp
        console.log(arr)
    }
    return arr;
}

let arr = [5, 3, 4, 1, 2]
let arr2 = [34, 22, 10, 19, 17]

selectionSort(arr)
console.log('')
selectionSort(arr2)