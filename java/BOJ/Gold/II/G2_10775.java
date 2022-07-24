/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/10775
 *
 * ! 제목: 공항
 * ! 시간 제한: 1초
 * ! 메모리 제한: 256MB
 *
 * ! 문제
 * 오늘은 신승원의 생일이다.
 * 박승원은 생일을 맞아 신승원에게 인천국제공항을 선물로 줬다.
 * 공항에는 G개의 게이트가 있으며 각각은 1에서 G까지의 번호를 가지고 있다.
 * 공항에는 P개의 비행기가 순서대로 도착할 예정이며, 당신은 i번째 비행기를 1번부터 gi (1 ≤ gi ≤ G) 번째 게이트중 하나에 영구적으로 도킹하려 한다. 비행기가 어느 게이트에도 도킹할 수 없다면 공항이 폐쇄되고, 이후 어떤 비행기도 도착할 수 없다.
 * 신승원은 가장 많은 비행기를 공항에 도킹시켜서 박승원을 행복하게 하고 싶어한다. 승원이는 비행기를 최대 몇 대 도킹시킬 수 있는가?
 *
 * ! 입력 & 파싱
 * 첫 번째 줄에는 게이트의 수 G (1 ≤ G ≤ 105)가 주어진다.
 * 두 번째 줄에는 비행기의 수 P (1 ≤ P ≤ 105)가 주어진다.
 * 이후 P개의 줄에 gi (1 ≤ gi ≤ G) 가 주어진다.
 *
 * 4    -> g
 * 3    -> p
 * 4    -> dock
 * 1    -> dock
 * 1    -> dock
 *
 * ! 출력
 * 승원이가 도킹시킬 수 있는 최대의 비행기 수를 출력한다.
 *
 * 2
 *
 * ? 채점 결과
 * 시간: 364ms
 * 메모리: 29036KB
 */
package Gold.II;

import java.io.*;
import java.util.stream.IntStream;

public class G2_10775 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        // * 파싱
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        // * 분리 집합(UNION_FIND)
        int[] parent = IntStream.range(0, g+1).toArray();
        
        // * 도킹 완료된 비행기의 수
        int count = 0;

        // * 순서대로 들어오는 모든 비행기에 대해서
        for (int i = 0; i < p; i++) {
            int dock = Integer.parseInt(br.readLine()); // * 현재 비행기의 번호
            union(parent, dock, parent[dock]); // * UNION_FIND => 도킹 가능한 게이트 번호를 찾음.

            dock = parent[dock]; // * 도킹 가능한 곳을 dock에 저장
            if (dock == 0) break; // * 도킹할 곳이 더이상 없으면 폐쇠하고 종료

            parent[dock] -= 1; // * 도킹 완료시 해당 게이트의 분리집합 값을 -1 (Greedy)
            count++;
        }

        bw.write(String.valueOf(count));

        bw.flush();
        br.close();
        bw.close();
    }

    // * UNION_FIND 함수
    public static boolean union(int[] parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);

        if (a == b) return false;
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
        return true;
    }

    public static int findParent(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent, parent[node]);
        }
        return parent[node];
    }
}
