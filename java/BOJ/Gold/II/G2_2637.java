/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/2637
 *
 * ! 제목: 장난감 조립
 * ! 시간 제한: 1초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 우리는 어떤 장난감을 여러 가지 부품으로 조립하여 만들려고 한다. 이 장난감을 만드는데는 기본 부품과 그 기본 부품으로 조립하여 만든 중간 부품이 사용된다. 기본 부품은 다른 부품을 사용하여 조립될 수 없는 부품이다. 중간 부품은 또 다른 중간 부품이나 기본 부품을 이용하여 만들어지는 부품이다.
 * 예를 들어보자. 기본 부품으로서 1, 2, 3, 4가 있다. 중간 부품 5는 2개의 기본 부품 1과 2개의 기본 부품 2로 만들어진다. 그리고 중간 부품 6은 2개의 중간 부품 5, 3개의 기본 부품 3과 4개의 기본 부품 4로 만들어진다. 마지막으로 장난감 완제품 7은 2개의 중간 부품 5, 3개의 중간 부품 6과 5개의 기본 부품 4로 만들어진다. 이런 경우에 장난감 완제품 7을 만드는데 필요한 기본 부품의 개수는 1번 16개, 2번 16개, 3번 9개, 4번 17개이다.
 * 이와 같이 어떤 장난감 완제품과 그에 필요한 부품들 사이의 관계가 주어져 있을 때 하나의 장난감 완제품을 조립하기 위하여 필요한 기본 부품의 종류별 개수를 계산하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에는 자연수 N(3 ≤ N ≤ 100)이 주어지는데, 1부터 N-1까지는 기본 부품이나 중간 부품의 번호를 나타내고, N은 완제품의 번호를 나타낸다. 그리고 그 다음 줄에는 자연수 M(3 ≤ M ≤ 100)이 주어지고, 그 다음 M개의 줄에는 어떤 부품을 완성하는데 필요한 부품들 간의 관계가 3개의 자연수 X, Y, K로 주어진다. 이 뜻은 "중간 부품이나 완제품 X를 만드는데 중간 부품 혹은 기본 부품 Y가 K개 필요하다"는 뜻이다. 두 중간 부품이 서로를 필요로 하는 경우가 없다.
 *
 * 7        -> n
 * 8        -> m
 * 5 1 2    -> x y z
 * 5 2 2    -> x y z
 * 7 5 2    -> x y z
 * 6 5 2    -> x y z
 * 6 3 3    -> x y z
 * 6 4 4    -> x y z
 * 7 6 3    -> x y z
 * 7 4 5    -> x y z
 *
 * ! 출력
 * 하나의 완제품을 조립하는데 필요한 기본 부품의 수를 한 줄에 하나씩 출력하되(중간 부품은 출력하지 않음), 반드시 기본 부품의 번호가 작은 것부터 큰 순서가 되도록 한다. 각 줄에는 기본 부품의 번호와 소요 개수를 출력한다.
 * 정답은 2,147,483,647 이하이다.
 *
 * 1 16
 * 2 16
 * 3 9
 * 4 17
 *
 * ? 채점 결과
 * 시간: 80ms
 * 메모리: 11800KB
 * 언어: JAVA8
 */
package Gold.II;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G2_2637 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // 파싱
    static int n, m;
    
    // 초기 설정
    static int[] basicParts, degree, result;
    static ArrayList<Node>[] list;

    // 부품 번호와 갯수를 의미하는 노드 클래스
    public static class Node {
        int num;
        int count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기
        topologicalSort(); // * 위상 정렬

        // * 기본부품인 경우에만 필요한 부품수 출력
        for (int i = 1; i <= n; i++) {
            if (basicParts[i] == 0) {
                bw.write(String.valueOf(i) + " " + String.valueOf(result[i]) + "\n");
            }
        }

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 위상 정렬
    public static void topologicalSort() {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(new Node(n, 1)); // 완제품 번호(n)와 갯수(1)을 삽입
        result[n] = 1; // 필요한 완제품의 갯수 1로 설정
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // * 현재 부품을 만들기 위해 필요한 부품들에 대하여
            for (Node next: list[cur.num]) {
                result[next.num] += result[cur.num] * next.count; // * 현재 부품의 필요한 개수 x 현재 부품 1개를 만들기 위해 필요한 부속 부품의 개수
                // * 더이상 해당 부속 부품을 필요로하는 상위 부품이 없을 때
                if (--degree[next.num] == 0) {
                    queue.add(new Node(next.num, result[next.num])); // * 큐에 삽입
                }
            }
        }
    }

    // * 입력 받기
    public static void input() throws IOException {
         n = Integer.parseInt(br.readLine());
         m = Integer.parseInt(br.readLine());

         list = new ArrayList[n+1];
         for (int i = 1; i <= n; i++) {
             list[i] = new ArrayList<>();
         }

         basicParts = new int[n+1];
         degree = new int[n+1];
         result = new int[n+1];

         for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            list[x].add(new Node(y, z));
            basicParts[x]++;
            degree[y]++;

         }
    }
}
