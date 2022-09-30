/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1240
 *
 * ? 제목: 노드사이의 거리
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * N(2≤N≤1,000)개의 노드로 이루어진 트리가 주어지고 M(M≤1,000)개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 노드의 개수 N이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리(10,000 이하의 정수)를 입력받는다. 그 다음 줄에는 거리를 알고 싶은 M개의 노드 쌍이 한 줄에 한 쌍씩 입력된다.
 *
 * 4 2      -> n m
 * 2 1 2    -> a b c
 * 4 3 2
 * 1 4 3
 * 1 2      -> a b
 * 3 2
 *
 * ? 출력
 * M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.
 *
 * 2
 * 7
 *
 * ? 채점 결과
 * * 시간: 2.02초
 * * 메모리: 22MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.*;
import java.util.StringTokenizer;

public class G5_1240_FLOYD {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static int[][] arr;

    // * 거리 최댓값
    static int MAX = 10_000_000;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. 플로이드-와샬
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (k == i || k == j) {
                        continue;
                    }

                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        // * 3. 출력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(arr[a][b] + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = MAX;
            }
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a][b] = c;
            arr[b][a] = c;
        }
    }
}
