function binarySearch(arr, N) {
    let start = 0;
    let end = arr.length - 1;
    let i = 1;
    while (start <= end) {
        console.log(i++)
        let mid = Math.floor((start+end)/2);
        if (arr[mid] < N) {
            start = mid+1
        } else if (arr[mid] > N) {
            end = mid-1
        } else if (arr[mid] === N) {
            return mid
        }
    }
    return -1;
}

function linearSearch(arr, N) {
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] === N) return i
    }
    return -1;
}

let arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
console.log(binarySearch(arr, 4))