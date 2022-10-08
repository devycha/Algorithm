/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2631
 *
 * ? 제목: 줄세우기
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ?문제
 * KOI 어린이집에는 N명의 아이들이 있다. 오늘은 소풍을 가는 날이다. 선생님은 1번부터 N번까지 번호가 적혀있는 번호표를 아이들의 가슴에 붙여주었다. 선생님은 아이들을 효과적으로 보호하기 위해 목적지까지 번호순서대로 일렬로 서서 걸어가도록 하였다. 이동 도중에 보니 아이들의 번호순서가 바뀌었다. 그래서 선생님은 다시 번호 순서대로 줄을 세우기 위해서 아이들의 위치를 옮기려고 한다. 그리고 아이들이 혼란스러워하지 않도록 하기 위해 위치를 옮기는 아이들의 수를 최소로 하려고 한다.
 * 예를 들어, 7명의 아이들이 다음과 같은 순서대로 줄을 서 있다고 하자.
 *
 * 3 7 5 2 6 1 4
 *
 * 아이들을 순서대로 줄을 세우기 위해, 먼저 4번 아이를 7번 아이의 뒤로 옮겨보자. 그러면 다음과 같은 순서가 된다.
 *
 * 3 7 4 5 2 6 1
 *
 * 이제, 7번 아이를 맨 뒤로 옮긴다.
 *
 * 3 4 5 2 6 1 7
 *
 * 다음 1번 아이를 맨 앞으로 옮긴다.
 *
 * 1 3 4 5 2 6 7
 *
 * 마지막으로 2번 아이를 1번 아이의 뒤로 옮기면 번호 순서대로 배치된다.
 *
 * 1 2 3 4 5 6 7
 *
 * 위의 방법으로 모두 4명의 아이를 옮겨 번호 순서대로 줄을 세운다. 위의 예에서 3명의 아이만을 옮겨서는 순서대로 배치할 수가 없다. 따라서, 4명을 옮기는 것이 가장 적은 수의 아이를 옮기는 것이다.*
 * N명의 아이들이 임의의 순서로 줄을 서 있을 때, 번호 순서대로 배치하기 위해 옮겨지는 아이의 최소 수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에는 아이들의 수 N이 주어진다. 둘째 줄부터는 1부터 N까지의 숫자가 한 줄에 하나씩 주어진다. N은 2 이상 200 이하의 정수이다.
 *
 * 7    -> n
 * 3    -> arr[0]
 * 7
 * 5
 * 2
 * 6
 * 1
 * 4    -> arr[n-1]
 *
 * ? 출력
 * 첫째 줄에는 번호 순서대로 줄을 세우는데 옮겨지는 아이들의 최소 수를 출력한다.
 *
 * 4
 *
 * ? 채점 결과
 * * 시간: 0.084초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_2631 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // * 파싱
    static int n;
    static int[] arr;
    
    // * 초기 설정
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. LIS
        int p = 1;
        dp[0] = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] > dp[p-1]) {
                dp[p++] = arr[i];
            } else {
                int left = 0;
                int right = p-1;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (dp[mid] == arr[i]) {
                        left = mid;
                        break;
                    } else if (dp[mid] > arr[i]) {
                        right = mid-1;
                    } else {
                        left = mid+1;
                    }
                }

                dp[left] = arr[i];
            }
        }

        // * 3. 출력(전체 길이에서 LIS 길이를 뺀 값을 출력)
        System.out.println(n - p);
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        dp = new int[n];
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }
}
