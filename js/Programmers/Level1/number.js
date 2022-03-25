// 문제 설명
// 네오와 프로도가 숫자놀이를 하고 있습니다. 네오가 프로도에게 숫자를 건넬 때 일부 자릿수를 영단어로 바꾼 카드를 건네주면 프로도는 원래 숫자를 찾는 게임입니다.

// 다음은 숫자의 일부 자릿수를 영단어로 바꾸는 예시입니다.

// 1478 → "one4seveneight"
// 234567 → "23four5six7"
// 10203 → "1zerotwozero3"
// 이렇게 숫자의 일부 자릿수가 영단어로 바뀌어졌거나, 혹은 바뀌지 않고 그대로인 문자열 s가 매개변수로 주어집니다. s가 의미하는 원래 숫자를 return 하도록 solution 함수를 완성해주세요.

// 참고로 각 숫자에 대응되는 영단어는 다음 표와 같습니다.

// 숫자	영단어
// 0	zero
// 1	one
// 2	two
// 3	three
// 4	four
// 5	five
// 6	six
// 7	seven
// 8	eight
// 9	nine
// 제한사항
// 1 ≤ s의 길이 ≤ 50
// s가 "zero" 또는 "0"으로 시작하는 경우는 주어지지 않습니다.
// return 값이 1 이상 2,000,000,000 이하의 정수가 되는 올바른 입력만 s로 주어집니다.
// 입출력 예
// s	result
// "one4seveneight"	1478
// "23four5six7"	234567
// "2three45sixseven"	234567
// "123"	123
function solution(s) {
    let answer = [];
    let dic = { zero: '0', one: '1', two: '2', three: '3', four: '4', five: '5', six: '6', seven: '7', eight: '8', nine: '9' }
    let word = "";
    for (let i = 0; i < s.length; i++) {
        if (!Number.isInteger(parseInt(s[i]))) {
            word += s[i];
            if (dic[word]) {
                answer.push(dic[word])
                word = "";
            }
        } else {
            answer.push(s[i])
        }
    }
    return parseInt(answer.join('').trim())
}
// 후기: 처음 객체 생성하는 과정에서 value값을 string이 아닌 number형태로 저장해서 오류가 있었다.
// 다른 사람 풀이 해석
function solution(s) {
    let charSet = {
        "zero": 0,
        "one": 1,
        "two": 2,
        "three": 3,
        "four": 4,
        "five": 5,
        "six": 6,
        "seven": 7,
        "eight": 8,
        "nine": 9,
    }

    for (let [key, value] of Object.entries(charSet)) {
        let re = new RegExp(`${key}`, "g");
        s = s.replace(re, value);
    }
    return parseInt(s);
}
// 객체를 활용해서 푼 점이 비슷했지만 객체를 for로 돌고 key값에 맞는 문자열들을 모두 value값으로 바꾸는 replace함수를 사용했다는 점에서
// 코드가 더 간단해졌다. replace 사용에 더 익숙해져야겠다.