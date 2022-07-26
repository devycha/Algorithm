/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/1208
 *
 * ! 제목: 부분수열의 합 2
 * ! 시간 제한: 1초
 * ! 메모리 제한: 256MB
 *
 * ! 문제
 * N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1 ≤ N ≤ 40, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.
 *
 * 5 0          -> n s
 * -7 -3 -2 5 8 -> arr
 *
 * ! 출력
 * 첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.
 *
 * 1
 *
 * ? 채점 결과
 * 시간: 848ms
 * 메모리: 30848KB
 * 언어: JAVA8
 */
package Gold.I;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G1_1208 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // 파싱
    static int n, s, arr[];
    // 초기 설정
    static long count = 0;

    public static void main(String[] args) throws IOException {
        input();
        int half = n / 2;
        int[] a = new int[1 << (n-half)];
        int[] b = new int[1 << half];

        // ! 2의 (n-half)제곱의 경우의 수만큼 부분수열이 생김
        for (int i = 0; i < (1 << n-half); i++) {
            for (int j = 0; j < n-half; j++) {
                if (((1 << j) & i) != 0) {
                    a[i] += arr[j];
                }
            }
        }

        // ! 2의 half 제곱의 경우의 수만ㄴ큼 부분수열이 생김
        for (int i = 0; i < (1 << half); i++) {
            for (int j = 0; j < half; j++) {
                if (((1 << j) & i) != 0) {
                    b[i] += arr[n-half+j];
                }
            }
        }
        
        // * 두 배열 모두 정렬
        Arrays.sort(a);
        Arrays.sort(b);

        // * 투 포인터를 이용한 부분집합들
        int ap = 0;
        int bp = b.length-1;

        // * 합이 s가 되는 구간들을 곱함
        // A가 1부터 3까지 3개가 같은값
        // B가 1부터 2까지 2개가 같은값
        // 이라면,
        // count += 3 * 2
        while (ap < a.length && bp >= 0) {
            int av = a[ap];
            int bv = b[bp];

            if (av + bv > s) {
                bp--;
            } else if (av + bv < s) {
                ap++;
            } else {
                long ac = 0;
                long bc = 0;

                while (ap < a.length && av == a[ap]) {
                    ac++;
                    ap++;
                }

                while (bp >= 0 && bv == b[bp]) {
                    bc++;
                    bp--;
                }

                count += (ac * bc);
            }
        }

        if (s == 0) count--; // 크기가 양수인 부분집합이어야 하므로 s가 0일 경우에 카운트 1개를 빼줌
        bw.write(String.valueOf(count));

        br.close();
        bw.flush();
        bw.close();
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
