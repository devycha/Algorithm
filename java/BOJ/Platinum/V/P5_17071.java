/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/17071
 *
 * ? 제목: 숨바꼭질 5
 * ? 시간 제한: 0.25초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 500,000)에 있고, 동생은 점 K(0 ≤ K ≤ 500,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다. 동생은 항상 걷기만 한다. 동생은 항상 매 초마다 이동을 하며, 이동은 가속이 붙는다. 동생이 이동하는 거리는 이전에 이동한 거리보다 1을 더한 만큼 이동한다. 즉, 동생의 처음 위치는 K, 1초가 지난 후 위치는 K+1, 2초가 지난 후 위치는 K+1+2, 3초가 지난 후의 위치는 K+1+2+3이다.
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오. 동생을 찾는 위치는 정수 좌표이어야 하고, 수빈이가 0보다 작은 좌표로, 50만보다 큰 좌표로 이동하는 것은 불가능하다.
 *
 * ? 입력 & 파싱
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 5 17 -> n m (편의상 k가 아니라 m으로)
 *
 * ? 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다. 수빈이가 동생을 찾을 수 없거나, 찾는 위치가 500,000을 넘는 경우에는 -1을 출력한다.
 *
 * 2
 *
 * ? 채점 결과
 * * 시간: 280ms
 * * 메모리: 74MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Platinum.V;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5_17071 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    
    // * 초기 설정
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        // * 1. 입력 받기
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // * 2. DP 방문리스트 초기화
        dp = new int[500001][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = -1;
            }
        }

        // * 3. BFS 수행
        bfs();

        // * 4. 수빈이가 동생을 찾는 가장 빠른 시간 출력
        System.out.println(nextLocation());
    }

    // ! BFS 수행해서 n(수빈)이 해당 위치까지 갈 수 있는 짝수횟수의 최솟값, 홀수횟수의 최솟값을 따로 구함
    // * 이동 시간이 짝수일 때는 dp[현재위치][0] = 이동 시간
    // * 이동 시간이 홀수일 때는 dp[현재위치][1] = 이동 시간
    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {n, 0});
        dp[n][0] = 0; 

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = -1; i <= 1; i++) {
                // 다음 위치
                int next;
                if (i == 0) {
                    next = cur[0] * 2;
                } else {
                    next = cur[0] + i;
                }

                // 범위 체크
                if (next < 0 || next > 500000) {
                    continue;
                }

                int k = cur[1] % 2 == 0 ? 0 : 1; // 홀 or 짝

                if (dp[next][k] == -1) {
                    dp[next][k] = cur[1]+1;
                    queue.add(new int[] {next, cur[1]+1});
                }
            }
        }
    }

    // ! 현재 위치에서 수빈이가 해당 위치를 최소로 방문한 홀수시간과 짝수시간을 비교하여 가장 빨리 잡는 시간을 찾는 함수
    // * 10의 위치를 수빈이가 홀수로는 3회, 짝수로는 4회 방문했다고 하고 동생은 그 위치를 시간이 5일때 방문했다면
    // * 홀수 방문했을 때 3회 -> 옆으로 갔다가 다시 들어오는 횟수(+2)가 가능하므로 5회가 최소
    public static int nextLocation() {
        if (n == m) {
            return 0;
        }

        int cur = m;
        int time = 0;
        int min = Integer.MAX_VALUE;

        while (true) {
            time++;
            cur += time;

            if (cur > 500000) {
                break;
            }

            if (time >= dp[cur][0] && (time % 2 == dp[cur][0] % 2)) {
                min = Math.min(min, time);
            }

            if (time >= dp[cur][1] && (time % 2 == dp[cur][1] % 2)) {
                min = Math.min(min, time);
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
