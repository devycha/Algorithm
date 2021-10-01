function same(arr1, arr2) {
  if (arr1.length !== arr2.length) {
    return false;
  }
  for (let i = 0; i < arr1.length; i++) {
    let num = arr1[i]**2;
    let isIncluded = false;
    for (let j = 0; j < arr2.length; j++) {
      if (arr2[j] == num) isIncluded = true;
    }
    if (!isIncluded) return false
  }
  return true;
}

function same2(arr1, arr2) {
  let obj = {};
  for (let i = 0; i < arr2.length; i++) {
    if(obj[arr2[i]]) obj[arr2[i]]++;
    else obj[arr2[i]] = 1;
  }
  for (let i = 0; i < arr1.length; i++) {
    if (!obj[arr1[i]**2]) return false
  }
  return true;
}

function validAnagram(str1, str2){
  if (str1.length !== str2.length) {
      return false
  }
  let obj = {};
  for(let i = 0; i < arr1.length; i++) {
    if (obj[str1[i]]) obj[str1[i]]++;
    else obj[str1[i]] = 1;
    if (obj[str2[i]]) obj[str2[i]]++;
    else obj[str2[i]] = 1;
  }
  for (let key in obj) {
      if (obj[key]%2) return false
  }
  return true;
  
}

let arr1 = [1, 2, 3, 4];
let arr2 = [4, 9, 1, 3];
console.log(same2(arr1, arr2))