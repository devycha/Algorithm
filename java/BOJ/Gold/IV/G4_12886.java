/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/12886
 *
 * ? 제목: 돌 그룹
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 오늘 강호는 돌을 이용해 재미있는 게임을 하려고 한다. 먼저, 돌은 세 개의 그룹으로 나누어져 있으며 각각의 그룹에는 돌이 A, B, C개가 있다. 강호는 모든 그룹에 있는 돌의 개수를 같게 만들려고 한다.
 * 강호는 돌을 단계별로 움직이며, 각 단계는 다음과 같이 이루어져 있다.
 * 크기가 같지 않은 두 그룹을 고른다. 그 다음, 돌의 개수가 작은 쪽을 X, 큰 쪽을 Y라고 정한다. 그 다음, X에 있는 돌의 개수를 X+X개로, Y에 있는 돌의 개수를 Y-X개로 만든다.
 * A, B, C가 주어졌을 때, 강호가 돌을 같은 개수로 만들 수 있으면 1을, 아니면 0을 출력하는 프로그램을 작성하시오.
 *
 * ? 입력
 * 첫째 줄에 A, B, C가 주어진다. (1 ≤ A, B, C ≤ 500)
 *
 * 10 15 36 -> A B C
 *
 * ? 출력
 * 돌을 같은 개수로 만들 수 있으면 1을, 아니면 0을 출력한다.
 *
 * 1
 *
 * ? 채점 결과
 * * 시간: 0.288초
 * * 메모리: 94MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Gold.IV;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_12886 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int A, B, C;
    static int[][] dir = {{1, 2}, {0, 2}, {0, 1}};

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {A, B, C});
        boolean[][] visit = new boolean[1501][1501];
        visit[A][B] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == cur[1] && cur[1] == cur[2]) {
                return 1;
            }

            for (int i = 0; i < 3; i++) {
                if (cur[dir[i][0]] != cur[dir[i][1]]) {
                    int[] next = new int[3];
                    next[i] = cur[i];

                    next[dir[i][0]] = cur[dir[i][0]] > cur[dir[i][1]] ? cur[dir[i][0]] - cur[dir[i][1]] : 2 * cur[dir[i][0]];
                    next[dir[i][1]] = cur[dir[i][1]] > cur[dir[i][0]] ? cur[dir[i][1]] - cur[dir[i][0]] : 2 * cur[dir[i][1]];

                    if (!visit[next[dir[i][0]]][next[dir[i][1]]]) {
                        visit[next[dir[i][0]]][next[dir[i][1]]] = true;
                        queue.add(next);
                    }
                }
            }
        }

        return 0;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }
}
