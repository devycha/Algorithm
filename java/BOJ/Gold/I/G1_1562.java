/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1562
 * <p>
 * ? 제목: 계단 수
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 * <p>
 * ? 문제
 * 45656이란 수를 보자.
 * 이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
 * N이 주어질 때, 길이가 N이면서 0부터 9까지 숫자가 모두 등장하는 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. 0으로 시작하는 수는 계단수가 아니다.
 * <p>
 * ? 입력 & 파싱
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 * <p>
 * 10   -> n
 * <p>
 * ? 출력
 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 * <p>
 * 1
 * <p>
 * ? 채점 결과
 * * 시간: 0.124초
 * * 메모리: 16MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G1_1562 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int div = 1_000_000_000;

        int[][][] dp = new int[n + 1][10][1 << 10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    if (j > 0) {
                        int bit = k | (1 << (j - 1));
                        dp[i + 1][j - 1][bit] = (dp[i + 1][j - 1][bit] + dp[i][j][k]) % div;
                    }

                    if (j < 9) {
                        int bit = k | (1 << (j + 1));
                        dp[i + 1][j + 1][bit] = (dp[i + 1][j + 1][bit] + dp[i][j][k]) % div;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer = (answer + dp[n][i][(1 << 10) - 1]) % div;
        }

        System.out.println(answer);
    }
}