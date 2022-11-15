/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16437
 *
 * ? 제목: 양 구출 작전
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * N개의 섬으로 이루어진 나라가 있습니다. 섬들은 1번 섬부터 N번 섬까지 있습니다.
 * 1번 섬에는 구명보트만 있고 다른 섬에는 양들 또는 늑대들이 살고 있습니다.
 * 늘어나는 늑대의 개체 수를 감당할 수 없던 양들은 구명보트를 타고 늑대가 없는 나라로 이주하기로 했습니다.
 * 각 섬에서 1번 섬으로 가는 경로는 유일하며 i번 섬에는 pi번 섬으로 가는 다리가 있습니다.
 * 양들은 1번 섬으로 가는 경로로 이동하며 늑대들은 원래 있는 섬에서 움직이지 않고 섬으로 들어온 양들을 잡아먹습니다. 늑대는 날렵하기 때문에 섬에 들어온 양을 항상 잡을 수 있습니다. 그리고 늑대 한 마리는 최대 한 마리의 양만 잡아먹습니다.
 * 얼마나 많은 양이 1번 섬에 도달할 수 있을까요?
 *
 * ? 입력 & 파싱
 * 첫 번째 줄에 섬의 개수 N (2 ≤ N ≤ 123,456) 이 주어집니다.
 * 두 번째 줄부터 N-1개에 줄에 2번 섬부터 N번 섬까지 섬의 정보를 나타내는 ti, ai, pi (1 ≤ ai ≤ 109, 1 ≤ pi ≤ N) 가 주어집니다.
 * ti가 'W' 인 경우 i번 섬에 늑대가 ai마리가 살고 있음을, ti가 'S'인 경우 i번 섬에 양이 ai마리가 살고 있음을 의미합니다. pi는 i번째 섬에서 pi번 섬으로 갈 수 있는 다리가 있음을 의미합니다.
 *
 * 4        -> n
 * S 100 3  -> sw c next
 * W 50 1   -> sw c next
 * S 10 1   -> sw c next
 *
 * ? 출력
 * 첫 번째 줄에 구출할 수 있는 양의 수를 출력합니다.
 *
 * 60
 *
 * ? 채점 결과
 * * 시간: 0.436초
 * * 메모리: 61MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.III;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_16437 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] degree;
    static int[] adj;
    static long[][] arr;

    public static void main(String[] args) throws IOException {
        input();
        topologicalSort();
        System.out.println(arr[1][0]);
    }

    public static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == 1) {
                break;
            }

            int next = adj[cur];

            if (arr[cur][0] != 0) {
                if (arr[next][1] < arr[cur][0]) {
                    arr[next][0] += (arr[cur][0] - arr[next][1]);
                    arr[next][1] = 0;
                    arr[cur][0] = 0;
                } else {
                    arr[next][1] -= arr[cur][0];
                    arr[cur][0] = 0;
                }
            }


            if (--degree[next] == 0) {
                queue.add(next);
            }
        }
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        degree = new int[n + 1];
        adj = new int[n + 1];
        arr = new long[n + 1][2];

        for (int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String sw = st.nextToken();
            int c = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            if (sw.equals("S")) {
                arr[i][0] = c;
            } else {
                arr[i][1] = c;
            }
            adj[i] = next;
            degree[next]++;
        }

    }
}
