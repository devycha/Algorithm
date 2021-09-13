// 문제 설명 (포스팅 완료)
// https://programmers.co.kr/learn/courses/30/lessons/17686?language=javascript
function solution(files) {
  return files.slice().sort((a,b) => {
      let arr1 = a.split('');
      let arr2 = b.split('');
      let aHead = '', bHead = '', aNumber = '', bNumber = '';
      for (let i = 0 ; i < arr1.length; i++) {
          if (Number.isInteger(parseInt(arr1[i]))) {
              if (aNumber.length < 5) aNumber += arr1[i];
              if (!Number.isInteger(parseInt(arr1[i+1]))) break;
          } else {
              aHead += arr1[i]
          }
      }
      for (let i = 0 ; i < arr2.length; i++) {
          if (Number.isInteger(parseInt(arr2[i]))) {
              if (bNumber.length < 5) bNumber += arr2[i];
              if (!Number.isInteger(parseInt(arr2[i+1]))) break;
          } else {
              bHead += arr2[i]
          }
      }
      aNumber = parseInt(aNumber);
      bNumber = parseInt(bNumber);
      aHead = aHead.toUpperCase();
      bHead = bHead.toUpperCase();
      if (aHead == bHead) return aNumber - bNumber
      else {
          if (aHead > bHead) return 1;
          else return -1;
      }
      
      
  })
}
// 후기: 문제를 보면 Number는 최대 5자리까지 제한이고, 문자열에 " "(공백)과 "-"(하이푼)을 포함해주어야 했는데
//        이를 무시하고 문자만 넣었기 떄문에 테스트케이스 몇가지가 실패했다. 문제를 꼼꼼히 읽자
// ex) im g01 과 img01은 다른것
// ex) im-g01 과 img01은 다른것