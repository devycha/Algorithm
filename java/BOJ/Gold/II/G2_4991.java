/**
 * ? 문제 출처: 백준 온라인 져지
 * ? https://www.acmicpc.net/problem/4991
 *
 * ? 제목: 로봇 청소기
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 오늘은 직사각형 모양의 방을 로봇 청소기를 이용해 청소하려고 한다. 이 로봇 청소기는 유저가 직접 경로를 설정할 수 있다.
 * 방은 크기가 1×1인 정사각형 칸으로 나누어져 있으며, 로봇 청소기의 크기도 1×1이다. 칸은 깨끗한 칸과 더러운 칸으로 나누어져 있으며, 로봇 청소기는 더러운 칸을 방문해서 깨끗한 칸으로 바꿀 수 있다.
 * 일부 칸에는 가구가 놓여져 있고, 가구의 크기도 1×1이다. 로봇 청소기는 가구가 놓여진 칸으로 이동할 수 없다.
 * 로봇은 한 번 움직일 때, 인접한 칸으로 이동할 수 있다. 또, 로봇은 같은 칸을 여러 번 방문할 수 있다.
 * 방의 정보가 주어졌을 때, 더러운 칸을 모두 깨끗한 칸으로 만드는데 필요한 이동 횟수의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 입력은 여러 개의 테스트케이스로 이루어져 있다.
 * 각 테스트 케이스의 첫째 줄에는 방의 가로 크기 w와 세로 크기 h가 주어진다. (1 ≤ w, h ≤ 20) 둘째 줄부터 h개의 줄에는 방의 정보가 주어진다. 방의 정보는 4가지 문자로만 이루어져 있으며, 각 문자의 의미는 다음과 같다.
 *
 * [ .: 깨끗한 칸 ]
 * [ *: 더러운 칸 ]
 * [ x: 가구 ]
 * [ o: 로봇 청소기의 시작 위치 ]
 * 더러운 칸의 개수는 10개를 넘지 않으며, 로봇 청소기의 개수는 항상 하나이다.
 * 입력의 마지막 줄에는 0이 두 개 주어진다.
 *
 * 7 5      -> m n
 * .......  -> arr[0][0] ~ arr[0][m-1]
 * .o...*.
 * .......
 * .*...*.
 * .......  -> arr[n-1][0] ~ arr[n-1][m-1]
 * 15 13            -> m n
 * .......x.......  -> arr[0][0] ~ arr[0][m-1]
 * ...o...x....*..
 * .......x.......
 * .......x.......
 * .......x.......
 * ...............
 * xxxxx.....xxxxx
 * ...............
 * .......x.......
 * .......x.......
 * .......x.......
 * ..*....x....*..
 * .......x.......  -> arr[n-1][0] ~ arr[n-1][m-1]
 * 10 10        -> m n
 * ..........   -> arr[0][0] ~ arr[0][m-1]
 * ..o.......
 * ..........
 * ..........
 * ..........
 * .....xxxxx
 * .....x....
 * .....x.*..
 * .....x....
 * .....x....   -> arr[n-1][0] ~ arr[n-1][m-1]
 * 0 0          m n
 *
 * ? 출력
 * 각각의 테스트 케이스마다 더러운 칸을 모두 깨끗한 칸으로 바꾸는 이동 횟수의 최솟값을 한 줄에 하나씩 출력한다. 만약, 방문할 수 없는 더러운 칸이 존재하는 경우에는 -1을 출력한다.
 *
 * 8
 * 49
 * -1
 *
 * ? 채점 결과
 * * 시간: 512ms
 * * 메모리: 19MB
 * * 언어: JAVA8
 */
package Gold.II;

import java.io.*;
import java.util.*;

public class G2_4991 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 초기 설정
    static int n, m; //  행, 열의 갯수
    static int[] robot; // 로봇청소기의 위치
    static char[][] arr; // 방 정보

    // * 초기 설정
    static int count; // 먼지의 수 + 1
    static int minDist; // 이동 횟수의 최솟값
    static boolean[][] visited; // 방문리스트
    static int[] sequence; // 먼지를 고르는 순서(순열)
    static int[][] dustNum; // 각위치의 먼지번호
    static int[][] dist; // 청소기-먼지, 먼지-먼지 사이의 최소 이동 거리 배열
    static ArrayList<int[]> dusts; // 먼지의 위치 리스트

    // * 상하좌우(인접)
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer(); // 정답 출력용 스트링버퍼

        // * 모든 케이스마다
        while (true) {
            input(); // * 1. 입력 받기

            // * 2. 종료 시그널
            if (n == 0 && m == 0) {
                break;
            }

            // * 3. BFS를 이용해 청소기-먼지, 먼지-먼지사이의 모든 거리를 계산
            bfs(robot[0], robot[1]);
            for (int[] dust: dusts) {
                bfs(dust[0], dust[1]);
            }

            // * 4. 순열을 이용하여 모든 순서에 대해 이동 거리의 최솟값을 갱신
            permutation(1, 1);

            // * 5. 해당 케이스의 결과를 스트링버퍼에 추가
            sb.append(minDist + "\n");
        }

        // * 6. 출력
        bw.write(sb.toString());

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 순열을 이용하여 먼지를 고르는 모든 순서에 대해 거리를 구하고 최솟값을 갱신하는 함수
    public static void permutation(int cnt, int flag) {
        if (cnt == count) {
            int distance = 0;
            for (int i = 0; i < sequence.length - 1; i++) {
                if (dist[sequence[i]][sequence[i+1]] == 0) {
                    minDist = -1;
                    return;
                }
                distance += dist[sequence[i]][sequence[i+1]];
            }

            minDist = Math.min(minDist, distance);
            return;
        }

        for (int i = 1; i < count; i++) {
            if ((flag & (1 << i)) != 0) {
                continue;
            }

            sequence[cnt] = i;
            permutation(cnt+1, flag | (1 << i));
        }
    }

    // ! BFS를 이용하여 청소기-먼지, 먼지-먼지의 모든 거리를 구하는 함수
    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y, 0});
        visited = new boolean[n][m];
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int c = cur[2];

            // 먼지에 도착했을 때 시작 먼지 번호와 현재 도착한 먼지 번호사이의 거리를 저장
            if (arr[cur[0]][cur[1]] == '*') {
                dist[dustNum[x][y]][dustNum[cur[0]][cur[1]]] = c;
            }

            // 상하좌우에 대하여
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위체크 + 방문체크 + 가구인지 체크 후 방문표시 및 큐에 삽입
                if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny] && arr[nx][ny] != 'x') {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, c+1});
                }
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        minDist = 50000;
        arr = new char[n][m];
        dustNum = new int[n][m];
        visited = new boolean[n][m];
        dusts = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '*') {
                    dustNum[i][j] = count;
                    dusts.add(new int[] {i, j});
                    count++;
                } else if (arr[i][j] == 'o') {
                    robot = new int[] {i, j};
                }
            }
        }
        dist = new int[count][count];
        sequence = new int[count];
    }
}