/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1915
 *
 * ? 제목: 가장 큰 정사각형
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * n×m의 0, 1로 된 배열이 있다. 이 배열에서 1로 된 가장 큰 정사각형의 크기를 구하는 프로그램을 작성하시오.
 * 0	1	0	0
 * 0	1	1	1
 * 1	1	1	0
 * 0	0	1	0
 * 위와 같은 예제에서는 가운데의 2×2 배열이 가장 큰 정사각형이다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 n, m(1 ≤ n, m ≤ 1,000)이 주어진다. 다음 n개의 줄에는 m개의 숫자로 배열이 주어진다.
 *
 * 4 4  -> n m
 * 0100 -> arr[0][0] ~ arr[0][m-1]
 * 0111
 * 1110
 * 0010 -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * 첫째 줄에 가장 큰 정사각형의 넓이를 출력한다.
 *
 * 4
 *
 * ? 채점 결과
 * * 시간: 0.236초
 * * 메모리: 25MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_1915 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static char[][] arr;

    // * 초기 설정
    static int max = 0;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP Table 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = arr[i][j] - '0';
            }
        }

        // * 3. DP(Tabulation: Bottom-Up)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    max = Math.max(max, arr[i][j] - '0');
                    continue;
                }

                if (arr[i][j] == '0') {
                    continue;
                }
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                max = Math.max(dp[i][j], max);
            }
        }

        // * 4. 출력
        System.out.println(max * max);
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
