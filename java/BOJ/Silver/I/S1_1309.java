/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * https://www.acmicpc.net/problem/1309
 *
 * ? 제목: 동물원
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 어떤 동물원에 가로로 두칸 세로로 N칸인 아래와 같은 우리가 있다.
 * 이 동물원에는 사자들이 살고 있는데 사자들을 우리에 가둘 때, 가로로도 세로로도 붙어 있게 배치할 수는 없다. 이 동물원 조련사는 사자들의 배치 문제 때문에 골머리를 앓고 있다.
 * 동물원 조련사의 머리가 아프지 않도록 우리가 2*N 배열에 사자를 배치하는 경우의 수가 몇 가지인지를 알아내는 프로그램을 작성해 주도록 하자. 사자를 한 마리도 배치하지 않는 경우도 하나의 경우의 수로 친다고 가정한다.
 *
 * ? 입력
 * 첫째 줄에 우리의 크기 N(1≤N≤100,000)이 주어진다.
 *
 * 4
 *
 * ? 출력
 * 첫째 줄에 사자를 배치하는 경우의 수를 9901로 나눈 나머지를 출력하여라.
 *
 * 41
 *
 * ? 채점 결과
 * * 시간: 0.092초
 * * 메모리: 15MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_1309 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // * 1. 입력 받기
        int n = Integer.parseInt(br.readLine());

        // * 초기 설정
        int div = 9901;

        // * DP 테이블 초기화
        int[][] dp = new int[n+1][3];

        // * Start Case 설정
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;

        /*
            0 : 해당 줄에 아무것도 선택하지 않음
            1 : 해당 줄에 왼쪽을 선택함
            2 : 해당 줄의 오른쪽을 선택함
         */

        // * 2. DP(Tabulation: Bottom-Up)
        for (int i = 2; i <= n; i++) {
            dp[i][0] = ((dp[i-1][0] % div + dp[i-1][1] % div) % div + dp[i-1][2] % div) % div;
            dp[i][1] = (dp[i-1][0] % div + dp[i-1][2]) % div;
            dp[i][2] = (dp[i-1][0] % div + dp[i-1][1] % div) % div;
        }

        // * 3. 출력
        System.out.println(
                ((dp[n][0] % div + dp[n][1] % div) % div
                + dp[n][2] % div) % div
        );
    }
}
