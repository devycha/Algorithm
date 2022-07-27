/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/9527
 *
 * ! 제목: 1의 개수 세기
 * ! 시간 제한: 1초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 두 자연수 A, B가 주어졌을 때, A ≤ x ≤ B를 만족하는 모든 x에 대해 x를 이진수로 표현했을 때 1의 개수의 합을 구하는 프로그램을 작성하시오.
 * 즉, f(x) = x를 이진수로 표현 했을 때 1의 개수라고 정의하고, 아래 식의 결과를 구하자.
 *
 * ! 입력 & 파싱
 * 첫 줄에 두 자연수 A, B가 주어진다. (1 ≤ A ≤ B ≤ 1016)
 *
 * 2 12 -> a b
 *
 * ! 출력
 * 1의 개수를 세어 출력한다.
 *
 * 21
 *
 * ? 채점 결과
 * 시간: 76ms
 * 메모리: 11636KB
 * 언어: JAVA8
 */
package Gold.II;

import java.io.*;
import java.util.StringTokenizer;

public class G2_9527 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // 파싱
    static long a, b;
    
    // ! 초기 설정 : 1의 갯수 누적합 => arr[n]: 2진법 n의 자리수까지의 1의 개수의 총합
    static long[] arr = new long[55];

    // 메인
    public static void main(String[] args) throws IOException {
        input(); // 입력 받기
        arr[0] = 0L; // 초기값 설정

        // ! 점화식을 이용한 누적합 구하기
        for (int i = 1; i <= 54; i++) {
            arr[i] = (long) Math.pow(2, i-1) + 2 * arr[i-1];
        }

        // ! b 까지의 1의개수 누적합 - (a-1)까지의 1의개수 누적합 출력
        bw.write(String.valueOf(getOne(b) - getOne(a-1)));

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // 누적합구하기
    public static long getOne(long num) {
        long answer = 0L;
        String bin_num = Long.toBinaryString(num);
        int length = bin_num.length();
        for (int i = 0; i < length; i++) {
            if (bin_num.charAt(i) == '1') {
                int pow = length - i - 1;
                answer += num - ((long) Math.pow(2, pow) - 1);
                answer += arr[pow];
                num -= (long) Math.pow(2, pow);
            }
        }
        return answer;
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
    }
}