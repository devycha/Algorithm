/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1890
 *
 * ? 제목: 점프
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * N×N 게임판에 수가 적혀져 있다. 이 게임의 목표는 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 점프를 해서 가는 것이다.
 * 각 칸에 적혀있는 수는 현재 칸에서 갈 수 있는 거리를 의미한다. 반드시 오른쪽이나 아래쪽으로만 이동해야 한다. 0은 더 이상 진행을 막는 종착점이며, 항상 현재 칸에 적혀있는 수만큼 오른쪽이나 아래로 가야 한다. 한 번 점프를 할 때, 방향을 바꾸면 안 된다. 즉, 한 칸에서 오른쪽으로 점프를 하거나, 아래로 점프를 하는 두 경우만 존재한다.
 * 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 이동할 수 있는 경로의 개수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력
 * 첫째 줄에 게임 판의 크기 N (4 ≤ N ≤ 100)이 주어진다. 그 다음 N개 줄에는 각 칸에 적혀져 있는 수가 N개씩 주어진다. 칸에 적혀있는 수는 0보다 크거나 같고, 9보다 작거나 같은 정수이며, 가장 오른쪽 아래 칸에는 항상 0이 주어진다.
 *
 * 4        -> n
 * 2 3 3 1  -> arr[0][0] ~ arr[0][n-1]
 * 1 2 1 3
 * 1 2 3 1
 * 3 1 1 0  -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 문제의 규칙에 맞게 갈 수 있는 경로의 개수를 출력한다. 경로의 개수는 263-1보다 작거나 같다.
 *
 * 3
 *
 * ? 채점 결과
 * * 시간: 0.08초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Silver.I;

import java.io.*;
import java.util.StringTokenizer;

public class S1_1890 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[][] arr;

    // * 초기 설정
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        dp[0][0] = 1L; // * Start Case 설정

        // * 2. DP(Tabulation: Bottom-Up)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = arr[i][j];

                if (k == 0) {
                    break;
                }

                if (i+k < n) {
                    dp[i+k][j] += dp[i][j];
                }

                if (j+k < n) {
                    dp[i][j+k] += dp[i][j];
                }
            }
        }

        // * 3. 출력
        System.out.println(dp[n-1][n-1]);
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
