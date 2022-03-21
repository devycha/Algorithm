// 문제 설명
// 자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return 하는 solution 함수를 만들어 주세요.
// 예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.

// 제한사항
// N의 범위 : 100,000,000 이하의 자연수
// 입출력 예
// N	answer
// 123	6
// 987	24
// 나의 풀이
function solution(n)
{
    return parseInt(n.toString().split("").reduce((sum,el) => parseInt(sum) + parseInt(el)));
}
// 후기: 맨 앞에 있는 parseInt를 제외하고 처음에 테스트를 했을 때 마지막케이스가 실패로 뜨게 되었다.
//      아무래도 n이 0일 때 오류를 내는 것 같았다. 따라서 앞에 parseInt를 넣어주는 것보다
//      n.toString().split("").reduce((sum,el) => parseInt(sum) + parseInt(el), 0) 처럼
//      reduce method 에 initial Value option으로 0을 추가해주는게 좋을 듯하다.
function solution(n)
{
    return n.toString().split("").reduce((sum,el) => parseInt(sum) + parseInt(el),0);
}
// 다른 사람 풀이 해석
function solution(n){
  return (n+"").split("").reduce((acc, curr) => acc + parseInt(curr), 0)
}