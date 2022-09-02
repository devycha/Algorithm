/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11727
 *
 * ? 제목: 2xn 타일링 2
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 2×n 직사각형을 1×2, 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
 *
 * 12   -> n
 *
 * ? 출력
 * 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
 *
 * 2731
 *
 * ? 채점 결과
 * * 시간: 76ms
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.III;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_11727 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // * 파싱
    static int n;
    
    // * 초기 설정
    static int div = 10_007;
    static int[] dp;

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        // * 1 입력 받기
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];

        // * 2. DP(tabulation) + 3. 출력
        System.out.println(tile());
    }

    // ! DP(tabulation)
    public static int tile() {
        if (n == 1) {
            return 1;
        }

        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = ((dp[i-2] % div + dp[i-2] % div) % div + dp[i-1] % div) % div;
        }

        return dp[n];
    }
}
