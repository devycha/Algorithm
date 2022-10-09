/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/9084
 *
 * ? 제목: 동전
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 우리나라 화폐단위, 특히 동전에는 1원, 5원, 10원, 50원, 100원, 500원이 있다. 이 동전들로는 정수의 금액을 만들 수 있으며 그 방법도 여러 가지가 있을 수 있다. 예를 들어, 30원을 만들기 위해서는 1원짜리 30개 또는 10원짜리 2개와 5원짜리 2개 등의 방법이 가능하다.
 * 동전의 종류가 주어질 때에 주어진 금액을 만드는 모든 방법을 세는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 입력의 첫 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 10)가 주어진다. 각 테스트 케이스의 첫 번째 줄에는 동전의 가지 수 N(1 ≤ N ≤ 20)이 주어지고 두 번째 줄에는 N가지 동전의 각 금액이 오름차순으로 정렬되어 주어진다. 각 금액은 정수로서 1원부터 10000원까지 있을 수 있으며 공백으로 구분된다. 세 번째 줄에는 주어진 N가지 동전으로 만들어야 할 금액 M(1 ≤ M ≤ 10000)이 주어진다.
 * 편의를 위해 방법의 수는 231 - 1 보다 작고, 같은 동전이 여러 번 주어지는 경우는 없다.
 *
 * 3        -> t
 * 2        -> n
 * 1 2      -> arr[0], ... , arr[n-1]
 * 1000     -> m
 * 3        -> n
 * 1 5 10   -> arr[0], ... , arr[n-1]
 * 100      -> m
 * 2        -> n
 * 5 7      -> arr[0], ... , arr[n-1]
 * 22       -> m
 *
 * ? 출력
 * 각 테스트 케이스에 대해 입력으로 주어지는 N가지 동전으로 금액 M을 만드는 모든 방법의 수를 한 줄에 하나씩 출력한다.
 *
 * 501
 * 121
 * 1
 *
 * ? 채점 결과
 * * 시간: 0.088초
 * * 메모리: 12MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Gold.V;

import java.io.*;
import java.util.StringTokenizer;

public class G5_9084 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static int[] arr;
    
    // * 초기 설정
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < t; i++) {
            input(); // * 1. 입력 받기

            // * 2. 처음으로 나오는 동전의 Start Case 설정
            for (int j = 1; j <= m; j++) {
                dp[0][j] = j % arr[0] == 0 ? 1 : 0;
            }

            // * 3. 두번째 동전부터 DP
            for (int j = 1; j < n; j++) {
                int coin = arr[j];

                dp[j][0] = 1;
                for (int k = 1; k <= m; k++) {
                    dp[j][k] = dp[j-1][k]; // 이전 동전까지만 사용해서 나타낼 수 있는 경우를 먼저 저장
                    
                    if (k >= coin) {
                        dp[j][k] = dp[j-1][k] + dp[j][k-coin];
                    }
                }
            }

            sb.append(dp[n-1][m] + "\n");
        }

        // * 4. 출력
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());

        dp = new int[n][m+1];
    }
}
