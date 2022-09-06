/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2293
 *
 * ? 제목: 동전 1
 * ? 시간 제한: 0.5초
 * ? 메모리 제한: 4MB
 *
 * ? 문제
 * n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그 경우의 수를 구하시오. 각각의 동전은 몇 개라도 사용할 수 있다.
 * 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다.
 *
 * 3 10 -> n k
 * 1    -> coins[0]
 * 2    ...
 * 5    -> coins[n-1]
 *
 * ? 출력
 * 첫째 줄에 경우의 수를 출력한다. 경우의 수는 2^31보다 작다.
 *
 * 10
 *
 * ? 채점 결과
 * * 시간: 96ms
 * * 메모리: 15MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_2293 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, k;
    static int[] coins;

    // * 초기 설정
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. Start Case 설정
        for (int i = 1; i <= k; i++) {
            dp[0][i] = (i % coins[0] == 0) ? 1 : 0;
        }

        // * 3. DP(Tabulation: Bottom-Up)
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= coins[i]) {
                    if (j == coins[i]) {
                        dp[i][j] = dp[i-1][j] + 1;
                    } else {
                        dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]];
                    }
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        // * 4. 정답 출력
        System.out.println(dp[n-1][k]);
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        dp = new int[n][k+1];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);
    }

}
