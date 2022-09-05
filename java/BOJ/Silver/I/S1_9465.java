/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/9465
 *
 * ? 제목: 스티커
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 상근이의 여동생 상냥이는 문방구에서 스티커 2n개를 구매했다. 스티커는 그림 (a)와 같이 2행 n열로 배치되어 있다. 상냥이는 스티커를 이용해 책상을 꾸미려고 한다.
 * 상냥이가 구매한 스티커의 품질은 매우 좋지 않다. 스티커 한 장을 떼면, 그 스티커와 변을 공유하는 스티커는 모두 찢어져서 사용할 수 없게 된다. 즉, 뗀 스티커의 왼쪽, 오른쪽, 위, 아래에 있는 스티커는 사용할 수 없게 된다.
 * 모든 스티커를 붙일 수 없게된 상냥이는 각 스티커에 점수를 매기고, 점수의 합이 최대가 되게 스티커를 떼어내려고 한다. 먼저, 그림 (b)와 같이 각 스티커에 점수를 매겼다. 상냥이가 뗄 수 있는 스티커의 점수의 최댓값을 구하는 프로그램을 작성하시오. 즉, 2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유 하지 않는 스티커 집합을 구해야 한다.
 * 위의 그림의 경우에 점수가 50, 50, 100, 60인 스티커를 고르면, 점수는 260이 되고 이 것이 최대 점수이다. 가장 높은 점수를 가지는 두 스티커 (100과 70)은 변을 공유하기 때문에, 동시에 뗄 수 없다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 n (1 ≤ n ≤ 100,000)이 주어진다. 다음 두 줄에는 n개의 정수가 주어지며, 각 정수는 그 위치에 해당하는 스티커의 점수이다. 연속하는 두 정수 사이에는 빈 칸이 하나 있다. 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다.
 *
 * 2                        -> t
 * 5                        -> n
 * 50 10 100 20 40          -> sticker[0][1] ~ sticker[0][n]
 * 30 50 70 10 60           -> sticker[1][1] ~ sticker[1][n]
 * 7                        -> n
 * 10 30 10 50 100 20 40    -> sticker[0][1] ~ sticker[0][n]
 * 20 40 30 50 60 20 80     -> sticker[1][1] ~ sticker[1][n]
 *
 * ? 출력
 * 각 테스트 케이스 마다, 2n개의 스티커 중에서 두 변을 공유하지 않는 스티커 점수의 최댓값을 출력한다.
 *
 * 260
 * 290
 *
 * ? 채점 결과
 * * 시간: 600ms
 * * 메모리: 125MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Silver.I;

import java.io.*;
import java.util.StringTokenizer;

public class S1_9465 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[][] sticker;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine()); // test case 개수
        StringBuffer sb = new StringBuffer(); // 출력문
        
        // * 0. t번동안
        for (int i = 0; i < t; i++) {
            input(); // * 1. 입력 받기
            
            // * 2. DP(Tabulation: Bottom-Up)
            if (n == 1) { // * n이 1인 경우 스티커 두개 중 max값 추가
                sb.append(Math.max(sticker[0][1], sticker[1][1]) + "\n");
            } else { // * n이 1이 아닌 경우 DP(Tabulation: Bottom-Up) 실행
                sb.append(dp() + "\n");
            }
        }

        // * 3. 출력
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    // ! DP(Tabulation: Bottom-Up)
    public static int dp() {
        int[][] table = new int[2][n+1];
        for (int i = 0; i < 2; i++) {
            table[i] = sticker[i].clone();
        }


        for (int j = 2; j <= n; j++) {
            for (int i = 0; i < 2; i++) {
                table[i][j] = Math.max(table[i][j],
                        Math.max(table[i^1][j-1], table[i^1][j-2]) + sticker[i][j]);
            }
        }

        return Math.max(table[0][n], table[1][n]);
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        sticker = new int[2][n+1];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                sticker[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
