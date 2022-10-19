/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/15486
 *
 * ? 제목: 퇴사 2
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 상담원으로 일하고 있는 백준이는 퇴사를 하려고 한다.
 * 오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.
 * 백준이는 비서에게 최대한 많은 상담을 잡으라고 부탁을 했고, 비서는 하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.
 * 각각의 상담은 상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi로 이루어져 있다.
 *
 * N = 7인 경우에 다음과 같은 상담 일정표를 보자.
 *
 *  	1일	2일	3일	4일	5일	6일	7일
 * Ti	3	5	1	1	2	4	2
 * Pi	10	20	10	20	15	40	200
 *
 * 1일에 잡혀있는 상담은 총 3일이 걸리며, 상담했을 때 받을 수 있는 금액은 10이다. 5일에 잡혀있는 상담은 총 2일이 걸리며, 받을 수 있는 금액은 15이다.
 * 상담을 하는데 필요한 기간은 1일보다 클 수 있기 때문에, 모든 상담을 할 수는 없다. 예를 들어서 1일에 상담을 하게 되면, 2일, 3일에 있는 상담은 할 수 없게 된다. 2일에 있는 상담을 하게 되면, 3, 4, 5, 6일에 잡혀있는 상담은 할 수 없다.
 * 또한, N+1일째에는 회사에 없기 때문에, 6, 7일에 있는 상담을 할 수 없다.
 * 퇴사 전에 할 수 있는 상담의 최대 이익은 1일, 4일, 5일에 있는 상담을 하는 것이며, 이때의 이익은 10+20+15=45이다.
 * 상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 N (1 ≤ N ≤ 1,500,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에 Ti와 Pi가 공백으로 구분되어서 주어지며, 1일부터 N일까지 순서대로 주어진다. (1 ≤ Ti ≤ 50, 1 ≤ Pi ≤ 1,000)
 *
 * 7        -> n
 * 3 10     -> arr[1][0] arr[1][1]
 * 5 20
 * 1 10
 * 1 20
 * 2 15
 * 4 40
 * 2 200    -> arr[n][0] arr[n][1]
 *
 * ? 출력
 * 첫째 줄에 백준이가 얻을 수 있는 최대 이익을 출력한다.
 *
 * 45
 *
 * ? 채점 결과
 * * 시간: 0.776초
 * * 메모리: 342MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_15486 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[][] arr;

    // * 초기 설정
    static int max = 0;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP(Tabulation: Bottom-Up)
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            if (i + arr[i][0] <= n + 1) {
                dp[i + arr[i][0]] = Math.max(dp[i + arr[i][0]], dp[i] + arr[i][1]);
                max = Math.max(dp[i + arr[i][0]], max);
            }
        }

        // * 3. 출력
        System.out.println(max);
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 2][2];
        dp = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
