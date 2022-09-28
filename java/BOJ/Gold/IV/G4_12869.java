/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/12869
 *
 * ? 제목: 뮤탈리스크
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 수빈이는 강호와 함께 스타크래프트 게임을 하고 있다. 수빈이는 뮤탈리스크 1개가 남아있고, 강호는 SCV N개가 남아있다.
 * 각각의 SCV는 남아있는 체력이 주어져있으며, 뮤탈리스크를 공격할 수는 없다. 즉, 이 게임은 수빈이가 이겼다는 것이다.
 * 뮤탈리스크가 공격을 할 때, 한 번에 세 개의 SCV를 공격할 수 있다.
 *
 * 첫 번째로 공격받는 SCV는 체력 9를 잃는다.
 * 두 번째로 공격받는 SCV는 체력 3을 잃는다.
 * 세 번째로 공격받는 SCV는 체력 1을 잃는다.
 *
 * SCV의 체력이 0 또는 그 이하가 되어버리면, SCV는 그 즉시 파괴된다. 한 번의 공격에서 같은 SCV를 여러 번 공격할 수는 없다.
 * 남아있는 SCV의 체력이 주어졌을 때, 모든 SCV를 파괴하기 위해 공격해야 하는 횟수의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 SCV의 수 N (1 ≤ N ≤ 3)이 주어진다. 둘째 줄에는 SCV N개의 체력이 주어진다. 체력은 60보다 작거나 같은 자연수이다.
 *
 * 3        -> n
 * 12 10 4  -> arr[0] arr[1] arr[2]
 *
 * ? 출력
 * 첫째 줄에 모든 SCV를 파괴하기 위한 공격 횟수의 최솟값을 출력한다.
 *
 * 2
 *
 * ? 채점 결과
 * * 시간: 0.088초
 * * 메모리: 12MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_12869 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, answer;
    static int[] arr;

    // * 뮤탈리스크 공격 순서 경우
    static int[] dx = {9, 3, 1, 9, 3, 1};
    static int[] dy = {3, 9, 3, 1, 1, 9};
    static int[] dz = {1, 1, 9, 3, 9, 3};

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2-1. n = 1일 때
        if (n == 1) {
            answer = arr[0] % 9 == 0 ? arr[0] / 9 : arr[0] / 9 + 1;
        }

        // * 2-2. n = 2일 때: BFS
        else if (n == 2) {
            int[][] dp = new int[61][61];
            dp[arr[0]][arr[1]] = 1;

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] {arr[0], arr[1]});

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                if (cur[0] == 0 && cur[1] == 0) {
                    answer = dp[0][0] - 1;
                }

                for (int i = 0; i < 2; i++) {
                    int nx = cur[0] - dx[i];
                    int ny = cur[1] - dy[i];

                    nx = Math.max(nx, 0);
                    ny = Math.max(ny, 0);

                    if (dp[nx][ny] == 0) {
                        dp[nx][ny] = dp[cur[0]][cur[1]] + 1;
                        queue.add(new int[] {nx, ny});
                    }

                }
            }
        }

        // * 2-3. n = 3일 때: BFS
        else {
            int[][][] dp = new int[61][61][61];
            dp[arr[0]][arr[1]][arr[2]] = 1;

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] {arr[0], arr[1], arr[2]});

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                if (cur[0] == 0 && cur[1] == 0 && cur[2] == 0) {
                    answer = dp[0][0][0] - 1;
                }

                for (int i = 0; i < 6; i++) {
                    int nx = cur[0] - dx[i];
                    int ny = cur[1] - dy[i];
                    int nz = cur[2] - dz[i];

                    nx = Math.max(nx, 0);
                    ny = Math.max(ny, 0);
                    nz = Math.max(nz, 0);

                    if (dp[nx][ny][nz] == 0) {
                        dp[nx][ny][nz] = dp[cur[0]][cur[1]][cur[2]] + 1;
                        queue.add(new int[] {nx, ny, nz});
                    }
                }
            }
        }

        // * 3. 출력
        System.out.println(answer);
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[n] = 0;
    }
}
