/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1912
 *
 * ? 제목: 연속합
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다.
 * 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 정답은 12+21인 33이 정답이 된다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
 *
 * 10                           -> n
 * 10 -4 3 1 5 6 -35 12 21 -1   -> arr[0] ~ arr[n-1]
 *
 * ? 출력
 * 첫째 줄에 답을 출력한다.
 *
 * 33
 *
 * ? 채점 결과
 * * 시간: 220ms
 * * 메모리: 22MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_1912 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[] arr;

    // * 초기 설정
    static int max; // 연속합 최댓값
    static int[] dp; // 연속합 최댓값 DP

    // # 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        max = arr[0]; // 최댓값 초기화
        dp[0] = arr[0]; // 시작값 초기화

        // * 2. DP(Tabulation)
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i] + dp[i-1], arr[i]);
            max = Math.max(max, dp[i]);
        }

        // * 3. 정답 출력
        System.out.println(max);
    }

    // # 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        dp = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
