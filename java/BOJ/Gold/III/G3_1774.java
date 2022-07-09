/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/1774
 *
 * ! 시간 제한: 2초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 황선자씨는 우주신과 교감을 할수 있는 채널러 이다. 하지만 우주신은 하나만 있는 것이 아니기때문에 황선자 씨는 매번 여럿의 우주신과 교감하느라 힘이 든다. 이러던 와중에 새로운 우주신들이 황선자씨를 이용하게 되었다.
 * 하지만 위대한 우주신들은 바로 황선자씨와 연결될 필요가 없다. 이미 황선자씨와 혹은 이미 우주신끼리 교감할 수 있는 우주신들이 있기 때문에 새로운 우주신들은 그 우주신들을 거쳐서 황선자 씨와 교감을 할 수 있다.
 * 우주신들과의 교감은 우주신들과 황선자씨 혹은 우주신들 끼리 이어진 정신적인 통로를 통해 이루어 진다. 하지만 우주신들과 교감하는 것은 힘든 일이기 때문에 황선자씨는 이런 통로들이 긴 것을  좋아하지 않는다. 왜냐하면 통로들이 길 수록 더 힘이 들기 때문이다.
 * 또한 우리들은 3차원 좌표계로 나타낼 수 있는 세상에 살고 있지만 우주신들과 황선자씨는 2차원 좌표계로 나타낼 수 있는 세상에 살고 있다. 통로들의 길이는 2차원 좌표계상의 거리와 같다.
 * 이미 황선자씨와 연결된, 혹은 우주신들과 연결된 통로들이 존재한다. 우리는 황선자 씨를 도와 아직 연결이 되지 않은 우주신들을 연결해 드려야 한다. 새로 만들어야 할 정신적인 통로의 길이들이 합이 최소가 되게 통로를 만들어 “빵상”을 외칠수 있게 도와주자.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 우주신들의 개수(N<=1,000) 이미 연결된 신들과의 통로의 개수(M<=1,000)가 주어진다.
 * 두 번째 줄부터 N개의 줄에는 황선자를 포함하여 우주신들의 좌표가 (0<= X<=1,000,000), (0<=Y<=1,000,000)가 주어진다. 그 밑으로 M개의 줄에는 이미 연결된 통로가 주어진다. 번호는 위의 입력받은 좌표들의 순서라고 생각하면 된다. 좌표는 정수이다.
 *
 * 4 1  -> n m
 * 1 1  1번 위치
 * 3 1
 * 2 3
 * 4 3  n번 위치
 * 1 4  이미 연결된 신
 *
 * ! 출력
 * 첫째 줄에 만들어야 할 최소의 통로 길이를 출력하라. 출력은 소수점 둘째짜리까지 출력하여라.
 *
 * 4.00
 *
 * ? 채점 결과
 * 메모리: 52528KB
 * 시간: 916ms
 * 언어: JAVA
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G3_1774 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n, m, location[][], parent[];
    static double cost = 0;
    static ArrayList<double[]> roads;

    public static void main(String[] args) throws IOException {
        input(); // 입력값 받기
        kruskal(); // 크루스칼
        bw.write(String.format("%.2f", cost)); // 출력
        bw.flush();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        roads = new ArrayList<double[]>();
        location = new int[n+1][2];
        parent = IntStream.range(0, n+1).toArray();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            location[i] =  new int[] {x, y};
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        for (int i = 1; i < location.length - 1; i++) {
            for (int j = i+1; j < location.length; j++) {
                double dist = Math.sqrt(Math.pow(location[i][0] - location[j][0], 2) + Math.pow(location[i][1] - location[j][1], 2));
                roads.add(new double[] {(double) i, (double) j, dist});
            }
        }

        roads.sort((a, b) -> {
            if (a[2] - b[2] > 0) return 1;
            if (a[2] - b[2] < 0) return -1;
            return 0;
        });
    }

    public static void kruskal() {
        int count = m;
        for (int i = 0; i < roads.size(); i++) {
            int x = (int) roads.get(i)[0];
            int y = (int) roads.get(i)[1];
            double dist = roads.get(i)[2];

            if (findParent(x) != findParent(y)) {
                count++;
                union(x, y);
                cost += dist;
            }
        }
    }

    public static int findParent(int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node]);
        }
        return parent[node];
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (b > a) parent[b] = a;
        else parent[a] = b;
    }
}
