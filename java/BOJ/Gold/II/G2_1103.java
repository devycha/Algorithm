/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/1103
 *
 * ! 제목: 게임
 * ! 시간 제한: 2초
 * ! 메모리 제한: 512MB
 *
 * ! 문제
 * 형택이는 1부터 9까지의 숫자와, 구멍이 있는 직사각형 보드에서 재밌는 게임을 한다.
 * 일단 보드의 가장 왼쪽 위에 동전을 하나 올려놓는다. 그다음에 다음과 같이 동전을 움직인다.
 * 동전이 있는 곳에 쓰여 있는 숫자 X를 본다.
 * 위, 아래, 왼쪽, 오른쪽 방향 중에 한가지를 고른다.
 * 동전을 위에서 고른 방향으로 X만큼 움직인다. 이때, 중간에 있는 구멍은 무시한다.
 * 만약 동전이 구멍에 빠지거나, 보드의 바깥으로 나간다면 게임은 종료된다. 형택이는 이 재밌는 게임을 되도록이면 오래 하고 싶다.
 * 보드의 상태가 주어졌을 때, 형택이가 최대 몇 번 동전을 움직일 수 있는지 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 줄에 보드의 세로 크기 N과 가로 크기 M이 주어진다. 이 값은 모두 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 보드의 상태가 주어진다. 쓰여 있는 숫자는 1부터 9까지의 자연수 또는 H이다. 가장 왼쪽 위칸은 H가 아니다. H는 구멍이다.
 *
 * 3 7      -> n m
 * 3942178  -> board[0][0] ~ board[0][m-1]
 * 1234567
 * 9123532  -> board[n-1][0] ~ board[n-1][m-1]
 *
 * ! 출력
 * 첫째 줄에 문제의 정답을 출력한다. 만약 형택이가 동전을 무한번 움직일 수 있다면 -1을 출력한다.
 *
 * 5
 *
 * ? 채점 결과
 * 시간: 88ms
 * 메모리: 12492KB
 * 언어: JAVA8
 */
package Gold.II;

import java.io.*;
import java.util.StringTokenizer;

public class G2_1103 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int n, m;
    static char[][] board;

    // 초기 설정
    static int[] dx = {1, -1, 0, 0}; // 상하
    static int[] dy = {0, 0, 1, -1}; // 좌우

    static boolean[][] visited; // 방문리스트
    static int[][] dp; // DP를 이용한 게임가능횟수 저장 배열

    // * 메인
    public static void main(String[] args) throws IOException {
        input(); // 입력 받기
        
        // * 맨 왼쪽 위에서부터 시작
        visited[0][0] = true;
        dfs(0, 0);

        // * (0, 0) 에서의 게임 가능 횟수가 10000보다 크면 싸이클이 발생하므로 -1을 출력, 그 외에는 dp[0][0]출력
        bw.write(String.valueOf(dp[0][0] >= 10000 ? -1 : dp[0][0]));

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    public static int dfs(int x, int y) {
        // * 이미 게임 횟수가 정해진 곳에 방문했을 때
        if (dp[x][y] > 0) return dp[x][y];

        for (int i = 0; i < 4; i++) {
            // * 해당 위치의 숫자만큼 상하좌우로 이동할 때의 nx, ny
            int num = Integer.parseInt(String.valueOf(board[x][y]));
            int nx = x + num * dx[i];
            int ny = y + num * dy[i];

            // * 밤위를 벗어나거나 H(구멍)에 빠지게 될 경우 dp[x][y]의 값을 1과 비교해서 큰 값으로 설정
            if (nx < 0 || ny < 0 || nx >= n || ny >= m || board[nx][ny] == 'H') {
                dp[x][y] = Math.max(dp[x][y], 1);
            } else {
                // * 이미 방문한 곳을 방문하여 무한번 게임 실행이 가능해질 때
                if (visited[nx][ny]) {
                    dp[x][y] = 10000; // * dp[x][y]를 10000으로 설정하고 종료
                    break;
                } else {
                    visited[nx][ny] = true; // * 방문표시
                    dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1); // * 상하좌우로 이동한 곳의 게임 횟수+1 중 큰 값을 저장
                    visited[nx][ny] = false; // * 방문 해제
                }
            }
        }
        return dp[x][y]; // * 현재 자신 위치의 게임 가능 횟수를 리턴
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][m];
        board = new char[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }
    }
}
