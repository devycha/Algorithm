/**
 * ? https://www.acmicpc.net/problem/2098
 * ? 58% 시간초과
 */
package failed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G1_2098 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int MAX = 987654321;
    static int[][] dist;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 1);
        System.out.println(dist[0][1]);
    }

    public static int dfs(int cur, int visit) {
        if (visit == (1 << n) - 1) {
            if (arr[cur][0] == 0) {
                return MAX;
            }
            return arr[cur][0];
        }

        if (dist[cur][visit] != MAX) {
            return dist[cur][visit];
        }

        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) == 0 && arr[cur][i] != 0) {
                dist[cur][visit] = Math.min(dist[cur][visit],
                        dfs(i, visit | (1 << i)) + arr[cur][i]);
            }
        }

        return dist[cur][visit];
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        dist = new int[n][(1 << n)];
        for (int[] d : dist) {
            Arrays.fill(d, MAX);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
