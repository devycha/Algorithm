/**
 * !문제 출처: 백준 온라인 져지
 * address: https://www.acmicpc.net/problem/17472
 *
 * ! 시간제한: 1초
 * ! 메모리제한: 512MB
 *
 * ! 문제(링크 참조)
 * address: https://www.acmicpc.net/problem/17472
 *
 * ! 입력 & 파싱
 * 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. 둘째 줄부터 N개의 줄에 지도의 정보가 주어진다. 각 줄은 M개의 수로 이루어져 있으며, 수는 0 또는 1이다. 0은 바다, 1은 땅을 의미한다.
 *
 * 7 8              -> n m
 * 0 0 0 0 0 0 1 1  -> arr[0][0] ~ arr[0][m-1]
 * 1 1 0 0 0 0 1 1
 * 1 1 0 0 0 0 0 0
 * 1 1 0 0 0 1 1 0
 * 0 0 0 0 0 1 1 0
 * 0 0 0 0 0 0 0 0
 * 1 1 1 1 1 1 1 1  -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ! 출력
 * 모든 섬을 연결하는 다리 길이의 최솟값을 출력한다. 모든 섬을 연결하는 것이 불가능하면 -1을 출력한다.
 *
 * 9
 *
 * ! 제한
 * 1 ≤ N, M ≤ 10
 * 3 ≤ N×M ≤ 100
 * 2 ≤ 섬의 개수 ≤ 6
 *
 * ! 초기 설정
 * dx, dy: 상하좌우
 * count: 섬의 개수
 * parent: 크루스칼 알고리즘 사용시 UNION_FIND 기법을 사용하기 위한
 * * parent = [0, 1, 2, 3, ... n-1]
 * islands: 연결된 섬마다 위치정보를 따로 모아서 저장
 * islands -> [섬1, 섬2, 섬3, ...]
 * islands.get(k) -> 섬1: [{x1 ,y1}, {x2, y2}, ...], 섬2: [{x3, y3}, ...]
 * dists: X섬과 Y섬을 잇는 최단거리의 다리(모든 간선) -> {x, y, distance}
 */
package Gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G1_17472 {
    // 1. 모든 섬과 섬 사이의 다리를 연결하는 최솟값을 구한다.
    // 2. 최소스패닝 트리(프림 or 크루스칼)로 해답을 구한다.

    // 초기 설정
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int n;
    static int m;
    static int[][] arr;
    static ArrayList<ArrayList<int[]>> islands;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int count = -1;
    static ArrayList<int[]> dists;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        // 파싱
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 섬 정보를 2차원 배열인 arr에 저장
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 정보를 이용해 같은 섬끼리 묶어서 리스트를 만듦.(islands의 값을 채움)
        getAllIslands();

        // 섬에 번호를 붙여서 UNION_FIND 실행
        // 섬의 길이만큼 parent를 채움 [0, 1, 2, 3, ... n-1]
        parent = new int[islands.size()];
        int parentCount = 0;
        for (int i = 0; i < parent.length; i++) {
            parent[i] = parentCount++;
        }

        // 모든 섬 사이의 거리 구하기(브루트포스)
        dists = new ArrayList();
        for (int i = 0; i < islands.size() - 1; i++) {
            for (int j = i+1; j < islands.size(); j++) {
                int[] minDistanceFromIslandtoIsland = getMinimumDistance(i, j);
                // 섬사이의 거리가 2이상인(다리의 길이는 2이상이어야 하는 조건)
                if (minDistanceFromIslandtoIsland[2] > 1) {
                    dists.add(minDistanceFromIslandtoIsland);
                }
            }
        }

        // 크루스칼 알고리즘: 최소스패닝트리가 이루어지지 않으면 -1을 리턴, 이루어지면 총 비용을 리턴
        int result = kruskal();
        System.out.println(result);
    }

    // DFS를 이용해서 섬의정보(arr)를 돌아서 같은 섬끼리 위치정보를 따로 모아 저장하는 함수
    public static ArrayList<ArrayList<int[]>> getAllIslands() {
        islands = new ArrayList();
        visited = new boolean[n][m];
        for (boolean[] v: visited) {
            Arrays.fill(v, false);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    ArrayList<int[]> island = new ArrayList();
                    island.add(new int[] {i, j});
                    islands.add(island);
                    count++;
                    dfs(i, j);
                }
            }
        }
        return islands;
    }

    // DFS함수
    public static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny] && arr[nx][ny] == 1) {
                visited[nx][ny] = true;
                islands.get(count).add(new int[] {nx, ny});
                dfs(nx, ny);
            }
        }
    }

    // x번째 섬과 y번째 섬 사이를 잇는 도로의 최소 거리를 구하는 함수
    public static int[] getMinimumDistance(int x, int y) {
        ArrayList<int[]> is1 = islands.get(x); // 1번재 썸 정보
        ArrayList<int[]> is2 = islands.get(y); // 2번째 섬 정보

        int minDistance = Integer.MAX_VALUE; // MAX값
        for (int i = 0; i < is1.size(); i++) { // 1번째 섬의 모든 좌표에 대하여
            for (int j = 0; j < is2.size(); j++) { // 2번째 섬의 모든 좌표에 대하여
                int ix1 = is1.get(i)[0]; // 1번째 섬의 x좌표
                int iy1 = is1.get(i)[1]; // 1번째 섬의 y좌표

                int ix2 = is2.get(j)[0]; // 2번째 섬의 x좌표
                int iy2 = is2.get(j)[1]; // 2번째 섬의 y좌표

                // 둘중 하나라도 일치하는 값이 없다면 가로 혹은 세로로 연결할 수 없기 때문에 패스
                if (ix1 != ix2 && iy1 != iy2) continue;
                // 가로 혹은 세로가 같은 두좌표를 이을 때 중간에 섬이 있는 경우는 연결할 수 없기 때문에 패스
                if (!canConnect(ix1, iy1, ix2, iy2)) continue;
                // 연결할 수 있는 경우 x좌표의 차 + y좌표의 차 -1의 값을 저장
                int distance = Math.abs(ix1 - ix2) + Math.abs(iy1 - iy2) - 1;
                // 도로의 길이가 2 이상이고 최솟값일 때 업데이트
                if (distance < minDistance && distance >= 2) {
                    minDistance = distance;
                }
            }
        }
        // 도로를 하나도 지을 수 없을 때(minDistance값이 변경된 적이 없을 때) 최소거리에 -1을 저장
        if (minDistance == Integer.MAX_VALUE) minDistance = -1;
        // {x번째섬, y번째섬, 최소거리} 리턴
        return new int[] {x, y, minDistance};
    }

    // (ix1, iy1) ~ (ix2, iy2) 까지 잇는 도로를 건설하는 사이에 섬이 있는 경우
    public static boolean canConnect(int ix1, int iy1, int ix2, int iy2) {
        if (ix1 == ix2) { // 가로로 잇는 도로
            int from = iy1 > iy2 ? iy2 : iy1;
            int to = iy1 > iy2 ? iy1 : iy2;

            for (int i = from+1; i <= to-1; i++) {
                // 도중에 섬이 있다면 false 리턴
                if (arr[ix1][i] == 1) return false;
            }
            return true;
        } else { // 세로로 잇는 도로
            int from = ix1 > ix2 ? ix2 : ix1;
            int to = ix1 > ix2 ? ix1: ix2;

            for (int i = from+1; i <= to-1; i++) {
                // 도중에 섬이 있다면 false 리턴
                if (arr[i][iy1] == 1) return false;
            }
            return true;
        }
    }

    // 크루스칼
    public static int kruskal() {
        int count = 0;
        int totalDistance = 0;
        dists.sort((a, b) -> a[2] - b[2]);
        for (int i = 0; i < dists.size(); i++) {
            int x = dists.get(i)[0];
            int y = dists.get(i)[1];
            int dist = dists.get(i)[2];

            if (findParent(x) != findParent(y)) {
                count++;
                totalDistance += dist;
                union(x, y);
            }
        }
        return count  == islands.size() - 1 ? totalDistance : -1;
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
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}
/**
 * 채점 결과
 * 메모리: 18392KB
 * 시간: 228ms
 * 언어: JAVA
 */