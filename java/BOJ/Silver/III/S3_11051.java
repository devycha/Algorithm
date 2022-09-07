/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11051
 *
 * ? 제목: 이항 계수 2
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 자연수 \(N\)과 정수 \(K\)가 주어졌을 때 이항 계수
 * \(\binom{N}{K}\)를 10,007로 나눈 나머지를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 \(N\)과 \(K\)가 주어진다. (1 ≤ \(N\) ≤ 1,000, 0 ≤ \(K\) ≤ \(N\))
 *
 * 5 2  -> n k
 *
 * ? 출력
 * ( N )
 * ( K )
 * 를 10,007로 나눈 나머지를 출력한다.
 *
 * 10
 *
 * ? 채점 결과
 * * 시간: 96ms
 * * 메모리: 15MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.III;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_11051 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        // * 1. 입력 받기
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // * 초기 설정
        int div = 10_007;

        // * 2. DP Table 초기화, Start Case 설정
        int[][] dp = new int[n+1][n+1];
        dp[0][0] = 1;

        // * 3. DP(Tabulation: Bottom-Up) - 파스칼의 삼각형(이항계수)
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == n) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = (dp[i-1][j-1] % div + dp[i-1][j] % div) % div;
                }
            }
        }

        // * 4. 정답 출력
        System.out.println(dp[n][k]);
    }
}
