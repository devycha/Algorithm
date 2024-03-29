/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2156
 *
 * ? 제목: 포도주 시식
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 효주는 포도주 시식회에 갔다. 그 곳에 갔더니, 테이블 위에 다양한 포도주가 들어있는 포도주 잔이 일렬로 놓여 있었다. 효주는 포도주 시식을 하려고 하는데, 여기에는 다음과 같은 두 가지 규칙이 있다.
 * 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
 * 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
 * 효주는 될 수 있는 대로 많은 양의 포도주를 맛보기 위해서 어떤 포도주 잔을 선택해야 할지 고민하고 있다. 1부터 n까지의 번호가 붙어 있는 n개의 포도주 잔이 순서대로 테이블 위에 놓여 있고, 각 포도주 잔에 들어있는 포도주의 양이 주어졌을 때, 효주를 도와 가장 많은 양의 포도주를 마실 수 있도록 하는 프로그램을 작성하시오.
 * 예를 들어 6개의 포도주 잔이 있고, 각각의 잔에 순서대로 6, 10, 13, 9, 8, 1 만큼의 포도주가 들어 있을 때, 첫 번째, 두 번째, 네 번째, 다섯 번째 포도주 잔을 선택하면 총 포도주 양이 33으로 최대로 마실 수 있다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 포도주 잔의 개수 n이 주어진다. (1 ≤ n ≤ 10,000) 둘째 줄부터 n+1번째 줄까지 포도주 잔에 들어있는 포도주의 양이 순서대로 주어진다. 포도주의 양은 1,000 이하의 음이 아닌 정수이다.
 *
 * 6    -> n
 * 6    -> arr[1]
 * 10
 * 13
 * 9
 * 8
 * 1    -> arr[n]
 *
 * ? 출력
 * 첫째 줄에 최대로 마실 수 있는 포도주의 양을 출력한다.
 *
 * 33
 *
 * ? 채점 결과
 * * 시간: 96ms
 * * 메모리: 13MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Silver.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_2156 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // * 파싱
    static int n;
    static int[] arr;
    
    // * 초기 설정
    static int[] dp;

    // # 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // 길이가 1인 경우(예외 케이스)
        if (n == 1) {
            System.out.println(arr[1]);
            return;
        }

        // * 2. DP(Tabulation)
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1],
                    Math.max(dp[i - 2] + arr[i],
                            dp[i - 3] + arr[i - 1] + arr[i]));
        }

        // * 3. 정답 출력
        System.out.println(dp[n]);
    }

    // # 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];
        dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }
}
