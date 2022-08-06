/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/2211
 *
 * ! 제목: 네트워크 복구
 * ! 시간 제한: 2초
 * ! 메모리 제한: 192MB
 *
 * ! 문제
 * N(1 ≤ N ≤ 1,000)개의 컴퓨터로 구성된 네트워크가 있다. 이들 중 몇 개의 컴퓨터들은 서로 네트워크 연결이 되어 있어 서로 다른 두 컴퓨터 간 통신이 가능하도록 되어 있다. 통신을 할 때에는 서로 직접 연결되어 있는 회선을 이용할 수도 있으며, 회선과 다른 컴퓨터를 거쳐서 통신을 할 수도 있다.
 * 각 컴퓨터들과 회선은 그 성능이 차이가 날 수 있다. 따라서 각각의 직접 연결되어 있는 회선을 이용해서 통신을 하는데 걸리는 시간이 서로 다를 수 있다. 심지어는 직접 연결되어 있는 회선이 오히려 더 느려서, 다른 컴퓨터를 통해서 통신을 하는 것이 더 유리할 수도 있다. 직접 연결되어 있는 회선을 사용할 경우에는 그 회선을 이용해서 통신을 하는 데 드는 시간만큼이 들게 된다. 여러 개의 회선을 거치는 경우에는 각 회선을 이용해서 통신을 하는 데 드는 시간의 합만큼의 시간이 걸리게 된다.
 * 어느 날, 해커가 네트워크에 침입하였다. 네트워크의 관리자는 우선 모든 회선과 컴퓨터를 차단한 후, 해커의 공격을 막을 수 있었다. 관리자는 컴퓨터에 보안 시스템을 설치하려 하였는데, 버전 문제로 보안 시스템을 한 대의 슈퍼컴퓨터에만 설치할 수 있었다. 한 컴퓨터가 공격을 받게 되면, 네트워크를 통해 슈퍼컴퓨터에 이 사실이 전달이 되고, 그러면 슈퍼컴퓨터에서는 네트워크를 이용해서 보안 패킷을 전송하는 방식을 사용하기로 하였다. 준비를 마친 뒤, 관리자는 다시 네트워크를 복구하기로 하였다. 이때, 다음의 조건들이 만족되어야 한다.
 * 해커가 다시 공격을 할 우려가 있기 때문에, 최소 개수의 회선만을 복구해야 한다. 물론, 그렇다면 아무 회선도 복구하지 않으면 되겠지만, 이럴 경우 네트워크의 사용에 지장이 생기게 된다. 따라서 네트워크를 복구한 후에 서로 다른 두 컴퓨터 간에 통신이 가능하도록 복구해야 한다.
 * 네트워크를 복구해서 통신이 가능하도록 만드는 것도 중요하지만, 해커에게 공격을 받았을 때 보안 패킷을 전송하는 데 걸리는 시간도 중요한 문제가 된다. 따라서 슈퍼컴퓨터가 다른 컴퓨터들과 통신하는데 걸리는 최소 시간이, 원래의 네트워크에서 통신하는데 걸리는 최소 시간보다 커져서는 안 된다.
 * 원래의 네트워크에 대한 정보가 주어졌을 때, 위의 조건을 만족하면서 네트워크를 복구하는 방법을 알아내는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 두 정수 N, M이 주어진다. 다음 M개의 줄에는 회선의 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 컴퓨터와 B번 컴퓨터가 통신 시간이 C (1 ≤ C ≤ 10)인 회선으로 연결되어 있다는 의미이다. 컴퓨터들의 번호는 1부터 N까지의 정수이며, 1번 컴퓨터는 보안 시스템을 설치할 슈퍼컴퓨터이다. 모든 통신은 완전쌍방향 방식으로 이루어지기 때문에, 한 회선으로 연결된 두 컴퓨터는 어느 방향으로도 통신할 수 있다.
 *
 * 4 5      -> n m
 * 1 2 1    -> a b c
 * 1 4 4    -> a b c
 * 1 3 2    -> a b c
 * 4 2 2    -> a b c
 * 4 3 3    -> a b c
 *
 * ! 출력
 * 첫째 줄에 복구할 회선의 개수 K를 출력한다. 다음 K개의 줄에는 복구한 회선을 나타내는 두 정수 A, B를 출력한다. 이는 A번 컴퓨터와 B번 컴퓨터를 연결하던 회선을 복구한다는 의미이다. 출력은 임의의 순서대로 하며, 답이 여러 개 존재하는 경우에는 아무 것이나 하나만 출력하면 된다.
 *
 * 3
 * 1 2
 * 3 1
 * 4 2
 *
 * ? 채점 결과
 * 시간: 792ms
 * 메모리: 111MB
 * 언어: JAVA8
 */
package Gold.II;

import java.io.*;
import java.util.*;

public class G2_2211 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static HashMap<Integer, ArrayList<int[]>> map;

    // * 초기 설정
    static int[] restored;
    static int[] before;

    // ! 메인
    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기
        restored[1] = 0; // * 슈퍼컴퓨터 위치(1)
        dijkstra(); // * 다익스트라

        int count = 0; // * 복구한 네트워크 갯수
        StringBuffer sb = new StringBuffer(); // * 복구한 네트워크 문자열

        // * 2부터 N번 컴퓨터까지 복구한 통신 회선을 sb에 추가
        for (int i = 2; i <= n; i++) {
            if (before[i] > 0) {
                count++;
                sb.append(i + " " + before[i] + "\n");
            }
        }

        // * 출력
        bw.write(String.valueOf(count) + "\n");
        bw.write(sb.toString());

        // * 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 다익스트라 알고리즘
    public static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {1, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[1] > restored[cur[0]]) continue;

            if (map.containsKey(cur[0])) {
                for (int[] next: map.get(cur[0])) {

                    if (restored[next[0]] == -1 || restored[next[0]] > cur[1] + next[1]) {
                        pq.add(new int[] {next[0], cur[1] + next[1]});
                        restored[next[0]] = cur[1] + next[1];
                        before[next[0]] = cur[0];
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

        map = new HashMap<>(); // * 인접 리스트
        restored = new int[n+1]; // * 최단 시간 경로
        before = new int[n+1]; // * 복구된 회선을 통해 연결된 컴퓨터 번호

        Arrays.fill(restored, -1); // * 초기화

        // * 인접 컴퓨터 리스트 해쉬맵
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (!map.containsKey(a)) map.put(a, new ArrayList<>());
            if (!map.containsKey(b)) map.put(b, new ArrayList<>());

            map.get(a).add(new int[] {b, c});
            map.get(b).add(new int[] {a, c});
        }
    }
}