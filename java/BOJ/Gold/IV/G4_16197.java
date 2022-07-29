/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/16197
 *
 * ! 제목: 두 동전
 * ! 시간 제한: 2초
 * ! 메모리 제한: 512MB
 *
 * ! 문제
 * N×M 크기의 보드와 4개의 버튼으로 이루어진 게임이 있다. 보드는 1×1크기의 정사각형 칸으로 나누어져 있고, 각각의 칸은 비어있거나, 벽이다. 두 개의 빈 칸에는 동전이 하나씩 놓여져 있고, 두 동전의 위치는 다르다.
 * 버튼은 "왼쪽", "오른쪽", "위", "아래"와 같이 4가지가 있다. 버튼을 누르면 두 동전이 버튼에 쓰여 있는 방향으로 동시에 이동하게 된다.
 * 동전이 이동하려는 칸이 벽이면, 동전은 이동하지 않는다.
 * 동전이 이동하려는 방향에 칸이 없으면 동전은 보드 바깥으로 떨어진다.
 * 그 외의 경우에는 이동하려는 방향으로 한 칸 이동한다.이동하려는 칸에 동전이 있는 경우에도 한 칸 이동한다.
 * 두 동전 중 하나만 보드에서 떨어뜨리기 위해 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 보드의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 20)
 * 둘째 줄부터 N개의 줄에는 보드의 상태가 주어진다.
 *
 * o: 동전
 * .: 빈 칸
 * #: 벽
 * 동전의 개수는 항상 2개이다.
 *
 * 6 2  -> n m
 * .#   -> arr[0][0] ~ arr[0][m-1]
 * .#
 * .#
 * o#
 * o#
 * ##   -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ! 출력
 * 첫째 줄에 두 동전 중 하나만 보드에서 떨어뜨리기 위해 눌러야 하는 버튼의 최소 횟수를 출력한다. 만약, 두 동전을 떨어뜨릴 수 없거나, 버튼을 10번보다 많이 눌러야 한다면, -1을 출력한다.
 *
 * 4
 *
 * ? 채점 결과
 * 시간: 80ms
 * 메모리: 11868KB
 * 언어: JAVA8
 */
package Gold.IV;

import java.io.*;
import java.util.*;

public class G4_16197 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // 파싱
    private static int n, m;
    private static char[][] arr;
    
    // 초기 설정
    static int answer = -1; // 정답
    static int[] dx = {1, -1, 0, 0}; // 상하
    static int[] dy = {0, 0, 1, -1}; // 좌우
    static int[][] coin = new int[2][2]; // 초기 동전의 위치

    // * 메인
    public static void main(String[] args) throws IOException {

        // TODO: 총 4가지 단계 풀이
        input(); // * 입력 받기
        findCoin(); // * 초기 동전의 위치 찾기
        bfs(); // * BFS 수행
        bw.write(String.valueOf(answer)); // * 정답 출력

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! BFS
    public static void bfs() {
        LinkedList<int[]> queue = new LinkedList<>(); // * BFS용 queue
        boolean[][][][] visited = new boolean[n][m][n][m]; // * 20 x 20 x 20 x 20 4차원 방문표시 배열(16만의 boolean 가능)

        visited[coin[0][0]][coin[0][1]][coin[1][0]][coin[1][1]] = true;
        queue.add(new int[] {coin[0][0], coin[0][1], coin[1][0], coin[1][1], 0});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x1 = cur[0];
            int y1 = cur[1];
            int x2 = cur[2];
            int y2 = cur[3];
            int count = cur[4];

            if (count >= 10) continue; // * 10번을 넘게 이동한 경우 중지
            if (x1 == x2 && y1 == y2) continue; // * 동전이 겹치게 되는 경우 중지

            // * 상하좌우에 대하여
            for (int i = 0; i < 4; i++) {
                // * 동전 1의 다음 위치
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];

                // * 동전 2의 다음 위치
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];
                
                if (!valueCheck(nx1, ny1) && !valueCheck(nx2, ny2)) { // ! 모두 밖으로 떨어지면 중지
                    continue;
                } else if ((valueCheck(nx1, ny1) && !valueCheck(nx2 ,ny2)) || (!valueCheck(nx1, ny1) && valueCheck(nx2, ny2))) {
                    // ! 둘 중 하나만 떨어지면 정답에 count+1 저장 후 전체 종료
                    answer = count+1;
                    return;
                } else {
                    // ! 두 동전 모두 안떨어지고 있으면 큐에 넣어 계속 진행
                    if (arr[nx1][ny1] == '#') { // * 벽이 있다면 nx -> x, ny -> y 원래대로 복귀(이동하지 못함)
                        nx1 = x1;
                        ny1 = y1;
                    }

                    if (arr[nx2][ny2] == '#') { // * 벽이 있다면 nx -> x, ny -> y 원래대로 복귀(이동하지 못함)
                        nx2 = x2;
                        ny2 = y2;
                    }

                    // * 이미 방문하지 않은 위치에 대해서
                    if (!visited[nx1][ny1][nx2][ny2]) {
                        visited[nx1][ny1][nx2][ny2] = true; // * 방문 표시
                        queue.add(new int[] {nx1 ,ny1, nx2, ny2, count+1}); // * 큐에 삽입
                    }
                }

            }
        }
    }

    // * 보드의 크기 안에 좌표가 들어가있는지 검사하는 함수
    public static boolean valueCheck(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
    
    // * 초기 동전의 위치를 찾는 함수 
    public static void findCoin() {
        byte count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'o') {
                    coin[count++] = new int[] {i, j};
                    arr[i][j] = '.';
                }

                if (count >= 3) break;
            }
        }
    }

    // * 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
