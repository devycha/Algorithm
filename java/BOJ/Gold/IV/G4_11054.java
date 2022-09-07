/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11054
 *
 * ? 제목: 가장 긴 바이토닉 부분 수열
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면, 그 수열을 바이토닉 수열이라고 한다.
 * 예를 들어, {10, 20, 30, 25, 20}과 {10, 20, 30, 40}, {50, 40, 25, 10} 은 바이토닉 수열이지만,  {1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.
 * 수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 수열 A의 크기 N이 주어지고, 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ Ai ≤ 1,000)
 *
 * 10                   -> n
 * 1 5 2 1 4 3 4 5 2 1  -> arr[0] ~ arr[n-1] & reverseArr[n-1] ~ reverseArr[0]
 *
 * ? 출력
 * 첫째 줄에 수열 A의 부분 수열 중에서 가장 긴 바이토닉 수열의 길이를 출력한다.
 *
 * 7
 *
 * ? 채점 결과
 * * 시간: 84ms
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_11054 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[] arr;
    static int[] reverseArr;

    // * 초기 설정
    static int[] LIS;
    static int[] REVERSE_LIS;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        getLIS(arr, LIS); // * 2. 정방향 LIS
        getLIS(reverseArr, REVERSE_LIS); // * 3. 역방향 LIS

        // * 4. 각 인덱스 위치에서의 정,역방향 LIS 길이의 값의 최댓값 비교
        for (int i = 0; i < n; i++) {
            max = Math.max(max, LIS[i] + REVERSE_LIS[n-i-1] + 1);
        }

        // * 5. 출력
        System.out.println(max);
    }

    // * DP(Tabulation: Bottom-Up) - LIS + Binary Search
    public static void getLIS(int[] numList, int[] lis) {
        int[] lisNum = new int[n];

        int p = 1;
        lisNum[0] = numList[0];
        lis[0] = 0;

        for (int i = 0; i < n; i++) {
            if (lisNum[p-1] < numList[i]) {
                lis[i] = p;
                lisNum[p++] = numList[i];
            } else {
                int left = 0;
                int right = p - 1;

                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (lisNum[mid] == numList[i]) {
                        left = mid;
                        break;
                    } else if (lisNum[mid] < numList[i]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                lis[i] = left;
                lisNum[left] = numList[i];
            }
        }
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        LIS = new int[n];
        REVERSE_LIS = new int[n];
        arr = new int[n];
        reverseArr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            reverseArr[n-i-1] = arr[i];
        }
    }
}
