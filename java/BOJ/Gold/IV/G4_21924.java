/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/21924
 *
 * ? 제목: 도시 건설
 * ? 시간 제한: 1초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 채완이는 신도시에 건물 사이를 잇는 양방향 도로를 만들려는 공사 계획을 세웠다.
 * 공사 계획을 검토하면서 비용이 생각보다 많이 드는 것을 확인했다.
 * 채완이는 공사하는 데 드는 비용을 아끼려고 한다.
 * 모든 건물이 도로를 통해 연결되도록 최소한의 도로를 만들려고 한다.*
 * 위 그림에서 건물과 직선으로 표시된 도로, 해당 도로를 만들 때 드는 비용을 표시해놓은 지도이다.*
 * 그림에 있는 도로를 다 설치할 때 드는 비용은 62이다. 모든 건물을 연결하는 도로만 만드는 비용은 27로 절약하는 비용은 35이다.
 * 채완이는 도로가 너무 많아 절약되는 금액을 계산하는 데 어려움을 겪고 있다.
 * 채완이를 대신해 얼마나 절약이 되는지 계산해주자.
 *
 * ? 입력 & 파싱
 * 첫 번째 줄에 건물의 개수 $N$ $(3 \le N \le 10^5 )$와 도로의 개수 $M$
 * $(2 \le M \le min( {N(N-1) \over 2}, 5×10^5)) $가 주어진다.
 * 두 번째 줄 부터 $M + 1$줄까지 건물의 번호 $a$, $b$ $(1 \le a, b \le N, a ≠ b)$와 두 건물 사이 도로를 만들 때 드는 비용 $c (1 \le c \le 10^6)$가 주어진다. 같은 쌍의 건물을 연결하는 두 도로는 주어지지 않는다.
 *
 * 7 9      -> n m
 * 1 2 15   -> a b c
 * 2 3 7    -> a b c
 * 1 3 3    -> a b c
 * 1 4 8    -> a b c
 * 3 5 6    -> a b c
 * 4 5 4    -> a b c
 * 4 6 12   -> a b c
 * 5 7 1    -> a b c
 * 6 7 6    -> a b c
 *
 * ? 출력
 * 예산을 얼마나 절약 할 수 있는지 출력한다. 만약 모든 건물이 연결되어 있지 않는다면 -1을 출력한다.
 *
 * 35
 *
 * ? 채점 결과
 * * 시간: 1.240초
 * * 메모리: 190MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G4_21924 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static long totalCost;
    static int[] parent;
    static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException {
        input();
        long cost = kruskal();
        if (cost == -1) {
            System.out.println(-1);
        } else {
            System.out.println(totalCost - cost);
        }
    }

    public static long kruskal() {
        int count = 0;
        long cost = 0;

        for (int i = 0; i < m; i++) {
            if (count == n-1) {
                break;
            }

            int[] road = list.get(i);

            if (union(road[0], road[1])) {
                count++;
                cost += road[2];
            }
        }

        return count == n-1 ? cost : -1;
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
        totalCost = 0;

        list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new int[] {a, b, c});
            totalCost += c;
        }

        list.sort((a, b) -> a[2] - b[2]);
        parent = IntStream.range(0, n+1).toArray();
    }
}
