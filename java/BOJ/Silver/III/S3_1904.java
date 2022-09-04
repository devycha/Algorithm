/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1904
 *
 * ? 제목: 01타일
 * ? 시간 제한: 0.75초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 지원이에게 2진 수열을 가르쳐 주기 위해, 지원이 아버지는 그에게 타일들을 선물해주셨다. 그리고 이 각각의 타일들은 0 또는 1이 쓰여 있는 낱장의 타일들이다.
 * 어느 날 짓궂은 동주가 지원이의 공부를 방해하기 위해 0이 쓰여진 낱장의 타일들을 붙여서 한 쌍으로 이루어진 00 타일들을 만들었다. 결국 현재 1 하나만으로 이루어진 타일 또는 0타일을 두 개 붙인 한 쌍의 00타일들만이 남게 되었다.
 * 그러므로 지원이는 타일로 더 이상 크기가 N인 모든 2진 수열을 만들 수 없게 되었다. 예를 들어, N=1일 때 1만 만들 수 있고, N=2일 때는 00, 11을 만들 수 있다. (01, 10은 만들 수 없게 되었다.) 또한 N=4일 때는 0011, 0000, 1001, 1100, 1111 등 총 5개의 2진 수열을 만들 수 있다.
 * 우리의 목표는 N이 주어졌을 때 지원이가 만들 수 있는 모든 가짓수를 세는 것이다. 단 타일들은 무한히 많은 것으로 가정하자.
 *
 * ? 입력 & 파싱
 * 첫 번째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 1,000,000)
 *
 * 4    -> n
 *
 * ? 출력
 * 첫 번째 줄에 지원이가 만들 수 있는 길이가 N인 모든 2진 수열의 개수를 15746으로 나눈 나머지를 출력한다.
 *
 * 5
 *
 * ? 채점 결과
 * * 시간: 104ms
 * * 메모리: 15MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Silver.III;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_1904 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // * 초기 설정
    static int div = 15746;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        // * 1. 입력 받기
        int n = Integer.parseInt(br.readLine());

        // * 2. 예외 케이스 처리
        if (n <= 3) {
            System.out.println(n);
            return;
        }

        // * DP Table 초기화
        dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        /*
            dp[i-1]
            0011
            1001
            1111

            dp[i-2]
            0000
            1100
         */

        // * 3. DP(Tabulation: Bottom-Up) 
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i-1] % div + dp[i-2] % div) % div;
        }

        // * 4. 출력
        System.out.println(dp[n]);
    }

}
