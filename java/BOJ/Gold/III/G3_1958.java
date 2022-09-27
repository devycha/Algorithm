/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1958
 *
 * ? 제목: LCS 3
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 문자열과 놀기를 세상에서 제일 좋아하는 영식이는 오늘도 문자열 2개의 LCS(Longest Common Subsequence)를 구하고 있었다. 어느 날 영식이는 조교들이 문자열 3개의 LCS를 구하는 것을 보았다. 영식이도 도전해 보았지만 실패하고 말았다.
 * 이제 우리가 할 일은 다음과 같다. 영식이를 도와서 문자열 3개의 LCS를 구하는 프로그램을 작성하라.
 *
 * ? 입력
 * 첫 줄에는 첫 번째 문자열이, 둘째 줄에는 두 번째 문자열이, 셋째 줄에는 세 번째 문자열이 주어진다. 각 문자열은 알파벳 소문자로 이루어져 있고, 길이는 100보다 작거나 같다.
 *
 * abcdefghijklmn   -> str1
 * bdefg            -> str2
 * efg              -> str3
 *
 * ? 출력
 * 첫 줄에 첫 번째 문자열과 두 번째 문자열과 세 번째 문자열의 LCS의 길이를 출력한다.
 *
 * 3
 *
 * ? 채점 결과
 * * 시간: 0.116초
 * * 메모리: 17MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.III;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G3_1958 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // * 파싱
    static String str1, str2, str3;
    
    // * 초기 설정
    static char[] arr1, arr2, arr3;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. DP(Tabulation: Bottom-Up)
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                for (int k = 1; k <= str3.length(); k++) {
                    if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                    }
                }
            }
        }

        // * 3. 출력
        System.out.println(dp[str1.length()][str2.length()][str3.length()]);
    }

    public static void input() throws IOException {
        str1 = br.readLine();
        str2 = br.readLine();
        str3 = br.readLine();

        arr1 = new char[str1.length()+1];
        arr2 = new char[str2.length()+1];
        arr3 = new char[str3.length()+1];

        dp = new int[arr1.length][arr2.length][arr3.length];

        for (int i = 0; i < str1.length(); i++) {
            arr1[i+1] = str1.charAt(i);
        }

        for (int i = 0; i < str2.length(); i++) {
            arr2[i+1] = str2.charAt(i);
        }

        for (int i = 0; i < str3.length(); i++) {
            arr3[i+1] = str3.charAt(i);
        }

    }
}
