/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1981
 *
 * ? 제목: 배열에서 이동
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * n×n짜리의 배열이 하나 있다. 이 배열의 (1, 1)에서 (n, n)까지 이동하려고 한다. 이동할 때는 상, 하, 좌, 우의 네 인접한 칸으로만 이동할 수 있다.
 * 이와 같이 이동하다 보면, 배열에서 몇 개의 수를 거쳐서 이동하게 된다. 이동하기 위해 거쳐 간 수들 중 최댓값과 최솟값의 차이가 가장 작아지는 경우를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 n(2 ≤ n ≤ 100)이 주어진다. 다음 n개의 줄에는 배열이 주어진다. 배열의 각 수는 0보다 크거나 같고, 200보다 작거나 같은 정수이다.
 *
 * 5            -> n
 * 1 1 3 6 8    -> arr[0][0] ~ arr[0][n-1]
 * 1 2 2 5 5
 * 4 4 0 3 3
 * 8 0 2 3 4
 * 4 3 0 2 1    -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 첫째 줄에 (최대 - 최소)가 가장 작아질 때의 그 값을 출력한다.
 *
 * 2
 *
 * ? 채점 결과
 * * 시간: 0.508초
 * * 메모리: 293MB
 * * 언어: JAVA8
 * * 시도: 8
 */
package Gold.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G1_1981 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[][] arr;

    static int MIN = 200;
    static int MAX = 0;
    static boolean[][] visit;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();

        int left = 0;
        int right = MAX - MIN;
        while (left <= right) {
            int gap = (left + right) / 2;
            boolean canGo = bfs(gap);

            if (canGo) {
                right = gap-1;
            } else {
                left = gap+1;
            }
        }

        System.out.println(left);
    }

    public static boolean bfs(int gap) {
        Queue<int[]> queue;
        for (int m = MIN; m + gap <= MAX; m++) {
            if (arr[0][0] < m || m + gap < arr[0][0]) {
                continue;
            }

            queue = new LinkedList<>();
            visit = new boolean[n][n];
            visit[0][0] = true;
            queue.add(new int[] {0, 0});

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                if (cur[0] == n-1 && cur[1] == n-1) {
                    return true;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny]) {
                        continue;
                    }

                    if (m <= arr[nx][ny] && arr[nx][ny] <= m + gap) {
                        visit[nx][ny] = true;
                        queue.add(new int[] {nx, ny});
                    }

                }
            }
        }

        return false;
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                MAX = Math.max(MAX, arr[i][j]);
                MIN = Math.min(MIN, arr[i][j]);
            }
        }
    }
}
