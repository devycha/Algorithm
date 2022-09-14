/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/10942
 *
 * ? 제목: 팰린드롬?
 * ? 시간 제한: 2.5초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 명우는 홍준이와 함께 팰린드롬 놀이를 해보려고 한다.
 * 먼저, 홍준이는 자연수 N개를 칠판에 적는다. 그 다음, 명우에게 질문을 총 M번 한다.
 * 각 질문은 두 정수 S와 E(1 ≤ S ≤ E ≤ N)로 나타낼 수 있으며, S번째 수부터 E번째 까지 수가 팰린드롬을 이루는지를 물어보며, 명우는 각 질문에 대해 팰린드롬이다 또는 아니다를 말해야 한다.
 * 예를 들어, 홍준이가 칠판에 적은 수가 1, 2, 1, 3, 1, 2, 1라고 하자.
 *
 * S = 1, E = 3인 경우 1, 2, 1은 팰린드롬이다.
 * S = 2, E = 5인 경우 2, 1, 3, 1은 팰린드롬이 아니다.
 * S = 3, E = 3인 경우 1은 팰린드롬이다.
 * S = 5, E = 7인 경우 1, 2, 1은 팰린드롬이다.
 * 자연수 N개와 질문 M개가 모두 주어졌을 때, 명우의 대답을 구하는 프로그램을 작성하시오.
 *
 * ? 입력
 * 첫째 줄에 수열의 크기 N (1 ≤ N ≤ 2,000)이 주어진다.
 * 둘째 줄에는 홍준이가 칠판에 적은 수 N개가 순서대로 주어진다. 칠판에 적은 수는 100,000보다 작거나 같은 자연수이다.*
 * 셋째 줄에는 홍준이가 한 질문의 개수 M (1 ≤ M ≤ 1,000,000)이 주어진다.
 * 넷째 줄부터 M개의 줄에는 홍준이가 명우에게 한 질문 S와 E가 한 줄에 하나씩 주어진다.
 *
 * 7                -> n
 * 1 2 1 3 1 2 1    -> arr[1] ~ arr[n]
 * 4                -> t
 * 1 3              -> s e
 * 2 5              -> s e
 * 3 3              -> s e
 * 5 7              -> s e
 *
 * ? 출력
 * 총 M개의 줄에 걸쳐 홍준이의 질문에 대한 명우의 답을 입력으로 주어진 순서에 따라서 출력한다. 팰린드롬인 경우에는 1, 아닌 경우에는 0을 출력한다.
 *
 * 1
 * 0
 * 1
 * 1
 *
 * ? 채점 결과
 * * 시간: 0.888초
 * * 메모리: 316MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Gold.IV;

import java.io.*;
import java.util.StringTokenizer;

public class G4_10942 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[] arr;

    // * 초기 설정
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        // * 1. 입력 받기
        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];
        dp = new int[n+1][n+1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // * 2. DP 테이블 초기화 + Start Case 설정
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }

        // * 3. DP(Tabulation: Bottom-Up)
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                if (i >= j) {
                    continue;
                } else if (j - i == 1) {
                    if (arr[i] == arr[j]) {
                        dp[i][j] = 1;
                    }
                } else {
                    if (dp[i+1][j-1] == 1 && arr[i] == arr[j]) {
                        dp[i][j] = 1;
                    }
                }
            }
        }

        // * 4. 각 테스트 케이스마다 정답 출력
        StringBuffer sb = new StringBuffer();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(dp[s][e] + "\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}
