/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/13424
 */
package Gold.IV;

import java.io.*;
import java.util.StringTokenizer;

public class G4_13424 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m, k;
    static int answer;
    static int[][] adj;
    static int[] friend;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        for (int testcase = 0; testcase < t; testcase++) {
            input();
            floyd();

            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                int sum = 0;
                for (int f : friend) {
                    sum += adj[f][i];
                }

                if (min > sum) {
                    min = sum;
                    answer = i;
                } else if (min == sum) {
                    answer = Math.min(answer, i);
                }
            }

            sb.append(answer + "\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (k == i || i == j) {
                        continue;
                    }

                    if (adj[i][j] > adj[i][k] + adj[k][j]) {
                        adj[i][j] = adj[i][k] + adj[k][j];
                    }
                }
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    adj[i][j] = 0;
                } else {
                    adj[i][j] = 1_000_000;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a][b] = Math.min(adj[a][b], c);
            adj[b][a] = Math.min(adj[b][a], c);
        }

        k = Integer.parseInt(br.readLine());
        friend = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            friend[i] = Integer.parseInt(st.nextToken());
        }
    }
}
