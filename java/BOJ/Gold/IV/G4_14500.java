/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/14500
 *
 * ! 제목: 테트로미노
 * ! 시간 제한: 2초
 * ! 메모리 제한: 512MB
 *
 * ! 문제
 * 폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.
 * 정사각형은 서로 겹치면 안 된다.
 * 도형은 모두 연결되어 있어야 한다.
 * 정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.
 * 정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.
 * 아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 쓰여 있다.
 * 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.
 * 테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)
 * 둘째 줄부터 N개의 줄에 종이에 쓰여 있는 수가 주어진다. i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 쓰여 있는 수이다. 입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.
 *
 * 5 5          -> n m
 * 1 2 3 4 5    -> arr[0][0] ~ arr[0][m-1]
 * 5 4 3 2 1
 * 2 3 4 5 6
 * 6 5 4 3 2
 * 1 2 1 2 1    -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ! 출력
 * 첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.
 *
 * 19
 *
 * ? 채점 결과
 * 시간: 616ms
 * 메모리: 31684KB
 * 언어: JAVA8
 */
package Gold.IV;

import java.io.*;
import java.util.StringTokenizer;

public class G4_14500 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int n, m;
    static int[][] arr;

    // 초기 설정
    static int max = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;

    // * 메인
    public static void main(String[] args) throws IOException {
        input(); // * 입력받기
        
        // * 모든 위치에서 DFS수행
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // * DFS 수행 후 방문 표시 해제
                visited[i][j] = true;
                dfs(i, j, 1, arr[i][j]);
                visited[i][j] = false;

                // * DFS로 구할 수 없는 경우 계산
                // * 'ㅗ' 'ㅓ' 'ㅏ' 'ㅜ' 경우 중 최대값만 생각하여 계산
                int count = 1;
                int min = 1000;
                int sum = arr[i][j];

                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if (0 <= ni && ni < n && 0 <= nj && nj < m) {
                        count++;
                        sum += arr[ni][nj];
                        min = Math.min(arr[ni][nj], min);
                    }
                }

                // * 위 네가지 경우가 모두 가능한 경우: 상하좌우값 중 최솟값을 빼서 4개블럭의 합의 최댓값만 취급하여 max값과 비교하여 업데이트
                if (count == 5) {
                    max = Math.max(sum - min, max);
                } else if (count == 4) {
                    // * 위 네가지 경우 중 한가지만 가능한 경우: max값과 비교하여 바로 업데이트
                    max = Math.max(sum, max);
                }
            }
        }

        bw.write(String.valueOf(max)); // * 출력

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! DFS
    public static void dfs(int x, int y, int count, int sum) {
        // * 4번의 이동이 끝났다면 종료
        if (count == 4) {
            max = Math.max(sum, max);
            return;
        }

        // * 상하좌우에 대하여 DFS 재귀 수행
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // * 범위체크 + 방문체크
            if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny]) {
                // * DFS가 완료되면 방문표시 해제
                visited[nx][ny] = true;
                dfs(nx, ny, count+1, sum + arr[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
