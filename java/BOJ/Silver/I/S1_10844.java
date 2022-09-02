/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/10844
 *
 * ? 제목: 쉬운 계단 수
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 45656이란 수를 보자.
 * 이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
 * N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 *
 * 1 -> n
 *
 * ? 출력
 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 *
 * 9
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

public class S1_10844 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        // * 1. 입력 받기
        int n = Integer.parseInt(br.readLine());

        // * 초기 설정
        int answer = 0;
        int div = 1_000_000_000;
        int[][] dp = new int[10][n+1];

        // * 2. DP(Tabulation)
        for (int i = 1; i <= 9; i++) {
            dp[i][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j-1 >= 0) {
                    dp[j-1][i+1] = (dp[j-1][i+1] % div + dp[j][i] % div) % div;
                }

                if (j+1 < 10) {
                    dp[j+1][i+1] = (dp[j+1][i+1] % div + dp[j][i] % div) % div;
                }
            }
        }

        // * 3. 1의자리수 0부터 9까지 나올 수 있는 모든 경우의 수 합(+나머지연산)
        for (int i = 0; i < 10; i++) {
            answer = (answer % div + dp[i][n] % div) % div;
        }

        // * 4. 정답 출력
        System.out.println(answer);
    }
}
