/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11049
 *
 * ? 제목: 행렬 곱셈 순서
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 크기가 N×M인 행렬 A와 M×K인 B를 곱할 때 필요한 곱셈 연산의 수는 총 N×M×K번이다. 행렬 N개를 곱하는데 필요한 곱셈 연산의 수는 행렬을 곱하는 순서에 따라 달라지게 된다.
 * 예를 들어, A의 크기가 5×3이고, B의 크기가 3×2, C의 크기가 2×6인 경우에 행렬의 곱 ABC를 구하는 경우를 생각해보자.
 * AB를 먼저 곱하고 C를 곱하는 경우 (AB)C에 필요한 곱셈 연산의 수는 5×3×2 + 5×2×6 = 30 + 60 = 90번이다.
 * BC를 먼저 곱하고 A를 곱하는 경우 A(BC)에 필요한 곱셈 연산의 수는 3×2×6 + 5×3×6 = 36 + 90 = 126번이다.
 * 같은 곱셈이지만, 곱셈을 하는 순서에 따라서 곱셈 연산의 수가 달라진다.
 * 행렬 N개의 크기가 주어졌을 때, 모든 행렬을 곱하는데 필요한 곱셈 연산 횟수의 최솟값을 구하는 프로그램을 작성하시오. 입력으로 주어진 행렬의 순서를 바꾸면 안 된다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 행렬의 개수 N(1 ≤ N ≤ 500)이 주어진다.
 * 둘째 줄부터 N개 줄에는 행렬의 크기 r과 c가 주어진다. (1 ≤ r, c ≤ 500)
 * 항상 순서대로 곱셈을 할 수 있는 크기만 입력으로 주어진다.
 *
 * 3    -> n
 * 5 3  -> r c
 * 3 2  -> r c
 * 2 6  -> r c
 *
 * ? 출력
 * 첫째 줄에 입력으로 주어진 행렬을 곱하는데 필요한 곱셈 연산의 최솟값을 출력한다. 정답은 231-1 보다 작거나 같은 자연수이다. 또한, 최악의 순서로 연산해도 연산 횟수가 231-1보다 작거나 같다.
 *
 * 90
 *
 * ? 채점 결과
 * * 시간: 0.208초
 * * 메모리: 14MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.III;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G3_11049 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[][] arr;

    // * 초기 설정
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP Table 초기화
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        // * 3. Start Case 설정
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int i = 0; i < n-1; i++) {
            dp[i][i+1] = arr[i][0] * arr[i][1] * arr[i+1][1];
        }

        // * 4. DP(Tabulation: Bottom-Up)
        for (int k = 2; k < n; k++) {
            for (int i = 0; i < n-2; i++) {
                if (i + k >= n) {
                    continue;
                }

                for (int j = i; j < i + k; j++) {
                    dp[i][i+k] = Math.min(
                            dp[i][i+k],
                            dp[i][j] + dp[j+1][i+k] + arr[i][0] * arr[j][1] * arr[i+k][1]
                    );
                }
            }
        }

        // * 5. 정답 출력
        System.out.println(dp[0][n-1]);
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new int[n][n];

        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[i][0] = r;
            arr[i][1] = c;
        }
    }
}