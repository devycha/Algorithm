/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/9470
 *
 * ? 제목: Strahler 순서
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 지질학에서 하천계는 유향그래프로 나타낼 수 있다. 강은 간선으로 나타내며, 물이 흐르는 방향이 간선의 방향이 된다. 노드는 호수나 샘처럼 강이 시작하는 곳, 강이 합쳐지거나 나누어지는 곳, 바다와 만나는 곳이다.*
 * 네모 안의 숫자는 순서를 나타내고, 동그라미 안의 숫자는 노드 번호를 나타낸다.
 * 하천계의 Strahler 순서는 다음과 같이 구할 수 있다.
 * 강의 근원인 노드의 순서는 1이다.
 * 나머지 노드는 그 노드로 들어오는 강의 순서 중 가장 큰 값을 i라고 했을 때, 들어오는 모든 강 중에서 Strahler 순서가 i인 강이 1개이면 순서는 i, 2개 이상이면 순서는 i+1이다.
 * 하천계의 순서는 바다와 만나는 노드의 순서와 같다. 바다와 만나는 노드는 항상 1개이며, 위의 그림의 Strahler 순서는 3이다.
 * 하천계의 정보가 주어졌을 때, Strahler 순서를 구하는 프로그램을 작성하시오.*
 * 실제 강 중에서 Strahler 순서가 가장 큰 강은 아마존 강(12)이며, 미국에서 가장 큰 값을 갖는 강은 미시시피 강(10)이다.
 * 노드 M은 항상 바다와 만나는 노드이다.
 *
 * ? 입력 * 파싱
 * 첫째 줄에 테스트 케이스의 수 T (1 ≤ T ≤ 1000)가 주어진다.
 * 각 테스트 케이스의 첫째 줄에는 K, M, P가 주어진다. K는 테스트 케이스 번호, M은 노드의 수, P는 간선의 수이다. (2 ≤ M ≤ 1000) 다음 P개 줄에는 간선의 정보를 나타내는 A, B가 주어지며, A에서 B로 물이 흐른다는 뜻이다. (1 ≤ A, B ≤ M) M은 항상 바다와 만나는 노드이며, 밖으로 향하는 간선은 존재하지 않는다.
 *
 * 1        -> t
 * 1 7 8    -> k m p
 * 1 3      -> a b
 * 2 3      -> a b
 * 6 4      -> a b
 * 3 4      -> a b
 * 3 5      -> a b
 * 6 7      -> a b
 * 5 7      -> a b
 * 4 7      -> a b
 *
 * ? 출력
 * 각 테스트 케이스마다 테스트 케이스 번호와 입력으로 주어진 하천계의 Strahler 순서를 한 줄에 하나씩 출력한다.
 *
 * 1 3
 *
 * ? 채점 결과
 * * 시간: 0.08초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_9470 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int k, m, p;
    static int[] degree;
    static int[][] strahler;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for (int c = 1; c <= t; c++) {
            input();
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= m; i++) {
                if (degree[i] == 0) {
                    queue.add(i);
                    strahler[i] = new int[] {1, 1};
                }
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int next : list[cur]) {
                    if (strahler[next][0] == strahler[cur][0]) {
                        strahler[next][1]++;
                    } else if (strahler[next][0] < strahler[cur][0]) {
                        strahler[next] = new int[] {strahler[cur][0], 1};
                    }

                    if (--degree[next] == 0) {
                        if (strahler[next][1] > 1) {
                            strahler[next][0]++;
                        }
                        strahler[next][1] = 0;
                        queue.add(next);
                    }
                };
            }
            sb.append(c + " " + strahler[m][0] + "\n");
        }



        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        strahler = new int[m+1][2];
        degree = new int[m+1];
        list = new ArrayList[m+1];
        for (int i = 1; i <= m; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            degree[b]++;
        }
    }
}
