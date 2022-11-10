/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14950
 *
 * ? 제목: 정복자
 * ? 시간 제한: 2초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 서강 나라는 N개의 도시와 M개의 도로로 이루어졌다. 모든 도시의 쌍에는 그 도시를 연결하는 도로로 구성된 경로가 있다. 각 도로는 양방향 도로이며, 각 도로는 사용하는데 필요한 비용이 존재한다. 각각 도시는 1번부터 N번까지 번호가 붙여져 있다. 그 중에서 1번 도시의 군주 박건은 모든 도시를 정복하고 싶어한다.
 * 처음 점거하고 있는 도시는 1번 도시 뿐이다. 만약 특정 도시 B를 정복하고 싶다면, B와 도로로 연결된 도시들 중에서 적어도 하나를 정복하고 있어야 한다. 조건을 만족하는 도시 중에서 하나인 A를 선택하면, B를 정복하는 과정에서 A와 B를 연결하는 도로의 비용이 소모된다. 박건은 한번에 하나의 도시만 정복을 시도하고 언제나 성공한다. 한 번 도시가 정복되면, 모든 도시는 경계를 하게 되기 때문에 모든 도로의 비용이 t만큼 증가하게 된다. 한 번 정복한 도시는 다시 정복하지 않는다.
 * 이때 박건이 모든 도시를 정복하는데 사용되는 최소 비용을 구하시오.
 *
 * ? 입력
 * 첫째 줄에 도시의 개수 N과 도로의 개수 M과 한번 정복할 때마다 증가하는 도로의 비용 t가 주어진다. N은 10000보다 작거나 같은 자연수이고, M은 30000보다 작거나 같은 자연수이다. t는 10이하의 자연수이다.
 * M개의 줄에는 도로를 나타내는 세 자연수 A, B, C가 주어진다. A와 B사이에 비용이 C인 도로가 있다는 뜻이다. A와 B는 N이하의 서로 다른 자연수이다. C는 10000 이하의 자연수이다.
 *
 * 4 5 8    -> n m t
 * 1 2 3    -> a b c
 * 1 3 2    -> a b c
 * 2 3 2    -> a b c
 * 2 4 4    -> a b c
 * 3 4 1    -> a b c
 *
 * ? 출력
 * 모든 도시를 정복하는데 사용되는 최소 비용을 출력하시오.
 *
 * 29
 *
 * ? 채점 결과
 * * 시간: 0.468초
 * * 메모리: 31MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G3_14950 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m, t;
    static int[] parent;
    static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException {
        input();
        int answer = kruskal();
        System.out.println(answer);
    }

    public static int kruskal() {
        int count = 0;
        int cost = 0;

        for (int i = 0; i < m; i++) {
            int[] road = list.get(i);

            if (count == n-1) {
                break;
            }

            if (union(road[0], road[1])) {
                cost += road[2] + t * count;
                count++;
            }
        }

        return cost;
    }

    public static int findParent(int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node]);
        }
        return parent[node];
    }

    public static boolean union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a == b) {
            return false;
        }

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }

        return true;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        parent = IntStream.range(0, n+1).toArray();
        list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new int[] {a, b, c});
        }

        list.sort((a, b) -> a[2] - b[2]);
    }
}
