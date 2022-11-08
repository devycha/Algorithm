/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14284
 *
 * ? 제목: 간선 이어가기 2
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 정점 n개, 0개의 간선으로 이루어진 무방향 그래프가 주어진다. 그리고 m개의 가중치 간선의 정보가 있는 간선리스트가 주어진다. 간선리스트에 있는 간선 하나씩 그래프에 추가해 나갈 것이다. 이때, 특정 정점 s와 t가 연결이 되는 시점에서 간선 추가를 멈출 것이다. 연결이란 두 정점이 간선을 통해 방문 가능한 것을 말한다.
 * s와 t가 연결이 되는 시점의 간선의 가중치의 합이 최소가 되게 추가하는 간선의 순서를 조정할 때, 그 최솟값을 구하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 정점의 개수 n, 간선리스트의 간선 수 m이 주어진다.(2≤n≤5000,1≤m≤100,000)
 * 다음 m줄에는 a,b,c가 주어지는데, 이는 a와 b는 c의 가중치를 가짐을 말한다. (1≤a,b≤n,1≤c≤100,a≠b)
 * 다음 줄에는 두 정점 s,t가 주어진다. (1≤s,t≤n,s≠t)
 * 모든 간선을 연결하면 그래프는 연결 그래프가 됨이 보장된다.
 *
 * 8 9      ->  n m
 * 1 2 3    -> a b c
 * 1 3 2    -> a b c
 * 1 4 4    -> a b c
 * 2 5 2    -> a b c
 * 3 6 1    -> a b c
 * 4 7 3    -> a b c
 * 5 8 6    -> a b c
 * 6 8 2    -> a b c
 * 7 8 7    -> a b c
 * 1 8      -> s e
 *
 * ? 출력
 * s와 t가 연결되는 시점의 간선의 가중치 합의 최솟값을 출력하시오,
 *
 * 5
 *
 * ? 채점 결과
 * * 시간: 0.5초
 * * 메모리: 49MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_14284 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, s, e;
    static ArrayList<int[]>[] list;
    static int[] visit;

    public static void main(String[] args) throws IOException {
        input();
        int answer = bfs();
        System.out.println(answer - 1);
    }

    public static int bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{s, 1});
        visit[s] = 1;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visit[cur[0]] != 0 && visit[cur[0]] < cur[1]) {
                continue;
            }

            if (cur[0] == e) {
                return cur[1];
            }

            for (int[] next : list[cur[0]]) {
                if (visit[next[0]] == 0 || visit[next[0]] > cur[1] + next[1]) {
                    visit[next[0]] = cur[1] + next[1];
                    pq.add(new int[]{next[0], visit[next[0]]});
                }
            }
        }
        return -1;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new int[n + 1];
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new int[]{b, c});
            list[b].add(new int[]{a, c});
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
    }
}
