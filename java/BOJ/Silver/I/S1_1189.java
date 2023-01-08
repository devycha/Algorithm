/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * https://www.acmicpc.net/problem/1189
 *
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * ? 한수는 캠프를 마치고 집에 돌아가려 한다. 한수는 현재 왼쪽 아래점에 있고 집은 오른쪽 위에 있다. 그리고 한수는 집에 돌아가는 방법이 다양하다. 단, 한수는 똑똑하여 한번 지나친 곳을 다시 방문하지는 않는다.
 *
 *       cdef  ...f  ..ef  ..gh  cdeh  cdej  ...f
 *       bT..  .T.e  .Td.  .Tfe  bTfg  bTfi  .Tde
 *       a...  abcd  abc.  abcd  a...  a.gh  abc.
 * 거리 :  6     6     6     8     8    10    6
 *
 * 위 예제는 한수가 집에 돌아갈 수 있는 모든 경우를 나타낸 것이다. T로 표시된 부분은 가지 못하는 부분이다. 문제는 R x C 맵에 못가는 부분이 주어지고 거리 K가 주어지면 한수가 집까지도 도착하는 경우 중 거리가 K인 가짓수를 구하는 것이다.
 *
 * ? 입력
 * 첫 줄에 정수 R(1 ≤ R ≤ 5), C(1 ≤ C ≤ 5), K(1 ≤ K ≤ R×C)가 공백으로 구분되어 주어진다. 두 번째부터 R+1번째 줄까지는 R×C 맵의 정보를 나타내는 '.'과 'T'로 구성된 길이가 C인 문자열이 주어진다.
 *
 * 3 4 6    -> r c k
 * ....     -> arr[0][0] ~ arr[0][c-1]
 * .T..
 * ....     -> arr[r-1][0] ~ arr[r-1][c-1]
 *
 * ? 출력
 * 첫 줄에 거리가 K인 가짓수를 출력한다.
 *
 * 4
 *
 * ? 채점 결과
 * * 시간: 0.096초
 * * 메모리: 12MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_1189 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int r, c, k;
    static int count = 0;

    static char[][] arr;
    static boolean[][] visit;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();

        visit[r-1][0] = true;
        dfs(r-1, 0, 1);

        System.out.println(count);
    }

    public static void dfs(int x, int y, int len) {
        if (x == 0 && y == c-1) {
            if (len == k) {
                count++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= r || ny >= c || visit[nx][ny] || arr[nx][ny] == 'T') {
                continue;
            }

            visit[nx][ny] = true;
            dfs(nx, ny, len+1);
            visit[nx][ny] = false;
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new char[r][c];
        visit = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
