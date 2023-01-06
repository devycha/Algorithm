import java.util.*;

/**
 * 문제링크 : https://school.programmers.co.kr/learn/courses/30/lessons/142085
 *
 * 입력예시
 * n = 7
 * k = 3
 * enemy = [4, 2, 4, 5, 3, 3, 1]
 *
 * 결과예시
 * 5
 */

class Solution {
    public int solution(int n, int k, int[] enemy) {

        // 적의 가장 적은 병력을 효율적으로 찾기 위해 우선순위큐를 선언합니다.
        int answer = 0;
        Queue<Integer> heap = new PriorityQueue<>();

        // "무적권"의 갯수 만큼 큐를 채워줍니다.
        for (int i = 0; i < k; i++, answer++) {
            if (i >= enemy.length) {
                break;
            }
            heap.offer(enemy[i]);
        }

        // 무적권이 없을 경우 나의 병력이 0 미만일때 까지 라운드를 진행합니다.
        if (heap.size() == 0) {
            for (int i : enemy) {
                if (n - i > 0) {
                    answer++;
                    n -= i;
                }
            }
        } else { // 적의 가장 적은 병력 수가 나의 병력보다 클때의 라운드 수가 정답입니다.
            for (int i = k; i < enemy.length; i++, answer++) {
                heap.offer(enemy[i]);
                int minValue = heap.poll();

                if (n < minValue) {
                    break;
                }

                n -= minValue;
            }
        }
        return answer;
    }
}


/*
테스트 1 〉	통과 (1.47ms, 79.6MB)
테스트 2 〉	통과 (6.75ms, 95MB)
테스트 3 〉	통과 (84.87ms, 136MB)
테스트 4 〉	통과 (4.96ms, 124MB)
테스트 5 〉	통과 (2.80ms, 79.6MB)
테스트 6 〉	통과 (93.06ms, 145MB)
테스트 7 〉	통과 (76.02ms, 142MB)
테스트 8 〉	통과 (33.14ms, 135MB)
테스트 9 〉	통과 (51.91ms, 126MB)
테스트 10 〉	통과 (142.13ms, 138MB)
테스트 11 〉	통과 (0.54ms, 122MB)
테스트 12 〉	통과 (0.88ms, 126MB)
테스트 13 〉	통과 (0.28ms, 78.3MB)
테스트 14 〉	통과 (0.42ms, 79MB)
테스트 15 〉	통과 (0.36ms, 67.5MB)
테스트 16 〉	통과 (0.33ms, 81.8MB)
테스트 17 〉	통과 (0.32ms, 73.5MB)
테스트 18 〉	통과 (0.30ms, 75.7MB)
테스트 19 〉	통과 (0.28ms, 74.9MB)
테스트 20 〉	통과 (0.32ms, 77.7MB)
테스트 21 〉	통과 (0.28ms, 74.8MB)
테스트 22 〉	통과 (0.46ms, 74.1MB)
테스트 23 〉	통과 (0.69ms, 77.9MB)
테스트 24 〉	통과 (0.71ms, 78.6MB)
테스트 25 〉	통과 (0.58ms, 72.7MB)
테스트 26 〉	통과 (0.66ms, 76.8MB)
테스트 27 〉	통과 (0.89ms, 76.1MB)
테스트 28 〉	통과 (0.54ms, 80.5MB)
테스트 29 〉	통과 (0.91ms, 81.4MB)
테스트 30 〉	통과 (0.76ms, 77.6MB)
테스트 31 〉	통과 (0.59ms, 77.4MB)
테스트 32 〉	통과 (0.91ms, 85.4MB)
 */