/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/17182
 *
 * ? 제목: 우주 탐사선
 * ? 시간 제한: 1초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 우주 탐사선 ana호는 어떤 행성계를 탐사하기 위해 발사된다. 모든 행성을 탐사하는데 걸리는 최소 시간을 계산하려 한다. 입력으로는 ana호가 탐색할 행성의 개수와 ana호가 발사되는 행성의 위치와 ana호가 행성 간 이동을 하는데 걸리는 시간이 2차원 행렬로 주어진다. 행성의 위치는 0부터 시작하여 0은 행렬에서 0번째 인덱스에 해당하는 행성을 의미한다. 2차원 행렬에서 i, j 번 요소는 i 번째 행성에서 j 번째 행성에 도달하는데 걸리는 시간을 나타낸다. i와 j가 같을 때는 항상 0이 주어진다. 모든 행성을 탐사하는데 걸리는 최소 시간을 계산하여라.
 * 탐사 후 다시 시작 행성으로 돌아올 필요는 없으며 이미 방문한 행성도 중복해서 갈 수 있다.
 *
 * ? 입력
 * 첫 번째 줄에는 행성의 개수 N과 ana호가 발사되는 행성의 위치 K가 주어진다. (2 ≤ N ≤ 10, 0 ≤ K < N)
 * 다음 N 줄에 걸쳐 각 행성 간 이동 시간 Tij 가 N 개 씩 띄어쓰기로 구분되어 주어진다. (0 ≤ Tij  ≤ 1000)
 *
 * 3 0      -> n start
 * 0 30 1   -> arr[0][0] ~ arr[0][n-1]
 * 1 0 29
 * 28 1 0   -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 모든 행성을 탐사하기 위한 최소 시간을 출력한다.
 *
 * 2
 *
 * ? 채점 결과
 * * 시간: 0.12초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 5
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G3_17182 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, start;
    static int[][] arr;
    static ArrayList<Integer>[] visit;
    static int pass;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        floydWarshall();
        backTracking(start, (1 << start), 0);
        System.out.println(min);
    }

    public static void backTracking(int cur, int visit, int time) {
        if (visit == pass) {
            min = Math.min(min, time);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (cur == i) {
                continue;
            }

            if ((visit & (1 << i)) == 0) {
                backTracking(i, visit | (1 << i), time + arr[cur][i]);
            }
        }
    }


    public static void floydWarshall() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (k == i || i == j) {
                        continue;
                    }

                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        pass = (1 << n) - 1;

        visit = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            visit[i] = new ArrayList<>();
        }

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
