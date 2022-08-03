/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/14003
 *
 * ! 제목: 가장 긴 증가하는 부분 수열 5
 * ! 시간 제한: 3초
 * ! 메모리 제한: 512MB
 *
 * ! 문제
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (-1,000,000,000 ≤ Ai ≤ 1,000,000,000)
 *
 * 6                    -> n
 * 10 20 10 30 20 50    -> arr
 *
 * ! 출력
 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
 * 둘째 줄에는 정답이 될 수 있는 가장 긴 증가하는 부분 수열을 출력한다.
 *
 * 4
 * 10 20 30 50
 *
 * ? 채점 결과
 * 시간: 1016ms
 * 메모리: 326344KB
 * 언어: JAVA8
 */
package Platinum.V;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class P5_14003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int n, arr[];

    // 초기 설정
    static int length;
    static int[] series, dp;

    // * 메인
    public static void main(String[] args) throws IOException {
        input(); // * 입력받기
        LIS(); // * 이분탐색을 이용한 LIS

        // 첫째 줄에 LIS 길이 저장
        StringBuffer sb = new StringBuffer();
        sb.append(length + "\n");

        // 수열의 끝부터 시작해서
        // ? 가장 긴 증가하는 부분수열의 인덱스가 가장 큰 값부터 차례대로 찾음
        // 길이가 5였다면
        // 끝에서부터 인덱스가 4 -> 3 -> 2 -> 1 -> 0을 찾아서 스택에 넣음
        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            if (dp[i] == length-1) {
                length--;
                stack.push(arr[i]);
            }
        }

        // * 스택의 맨 위부터 두번째 출력에 담음(가장 긴 증가하는 부분 수열 중 작은 값부터)
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        // * 출력
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    // ! 이분 탐색을 이용한 LIS와 각 수열의 수가 속하는 LIS의 인덱스를 dp배열에 저장하는 함수
    public static void LIS() {
        series = new int[n];
        dp = new int[n];

        series[0] = arr[0];
        dp[0] = 0;
        length = 1;

        for (int i = 1; i < n; i++) {
            if (series[length-1] < arr[i]) {
                series[length++] = arr[i];
                dp[i] = length-1;
            } else {
                int left = 0;
                int right = length-1;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if (series[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                series[left] = arr[i];
                dp[i] = left;
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
