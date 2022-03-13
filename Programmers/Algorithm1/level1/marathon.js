// 마라톤 알고리즘
// 동명이인 존재, 완주하지못한 1인 찾기 알고리즘
// participant와 completion은 사람이름의 문자열로 이루어진 배열
// 내가 쓴 풀이
function solution(participant, completion) {
    let answer = '';
    let part = {};
    let comp = {};
    participant.forEach((people) => {
        if (part[people]) {
            part[people] += 1;
        } else {
            part[people] = 1;
        }
    })
    completion.forEach((people) => {
        if (comp[people]) {
            comp[people] += 1;
        } else {
            comp[people] = 1;
        }
    })
    for (let mem in part) {
        if (comp[mem] !== part[mem] || !comp[mem]) {
            answer += mem;
            break;
        }
    }
    return answer;
}

// 완료 후 리펙토링
function solution(participant, completion) {
    let p = participant.reduce((allpart, part) => (allpart[part] = allpart[part] ? allpart[part] + 1 : 1, allpart), {});
    let c = completion.reduce((allcomp, comp) => (allcomp[comp] = allcomp[comp] ? allcomp[comp] + 1 : 1, allcomp), {});
    let answer = participant.find(part => {
        if (c[part] !== p[part] || !c[part]) {
            return true;
        }
    });
    return answer;
}
// 후기: 가독성은 첫번째 풀이가 더 좋은 것 같다. 하지만 리펙토링 후에 더 코드가 멋있어 진 것 같다는 혼자만의 생각..



// 다른 사람들의 풀이 해석
//1
function solution(participant, completion) {
    var dic = completion.reduce((obj, t) => (obj[t] = obj[t] ? obj[t] + 1 : 1, obj), {});
    return participant.find(t => {
        if (dic[t])
            dic[t] = dic[t] - 1;
        else
            return true;
    });
}

//2
var solution = (participant, completion) => participant.find(name => !completion[name]--, completion.map(name => completion[name] = (completion[name] | 0) + 1))