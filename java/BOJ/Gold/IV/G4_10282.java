/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/10282
 *
 * ! 시간 제한: 2초
 * ! 메모리 제한: 256MB
 *
 * ! 문제
 * 최흉최악의 해커 yum3이 네트워크 시설의 한 컴퓨터를 해킹했다! 이제 서로에 의존하는 컴퓨터들은 점차 하나둘 전염되기 시작한다. 어떤 컴퓨터 a가 다른 컴퓨터 b에 의존한다면, b가 감염되면 그로부터 일정 시간 뒤 a도 감염되고 만다. 이때 b가 a를 의존하지 않는다면, a가 감염되더라도 b는 안전하다.
 * 최흉최악의 해커 yum3이 해킹한 컴퓨터 번호와 각 의존성이 주어질 때, 해킹당한 컴퓨터까지 포함하여 총 몇 대의 컴퓨터가 감염되며 그에 걸리는 시간이 얼마인지 구하는 프로그램을 작성하시오.
 *
 * ! 입력
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 개수는 최대 100개이다. 각 테스트 케이스는 다음과 같이 이루어져 있다.
 * 첫째 줄에 컴퓨터 개수 n, 의존성 개수 d, 해킹당한 컴퓨터의 번호 c가 주어진다(1 ≤ n ≤ 10,000, 1 ≤ d ≤ 100,000, 1 ≤ c ≤ n).
 * 이어서 d개의 줄에 각 의존성을 나타내는 정수 a, b, s가 주어진다(1 ≤ a, b ≤ n, a ≠ b, 0 ≤ s ≤ 1,000). 이는 컴퓨터 a가 컴퓨터 b를 의존하며, 컴퓨터 b가 감염되면 s초 후 컴퓨터 a도 감염됨을 뜻한다.
 * 각 테스트 케이스에서 같은 의존성 (a, b)가 두 번 이상 존재하지 않는다.
 *
 * 2        -> t
 * 3 2 2    -> n d c (case1)
 * 2 1 5    -> a b s
 * 3 2 5    -> a b s
 * 3 3 1    -> n d c (case2)
 * 2 1 2    -> a b s
 * 3 1 8    -> a b s
 * 3 2 4    -> a b s
 *
 * ! 출력
 * 각 테스트 케이스마다 한 줄에 걸쳐 총 감염되는 컴퓨터 수, 마지막 컴퓨터가 감염되기까지 걸리는 시간을 공백으로 구분지어 출력한다.
 *
 * 2 5
 * 3 6
 *
 * ? 채점 결과
 * 메모리: 165756KB
 * 시간: 1024ms
 * 언어: JAVA
 */
package Gold.IV;

import java.io.*;
import java.util.*;

public class G4_10282 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n, d, c, count, maxTime;
    static int[] isHacked;
    static HashMap<Integer, ArrayList<int[]>> map;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            input();
            dijkstra();
            bw.write(String.format("%d %d\n", count, maxTime));
            bw.flush();
        }
    }

    // 입력 받고 멤버 변수 초기화
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        isHacked = new int[n+1];
        Arrays.fill(isHacked, 1000000001);

        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<int[]>());
        }

        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            map.get(b).add(new int[] {a, s});
        }
    }

    // 다익스트라 실행
    public static void dijkstra() {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[] {c, 0});
        count = 0;
        maxTime = 0;
        isHacked[c] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int com = cur[0];
            int time = cur[1];

            if (isHacked[com] < time) continue;
            if (maxTime < time) {
                maxTime = time;
            }
            count++;

            for (int[] next: map.get(com)) {
                if (isHacked[next[0]] > next[1] + time) {
                    isHacked[next[0]] = next[1] + time;
                    queue.add(new int[] {next[0], time + next[1]});
                }
            }
        }
    }
}
