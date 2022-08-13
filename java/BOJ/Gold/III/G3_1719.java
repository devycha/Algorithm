/**
 * ? 문제 출처: 백준 온라인 져지
 * ? https://www.acmicpc.net/problem/1719
 *
 * ? 제목: 택배
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 명우기업은 2008년부터 택배 사업을 새로이 시작하기로 하였다. 우선 택배 화물을 모아서 처리하는 집하장을 몇 개 마련했지만, 택배 화물이 각 집하장들 사이를 오갈 때 어떤 경로를 거쳐야 하는지 결정하지 못했다. 어떤 경로를 거칠지 정해서, 이를 경로표로 정리하는 것이 여러분이 할 일이다.
 * 예시된 그래프에서 굵게 표시된 1, 2, 3, 4, 5, 6은 집하장을 나타낸다. 정점간의 간선은 두 집하장간에 화물 이동이 가능함을 나타내며, 가중치는 이동에 걸리는 시간이다. 이로부터 얻어내야 하는 경로표는 다음과 같다.
 * 경로표는 한 집하장에서 다른 집하장으로 최단경로로 화물을 이동시키기 위해 가장 먼저 거쳐야 하는 집하장을 나타낸 것이다. 예를 들어 4행 5열의 6은 4번 집하장에서 5번 집하장으로 최단 경로를 통해 가기 위해서는 제일 먼저 6번 집하장으로 이동해야 한다는 의미이다.
 * 이와 같은 경로표를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 두 수 n과 m이 빈 칸을 사이에 두고 순서대로 주어진다. n은 집하장의 개수로 200이하의 자연수, m은 집하장간 경로의 개수로 10000이하의 자연수이다. 이어서 한 줄에 하나씩 집하장간 경로가 주어지는데, 두 집하장의 번호와 그 사이를 오가는데 필요한 시간이 순서대로 주어진다. 집하장의 번호들과 경로의 소요시간은 모두 1000이하의 자연수이다.
 *
 * 6 10     -> n m
 * 1 2 2    -> a b c
 * 1 3 1    -> a b c
 * 2 4 5    -> a b c
 * 2 5 3    -> a b c
 * 2 6 7    -> a b c
 * 3 4 4    -> a b c
 * 3 5 6    -> a b c
 * 3 6 7    -> a b c
 * 4 6 4    -> a b c
 * 5 6 2    -> a b c
 *
 * ? 출력
 * 예시된 것과 같은 형식의 경로표를 출력한다.
 *
 * - 2 3 3 2 2
 * 1 - 1 4 5 5
 * 1 1 - 4 5 6
 * 3 2 3 - 6 6
 * 2 2 3 6 - 6
 * 5 5 3 4 5 -
 *
 * ? 채점 결과
 * * 시간: 672ms
 * * 메모리: 37MB
 * * 언어: JAVA8
 */
package Gold.III;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G3_1719 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m; // 집하장 개수 , 경로 개수
    static int[][] arr; // i -> j로 바로 가는 경로 중 최단거리

    // * 초기 설정 : i부터 j까지 최단경로로 가기 위해 가장 먼저 지나야 하는 집하장 번호 arr[i][j] = k (최단거리: i -> k -> ... -> j)
    static int[][] result;

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. 모든 집하장에서 다른 모든 집하장까지의 최단경로 구하는 다익스트라 알고리즘
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }

        // * 3. 정답 출력
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (result[i][j] == 0) {
                    sb.append("- ");
                } else {
                    sb.append(result[i][j] + " ");
                }
            }
            sb.append("\n");
        }
        
        bw.write(sb.toString());

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 다익스트라 알고리즘
    public static void dijkstra(int start) {
        int[] visited = new int[n+1]; // 방문리스트 겸 최단경로
        Arrays.fill(visited, 200000); // 방문리스트 값을 최댓값으로 초기화

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // 소요시간 가중치에 따른 우선순위큐(최소힙)
        pq.add(new int[] {start, start, 0}); // 시작 위치를 최소힙에 삽입
        visited[start] = 0; // 방문리스트에 시작위치 값을 0으로 초기화

        // * 다익스트라 알고리즘 수행
        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); // * 현재 위치 [현재 집하장 번호, 가장 먼저 지나온 집하장, 도착 시간]
            
            // * 현재 위치에 방문한 시간이 이미 다른 위치로부터 시작돼서 방문한 시간보다 크다면 패스
            if (cur[2] > visited[cur[0]]) {
                continue;
            }

            // * 현재 집하장에서 갈 수 있는 다른 모든 집하장에 대하여
            for (int i = 1; i <= n; i++) {
                if (arr[cur[0]][i] != 0) { // 경로가 있고
                    if (visited[i] >= cur[2] + arr[cur[0]][i]) { // 기존에 도착한 시간보다 지금 도착하는 시간이 더 짧거나 같을 때
                        visited[i] = cur[2] + arr[cur[0]][i]; // 방문리스트 최단경로 시간 업데이트

                        // * 최소힙에 삽입(이때, 가장 먼저 지나온 집하장이 start위치와 같다면 i(다음집하장 번호)로 변경하여 삽입
                        pq.add(new int[] {i, cur[1] == start ? i : cur[1], visited[i]});

                        // * 가장 먼저 지나온 집하장이 start위치와 같다면 결과값에 result[start][다음위치] = 다음위치
                        if (cur[1] == start) {
                            result[start][i] = i;
                        } else {
                            result[start][i] = cur[1];
                        }
                    }
                }
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1][n+1];
        result = new int[n+1][n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (arr[a][b] == 0) {
                arr[a][b] = c;
            } else {
                arr[a][b] = Math.min(arr[a][b], c);
            }

            if (arr[b][a] == 0) {
                arr[b][a] = c;
            } else {
                arr[b][a] = Math.min(arr[b][a], c);
            }
        }
    }
}
