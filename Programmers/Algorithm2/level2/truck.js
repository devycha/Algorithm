function solution(bridge_length, weight, truck_weights) {
    var answer = 0;
    let clear = truck_weights.length;
    let clearTime = bridge_length;
    let pass = [];
    let nopass = [];
    while (pass.length < clear) {
        let truck = truck_weights.shift();
        let sum = 0;
        for (let i = 0 ; i < nopass.length; i++) {
            sum += nopass[i];
        }
        if (sum + truck <= weight) {
            nopass.push(truck);
        } else {
            nopass.push(0);
            truck_weights.unshift(truck);
        }
        if (nopass.length == clearTime) {
            let passTruck = nopass.shift();
            if (passTruck) {
                pass.push(passTruck);
            }
        }  
        answer += 1;
    }
    return answer+1;
}
// 후기: 시간복잡도가 매우 큰 것 같다.
// 다른 사람 풀이 해석
function solution(bridge_length, weight, truck_weights) {
    // '다리'를 모방한 큐에 간단한 배열로 정리 : [트럭무게, 얘가 나갈 시간].
    let time = 0, qu = [[0, 0]], weightOnBridge = 0;
  
    // 대기 트럭, 다리를 건너는 트럭이 모두 0일 때 까지 다음 루프 반복
    while (qu.length > 0 || truck_weights.length > 0) {
      // 1. 현재 시간이, 큐 맨 앞의 차의 '나갈 시간'과 같다면 내보내주고,
      //    다리 위 트럭 무게 합에서 빼준다.
      if (qu[0][1] === time) weightOnBridge -= qu.shift()[0];
  
      if (weightOnBridge + truck_weights[0] <= weight) {
        // 2. 다리 위 트럭 무게 합 + 대기중인 트럭의 첫 무게가 감당 무게 이하면 
        //    다리 위 트럭 무게 업데이트, 큐 뒤에 [트럭무게, 이 트럭이 나갈 시간] 추가.
        weightOnBridge += truck_weights[0];
        qu.push([truck_weights.shift(), time + bridge_length]);
      } else {
        // 3. 다음 트럭이 못올라오는 상황이면 얼른 큐의
        //    첫번째 트럭이 빠지도록 그 시간으로 점프한다.
        //    참고: if 밖에서 1 더하기 때문에 -1 해줌
        if (qu[0]) time = qu[0][1] - 1;
      }
      // 시간 업데이트 해준다.
      time++;
    }
    return time;
  }

  // 리팩토링
  function solution(bridge_length, weight, truck_weights) {
    let answer = 0, sumWeight = 0;
    let clear = truck_weights.length, clearTime = bridge_length;
    let pass = [], nopass = [];
    while (pass.length < clear) {
        let truck = truck_weights.shift();
        if (sumWeight + truck <= weight) {
            nopass.push(truck);
            sumWegith += truck
        } else {
            nopass.push(0);
            truck_weights.unshift(truck);
        }
        if (nopass.length == clearTime) {
            let passTruck = nopass.shift();
            if (passTruck) {
                pass.push(passTruck);
                sumWeight -= passTruck;
            }
        }  
        answer += 1;
    }
    return answer+1;
}
// 리펙토링 전과 후 시간 비교
// 테스트 1 〉	통과 (10.20ms, 32.8MB)
// 테스트 2 〉	통과 (257.56ms, 32.9MB)
// 테스트 3 〉	통과 (0.24ms, 30MB)
// 테스트 4 〉	통과 (64.44ms, 33.4MB)
// 테스트 5 〉	통과 (1574.90ms, 35MB)
// 테스트 6 〉	통과 (286.04ms, 34.7MB)
// 테스트 7 〉	통과 (10.54ms, 32.8MB)
// 테스트 8 〉	통과 (1.07ms, 29.7MB)
// 테스트 9 〉	통과 (5.98ms, 32.8MB)
// 테스트 10 〉	통과 (4.23ms, 32.8MB)
// 테스트 11 〉	통과 (0.11ms, 30.3MB)
// 테스트 12 〉	통과 (0.83ms, 30.1MB)
// 테스트 13 〉	통과 (19.20ms, 32.8MB)
// 테스트 14 〉	통과 (0.45ms, 30.3MB)

/////////////////////////////////////////////////

// 테스트 1 〉	통과 (0.98ms, 30.2MB)
// 테스트 2 〉	통과 (13.56ms, 33.2MB)
// 테스트 3 〉	통과 (0.12ms, 30.1MB)
// 테스트 4 〉	통과 (9.48ms, 33.1MB)
// 테스트 5 〉	통과 (73.99ms, 34.7MB)
// 테스트 6 〉	통과 (47.13ms, 34.6MB)
// 테스트 7 〉	통과 (0.90ms, 30MB)
// 테스트 8 〉	통과 (0.26ms, 30MB)
// 테스트 9 〉	통과 (5.56ms, 33MB)
// 테스트 10 〉	통과 (0.28ms, 30.1MB)
// 테스트 11 〉	통과 (0.10ms, 30.2MB)
// 테스트 12 〉	통과 (0.24ms, 30.2MB)
// 테스트 13 〉	통과 (0.77ms, 30MB)
// 테스트 14 〉	통과 (0.13ms, 30.1MB)

// 다른 사람의 2차원 배열을 통한 풀이 시간 분석

// 테스트 1 〉	통과 (0.09ms, 30MB)
// 테스트 2 〉	통과 (0.13ms, 30.2MB)
// 테스트 3 〉	통과 (0.10ms, 30MB)
// 테스트 4 〉	통과 (0.31ms, 30MB)
// 테스트 5 〉	통과 (0.54ms, 30.2MB)
// 테스트 6 〉	통과 (0.42ms, 29.8MB)
// 테스트 7 〉	통과 (0.09ms, 29.9MB)
// 테스트 8 〉	통과 (0.12ms, 30.1MB)
// 테스트 9 〉	통과 (0.41ms, 30.1MB)
// 테스트 10 〉	통과 (0.11ms, 30.2MB)
// 테스트 11 〉	통과 (0.11ms, 30MB)
// 테스트 12 〉	통과 (0.17ms, 30.2MB)
// 테스트 13 〉	통과 (0.16ms, 30.3MB)
// 테스트 14 〉	통과 (0.09ms, 30MB)