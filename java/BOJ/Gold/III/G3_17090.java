/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/17090
 *
 * ? 제목: 미로 탈출하기
 * ? 시간 제한: 1초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 크기가 N×M인 미로가 있고, 미로는 크기가 1×1인 칸으로 나누어져 있다. 미로의 각 칸에는 문자가 하나 적혀있는데, 적혀있는 문자에 따라서 다른 칸으로 이동할 수 있다.
 * 어떤 칸(r, c)에 적힌 문자가
 *
 * U인 경우에는 (r-1, c)로 이동해야 한다.
 * R인 경우에는 (r, c+1)로 이동해야 한다.
 * D인 경우에는 (r+1, c)로 이동해야 한다.
 * L인 경우에는 (r, c-1)로 이동해야 한다.
 *
 * 미로에서 탈출 가능한 칸의 수를 계산해보자. 탈출 가능한 칸이란, 그 칸에서 이동을 시작해서 칸에 적힌대로 이동했을 때, 미로의 경계 밖으로 이동하게 되는 칸을 의미한다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 미로의 크기 N, M(3 ≤ N, M ≤ 500)이 주어진다. 둘째 줄부터 N개의 줄에는 미로의 각 칸에 적힌 문자가 주어진다.
 *
 * 3 3  -> n m
 * DDD  -> arr[0][0] ~ arr[0][m-1]
 * DDD
 * DDD  -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * 첫째 줄에 탈출 가능한 칸의 수를 출력한다.
 *
 * 9
 *
 * ? 채점 결과
 * * 시간: 0.208초
 * * 메모리: 57MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.III;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class G3_17090 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static char[][] arr;
    static boolean[][] visit;
    static int[][] answer;
    static Map<Character, int[]> dir;

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (answer[i][j] == 0) {
                    visit[i][j] = true;
                    dfs(i, j);
                    visit[i][j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (answer[i][j] == 1) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    public static int dfs(int x, int y) {
        int[] d = dir.get(arr[x][y]);
        int nx = x + d[0];
        int ny = y + d[1];

        if (answer[x][y] != 0) {
            return answer[x][y];
        }

        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
            return answer[x][y] = 1;
        }

        if (visit[nx][ny]) {
            return answer[x][y] = -1;
        }

        visit[nx][ny] = true;
        answer[x][y] = dfs(nx, ny);
        visit[nx][ny] = false;

        return answer[x][y];
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m];
        answer = new int[n][m];
        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        dir = new HashMap<>();
        dir.put('D', new int[] {1, 0});
        dir.put('U', new int[] {-1, 0});
        dir.put('L', new int[] {0, -1});
        dir.put('R', new int[] {0, 1});
    }
}
