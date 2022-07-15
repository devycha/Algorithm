package failed;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G1_9328 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int h, w;
    static char[][] arr;
    static ArrayList<Integer>[][] visited;
    static int keys;
    static int count;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            input();
            find();
            bw.write(String.valueOf(count + "\n"));
        }
        bw.flush();

    }

    public static void find() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || j == 0 || i == h-1 || j == w-1) {
                    if (isKey(arr[i][j])) {
                        int nKey = keys | getKey(arr[i][j]);
                        arr[i][j] = '.';
                        bfs(i, j, nKey);
                        return;
                    } else if (isDoor(arr[i][j]) && hasKey(arr[i][j], keys)) {
                        arr[i][j] = '.';
                        bfs(i, j, keys);
                        return;
                    } else if (arr[i][j] == '$') {
                        count++;
                        arr[i][j] = '.';
                        bfs(i, j, keys);
                        return;
                    } else if (arr[i][j] == '.') {
                        bfs(i, j, keys);
                        return;
                    }
                }
            }
        }
    }

    public static void bfs(int x, int y, int key) {
        LinkedList<int[]> queue = new LinkedList<>();
        visited[x][y].add(key);
        queue.add(new int[] {x, y, key});
        System.out.println();

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curKey = cur[2];
            System.out.println(curX + " " + curY + " " + Integer.toBinaryString(curKey) + " " + visited[curX][curY]);

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (0 <= nx & nx < h && 0 <= ny && ny < w && !alreadyVisited(nx, ny, curKey)) {
                    visited[nx][ny].add(curKey);
                    if (isKey(arr[nx][ny])) {
                        int nextKey = curKey | getKey(arr[nx][ny]);
                        queue.add(new int[] {nx, ny, nextKey});
                        arr[nx][ny] = '.';
                    } else if (isDoor(arr[nx][ny]) && hasKey(arr[nx][ny], curKey)) {
                        queue.add(new int[] {nx, ny, curKey});
                        arr[nx][ny] = '.';
                    } else if (arr[nx][ny] == '$') {
                        count++;
                        queue.add(new int[] {nx, ny, curKey});
                        arr[nx][ny] = '.';
                    } else if (arr[nx][ny] == '.') {
                        queue.add(new int[] {nx, ny, curKey});
                    }
                } else {
                    for (int a = 0; a < h; a++) {
                        for (int b = 0; b < w; b++) {
                            if (a == 0 || b == 0 || a == h-1 || b == w-1 && !alreadyVisited(a, b, curKey)) {
                                visited[a][b].add(curKey);
                                if (isKey(arr[a][b])) {
                                    int nextKey = curKey | getKey(arr[a][b]);
                                    queue.add(new int[] {a, b, nextKey});
                                    arr[a][b] = '.';
                                } else if (isDoor(arr[a][b]) && hasKey(arr[a][b], curKey)) {
                                    queue.add(new int[] {a, b, curKey});
                                    arr[a][b] = '.';
                                } else if (arr[a][b] == '$') {
                                    count++;
                                    queue.add(new int[] {a, b, curKey});
                                    arr[a][b] = '.';
                                } else if (arr[a][b] == '.') {
                                    queue.add(new int[] {a, b, curKey});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean alreadyVisited(int x, int y, int curKey) {
        for (int num: visited[x][y]) {
            if (curKey == num) return true;
        }
        return false;
    }

    public static boolean isKey(char c) {
        return (0 <= c - 'a') && (c - 'a' < 26);
    }

    public static int getKey(char key) {
        return 1 << (key - 'a');
    }

    public static boolean isDoor(char c) {
        return (0 <= c - 'A') && (c - 'A' < 26);
    }

    public static boolean hasKey(char door, int key) {
        int needKey = 1 << (door - 'A');
        return (key & needKey) != 0;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        count = 0;
        arr = new char[h][w];
        visited = new ArrayList[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                visited[i][j] = new ArrayList<Integer>();
            }
        }

        for (int i = 0; i < h; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        char[] k = br.readLine().toCharArray();
        keys = 0;

        if (k[0] != '0') {
            for (char c: k) {
                keys |= (1 << (c - 'a'));
            }
        }
    }
}
