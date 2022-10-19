/**
 * ? 문제 출처;
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_15486 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[][] arr;

    static int max = 0;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            if (i + arr[i][0] <= n + 1) {
                dp[i + arr[i][0]] = Math.max(dp[i + arr[i][0]], dp[i] + arr[i][1]);
                max = Math.max(dp[i + arr[i][0]], max);
            }
        }

        System.out.println(max);
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 2][2];
        dp = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
