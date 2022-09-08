/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11048
 *
 * ? 제목: 이동하기
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 준규는 N×M 크기의 미로에 갇혀있다. 미로는 1×1크기의 방으로 나누어져 있고, 각 방에는 사탕이 놓여져 있다. 미로의 가장 왼쪽 윗 방은 (1, 1)이고, 가장 오른쪽 아랫 방은 (N, M)이다.
 * 준규는 현재 (1, 1)에 있고, (N, M)으로 이동하려고 한다. 준규가 (r, c)에 있으면, (r+1, c), (r, c+1), (r+1, c+1)로 이동할 수 있고, 각 방을 방문할 때마다 방에 놓여져있는 사탕을 모두 가져갈 수 있다. 또, 미로 밖으로 나갈 수는 없다.
 * 준규가 (N, M)으로 이동할 때, 가져올 수 있는 사탕 개수의 최댓값을 구하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 미로의 크기 N, M이 주어진다. (1 ≤ N, M ≤ 1,000)
 * 둘째 줄부터 N개 줄에는 총 M개의 숫자가 주어지며, r번째 줄의 c번째 수는 (r, c)에 놓여져 있는 사탕의 개수이다. 사탕의 개수는 0보다 크거나 같고, 100보다 작거나 같다.
 *
 * 3 4      -> n m
 * 1 2 3 4  -> arr[0][0] ~ arr[0][m-1]
 * 0 0 0 5
 * 9 8 7 6  -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * 첫째 줄에 준규가 (N, M)으로 이동할 때, 가져올 수 있는 사탕 개수를 출력한다.
 *
 * 31
 *
 * ? 채점 결과
 * * 시간: 0.5초
 * * 메모리: 75MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_11048 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static int[][] arr;

    // * 초기 설정
    static int[][] dp;

    // * 방향 설정
    static int[] dx = {1, 1, 0};
    static int[] dy = {1, 0, 1};

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP(Tabulation: Bottom-Up)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 3; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];

                    if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                        continue;
                    }

                    dp[ny][nx] = Math.max(dp[ny][nx], dp[i][j] + arr[ny][nx]);
                }
            }
        }

        // * 3. 출력
        System.out.println(dp[n-1][m-1]);
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = arr[i][j];
            }
        }
    }
}
