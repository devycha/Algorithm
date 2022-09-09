/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2225
 *
 * ? 제목: 합분해
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.
 * 덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 두 정수 N(1 ≤ N ≤ 200), K(1 ≤ K ≤ 200)가 주어진다.
 *
 * 20 2 -> n k
 *
 * ? 출력
 * 첫째 줄에 답을 1,000,000,000으로 나눈 나머지를 출력한다.
 *
 * 21
 *
 * ? 채점 결과
 * * 시간: 0.156초
 * * 메모리: 12MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_2225 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, k;

    // * 초기 설정
    static int div = 1_000_000_000;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. Start Case 설정
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 1;
        }

        // * 3. DP(Tabulation: Bottom-Up)
        for (int i = 1; i < k; i++) {
            for (int j = 0; j <= n; j++) {
                for (int w = 0; w <= j; w++) {
                    dp[i][j] = (dp[i][j] % div + dp[i-1][w] % div) % div;
                }
            }
        }

        // * 4. 출력
        System.out.println(dp[k-1][n]);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[k][n+1]; // DP 테이블 초기화
    }
}
