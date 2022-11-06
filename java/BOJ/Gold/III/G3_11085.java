/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11085
 *
 * ? 제목: 군사 이동
 * ? 시간 제한: 2초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 전쟁 당시 Baekjoon World의 국왕은 Cube World를 공격할 작전을 세운 적이 있습니다. Baekjoon World와 Cube World는 p개의 지점과 w개의 길로 표현됩니다. 모든 길은 양방향이며, 각 길마다 너비가 존재하여 이에 비례하는 수의 군사가 지나갈 수 있습니다.
 * Baekjoon World의 국왕은 군사들이 뭉치는 것이 유리하다고 생각해서, 미리 Cube World로 가는 경로를 정해 두고 그 경로로만 모든 군사를 보냈습니다. Baekjoon World의 국왕은 총명해서, 경로 상에 있는 길 중 너비가 가장 좁은 길의 너비를 최대화하는 경로를 택했습니다.
 * 그런데 전쟁 때문에 어느 길로 보냈는지에 대한 기록이 불타 없어져 버렸습니다. 전쟁사를 완성하려면 이 기록이 꼭 필요합니다. 위대한 과학자인 당신이 다시 복구해 주세요.
 *
 * ? 입력 & 파싱
 * 첫 줄에 p와 w가 공백을 사이에 두고 주어집니다. (2 ≤ p ≤ 1 000; 1 ≤ w ≤ 50 000)
 * 다음 줄에 Baekjoon World의 수도 c와 Cube World의 수도 v가 공백을 사이에 두고 주어집니다. (0 ≤ c, v < p; c ≠ v)
 * 다음 w줄에 길이 연결하는 두 지점 wstart, wend,와 길의 너비 wwidth가 공백을 사이에 두고 주어집니다. (0 ≤ wstart, wend < p; wstart ≠ wend; 1 ≤ wwidth ≤ 1 000)
 *
 * 7 11     -> n m
 * 3 5      -> s e
 * 0 1 15   -> a b c
 * 0 2 23   -> a b c
 * 1 2 16   -> a b c
 * 1 3 27   -> a b c
 * 2 4 3    -> a b c
 * 2 6 21   -> a b c
 * 3 4 14   -> a b c
 * 3 5 10   -> a b c
 * 4 5 50   -> a b c
 * 4 6 9    -> a b c
 * 5 6 42   -> a b c
 *
 * ? 출력
 * 첫 줄에 Baekjoon World의 국왕이 정한 경로 상에 있는 길 중 너비가 가장 좁은 길의 너비를 출력합니다.
 *
 * 16
 *
 * ? 채점 결과
 * * 시간: 0.468초
 * * 메모리: 36MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.III;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G3_11085 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int p, w, s, e;
    static int[] visit;
    static ArrayList<int[]>[] list;

    public static void main(String[] args) throws IOException {
        input();
        dijkstra();
        System.out.println(visit[e]);
    }

    public static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        pq.add(new int[]{s, 1000});
        visit[s] = 1000;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visit[cur[0]] > cur[1]) {
                continue;
            }

            for (int[] next : list[cur[0]]) {
                int minWidth = Math.min(cur[1], next[1]);

                if (visit[next[0]] < minWidth) {
                    visit[next[0]] = minWidth;
                    pq.add(new int[]{next[0], minWidth});
                }
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        visit = new int[p];
        Arrays.fill(visit, -1);
        list = new ArrayList[p];
        for (int i = 0; i < p; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new int[]{b, c});
            list[b].add(new int[]{a, c});
        }
    }
}
