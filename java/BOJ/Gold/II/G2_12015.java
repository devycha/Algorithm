/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/12015
 *
 * ! 제목: 가장 긴 증가하는 부분 수열 2
 * ! 시간 제한: 1초
 * ! 메모리 제한: 512MB
 *
 * ! 문제
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)
 *
 * 6                    -> n
 * 10 20 10 30 20 50    -> arr
 *
 * ! 출력
 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
 *
 * 4
 *
 * ? 채점 결과
 * 시간: 508ms
 * 메모리: 125412KB
 * 언어: JAVA8
*/
package Gold.II;

import java.io.*;
import java.util.StringTokenizer;

public class G2_12015 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int n, arr[];

    // 초기 설정
    static int length;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기
        search(); // * 이분 탐색을 이용한 LIS

        bw.write(String.valueOf(length)); // * LIS 길이 출력

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 이분탐색을 이용한 LIS
    public static void search() {
        answer = new int[n];

        answer[0] = arr[0];
        length = 1;

        for (int i = 1; i < n; i++) {
            if (answer[length-1] < arr[i]) {
                answer[length++] = arr[i];
            } else {
                int left = 0;
                int right = length-1;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if (answer[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                answer[left] = arr[i];
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
