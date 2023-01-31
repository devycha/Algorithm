/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14466
 */
package Gold.IV;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_14466 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, k, r;
    static ArrayList<int[]>[][] farm;
    static int[][] arr;
    static ArrayList<int[]> cows;
    static boolean[][] visit;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();
        StringBuffer sb = new StringBuffer();

        int sum = 0;
        for (int[] cow : cows) {
            sum += bfs(cow);
        }

        System.out.println(sum / 2);
    }

    public static int bfs(int[] cow) {
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        visit = new boolean[n+1][n+1];
        visit[cow[0]][cow[1]] = true;
        queue.add(cow);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx <= 0 || ny <= 0 || nx > n || ny > n || visit[nx][ny]) {
                    continue;
                }

                boolean needRoad = false;
                for (int[] road : farm[cur[0]][cur[1]]) {
                    if (road[0] == nx && road[1] == ny) {
                        needRoad = true;
                    }
                }

                if (needRoad) {
                    continue;
                }

                if (arr[nx][ny] == 1) {
                    count++;
                }

                visit[nx][ny] = true;
                queue.add(new int[] {nx, ny});
            }
        }

        return k - count - 1;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n+1][n+1];
        farm = new ArrayList[n+1][n+1];
        visit = new boolean[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                farm[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            farm[r1][c1].add(new int[] {r2, c2});
            farm[r2][c2].add(new int[] {r1, c1});
        }

        cows = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[r][c] = 1;

            cows.add(new int[] {r, c});
        }
    }
}
