/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/2887
 *
 * ! 제목: 행성 터널
 * ! 시간 제한: 1초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 때는 2040년, 이민혁은 우주에 자신만의 왕국을 만들었다. 왕국은 N개의 행성으로 이루어져 있다. 민혁이는 이 행성을 효율적으로 지배하기 위해서 행성을 연결하는 터널을 만들려고 한다.
 * 행성은 3차원 좌표위의 한 점으로 생각하면 된다. 두 행성 A(xA, yA, zA)와 B(xB, yB, zB)를 터널로 연결할 때 드는 비용은 min(|xA-xB|, |yA-yB|, |zA-zB|)이다.
 * 민혁이는 터널을 총 N-1개 건설해서 모든 행성이 서로 연결되게 하려고 한다. 이때, 모든 행성을 터널로 연결하는데 필요한 최소 비용을 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 행성의 개수 N이 주어진다. (1 ≤ N ≤ 100,000) 다음 N개 줄에는 각 행성의 x, y, z좌표가 주어진다. 좌표는 -109보다 크거나 같고, 109보다 작거나 같은 정수이다. 한 위치에 행성이 두 개 이상 있는 경우는 없다.
 *
 * 5            -> n
 * 11 -15 -15   -> x y z
 * 14 -5 -15    -> x y z
 * -1 -1 -5     -> x y z
 * 10 -4 -1     -> x y z
 * 19 -4 19     -> x y z
 *
 * ! 출력
 * 첫째 줄에 모든 행성을 터널로 연결하는데 필요한 최소 비용을 출력한다.
 *
 * 4
 *
 * ? 채점 결과
 * 시간: 1360ms
 * 메모리: 79808KB
 * 언어: JAVA8
 */
package Platinum.V;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class P5_2887 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // * 파싱
    static int n;
    static ArrayList<int[]> nodeList = new ArrayList<>();

    // * 초기 설정
    static int cost = 0; // 최소 비용
    static int[] parent; // 합집합

    // * X좌표간의 거리, Y좌표간의 거리, Z좌표간의 거리를 기준으로 Edge는 저장하는 리스트
    static ArrayList<Edge> edgeList = new ArrayList<>();

    // * 3차원 좌표 -> X좌표간의 거리 Edge, Y좌표간의 Edge, Z좌표간의 거리 Edge
    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    // ! 메인
    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기
        // * X, Y, Z를 중심으로 각각 3번 정렬 후 X좌표간의 거리, Y좌표간의 거리, Z좌표간의 거리를 기준으로 Edge를 만들어 edgeList에 삽입
        for (int k = 1; k <= 3; k++) {
            int sortingCoordinate = k;
            nodeList.sort((a, b) -> a[sortingCoordinate] - b[sortingCoordinate]);
            
            for (int i = 0; i < n-1; i++) {
                Edge edge = new Edge(nodeList.get(i)[0], nodeList.get(i+1)[0], Math.abs(nodeList.get(i)[k] - nodeList.get(i+1)[k]));
                edgeList.add(edge);
            }
        }
        kruskal(); // * 크루스칼 알고리즘
        bw.write(String.valueOf(cost)); // * 최소비용 출력

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 크루스칼 알고리즘
    public static void kruskal() {
        int count = 0;
        edgeList.sort((a, b) -> a.weight - b.weight); // * edgeList를 간선의 가중치를 기준으로 오름차순 정렬
        for (int i = 0; i < edgeList.size(); i++) {
            if (count == n-1) break; // * 최소 스패닝 트리가 완성되었다면 종료

            Edge edge = edgeList.get(i); // * 간선 선택
            if (union(edge.from, edge.to)) { // * 싸이클이 발생하지 않는다면 해당 간선을 선택하고 가중치를 cost에 더함
                count++;
                cost += edge.weight;
            }
        }
    }

    // ! UNION_FIND 합집합 찾기
    public static int findParent(int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node]);
        }
        return parent[node];
    }

    // ! UNION_FIND 합집합 찾기
    public static boolean union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a == b) return false;

        if (a < b) parent[b] = a;
        else parent[a] = b;

        return true;
    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // * [좌표번호, X좌표, Y좌표, Z좌표]
            nodeList.add(new int[] {i, x, y, z});
        }

        parent = IntStream.range(0, n).toArray();
    }
}
