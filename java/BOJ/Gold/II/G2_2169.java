/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2169
 * <p>
 * ? 제목: 로봇 조종하기
 * ? 시간 제한: 1초
 * ? 메모리 제한: 512MB
 * <p>
 * ? 문제
 * NASA에서는 화성 탐사를 위해 화성에 무선 조종 로봇을 보냈다. 실제 화성의 모습은 굉장히 복잡하지만, 로봇의 메모리가 얼마 안 되기 때문에 지형을 N×M 배열로 단순화 하여 생각하기로 한다.
 * 지형의 고저차의 특성상, 로봇은 움직일 때 배열에서 왼쪽, 오른쪽, 아래쪽으로 이동할 수 있지만, 위쪽으로는 이동할 수 없다. 또한 한 번 탐사한 지역(배열에서 하나의 칸)은 탐사하지 않기로 한다.
 * 각각의 지역은 탐사 가치가 있는데, 로봇을 배열의 왼쪽 위 (1, 1)에서 출발시켜 오른쪽 아래 (N, M)으로 보내려고 한다. 이때, 위의 조건을 만족하면서, 탐사한 지역들의 가치의 합이 최대가 되도록 하는 프로그램을 작성하시오.
 * <p>
 * ? 입력 & 파싱
 * 첫째 줄에 N, M(1≤N, M≤1,000)이 주어진다. 다음 N개의 줄에는 M개의 수로 배열이 주어진다. 배열의 각 수는 절댓값이 100을 넘지 않는 정수이다. 이 값은 그 지역의 가치를 나타낸다.
 * <p>
 * 5 5                  -> n m
 * 10 25 7 8 13         -> arr[1][1] ~ arr[1][m]
 * 68 24 -78 63 32
 * 12 -69 100 -29 -25
 * -16 -22 -57 -33 99
 * 7 -76 -11 77 15      -> arr[n][1] ~ arr[n][m]
 * <p>
 * ? 출력
 * 첫째 줄에 최대 가치의 합을 출력한다.
 * <p>
 * 319
 * <p>
 * ? 채점 결과
 * * 시간: 0.628초
 * * 메모리: 116MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_2169 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static int[][] arr;

    static int MIN = -100_000_000;
    static boolean[][] visit;
    static int[][][] dp;

    // 0 : 아래, 1: 오른쪽, 2: 왼쪽
    static int[] dx = {1, 0, 0};
    static int[] dy = {0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();
        
        System.out.println(
                Math.max(
                        findMax(1, 1, 0),
                        findMax(1, 1, 1))
        );
    }

    public static int findMax(int x, int y, int dir) {
        if (x == n && y == m) {
            return arr[n][m];
        }

        if (dp[x][y][dir] != 0) {
            return dp[x][y][dir];
        }

        visit[x][y] = true;
        int max = MIN;

        // 0 : 아래, 1: 오른쪽, 2: 왼쪽
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (1 <= nx && 1 <= ny && nx <= n && ny <= m && !visit[nx][ny]) {
                max = Math.max(max, findMax(nx, ny, i) + arr[x][y]);
            }
        }

        visit[x][y] = false;
        return dp[x][y][dir] = max;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][m + 1];
        visit = new boolean[n + 1][m + 1];
        dp = new int[n + 1][m + 1][3];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
