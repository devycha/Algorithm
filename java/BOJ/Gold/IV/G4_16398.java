/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16398
 *
 * ? 제목: 행성 연결
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 홍익 제국의 중심은 행성 T이다. 제국의 황제 윤석이는 행성 T에서 제국을 효과적으로 통치하기 위해서, N개의 행성 간에 플로우를 설치하려고 한다.
 * 두 행성 간에 플로우를 설치하면 제국의 함선과 무역선들은 한 행성에서 다른 행성으로 무시할 수 있을 만큼 짧은 시간만에 이동할 수 있다. 하지만, 치안을 유지하기 위해서 플로우 내에 제국군을 주둔시켜야 한다.
 * 모든 행성 간에 플로우를 설치하고 플로우 내에 제국군을 주둔하면, 제국의 제정이 악화되기 때문에 황제 윤석이는 제국의 모든 행성을 연결하면서 플로우 관리 비용을 최소한으로 하려 한다.
 * N개의 행성은 정수 1,…,N으로 표시하고, 행성 i와 행성 j사이의 플로우 관리비용은 Cij이며, i = j인 경우 항상 0이다.
 * 제국의 참모인 당신은 제국의 황제 윤석이를 도와 제국 내 모든 행성을 연결하고, 그 유지비용을 최소화하자. 이때 플로우의 설치비용은 무시하기로 한다.
 *
 * ? 입력 & 파싱
 * 입력으로 첫 줄에 행성의 수 N (1 ≤ N ≤ 1000)이 주어진다.
 * 두 번째 줄부터 N+1줄까지 각 행성간의 플로우 관리 비용이 N x N 행렬 (Cij), (1 ≤ i, j ≤ N, 1 ≤ Cij ≤ 100,000,000, Cij = Cji, Cii = 0) 로 주어진다.
 *
 * 3        -> n
 * 0 2 3    -> arr[0][0] ~ arr[0][n-1]
 * 2 0 1
 * 3 1 0    -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 모든 행성을 연결했을 때, 최소 플로우의 관리비용을 출력한다.
 *
 * 3
 *
 * ? 채점 결과
 * * 시간: 1.556초
 * * 메모리: 185MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.IV;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G4_16398 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[][] arr;

    // * 초기 설정
    static long cost;
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        prim(); // * 2. 최소 스패닝 트리(프림 알고리즘)
        bw.write(String.valueOf(cost)); // * 3. 출력

        br.close();
        bw.flush();
        bw.close();
    }

    // 최소 스패닝 트리(프림 알고리즘)
    public static void prim() {
        int count = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 1; i < n; i++) {
            pq.add(new int[] {0, i, arr[0][i]});
        }

        while (!pq.isEmpty() || count < n) {
            int[] cur = pq.poll();

            if (findParent(cur[0]) != findParent(cur[1])) {
                count++;
                cost += cur[2];
                union(cur[0], cur[1]);

                for (int i = 0; i < n; i++) {
                    if (i == cur[1]) {
                        continue;
                    }

                    pq.add(new int[] {cur[1], i, arr[cur[1]][i]});
                }
            }
        }

    }

    // UNION_FIND
    public static int findParent(int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node]);
        }
        return parent[node];
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = IntStream.range(0, n).toArray();
        arr = new int[n][n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j= 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
