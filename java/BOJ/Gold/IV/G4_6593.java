package Gold.IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 문제 출처: 백준 온라인 져지
 * https://www.acmicpc.net/problem/6593
 *
 * 시간제한: 1초
 * 메모리제한: 128MB
 *
 * 문제
 * 당신은 상범 빌딩에 갇히고 말았다. 여기서 탈출하는 가장 빠른 길은 무엇일까? 상범 빌딩은 각 변의 길이가 1인 정육면체(단위 정육면체)로 이루어져있다. 각 정육면체는 금으로 이루어져 있어 지나갈 수 없거나, 비어있어서 지나갈 수 있게 되어있다. 당신은 각 칸에서 인접한 6개의 칸(동,서,남,북,상,하)으로 1분의 시간을 들여 이동할 수 있다. 즉, 대각선으로 이동하는 것은 불가능하다. 그리고 상범 빌딩의 바깥면도 모두 금으로 막혀있어 출구를 통해서만 탈출할 수 있다.
 * 당신은 상범 빌딩을 탈출할 수 있을까? 만약 그렇다면 얼마나 걸릴까?
 *
 * 입력 & 파싱
 * 입력은 여러 개의 테스트 케이스로 이루어지며, 각 테스트 케이스는 세 개의 정수 L, R, C로 시작한다. L(1 ≤ L ≤ 30)은 상범 빌딩의 층 수이다. R(1 ≤ R ≤ 30)과 C(1 ≤ C ≤ 30)는 상범 빌딩의 한 층의 행과 열의 개수를 나타낸다.
 * 그 다음 각 줄이 C개의 문자로 이루어진 R개의 행이 L번 주어진다. 각 문자는 상범 빌딩의 한 칸을 나타낸다. 금으로 막혀있어 지나갈 수 없는 칸은 '#'으로 표현되고, 비어있는 칸은 '.'으로 표현된다. 당신의 시작 지점은 'S'로 표현되고, 탈출할 수 있는 출구는 'E'로 표현된다. 각 층 사이에는 빈 줄이 있으며, 입력의 끝은 L, R, C가 모두 0으로 표현된다. 시작 지점과 출구는 항상 하나만 있다.
 *
     3 4 5 -> l r c
     S.... -> arr[0][0][0] ~
     .###.
     .##..
     ###.#

     #####
     #####
     ##.##
     ##...

     #####
     #####
     #.###
     ####E  -> ~ arr[l-1][r-1]c-1];

     1 3 3  -> l r c
     S##    -> arr[0][0][0] ~
     #E#
     ###    -> ~ arr[l-1][r-1][c-1]

     0 0 0
 *
 * 출력
 * 각 빌딩에 대해 한 줄씩 답을 출력한다. 만약 당신이 탈출할 수 있다면 다음과 같이 출력한다.
 *
 * Escaped in x minute(s).
 * 여기서 x는 상범 빌딩을 탈출하는 데에 필요한 최단 시간이다.
 *
 * 만일 탈출이 불가능하다면, 다음과 같이 출력한다.
 *
 * Trapped!
 *
 * 초기 설정
 * visited: 3차원 방문 리스트
 * findS: 시작 위치 배열을 리턴하는 함수
 * escape: 탈출 함수(탈출했다면 횟수 리턴, 탈출을 못했다면 -1을 리턴)
 */
public class G4_6593 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        while ((st = new StringTokenizer(br.readLine())) != null) {
            // 파싱
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 입력의 끝 처리
            if (l == 0 && r == 0 && c == 0) return;
            
            // 
            char[][][] arr = new char[l][r][c];
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    arr[i][j] = br.readLine().toCharArray();
                }
                br.readLine();
            }

            // 초기 설정
            int[][][] visited = new int[l][r][c];
            for (int[][] v: visited) {
                for (int[] vv: v) {
                    Arrays.fill(vv, -1);
                }
            }

            // escape BFS 실행
            int result = escape(arr, visited, findS(arr));
            // -1(탈출불가능)일때와 아닐때를 나눠서 출력
            System.out.println(result == -1 ? "Trapped!" : "Escaped in " + result + " minute(s).");
        }
    }

    public static int escape(char[][][] arr, int[][][] visited, int[] start) {
        LinkedList<int[]> q = new LinkedList();
        visited[start[0]][start[1]][start[2]] = 0;
        q.add(start);
        
        // 동서남북상하(6가지 방향)
        int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        // BFS
        while (!q.isEmpty()) {
            int[] currentInfo = q.poll();
            int x = currentInfo[0];
            int y = currentInfo[1];
            int z = currentInfo[2];

            if (arr[x][y][z] == 'E') {
                return visited[x][y][z];
            }

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];
                // 범위체크 및 방문체크 및 "." or "E" 체크
                if (0 <= nx && 0 <= ny && 0 <= nz && nx < arr.length && ny < arr[0].length && nz < arr[0][0].length && visited[nx][ny][nz] == -1 && (arr[nx][ny][nz] == '.' || arr[nx][ny][nz] == 'E')) {
                    visited[nx][ny][nz] = visited[x][y][z] + 1; // 방문 표시
                    q.add(new int[] {nx, ny, nz}); // 큐에 삽입
                }
            }
        }
        return -1;
    }

    // 시작점("S")의 위치를 찾아 배열로 리턴하는 함수
    public static int[] findS(char[][][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr[0][0].length; k++) {
                    if (arr[i][j][k] == 'S') return new int[] {i, j, k};
                }
            }
        }
        return null;
    }
}
/**
 * 채점 결과
 * 메모리: 16308KB
 * 시간: 128ms
 * 언어: JAVA
 */
