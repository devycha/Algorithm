// 문제 설명
// 2016년 1월 1일은 금요일입니다. 2016년 a월 b일은 무슨 요일일까요? 두 수 a ,b를 입력받아 2016년 a월 b일이 무슨 요일인지 리턴하는 함수, solution을 완성하세요. 요일의 이름은 일요일부터 토요일까지 각각 SUN,MON,TUE,WED,THU,FRI,SAT

// 입니다. 예를 들어 a=5, b=24라면 5월 24일은 화요일이므로 문자열 "TUE"를 반환하세요.

// 제한 조건
// 2016년은 윤년입니다.
// 2016년 a월 b일은 실제로 있는 날입니다. (13월 26일이나 2월 45일같은 날짜는 주어지지 않습니다)
// 나의 풀이
function solution(a, b) {
  var answer = '';
  let days = -1; // 1월 1일부터 시작함
  let day = ['FRI', 'SAT','SUN', 'MON', 'TUE', 'WED', 'THU'];
  let mon31 = [1, 3, 5, 7, 8, 10, 12];
  let mon30 = [4, 6, 9, 11];
  for (let i = 1; i < a; i++) {
      if (mon31.includes(i)) {days += 31;}
      else if (mon30.includes(i)) {days += 30;}
      else {days += 29;} // 윤년
  }
  days += b;
  return day[days%7];
}

// 다른 사람 풀이 해석
function getDayName(a,b){
  var date = new Date(2016, (a - 1), b);
    return date.toString().slice(0, 3).toUpperCase();
}

function getDayName(a,b){
  var arr = ['SUN','MON','TUE','WED','THU','FRI','SAT'];
  var date = new Date(`2016-${a}-${b}`);
var day = date.getDay()
  return arr[day];
}
// 이 두 방법은 date함수를 이용한 적절하지 않은 방안같음. 지연시간도 내가 푼 풀이보다 오래걸리는 것을 봤을 때
// 객체 매서드를 잘 활용하는 것에 있어서 좋다고 생각하지만 알고리즘 공부에 있어서는 옳바르지 않다고 생각함
