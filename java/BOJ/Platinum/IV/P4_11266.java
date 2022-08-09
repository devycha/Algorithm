/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/11266
 *
 * ! 제목: 단절점
 * ! 시간 제한: 1초
 * ! 메모리 제한: 256MB
 *
 * ! 문제
 * 그래프가 주어졌을 때, 단절점을 모두 구해 출력하는 프로그램을 작성하시오.
 * 단절점이란 그 정점을 제거했을 때, 그래프가 두 개 또는 그 이상으로 나누어지는 정점을 말한다. 즉, 제거했을 때 그래프의 connected component의 개수가 증가하는 정점을 말한다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 두 정수 V(1≤V≤10,000), E(1≤E≤100,000)가 주어진다. 이는 그래프가 V개의 정점과 E개의 간선으로 이루어져 있다는 의미이다. 다음 E개의 줄에는 간선에 대한 정보를 나타내는 두 정수 A, B가 주어진다. 이는 A번 정점과 B번 정점이 연결되어 있다는 의미이며, 방향은 양방향이다.
 * 입력으로 주어지는 그래프는 연결 그래프가 아닐 수도 있다. 정점은 1부터 V까지 번호가 매겨져 있다.
 *
 * 7 7  -> v e
 * 1 4  -> a b
 * 4 5  -> a b
 * 5 1  -> a b
 * 1 6  -> a b
 * 6 7  -> a b
 * 2 7  -> a b
 * 7 3  -> a b
 *
 * ! 출력
 * 첫째 줄에 단절점의 개수를 출력한다.
 * 둘째 줄에는 단절점의 번호를 공백으로 구분해 오름차순으로 출력한다.
 *
 * 3
 * 1 6 7
 *
 * ! 채점 결과
 * ? 시간: 404ms
 * ? 메모리: 46MB
 * ? 언어: JAVA8
 */
package Platinum.IV;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P4_11266 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int v, e;
    static ArrayList<Integer>[] adj;

    // * 초기 설정
    static int sequence = 0;
    static int[] visited;
    static boolean[] cut;

    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기

        // * 1부터 V까지 방문한 적이 없는 노드를 루트노드로 취급하고 DFS 수행
        for (int i = 1; i <= v; i++) {
            if (visited[i] == 0) {
                dfs(i, true);
            }
        }

        // * 단절점의 개수와 번호 출력
        int count = 0;
        StringBuffer sb = new StringBuffer();

        for (int i = 1; i <= v; i++) {
            if (cut[i]) {
                count++;
                sb.append(i + " ");
            }
        }

        bw.write(String.valueOf(count) + "\n");
        if (count > 0) {
            bw.write(sb.toString());
        }

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! DFS
    public static int dfs(int cur, boolean start) {
        visited[cur] = ++sequence; // DFS 순서 저장
        int ret = visited[cur]; // 도달할 수 있는 노드 중 DFS 방문 순서가 가장 작은 횟수
        int child = 0; // 자식 개수 (연결된 노드중에 이미 visited값이 0이 아니면 자식이 아니라 부모임)

        // 현재 노드와 연결된 인접 노드들에 대하여
        for (int i = 0; i < adj[cur].size(); i++) {
            int next = adj[cur].get(i); // 인접 노드

            // 이미 visited값이 0이 아니라면 부모 노드인 것
            if (visited[next] > 0) {
                ret = Math.min(ret, visited[next]); // DFS 방문 순서 번호 업데이트
                continue;
            }

            child++; // 자식 노드 개수 추가
            int prev = dfs(next, false); // 자식 노드가 방문할 수 있는 노드 중 DFS 방문 순서가 작은 값(재귀)

            // 루트노드가 아니면서 자식 노드가 방문할 수 있는 노드 중 DFS 방문 순서가
            // 현재 노드의 DFS 방문 순서보다 크거나 같다면 단절점
            if (!start && prev >= visited[cur]) {
                cut[cur] = true;
            }

            // DFS 방문 순서 번호 업데이트
            ret = Math.min(ret, prev);
        }

        // 루트라면 자식노드가 2개 이상일 때 단절점
        if (start) {
            cut[cur] = child >= 2;
        }

        // 도달할 수 있는 노드 중 DFS 방문 순서가 가장 작은 값 리턴
        return ret;
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        adj = new ArrayList[v+1];
        for (int i = 1; i <= v; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        visited = new int[v+1];
        cut = new boolean[v+1];
    }
}
