/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16940
 *
 * ? 제목: BFS 스페셜저지
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * BOJ에서 정답이 여러가지인 경우에는 스페셜 저지를 사용한다. 스페셜 저지는 유저가 출력한 답을 검증하는 코드를 통해서 정답 유무를 결정하는 방식이다. 오늘은 스페셜 저지 코드를 하나 만들어보려고 한다.
 * 정점의 개수가 N이고, 정점에 1부터 N까지 번호가 매겨져있는 양방향 그래프가 있을 때, BFS 알고리즘은 다음과 같은 형태로 이루어져 있다.
 * 큐에 시작 정점을 넣는다. 이 문제에서 시작 정점은 1이다. 1을 방문했다고 처리한다.
 * 큐가 비어 있지 않은 동안 다음을 반복한다.
 * 큐에 들어있는 첫 정점을 큐에서 꺼낸다. 이 정점을 x라고 하자.
 * x와 연결되어 있으면, 아직 방문하지 않은 정점 y를 모두 큐에 넣는다. 모든 y를 방문했다고 처리한다.
 * 2-2 단계에서 방문하지 않은 정점을 방문하는 순서는 중요하지 않다. 따라서, BFS의 결과는 여러가지가 나올 수 있다.
 * 트리가 주어졌을 때, 올바른 BFS 방문 순서인지 구해보자.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 정점의 수 N(2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에는 트리의 간선 정보가 주어진다. 마지막 줄에는 BFS 방문 순서가 주어진다. BFS 방문 순서는 항상 N개의 정수로 이루어져 있으며, 1부터 N까지 자연수가 한 번씩 등장한다.
 *
 * 4        -> n
 * 1 2      -> a b
 * 1 3      -> a b
 * 2 4      -> a b
 * 1 2 3 4  -> sequence
 *
 * ? 출력
 * 입력으로 주어진 BFS 방문 순서가 올바른 순서면 1, 아니면 0을 출력한다.
 *
 * 1
 *
 * ? 채점 결과
 * * 시간: 0.808초
 * * 메모리: 75MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Gold.III;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G3_16940 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, result;
    static ArrayList<Integer>[] arr;
    static int[] sequence, order;

    // * 초기 설정
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        bfs(); // * 2. BFS
        System.out.println(result); // * 3. 결과 출력
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        int idx = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (sequence[idx++] != cur) {
                result = 0;
                break;
            }

            for (int next : arr[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        result = 1;

        arr = new ArrayList[n+1];
        order = new int[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        sequence = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
            order[sequence[i]] = i;
        }

        // ************ 주어진 탐색 순서를 기준으로 인접노드리스트 배열 정렬 *****************
        for (int i = 1; i <= n; i++) {
            arr[i].sort((a, b) -> order[a] - order[b]);
        }
    }
}
