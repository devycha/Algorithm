/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 *
 * ! 제목: 전력난
 * ! 시간 제한: 1초
 * ! 메모리 제한: 256MB
 *
 * ! 문제
 * 성진이는 한 도시의 시장인데 거지라서 전력난에 끙끙댄다. 그래서 모든 길마다 원래 켜져 있던 가로등 중 일부를 소등하기로 하였다. 길의 가로등을 켜 두면 하루에 길의 미터 수만큼 돈이 들어가는데, 일부를 소등하여 그만큼의 돈을 절약할 수 있다.
 * 그러나 만약 어떤 두 집을 왕래할 때, 불이 켜져 있지 않은 길을 반드시 지나야 한다면 위험하다. 그래서 도시에 있는 모든 두 집 쌍에 대해, 불이 켜진 길만으로 서로를 왕래할 수 있어야 한다.
 * 위 조건을 지키면서 절약할 수 있는 최대 액수를 구하시오.
 *
 * ! 입력 & 파싱
 * 입력은 여러 개의 테스트 케이스로 구분되어 있다.
 * 각 테스트 케이스의 첫째 줄에는 집의 수 m과 길의 수 n이 주어진다. (1 ≤ m ≤ 200000, m-1 ≤ n ≤ 200000)
 * 이어서 n개의 줄에 각 길에 대한 정보 x, y, z가 주어지는데, 이는 x번 집과 y번 집 사이에 양방향 도로가 있으며 그 거리가 z미터라는 뜻이다. (0 ≤ x, y < m, x ≠ y)
 * 도시는 항상 연결 그래프의 형태이고(즉, 어떤 두 집을 골라도 서로 왕래할 수 있는 경로가 있다), 도시상의 모든 길의 거리 합은 231미터보다 작다.
 * 입력의 끝에서는 첫 줄에 0이 2개 주어진다.
 *
 * 7 11     -> n m (문제는 m n인데 편의상 n m으로 바꿔서 풀이)
 * 0 1 7    -> roads.get(0);
 * 0 3 5
 * 1 2 8
 * 1 3 9
 * 1 4 7
 * 2 4 5
 * 3 4 15
 * 3 5 6
 * 4 5 8
 * 4 6 9
 * 5 6 11   -> roads.get(m-1);
 * 0 0      -> 종료
 *
 * ! 출력
 * 각 테스트 케이스마다 한 줄에 걸쳐 절약할 수 있는 최대 비용을 출력한다.
 *
 * 51
 *
 * ? 채점 결과
 * 메모리: 266676KB
 * 시간: 1092ms
 * 언어: JAVA
 */
package Gold.IV;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G4_6497 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int n, m; // 집 개수 | 도로 개수
    static ArrayList<long[]> roads; // 도로 정보
    // 초기 설정
    static int[] parent; // 합집합 배열
    static long totalCost; // 기존 도로들의 길이의 총 합
    static long cost; // 최소 스패닝 트리일 때 도로 길이의 총 합

    public static void main(String[] args) throws IOException {
        while (true) {
            input(); // 입력 받기
            if (n == 0 && m == 0) break; // 0 0이 주어지면 종료
            parent = IntStream.range(0, n).toArray(); // 합집합 배열 생성 [0, 1, ..., n-1]
            kruskal(); // 크루스칼 알고리즘
            bw.write(String.valueOf(totalCost - cost) + "\n"); // 정답 출력
        }
        bw.flush();
    }

    public static void kruskal() {
        int count = 0; // 갯수 초기화
        for (int i = 0; i < roads.size(); i++) {
            if (count == n-1) break; // n-1개의 도로가 선택됐을 때 종료

            long[] road = roads.get(i);
            // UNION_FIND
            if (findParent((int) road[0]) != findParent((int) road[1])) {
                // 서로 연결되어있지 않은 두 집에 대해서
                union((int) road[0], (int) road[1]); // 연결 처리
                count++; // 선택한 도로 개수 1 증가
                cost += road[2]; // 비용 더하기
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

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (n == 0 && m == 0) {
            return;
        }

        // 초기화
        totalCost = 0;
        cost = 0;
        roads = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            long z = Integer.parseInt(st.nextToken());
            totalCost += z;
            roads.add(new long[] {x, y, z});
        }
        roads.sort((a,b) -> Long.compare(a[2], b[2])); // 모든 도로를 길이에 대하여 오름차순으로 정렬
    }
}
