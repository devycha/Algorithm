/**
 *  ? 문제 출처: 백준 온라인 져지(BOJ)
 *  ? https://www.acmicpc.net/problem/15988
 *
 *  ? 제목: 1, 2, 3 더하기 3
 *  ? 시간 제한: 1초
 *  ? 메모리 제한: 512MB
 *
 *  ? 문제
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
 * 1+1+1+1
 * 1+1+2
 * 1+2+1
 * 2+1+1
 * 2+2
 * 1+3
 * 3+1
 * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 1,000,000보다 작거나 같다.
 *
 * 3    -> t
 * 4    -> num
 * 7    -> num
 * 10   -> num
 *
 * ? 출력
 * 각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 1,000,000,009로 나눈 나머지를 출력한다.
 *
 * 7
 * 44
 * 274
 *
 * ? 채점 결과
 * * 시간: 0.124초
 * * 메모리: 15MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Silver.II;

import java.io.*;

public class S2_15988 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        // * 초기 설정
        int[] dp = new int[1_000_001];
        StringBuffer sb = new StringBuffer();
        int div = 1_000_000_009;

        // * Start Case
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        // * 1. DP(Tabulation: Bottom-Up)
        for (int i = 4; i < dp.length; i++) {
            dp[i] = ((dp[i-1] % div + dp[i-2] % div) % div + dp[i-3] % div) % div;
        }

        // * 2. 입력 받기
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num] + "\n");
        }

        // * 3. 출력
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}
