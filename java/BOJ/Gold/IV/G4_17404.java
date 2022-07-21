/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/17404
 *
 * ! 문제: RGB거리 2
 * ! 시간제한: 0.5초(추가시간없음)
 * ! 메모리제한: 128MB
 *
 * ! 문제
 * RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 * 1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
 * N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
 * i(2 ≤ i ≤ N-1)번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 한다.
 *
 *
 * ! 입력  & 파싱
 * 첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
 *
 * 3        -> n
 * 26 40 83 -> arr[0][0] ~ arr[0][n-1]
 * 49 60 57
 * 13 89 99 -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ! 출력
 * 첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
 *
 * 110
 *
 * ? 채점 결과
 * 메모리: 27820KB
 * 시간: 112ms
 * 언어: JAVA8
 **/
package Gold.IV;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_17404 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // * 파싱 
    static int n; // 집의 개수 N
    static int[][] arr; // 각 집의 색깔별 가격

    // * 초기 설정
    static int[][] dp; // DP를 위한 메모이제이션 배열
    static int min = 1000000; // 모든 집을 칠하는 비용의 최솟값

    // * 메인함수
    public static void main(String[] args) throws IOException {
        input(); // 입력 받기
        
        // ! R, G, B 총 세가지 시작에 대한 DP 시작
        for (int i = 0; i < 3; i++) {
            // * DP 초기화
            dp = new int[n][n];
            for (int[] d: dp) {
                Arrays.fill(d, 1000000);
            }
            dp[0] = arr[0].clone();
            
            // * DP를 이용한 최솟값 도출
            find(i);

            // * 맨 마지막 집을 칠할 때 까지 사용한 최소 비용을 구하고
            // * 최솟값을 업데이트
            for (int cost: dp[n-1]) {
                if (cost < min) min = cost;
            }
        }

        // * 출력
        bw.write(String.valueOf(min));
        br.close();
        bw.flush();
        bw.close();
    }

    // * 각 시작점에 따른 DP 시작
    public static void find(int start) {
        // * 2번째 집(1번째 열)은 시작점을 지난 값을 모두 더해준다.
        // * 만약 시작점과 같은 색깔일 경우에는 1000000 대입
        for (int i = 0; i < 3; i++) {
            if (i == start) dp[1][i] = 1000000;
            else {
                // arr[0][start] -> start로 무조건 시작해야 되기 때문
                dp[1][i] = arr[1][i] + arr[0][start];
            }
        }

        // * 3번째 집부터 마지막 집까지
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                // * 마지막 집의 시작점과 같은색깔은 1000000대입
                if (i == n-1 && j == start) dp[i][j] = 1000000;
                // * 그렇지 않은 경우에(일반적인 CASE)
                else {
                    for (int k = 0; k < 3; k++) {
                        // * 바로 이전 집의 색깔과 같지 않은 경우에만
                        if (k == j) continue;
                        // * 비용의 최솟값을 구함
                        dp[i][j] = Math.min(dp[i][j], arr[i][j] + dp[i-1][k]);
                    }
                }
            }
        }
    }

    // * 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
