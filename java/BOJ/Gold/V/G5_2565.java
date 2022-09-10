/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2565
 *
 * ? 제목: 전깃줄
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 두 전봇대 A와 B 사이에 하나 둘씩 전깃줄을 추가하다 보니 전깃줄이 서로 교차하는 경우가 발생하였다. 합선의 위험이 있어 이들 중 몇 개의 전깃줄을 없애 전깃줄이 교차하지 않도록 만들려고 한다.
 * 예를 들어, < 그림 1 >과 같이 전깃줄이 연결되어 있는 경우 A의 1번 위치와 B의 8번 위치를 잇는 전깃줄, A의 3번 위치와 B의 9번 위치를 잇는 전깃줄, A의 4번 위치와 B의 1번 위치를 잇는 전깃줄을 없애면 남아있는 모든 전깃줄이 서로 교차하지 않게 된다.
 * 전깃줄이 전봇대에 연결되는 위치는 전봇대 위에서부터 차례대로 번호가 매겨진다. 전깃줄의 개수와 전깃줄들이 두 전봇대에 연결되는 위치의 번호가 주어질 때, 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 * 파싱
 * 첫째 줄에는 두 전봇대 사이의 전깃줄의 개수가 주어진다. 전깃줄의 개수는 100 이하의 자연수이다. 둘째 줄부터 한 줄에 하나씩 전깃줄이 A전봇대와 연결되는 위치의 번호와 B전봇대와 연결되는 위치의 번호가 차례로 주어진다. 위치의 번호는 500 이하의 자연수이고, 같은 위치에 두 개 이상의 전깃줄이 연결될 수 없다.
 *
 * 8    -> n
 * 1 8  -> arr[0][0] arr[0][1]
 * 3 9
 * 2 2
 * 4 1
 * 6 4
 * 10 10
 * 9 7
 * 7 6  -> arr[n-1][0] arr[n-1][1]
 *
 * ? 출력
 * 첫째 줄에 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 출력한다.
 *
 * 3
 *
 * ? 채점 결과
 * * 시간: 0.22초
 * * 메모리: 18MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.V;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_2565 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기 (B전봇대를 기준으로 정렬)
        int lis = LIS(); // * 2. LIS
        System.out.println(n - lis); // * 3. 전봇대 갯수에서 LIS 길이를 뺀 값 출력
    }

    // * LIS
    public static int LIS() {
        int[] dp = new int[n];
        dp[0] = arr[0][0];
        int p = 1;

        for (int i = 1; i < n; i++) {
            if (dp[p-1] < arr[i][0]) {
                dp[p++] = arr[i][0];
            } else {
                int left = 0;
                int right = p-1;

                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (dp[mid] == arr[i][0]) {
                        break;
                    }

                    if (dp[mid] > arr[i][0]) {
                        right = mid-1;
                    } else {
                        left = mid+1;
                    }
                }

                dp[left] = arr[i][0];
            }
        }

        return p;
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[i] = new int[] {a, b};
        }

        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
    }
}