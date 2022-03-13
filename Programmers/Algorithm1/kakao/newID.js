// 카카오에 입사한 신입 개발자 네오는 "카카오계정개발팀"에 배치되어, 카카오 서비스에 가입하는 유저들의 아이디를 생성하는 업무를 담당하게 되었습니다. "네오"에게 주어진 첫 업무는 새로 가입하는 유저들이 카카오 아이디 규칙에 맞지 않는 아이디를 입력했을 때, 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램을 개발하는 것입니다.
// 다음은 카카오 아이디의 규칙입니다.

// 아이디의 길이는 3자 이상 15자 이하여야 합니다.
// 아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
// 단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.
// "네오"는 다음과 같이 7단계의 순차적인 처리 과정을 통해 신규 유저가 입력한 아이디가 카카오 아이디 규칙에 맞는 지 검사하고 규칙에 맞지 않은 경우 규칙에 맞는 새로운 아이디를 추천해 주려고 합니다.
// 신규 유저가 입력한 아이디가 new_id 라고 한다면,

// 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
// 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
// 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
// 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
// 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
// 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
//      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
// 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.

// 나의 풀이 => 테스트 실패 => 정규식 표현을 잘 몰랐음 => 공백처리를 하지 못함 => substring으로 접근 한 것은 잘했다고 생각함.

function solution(new_id) {
    var answer = '';
    let a = new_id.toLowerCase();
    for (let i = 0; i < a.length; i++) {
        let b = a[i].charCodeAt();
        if ((b >= 48 && b <= 57) || b == 45 || b == 95 || (b >= 97 && b <= 122)) {
            answer += a[i];
        }
        if (b == 46) {
            answer += a[i] == a[i + 1] ? "" : a[i];
        }

    }
    for (let j = 0; j < answer.length; j++) {
        if (answer[j] == ".") {
            answer = (answer[j] == answer[j + 1]) ? answer.substring(0, j) + answer.substring(j + 1, answer.length) : answer;
        }
    }
    if (answer[0] == ".") {
        answer = answer.substring(1, answer.length);
    }
    if (answer[answer.length - 1] == ".") {
        answer = answer.substring(0, answer.length - 1);
    }
    if (answer.length >= 16) {
        answer = answer.substring(0, 15);
    }
    if (answer[0] == ".") {
        answer = answer.substring(1, answer.length);
    }
    if (answer[answer.length - 1] == ".") {
        answer = answer.substring(0, answer.length - 1);
    }
    if (answer.length == 0) {
        answer = "a"
    }
    while (answer.length <= 2) {
        answer += answer[answer.length - 1];
    }
    return answer;
}

// 후기: 자바스크립트의 정규표현식에 대해 공부할 수 있었다.

// 정규표현식을 활용한 풀이 
function solution(new_id) {
    let answer = "";
    // 1단계
    answer = new_id.toLowerCase();
    const regexp = /[~! @#$%^&*()=+\[\{\]\}\:?,<>/]/g;
    // 2단계
    answer = answer.replace(regexp, "");
    // 3단계
    answer = answer.replace(/\.{2,}/g, ".");
    // answer = answer.replace(/^\.|\.$/g, "");

    // 4단계
    if (/^\./g.test(answer)) {
        answer = answer.substring(1, answer.length);
    }
    if (/\.$/g.test(answer)) {
        answer = answer.substring(0, answer.length - 1);
    }

    // 5단계
    if (answer.length == 0) {
        answer = "a";
    }
    // 6단계
    if (answer.length >= 16) {
        answer = answer.substring(0, 15);
    }
    if (/^\./g.test(answer)) {
        answer = answer.substring(1, answer.length);
    }
    if (/\.$/g.test(answer)) {
        answer = answer.substring(0, answer.length - 1);
    }
    // 7단계
    while (answer.length <= 2) {
        answer += answer[answer.length - 1];
    }
    console.log(answer);
    return answer = answer.trim();
}
// 예시
let id = ".ehq:{}>?<.,!$%!@#_0+wEIGQWH_.._.._"
console.log(solution(id));
let id1 = "b.=.=.=.=.=.=  .=.=.=.=.=.=.=.=.=.=.=.=.x";
console.log(solution(id1));
let id2 = ".....a......a.....a......a....";
console.log(solution(id2));

// 완료 후 리펙토링
function solution(new_id) {
    const answer = new_id
        .toLowerCase() // 1단계
        .replace(/[^\w-_.]/g, '') // 2단계
        .replace(/\.+/g, '.') // 3단계
        .replace(/^\.|\.$/g, '') // 4단계
        .replace(/^$/, 'a') // 5단계
        .slice(0, 15).replace(/\.$/, ''); // 6단계
    const len = answer.length;
    return len > 2 ? answer : answer + answer.charAt(len - 1).repeat(3 - len);
}