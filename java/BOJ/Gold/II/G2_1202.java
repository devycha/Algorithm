/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/1202
 *
 * ! 제목: 보석 도둑
 * ! 시간 제한: 1초
 * ! 메모리 제한: 256MB
 *
 * ! 문제
 * 세계적인 도둑 상덕이는 보석점을 털기로 결심했다.
 * 상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게 Mi와 가격 Vi를 가지고 있다. 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci이다. 가방에는 최대 한 개의 보석만 넣을 수 있다.
 * 상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 N과 K가 주어진다. (1 ≤ N, K ≤ 300,000)
 * 다음 N개 줄에는 각 보석의 정보 Mi와 Vi가 주어진다. (0 ≤ Mi, Vi ≤ 1,000,000)
 * 다음 K개 줄에는 가방에 담을 수 있는 최대 무게 Ci가 주어진다. (1 ≤ Ci ≤ 100,000,000)
 * 모든 숫자는 양의 정수이다.
 *
 * 3 2  -> n m
 * 1 65 -> jewels.get(0)
 * 5 23 -> ...
 * 2 99 -> jewels.get(n-1)
 * 10   -> bags.get(0)
 * 2    -> bags.get(m-1)
 *
 * ! 출력
 * 첫째 줄에 상덕이가 훔칠 수 있는 보석 가격의 합의 최댓값을 출력한다.
 *
 * 164
 *
 * ? 채점 결과
 * 시간: 1760ms
 * 메모리: 155316KB
 * 언어: JAVA8
*/
package Gold.II;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G2_1202 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n;
    static int m;
    static ArrayList<int[]> jewels = new ArrayList<>();
    static ArrayList<Integer> bags = new ArrayList<>();
    static long weight = 0L;

    public static void main(String[] args) throws IOException {
        input(); // 입력 받기
        steal(); // 보석 훔치기
        bw.write(String.valueOf(weight)); // 출력

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // * 보석 훔치기
    public static void steal() {
        int idx = 0; // 무게가 작은 순서의 보석대로
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // 훔칠 수 있는 보석들
        
        // 최대로 담을 수 있는 무게가 작은 가방 순서대로
        for (int i = 0; i < m; i++) {

            int limitWeight = bags.get(i);

            // ! 현재 가방에서 훔칠 수 있는 보석들을 최대힙(pq)에 모두 담음
            // ! => 다음의 가방에서도 해당 보석을 모두 담을 수 있다.
            // ! => 그 중에 가치가 제일 높은 보석을 우선으로 챙김(Greedy)
            while (idx < n && jewels.get(idx)[0] <= limitWeight) {
                pq.add(jewels.get(idx)[1]);
                idx++;
            }

            if (!pq.isEmpty()) {
                weight += pq.poll();
            }
        }
    }

    // * 입력받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            jewels.add(new int[] {a, b});
        }

        jewels.sort((a, b) -> a[0] - b[0]);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            bags.add(Integer.parseInt(st.nextToken()));
        }

        bags.sort((a, b) -> a - b);
    }
}
