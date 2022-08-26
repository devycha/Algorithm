/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/17135
 *
 * ? 제목: 캐슬 디펜스
 * ? 시간 제한: 1초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 캐슬 디펜스는 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다. 게임이 진행되는 곳은 크기가 N×M인 격자판으로 나타낼 수 있다. 격자판은 1×1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는 최대 하나이다. 격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.
 * 성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다. 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다. 각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다. 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 같은 적이 여러 궁수에게 공격당할 수 있다. 공격받은 적은 게임에서 제외된다. 궁수의 공격이 끝나면, 적이 이동한다. 적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다. 모든 적이 격자판에서 제외되면 게임이 끝난다.
 * 게임 설명에서 보다시피 궁수를 배치한 이후의 게임 진행은 정해져있다. 따라서, 이 게임은 궁수의 위치가 중요하다. 격자판의 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.
 * 격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 격자판 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다. 둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸이다.
 *
 * 5 5 1        -> n m d
 * 0 0 0 0 0    -> arr[0][0] ~ arr[0][m-1]
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 1 1 1 1 1    -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * 첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.
 *
 * 3
 *
 * ? 제한
 * 3 ≤ N, M ≤ 15
 * 1 ≤ D ≤ 10
 *
 * ? 채점 결과
 * * 시간: 172ms
 * * 메모리: 40MB
 * * 언어: JAVA8
 * * 시도: 7
 */
package Gold.III;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_17135 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m, d;
    static int[][] arr;

    // * 초기 설정
    static int max = 0; // 죽일 수 있는 적의 최댓값
    static int count = 0; // 궁수 배치마다 죽일 수 있는 적의 수
    static int[] archers = new int[3]; // 궁수 배치
    static int[][] castle; // 궁수 배치마다 초기 적의 위치

    // * 3방( 왼쪽 위 오른쪽 )
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        setArcher(0, 0); // * 2. 궁수 배치의 모든 조합마다 죽일 수 있는 적의 수를 구하고 그때의 최댓값 구하기

        bw.write(String.valueOf(max)); // * 3. 정답 출력

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    // ! 궁수 배치의 모든 경우의 수 구하기(조합)
    public static void setArcher(int len, int start) {
        if (len == 3) {
            play(); // * 각 배치마다 play 실행
            return;
        }

        for (int i = start; i < m; i++) {
            archers[len] = i;
            setArcher(len+1, i+1);
        }
    }

    // ! 궁수 배치가 끝났다면 n번의 턴동안 궁수들이 죽일 수 있는 적들을 누적해서 더하기(count)
    public static void play() {
        count = 0;
        castle = new int[n][m];
        for (int i = 0; i < n; i++) {
            castle[i] = arr[i].clone();
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < archers.length; i++) {
                findEnemy(archers[i]); // * 현재 격자 상태에서 3명의 궁수들이 적을 죽이기(한명의 적이 여러명의 궁수에게 맞을 수 있음)
            }
            down(); // * 한 턴이 끝났다면 밑으로 한칸 내림
        }
        
        // * 모든 턴이 끝났다면 죽인 적의 수를 최댓값과 비교하여 업데이트
        if (max < count) {
            max = count;
        }
    }

    // ! 현재 궁수의 위치에서 죽일 수 있는 적을 찾기
    public static void findEnemy(int archer) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {n, archer, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[2] == d) {
                continue;
            }

            for (int i = 0; i < 3; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) {
                    continue;
                }

                // 적의 위치일 때
                if (castle[nx][ny] >= 1) {
                    if (castle[nx][ny] == 1) {
                        count++;
                    }
                    castle[nx][ny]++; // * 중복으로 적을 공격할 수 있기 때문에 중복 공격당했다는 것을 표시하기 위해 +1
                    return;
                }

                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny, cur[2]+1});
            }
        }

    }

    // ! 적들의 위치를 한칸 내리는 함수
    public static void down() {
        int[][] newArr = new int[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (castle[i-1][j] >= 2) { // * 적이 죽었다면 0으로 셋팅
                    newArr[i][j] = 0;
                } else {
                    newArr[i][j] = castle[i-1][j];
                }
            }
        }
        castle = newArr;
    }
}
