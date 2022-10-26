/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11058
 *
 * ? 문제: 크리보드
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 크리보드는 kriii가 만든 신기한 키보드이다. 크리보드에는 버튼이 4개만 있으며, 하는 역할은 다음과 같다.
 * 화면에 A를 출력한다.
 * Ctrl-A: 화면을 전체 선택한다
 * Ctrl-C: 전체 선택한 내용을 버퍼에 복사한다
 * Ctrl-V: 버퍼가 비어있지 않은 경우에는 화면에 출력된 문자열의 바로 뒤에 버퍼의 내용을 붙여넣는다.
 * 크리보드의 버튼을 총 N번 눌러서 화면에 출력된 A개수를 최대로하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.
 *
 * 3 -> n
 *
 * ? 출력
 * 크리보드의 버튼을 총 N번 눌러서 화면에 출력할 수 있는 A 개수의 최댓값을 출력한다.
 *
 * 3
 *
 * ? 채점 결과
 * * 시간: 0.076초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_11058 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n+1];

        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= 5; j++) {
                if (i - j - 1 > 0) {
                    dp[i] = Math.max(dp[i-(j+1)] * j, dp[i]);
                }
            }
        }

        System.out.println(dp[n]);
    }
}
