/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1965
 *
 * ? 상자넣기
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 정육면체 모양의 상자가 일렬로 늘어서 있다. 상자마다 크기가 주어져 있는데, 앞에 있는 상자의 크기가 뒤에 있는 상자의 크기보다 작으면, 앞에 있는 상자를 뒤에 있는 상자 안에 넣을 수가 있다. 예를 들어 앞에서부터 순서대로 크기가 (1, 5, 2, 3, 7)인 5개의 상자가 있다면, 크기 1인 상자를 크기 5인 상자에 넣고, 다시 이 상자를 크기 7인 상자 안에 넣을 수 있다. 하지만 이렇게 상자를 넣을 수 있는 방법은 여러 가지가 있을 수 있다. 앞의 예에서 차례대로 크기가 1, 2, 3, 7인 상자를 선택하면 총 4개의 상자가 한 개의 상자에 들어가게 된다.
 * 상자의 크기가 주어질 때, 한 번에 넣을 수 있는 최대의 상자 개수를 출력하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 파일의 첫 번째 줄은 상자의 개수 n (1 ≤ n ≤ 1000)을 나타낸다. 두 번째 줄에는 각 상자의 크기가 순서대로 주어진다. 상자의 크기는 1,000을 넘지 않는 자연수이다.
 *
 * 8
 * 1 6 2 5 7 3 5 6
 *
 * ? 출력
 *
 * 5
 *
 * ? 채점 결과
 * * 시간: 0.08초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_1965 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱 & 초기 설정
    static int n;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. Start Case 설정
        dp[0] = arr[0];
        int p = 1;

        // * 3. LIS(DP: Tabulation)
        for (int i = 0; i < n; i++) {
            if (dp[p-1] < arr[i]) {
                dp[p++] = arr[i];
            } else {
                int left = 0;
                int right = p-1;

                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (dp[mid] == arr[i]) {
                        left = mid;
                        break;
                    }

                    if (dp[mid] > arr[i]) {
                        right = mid-1;
                    } else {
                        left = mid+1;
                    }
                }

                dp[left] = arr[i];
            }
        }

        // * 4. 정답 출력
        System.out.println(p);
    }

    // 입력 받기 
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        dp = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
