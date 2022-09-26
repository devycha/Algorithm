/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/4803
 *
 * ? 제목: 트리
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 그래프는 정점과 간선으로 이루어져 있다. 두 정점 사이에 경로가 있다면, 두 정점은 연결되어 있다고 한다. 연결 요소는 모든 정점이 서로 연결되어 있는 정점의 부분집합이다. 그래프는 하나 또는 그 이상의 연결 요소로 이루어져 있다.
 * 트리는 사이클이 없는 연결 요소이다. 트리에는 여러 성질이 있다. 예를 들어, 트리는 정점이 n개, 간선이 n-1개 있다. 또, 임의의 두 정점에 대해서 경로가 유일하다.
 * 그래프가 주어졌을 때, 트리의 개수를 세는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 n ≤ 500과 m ≤ n(n-1)/2을 만족하는 정점의 개수 n과 간선의 개수 m이 주어진다. 다음 m개의 줄에는 간선을 나타내는 두 개의 정수가 주어진다. 같은 간선은 여러 번 주어지지 않는다. 정점은 1번부터 n번까지 번호가 매겨져 있다. 입력의 마지막 줄에는 0이 두 개 주어진다.
 *
 * 6 3  -> n m
 * 1 2  -> a b
 * 2 3  -> a b
 * 3 4  -> a b
 * 6 5  -> n m
 * 1 2  -> a b
 * 2 3  -> a b
 * 3 4  -> a b
 * 4 5  -> a b
 * 5 6  -> a b
 * 6 6  -> n m
 * 1 2  -> a b
 * 2 3  -> a b
 * 1 3  -> a b
 * 4 5  -> a b
 * 5 6  -> a b
 * 6 4  -> a b
 * 0 0  -> a b
 *
 * ? 출력
 * 입력으로 주어진 그래프에 트리가 없다면 "No trees."를, 한 개라면 "There is one tree."를, T개(T > 1)라면 "A forest of T trees."를 테스트 케이스 번호와 함께 출력한다.
 *
 * Case 1: A forest of 3 trees.
 * Case 2: There is one tree.
 * Case 3: No trees.
 *
 * ? 채점 결과
 * * 시간: 0.636초
 * * 메모리: 72MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.IV;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G4_4803 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static ArrayList<Integer>[] arr;

    // * 초기 설정
    static int[] parent;

    public static void main(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer();
        int t = 0;
        while (true) { // * 각 테스트 케이스마다
            t++;
            input(); // * 1. 입력 받기

            if (n == 0 && m == 0) { // * 종료 케이스
                break;
            }

            parent = IntStream.range(0, n+1).toArray(); // 방문리스트 겸 부모노드

            // *** 인접 노드 리스트 배열 생성 & 초기화 & 셋팅
            arr = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                arr[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a].add(b);
                arr[b].add(a);
            }

            // * 2. BFS: 트리의 갯수 세기
            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (parent[i] == i) {
                    boolean hasCycle = false; // * 사이클이 발생하는지 여부
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[] {i, 0});

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for (int next : arr[cur[0]]) {
                            // * 바로 직전에서 온 노드 제외
                            if (next == cur[1]) {
                                continue;
                            }

                            if (parent[next] == i) {
                                hasCycle = true;
                            } else {
                                parent[next] = i;
                                queue.add(new int[] {next, cur[0]});
                            }
                        }
                    }

                    // 사이클이 발생하지 않았을 경우에만 count 증가
                    if (!hasCycle) {
                        count++;
                    }
                }
            }

            // * 3. 출력값 추가
            switch (count) {
                case 0:
                    sb.append("Case " + t + ": " + "No trees.\n");
                    break;
                case 1:
                    sb.append("Case " + t + ": " + "There is one tree.\n");
                    break;
                default:
                    sb.append("Case " + t + ": " + "A forest of " + count + " trees.\n");
                    break;
            }
        }

        // * 4. 출력
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }
}
