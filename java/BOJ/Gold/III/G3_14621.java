/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14621
 *
 * ? 제목: 나만 안되는 연애
 * ? 시간 제한: 2초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 깽미는 24살 모태솔로이다. 깽미는 대마법사가 될 순 없다며 자신의 프로그래밍 능력을 이용하여 미팅 어플리케이션을 만들기로 결심했다. 미팅 앱은 대학생을 타겟으로 만들어졌으며 대학교간의 도로 데이터를 수집하여 만들었다.
 * 이 앱은 사용자들을 위해 사심 경로를 제공한다. 이 경로는 3가지 특징을 가지고 있다.
 * 사심 경로는 사용자들의 사심을 만족시키기 위해 남초 대학교와 여초 대학교들을 연결하는 도로로만 이루어져 있다.
 * 사용자들이 다양한 사람과 미팅할 수 있도록 어떤 대학교에서든 모든 대학교로 이동이 가능한 경로이다.
 * 시간을 낭비하지 않고 미팅할 수 있도록 이 경로의 길이는 최단 거리가 되어야 한다.
 * 만약 도로 데이터가 만약 왼쪽의 그림과 같다면, 오른쪽 그림의 보라색 선과 같이 경로를 구성하면 위의 3가지 조건을 만족하는 경로를 만들 수 있다.
 * 이때, 주어지는 거리 데이터를 이용하여 사심 경로의 길이를 구해보자.
 *
 * ? 입력 & 파싱
 * 입력의 첫째 줄에 학교의 수 N와 학교를 연결하는 도로의 개수 M이 주어진다. (2 ≤ N ≤ 1,000) (1 ≤ M ≤ 10,000)
 * 둘째 줄에 각 학교가 남초 대학교라면 M, 여초 대학교라면 W이 주어진다.
 * 다음 M개의 줄에 u v d가 주어지며 u학교와 v학교가 연결되어 있으며 이 거리는 d임을 나타낸다. (1 ≤ u, v ≤ N) , (1 ≤ d ≤ 1,000)
 *
 * 5 7          -> n m
 * M W W W M    -> boys[1] ~ boys[n]
 * 1 2 12       -> a, b, c
 * 1 3 10       -> a, b, c
 * 4 2 5        -> a, b, c
 * 5 2 5        -> a, b, c
 * 2 5 10       -> a, b, c
 * 3 4 3        -> a, b, c
 * 5 4 7        -> a, b, c
 *
 * ? 출력
 * 깽미가 만든 앱의 경로 길이를 출력한다. (모든 학교를 연결하는 경로가 없을 경우 -1을 출력한다.)
 *
 * 34
 *
 * ? 채점 결과
 * * 시간: 372ms
 * * 메모리: 226MB
 * * 언어: JAVA8
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G3_14621 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static boolean[] boys;
    static ArrayList<int[]> list;

    // * 초기 설정
    static int count = 0, length = 0;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        kruskal(); // * 2. 크루스칼 알고리즘

        // * 3. 모든 대학교가 연결됐을 때 그 경로를 출력 vs 연결 못했을 때 -1 출력
        bw.write(String.valueOf(count == n-1 ? length : -1));

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boys = new boolean[n+1];
        int p = 1;
        for (String s: br.readLine().split(" ")) {
            if (s.equals("M")) {
                boys[p++] = true;
            } else {
                boys[p++] = false;
            }
        }

        list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new int[] {a, b, c});
        }
        list.sort((school1, school2) -> school1[2] - school2[2]); // * 경로의 길이를 기준으로 오름차순 정렬
        parent = IntStream.range(0, n+1).toArray();
    }

    // ! 크루스칼 알고리즘
    public static void kruskal() {
        for (int i = 0; i < list.size(); i++) {
            if (count == n-1) {
                break;
            }

            int[] con = list.get(i);
            if (boys[con[0]] == boys[con[1]]) { // * 남초대학 - 남초대학 | 여초대학 - 여초대학 사이의 경로는 패스
                continue;
            }

            if (union(con[0], con[1])) { // * 사이클이 발생하지 않는다면 해당 경로를 선택하고 경로 길이 업데이트
                count++;
                length += con[2];
            }
        }
    }

    // ! UNION_FIND (합집합 찾기)
    public static int findParent(int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node]);
        }

        return parent[node];
    }

    // ! UNION_FIND (합집합 찾기)
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
}
