/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/1948
 *
 * ! 문제: 임계경로
 * ! 시간 제한: 2초
 * ! 메모리 제한: 512MB
 *
 * ! 문제
 * 월드 나라는 모든 도로가 일방통행인 도로이고, 싸이클이 없다. 그런데 어떤 무수히 많은 사람들이 월드 나라의 지도를 그리기 위해서, 어떤 시작 도시로부터 도착 도시까지 출발을 하여 가능한 모든 경로를 탐색한다고 한다.
 * 이 지도를 그리는 사람들은 사이가 너무 좋아서 지도를 그리는 일을 다 마치고 도착 도시에서 모두 다 만나기로 하였다. 그렇다고 하였을 때 이들이 만나는 시간은 출발 도시로부터 출발한 후 최소 몇 시간 후에 만날 수 있는가? 즉, 마지막에 도착하는 사람까지 도착을 하는 시간을 의미한다.
 * 어떤 사람은 이 시간에 만나기 위하여 1분도 쉬지 않고 달려야 한다. 이런 사람들이 지나는 도로의 수를 카운트 하여라.
 * 출발 도시는 들어오는 도로가 0개이고, 도착 도시는 나가는 도로가 0개이다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 도시의 개수 n(1 ≤ n ≤ 10,000)이 주어지고 둘째 줄에는 도로의 개수 m(1 ≤ m ≤ 100,000)이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 도로의 정보가 주어진다. 처음에는 도로의 출발 도시의 번호가 주어지고 그 다음에는 도착 도시의 번호, 그리고 마지막에는 이 도로를 지나는데 걸리는 시간이 주어진다. 도로를 지나가는 시간은 10,000보다 작거나 같은 자연수이다.
 * 그리고 m+3째 줄에는 지도를 그리는 사람들이 출발하는 출발 도시와 도착 도시가 주어진다.
 * 모든 도시는 출발 도시로부터 도달이 가능하고, 모든 도시로부터 도착 도시에 도달이 가능하다.
 *
 * 7        -> n
 * 9        -> m
 * 1 2 4    -> a b c
 * 1 3 2    -> a b c
 * 1 4 3    -> a b c
 * 2 6 3    -> a b c
 * 2 7 5    -> a b c
 * 3 5 1    -> a b c
 * 4 6 4    -> a b c
 * 5 6 2    -> a b c
 * 6 7 5    -> a b c
 * 1 7      -> s e
 *
 * ! 출력
 * 첫째 줄에는 이들이 만나는 시간을, 둘째 줄에는 1분도 쉬지 않고 달려야 하는 도로의 수가 몇 개인지 출력하여라.
 *
 * 12
 * 5
 *
 * ? 채점 결과
 * 메모리: 53380KB
 * 시간: 412ms
 * 언어: JAVA8
 */
package Platinum.V;

import java.io.*;
import java.util.*;

public class P5_1948 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int n, m, s, e; // n: 도시개수, m: 도로개수, s: 출발도시, e: 도착도시
    static HashMap<Integer, ArrayList<int[]>> map; // 인접 도시 리스트

    // 초기 설정
    static int[] degree; // 진입차수 위상배열
    static int[] time; // 각 도시에 도착하는데 가장 오래 걸리는 시간
    static boolean[] check; // 1분도 쉬지 않고 달려야 하는 도로의 개수를 구하기 위한 방문 리스트
    static ArrayList<Integer>[] maxTimeBefore; // 가장 오래시간 걸린 이전 출발점
    static int count = 0; // 1분도 쉬지 않고 달려야하는 도로의 개수

    // 메인
    public static void main(String[] args) throws IOException {
        input(); // 입력받기
        degreeSort(); // 위상정렬: Topological Sort
        countRoads(e); // 역추적 DFS를 이용해서 1분도 쉬지 않고 달려야하는 도로의 개수

        // 정답 출력
        bw.write(String.valueOf(time[e]) + "\n");
        bw.write(String.valueOf(count));

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // 위상 정렬: Topological Sort
    public static void degreeSort() {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {s, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int city = cur[0];
            int t = cur[1];

            if (city == e) continue;

            // 도로로 연결된 도시에 대해서
            for (int[] next: map.get(city)) {
                // 현재 해당 도시로 갈 수 있는 시간이 기존보다 더 오래 걸리는 경우
                if (time[next[0]] < t + next[1]) {
                    // 더 큰 시간 값으로 업데이트
                    time[next[0]] = t + next[1];

                    // 현재 도시 출발점을 **새롭게** 리스트에 담아서 저장
                    // maxTimeBefore[다음도시] = [현재도시]
                    maxTimeBefore[next[0]] =  new ArrayList<>();
                    maxTimeBefore[next[0]].add(city);
                } else if (time[next[0]] ==  t + next[1]) {
                    // 같은 시간이 걸리는 경우에는 리스트에 현재 도시 출발점을 추가
                    maxTimeBefore[next[0]].add(city);
                }
                
                // 다음 도시의 진입차수가 0이되면 더이상 해당 도시로 도착할 수 있는 경우가 없기 때문에 
                // queue에 삽입
                if (--degree[next[0]] == 0) {
                    queue.add(new int[] {next[0], time[next[0]]});
                }
            }
        }
    }

    // 역추적 DFS를 이용한 1분도 쉬지 않고 달려아하는 도로의 개수
    public static void countRoads(int start) {
        if (check[start]) return; // 이미 해당 도시에서 시작점까지 가는 도로를 모두 탐색한 경우 종료
        check[start] = true; // 현재 도시를 방문표시
        if (maxTimeBefore[start] == null) return; // 다음으로 갈 도시가 시작점이라면 종료

        // 다음으로 갈 도로에 대해서 
        for (int next: maxTimeBefore[start]) {
            count++; // 카운트 증가
            countRoads(next); // 재귀 DFS
        }
    }

    // 입력 받기
    public static void input() throws IOException {
        // 파싱
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (!map.containsKey(a)) map.put(a, new ArrayList<>());
            map.get(a).add(new int[] {b, c});
            degree[b]++;
        }

        // 초기 설정
        time = new int[n+1];
        degree = new int[n+1];
        check = new boolean[n+1];
        maxTimeBefore = new ArrayList[n+1];
    }
}
