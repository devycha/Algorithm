/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/1806
 *
 * ! 제목: 부분합
 * ! 시간제한: 0.5초
 * ! 메모리제한: 128MB
 *
 * ! 문제
 * 10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다. 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다. 둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.
 *
 * 10 15                -> n s
 * 5 1 3 5 10 7 4 9 2 8 -> arr
 *
 * ! 출력
 * 첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.
 *
 * 2
 *
 * ? 채점 결과
 * 메모리: 28068KB
 * 시간: 412ms
 * 언어: JAVA
*/
package Gold.IV;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_1806 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n, s;
    static int[] arr;
    static int count = 0;
    static int answer = 100000;

    public static void main(String[] args) throws IOException {
        input();
        int[] sum = new int[arr.length+1];
        int t = Arrays.stream(arr).reduce(0, (tot, el) -> {
            sum[count++] = tot;
            return tot + el;
        });
        sum[count] = t;

        int left = 0;
        int right = 0;

        while (right < sum.length) {
            if (sum[right] >= s) {
                while (sum[right] - sum[left+1] >= s) left++;
                if (right - left < answer) answer = right - left;
            }
            right++;
        }
        bw.write(String.valueOf(answer == 100000 ? 0 : answer));
        bw.flush();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
