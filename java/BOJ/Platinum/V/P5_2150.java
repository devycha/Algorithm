/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/2150
 *
 * ! 제목: Strongly Connected Component(SCC)
 * ! 시간 제한: 2초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 방향 그래프가 주어졌을 때, 그 그래프를 SCC들로 나누는 프로그램을 작성하시오.
 * 방향 그래프의 SCC는 우선 정점의 최대 부분집합이며, 그 부분집합에 들어있는 서로 다른 임의의 두 정점 u, v에 대해서 u에서 v로 가는 경로와 v에서 u로 가는 경로가 모두 존재하는 경우를 말한다.
 * 예를 들어 위와 같은 그림을 보자. 이 그래프에서 SCC들은 {a, b, e}, {c, d}, {f, g}, {h} 가 있다. 물론 h에서 h로 가는 간선이 없는 경우에도 {h}는 SCC를 이룬다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 두 정수 V(1 ≤ V ≤ 10,000), E(1 ≤ E ≤ 100,000)가 주어진다.
 * 이는 그래프가 V개의 정점과 E개의 간선으로 이루어져 있다는 의미이다.
 * 다음 E개의 줄에는 간선에 대한 정보를 나타내는 두 정수 A, B가 주어진다.
 * 이는 A번 정점과 B번 정점이 연결되어 있다는 의미이다. 이때 방향은 A → B가 된다.
 * 정점은 1부터 V까지 번호가 매겨져 있다.
 *
 * 7 9  -> n m
 * 1 4  -> a b
 * 4 5  -> a b
 * 5 1  -> a b
 * 1 6  -> a b
 * 6 7  -> a b
 * 2 7  -> a b
 * 7 3  -> a b
 * 3 7  -> a b
 * 7 2  -> a b
 *
 * ! 출력
 * 첫째 줄에 SCC의 개수 K를 출력한다. 다음 K개의 줄에는 각 줄에 하나의 SCC에 속한 정점의 번호를 출력한다. 각 줄의 끝에는 -1을 출력하여 그 줄의 끝을 나타낸다. 각각의 SCC를 출력할 때 그 안에 속한 정점들은 오름차순으로 출력한다. 또한 여러 개의 SCC에 대해서는 그 안에 속해있는 가장 작은 정점의 정점 번호 순으로 출력한다.
 *
 * ? 채점 결과과
 * 메모리: 51912KB
 * 시간: 648ms
 * 언어: JAVA8
 */
package Platinum.V;

import java.io.*;
import java.util.*;

public class P5_2150 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static ArrayList<Integer>[] way;
    static ArrayList<Integer>[] reverseWay;

    // * 초기 설정
    static boolean[] visited;
    static Stack<Integer> stack;
    static ArrayList<ArrayList<Integer>> list;

    // * 메인함수: 코사라주 알고리즘
    public static void main(String[] args) throws IOException {
        input(); // 입력받기
        
        // * 일반 DFS 수행(입력값에 따른 방향 인접 노드리스트 배열을 이용)
        // ! 스택에 저장하는 방법이 살짝 다름
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) dfs(i);
        }
        
        // * 방문리스트 초기화
        visited = new boolean[n+1];

        // * 스택에서 하나씩 뽑아서 역으로 DFS 수행(역방향 인접 노드리스트 배열을 이용)
        while (!stack.isEmpty()) {
            int s = stack.pop(); // 스택에서 뽑고
            if (visited[s]) continue; // ! 이미 방문표시가 되어있으면 SCC에 포함된 노드이기때문에 생략

            // * 새로운 SCC그룹이 생성되었으므로 리스트 추가하고 삽입
            ArrayList<Integer> newList = new ArrayList<>(); 
            list.add(newList);

            // * 역 DFS 시작
            reverseDFS(s);
        }

        // * SCC 그룹 리스트 안의 각 요소를 오름차순 정렬
        for (int i = 0; i < list.size(); i++) {
            list.get(i).sort((a, b) -> a - b);
        }
        
        // * SCC 그룹 전체를 가장 처음 요소(각 그룹의 가장 작은번호의 요소)를 기준으로 오름차순 정렬
        list.sort((a, b) -> a.get(0) - b.get(0));
        
        // * 출력
        print(list);

        // * 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // * 출력
    public static void print(ArrayList<ArrayList<Integer>> list) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(list.size() + "\n");

        // * 정렬된 순서에 맞게 출력
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                sb.append(String.valueOf(list.get(i).get(j)) + " ");
            }
            sb.append("-1\n"); // 마지막은 -1로 끝내라는 문제 조건 확인
        }

        bw.write(sb.toString()); // 출력
    }

    // * 일반 DFS
    public static void dfs(int start) {
        visited[start] = true; // 방문 표시
        
        // ? 인접 노드가 없다면
        // ? DFS의 한 방향이 끝나는 구간이므로 스택에 삽입하고 종료
        if (way[start] == null) {
            stack.push(start); 
            return;
        }
        
        // ? 인접 노드에 대해서
        for (int next: way[start]) {
            // ? 방문하지 않았다면 DFS 수행
            if (!visited[next]) dfs(next);
        }
        
        // ? 현재 노드에서 DFS 탐색이 끝났다면 스택에 저장
        stack.push(start);
    }

    // * 역 DFS
    public static void reverseDFS(int start) {
        visited[start] = true; // 방문 표시
        list.get(list.size()-1).add(start); // ! 가장 최근 SCC 그룹에 현재 노드 추가
        if (reverseWay[start] == null) return; // ! 역방향 인접 노드가 없다면 종료
        for (int next: reverseWay[start]) { // ! 역방향 인접 노드에 대하여
            if (!visited[next]) reverseDFS(next); // ! 방문한 적이 없으면 역 DFS 재귀 실행
        }

    }
    
    // * 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 노드 개수
        m = Integer.parseInt(st.nextToken()); // 간선 개수

        way = new ArrayList[n+1]; // 순방향 인접 노드리스트 배열
        reverseWay = new ArrayList[n+1]; // 역방향 인접 노드리스트 배열
        visited = new boolean[n+1]; // 방문리스트
        stack = new Stack<>(); // ! 코사라주 알고리즘을 위한 스택
        list = new ArrayList<>(); // ! SCC그룹별로 저장하는 리스트 (ArrayList<ArrayList<Integer>>)

        // ! 순방향 & 역방향 인접 노드리스트 배열 채우기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (way[a] == null) way[a] = new ArrayList<>();
            if (reverseWay[b] == null) reverseWay[b] = new ArrayList<>();

            way[a].add(b);
            reverseWay[b].add(a);
        }
    }
}
