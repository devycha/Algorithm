/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1240
 *
 * ? 제목: 노드사이의 거리
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * N(2≤N≤1,000)개의 노드로 이루어진 트리가 주어지고 M(M≤1,000)개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 노드의 개수 N이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리(10,000 이하의 정수)를 입력받는다. 그 다음 줄에는 거리를 알고 싶은 M개의 노드 쌍이 한 줄에 한 쌍씩 입력된다.
 *
 * 4 2      -> n m
 * 2 1 2    -> a b c
 * 4 3 2
 * 1 4 3
 * 1 2      -> a b
 * 3 2
 *
 * ? 출력
 * M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.
 *
 * 2
 * 7
 *
 * ? 채점 결과
 * * 시간: 0.276초
 * * 메모리: 48MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_1240_BFS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static ArrayList<int[]>[] arr;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        StringBuffer sb = new StringBuffer();

        // * 2. BFS
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(bfs(a, b) + "\n");
        }

        // * 3. 출력
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    // * a -> b 로 가는 BFS
    public static int bfs(int a, int b) {
        boolean[] visit = new boolean[n+1];
        Queue<int[]> queue = new LinkedList<>();
        visit[a] = true;
        queue.add(new int[] {a, 0});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == b) {
                return cur[1];
            }

            for (int[] next : arr[cur[0]]) {
                if (!visit[next[0]]) {
                    visit[next[0]] = true;
                    queue.add(new int[] {next[0], cur[1] + next[1]});
                }
            }

        }

        return -1;
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a].add(new int[] {b, c});
            arr[b].add(new int[] {a, c});
        }
    }
}
