/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1541
 *
 * ? 제목: 잃어버린 괄호
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 세준이는 양수와 +, -, 그리고 괄호를 가지고 식을 만들었다. 그리고 나서 세준이는 괄호를 모두 지웠다.
 * 그리고 나서 세준이는 괄호를 적절히 쳐서 이 식의 값을 최소로 만들려고 한다.
 * 괄호를 적절히 쳐서 이 식의 값을 최소로 만드는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 식이 주어진다. 식은 ‘0’~‘9’, ‘+’, 그리고 ‘-’만으로 이루어져 있고, 가장 처음과 마지막 문자는 숫자이다. 그리고 연속해서 두 개 이상의 연산자가 나타나지 않고, 5자리보다 많이 연속되는 숫자는 없다. 수는 0으로 시작할 수 있다. 입력으로 주어지는 식의 길이는 50보다 작거나 같다.
 *
 * 55-50+40 -> str
 *
 * ? 출력
 * 첫째 줄에 정답을 출력한다.
 * 
 * 35
 *
 * ? 채점 결과
 * * 시간: 0.088초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_1541 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        String[] substracts = str.split("-");
        int total = 0;
        for (int i = 0; i < substracts.length; i++) {
            String[] sums = substracts[i].split("\\+");
            int sum = 0;
            for (String s : sums) {
                sum += Integer.parseInt(s);
            }

            if (i == 0) {
                total += sum;
            } else {
                total -= sum;
            }
        }

        System.out.println(total);
    }
}
