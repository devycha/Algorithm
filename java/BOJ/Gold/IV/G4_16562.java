/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/16562
 *
 * ! 문제: 친구비
 * ! 시간 제한: 2초
 * ! 메모리 제한: 512MB
 *
 * ! 문제
 * 19학번 이준석은 학생이 N명인 학교에 입학을 했다. 준석이는 입학을 맞아 모든 학생과 친구가 되고 싶어한다. 하지만 준석이는 평생 컴퓨터랑만 대화를 하며 살아왔기 때문에 사람과 말을 하는 법을 모른다. 그런 준석이에게도 희망이 있다. 바로 친구비다!
 * 학생 i에게 Ai만큼의 돈을 주면 그 학생은 1달간 친구가 되어준다! 준석이에게는 총 k원의 돈이 있고 그 돈을 이용해서 친구를 사귀기로 했다. 막상 친구를 사귀다 보면 돈이 부족해질 것 같다는 생각을 하게 되었다. 그래서 준석이는 “친구의 친구는 친구다”를 이용하기로 했다.
 * 준석이는 이제 모든 친구에게 돈을 주지 않아도 된다!
 * 위와 같은 논리를 사용했을 때, 가장 적은 비용으로 모든 사람과 친구가 되는 방법을 구하라.
 *
 * ! 입력
 * 첫 줄에 학생 수 N (1 ≤ N ≤ 10,000)과 친구관계 수 M (0 ≤ M ≤ 10,000), 가지고 있는 돈 k (1 ≤ k ≤ 10,000,000)가 주어진다.
 * 두번째 줄에 N개의 각각의 학생이 원하는 친구비 Ai가 주어진다. (1 ≤ Ai ≤ 10,000, 1 ≤ i ≤ N)
 * 다음 M개의 줄에는 숫자 v, w가 주어진다. 이것은 학생 v와 학생 w가 서로 친구라는 뜻이다. 자기 자신과 친구일 수도 있고, 같은 친구 관계가 여러 번 주어질 수도 있다.
 *
 * 5 3 20           -> n m k
 * 10 10 20 20 30   -> 각 친구 사귈 때 비용
 * 1 3              -> 이미 친구인 경우들...
 * 2 4
 * 5 4
 *
 * ! 출력
 * 준석이가 모든 학생을 친구로 만들 수 있다면, 친구로 만드는데 드는 최소비용을 출력한다. 만약 친구를 다 사귈 수 없다면, “Oh no”(따옴표 제거)를 출력한다.
 *
 * 20
 *
 * ? 채점 결과
 * 메모리: 23820KB
 * 시간: 396ms
 * 언어: JAVA
 */
package Gold.IV;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G4_16562 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m, k;
    static int count; // 친구 연결고리 갯수
    static int cost; // 총 비용
    static int[] parent; // 합집합
    static LinkedList<int[]> friends; // 친구를 사귀는데 드는 비용

    public static void main(String[] args) throws IOException {
        input(); // 입력받기
        int result = kruskal(); // 크루스칼
        bw.write(result == -1 ? "Oh no" : String.valueOf(result)); // 결과 출력
        bw.flush();
    }

    // 크루스칼
    public static int kruskal() {
        cost = 0; // 비용 초기화
        parent = IntStream.range(0, n+1).toArray(); // 합집합 초기화
        count = 0;
        while (!friends.isEmpty()) {
            if (count == n) break; // 자기자신을 0번으로 추가해서 0번부터 n번까지의 노드를 최소 스패닝 트리로 작성
            if (cost > k) break; // 이미 가지고 있는 비용을 넘어섰다면 종료

            int[] friend = friends.poll();
            // UNION_FIND
            if (findParent(friend[0]) != findParent(friend[1])) {
                union(friend[0], friend[1]);
                cost += friend[2];
                count++;
            }
        }
        return (cost > k) || (count < n) ? -1 : cost;
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        friends = new LinkedList<int[]>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int friendCost = Integer.parseInt(st.nextToken());
            friends.add(new int[] {0, i, friendCost}); // 친구를 사귈때 드는 비용을 삽입 [자기자신, 친구번호, 비용]
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            friends.add(new int[] {v, w, 0}); // 이미 친구인 경우는 비용이 0이기 때문에 [친구1, 친구2, 비용(=0)] 삽입
        }

        friends.sort((a, b) -> a[2] - b[2]); // 비용에 대해 오름차순 정렬
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

        if (b > a) parent[b] = a;
        else parent[a] = b;
    }
}
