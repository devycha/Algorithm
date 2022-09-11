/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14002
 *
 * ? 제목: 가장 긴 증가하는 부분 수열 4
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
 *
 * ? 입력
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
 *
 * ? 출력
 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
 * 둘째 줄에는 가장 긴 증가하는 부분 수열을 출력한다. 그러한 수열이 여러가지인 경우 아무거나 출력한다.
 *
 * ? 채점 결과
 * * 시간: 0.08초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Gold.IV;

import java.io.*;
import java.util.StringTokenizer;

public class G4_14002 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[] arr;

    // * 초기 설정
    static int[] dp;
    static int[] index;
    static int[] lis;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        LIS(); // * 2. DP(Tabulation: Bottom-Up): LIS
        getLIS(); // * 3. LIS 배열 찾기

        // * 4. 출력
        bw.write(String.valueOf(lis.length) + "\n");
        for (int i = 0; i < lis.length; i++) {
            bw.write(String.valueOf(lis[i]) + " ");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[n];
        dp = new int[n];
        index = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    // LIS
    public static void LIS() {
        int p = 1;
        dp[0] = arr[0];
        index[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[p-1] < arr[i]) {
                index[i] = p;
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
                index[i] = left;
            }
        }

        lis = new int[p]; // LIS 배열 크기 설정
    }

    // LIS 배열 찾기
    public static void getLIS() {
        int p = lis.length - 1;
        int idx = n-1;

        while (idx >= 0) {
            if (index[idx] == p) {
                lis[p--] = arr[idx];
            }
            idx--;
        }
    }
}
