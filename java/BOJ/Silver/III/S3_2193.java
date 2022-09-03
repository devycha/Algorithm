/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2193
 *
 * ? 제목: 이친수
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 0과 1로만 이루어진 수를 이진수라 한다. 이러한 이진수 중 특별한 성질을 갖는 것들이 있는데, 이들을 이친수(pinary number)라 한다. 이친수는 다음의 성질을 만족한다.
 * 이친수는 0으로 시작하지 않는다.
 * 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
 * 예를 들면 1, 10, 100, 101, 1000, 1001 등이 이친수가 된다. 하지만 0010101이나 101101은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다.
 * N(1 ≤ N ≤ 90)이 주어졌을 때, N자리 이친수의 개수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 N이 주어진다.
 *
 * 3 -> n
 *
 * ? 출력
 * 첫째 줄에 N자리 이친수의 개수를 출력한다.
 *
 * 2
 *
 * ? 채점 결과
 * * 시간: 76ms
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Silver.III;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_2193 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // * 파싱
    static int n;

    // * 초기 설정
    static long[] dp;

    public static void main(String[] args) throws IOException {
        // * 1. 입력 받기
        n = Integer.parseInt(br.readLine());

        // * 예외 처리
        if (n == 1) {
            System.out.println(1);
            return;
        }

        // * 2. end case
        dp = new long[n+1];
        dp[1] = 1;
        dp[2] = 1;

        // * 3. DP(Memoization)
        topDown(n);

        // * 4. 출력
        System.out.println(dp[n]);
    }

    // # DP(Top-Down: Memoization)
    public static long topDown(int idx) {
        if (dp[idx] > 0) {
            return dp[idx];
        }

        dp[idx] = topDown(idx-1) + topDown(idx-2);
        return dp[idx];
    }
}
