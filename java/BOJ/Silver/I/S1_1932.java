/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1932
 *
 * ? 제목: 정수 삼각형
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 *         7
 *       3   8
 *     8   1   0
 *   2   7   4   4
 * 4   5   2   6   5
 * 위 그림은 크기가 5인 정수 삼각형의 한 모습이다.
 *
 * 맨 위층 7부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때, 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라. 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
 * 삼각형의 크기는 1 이상 500 이하이다. 삼각형을 이루고 있는 각 수는 모두 정수이며, 범위는 0 이상 9999 이하이다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 삼각형의 크기 n(1 ≤ n ≤ 500)이 주어지고, 둘째 줄부터 n+1번째 줄까지 정수 삼각형이 주어진다.
 *
 * 5            -> n
 * 7            -> arr[0][0]
 * 3 8          -> arr[1][0] ~ arr[1][1]
 * 8 1 0        -> arr[2][0] ~ arr[2][2]
 * 2 7 4 4      -> arr[3][0] ~ arr[3][3]
 * 4 5 2 6 5    -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 출력한다.
 *
 * 30
 *
 * ? 채점 결과
 * * 시간: 412ms
 * * 메모리: 28MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S1_1932 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n;

    // * 초기 설정
    static int[][] arr;

    // # 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // 1. 입력 받기

        // 2. Top-Down 방식 DP (Tabulation)
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i+1; j++) {
                if (j == 0) {
                    arr[i][j] = arr[i-1][j] + arr[i][j];
                } else {
                    arr[i][j] = Math.max(arr[i-1][j-1], arr[i-1][j]) + arr[i][j];
                }
            }
        }

        // 3. 정답 출력
        int max = Arrays.stream(arr[n-1]).max().getAsInt();
        System.out.println(max);
    }

    // # 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i+1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
