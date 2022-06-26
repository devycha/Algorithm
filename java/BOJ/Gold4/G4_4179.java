package Gold4;

import java.io.*;
import java.util.*;

public class G4_4179 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        // 파싱
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        // 초기 설정
        int[] dx = new int[] {1, -1, 0, 0};
        int[] dy = new int[] {0 ,0, 1, -1};
        int answer = 0;

        // 불 & 지훈의 위치 큐에 담기
        Queue<int[]> queue = new LinkedList<>();
        int[] jihun = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'F') {
                    queue.add(new int[] {i, j, 1});
                } else if (arr[i][j] == 'J') {
                    jihun = new int[] {i, j, 0};
                }
            }
        }
        // 마지막에 지훈이 위치 넣기
        queue.add(jihun);

        // BFS 수행
        int[][] visited = new int[n][m];
        for (int[] v: visited) {
            Arrays.fill(v, 0);
        }
        visited[jihun[0]][jihun[1]] = 1;

        // BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x  = current[0], y = current[1], isFire = current[2];
            
            if ((x == 0 || x == n-1 || y == 0 || y == m-1) && isFire == 0) {
                answer = visited[x][y];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (isFire == 1 && (arr[nx][ny] == '.' || arr[nx][ny] == 'J')) {
                        arr[nx][ny] = 'F';
                        queue.add(new int[] {nx, ny, 1});
                    } else if (isFire == 0 && arr[nx][ny] == '.' && visited[nx][ny] == 0) {
                        visited[nx][ny] = visited[x][y] + 1;
                        queue.add(new int[] {nx, ny, 0});
                    }
                }
            }

        }
        System.out.println(answer == 0 ? "IMPOSSIBLE" : answer);
    }
}
