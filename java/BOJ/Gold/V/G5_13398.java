/**
 * ? 문제 출처: 백준 온라인 져지
 * ? https://www.acmicpc.net/problem/13398
 *
 * ? 제목: 연속합 2
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다. 또, 수열에서 수를 하나 제거할 수 있다. (제거하지 않아도 된다)
 * 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 수를 제거하지 않았을 때의 정답은 12+21인 33이 정답이 된다.
 * 만약, -35를 제거한다면, 수열은 10, -4, 3, 1, 5, 6, 12, 21, -1이 되고, 여기서 정답은 10-4+3+1+5+6+12+21인 54가 된다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
 *
 * 10                           -> n
 * 10 -4 3 1 5 6 -35 12 21 -1   -> arr[0] ~ arr[n-1]
 *
 * ? 출력
 * 첫째 줄에 답을 출력한다.
 *
 * 54
 *
 * ? 채점 결과
 * * 시간: 0.224초
 * * 메모리: 22MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Gold.V;

import java.io.*;
import java.util.StringTokenizer;

public class G5_13398 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[] arr;

    // * 초기 설정
    static int answer;
    static int[] dp1, dp2;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP(Tabulation: Bottom-Up)
        // * 왼쪽 -> 오른쪽 연속합의 최댓값 dp
        answer = arr[0];
        dp1[0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp1[i] = Math.max(dp1[i - 1] + arr[i], arr[i]);
            answer = Math.max(answer, dp1[i]); // 숫자를 제거하지 않는 경우의 max 값 업데이트
        }

        // * 오른쪽 -> 왼쪽 연속합의 최댓값 dp
        dp2[n-1] = arr[n-1];
        for (int i = n - 2; i >= 0; i--) {
            dp2[i] = Math.max(dp2[i + 1] + arr[i], arr[i]);
        }

        // * 숫자를 제거하는 경우에 왼쪽 -> i <- 오른쪽 연속합의 합의 최댓값 업데이트
        for (int i = 1; i < n-1; i++) {
            answer = Math.max(answer, dp1[i-1] + dp2[i+1]);
        }

        // * 3. 출력
        bw.write(String.valueOf(answer));

        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp1 = new int[n];
        dp2 = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
