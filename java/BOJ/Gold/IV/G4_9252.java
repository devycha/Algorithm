/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/9252
 *
 * ? 제목: LCS2
 * ? 시간 제한: 0.1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
 * 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
 *
 * ? 입력 & 파싱
 * 첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.
 *
 * ACAYKP   -> s1
 * CAPCAK   -> s2
 *
 * ? 출력
 * 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를, 둘째 줄에 LCS를 출력한다.
 * LCS가 여러 가지인 경우에는 아무거나 출력하고, LCS의 길이가 0인 경우에는 둘째 줄을 출력하지 않는다.
 *
 * 4
 * ACAK
 *
 * ? 채점 결과
 * * 시간: 0.104초
 * * 메모리: 15MB
 * * 언어: JAVA8
 * * 시도: 4
 */
package Gold.IV;

import java.io.*;

public class G4_9252 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n1, n2;
    static char[] s1, s2;
    static int[][] lcs;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP(Tabulation: Bottom-Up) : LCS
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (s1[i-1] == s2[j-1]) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }

        // * 3. LCS 문자열 구하기
        StringBuffer sb = new StringBuffer();
        int i = n1, j = n2;
        while (i > 0 && j > 0) {
            if (lcs[i][j] == lcs[i-1][j]) {
                i--;
            } else if (lcs[i][j] == lcs[i][j-1]) {
                j--;
            } else {
                sb.append(s1[i-1]);
                i--;
                j--;
            }
        }

        // * 4. 정답 출력
        System.out.println(sb.length());
        if (sb.length() != 0) {
            System.out.println(sb.reverse().toString());
        }
    }

    // 입력 받기
    public static void input() throws IOException {
        s1 = br.readLine().toCharArray();
        s2 = br.readLine().toCharArray();

        n1 = s1.length;
        n2 = s2.length;

        lcs = new int[n1+1][n2+1];
    }
}
