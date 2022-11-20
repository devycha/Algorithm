/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16932
 *
 * ? 제목: 모양 만들기
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * N×M인 배열에서 모양을 찾으려고 한다. 배열의 각 칸에는 0과 1 중의 하나가 들어있다. 두 칸이 서로 변을 공유할때, 두 칸을 인접하다고 한다.
 * 1이 들어 있는 인접한 칸끼리 연결했을 때, 각각의 연결 요소를 모양이라고 부르자. 모양의 크기는 모양에 포함되어 있는 1의 개수이다.
 * 배열의 칸 하나에 들어있는 수를 변경해서 만들 수 있는 모양의 최대 크기를 구해보자.
 *
 * ? 입력
 * 첫째 줄에 배열의 크기 N과 M이 주어진다. 둘째 줄부터 N개의 줄에는 배열에 들어있는 수가 주어진다.
 *
 * 3 3      -> n m
 * 0 1 1    -> arr[0][0] ~ arr[0][m-1]
 * 0 0 1
 * 0 1 0    -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * 첫째 줄에 수 하나를 변경해서 만들 수 있는 모양의 최대 크기를 출력한다.
 *
 * 5
 *
 * ? 제한
 * 2 ≤ N, M ≤ 1,000
 * 0과 1의 개수는 하나 이상이다.
 *
 * ? 채점 결과
 * * 시간: 3.452초
 * * 메모리: 209MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.III;

import java.io.*;
import java.util.*;

public class G3_16932 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m;
    static int[][] arr, groupNum;
    static boolean[][] visit;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int[] group;
    static int num;

    public static void main(String[] args) throws IOException {
        input();
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !visit[i][j]) {
                    num++;
                    visit[i][j] = true;
                    groupNum[i][j] = num;
                    group[num] = 1;
                    dfs(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    int count = 0;
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                            continue;
                        }

                        if (groupNum[nx][ny] != 0) {
                            set.add(groupNum[nx][ny]);
                        }
                    }

                    for (int g : set) {
                        count += group[g];
                    }

                    max = Math.max(max, count);
                }
            }
        }

        System.out.println(max+1);
    }

    public static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny]) {
                continue;
            }

            if (arr[nx][ny] == 1) {
                visit[nx][ny] = true;
                groupNum[nx][ny] = num;
                group[num]++;
                dfs(nx, ny);
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visit = new boolean[n][m];
        groupNum = new int[n][m];

        num = 0;

        int count = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    count++;
                }
            }
        }

        group = new int[count+1];
    }
}
