/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11660
 *
 * ? 제목: 구간 합 구하기 5
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * N×N개의 수가 N×N 크기의 표에 채워져 있다. (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오. (x, y)는 x행 y열을 의미한다.
 * 예를 들어, N = 4이고, 표가 아래와 같이 채워져 있는 경우를 살펴보자.
 *
 * 1	2	3	4
 * 2	3	4	5
 * 3	4	5	6
 * 4	5	6	7
 * 여기서 (2, 2)부터 (3, 4)까지 합을 구하면 3+4+5+4+5+6 = 27이고, (4, 4)부터 (4, 4)까지 합을 구하면 7이다.
 * 표에 채워져 있는 수와 합을 구하는 연산이 주어졌을 때, 이를 처리하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다. (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000) 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다. 다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다. 표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다. (x1 ≤ x2, y1 ≤ y2)
 *
 * 4 3      -> n m
 * 1 2 3 4  -> arr[0][0] ~ arr[0][n-1]
 * 2 3 4 5
 * 3 4 5 6
 * 4 5 6 7  -> arr[n-1][0] ~ arr[n-1][n-1]
 * 2 2 3 4  -> test[0] ~ test[0][3]
 * 3 4 3 4
 * 1 1 4 4  -> test[m-1][0] ~ test[m-1][3]
 *
 * ? 출력
 * 총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.
 *
 * 27
 * 6
 * 64
 *
 * ? 채점 결과
 * * 시간: 0.724초
 * * 메모리: 144MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Silver.I;

import java.io.*;
import java.util.StringTokenizer;

public class S1_11660 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static int[][] arr;
    static int[][] test;

    // * 초기 설정
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP(Tabulation: Bottom-Up): 누적합 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i-1 >= 0 && j-1 >= 0) {
                    dp[i][j] = dp[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
                } else if (i-1 >= 0) {
                    dp[i][j] += dp[i-1][j];
                } else if (j-1 >= 0) {
                    dp[i][j] += dp[i][j-1];
                }
            }
        }

        // * 3. 구간합 구하기(중복제거)
        StringBuffer sb = new StringBuffer();
        for (int[] t : test) {
            int sum = 0;
            if (t[0]-1 >= 0 && t[1]-1 >= 0) {
                sum = dp[t[2]][t[3]]
                        - dp[t[0]-1][t[3]]
                        - dp[t[2]][t[1]-1]
                        + dp[t[0]-1][t[1]-1];
            } else if (t[0]-1 >= 0) {
                sum = dp[t[2]][t[3]] - dp[t[0]-1][t[3]];
            } else if (t[1]-1 >= 0) {
                sum = dp[t[2]][t[3]] - dp[t[2]][t[1]-1];
            } else {
                sum = dp[t[2]][t[3]];
            }
            sb.append(sum + "\n");
        }

        // * 4. 출력
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        test = new int[m][4];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = arr[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                test[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
    }
}
