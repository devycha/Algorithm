// 문제 설명
// 선행 스킬이란 어떤 스킬을 배우기 전에 먼저 배워야 하는 스킬을 뜻합니다.

// 예를 들어 선행 스킬 순서가 스파크 → 라이트닝 볼트 → 썬더일때, 썬더를 배우려면 먼저 라이트닝 볼트를 배워야 하고, 라이트닝 볼트를 배우려면 먼저 스파크를 배워야 합니다.

// 위 순서에 없는 다른 스킬(힐링 등)은 순서에 상관없이 배울 수 있습니다. 따라서 스파크 → 힐링 → 라이트닝 볼트 → 썬더와 같은 스킬트리는 가능하지만, 썬더 → 스파크나 라이트닝 볼트 → 스파크 → 힐링 → 썬더와 같은 스킬트리는 불가능합니다.

// 선행 스킬 순서 skill과 유저들이 만든 스킬트리1를 담은 배열 skill_trees가 매개변수로 주어질 때, 가능한 스킬트리 개수를 return 하는 solution 함수를 작성해주세요.

// 제한 조건
// 스킬은 알파벳 대문자로 표기하며, 모든 문자열은 알파벳 대문자로만 이루어져 있습니다.
// 스킬 순서와 스킬트리는 문자열로 표기합니다.
// 예를 들어, C → B → D 라면 "CBD"로 표기합니다
// 선행 스킬 순서 skill의 길이는 1 이상 26 이하이며, 스킬은 중복해 주어지지 않습니다.
// skill_trees는 길이 1 이상 20 이하인 배열입니다.
// skill_trees의 원소는 스킬을 나타내는 문자열입니다.
// skill_trees의 원소는 길이가 2 이상 26 이하인 문자열이며, 스킬이 중복해 주어지지 않습니다.
// 입출력 예
// skill	skill_trees	return
// "CBD"	["BACDE", "CBADF", "AECB", "BDA"]	2
function solution(skill, skill_trees) {
    let s = skill.split('');
    let count = 0;
    skill_trees.forEach((skills,i) => {
        let arr1 = skills.split('');
        let arr2 = [];
        for (let j = 0; j < s.length; j++) {
            if (arr1.includes(s[j])) arr2.push(arr1.indexOf(s[j]))
            else arr2.push(21);
        }
        let arr3 = arr2.slice().sort((a,b) => a-b);
        for (let i = 0; i < arr2.length; i++) {
            if (arr3[i] !== arr2[i]) {
                break;
            }
            if (i == arr2.length-1) count += 1;
        }
    })
    return count;
}

// 다른 사람 풀이 해석
function solution(skill, skill_trees) {
    var answer = 0;
    var regex = new RegExp(`[^${skill}]`, 'g');

    return skill_trees
        .map((x) => x.replace(regex, ''))
        .filter((x) => {
            return skill.indexOf(x) === 0 || x === "";
        })
        .length
}
// 후기: 정규표현식에서 `[^abc]`는 abc를 제외한 모든 문자와 대응된다.
// MDN 기술문서 참고
// 원래 정규표현식에서 ^는 입력의 시작 부분에 대응됩니다. 만약 다중행 플래그가 참으로 설정되어 있다면, 줄 바꿈 문자 바로 다음 부분과도 대응됩니다.
// 예를 들어, /^A/ 는 "an A" 의 'A'와는 대응되지 않습니다, 그러나 "An E" 의 'A'와는 대응됩니다.
// '^' 가 문자셋([abc]) 패턴의 첫 글자로 쓰인다면, 그 때는 전혀 다른 의미를 가집니다. 자세한 내용은 역 문자셋을 참고하세요.
var regex = new RegExp(`[^${skill}]`, 'g');