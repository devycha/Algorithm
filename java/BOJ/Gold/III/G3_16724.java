/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16724
 *
 * ? 제목: 피리 부는 사나이
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 피리 부는 사나이 성우는 오늘도 피리를 분다.
 * 성우가 피리를 불 때면 영과일 회원들은 자기도 모르게 성우가 정해놓은 방향대로 움직이기 시작한다. 성우가 정해놓은 방향은 총 4가지로 U, D, L, R이고 각각 위, 아래, 왼쪽, 오른쪽으로 이동하게 한다.
 * 이를 지켜보던 재훈이는 더 이상 움직이기 힘들어하는 영과일 회원들을 지키기 위해 특정 지점에 ‘SAFE ZONE’ 이라는 최첨단 방음 시설을 만들어 회원들이 성우의 피리 소리를 듣지 못하게 하려고 한다. 하지만 예산이 넉넉하지 않은 재훈이는 성우가 설정해 놓은 방향을 분석해서 최소 개수의 ‘SAFE ZONE’을 만들려 한다.
 * 성우가 설정한 방향 지도가 주어졌을 때 재훈이를 도와서 영과일 회원들이 지도 어느 구역에 있더라도 성우가 피리를 불 때 ‘SAFE ZONE’에 들어갈 수 있게 하는 ‘SAFE ZONE’의 최소 개수를 출력하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫 번째 줄에 지도의 행의 수를 나타내는 N(1 ≤ N ≤ 1,000)과 지도의 열의 수를 나타내는 M(1 ≤ M ≤ 1,000)이 주어진다.
 * 두 번째 줄부터 N개의 줄에 지도의 정보를 나타내는 길이가 M인 문자열이 주어진다.
 * 지도 밖으로 나가는 방향의 입력은 주어지지 않는다.
 *
 * 3 4  -> n m
 * DLLL -> arr[0][0] ~ arr[0][m-1]
 * DRLU
 * RRRU -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * 첫 번째 줄에 ‘SAFE ZONE’의 최소 개수를 출력한다.
 *
 * 2
 *
 * ? 채점 결과
 * * 시간: 0.312초
 * * 메모리: 28MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Gold.III;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class G3_16724 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static char[][] arr;

    // * 초기 설정
    static int count = 0;
    static boolean[][] visited;
    static int[][] route;

    // * 4방(DULR)
    static HashMap<Character, int[]> dir = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. 방문하지 않은 위치에서 DFS수행
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j);
                }
            }
        }

        // * 3. 독립된 경로의 개수 출력
        System.out.println(count);
    }

    // DFS
    public static int dfs(int x, int y) {
        if (route[x][y] > 0) {
            return route[x][y];
        }

        int nx = x + dir.get(arr[x][y])[0];
        int ny = y + dir.get(arr[x][y])[1];

        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
            return ++count;
        }

        // 방문했던 곳일 때
        if (visited[nx][ny]) {
            // 현재 DFS 경로 상에서 싸이클이 발생한 경우
            if (route[nx][ny] == 0) {
                route[x][y] = ++count; // 독립된 경로의 개수 + 1
            } else { // 이미 찾았던 경로인 경우
                route[x][y] = route[nx][ny]; // 해당 경로에 포함 
            }
            return route[x][y];
        }

        visited[nx][ny] = true;
        route[x][y] = dfs(nx, ny);
        return route[x][y];
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        route = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        dir.put('D', new int[] {1, 0});
        dir.put('U', new int[] {-1, 0});
        dir.put('R', new int[] {0, 1});
        dir.put('L', new int[] {0, -1});
    }
}
