// 문제 링크: https://www.acmicpc.net/problem/4358
/**
 * 입력값 예시
 * Red Alder
 * Ash
 * Aspen
 * Basswood
 * Ash
 * Beech
 * Yellow Birch
 * Ash
 * Cherry
 * Cottonwood
 * Ash
 * Cypress
 * Red Elm
 * Gum
 * Hackberry
 * White Oak
 * Hickory
 * Pecan
 * Hard Maple
 * White Oak
 * Soft Maple
 * Red Oak
 * Red Oak
 * White Oak
 * Poplan
 * Sassafras
 * Sycamore
 * Black Walnut
 * Willow
 *
 * 출력값 예시
 * Ash 13.7931
 * Aspen 3.4483
 * Basswood 3.4483
 * Beech 3.4483
 * Black Walnut 3.4483
 * Cherry 3.4483
 * Cottonwood 3.4483
 * Cypress 3.4483
 * Gum 3.4483
 * Hackberry 3.4483
 * Hard Maple 3.4483
 * Hickory 3.4483
 * Pecan 3.4483
 * Poplan 3.4483
 * Red Alder 3.4483
 * Red Elm 3.4483
 * Red Oak 6.8966
 * Sassafras 3.4483
 * Soft Maple 3.4483
 * Sycamore 3.4483
 * White Oak 10.3448
 * Willow 3.4483
 * Yellow Birch 3.4483
 *
 * 파싱
 * arr = [
 *  Red Alder,
 *  Ash,
 *  Aspen,
 *  Basswood,
 *  Ash,
 *  Beech,
 *  Yellow Birch,
 *  Ash,
 *  Cherry,
 *  Cottonwood,
 *  Ash,
 *  Cypress,
 *  Red Elm,
 *  Gum,
 *  Hackberry,
 *  White Oak,
 *  Hickory,
 *  Pecan,
 *  Hard Maple,
 *  White Oak,
 *  Soft Maple,
 *  Red Oak,
 *  Red Oak,
 *  White Oak,
 *  Poplan,
 *  Sassafras,
 *  Sycamore,
 *  Black Walnut,
 *  Willow,
 * ]
 *
 * 초기 설정
 * obj = {}
 * 주의할 점: 입력의 마지막에는 항상 줄바꿈 문자로 끝나기 때문에 양 끝에 white space를 없애주는 trim을 작업함
 */
const fs = require("fs");
const arr = fs.readFileSync("input.txt").toString().trim().split("\n"); // trim으로 마지막 입력의 공백을 없애줌.

/**
 * 나무의 갯수 카운트
 */
let obj = {};
arr.forEach((tree) => {
  tree = tree.trim();
  if (!obj[tree]) obj[tree] = 1;
  else obj[tree] += 1;
});

let keys = Object.keys(obj); // 나무의 종과 갯수를 카운트한 객체의 key값만 받아와서 배열 keys에 저장.
/**
 * key들을 알파벳 순으로 정렬한 뒤에
 * map 함수를 이용하여 '종이름 백분율'의 형태로 mapping해줌.
 * 이때 toFixed 함수를 사용하여 소수점 4째자리까지 반올림함.
 * 마지막으로 줄바꿈 문자를 통해 하나의 문자열로 합쳐준 뒤에 출력
 */
let result = keys
  .sort()
  .map((key) => `${key} ${((100 * obj[key]) / arr.length).toFixed(4)}`)
  .join("\n");

console.log(result);
