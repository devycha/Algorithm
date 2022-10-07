/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/9328
 *
 * ? 제목: 열쇠
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 상근이는 1층 빌딩에 침입해 매우 중요한 문서를 훔쳐오려고 한다. 상근이가 가지고 있는 평면도에는 문서의 위치가 모두 나타나 있다. 빌딩의 문은 모두 잠겨있기 때문에, 문을 열려면 열쇠가 필요하다. 상근이는 일부 열쇠를 이미 가지고 있고, 일부 열쇠는 빌딩의 바닥에 놓여져 있다. 상근이는 상하좌우로만 이동할 수 있다.
 * 상근이가 훔칠 수 있는 문서의 최대 개수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 수는 100개를 넘지 않는다.
 * 각 테스트 케이스의 첫째 줄에는 지도의 높이와 너비 h와 w (2 ≤ h, w ≤ 100)가 주어진다. 다음 h개 줄에는 빌딩을 나타내는 w개의 문자가 주어지며, 각 문자는 다음 중 하나이다.
 *
 * '.'는 빈 공간을 나타낸다.
 * '*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
 * '$'는 상근이가 훔쳐야하는 문서이다.
 * 알파벳 대문자는 문을 나타낸다.
 * 알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
 * 마지막 줄에는 상근이가 이미 가지고 있는 열쇠가 공백없이 주어진다. 만약, 열쇠를 하나도 가지고 있지 않는 경우에는 "0"이 주어진다.
 * 상근이는 처음에는 빌딩의 밖에 있으며, 빌딩 가장자리의 벽이 아닌 곳을 통해 빌딩 안팎을 드나들 수 있다. 각각의 문에 대해서, 그 문을 열 수 있는 열쇠의 개수는 0개, 1개, 또는 그 이상이고, 각각의 열쇠에 대해서, 그 열쇠로 열 수 있는 문의 개수도 0개, 1개, 또는 그 이상이다. 열쇠는 여러 번 사용할 수 있다.
 *
 * 3                    -> t
 * 5 17                 -> h w
 * *****************    -> arr
 * .............**$*
 * *B*A*P*C**X*Y*.X.
 * *y*x*a*p**$*$**$*
 * *****************
 * cz                   -> keys
 * 5 11                 -> 반복
 * *.*********
 * *...*...*x*
 * *X*.*.*.*.*
 * *$*...*...*
 * ***********
 * 0
 * 7 7
 * *ABCDE*
 * X.....F
 * W.$$$.G
 * V.$$$.H
 * U.$$$.J
 * T.....K
 * *SQPML*
 * irony
 *
 * ? 출력
 * 각 테스트 케이스 마다, 상근이가 훔칠 수 있는 문서의 최대 개수를 출력한다.
 *
 * 3
 * 1
 * 0
 *
 * ? 채점 결과
 * * 시간: 0.188초
 * * 메모리: 20MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Gold.I;

import java.io.*;
import java.util.*;

public class G1_9328 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int h, w;
    static char[][] arr;

    // * 초기 설정
    static int keys; // 현재 갖고있는 키 정보(비트마스크)
    static int count; // 현재 획득한 문서의 개수
    static boolean[][] visited; // 방문 표시 배열

    // * 추가 정보
    // ! 키가 없어서 통과하지 못한 문의 위치
    // ex) 0 : [[1, 2], [2,3], ...] -> 현재까지 키가 없어서 통과하지 못한 A(=0)문의 위치들
    static HashMap<Integer, ArrayList<int[]>> map;
    static Queue<int[]> queue;

    // * 상하좌우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine()); // 테스트케이스 개수
        for (int i = 0; i < t; i++) {
            input(); // * 1. 입력 받기

            for (int a = 0; a < h; a++) {
                for (int b = 0; b < w; b++) {
                    // * 2. 테두리가 벽이 아닌 곳들을 큐에 삽입(+방문표시)
                    if (a == 0 || b == 0 || a == h-1 || b == w-1) {
                        if (arr[a][b] != '*') {
                            visited[a][b] = true;
                            queue.add(new int[] {a, b});
                        }
                    }
                }
            }

            bfs(); // * 3. BFS

            bw.write(String.valueOf(count + "\n")); // * 4. 출력
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            // 현재 위치가 키인 경우
            if (isKey(arr[x][y])) {
                // 새롭게 키를 습득한 경우
                if ((keys & (1 << (arr[x][y] - 'a'))) == 0) {
                    keys  |= (1 << (arr[x][y] - 'a')); // 키 추가

                    // 현재까지 해당 키가 없어서 통과하지 못한 위치들 중
                    // 문이 안열려있는 곳("."이 아닌 곳)을 큐에 넣어주고 방문 표시
                    // 문이 열렸으므로 빈공간으로 표시
                    for (int[] next : map.get(arr[x][y] - 'a')) {
                        if (arr[next[0]][next[1]] != '.') {
                            visited[next[0]][next[1]] = true;
                            queue.add(next);
                            arr[next[0]][next[1]] = '.';
                        }
                    }
                }
                // 키를 획득했으므로 빈공간으로 만들어주기
                arr[x][y] = '.';
            }

            // 현재 위치가 방문인 경우
            else if (isDoor(arr[x][y])) {
                // 해당 문에 대한 키가 없을 경우
                if (!hasKey(arr[x][y])) {
                    // map에 해당 위치 추가
                    map.get(arr[x][y] - 'A').add(new int[] {x, y});
                    continue;
                }
            }

            // 현재 위치가 문서인 경우 카운트를 증가하고 빈공간으로 표시
            else if (arr[x][y] == '$') {
                arr[x][y] = '.';
                count++;
            }

            // 상하좌우 중 벽이 아닌 곳만 큐에 삽입(+방문표시)
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w || visited[nx][ny] || arr[nx][ny] == '*') {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny});
            }

        }
    }

    // 해당 문에 맞는 키를 현재 가지고 있는지
    public static boolean hasKey(char door) {
        return (keys & (1 << (door - 'A'))) != 0;
    }

    // 현재 위치의 값이 문인지
    public static boolean isDoor(char c) {
        return 0 <= c - 'A' && c - 'A' < 26;
    }

    // 현재 위치의 값이 키인지
    public static boolean isKey(char c) {
        return 0 <= c - 'a' && c - 'a' < 26;
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        count = 0;
        arr = new char[h][w];
        visited = new boolean[h][w];
        queue = new LinkedList<>();

        map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < h; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        keys = 0;
        char[] k = br.readLine().toCharArray();

        if (k[0] != '0') {
            for (char c: k) {
                keys |= (1 << (c - 'a'));
            }
        }
    }
}
