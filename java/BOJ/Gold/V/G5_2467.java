/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2467
 *
 * ? 제목: 용액
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * KOI 부설 과학연구소에서는 많은 종류의 산성 용액과 알칼리성 용액을 보유하고 있다. 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다. 산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고, 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.
 * 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다. 이 연구소에서는 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
 * 예를 들어, 주어진 용액들의 특성값이 [-99, -2, -1, 4, 98]인 경우에는 특성값이 -99인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고, 이 용액의 특성값이 0에 가장 가까운 용액이다. 참고로, 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
 * 산성 용액과 알칼리성 용액의 특성값이 정렬된 순서로 주어졌을 때, 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에는 전체 용액의 수 N이 입력된다. N은 2 이상 100,000 이하의 정수이다. 둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 오름차순으로 입력되며, 이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다. N개의 용액들의 특성값은 모두 서로 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.
 *
 * 5                -> n
 * -99 -2 -1 4 98   -> arr[0] ~ arr[n-1]
 *
 * ? 출력
 * 첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력한다. 출력해야 하는 두 용액은 특성값의 오름차순으로 출력한다. 특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력한다.
 *
 * -99 98
 *
 * ? 채점 결과
 * * 시간: 0.296초
 * * 메모리: 32MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.V;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_2467 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static int[] arr;

    // * 초기 설정
    static int max = Integer.MAX_VALUE;
    static int[] result;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. 이분탐색
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            int val = arr[i];

            while (left <= right) {
                int mid = (left + right) / 2;
                int sum = val + arr[mid];

                if (Math.abs(sum) <= max && mid != i) {
                    max = Math.abs(sum);
                    result[0] = arr[i];
                    result[1] = arr[mid];
                }

                if (sum > 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        // * 3. 출력
        if (result[0] > result[1]) {
            bw.write(result[1] + " " + result[0]);
        } else {
            bw.write(result[0] + " " + result[1]);
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        result = new int[2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
    }
}
