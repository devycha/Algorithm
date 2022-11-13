/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11047
 *
 * ? 제목: 동전 0
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 준규가 가지고 있는 동전은 총 N종류이고, 각각의 동전을 매우 많이 가지고 있다.
 * 동전을 적절히 사용해서 그 가치의 합을 K로 만들려고 한다. 이때 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * ? 입력
 * 첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000)
 * 둘째 줄부터 N개의 줄에 동전의 가치 Ai가 오름차순으로 주어진다. (1 ≤ Ai ≤ 1,000,000, A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수)
 *
 * 10 4200  -> n k
 * 1        -> coin[0]
 * 5
 * 10
 * 50
 * 100
 * 500
 * 1000
 * 5000
 * 10000
 * 50000    -> coin[n-1]
 *
 * ? 출력
 * 첫째 줄에 K원을 만드는데 필요한 동전 개수의 최솟값을 출력한다.
 *
 * 12
 *
 * ? 채점 결과
 * * 시간: 0.076초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.IV;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class S4_11047 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, k;
    static Integer[] coin;

    public static void main(String[] args) throws IOException {
        input();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int c = k / coin[i];
            count += c;
            k -= coin[i] * c;
        }
        System.out.println(count);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coin = new Integer[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coin, Collections.reverseOrder());
    }
}
