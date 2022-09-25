/**
 * ? 문제 출처: 백준 온라인 져지
 * ? https://www.acmicpc.net/problem/2011
 *
 * ? 제목: 암호 코드
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 상근이와 선영이가 다른 사람들이 남매간의 대화를 듣는 것을 방지하기 위해서 대화를 서로 암호화 하기로 했다. 그래서 다음과 같은 대화를 했다.
 *
 * 상근: 그냥 간단히 암호화 하자. A를 1이라고 하고, B는 2로, 그리고 Z는 26으로 하는거야.
 * 선영: 그럼 안돼. 만약, "BEAN"을 암호화하면 25114가 나오는데, 이걸 다시 글자로 바꾸는 방법은 여러 가지가 있어.
 * 상근: 그렇네. 25114를 다시 영어로 바꾸면, "BEAAD", "YAAD", "YAN", "YKD", "BEKD", "BEAN" 총 6가지가 나오는데, BEAN이 맞는 단어라는건 쉽게 알수 있잖아?
 * 선영: 예가 적절하지 않았네 ㅠㅠ 만약 내가 500자리 글자를 암호화 했다고 해봐. 그 때는 나올 수 있는 해석이 정말 많은데, 그걸 언제 다해봐?
 * 상근: 얼마나 많은데?
 * 선영: 구해보자!
 * 어떤 암호가 주어졌을 때, 그 암호의 해석이 몇 가지가 나올 수 있는지 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 5000자리 이하의 암호가 주어진다. 암호는 숫자로 이루어져 있다.
 *
 * 25114 -> str
 *
 * ? 출력
 * 나올 수 있는 해석의 가짓수를 구하시오. 정답이 매우 클 수 있으므로, 1000000으로 나눈 나머지를 출력한다.
 * 암호가 잘못되어 암호를 해석할 수 없는 경우에는 0을 출력한다.
 *
 * 6
 *
 * ? 채점 결과
 * * 시간: 0.076초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Gold.V;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_2011 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // * 파싱(입력받기)
        String str = br.readLine();

        // * 초기 설정
        int len = str.length();
        int div = 1000000;
        boolean isError = false;

        // * DP 테이블 초기화
        int[] arr = new int[len+1];
        int[] dp = new int[len+1];

        // * 예외 처리
        if (str.length() == 0) {
            isError = true;
        }
        else if (str.charAt(0) == '0') {
            isError = true;
        }

        // * DP (Tabulation: Bottom-Up)
        else {
            for (int i = 1; i <= len; i++) {
                arr[i] = str.charAt(i-1) - '0';
            }

            // * Start Case
            dp[0] = 1;
            dp[1] = 1;

            for (int i = 2; i <= len; i++) {
                int num = arr[i-1] * 10 + arr[i];
                if (arr[i] == 0) { // 0인 곳은 앞자리와 같이 암호가 되어야만 함
                    if (!isOk(num)) { // 앞자리와 합친 값이 invalid 할 때
                        isError = true;
                        break;
                    } else {
                        dp[i] = dp[i-2];
                    }
                } else {
                    if (isOk(num) && arr[i-1] != 0) { // 앞자리와 합친 값이 정상적일 때
                        dp[i] = (dp[i-1] % div  + dp[i-2] % div) % div;
                    } else { // 앞자리와 합칠 수 없을 때
                        dp[i] = dp[i-1];
                    }
                }
            }
        }

        // * 출력
        System.out.println(isError ? 0 : dp[len]);
    }

    public static boolean isOk(int num) {
        return num >= 1 && num <= 26;
    }
}
