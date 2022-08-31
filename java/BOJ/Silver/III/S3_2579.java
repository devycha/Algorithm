/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2579
 *
 * ? 제목: 계단 오르기
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
 * 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
 * 마지막 도착 계단은 반드시 밟아야 한다.
 * 따라서 첫 번째 계단을 밟고 이어 두 번째 계단이나, 세 번째 계단으로 오를 수 있다. 하지만, 첫 번째 계단을 밟고 이어 네 번째 계단으로 올라가거나, 첫 번째, 두 번째, 세 번째 계단을 연속해서 모두 밟을 수는 없다.
 * 각 계단에 쓰여 있는 점수가 주어질 때 이 게임에서 얻을 수 있는 총 점수의 최댓값을 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 입력의 첫째 줄에 계단의 개수가 주어진다.
 * 둘째 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다. 계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10,000이하의 자연수이다.
 *
 * 6    -> n
 * 10   -> stair[1]
 * 20
 * 15
 * 25
 * 10
 * 20   -> stair[n]
 *
 * ? 출력
 * 첫째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값을 출력한다.
 *
 * 75
 *
 * ? 채점 결과
 * * 시간: 76ms
 * * 메모리: 115MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Silver.III;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_2579 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] stair;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input();

        if (n == 1) {
            System.out.println(stair[n]);
            return;
        }

        dp[1] = stair[1];
        dp[2] = stair[1] + stair[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i-3] + stair[i-1], dp[i-2]) + stair[i];
        }

        System.out.println(dp[n]);

    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        stair = new int[n + 1];
        dp = new int[n + 1];

        for (int i = 1 ; i <= n; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }
    }
}
