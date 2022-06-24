package Gold4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_1956 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        int min = 987654321;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] floyd = new int[n+1][n+1];
        for (int[] arr: floyd) {
            Arrays.fill(arr, min);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            floyd[a][b] = c;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (floyd[i][j] > floyd[i][k] + floyd[k][j]) {
                        floyd[i][j] = floyd[i][k] + floyd[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (min > floyd[i][i]) min = floyd[i][i];
        }

        bw.write(min == 987654321 ? "-1" : min+"");
        bw.close();
    }
}
