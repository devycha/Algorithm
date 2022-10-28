/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/17836
 *
 * ? 제목: 공주님을 구해라!
 * ? 시간 제한: 1초
 * ? 메모리 제한; 256MB
 *
 * ? 문제
 * 용사는 마왕이 숨겨놓은 공주님을 구하기 위해 (N, M) 크기의 성 입구 (1,1)으로 들어왔다. 마왕은 용사가 공주를 찾지 못하도록 성의 여러 군데 마법 벽을 세워놓았다. 용사는 현재의 가지고 있는 무기로는 마법 벽을 통과할 수 없으며, 마법 벽을 피해 (N, M) 위치에 있는 공주님을 구출해야만 한다.
 * 마왕은 용사를 괴롭히기 위해 공주에게 저주를 걸었다. 저주에 걸린 공주는 T시간 이내로 용사를 만나지 못한다면 영원히 돌로 변하게 된다. 공주님을 구출하고 프러포즈 하고 싶은 용사는 반드시 T시간 내에 공주님이 있는 곳에 도달해야 한다. 용사는 한 칸을 이동하는 데 한 시간이 걸린다. 공주님이 있는 곳에 정확히 T시간만에 도달한 경우에도 구출할 수 있다. 용사는 상하좌우로 이동할 수 있다.
 * 성에는 이전 용사가 사용하던 전설의 명검 "그람"이 숨겨져 있다. 용사가 그람을 구하면 마법의 벽이 있는 칸일지라도, 단숨에 벽을 부수고 그 공간으로 갈 수 있다. "그람"은 성의 어딘가에 반드시 한 개 존재하고, 용사는 그람이 있는 곳에 도착하면 바로 사용할 수 있다. 그람이 부술 수 있는 벽의 개수는 제한이 없다.
 * 우리 모두 용사가 공주님을 안전하게 구출 할 수 있는지, 있다면 얼마나 빨리 구할 수 있는지 알아보자.
 *
 * ? 입력 & 파싱
 * 첫 번째 줄에는 성의 크기인 N, M 그리고 공주에게 걸린 저주의 제한 시간인 정수 T가 주어진다. 첫 줄의 세 개의 수는 띄어쓰기로 구분된다. (3 ≤ N, M ≤ 100, 1 ≤ T ≤ 10000)
 * 두 번째 줄부터 N+1번째 줄까지 성의 구조를 나타내는 M개의 수가 띄어쓰기로 구분되어 주어진다. 0은 빈 공간, 1은 마법의 벽, 2는 그람이 놓여있는 공간을 의미한다. (1,1)과 (N,M)은 0이다.
 *
 * 6 6 16           -> n m t
 * 0 0 0 0 1 1      -> arr[0][0] ~ arr[0][m-1]
 * 0 0 0 0 0 2
 * 1 1 1 0 1 0
 * 0 0 0 0 0 0
 * 0 1 1 1 1 1
 * 0 0 0 0 0 0      -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * 용사가 제한 시간 T시간 이내에 공주에게 도달할 수 있다면, 공주에게 도달할 수 있는 최단 시간을 출력한다.
 * 만약 용사가 공주를 T시간 이내에 구출할 수 없다면, "Fail"을 출력한다.
 *
 * 10
 *
 * ? 채점 결과
 * * 시간: 0.144초
 * * 메모리: 15MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_17836 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, t;
    static int[][] arr;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();
        int answer = bfs();

        if (answer <= t && answer != -1) {
            System.out.println(answer);
        } else {
            System.out.println("Fail");
        }
    }

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visit = new boolean[n][m][2];
        queue.add(new int[] {0, 0, 0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == n-1 && cur[1] == m-1) {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (arr[nx][ny] == 2) {
                    arr[nx][ny] = 0;
                    visit[nx][ny][0] = true;
                    queue.add(new int[] {nx, ny, cur[2]+1, 1});
                } else if (arr[nx][ny] == 0 && !visit[nx][ny][cur[3]]) {
                    visit[nx][ny][cur[3]] = true;
                    queue.add(new int[] {nx, ny, cur[2]+1, cur[3]});
                } else if (arr[nx][ny] == 1 && cur[3] == 1 && !visit[nx][ny][0]) {
                    visit[nx][ny][0] = true;
                    queue.add(new int[] {nx, ny, cur[2]+1, 1});
                }
            }
        }

        return -1;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
