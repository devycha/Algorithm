/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/15971
 *
 * ? 제목: 두 로봇
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 2018년 강원도에서 새로운 동굴이 발견되었다. 이 동굴에는 총 N개의 넓은 방이 존재하며 좁은 통로로 서로 연결되어 있는 것으로 밝혀졌다. N개의 방은 1번부터 N번까지의 번호를 붙여 1번 방, 2번 방, …, N번 방으로 부른다. 통로는 정확히 N-1개가 발견되었는데, 각각 서로 다른 두 방 사이를 연결시켜 주며 중간에 다른 통로와 이어지는 경우는 없다고 한다. 또한 이 통로들을 이용하여 임의의 두 방 사이를 이동하는 것이 가능하며, 임의의 두 방 사이를 이동할 때 같은 통로를 두 번 이상 지나지 않는 경로는 유일한 것으로 밝혀졌다.
 * 새로 발견된 동굴을 조사하기 위해 동굴 탐사 로봇 두 대를 이용하기로 하였다. 두 로봇은 어떤 시점이 되면 각자가 획득한 정보를 공유하기 위해 통신을 해야 한다. 두 로봇이 서로 통신을 하기 위해서는 동굴 내의 같은 통로 위에 위치해야만 한다. 참고로 임의의 통로의 양 끝에 위치한 두 방들도 그 통로 위에 위치해 있다고 간주한다.
 * <그림 1> 동굴 내부를 간략히 표현한 그림
 * <그림 1>은 방이 9개인 동굴 내부를 간략하게 나타낸 예이다. <그림 1>에서 방은 원으로 표현되어 있으며 원 안의 수는 방 번호이다. 8개의 통로는 두 원 사이의 선분으로 표시되어 있으며 그 위의 정수 값이 통로의 길이이다. 예를 들어, 5번 방과 9번 방 사이에 길이가 6 인 통로가 있음을 알 수 있다. 만약 두 로봇이 1번 방과 9번 방에 위치해 있다면, 각각 2번 방과 5번 방으로 이동한 후 통신할 수 있으며 이때 이동한 거리의 합은 14로 최소이다.
 * 동굴 내의 통로에 대한 정보와 두 로봇의 현재 위치가 입력으로 주어질 때, 서로 통신하기 위해 이동해야 하는 거리의 합의 최솟값을 계산하는 프로그램을 작성하시오.
 * 동굴의 각 통로는 양 끝에 위치한 두 방의 번호와 그 길이로 주어진다. 두 로봇의 위치는 방 번호로 주어진다.
 *
 * ? 입력 & 파싱
 * 표준 입력으로 동굴의 방의 개수 N과 두 로봇이 위치한 방의 번호가 세 개의 양의 정수로 공백으로 분리되어 첫 줄에 주어진다. 이후 동굴의 통로 N-1개가 한 줄에 하나씩 주어진다. 각 통로는 세 개의 양의 정수로 공백으로 분리되어 한 줄에 주어지며, 앞 두 정수는 통로의 양 끝에 위치한 방의 번호를, 세 번째 정수는 그 통로의 길이를 의미한다.
 *
 * 5 1 5    -> n s e
 * 1 2 1    -> a b c
 * 2 3 2    -> a b c
 * 3 4 3    -> a b c
 * 4 5 4    -> a b c
 *
 * ? 출력
 * 표준 출력으로 두 로봇이 서로 통신하기 위해 현재 위치에서 이동해야 하는 거리의 합의 최솟값을 정수로 출력한다.
 *
 * 6
 *
 * ? 제한
 * 모든 서브태스크에서 1 ≤ N ≤ 100,000이며, 통로의 길이는 1,000을 넘지 않는다.
 *
 * ? 채점 결과
 * * 시간: 0.588초
 * * 메모리: 83MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_15971 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, s, e;
    static ArrayList<int[]>[] adj;

    // * 초기 설정
    static int totalLength = 0;
    static int maxLength = 0;
    static boolean[] visit; // 방문 리스트
    static int[] before; // 방문하기 전에 지나온 노드의 번호

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        bfs(); // * 2. BFS 수행 + 경로 저장
        findWay(); // * 3. 경로를 역으로 돌아가면서 total 길이와 max 길이를 구함
        bw.write(String.valueOf(totalLength - maxLength)); // * 4. 출력

        br.close();
        bw.flush();
        bw.close();
    }

    // BFS
    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {s, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == e) {
                break;
            }

            for (int[] next: adj[cur[0]]) {
                if (!visit[next[0]]) {
                    visit[next[0]] = true;
                    before[next[0]] = cur[0];
                    queue.add(new int[] {next[0], cur[1] + next[1]});
                }
            }
        }
    }

    // 경로 찾기
    public static void findWay() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(e);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == s) {
                break;
            }

            for (int[] next : adj[cur]) {
                if (next[0] == before[cur]) {
                    queue.add(next[0]);
                    maxLength = Math.max(maxLength, next[1]);
                    totalLength += next[1];
                }
            }
        }
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new int[] {b, c});
            adj[b].add(new int[] {a, c});
        }

        visit = new boolean[n+1];
        before = new int[n+1];
    }
}
