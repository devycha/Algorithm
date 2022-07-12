/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/14938
 *
 * ! 제목: 서강그라운드
 * ! 시간 제한: 1초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 예은이는 요즘 가장 인기가 있는 게임 서강그라운드를 즐기고 있다. 서강그라운드는 여러 지역중 하나의 지역에 낙하산을 타고 낙하하여, 그 지역에 떨어져 있는 아이템들을 이용해 서바이벌을 하는 게임이다. 서강그라운드에서 1등을 하면 보상으로 치킨을 주는데, 예은이는 단 한번도 치킨을 먹을 수가 없었다. 자신이 치킨을 못 먹는 이유는 실력 때문이 아니라 아이템 운이 없어서라고 생각한 예은이는 낙하산에서 떨어질 때 각 지역에 아이템 들이 몇 개 있는지 알려주는 프로그램을 개발을 하였지만 어디로 낙하해야 자신의 수색 범위 내에서 가장 많은 아이템을 얻을 수 있는지 알 수 없었다.
 * 각 지역은 일정한 길이 l (1 ≤ l ≤ 15)의 길로 다른 지역과 연결되어 있고 이 길은 양방향 통행이 가능하다. 예은이는 낙하한 지역을 중심으로 거리가 수색 범위 m (1 ≤ m ≤ 15) 이내의 모든 지역의 아이템을 습득 가능하다고 할 때, 예은이가 얻을 수 있는 아이템의 최대 개수를 알려주자.
 * 주어진 필드가 위의 그림과 같고, 예은이의 수색범위가 4라고 하자. ( 원 밖의 숫자는 지역 번호, 안의 숫자는 아이템 수, 선 위의 숫자는 거리를 의미한다) 예은이가 2번 지역에 떨어지게 되면 1번,2번(자기 지역), 3번, 5번 지역에 도달할 수 있다. (4번 지역의 경우 가는 거리가 3 + 5 = 8 > 4(수색범위) 이므로 4번 지역의 아이템을 얻을 수 없다.) 이렇게 되면 예은이는 23개의 아이템을 얻을 수 있고, 이는 위의 필드에서 예은이가 얻을 수 있는 아이템의 최대 개수이다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에는 지역의 개수 n (1 ≤ n ≤ 100)과 예은이의 수색범위 m (1 ≤ m ≤ 15), 길의 개수 r (1 ≤ r ≤ 100)이 주어진다.
 * 둘째 줄에는 n개의 숫자가 차례대로  각 구역에 있는 아이템의 수 t (1 ≤ t ≤ 30)를 알려준다.
 * 세 번째 줄부터 r+2번째 줄 까지 길 양 끝에 존재하는 지역의 번호 a, b, 그리고 길의 길이 l (1 ≤ l ≤ 15)가 주어진다.
 *
 * 5 5 4        -> n m r
 * 5 7 8 2 3    -> weapons(아이템 갯수 배열)
 * 1 4 5
 * 5 2 4
 * 3 2 3
 * 1 2 3
 *
 * ! 출력
 * 예은이가 얻을 수 있는 최대 아이템 개수를 출력한다.
 *
 * 23
 *
 * ? 채점 결과
 * 메모리: 11984KB
 * 시간: 104ms
 * 언어: JAVA
 */
package Gold.IV;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_14938 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n, m, r; // n: 낙하위치 개수, m: 수색범위, r: 경로 개수
    static int[] weapons; // 낙하위치에 있는 아이템 갯수
    static int[][] floyd; // 모든 낙하 위치에서 다른 모든 낙하 위치까지의 최단 경로

    public static void main(String[] args) throws IOException {
        input(); // 입력값 받기
        floydWashall(); // 플로이드 와샬
        bw.write(String.valueOf(findMaxItems())); // 얻을 수 있는 아이템의 최대 갯수 출력
        bw.flush();
    }

    // 플로이드 와샬
    public static void floydWashall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (floyd[i][j] > floyd[i][k] + floyd[k][j]) {
                        floyd[i][j] = floyd[i][k] + floyd[k][j];
                    }
                }
            }
        }
    }

    // 각 낙하위치에서 수색 범위 안으로 도착할 수 있는 낙하위치의 아이템들을 비교하여 최댓값 탐색
    public static int findMaxItems() {
        int maxItems = 0;
        for (int i = 1; i <= n; i++) {
            int items = 0;
            for (int j = 1; j <= n; j++) {
                if (floyd[i][j] <= m) items += weapons[j];
            }
            if (maxItems < items) maxItems = items;
        }

        return maxItems;
    }

    // 입력값 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        weapons = new int[n+1];
        for (int i = 1; i <= n; i++) {
            weapons[i] = Integer.parseInt(st.nextToken());
        }

        floyd = new int[n+1][n+1];
        for (int[] _f : floyd) {
            Arrays.fill(_f, 2000);
        }

        for (int i = 1; i <= n; i++) {
            floyd[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            floyd[a][b] = Math.min(floyd[a][b], c);
            floyd[b][a] = Math.min(floyd[b][a], c);
        }

    }
}
