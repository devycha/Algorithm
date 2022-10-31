/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1256
 *
 * ? 제목: 사전
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 동호와 규완이는 212호에서 문자열에 대해 공부하고 있다. 김진영 조교는 동호와 규완이에게 특별 과제를 주었다. 특별 과제는 특별한 문자열로 이루어 진 사전을 만드는 것이다. 사전에 수록되어 있는 모든 문자열은 N개의 "a"와 M개의 "z"로 이루어져 있다. 그리고 다른 문자는 없다. 사전에는 알파벳 순서대로 수록되어 있다.
 * 규완이는 사전을 완성했지만, 동호는 사전을 완성하지 못했다. 동호는 자신의 과제를 끝내기 위해서 규완이의 사전을 몰래 참조하기로 했다. 동호는 규완이가 자리를 비운 사이에 몰래 사전을 보려고 하기 때문에, 문자열 하나만 찾을 여유밖에 없다.
 * N과 M이 주어졌을 때, 규완이의 사전에서 K번째 문자열이 무엇인지 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 세 정수 N, M, K가 순서대로 주어진다.
 *
 * 2 2 2 -> n m k
 *
 * ? 출력
 * 첫째 줄에 규완이의 사전에서 K번째 문자열을 출력한다. 만약 규완이의 사전에 수록되어 있는 문자열의 개수가 K보다 작으면 -1을 출력한다.
 *
 * azaz
 *
 * ? 제한
 * 1 ≤ N, M ≤ 100
 * 1 ≤ K ≤ 1,000,000,000
 *
 * ? 채점 결과
 * * 시간: 0.124초
 * * 메모리: 14MB
 * * 언어: JAVA11
 * * 시도: 4
 */
package Gold.II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_1256 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, k;
    static int MAX = 1_000_000_000;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + dp[i][j - 1], MAX);
            }
        }

        if (dp[n][m] < k) {
            System.out.println(-1);
        } else {
            StringBuffer sb = new StringBuffer();

            while (true) {
                if (n == 0 || m == 0) {
                    sb.append("z".repeat(m));
                    sb.append("a".repeat(n));
                    break;
                }

                if (dp[n - 1][m] >= k) {
                    sb.append("a");
                    n--;
                } else {
                    sb.append("z");
                    k -= dp[n - 1][m];
                    m--;
                }
            }

            System.out.println(sb);
        }

    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i <= m; i++) {
            dp[0][i] = 1;
        }
    }
}
