function three() {
  console.log(1)
}

function two() {
  console.log(2)
  three()
}

function one() {
  console.log(3)
  two()
}

one()

function recursiveSum(n) {
  // if (n == -10) return -10;
  return n + recursiveSum(n-1)
}

console.log(recursiveSum(10))


function capitalizeFirst (arr) {
  if (arr.length == 1) return [arr[0][0].toUpperCase() + arr[0].substr(1, arr[0].length)]
  return [arr[0][0].toUpperCase() + arr[0].substr(1, arr[0].length), ...capitalizeFirst(arr.slice(1))]
}

console.log(capitalizeFirst(['car', 'taco', 'banana']))

function nestedEvenSum (obj, sum = 0) {
  for (let key in obj) {
    if (typeof(obj[key]) == 'object') sum += nestedEvenSum(obj[key])
    if (Number.isInteger(obj[key]) && !(obj[key]%2)) {
      sum += obj[key]
    }
  }
  return sum
}

var obj1 = {
  outer: 2,
  obj: {
    inner: 2,
    otherObj: {
      superInner: 2,
      notANumber: true,
      alsoNotANumber: "yup"
    }
  }
}
var obj2 = {
  a: 2,
  b: {b: 2, bb: {b: 3, bb: {b: 2}}},
  c: {c: {c: 2}, cc: 'ball', ccc: 5},
  d: 1,
  e: {e: {e: 2}, ee: 'car'}
};

console.log(nestedEvenSum(obj1))
console.log(nestedEvenSum(obj2))



nestedEvenSum(obj1); // 6
nestedEvenSum(obj2); // 10

function capitalizeWords (arr) {
  if(arr.length == 1) return [arr[0].toUpperCase()]
  return [arr[0].toUpperCase(), ...capitalizeWords(arr.slice(1))]
}

let words = ['i', 'am', 'learning', 'recursion'];
capitalizedWords(words); // ['I', 'AM', 'LEARNING', 'RECURSION']

function stringifyNumbers(obj) {
  let answer = {};
  for (let key in obj) {
    if (typeof(obj[key]) == 'number') {
      answer[key] = obj[key].toString()
    }
    else if (typeof(obj[key]) == 'object' && !Array.isArray(obj[key])) {
        answer[key] = stringifyNumbers(obj[key])
    } else {
      answer[key] = obj[key]
    }
  }
  return answer
}

let obj = {
    num: 1,
    test: [],
    data: {
        val: 4,
        info: {
            isRight: true,
            random: 66
        }
    }
}


console.log(stringifyNumbers(obj))
// console.log(typeof(stringifyNumbers(obj).num))

function collectStrings(obj) {
  let arr = [];
  for (let key in obj) {
    if (typeof(obj[key]) == "string") arr.push(obj[key])
    else {
      if (typeof(obj[key]) == 'object') {
        arr.push(...collectStrings(obj[key]))
      }
    }
  }
  return arr
}

const obj = {
  stuff: "foo",
  data: {
      val: {
          thing: {
              info: "bar",
              moreInfo: {
                  evenMoreInfo: {
                      weMadeIt: "baz"
                  }
              }
          }
      }
  }
}

console.log(collectStrings(obj)) // ["foo", "bar", "baz"])

