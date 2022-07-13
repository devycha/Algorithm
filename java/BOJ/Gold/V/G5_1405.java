/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/1405
 *
 * ! 제목: 미친 로봇
 * ! 시간 제한: 1초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 통제 할 수 없는 미친 로봇이 평면위에 있다. 그리고 이 로봇은 N번의 행동을 취할 것이다.
 * 각 행동에서 로봇은 4개의 방향 중에 하나를 임의로 선택한다. 그리고 그 방향으로 한 칸 이동한다.
 * 로봇이 같은 곳을 한 번보다 많이 이동하지 않을 때, 로봇의 이동 경로가 단순하다고 한다. (로봇이 시작하는 위치가 처음 방문한 곳이다.) 로봇의 이동 경로가 단순할 확률을 구하는 프로그램을 작성하시오. 예를 들어, EENE와 ENW는 단순하지만, ENWS와 WWWWSNE는 단순하지 않다. (E는 동, W는 서, N은 북, S는 남)
 *
 * ! 입력 & 파싱
 * 첫째 줄에 N, 동쪽으로 이동할 확률, 서쪽으로 이동할 확률, 남쪽으로 이동할 확률, 북쪽으로 이동할 확률이 주어진다. N은 14보다 작거나 같은 자연수이고,  모든 확률은 100보다 작거나 같은 자연수 또는 0이다. 그리고, 동서남북으로 이동할 확률을 모두 더하면 100이다.
 * 확률의 단위는 %이다.
 *
 * 14 50 50 0 0 -> n dirProb[0] dirProb[1] dirProb[2] dirProb[3]
 *
 * ! 출력
 * 첫째 줄에 로봇의 이동 경로가 단순할 확률을 출력한다. 절대/상대 오차는 10-9 까지 허용한다.
 *
 * ? 채점 결과
 * 메모리: 12584KB
 * 시간: 160ms
 * 언어: JAVA
 */
package Gold.V;

import java.io.*;
import java.util.StringTokenizer;

public class G5_1405 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n; // 로봇의 이동 횟수
    static double[] dirProb = new double[4]; // 동서남북 각각으로 이동할 확률

    // 동 서 남 북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    // 방문 리스트
    static boolean[][] visited = new boolean[29][29];
    // 로봇이 단순하게 이동할 확률
    static double simpleProb;

    // 메인함수
    public static void main(String[] args) throws IOException {
        input(); // 입력값 받기
        visited[14][14] = true; // 시작위치 방문 표시
        dfs(14, 14, 1, 0); // 시작위치에서 DFS 수행
        bw.write(String.valueOf(simpleProb)); // 정답 출력
        bw.flush();
    }

    public static void dfs(int x, int y, double prob, int count) {
        if (count == n) { // n번 이동을 방문한 곳 없이 완료했을 경우
            simpleProb += prob; // 현재까지의 확률을 더하기
            return; // 종료
        }

        // 동서남북에 대하여
        for (int i = 0; i < 4; i++) {
            if (dirProb[i] != 0) { // 확률이 0이 아닌 경우에만
                int nx = x + dx[i];
                int ny = y + dy[i];
                double nProb = prob * dirProb[i]; // 현재 확률에다가 다음으로 이동할 수 있을 확률을 곱하기

                // 범위 체크와 이미 방문했는지 체크
                if (0 <= nx && nx < 29 && 0 <= ny && ny < 29 && !visited[nx][ny]) {
                    visited[nx][ny] = true; // 방문 체크
                    dfs(nx, ny, nProb, count+1);
                    visited[nx][ny] = false; // 방문 체크 해제
                }
            }
        }
    }

    // 입력값 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            dirProb[i] = Double.parseDouble(st.nextToken()) / 100;
        }
    }
}
