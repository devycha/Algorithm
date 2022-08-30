/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/9251
 *
 * ? 제목: LCS
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
 * ACAYKP -> word1
 * CAPCAK -> word2
 *
 * ? 출력
 * 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.
 *
 * 4
 *
 * ? 채점 결과
 * * 시간: 104ms
 * * 메모리: 15MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_9251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // * 파싱
    static char[] word1, word2;
    
    // * 초기 설정
    static int[][] lcs;

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. Bottom-Up 방식 LCS
        for (int i = 1; i <= word1.length; i++) {
            for (int j = 1; j <= word2.length; j++) {
                if (word1[i - 1] == word2[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }

        System.out.println(lcs[word1.length][word2.length]);
    }

    // ! 입력 받기
    public static void input() throws IOException {
        word1 = br.readLine().toCharArray();
        word2 = br.readLine().toCharArray();

        lcs = new int[word1.length+1][word2.length+1];

    }
}
