/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/20058
 *
 * ? 제목: 마법사 상어와 파이어스톰
 * ? 시간 제한: 1초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 마법사 상어는 파이어볼과 토네이도를 조합해 파이어스톰을 시전할 수 있다. 오늘은 파이어스톰을 크기가 2N × 2N인 격자로 나누어진 얼음판에서 연습하려고 한다. 위치 (r, c)는 격자의 r행 c열을 의미하고, A[r][c]는 (r, c)에 있는 얼음의 양을 의미한다. A[r][c]가 0인 경우 얼음이 없는 것이다.
 * 파이어스톰을 시전하려면 시전할 때마다 단계 L을 결정해야 한다. 파이어스톰은 먼저 격자를 2L × 2L 크기의 부분 격자로 나눈다. 그 후, 모든 부분 격자를 시계 방향으로 90도 회전시킨다. 이후 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다. (r, c)와 인접한 칸은 (r-1, c), (r+1, c), (r, c-1), (r, c+1)이다. 아래 그림의 칸에 적힌 정수는 칸을 구분하기 위해 적은 정수이다.
 * 마법을 시전하기 전	L = 1	L = 2
 * 마법사 상어는 파이어스톰을 총 Q번 시전하려고 한다. 모든 파이어스톰을 시전한 후, 다음 2가지를 구해보자.
 * 남아있는 얼음 A[r][c]의 합
 * 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
 * 얼음이 있는 칸이 얼음이 있는 칸과 인접해 있으면, 두 칸을 연결되어 있다고 한다. 덩어리는 연결된 칸의 집합이다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 N과 Q가 주어진다. 둘째 줄부터 2N개의 줄에는 격자의 각 칸에 있는 얼음의 양이 주어진다. r번째 줄에서 c번째 주어지는 정수는 A[r][c] 이다.
 * 마지막 줄에는 마법사 상어가 시전한 단계 L1, L2, ..., LQ가 순서대로 주어진다.
 *
 * 3 1              -> n q
 * 1 2 3 4 5 6 7 8  -> arr[0][0] ~ arr[0][2^n - 1]
 * 8 7 6 5 4 3 2 1
 * 1 2 3 4 5 6 7 8
 * 8 7 6 5 4 3 2 1
 * 1 2 3 4 5 6 7 8
 * 8 7 6 5 4 3 2 1
 * 1 2 3 4 5 6 7 8
 * 8 7 6 5 4 3 2 1  -> arr[2^n - 1][0] ~ arr[2^n - 1][2^n - 1]
 * 1                -> fire[0] ~ fire[q-1]
 *
 * ? 출력
 * 첫째 줄에 남아있는 얼음 A[r][c]의 합을 출력하고, 둘째 줄에 가장 큰 덩어리가 차지하는 칸의 개수를 출력한다. 단, 덩어리가 없으면 0을 출력한다.\
 *
 * 284
 * 64
 *
 * ? 제한
 * 2 ≤ N ≤ 6
 * 1 ≤ Q ≤ 1,000
 * 0 ≤ A[r][c] ≤ 100
 * 0 ≤ Li ≤ N
 *
 * ? 채점 결과
 * * 시간: 0.316초
 * * 메모리: 54MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.IV;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class G4_20058 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, q, len;
    static int[][] arr;
    static int[] fire;

    // * 4방
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // * 초기 설정
    static boolean[][] visited;
    static ArrayList<Integer> mass = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        for (int l : fire) { // * 2. 파이어스톰 시도마다 90도 회전 후 얼음 녹이기
            arr = clockwiseRotate((int) Math.pow(2, l)); // * 기준격자마다 회전
            melt(); // * 얼음 녹이기
        }
        
        int sum = findSum(); // * 3. 남아있는 얼음 개수의 합
        int max = findMax(); // * 4. 남아있는 얼음 덩어리의 개수의 최댓값

        // * 5. 출력
        bw.write(sum + "\n");
        bw.write(max + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        len = (int) Math.pow(2, n);

        arr = new int[len][len];
        fire = new int[q];

        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < len; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            fire[i] = Integer.parseInt(st.nextToken());
        }
    }

    // 기준격자길이(l)를 기준으로 시계방향 90도 회전
    public static int[][] clockwiseRotate(int l) {
        int[][] newArr = new int[len][len];

        for (int x = 0; x < len; x += l) {
            for (int y = 0; y < len; y += l) {
                for (int i = x; i < x + l; i++) {
                    for (int j = y; j < y + l; j++) {
                        newArr[j-y+x][l-1-i+x+y] = arr[i][j];
                    }
                }
            }
        }

        return newArr;
    }

    // 인접한 4방의 얼음이 있는 경우가 3개 미만일 때 얼음녹이기
    public static void melt() {
        ArrayList<int[]> list = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if (ni < 0 || nj < 0 || ni >= len || nj >= len) {
                        continue;
                    }

                    if (arr[ni][nj] > 0) {
                        count++;
                    }
                }

                if (count < 3) {
                    list.add(new int[] {i, j});
                }
            }
        }

        for (int[] m : list) {
            if (arr[m[0]][m[1]] > 0) {
                arr[m[0]][m[1]]--;
            }
        }
    }

    // 남아있는 얼음 개수 합 구하기
    public static int findSum() {
        int sum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }

    // 남아있는 얼음 덩어리의 개수 최댓값 구하기 DFS
    public static int findMax() {
        visited = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (arr[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    mass.add(1);
                    dfs(i, j);
                }
            }
        }

        return mass.size() != 0 ? Collections.max(mass) : 0;
    }

    // DFS
    public static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= len || ny >= len) {
                continue;
            }

            if (!visited[nx][ny] && arr[nx][ny] > 0) {
                mass.set(mass.size()-1, mass.get(mass.size()-1)+1);
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }
}
