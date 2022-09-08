/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2294
 *
 * ? 제목: 동전 2
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.
 * 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다. 가치가 같은 동전이 여러 번 주어질 수도 있다.
 *
 * 3 15 -> n k
 * 1    -> HashSet 삽입
 * 5
 * 12
 *
 * ? 출력
 * 첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.
 *
 * 3
 *
 * ? 채점 결과
 * * 시간: 0.1초
 * * 메모리: 16MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class G5_2294 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, k;
    static HashSet<Integer> coins;

    // * 초기 설정
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP(Tabulation: Bottom-Up)
        int p = 0;
        for (int coin : coins) {
            // * 가장 단위가 작은 동전일 때
            if (p == 0) {
                for (int j = 1; j * coin <= k; j++) {
                    dp[p][j * coin] = j;
                }
                p++;
                continue;
            }

            for (int j = 0; j <= k; j++) {
                if (j < coin) {
                    dp[p][j] = dp[p-1][j];
                } else {
                    dp[p][j] = Math.min(dp[p-1][j], dp[p][j-coin] + 1);
                }
            }
            p++;
        }

        // * 3. 정답 출력(불가능한 경우 값이 100000이므로 그때는 -1)
        System.out.println(dp[dp.length-1][k] == 100000 ? -1 : dp[dp.length-1][k]);
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new HashSet<>();
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        dp = new int[coins.size()][k+1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = 100000; // 충분히 큰 수로 DP 테이블 초기화
            }
        }
    }
}
