function continuousSum(arr, N) {
    let max = -Infinity
    for (let i = 0; i < arr.length - N + 1; i++) {
        let sum = 0;
        for (let j = i; j < i+N; j++) {
            sum += arr[j]
        }
        max = Math.max(max, sum)
    }
    return max
}
// let arr = [1, 2, 3, 4, 5, 6]
// console.log(continuousSum(arr, 2))

function continuousSum2(arr, N) {
    let sum = 0;
    for (let i = 0; i < N; i++) {
        sum += arr[i]
    }
    console.log(sum)
    let max = sum;
    console.log(max)
    for (let i = 1; i < arr.length-N+1; i++) {
        sum = sum - arr[i-1] + arr[i+N-1]
        max = Math.max(sum, max)
        console.log(sum ,max)
    }
    return max
}

let arr2 = [3, 2, 3, 7, 5, 6, 12, 3, 9, 18, 20, 13, 3, 3, 7]
console.log(continuousSum2(arr2, 4))