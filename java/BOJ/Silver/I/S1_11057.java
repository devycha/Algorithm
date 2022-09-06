/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11057
 *
 * ? 제목: 오르막 수
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.
 * 예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
 * 수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.
 *
 * ? 입력
 * 첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.
 *
 * 2
 *
 * ? 출력
 * 첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
 *
 * 55
 *
 * ? 채점 결과
 * * 시간: 76ms
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_11057 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // * 1. 입력 받기 + 초기 설정
        int n = Integer.parseInt(br.readLine());
        int div = 10_007;

        // * 2. DP Table 초기화 + Start Case 설정
        int[][] dp = new int[10][n+1];
        for (int i = 0; i < 10; i++) {
            dp[i][1] = 1;
        }

        // * 3. DP(Tabulation: Bottom-Up)
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < 10; i++) {
                for (int k = i; k < 10; k++) {
                    dp[k][j+1] = (dp[k][j+1] % div + dp[i][j] % div ) % div;
                }
            }
        }

        // * 4. 정답 계산 및 출력
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer % div + dp[i][n] % div) % div;
        }
        System.out.println(answer);
    }
}
