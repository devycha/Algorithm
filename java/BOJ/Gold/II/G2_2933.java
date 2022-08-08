/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/2933
 *
 * ! 제목: 미네랄
 * ! 시간 제한: 1초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 창영과 상근은 한 동굴을 놓고 소유권을 주장하고 있다. 두 사람은 막대기를 서로에게 던지는 방법을 이용해 누구의 소유인지를 결정하기로 했다. 싸움은 동굴에서 벌어진다. 동굴에는 미네랄이 저장되어 있으며, 던진 막대기가 미네랄을 파괴할 수도 있다.
 * 동굴은 R행 C열로 나타낼 수 있으며, R×C칸으로 이루어져 있다. 각 칸은 비어있거나 미네랄을 포함하고 있으며, 네 방향 중 하나로 인접한 미네랄이 포함된 두 칸은 같은 클러스터이다.
 * 창영은 동굴의 왼쪽에 서있고, 상근은 오른쪽에 서있다. 두 사람은 턴을 번갈아가며 막대기를 던진다. 막대를 던지기 전에 던질 높이를 정해야 한다. 막대는 땅과 수평을 이루며 날아간다.
 * 막대가 날아가다가 미네랄을 만나면, 그 칸에 있는 미네랄은 모두 파괴되고 막대는 그 자리에서 이동을 멈춘다.
 * 미네랄이 파괴된 이후에 남은 클러스터가 분리될 수도 있다. 새롭게 생성된 클러스터가 떠 있는 경우에는 중력에 의해서 바닥으로 떨어지게 된다. 떨어지는 동안 클러스터의 모양은 변하지 않는다. 클러스터는 다른 클러스터나 땅을 만나기 전까지 게속해서 떨어진다. 클러스터는 다른 클러스터 위에 떨어질 수 있고, 그 이후에는 합쳐지게 된다.
 * 동굴에 있는 미네랄의 모양과 두 사람이 던진 막대의 높이가 주어진다. 모든 막대를 던지고 난 이후에 미네랄 모양을 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 동굴의 크기 R과 C가 주어진다. (1 ≤ R,C ≤ 100)
 * 다음 R개 줄에는 C개의 문자가 주어지며, '.'는 빈 칸, 'x'는 미네랄을 나타낸다.
 * 다음 줄에는 막대를 던진 횟수 N이 주어진다. (1 ≤ N ≤ 100)
 * 마지막 줄에는 막대를 던진 높이가 주어지며, 공백으로 구분되어져 있다. 모든 높이는 1과 R사이이며, 높이 1은 행렬의 가장 바닥, R은 가장 위를 의미한다. 첫 번째 막대는 왼쪽에서 오른쪽으로 던졌으며, 두 번째는 오른쪽에서 왼쪽으로, 이와 같은 식으로 번갈아가며 던진다.
 * 공중에 떠 있는 미네랄 클러스터는 없으며, 두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우도 없다. 클러스터가 떨어질 때, 그 클러스터 각 열의 맨 아래 부분 중 하나가 바닥 또는 미네랄 위로 떨어지는 입력만 주어진다.
 *
 * 5 6      -> n m
 * ......   -> arr[0][0] ~ arr[0][m-1]
 * ..xx..
 * ..x...
 * ..xx..
 * .xxxx.   -> arr[n-1][0] ~ arr[n-1][m-1]
 * 1        -> c
 * 3        -> throwing[0] ~ throwing[c-1]
 *
 * ! 출력
 * 입력 형식과 같은 형식으로 미네랄 모양을 출력한다.
 *
 * ......
 * ......
 * ..xx..
 * ..xx..
 * .xxxx.
 *
 * ? 채점 결과
 * 시간: 144ms
 * 메모리: 14MB
 * 언어: JAVA8
 **/
package Gold.II;

import java.io.*;
import java.util.*;

public class G2_2933 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m, c;
    static char[][] arr;
    static int[] throwing;

    // * 초기 설정
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // * 막대기 던지는 단계마다 분리된 클리스터
    static ArrayList<int[]> cluster;

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기
        throwStick(); // * 번갈아가면서 막대기 던지기

        // * 출력
        StringBuffer sb= new StringBuffer();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        bw.write(String.valueOf(sb.toString()));

        br.close();
        bw.flush();
        bw.close();
    }

    // ! 막대기 던지기
    public static void throwStick() {
        for (int i = 0; i < throwing.length; i++) {
            visited = new boolean[n][m];
            // * 막대기에 부딪힌 클러스터 찾기
            int tx = n - throwing[i];
            int ty = -1;

            if (i % 2 == 0) {
                for (int j = 0; j < m; j++) {
                    if (arr[tx][j] == 'x') {
                        ty = j;
                        break;
                    }
                }
            } else {
                for (int j = m-1; j >= 0; j--) {
                    if (arr[tx][j] == 'x') {
                        ty = j;
                        break;
                    }
                }
            }

            if (ty == -1) continue; // * 부딪힌 클러스터가 없다면 패스
            arr[tx][ty] = '.'; // * 해당 위치의 미네랄 부수기
            // * 바닥과 연결된 클러스터들 방문 표시
            floor();

            // * 방문표시 안 된 클러스터들 밑바닥까지 떨구기(떨굴 때 모양 바꾸지 않기)
            // * 도중에 클러스터나 바닥을 만나면 종료
            findCluster(tx, ty);
        }
    }

    // ! 바닥과 연결된 클러스트들을 방문표시하기
    public static void floor() {
        for (int i = 0; i < m; i++) {
            if (arr[n-1][i] == 'x') {
                visited[n-1][i] = true;
                dfs(n-1, i);
            }
        }
    }

    // ! 일반 DFS
    public static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny] && arr[nx][ny] == 'x') {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }

    // ! 분리된 클러스터덩어리를 찾고 바닥으로 떨어뜨리기
    public static void findCluster(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // * 범위 + 방문여부 + 값 체크
            if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || arr[nx][ny] != 'x') continue;

            int p = 0;
            cluster = new ArrayList<>(); // * 클러스터 초기화
            cluster.add(new int[] {nx, ny});
            visited[nx][ny] = true;

            while (p < cluster.size()) {
                int[] cur = cluster.get(p++);

                for (int k = 0; k < 4; k++) {
                    int nx2 = cur[0] + dx[k];
                    int ny2 = cur[1] + dy[k];

                    if (0 <= nx2 && nx2 < n && 0 <= ny2 && ny2 < m && !visited[nx2][ny2] && arr[nx2][ny2] == 'x') {
                        visited[nx2][ny2] = true;
                        cluster.add(new int[] {nx2, ny2});

                    }
                }
            }

            down(); // * 바닥으로 떨어뜨리기
            break; // * 클러스터가 두개 이상 한번에 떨어지지 않기 때문에 한번 떨어졌다면 종료
        }
    }

    // ! 분리된 클러스터를 떨어뜨리기(중간에 클러스터를 만나거나 바닥과 닿으면 다시 원상복귀 시키고 종료)
    public static void down() {
        while (true) {
            for (int[] cur: cluster) {
                arr[cur[0]][cur[1]] = '.';
            }

            for (int[] cur: cluster) {
                if (cur[0]+1 == n || arr[cur[0]+1][cur[1]] == 'x') {
                    for (int[] cur2: cluster) {
                        arr[cur2[0]][cur2[1]] = 'x';
                    }
                    return;
                }
            }

            for (int[] cur: cluster) {
                arr[cur[0]+1][cur[1]] = 'x';
                cur[0] += 1;
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        c = Integer.parseInt(br.readLine());
        throwing = new int[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            throwing[i] = Integer.parseInt(st.nextToken());
        }
    }
}
