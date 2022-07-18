/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/16946
 *
 * ! 제목: 벽 부수고 이동하기 4
 * ! 시간 제한: 2초
 * ! 메모리 제한: 512MB
 *
 * ! 문제
 * N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 한 칸에서 다른 칸으로 이동하려면, 두 칸이 인접해야 한다. 두 칸이 변을 공유할 때, 인접하다고 한다.
 * 각각의 벽에 대해서 다음을 구해보려고 한다.
 * 벽을 부수고 이동할 수 있는 곳으로 변경한다.
 * 그 위치에서 이동할 수 있는 칸의 개수를 세어본다.
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다.
 *
 * 3 3  -> n m
 * 101  -> arr[0][0] ~ arr[0][m-1]
 * 010
 * 101  -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ! 출력
 * 맵의 형태로 정답을 출력한다. 원래 빈 칸인 곳은 0을 출력하고, 벽인 곳은 이동할 수 있는 칸의 개수를 10으로 나눈 나머지를 출력한다.
 *
 * 303
 * 050
 * 303
 *
 * ? 채점 결과
 * 시간: 724ms
 * 메모리: 153400KB
 * 언어: JAVA8
 */
package Gold.IV;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G2_16946 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // 입력 파싱
    static int n, m;
    static char[][] arr;
    
    // 초기 설정
    static int[][] answer; // 정답배열
    static int[][] visited; // 방문리스트겸 인접 그룹 배열
    static int[] dx = {1, -1, 0, 0}; // 상하
    static int[] dy = {0, 0, 1, -1}; // 좌우
    static int count = 1; // 그룹번호
    static ArrayList<int[]> list; // 같은 그룹의 좌표들을 모은 리스트


    public static void main(String[] args) throws IOException {
        input(); // 입력 받기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '0' && visited[i][j] == 0) { // 0인 곳이면서 방문안한 곳에 대해
                    visited[i][j] = count; // 방문리스트에 현재 그룹번호 저장
                    list = new ArrayList<>(); // 그룹리스트 초기화
                    list.add(new int[] {i, j}); // 그룹리스트에 좌표 삽입
                    dfs(i, j); // DFS 시작

                    // 같은 그룹에 속한 좌표에 리스트의 크기(좌표들의 갯수) 저장 => 이동할 수 있는 총 갯수
                    for (int[] l : list) {
                        answer[l[0]][l[1]] = list.size();
                    }
                    count++; // DFS가 끝나고나면 그룹 번호 1 증가
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (answer[i][j] == 0) { // 벽이었던 곳
                    answer[i][j] = 1; // 자기 자신을 이동할 수 있게 만들어서 +1
                    ArrayList<Integer> r = new ArrayList<>(); // 이동 가능한 그룹 번호를 갖고 있는 리스트
                    // 4가지 방향에 대해서
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        // 범위체크 & 이동할 수 있는 곳인지 체크 & 이미 방문한 그룹이 아닌 곳 체크
                        if (0 <= ni && ni < n && 0 <= nj && nj < m && arr[ni][nj] == '0' && !r.contains(visited[ni][nj])) {
                            r.add(visited[ni][nj]); // 이동 가능한 그룹 리스트에 해당 그룹 번호 추가
                            answer[i][j] = (answer[i][j] % 10 + answer[ni][nj] % 10) % 10; // 10으로 나눈 나머지를 저장
                        }

                    }
                }
            }
        }

        // 원래 0이었던 곳은 다시 0으로 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '0') answer[i][j] = 0;
            }
        }

        // 정답 출력
        for (int[] a: answer) {
            for (int b: a) {
                bw.write(String.valueOf(b));
            }
            bw.write("\n");
        }

        bw.flush();
    }

    // DFS
    public static void dfs(int x, int y) {
        // 상하좌우
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크 & 방문 체크 & 이동할 수 있는지 체크
            if (0 <= nx && nx < n && 0 <= ny && ny < m && visited[nx][ny] == 0 && arr[nx][ny] == '0') {
                visited[nx][ny] = count; // 방문리스트에 현재 그룹 번호 저장
                list.add(new int[] {nx, ny}); // 같은 그룹에 속한 좌표들이 있는 리스트에 현재 위치 삽입
                dfs(nx, ny); // DFS
            }
        }
    }

    // 입력값 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        answer = new int[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

    }
}
