/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/2234
 *
 * ! 문제(그림은 링크 참조)
 * 대략 위의 그림과 같이 생긴 성곽이 있다. 굵은 선은 벽을 나타내고, 점선은 벽이 없어서 지나다닐 수 있는 통로를 나타낸다. 이러한 형태의 성의 지도를 입력받아서 다음을 계산하는 프로그램을 작성하시오.
 *
 * 이 성에 있는 방의 개수
 * 가장 넓은 방의 넓이
 * 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
 *
 * 위의 예에서는 방은 5개고, 가장 큰 방은 9개의 칸으로 이루어져 있으며, 위의 그림에서 화살표가 가리키는 벽을 제거하면 16인 크기의 방을 얻을 수 있다.
 * 성은 M × N(1 ≤ M, N ≤ 50)개의 정사각형 칸으로 이루어진다. 성에는 최소 두 개의 방이 있어서, 항상 하나의 벽을 제거하여 두 방을 합치는 경우가 있다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 두 정수 N, M이 주어진다. 다음 M개의 줄에는 N개의 정수로 벽에 대한 정보가 주어진다. 벽에 대한 정보는 한 정수로 주어지는데, 서쪽에 벽이 있을 때는 1을, 북쪽에 벽이 있을 때는 2를, 동쪽에 벽이 있을 때는 4를, 남쪽에 벽이 있을 때는 8을 더한 값이 주어진다. 참고로 이진수의 각 비트를 생각하면 쉽다. 따라서 이 값은 0부터 15까지의 범위 안에 있다.
 *
 * 7 4                  -> m n
 * 11 6 11 6 3 10 6     -> arr[0][0] ~ arr[0][m-1]
 * 7 9 6 13 5 15 5
 * 1 10 12 7 13 7 5
 * 13 11 10 8 10 12 13  -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ! 출력
 * 첫째 줄에 1의 답을, 둘째 줄에 2의 답을, 셋째 줄에 3의 답을 출력한다.
 *
 * 5
 * 9
 * 16
 *
 * ? 채점 결과
 * 메모리: 15124
 * 시간: 196ms
 * 언어: JAVA
 */
package Gold.IV;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_2234 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;
    static int count = 0;
    static int n, m;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] arr;
    static int[][] visited;
    static ArrayList<ArrayList<int[]>> rooms = new ArrayList<>();

    /*
           북쪽(2)
    서쪽(1)       동쪽(4)
           남쪽(8)
     */
    public static void main(String[] args) throws IOException {
        input(); // 입력 받기
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == -1) {
                    visited[i][j] = count;
                    int[] start = {i, j};
                    ArrayList<int[]> list = new ArrayList<>();
                    list.add(start);
                    rooms.add(list);
                    dfs(i, j);
                    count++;
                }
            }
        }

        bw.write(count + "\n");
        bw.write(findMaxRoom() + "\n");
        bw.write(String.valueOf(findMaxAdjacentRoom()));
        bw.flush();
    }

    // DFS: 인접한 방에 같은 번호를 방문리스트에 저장하는 함수
    public static void dfs(int x, int y) {
        int num = arr[x][y];
        // 2진법 계산으로 접근
        for (int i = 3; i >= 0; i--) {
            if ((1 << i) <= num) {
                num -= (1 << i);
            } else {
                int nx = x + dx[3-i];
                int ny = y + dy[3-i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (visited[nx][ny] == -1) {
                    visited[nx][ny] = count;
                    rooms.get(count).add(new int[] {nx, ny});
                    dfs(nx, ny);
                }
            }
        }
    }

    // 입력값 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new int[n][m];
        for (int[] _v: visited) {
            Arrays.fill(_v, -1);
        }
    }

    // 가장 넓은 방의 넓이
    public static int findMaxRoom() {
        int max = 0;
        for (ArrayList<int[]> room: rooms) {
            if (room.size() > max) {
                max = room.size();
            }
        }
        return max;
    }

    // r1번 방과 r2번 방이 인접 하는지 안하는지 판단
    public static boolean isAdjacent(int r1, int r2) {
        for (int i = 0; i < rooms.get(r1).size(); i++) {
            for (int j = 0; j < rooms.get(r2).size(); j++) {
                int[] adj1 = rooms.get(r1).get(i);
                int[] adj2 = rooms.get(r2).get(j);
                if (Math.abs(adj1[0] - adj2[0]) == 1 && Math.abs(adj1[1] - adj2[1]) == 0) return true;
                if (Math.abs(adj1[0] - adj2[0]) == 0 && Math.abs(adj1[1] - adj2[1]) == 1) return true;
            }
        }
        return false;
    }

    // 벽을 하나 제거했을 때 얻을 수 있는 가장 넓은 방의 크기
    public static int findMaxAdjacentRoom() {
        int max = 0;
        for (int i = 0; i < rooms.size()-1; i++) {
            for (int j = i+1; j < rooms.size(); j++) {
                // 인접한 방에 대해서만 두 방의 크기를 합한 값과 최댓값을 비교
                if (isAdjacent(i, j)) {
                    if (max < rooms.get(i).size() + rooms.get(j).size()) {
                        max = rooms.get(i).size() + rooms.get(j).size();
                    }
                }
            }
        }
        return max;
    }
}
