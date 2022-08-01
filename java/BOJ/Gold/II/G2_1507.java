/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/1507
 *
 * ! 제목: 궁금한 민호
 * ! 시간 제한: 2초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 강호는 N개의 도시로 이루어진 나라에 살고 있다. 각 도시는 M개의 도로로 연결되어 있으며, 각 도로를 지날 때 필요한 시간이 존재한다. 도로는 잘 연결되어 있기 때문에, 도시 A에서 B로 이동할 수 없는 경우는 존재하지 않는다.
 * 도시 A에서 도시 B로 바로 갈 수 있는 도로가 있거나, 다른 도시를 거쳐서 갈 수 있을 때, 도시 A에서 B를 갈 수 있다고 한다.
 * 강호는 모든 쌍의 도시에 대해서 최소 이동 시간을 구해놓았다. 민호는 이 표를 보고 원래 도로가 몇 개 있는지를 구해보려고 한다.
 * 예를 들어, 예제의 경우에 모든 도시 사이에 강호가 구한 값을 가지는 도로가 존재한다고 해도 된다. 하지만, 이 도로의 개수는 최솟값이 아니다. 예를 들어, 도시 1-2, 2-3, 1-4, 3-4, 4-5, 3-5를 연결하는 도로만 있다고 가정해도, 강호가 구한 모든 쌍의 최솟값을 구할 수 있다. 이 경우 도로의 개수는 6개이고, 모든 도로의 시간의 합은 55이다.
 * 모든 쌍의 도시 사이의 최소 이동 시간이 주어졌을 때, 이 나라에 존재할 수 있는 도로의 개수의 최솟값일 때, 모든 도로의 시간의 합을 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 20)이 주어진다. 둘째 줄부터 N개의 줄에 각각의 도시 사이에 이동하는데 필요한 시간이 주어진다. A에서 B로 가는 시간과 B에서 A로 가는 시간은 같다. 또, A와 B가 같은 경우에는 0이 주어지고, 그 외의 경우에 필요한 시간은 2500보다 작거나 같은 자연수이다.
 *
 * 5            -> n
 * 0 6 15 2 6   -> arr[0][0] ~ arr[0][n-1]
 * 6 0 9 8 12
 * 15 9 0 16 18
 * 2 8 16 0 4
 * 6 12 18 4 0  -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ! 출력
 * 첫째 줄에 도로 개수가 최소일 때, 모든 도로의 시간의 합을 출력한다. 불가능한 경우에는 -1을 출력한다.
 *
 * 55
 *
 * ? 채점 결과
 * 시간: 84ms
 * 메모리: 11688KB
 * 언어: JAVA8
 */
package Gold.II;

import java.io.*;
import java.util.StringTokenizer;

public class G2_1507 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int n, arr[][];

    // 초기 설정
    static int total = 0; // 도로의 개수가 최솟값일 때, 모든 도로의 시간의 합
    static boolean[][] minRoad; // 도로의 개수가 최솟값이 되기 위해 선택하지 말아야 할 도로(true값이면 선택하지 않음)
    static boolean result = true; // 불가능한 경우가 있는지 판단

    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기
        floyd(); // * 플로이드 와샬

        // * minRoad의 값이 true인 값을 제외하고 모든 도로의 길이를 더한다(중복 없이)
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (!minRoad[i][j]) total += arr[i][j];
            }
        }

        bw.write(String.valueOf(result ? total : -1)); // * 출력

        br.close();
        bw.flush();
        bw.close();
    }

    // ! 플로이드 와샬
    public static void floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || j == k || k == i) continue;

                    // * 플로이드 와샬이 성립하지 않는 경우 종료
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        result = false;
                        return;
                    } else if (arr[i][j] == arr[i][k] + arr[k][j]) {
                        // * i -> j로 가는 최소거리와 i -> k -> j로 가는 최소거리가 같다면
                        // * i -> j로 가는 경로를 포함시킬 필요 없음(i -> k, k -> j만 선택해도 됨)
                        minRoad[i][j] = true;
                    }
                }
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        minRoad = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
