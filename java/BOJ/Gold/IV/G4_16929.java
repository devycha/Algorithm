/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16929
 *
 * ? 제목: Two Dots
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * Two Dots는 Playdots, Inc.에서 만든 게임이다. 게임의 기초 단계는 크기가 N×M인 게임판 위에서 진행된다.
 * 각각의 칸은 색이 칠해진 공이 하나씩 있다. 이 게임의 핵심은 같은 색으로 이루어진 사이클을 찾는 것이다.
 * 다음은 위의 게임판에서 만들 수 있는 사이클의 예시이다.
 * 점 k개 d1, d2, ..., dk로 이루어진 사이클의 정의는 아래와 같다.
 *
 * 모든 k개의 점은 서로 다르다.
 * k는 4보다 크거나 같다.
 * 모든 점의 색은 같다.
 * 모든 1 ≤ i ≤ k-1에 대해서, di와 di+1은 인접하다. 또, dk와 d1도 인접해야 한다. 두 점이 인접하다는 것은 각각의 점이 들어있는 칸이 변을 공유한다는 의미이다.
 * 게임판의 상태가 주어졌을 때, 사이클이 존재하는지 아닌지 구해보자.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 게임판의 크기 N, M이 주어진다. 둘째 줄부터 N개의 줄에 게임판의 상태가 주어진다. 게임판은 모두 점으로 가득차 있고, 게임판의 상태는 점의 색을 의미한다. 점의 색은 알파벳 대문자 한 글자이다.
 *
 * 3 4  -> n m
 * AAAA -> arr[0][0] ~ arr[0][m-1]
 * ABCA
 * AAAA -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * 사이클이 존재하는 경우에는 "Yes", 없는 경우에는 "No"를 출력한다.
 *
 * Yes
 *
 * ? 제한
 *
 * 2 ≤ N, M ≤ 50
 *
 * ? 채점 결과
 * * 시간: 0.076초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.IV;

import java.io.*;
import java.util.StringTokenizer;

public class G4_16929 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static char[][] arr;

    // * 초기 설정
    static boolean hasCycle = false;
    static boolean[][] visit;

    // * 상하좌우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DFS
        dfsLoop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    dfs(i, j, i, j);
                }

                if (hasCycle) {
                    break dfsLoop;
                }
            }
        }

        // * 3. 출력
        bw.write(hasCycle ? "Yes" : "No");

        br.close();
        bw.flush();
        bw.close();
    }

    // DFS
    public static void dfs(int x, int y, int bx, int by) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || (nx == bx && ny == by)) {
                continue;
            }

            if (arr[nx][ny] == arr[x][y]) {
                if (visit[nx][ny]) {
                    hasCycle = true;
                    return;
                }

                visit[nx][ny] = true;
                dfs(nx, ny, x, y);
            }
        }
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m];
        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
