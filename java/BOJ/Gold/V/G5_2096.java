/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2096
 *
 * ? 제목: 내려가기
 * ? 시간 제한: 1초
 * ? 메모리 제한: 4MB
 *
 * ? 문제
 * N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데, 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.
 * 먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다. 그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다. 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다. 이 제약 조건을 그림으로 나타내어 보면 다음과 같다.
 * 별표는 현재 위치이고, 그 아랫 줄의 파란 동그라미는 원룡이가 다음 줄로 내려갈 수 있는 위치이며, 빨간 가위표는 원룡이가 내려갈 수 없는 위치가 된다. 숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.
 *
 * ? 입력
 * 첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 숫자가 세 개씩 주어진다. 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.
 *
 * 3        -> n
 * 1 2 3    -> arr[0][0] ~ arr[0][2]
 * 4 5 6
 * 4 9 0    -> arr[n-1][0] ~ arr[n-1][2]
 *
 * ? 출력
 * 첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.
 *
 * 18 6
 *
 * ? 채점 결과
 * * 시간: 0.54초
 * * 메모리: 57MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_2096 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[][] arr;

    // * 초기 설정
    static int[][] maxDp;
    static int[][] minDp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP(Tabulation: Bottom-Up)
        for (int i = 1; i < n; i++) {
            // * 내려가기 최댓값 DP
            maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1]) + arr[i][0];
            maxDp[i][1] = Math.max(maxDp[i-1][0], Math.max(maxDp[i-1][1], maxDp[i-1][2])) + arr[i][1];
            maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2]) + arr[i][2];

            // * 내려가기 최솟값 DP
            minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1]) + arr[i][0];
            minDp[i][1] = Math.min(minDp[i-1][0], Math.min(minDp[i-1][1], minDp[i-1][2])) + arr[i][1];
            minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2]) + arr[i][2];
        }
        
        // * 3. 정답 출력
        int max = Arrays.stream(maxDp[n-1]).max().getAsInt();
        int min = Arrays.stream(minDp[n-1]).min().getAsInt();

        bw.write(max + " " + min);

        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n][3];
        maxDp = new int[n][3];
        minDp = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                maxDp[i][j] = arr[i][j];
                minDp[i][j] = arr[i][j];
            }
        }
    }
}
