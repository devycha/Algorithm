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